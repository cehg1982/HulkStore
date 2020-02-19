package com.todo1.hulkstore.data.util;

import java.util.ResourceBundle;

public class RecursosUtil {
	
		private static final String BUNDLE_NAME = "properties.storedProcedures";
		private static final String BUNDLE_MSJ_NAME = "properties.mensajesData";
		private static final String BUNDLE_CNXN_BD = "properties.conexionBD";

		private RecursosUtil() {
		}
		
		public static ResourceBundle getBundle(String path) {
			return ResourceBundle.getBundle(path);
		}

		public static String getStringStoredprocedures(String key) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
			return resourceBundle.getString(key);
		}	
		
		public static String getStringMensajes(String key) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_MSJ_NAME);
			return resourceBundle.getString(key);
		}
		
		public static String getStringBD(String key) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE_CNXN_BD);
			return resourceBundle.getString(key);
		}
}