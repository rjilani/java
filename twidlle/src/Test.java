import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.apache.commons.io.FileUtils;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rjilan01
 */
public class Test {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        server.createContext("/index", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public static void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        server.createContext("/index", new MyHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started at http://localhost:9000/index");
        server.start();
    }

}

class MyHandler implements HttpHandler {

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange he) throws IOException {
        String response = "This is the response";
        File html = new File("./output.html");
        String lines = FileUtils.readFileToString(html, "UTF-8");
        he.sendResponseHeaders(200, html.length());
        try (OutputStream os = he.getResponseBody()) {
            os.write(lines.getBytes());
        }
    }
}
