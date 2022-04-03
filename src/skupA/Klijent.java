package skupA;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Klijent extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Group root = new Group();
    BorderPane borderPane = new BorderPane();
    DatePicker datum;
    Label fizicki;
    Label emocionalni;
    Label intelekutalni;
    Label poruka;
    Label poruka2;
    Label poruka3;
    ComboBox cb;
    TextField[][] polja;
    TextField unos;
    Label odg;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SKUP A");
        Scene scene = new Scene(root, 500,600, Color.WHITE);
        TabPane tabPane = new TabPane();

        Tab tab = new Tab();
        tab.setText("Zadatak 1");
        HBox hbox = new HBox();
        hbox.getChildren().add(zadatak1());
        hbox.setAlignment(Pos.CENTER);
        tab.setContent(hbox);
        tabPane.getTabs().add(tab);

        Tab tab2 = new Tab();
        tab2.setText("Zadatak 2");
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(zadatak2());
        hbox2.setAlignment(Pos.CENTER);
        tab2.setContent(hbox2);
        tabPane.getTabs().add(tab2);

        Tab tab3 = new Tab();
        tab3.setText("Zadatak 3");
        HBox hbox3 = new HBox();
        hbox3.getChildren().add(zadatak3());
        hbox3.setAlignment(Pos.CENTER);
        tab3.setContent(hbox3);
        tabPane.getTabs().add(tab3);

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.setCenter(tabPane);
        root.getChildren().add(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public GridPane zadatak1(){
        GridPane gp = new GridPane();
        Button posalji = new Button("POSALJI");
        fizicki = new Label("Fizicki bioritam: ");
        emocionalni = new Label("Emocionalni bioritam: ");
        intelekutalni = new Label("Intelektualni bioritam: ");
        fizicki.setTextFill(Color.BLUE);
        emocionalni.setTextFill(Color.RED);
        intelekutalni.setTextFill(Color.GREEN);
        poruka = new Label();
        datum = new DatePicker();
        gp.setVgap(4);
        gp.setHgap(10);

        BorderPane bp = new BorderPane();
        Label naslov = new Label("Bioritam");
        naslov.setStyle("-fx-font-size: 20px;");
        bp.setCenter(naslov);

        gp.setPadding(new Insets(5,5,5,5));

        gp.add(new Label(" "), 0, 0);
        gp.add(bp, 0, 1);
        gp.add(datum, 0, 4);
        gp.add(posalji, 2,4,2,2);
        gp.add(fizicki, 0, 6);
        gp.add(emocionalni, 0, 7);
        gp.add(intelekutalni, 0, 8);
        gp.add(poruka, 0, 9);
        posalji.setOnAction(e -> pokreniKlijentaZadatak1());
        return gp;
    }
    public GridPane zadatak2(){
        GridPane gp = new GridPane();
        Label l = new Label("Izaberi vrednost: ");
        poruka2 = new Label();
        cb = new ComboBox();
        for(int i=5; i<=30; i++){
            cb.getItems().add(i);
        }
        Button posalji = new Button("POSALJI");
        polja = new TextField[3][3];
        GridPane gridMatrica = new GridPane();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                polja[i][j] = new TextField();
                polja[i][j].setPrefWidth(80);
                polja[i][j].setMaxWidth(80);
                polja[i][j].setAlignment(Pos.CENTER);
                polja[i][j].setEditable(false);
                gridMatrica.add(polja[i][j], j, i);
            }
        }
        gp.setVgap(5);
        gp.setHgap(5);

        HBox hb=new HBox(10);
        hb.getChildren().addAll(l, cb);

        BorderPane bp = new BorderPane();
        Label naslov = new Label("Magicni kvadrat");
        naslov.setStyle("-fx-font-size: 20px;");
        bp.setCenter(naslov);

        gp.setPadding(new Insets(5,5,5,5));

        gp.add(new Label(" "), 0, 0);
        gp.add(bp, 0, 1);
        gp.add(new Label(" "), 0, 3);
        gp.add(hb, 0, 4);
        gp.add(posalji, 0, 5);
        gp.add(gridMatrica, 0, 2, 2, 1);
        gp.add(poruka2, 0, 10);
        posalji.setOnAction(e -> pokreniKlijentaZadatak2());
        return gp;
    }
    public GridPane zadatak3(){
        GridPane gp = new GridPane();
        Label l = new Label("Unesite tekst: ");
        poruka3 = new Label();
        unos = new TextField();
        Button posalji = new Button("Proveri da li je uneti tekst palindorm");
        odg = new Label("ODGOVOR");
        gp.setVgap(5);
        gp.setHgap(5);
        BorderPane bp = new BorderPane();
        Label naslov = new Label("Palindrom");
        naslov.setStyle("-fx-font-size: 20px;");
        bp.setCenter(naslov);

        gp.setPadding(new Insets(5,5,5,5));

        gp.add(new Label(" "), 0, 0);
        gp.add(bp, 0, 1);
        gp.add(l,0,2);
        gp.add(unos, 0,3);
        gp.add(posalji, 0, 4);
        gp.add(odg,0, 5);
        gp.add(poruka3, 0, 6);
        posalji.setOnAction(e->pokreniKlijentaZadatak3());
        return gp;
    }
    public void pokreniKlijentaZadatak1(){
        try {
            InetAddress adresa = InetAddress.getByName("127.0.0.1");
            Socket soket = new Socket(adresa, 9000);
            BufferedReader in = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soket.getOutputStream())),true);

            String date = "";
            System.out.println(datum.getValue());
            if(datum.getValue() != null) {
                LocalDate d = datum.getValue();
                date = d.toString();
            }
            String prosledi = "ZADATAK1~";
            prosledi += date;
            out.println(prosledi);

            String primljeno = in.readLine();
            if(primljeno.contains("~")) {

                String[] podaci = primljeno.split("~");

                fizicki.setText(podaci[0]);
                emocionalni.setText(podaci[1]);
                intelekutalni.setText(podaci[2]);
                poruka.setText("");
            }else{
                poruka.setText(primljeno);
            }

            in.close();
            out.close();
            soket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pokreniKlijentaZadatak2(){
        try {
            InetAddress adresa = InetAddress.getByName("127.0.0.1");
            Socket soket = new Socket(adresa, 9000);
            BufferedReader in = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soket.getOutputStream())),true);

            String prosledi = "ZADATAK2~";

            if(cb.getValue() != null) {
                int broj = Integer.parseInt(cb.getValue().toString());

                prosledi += broj;
            }

            out.println(prosledi);

            String primljeno = in.readLine();
            if(primljeno.contains("!")){
                poruka2.setText(primljeno);
            }else {
                String[] str = primljeno.split(" ");
                int br = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        polja[i][j].setText(str[br]);
                        br++;
                    }
                }
                poruka2.setText("");
            }

            in.close();
            out.close();
            soket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void pokreniKlijentaZadatak3(){
        try {
            InetAddress adresa = InetAddress.getByName("127.0.0.1");
            Socket soket = new Socket(adresa, 9000);
            BufferedReader in = new BufferedReader(new InputStreamReader(soket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soket.getOutputStream())),true);

            String prosledi = "ZADATAK3~";

            String reci = unos.getText();

            prosledi+=reci;

            out.println(prosledi);

            String primljeno = in.readLine();

            odg.setText(primljeno);

            in.close();
            out.close();
            soket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
