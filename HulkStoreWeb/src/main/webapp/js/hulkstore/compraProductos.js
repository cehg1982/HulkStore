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

//valida compra productos
jQuery('#btnComprar').on('click', function(e) {	
	if (jQuery('#inputProducto').val()!="" && jQuery('#inputCantidad').val()!=""
		&& jQuery('#inputprecio').val()!=""){
		compraProductosRest(function(data){
			if(data!=null){
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
	
	detalleCompraProductoVO.cnsctvoPrdcto=jQuery('#selectProducto :selected').val();
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
		alertify.success(ALERT_COMPRA_PRODUCTO);
		console.log(ALERT_COMPRA_PRODUCTO);
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
	consultarProductos();
	jQuery('#selectProducto').select2({		
		allowClear: true,
		maximunSelectionSize: 1,
		placeholder: "Productos...",
		data: window.datosAutoCompletarprodcutos,				
		minimumInputLength: 3
	});
	console.log(COMPRA_PRODUCTOS)
});