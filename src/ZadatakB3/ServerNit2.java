package ZadatakB3;

import java.io.*;
import java.net.Socket;

public class ServerNit2 extends  Thread{
    private Socket socketK;
    private BufferedReader in;
    private PrintWriter out;
    public static int bool=0;
    public ServerNit2(Socket accept) {
        socketK=accept;
        try {
            in = new BufferedReader(new InputStreamReader(socketK.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketK.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {


        String odgovor="";
        String poruka="";


            try {
                poruka=in.readLine();//cita sa KLIJENTA
                bool=0;
                System.out.println("Klijent poslao: "+poruka);
            } catch (IOException e) {
                e.printStackTrace();
            }

             double mat[][]=new double[3][4];//matrica
            if(poruka.contains(":")){
                String nizA[]=poruka.split(":");
                for(int i=0;i<3;i++){
                    for(int j=0;j< 4;j++){
                        try{
                            mat[i][j]=Double.parseDouble(nizA[i].split(",")[j]);

                        }catch (Exception e){
                            System.out.println("Server salje poruku Niste uneli dobru vrednost!");
                            out.println("Niste uneli dobru vrednost!");
                            bool=1;
                        }

                    }

                }
                Matrica.pronadjiResenjeJednacine(mat);
                if(bool!=1){
                    System.out.println("Server salje klijentu resenje: "+Matrica.getString);
                    out.println(Matrica.getString);
                }






            }
            else {
                out.println("Niste prosledili matricu!");
            }








        }


}


