var 	direccion 	= window.location.host;

const 	urlApp= protocolo+'//'+direccion;
const 	urlWeb = urlApp+'/HulkStoreWeb/view/hulkstore/';
const 	urlServiciosWeb = urlApp+'/HulkStoreServices/rest/';
const 	inxDscrptn='@';

const 	f = new Date();
const 	fechaActual=f.getFullYear() + "-" +f.getDate() + "-" + (f.getMonth() +1) ;