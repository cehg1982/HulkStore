use bdHulkStore
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF object_id('dbo.spCrearProducto') IS NULL
BEGIN
    EXEC sp_executesql N'CREATE PROCEDURE dbo.spCrearProducto AS SELECT 1;'
END
GO
/*---------------------------------------------------------------------------------
* Metodo o PRG:  spCrearProducto
* Desarrollado por: <\A Ing. Crithian Hernandez									  A\>
* Descripcion: <\D Registra productos											  D\>
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
--exec dbo.[spCrearProducto] '{"nmbrePrdcto": "taza", "dscrpconPrdcto": "avengers", "cntddStck":3, "usroCrcn":"user_HulkStore"}'
ALTER PROCEDURE dbo.[spCrearProducto] @jsonPrdcto varchar(max)
As

BEGIN
	SET NOCOUNT ON;

	declare @tmptbProductos table(
		cnsctvo_prdcto [int],
		nmbre_prdcto [varchar](50),
		dscrpcon_prdcto [varchar](500),
		cntdd_stck [int],
		stado [varchar](1),
		fcha_crcn [datetime],
		usro_crcn [varchar](20),
		fcha_ultma_mdfccn [datetime],
		usro_ultma_mdfccn [varchar](20)
	) 

   Insert into @tmptbProductos(
		nmbre_prdcto
		,dscrpcon_prdcto
		,cntdd_stck
		,usro_crcn
	)
   SELECT 
            JsonData.nmbrePrdcto
           ,JsonData.dscrpcon_prdcto
           ,JsonData.cntdd_stck
		   ,JsonData.usro_crcn
  FROM OPENJSON(@jsonPrdcto)
   WITH (
			nmbrePrdcto  VARCHAR(20)	'$.nmbrePrdcto',
			dscrpcon_prdcto VARCHAR(20)	'$.dscrpconPrdcto',
			cntdd_stck INT				'$.cntddStck',
			usro_crcn VARCHAR(20)		'$.usroCrcn'
  ) AS JsonData;

  INSERT INTO dbo.tbProductos
           (nmbre_prdcto
			,dscrpcon_prdcto
			,cntdd_stck
			,usro_crcn)
   SELECT 
			nmbre_prdcto
			,dscrpcon_prdcto
			,cntdd_stck
			,usro_crcn
  FROM     @tmptbProductos

END
GO
grant execute on dbo.spCrearProducto to user_HulkStore
GO
exec sp_recompile 'dbo.spCrearProducto'
GO