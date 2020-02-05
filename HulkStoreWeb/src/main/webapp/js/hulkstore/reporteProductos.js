
//valida consulta productos
jQuery('#btnReporte').on('click', function(e) {	
	if (jQuery('#inputFechaDesde').val()!="" && jQuery('#inputFechaHasta').val()!=""){
		reporteProductosRest(function(data){
			if(data[0]!=null){
				sessionStorage.productos = JSON.stringify(data);
				var datosProducto = data;
				
				jQuery('#idTablaProductos').DataTable({
					"searching": false,
					"paging": false,
					"colReorder": false,
					"ordering": false,
					"bInfo" : false,
					data: datosProducto,
					columns: [		        
						{ data: 'cnsctvoPrdcto' },
						{ data: 'nmbrePrdcto'+' - '+'dscrpconPrdcto' },
						{ data: 'cntddStck' },
					    { data: 'cntddCmpra' },
						{ data: 'prcioCmpra' },
					    { data: 'totalCmpra' },
						{ data: 'cntddVnta' },
						{ data: 'prcioUntro' },			
						{ data: 'totalVnta' },
						{ data: 'utldd' }
					],
					destroy: true,
			        responsive: true,
					select: {
				         style: 'multi'
				     }
				});
				
				
			}else{
				console.log(ALERT_CONSULTA);
				alertify.error(ALERT_CONSULTA);
			}
		});
	}else{
		alertify.notify(ALERT_FILTROS_REPORTE_PRODUCTO, ERROR, 5);
		limpiarRprte();	
	}
});	

//Servicio consulta productos
function reporteProductosRest(callback){

	jQuery.ajax({
		url: urlServiciosWeb+'ProductosWS/ReporteProductos/'
		+jQuery('#inputProducto').val()+'/'
		+jQuery('#inputFechaDesde').val()+'/'
		+jQuery('#inputFechaHasta').val(),
		data: {},
		type: 'GET',
        dataType: 'json',
        delay: 300
	}).done(function(response){
		callback(response);
	}).fail(function(response){
		callback({});
	});	
}


//Limpia los campos
function limpiarRprte(){	
	jQuery("#formReportePrdcts")[0].reset();
}

//Funcion principal
jQuery(document).ready(function() {	
	console.log(REPORTAR_PRODUCTOS)
});