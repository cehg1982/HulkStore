
//valida consulta productos
jQuery('#btnCrear').on('click', function(e) {	
	if (jQuery('#inputNombreProducto').val()!="" && jQuery('#inputDscProducto').val()!=""
		&& jQuery('#inputcntddStk').val()!=""){
		crearProductosRest(function(data){
			if(data[0].nmbrePrdcto!=null){
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

//Servicio consulta productos
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
	productoVO.cntddStck=jQuery('#inputcntddStk').val();
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
	}).fail(function(response){
		callback({});
	});	
}


//Limpia los campos
function limpiarCnslta(){	
	jQuery("#formPrdcts")[0].reset();
}

//Funcion principal
jQuery(document).ready(function() {	
	console.log(CONSULTAR_PRODUCTOS)
});