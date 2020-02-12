package com.todo1.hulkstore.service.config;


import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

public class JerseySwaggerApplication extends ResourceConfig  {
	private static final String BASE_DIR = "/HulkStoreServices/rest";
	private static final String HOST = "localhost:8080";
	private static final String HTTP = "http";
	private static final String PAQUETE_RECURSOS = "com.todo1.hulkstore.service.ws";
	private static final String VERSION = "1.0.0";

	public JerseySwaggerApplication(){
		super(MultiPartFeature.class);
		packages(PAQUETE_RECURSOS);
        register(io.swagger.jaxrs.listing.ApiListingResource.class);
        register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(VERSION);
        beanConfig.setSchemes(new String[]{HTTP});
        beanConfig.setHost(HOST);
        beanConfig.setBasePath(BASE_DIR);
        beanConfig.setResourcePackage(PAQUETE_RECURSOS);
        beanConfig.setScan(true);
	}
}
