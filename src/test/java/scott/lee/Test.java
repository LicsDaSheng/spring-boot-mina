package scott.lee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Scott on 16/8/24.
 */
public class Test extends Thread{


    static Socket server;


    public static void main(String[] args) throws InterruptedException {
        long sleep = 1000;
        while (true) {
            Test t = new Test();
            t.start();
            sleep(sleep);
        }

    }

    public void run() {


        try {

            server = new Socket("192.168.1.199", 9002);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream());
            double tmp = Math.random();
            String str = tmp + "";
            System.out.println(str);
            out.println(str);
            out.flush();
            server.close();

        } catch (IOException ex) {
        } finally {

        }
    }
}
