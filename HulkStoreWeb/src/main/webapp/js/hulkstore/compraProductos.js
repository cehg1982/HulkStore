
//valida venta productos
jQuery('#formCmprPrdcts').on('click', function(e) {	
	if (jQuery('#inputProducto').val()!="" && jQuery('#inputCantidad').val()!=""
		&& jQuery('#inputprecio').val()!=""){
		compraProductosRest(function(data){
			if(data[0]!=null){
				sessionStorage.productos = JSON.stringify(data);
				var datosProducto = data;
				alertify.success(ALERT_COMPRA_PRODUCTO);
			}else{
				alertify.error(ERROR_COMPRA_PRODUCTO);
			}
		});
	}else{
		alertify.notify(ALERT_FILTROS_CONSULTA_PRODUCTO, ERROR, 5);
		limpiarCompra();	
	}
});	

//Servicio compra productos
function compraProductosRest(callback){
	

	var detalleCompraProductoVO= {cnsctvoPrdcto	: '',
								 cntdd			: '',
								 prcoCmpra		: '',
								 fchaCrcn		: '',	
								 usroCrcn		: ''
								};
	
	detalleCompraProductoVO.cnsctvoPrdcto=jQuery('#inputProducto').val();
	detalleCompraProductoVO.cntdd=jQuery('#inputCantidad').val();
	detalleCompraProductoVO.prcoCmpra=jQuery('#inputprecio').val();
	detalleCompraProductoVO.usroCrcn='user_HulkStore';
	
	sessionStorage.detalleCompraProductoVO=JSON.stringify(detalleCompraProductoVO);
	
	jQuery.ajax({
		url: urlServiciosWeb+'ProductosWS/CompraProductos/',
		type: 'POST',
        dataType: 'json',
        contentType: "application/json",
        data: sessionStorage.detalleCompraProductoVO,
        delay: 300
	}).done(function(response){
		callback(response);
	}).fail(function(response){
		callback({});
	});	
}


//Limpia los campos
function limpiarCompra(){	
	jQuery("#formCmprPrdcts")[0].reset();
}

//Funcion principal
jQuery(document).ready(function() {	
	console.log(COMPRA_PRODUCTOS)
});