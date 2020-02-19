//Se invoca servicio para obtener los productos
function consultarProductos(){
	jQuery.ajax({
		async: false,
		url: urlServiciosWeb+'ProductosWS/ConsultaProductos/',
		data: {},
		type: 'GET',
        dataType: 'json',
        delay: 300
    }).done(function (response) {    	
    	 window.datosAutoCompletarprodcutos = jQuery.map(response,function(obj){
			return {id: obj.cnsctvoPrdcto, text: obj.nmbrePrdcto};
		});
    }).fail(function () {					    	
    	console.log(ERROR_CONSULTA_PRODUCTO);
    });
}

//valida venta productos
jQuery('#btnVenta').on('click', function(e) {	
	if (jQuery('#inputProducto').val()!="" && jQuery('#inputCantidad').val()!=""
		&& jQuery('#inputprecio').val()!=""){
		ventaProductosRest(function(data){
			if(data!=null){
				sessionStorage.productos = JSON.stringify(data);
				var datosProducto = data;			
				alertify.success(ALERT_VENTA_PRODUCTO);
			}else{
				alertify.error(ERROR_VENTA_PRODUCTO);
			}
		});
	}else{
		alertify.notify(ALERT_FILTROS_CONSULTA_PRODUCTO, ERROR, 5);
		limpiarCnslta();	
	}
});	

//Servicio venta productos
function ventaProductosRest(callback){

	var detalleVetaProductoVO= {cnsctvoPrdcto	: '',
								 cntdd			: '',
								 prcoUntro		: '',
								 fchaCrcn		: '',	
								 usroCrcn		: ''
								};

	detalleVetaProductoVO.cnsctvoPrdcto=jQuery('#selectProducto :selected').val();
	detalleVetaProductoVO.cntdd=jQuery('#inputCantidad').val();
	detalleVetaProductoVO.prcoUntro=jQuery('#inputprecio').val();
	detalleVetaProductoVO.usroCrcn='user_HulkStore';
	
	sessionStorage.detalleVetaProductoVO=JSON.stringify(detalleVetaProductoVO);
	
	jQuery.ajax({
		url: urlServiciosWeb+'ProductosWS/VentaProductos/',
		type: 'POST',
		dataType: 'json',
		contentType: "application/json",
		data: sessionStorage.detalleVetaProductoVO,
		delay: 300
		}).done(function(response){
		callback(response);
		}).fail(function(response){
		callback({});
	});	
}


//Limpia los campos
function limpiarCnslta(){	
	jQuery("#formVntaPrdcts")[0].reset();
}

//Funcion principal
jQuery(document).ready(function() {	
	consultarProductos();
	jQuery('#selectProducto').select2({		
		allowClear: true,
		maximunSelectionSize: 1,
		placeholder: "Productos...",
		data: window.datosAutoCompletarprodcutos,				
		minimumInputLength: 3
	});
	console.log(VENTA_PRODUCTOS)
});