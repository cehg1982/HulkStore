var 	direccion 	= window.location.host;
const   protocolo = window.location.protocol;
const   servidor = window.location.hostname;
const   puerto = window.location.port;

const 	urlApp= protocolo+'//'+direccion;
const 	urlWeb = urlApp+'/HulkStoreWeb/view/hulkstore/';
const 	urlServiciosWeb = urlApp+'/HulkStoreServices/rest/';
const 	inxDscrptn='@';

const 	f = new Date();
const 	fechaActual=f.getFullYear() + "-" +f.getDate() + "-" + (f.getMonth() +1) ;

//Constantes para guardar las claves con las que se guardan objetos en el session storage
const storage = new Object();
storage.currentPage='currentPage';
storage.currentPageDesc='currentPageDesc';
storage.menuJsonStrorage='menuJsonStrorage';
storage.usuarioJsonStorage='usuarioJsonStorage';
storage.gestiones='gestionesJsonStorage';
storage.sessionValida='sessionValida';