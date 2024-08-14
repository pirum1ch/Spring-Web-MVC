package ru.voter.springwebmvc;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;


import java.io.IOException;
import java.nio.file.Files;

public class SpringWebMvcApplication {

    public static void main(String[] args) throws LifecycleException, IOException {
        final Tomcat tomcat = new Tomcat();
        final java.nio.file.Path baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final Connector connector = new Connector();
        connector.setPort(9999);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}
