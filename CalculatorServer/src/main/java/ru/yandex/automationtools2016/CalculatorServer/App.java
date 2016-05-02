package ru.yandex.automationtools2016.CalculatorServer;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.inject.ExtractorException;
import org.glassfish.jersey.servlet.ServletContainer;

public class App {


    public static void main(String[] args) {

        ResourceConfig config = new ResourceConfig();
        config.packages("ru.yandex.automationtools2016.CalculatorServer");
        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(2222);
        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e){ }
        finally {
            server.destroy();
        }


    }
}
