package org.wsh.common.rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.wsh.common.rest.application.RESTApplication;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
//    public static final String BASE_URI = "http://172.30.251.10:8080/withdraw/";
    public static final String BASE_URI = "http://localhost:8080/common/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     *
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // 加载jersey扫描
        RESTApplication fundsApplication = new RESTApplication();

        // 启动服务
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), fundsApplication);
    }

    /**
     * Main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));

        System.in.read();
        server.shutdownNow();
    }
}

