package ZadatakB3;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket ss= null;
        // launch(args);
        try {     System.out.println("Server pokrenut....");


            ss = new ServerSocket(9000);
            while (1==1){
                new Thread(new ServerNit2(ss.accept())).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}}
