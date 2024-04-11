//package com.gabeWebTest.webTest.security;
//
//import org.apache.catalina.Context;
//import org.apache.tomcat.util.descriptor.web.SessionConfig;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.WebServerFactoryCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TomcatConfiguration {
//
//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieCustomizer() {
//        return factory -> factory.addContextCustomizers(context -> {
//            SessionConfig sessionConfig = context.getManager().ge();
//            sessionConfig.setCookieSecure("true");
//            sessionConfig.setCookieHttpOnly("true");
//        });
//    }
////}
