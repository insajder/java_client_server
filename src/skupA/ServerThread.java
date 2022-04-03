package skupA;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class ServerThread extends Thread{
    Socket soket;
    BufferedReader in;
    PrintWriter out;
    TextArea txt;

    public ServerThread(Socket s, TextArea txt) {
        this.soket = s;
        this.txt = txt;
        try {
            in = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        start();
    }
    @Override
    public void run(){
        txt.appendText("Klijentov zahtev:\n");
        try {
            String primljeno = in.readLine();

            String[] podaci = primljeno.split("~");
            txt.appendText(podaci[0] + "\n");

            for(String s: podaci){
                System.out.println(s);
            }

            if(podaci[0].equals("ZADATAK1")) {
                String prosledi = "";

                if(podaci.length==1){
                    txt.appendText("Izaberite datum!!!\n ");
                    prosledi +="Izaberite datum!!!!";
                }else {
                    txt.appendText(podaci[1] + "\n");

                    long dani = ukupanBrojDana(podaci[1]);

                    prosledi += "Fizicki bioritam: " + fizickiBioritam(dani) + "~";
                    prosledi += "Emocionalni bioritam: " + emocionalniBioritam(dani) + "~";
                    prosledi += "Intelektualni bioritam: " + intelektualniBioritam(dani);

                    txt.appendText("Fizicki bioritam: " + fizickiBioritam(dani) + "\n");
                    txt.appendText("Emocionalni bioritam: " + emocionalniBioritam(dani) + "\n");
                    txt.appendText("Intelektualni bioritam: " + intelektualniBioritam(dani) + "\n");
                }


                out.println(prosledi);
            }
            else if(podaci[0].equals("ZADATAK2")){
                String prosledi = "";

                if(podaci.length==1){
                    txt.appendText("Izaberite broj!!!\n ");
                    prosledi +="Izaberite broj!!!!";
                }
                else {
                    int n = Integer.parseInt(podaci[1]);

                    int[][] matrica = new int[3][3];
                    matrica[0][0] = n - 3;
                    matrica[0][1] = n + 2;
                    matrica[0][2] = n + 1;
                    matrica[1][0] = n + 4;
                    matrica[1][1] = n;
                    matrica[1][2] = n - 4;
                    matrica[2][0] = n - 1;
                    matrica[2][1] = n - 2;
                    matrica[2][2] = n + 3;

                    prosledi += matricaToString(matrica);

                    ispisMatrice(prosledi);
                }

                out.println(prosledi);
            }
            else if(podaci[0].equals("ZADATAK3")){

                if(podaci.length == 1){
                    txt.appendText("Prazno polje!!!\n");
                    out.println("Prazno polje!!!");
                }else {
                    txt.appendText(palindrom(podaci[1] + "\n"));
                    out.println(palindrom(podaci[1]));
                }
            }

            in.close();
            out.close();
            soket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public long ukupanBrojDana(String datum){
        LocalDate d = LocalDate.parse(datum);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = new Date();
        String danasnji = sdf.format(new Date());

        long brojDana = ChronoUnit.DAYS.between(LocalDate.parse(danasnji), d);
        brojDana *= (-1);

        return brojDana;
    }
    public int fizickiBioritam(long dani){
        return (int)Math.round(Math.sin(((2 * Math.PI) / 23) * dani) * 100);
    }
    public int emocionalniBioritam(long dani){
        return (int)Math.round(Math.sin(((2 * Math.PI) / 28) * dani) * 100);
    }
    public int intelektualniBioritam(long dani){
        return (int)Math.round(Math.sin(((2 * Math.PI) / 33) * dani) * 100);
    }
    public String matricaToString(int[][] m){
        String str = "";
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                str += m[i][j]+" ";
            }
        }
        return str;
    }
    public void ispisMatrice(String s){
        String[] str = s.split(" ");
        int br=0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(str[br].length()==1) txt.appendText(" ");
                txt.appendText(" "+str[br]);
                br++;
            }
            txt.appendText("\n");
        }
    }
    public String palindrom(String str){

        if(!proveraStringa(str)){
            return "Pogresan unos!!!";
        }

        String spoji = str.replace(" ", "");
        String smanji = spoji.toLowerCase();
        String okreni = "";
        for(int i=smanji.length()-1; i>=0; i--){
            okreni += smanji.charAt(i);
        }
        if(smanji.equals(okreni)){
            return "Rec je palindrom.";
        }else{
            return "Rec nije palindrom.";
        }
    }
    public boolean proveraStringa(String str){
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) != ' ' && !Character.isAlphabetic(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
