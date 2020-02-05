use bdHulkStore
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF object_id('dbo.spComprarProducto') IS NULL
BEGIN
    EXEC sp_executesql N'CREATE PROCEDURE dbo.spComprarProducto AS SELECT 1;'
END
GO
/*---------------------------------------------------------------------------------
* Metodo o PRG:  spComprarProducto
* Desarrollado por: <\A Ing. Crithian Hernandez									  A\>
* Descripcion: <\D Registra compras de productos								  D\>
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
--exec dbo.[spComprarProducto] '{"cnsctvo_prdcto": 1, "cntdd": 2, "prcio_cmpra": 20000, "usro_crcn":"user_HulkStore"}'
ALTER PROCEDURE dbo.[spComprarProducto] @jsonPrdcto varchar(max)
As

BEGIN
	SET NOCOUNT ON;

	Declare
	@tmptbDetalleCompraProducto table(
			cnsctvo_prdcto int,
			cntdd int,
			prcio_cmpra int,
			fcha_crcn datetime,
			usro_crcn varchar(20)
	) 

   INSERT INTO @tmptbDetalleCompraProducto
           (cnsctvo_prdcto
           ,cntdd
           ,prcio_cmpra
           ,usro_crcn)
   SELECT 
			JsonData.cnsctvo_prdcto
           ,JsonData.cntdd
           ,JsonData.prcio_cmpra
           ,JsonData.usro_crcn
  FROM OPENJSON(@jsonPrdcto)
   WITH (
			cnsctvo_prdcto INT		'$.cnsctvo_prdcto',
			cntdd INT				'$.cntdd',
			prcio_cmpra INT			'$.prcio_cmpra',
			usro_crcn VARCHAR(20)	'$.usro_crcn'
  ) AS JsonData;

  INSERT INTO dbo.tbDetalleCompraProducto
           (cnsctvo_prdcto
           ,cntdd
           ,prcio_cmpra
           ,usro_crcn)
   SELECT 
			cnsctvo_prdcto
           ,cntdd
           ,prcio_cmpra
           ,usro_crcn
  FROM     @tmptbDetalleCompraProducto

  Update a
  Set	a.cntdd_stck=a.cntdd_stck+b.cntdd
  From	dbo.tbProductos a
  Inner join @tmptbDetalleCompraProducto b
  On	b.cnsctvo_prdcto=a.cnsctvo_prdcto

END
GO
grant execute on dbo.spComprarProducto to user_HulkStore
GO
exec sp_recompile 'dbo.spComprarProducto'
GO