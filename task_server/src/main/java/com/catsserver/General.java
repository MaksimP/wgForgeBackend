package com.catsserver;

import com.catsserver.service.CatsRESTService;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

public class General {

    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(webPort));

        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        tomcat.addServlet(context, "jersey-container-servlet",
                new ServletContainer(new ResourceConfig(CatsRESTService.class)));
        context.addServletMappingDecoded("/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
