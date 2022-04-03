package ZadatakB3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Klijent extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Zadatak 2C");

        Label lbl1 = new Label("X");
        Label lbl2 = new Label("Y");
        Label lbl3 = new Label("Z");
        Label lbl4 = new Label("=");

        TextField A1=new TextField();
        TextField A2=new TextField();
        TextField A3=new TextField();
        TextField A4=new TextField();

        TextField B1=new TextField();
        TextField B2=new TextField();
        TextField B3=new TextField();
        TextField B4=new TextField();

        TextField C1=new TextField();
        TextField C2=new TextField();
        TextField C3=new TextField();
        TextField C4=new TextField();
        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.addRow(0,lbl1,lbl2,lbl3,lbl4);
        gridPane.addRow(1,A1,A2,A3,A4);
        gridPane.addRow(2,B1,B2,B3,B4);
        gridPane.addRow(3,C1,C2,C3,C4);
        Button salji=new Button("Izracunaj");
        Label pom=new Label("         ");
        salji.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    double niz[][] = new double[3][4];
                    InetAddress ipAdresaServera=InetAddress.getByName("127.0.0.1");
                    int portServisa=9000;
                    Socket soket=new Socket(ipAdresaServera,portServisa);
                    BufferedReader in=new BufferedReader(new InputStreamReader(soket.getInputStream()));//input

                    PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(soket.getOutputStream())),true);
                    Scanner sc=new Scanner(System.in);
                    String poruka="";


                    String poruka2=A1.getText().trim()+","+A2.getText().trim()+","+A3.getText().trim()+","+A4.getText().trim()+":"
                            +B1.getText().trim()+","+B2.getText().trim()+","+B3.getText().trim()+","+B4.getText().trim()+":"+C1.getText().trim()+","+C2.getText().trim()+","+C3.getText().trim()+","+C4.getText().trim();
                    poruka="1,55,11,33:4,3,-4,1:-22,1,133,1";//3x4 jednacina
                    //System.out.println("Klijent salje serveru poruku"+poruka);
                   out.println(poruka2);//out salje klijent-server



                    String odgovor=in.readLine();//in prima sa servera-klijent
                    pom.setText(odgovor);


                    in.close();
                    out.close();
                    soket.close();


                } catch (UnknownHostException e) {
                    System.out.println("NEPOZNAT HOST!!!!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        gridPane.addRow(4,salji,pom);
       // gridPane.addRow(5,pom);
        gridPane.setColumnSpan(pom,3);
        Scene scene = new Scene(gridPane, 300, 250);
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);

    }



}
