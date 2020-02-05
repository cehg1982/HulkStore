/*
 * Funcion que permite la nevegación teniendo en cuenta el F5
 */
jQuery(document).ready(function(){
	var current = sessionStorage.getItem(storage.currentPage);
	var currentDesc = sessionStorage.getItem(storage.currentPageDesc);
	
	if (current != null && current != 'undefined' && current.length > 0){
		var path = window.location.pathname;
		var url = path.split("/").pop();
		
		if (url != current) {
			navegar(current, currentDesc);
		}
	}
});	

/*
 * Funcion que permite la nevegación
 */
function navegar(url, descripcion){
	var d = new Date();
	var n = d.getTime();
	var nuevaURL;
	if(url.includes('?')){
		nuevaURL = url + '&t=' + n;
	}else{
		nuevaURL = url + '?t=' + n;
	}
	jQuery('#titulo-funcionalidad').text(descripcion);
	jQuery('#main-panel').load(nuevaURL);
	sessionStorage.setItem(storage.currentPage, url);
	sessionStorage.setItem(storage.currentPageDesc, descripcion);
}