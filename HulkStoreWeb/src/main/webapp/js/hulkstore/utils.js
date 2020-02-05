/*
 * Funcion que muestra el bloqueo de la pantalla en las peticions Ajax
 */
jQuery( document ).ajaxStart(function() {
  jQuery.blockUI({
			message: 'Procesando...',
			css: { 
				border: 'none', 
				padding: '15px', 
				backgroundColor: '#000', 
				'-webkit-border-radius': '10px', 
				'-moz-border-radius': '10px', 
				opacity: .5, 
				color: '#fff' 
        } });
});

/*
 * Funcion que finaliza el bloqueo de la pantalla en las peticions Ajax
 */
jQuery( document ).ajaxStop(function() {
  jQuery.unblockUI();
});

/*
 * Funcion que muestra un div por su id
 */
function toggler(divId) {
    jQuery('#' + divId).toggle();
}

/*
 * Funcion que muestra el mensaje informativo en un modal
 */
function mostrarMensaje(mensaje) {
	mostrarMensaje(mensaje, null);
}

/*
 * Funcion que muestra el mensaje informativo en un modal
 */
function mostrarMensaje(mensaje, titulo) {
	if(titulo != null){
		jQuery('#spanTituloMensaje').html(titulo);
	}
	jQuery('#spanTextoMensaje').html(mensaje);
	jQuery('#mensajesModal').modal('toggle');
}

/*
 * Funcion que retorna el valor de un parametro enviado en el querystring
 */
function obtenerParametroUrl(queryString, nombreParametro) {
	var parametros = queryString.split('&');
	for (var i = 0; i < parametros.length; i++) {
	    var parametro = parametros[i].split('=');
	    if (parametro[0] == nombreParametro) {
	    	return parametro[1];
	    }
	  }
	 return null;
}

/*
 * Funcion que asigna valores a los controles a partir de un obj js
 */
function poblarValoresControles(data){
	const entries = Object.entries(data)
	for (const [llave, valor] of entries) {
		//console.log(`${llave} = ${valor}`);
		if(!Array.isArray(valor)){
			if(jQuery('#' + llave).length > 0){
				jQuery('#' + llave).text(valor);
			}
		}
	}
}

function poblarSelect(idSelect, opciones){
	for (var i = 0; i < opciones.length; i++) {
		var opcionObj = opciones[i];
		var opcion = {nombre:"value", valor:opcionObj.id};
		var attributes = [opcion];
		var opcionHtml = crearNodoDinamico('option', opcionObj.descripcion, null, attributes);
		jQuery('#' + idSelect).append(opcionHtml);
	}
}

/**
 * Retorna una cadena reeplazando {n} por el valor en param[n] 
 * @param str
 * @param param
 * @returns
 */
function stringFormat(str, parametros){
	for (var i = 0; i < parametros.length; i++) {
		str = str.replace('{'+ i + '}', parametros[i]);
	}
	return  str;
}

/*
 * Funcion que muestra el mensaje informativo en un modal
 */
function mostrarMensaje(mensaje) {
	mostrarMensaje(mensaje, null);
}

/*
 * Funcion que muestra el mensaje informativo en un modal
 */
function mostrarMensaje(mensaje, titulo) {
	if(titulo != null){
		jQuery('#spanTituloMensaje').html(titulo);
	}
	jQuery('#spanTextoMensaje').html(mensaje);
	jQuery('#mensajesModal').modal('toggle');
}

/*
 * Funcion que retorna el valor de un parametro enviado en el querystring
 */
function obtenerParametroUrl(queryString, nombreParametro) {
	var parametros = queryString.split('&');
	for (var i = 0; i < parametros.length; i++) {
	    var parametro = parametros[i].split('=');
	    if (parametro[0] == nombreParametro) {
	    	return parametro[1];
	    }
	  }
	 return null;
}

function calcularEdad() {
	var hoy = new Date();
	var cumpleanos = new Date(sessionStorage.fchNacimiento);
	var edad = hoy.getFullYear() - cumpleanos.getFullYear();
	var m = hoy.getMonth() - cumpleanos.getMonth();

	if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
		edad--;
	}
	sessionStorage.edadAfiliado=edad;
}






