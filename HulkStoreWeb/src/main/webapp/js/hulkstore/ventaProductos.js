
//valida venta productos
jQuery('#btnVenta').on('click', function(e) {	
	if (jQuery('#inputProducto').val()!="" && jQuery('#inputCantidad').val()!=""
		&& jQuery('#inputprecio').val()!=""){
		ventaProductosRest(function(data){
			if(data[0].nmbrePrdcto!=null){
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
								 prcoCmpra		: '',
								 fchaCrcn		: '',	
								 usroCrcn		: ''
								};

	detalleVetaProductoVO.cnsctvoPrdcto=jQuery('#inputProducto').val();
	detalleVetaProductoVO.cntdd=jQuery('#inputCantidad').val();
	detalleVetaProductoVO.prcoCmpra=jQuery('#inputprecio').val();
	detalleVetaProductoVO.usroCrcn='user_HulkStore';
	
	sessionStorage.detalleVetaProductoVO=JSON.stringify(detalleVetaProductoVO);
	
	jQuery.ajax({
		url: urlServiciosWeb+'ProductosWS/CompraProductos/',
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
	console.log(VENTA_PRODUCTOS)
});