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
--exec dbo.[spReporteProductos] 1, '2020-01-01', '2020-02-13'
ALTER PROCEDURE [dbo].[spReporteProductos] @cnsctvo_prdcto int, @fhcInco date, @fchFn date
As

BEGIN
	SET NOCOUNT ON;

	Declare
	@tmptbReporteProductosComprados table(
			cnsctvo_prdcto	int,
			nmbre_prdcto	varchar(50),
			dscrpcon_prdcto varchar(500),
			cntdd_stck		int,
			cntdd_cmpra		int,
			prcio_cmpra		int,
			total_cmpra		int
	) 

	Declare
	@tmptbReporteProductosVendidos table(
			cnsctvo_prdcto	int,
			nmbre_prdcto	varchar(50),
			dscrpcon_prdcto varchar(500),
			cntdd_stck		int,
			cntdd_vnta		int,
			prcio_untro		int,
			total_vnta		int,
			utldd			int
	) 

	If @cnsctvo_prdcto is not null and @fhcInco is not null and @fchFn is not null
	Begin
	   INSERT INTO @tmptbReporteProductosComprados
				   (cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_cmpra,
					prcio_cmpra,
					total_cmpra)
	   SELECT		a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(b.cntdd) cntdd_cmprds,
					b.prcio_cmpra,
					(b.prcio_cmpra*sum(b.cntdd)) ttl_cmprds
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleCompraProducto] b
	   On			b.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		a.cnsctvo_prdcto=@cnsctvo_prdcto
	   And			b.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, b.prcio_cmpra


	   INSERT INTO @tmptbReporteProductosVendidos
					(cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_vnta,
					prcio_untro,
					total_vnta)  
	   SELECT		a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(c.cntdd),
					c.prcio_untro,
					(c.prcio_untro*sum(c.cntdd)) ttl_vnts
					--(c.prcio_untro-a.prcio_cmpra)*sum(c.cntdd) utldd
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleVentaProducto] c
	   On			c.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		a.cnsctvo_prdcto=@cnsctvo_prdcto
	   And			c.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, c.prcio_untro

	End

	If @cnsctvo_prdcto is null and @fhcInco is not null and @fchFn is not null
	Begin
	   INSERT INTO @tmptbReporteProductosComprados
				   (cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_cmpra,
					prcio_cmpra,
					total_cmpra)
	   SELECT		a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(b.cntdd) cntdd_cmprds,
					b.prcio_cmpra,
					(b.prcio_cmpra*sum(b.cntdd)) ttl_cmprds
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleCompraProducto] b
	   On			b.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		b.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, b.prcio_cmpra


	   INSERT INTO @tmptbReporteProductosVendidos
					(cnsctvo_prdcto,
					nmbre_prdcto,
					dscrpcon_prdcto,
					cntdd_stck,
					cntdd_vnta,
					prcio_untro,
					total_vnta)  
	   SELECT		a.cnsctvo_prdcto,
					a.nmbre_prdcto,
					a.dscrpcon_prdcto,
					a.cntdd_stck,
					sum(c.cntdd),
					c.prcio_untro,
					(c.prcio_untro*sum(c.cntdd)) ttl_vnts
	   From			[dbo].tbproductos a
	   Inner join	[dbo].[tbDetalleVentaProducto] c
	   On			c.cnsctvo_prdcto=a.cnsctvo_prdcto
	   Where		c.fcha_crcn between @fhcInco and @fchFn
	   GROUP BY a.cnsctvo_prdcto, a.nmbre_prdcto, a.dscrpcon_prdcto, a.cntdd_stck, c.prcio_untro

	End

   Select	a.cnsctvo_prdcto,
			a.nmbre_prdcto,
			a.dscrpcon_prdcto,
			a.cntdd_stck,
			a.cntdd_cmpra,
			b.cntdd_vnta,
			a.prcio_cmpra,
			b.prcio_untro,
			a.total_cmpra,
			b.total_vnta,
			(b.prcio_untro-a.prcio_cmpra)*sum(b.cntdd_vnta) utldd
	From	@tmptbReporteProductosComprados a
	Inner	join @tmptbReporteProductosVendidos b
	On		a.cnsctvo_prdcto=b.cnsctvo_prdcto
	GROUP BY a.cnsctvo_prdcto,a.nmbre_prdcto,a.dscrpcon_prdcto,a.cntdd_stck,a.cntdd_cmpra,b.cntdd_vnta,a.prcio_cmpra,b.prcio_untro,a.total_cmpra,b.total_vnta

END
GO
grant execute on dbo.spReporteProductos to user_HulkStore
GO
exec sp_recompile 'dbo.spReporteProductos'
GO