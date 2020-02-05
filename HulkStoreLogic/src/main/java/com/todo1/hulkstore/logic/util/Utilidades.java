package com.todo1.hulkstore.logic.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;

public final class Utilidades {

	private static DatatypeFactory df = null;
	/**
	 * Variable que almacena la instancia del Logger
	 */
	private static final Logger LOG = Logger.getLogger(Utilidades.class);
	
	private Utilidades(){
		/*clase utilizaría constructor privado para evitar instancias */
	}
	
	/**
	 * Quitar espacion final.
	 *
	 * @param cadena
	 *            the cadena
	 * @return the string
	 */
	public static String quitarEspacionFinal(String cadena) {
		if (cadena.indexOf(' ') > -1) {
			return cadena.substring(0, cadena.indexOf(' '));
		} else {
			return cadena;
		}
	}

	/**
	 * Obtener string de date.
	 *
	 * @param dateToFormat
	 *            the date to format
	 * @param formatoDeseado
	 *            the formato deseado
	 * @return the string
	 */
	public static String obtenerStringDeDate(Date dateToFormat,	String formatoDeseado, boolean formato) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (!formato) {
			if (formatoDeseado != null && formatoDeseado.contains("-")) {
				sdf.applyPattern("yyyy-MM-dd");
			} else if (formatoDeseado != null && formatoDeseado.contains("/")) {
				sdf.applyPattern("yyyy/MM/dd");
			} else {
				sdf.applyPattern("yyyyMMdd");
			}
		} else {
			sdf.applyPattern(formatoDeseado);
		}
		return sdf.format(dateToFormat);
	}



	/**
	 * Verifica si la cadena ingresada es un numero o no lo es.
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean esNumero(String cadena) {
		try {
			Long.valueOf(cadena);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException dce) {
			throw new IllegalStateException(
					"Exception while obtaining DatatypeFactory instance", dce);
		}
	}

	public static BigInteger convertirNumeroToBigInteger(Integer numero) {
		BigInteger conversionNumero;

		if (numero == null) {
			conversionNumero = null;
		} else {
			conversionNumero = BigInteger.valueOf(numero);
		}
		return conversionNumero;
	}

	public static BigDecimal convertirNumeroToBigDecimal(Integer numero) {
		BigDecimal conversionNumero;

		if (numero == null) {
			conversionNumero = null;
		} else {
			conversionNumero = BigDecimal.valueOf(numero);
		}
		return conversionNumero;
	}
	public static final boolean validarCamposVacios(Object object) {
		if (object instanceof String) {
			String cadena = (String) object;
			return cadena != null && !cadena.trim().isEmpty();
		}
		return object != null;
	}


	/** Validacion cadena vacia */
	public static String validaCadenaTextoCombo(String cadena, String valor) {

		String resultadoCadena;

		if (cadena != null) {
			resultadoCadena = cadena.trim();
		} else {
			resultadoCadena = valor;
		}

		return resultadoCadena;
	}

	/** Validacion numero */
	public static BigInteger validaNumero(Integer numero, BigInteger valor) {

		BigInteger resultadoBigInteger;

		if (numero != null) {
			resultadoBigInteger = convertirNumeroToBigInteger(numero);
		} else {
			resultadoBigInteger = valor;
		}

		return resultadoBigInteger;
	}

	/** metodo que permite quitar los espacios a una cadena */
	public static String quitarEspaciosCadena(String cadena) {
		if(cadena == null)
			return cadena;
		return cadena.trim();
	}

	
	/** Validacion que la fecha se igual a la fecha actual */
	public static boolean esfechaMayor24HActual(Date fechaActual, Date fechaingresada) {
		 long diferencia = fechaActual.getTime() - fechaingresada.getTime();
		 double horas = Math.floor(diferencia / (1000.0 * 60.0 * 60.0)); 
		 return ((int) horas)>24;
	}
	
	/** Validacion que la fecha se igual a la fecha actual */
	public static boolean esfechaMayorNMesesActual(Date fechaActual, Date fechaingresada, Integer nmeses) {
		 long diferencia = fechaActual.getTime() - fechaingresada.getTime();
		 double meses = Math.floor((double)diferencia / new Long(2629750000L)); 
		 return ((int) meses)>nmeses;
	}
	
	/** Validacion que la fecha se igual a la fecha incial */
	public static boolean esfechaMayorNMeses(Date fechaInicial, Date fechaFinal, double nmeses) {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(fechaInicial);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(fechaFinal);

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		return diffMonth>nmeses;
	}

	/** Validacion de datos obligatorios para los consecutivos */
	public static boolean validarObservaciones(String obs) {
		return obs.length() < 10 || obs.length() > 250;
	}
	
	/** Validacion de expresiones regulares */
	public static boolean validarExpresionRegular(String cadena, String expresionRegular){
		Pattern pat = Pattern.compile(expresionRegular);
		Matcher mat = pat.matcher(cadena);
		return mat.matches();
	}
	
	/**
	 * Metodo que valdia el Null
	 * @param numero
	 * @return
	 */
	public static String validarNullCadena(Object entrada){
		if(entrada == null){
			return "";
		}else{
			return entrada.toString();
		}
		
	}
	
	/**
	 * Metodo que valdia el Null
	 * @param numero
	 * @return
	 */
	public static int validarNullint(Object entrada){
		if(entrada == null){
			return 0;
		}else{
			return Integer.parseInt(entrada.toString());
		}
		
	}
	
	/**		
	 * Valida si el correo tiene una estructuta valida.		
	 * @param correo		
	 * @return		
	 */		
	public static boolean validarCorreoElectronico(String correo){		
		final String patronMail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"		
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";		
		Pattern pattern = Pattern.compile(patronMail);		
		Matcher matcher = pattern.matcher(correo);		
        return matcher.matches();		
	}
	
}