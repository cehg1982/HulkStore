use bdHulkStore
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF object_id('dbo.spVentasProducto') IS NULL
BEGIN
    EXEC sp_executesql N'CREATE PROCEDURE dbo.spVentasProducto AS SELECT 1;'
END
GO
/*---------------------------------------------------------------------------------
* Metodo o PRG:  spVentasProducto
* Desarrollado por: <\A Ing. Crithian Hernandez									  A\>
* Descripcion: <\D Registra ventas de productos								  D\>
* Observaciones: <\O															  O\>
* Parametros: <\P																  P\>
* Variables: <\V  										  						  V\>
* Fecha Creacion: <\FC 2020/02/03												  FC\>
*---------------------------------------------------------------------------------
* DATOS DE MODIFICACION   
*---------------------------------------------------------------------------------  
* Modificado Por		 : <\AM	 					AM\>
* Descripcion			 : <\DM  DM\>
* Nuevos Parametros	 	 : <\PM   					PM\>
* Nuevas Variables		 : <\VM   					VM\>
* Fecha Modificacion	 : <\FM						FM\>
*--------------------------------------------------------------------------------- */
--exec dbo.[spVentasProducto] '{"cnsctvoPrdcto": 1, "cntdd": 1, "prcoUntro": 25000, "usro_crcn":"usroCrcn"}'
ALTER PROCEDURE [dbo].[spVentasProducto] @jsonPrdcto varchar(max)
As

BEGIN
	SET NOCOUNT ON;

	Declare
	@tmptbDetalleVentaProducto table(
			cnsctvo_prdcto int,
			cntdd int,
			prcio_untro int,
			fcha_crcn datetime,
			usro_crcn varchar(20)
	) 

   INSERT INTO @tmptbDetalleVentaProducto
           (cnsctvo_prdcto
           ,cntdd
           ,prcio_untro
           ,usro_crcn)
   SELECT 
			JsonData.cnsctvo_prdcto
           ,JsonData.cntdd
           ,JsonData.prcio_untro
           ,JsonData.usro_crcn
  FROM OPENJSON(@jsonPrdcto)
   WITH (
			cnsctvo_prdcto INT		'$.cnsctvoPrdcto',
			cntdd INT				'$.cntdd',
			prcio_untro INT			'$.prcoUntro',
			usro_crcn VARCHAR(20)	'$.usroCrcn'
  ) AS JsonData;

  INSERT INTO dbo.tbDetalleVentaProducto
           (cnsctvo_prdcto
           ,cntdd
           ,prcio_untro
           ,usro_crcn)
   SELECT 
			cnsctvo_prdcto
           ,cntdd
           ,prcio_untro
           ,usro_crcn
  FROM     @tmptbDetalleVentaProducto

  Update a
  Set	a.cntdd_stck=a.cntdd_stck-b.cntdd
  From	dbo.tbProductos a
  Inner join @tmptbDetalleVentaProducto b
  On	b.cnsctvo_prdcto=a.cnsctvo_prdcto

END
GO
grant execute on dbo.spVentasProducto to user_HulkStore
GO
exec sp_recompile 'dbo.spVentasProducto'
GO