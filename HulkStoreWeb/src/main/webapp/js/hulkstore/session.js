/*
 * Funciones para mostrar el nombre del usuario logeado 
 */
var idleTime = 0;
var timeout=30;
jQuery(document).ready(function() {
   verificarSession();
   var usuarioJsonStorage = sessionStorage.getItem(storage.usuarioJsonStorage);
   if(usuarioJsonStorage == null || usuarioJsonStorage == 'undefined' || usuarioJsonStorage.length == 0){
	   //Obtener el json del usuario logeado
	   jQuery.ajax({
	        url: urlObjSession + 'USUARIO_SESSION',
	        success: function (usuarioJson) {
	        	sessionStorage.setItem(storage.usuarioJsonStorage, usuarioJson);
	        },
			error: function (error) {
	        	window.location.replace(urlLogin);
	        },
	        async: false
	    });
   }
   usuarioJsonStorage = sessionStorage.getItem(storage.usuarioJsonStorage);
   var usuarioJsonStrorageObj = JSON.parse(usuarioJsonStorage);
   jQuery('#nombreUsuario').text(usuarioJsonStrorageObj.login + ' - ' + usuarioJsonStrorageObj.nombre);
});

/**
 * Funcion para verificar si la session esta activa
 * @returns
 */
function verificarSession(){
	setInterval("timerIncrement()", 60000); // 60000 ms tiene un min
	jQuery(document).mousemove(function(e) {
		idleTime = 0;
	});
	jQuery(document).click(function(e) {
		idleTime = 0;
	});
	jQuery(document).keypress(function(e) {
		idleTime = 0;
	});
}

/**
 * Funcion para incremetar contador
 * @returns
 */
function timerIncrement() {
	idleTime = idleTime + 1;
	if (idleTime == timeout) {
		removerUsuarioSession();
		resetSessionStorage();
		jQuery.blockUI({ message: jQuery('#sessionExpiradaModal'), css: { 'text-align': 'left', 'height': '0%', 'border': '0px' } }); 
		jQuery('#btnAceptarCerrarSession').click(function() { 
			 jQuery('#btnCerrarSession').click();
		 });
	}
}

/*
 * Funcion que elimina del sessionStorage los datos de navegacion actual
 */
function resetSessionStorage(){
	sessionStorage.clear();
}

function removerUsuarioSession(){
	jQuery.ajax({
		url: urlRemoveObjSession + 'USUARIO_SESSION',
		async: false
	});
}