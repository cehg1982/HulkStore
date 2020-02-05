use bdHulkStore
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF object_id('dbo.spReporteProductos') IS NULL
BEGIN
    EXEC sp_executesql N'CREATE PROCEDURE dbo.spReporteProductos AS SELECT 1;'
END
GO
/*---------------------------------------------------------------------------------
* Metodo o PRG:  spReporteProductos
* Desarrollado por: <\A Ing. Crithian Hernandez									  A\>
* Descripcion: <\D Reporte de productos											  D\>
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
--exec dbo.[spReporteProductos] 1, '2020-02-03', '2020-02-04'
ALTER PROCEDURE dbo.[spReporteProductos] @cnsctvo_prdcto int, @fhcInco date, @fchFn date
As

BEGIN
	SET NOCOUNT ON;

	Declare
	@tmptbReposteProductos table(
			cnsctvo_prdcto	int,
			nmbre_prdcto	varchar(50),
			dscrpcon_prdcto varchar(500),
			cntdd_stck		int,
			cntdd_cmpra		int,
			cntdd_vnta		int,
			prcio_cmpra		int,
			prcio_untro		int,
			total_cmpra		int,
			total_vnta		int,
			utldd			int
	) 

	If @cnsctvo_prdcto is not null and @fhcInco is not null and @fchFn is not null
	   INSERT INTO @tmptbReposteProductos
				   (cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_cmpra,
					cntdd_vnta,
					prcio_cmpra,
					prcio_untro,
					total_cmpra,
					total_vnta,
					utldd)
	   SELECT		a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(b.cntdd),
					sum(c.cntdd),
					b.prcio_cmpra,
					c.prcio_untro,
					sum(b.prcio_cmpra),
					sum(c.prcio_untro),
					sum(c.prcio_untro)-sum(b.prcio_cmpra)
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleCompraProducto] b
	   On			b.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Inner join	[dbo].[tbDetalleVentaProducto] c
	   On			c.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		a.cnsctvo_prdcto=@cnsctvo_prdcto
	   And			b.fcha_crcn between @fhcInco and @fchFn
	   And			c.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, b.prcio_cmpra, c.prcio_untro 

	If @cnsctvo_prdcto is null and @fhcInco is not null and @fchFn is not null
	   INSERT INTO @tmptbReposteProductos
				   (cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_cmpra,
					cntdd_vnta,
					prcio_cmpra,
					prcio_untro,
					total_cmpra,
					total_vnta,
					utldd)
		   SELECT	a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(b.cntdd),
					sum(c.cntdd),
					b.prcio_cmpra,
					c.prcio_untro,
					sum(b.prcio_cmpra),
					sum(c.prcio_untro),
					sum(c.prcio_untro)-sum(b.prcio_cmpra)
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleCompraProducto] b
	   On			b.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Inner join	[dbo].[tbDetalleVentaProducto] c
	   On			c.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		b.fcha_crcn between @fhcInco and @fchFn
	   And			c.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, b.prcio_cmpra, c.prcio_untro

   Select	cnsctvo_prdcto,
			nmbre_prdcto,
			dscrpcon_prdcto,
			cntdd_stck,
			cntdd_cmpra,
			cntdd_vnta,
			prcio_cmpra,
			prcio_untro,
			total_cmpra,
			total_vnta,
			utldd
	From	@tmptbReposteProductos

END
GO
grant execute on dbo.spReporteProductos to user_HulkStore
GO
exec sp_recompile 'dbo.spReporteProductos'
GO