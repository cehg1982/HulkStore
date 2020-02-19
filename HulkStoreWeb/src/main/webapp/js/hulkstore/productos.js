
//valida creacion productos
jQuery('#btnCrear').on('click', function(e) {	
	if (jQuery('#inputNombreProducto').val()!="" && jQuery('#inputDscProducto').val()!=""
		&& jQuery('#inputcntddStk').val()!=""){
		crearProductosRest(function(data){
			if(data!=null){
				sessionStorage.productos = JSON.stringify(data);
				var datosProducto = data;
				alertify.success(CREAR_PRODUCTO);
			}else{
				console.log(ALERT_CONSULTA);
				alertify.error(ALERT_CONSULTA);
			}
		});
	}else{
		alertify.notify(ALERT_FILTROS_CONSULTA_PRODUCTO, ERROR, 5);
		limpiarCnslta();	
	}
});	

//Servicio crear productos
function crearProductosRest(callback){
	
	var productoVO= {cnsctvoPrdcto		: '',
					nmbrePrdcto			: '',
					dscrpconPrdcto		: '',
					cntddStck			: '',	
					stado				: '',
					fchaCrcn			: '',
					usroCrcn			: '',
					fchaUltma_mdfccn	: '',
					usroUltma_mdfccn	: ''	
	};
	
	productoVO.nmbrePrdcto=jQuery('#inputNombreProducto').val();
	productoVO.dscrpconPrdcto=jQuery('#inputDscProducto').val();
	productoVO.usroCrcn='user_HulkStore';
	
	sessionStorage.productoVO=JSON.stringify(productoVO);
	
	jQuery.ajax({
		url: urlServiciosWeb+'ProductosWS/CrearProductos',
		type: 'POST',
        dataType: 'json',
        contentType: "application/json",
        data: sessionStorage.productoVO,
        delay: 300
	}).done(function(response){
		callback(response);
		alertify.success(CREAR_PRODUCTO);
		console.log(CREAR_PRODUCTO);
	}).fail(function(response){
		callback({});
		console.log(ERROR_CREAR_PRODUCTO);
	});	
}


//Limpia los campos
function limpiarCmpsPrdto(){	
	jQuery("#formPrdcts")[0].reset();
}

//Funcion principal
jQuery(document).ready(function() {	
	console.log(CONSULTAR_PRODUCTOS)
});