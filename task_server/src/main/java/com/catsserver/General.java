package com.catsserver;

import com.catsserver.controller.CatsRESTController;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.io.File;

public class General {

    public static void main(String[] args) throws Exception {
        int countThreads = 1;
        if (args.length != 0) {
            for (int i = 0; i < args.length - 1; i++) {
                if (args[i].equals("-t")) {
                    try {
                        countThreads = Integer.valueOf(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.out.println("Не числовое значение: " + args[i + 1]);
                    }
                }
            }
        }
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(webPort));

        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        context.setStartStopThreads(countThreads);

        tomcat.addServlet(context, "jersey-container-servlet",
                new ServletContainer(new ResourceConfig(CatsRESTController.class)));
        context.addServletMappingDecoded("/*", "jersey-container-servlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
