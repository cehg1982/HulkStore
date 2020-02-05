use bdHulkStore
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF object_id('dbo.spConsultarProducto') IS NULL
BEGIN
    EXEC sp_executesql N'CREATE PROCEDURE dbo.spConsultarProducto AS SELECT 1;'
END
GO
/*---------------------------------------------------------------------------------
* Metodo o PRG:  spConsultarProducto
* Desarrollado por: <\A Ing. Crithian Hernandez									  A\>
* Descripcion: <\D Consulta la información referente al	producto				  D\>
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
--exec dbo.[spConsultarProducto]
ALTER PROCEDURE dbo.[spConsultarProducto]
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
		cnsctvo_prdcto
		,nmbre_prdcto
		,dscrpcon_prdcto
		,cntdd_stck
		,stado
		,fcha_crcn
		,usro_crcn
		,fcha_ultma_mdfccn
		,usro_ultma_mdfccn
	)
	SELECT cnsctvo_prdcto
		,nmbre_prdcto
		,dscrpcon_prdcto
		,cntdd_stck
		,stado
		,fcha_crcn
		,usro_crcn
		,fcha_ultma_mdfccn
		,usro_ultma_mdfccn
	FROM dbo.tbProductos

	SELECT [cnsctvo_prdcto]
		,[nmbre_prdcto]
		,[dscrpcon_prdcto]
		,[cntdd_stck]
		,[stado]
		,[fcha_crcn]
		,[usro_crcn]
		,[fcha_ultma_mdfccn]
		,[usro_ultma_mdfccn]
	FROM @tmptbProductos

END
GO
grant execute on dbo.spConsultarProducto to user_HulkStore
GO
exec sp_recompile 'dbo.spConsultarProducto'
GO