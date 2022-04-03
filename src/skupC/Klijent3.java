package skupC;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.lang.reflect.Array;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Klijent3 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Group root = new Group();
    BorderPane borderPane = new BorderPane();

    Button start;
    Button[][] btn;
    boolean kliknutPrvi = false;
    boolean kliknutDrugi = false;
    int prviBroj;
    int drugiBroj;
    Label cest;

    Button start2;
    Button[][] btn2;
    Label cestitke;

    Button start3;
    Button[][] btn3;
    Label cestitke2;
    ArrayList<Integer> brojeviRnd;
    ArrayList<Integer> redosled = new ArrayList<>();

    static Stage glavniStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        glavniStage = primaryStage;
        primaryStage.setTitle("SKUP C");
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
        GridPane gridPane = new GridPane();
        GridPane gp = new GridPane();
        btn = new Button[4][4];
        cest=new Label();
        cest.setAlignment(Pos.CENTER);
        cest.setFont(new Font(25));
        cest.setTextFill(Color.GREEN);

        start = new Button("START");
        start.setMinHeight(50);
        start.setMinWidth(100);
        start.setFont(new Font(20));

        BorderPane bpb = new BorderPane();
        bpb.setCenter(start);

        int br=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                btn[i][j] = new Button();
                btn[i][j].setMinWidth(50);
                btn[i][j].setMinHeight(50);
                btn[i][j].setStyle("-fx-background-radius: 0;"+
                        "-fx-font-size:20;"+
                        "-fx-border-color: Black;");
                String n = String.format("%02d", br);
                btn[i][j].setText(n);
                br++;
                gp.add(btn[i][j], j, i);
            }
        }
        BorderPane bp = new BorderPane();
        Label naslov = new Label("MaxMin 4x4");
        naslov.setStyle("-fx-font-size: 25px;");
        bp.setCenter(naslov);

        gridPane.add(new Label("   "), 0, 0);
        gridPane.add(bp, 0, 1);
        gridPane.add(new Label("   "), 0, 2);
        gridPane.add(gp, 0, 3);
        gridPane.add(new Label("   "), 0, 4);
        gridPane.add(bpb, 0, 6);
        gridPane.add(cest, 0, 7);
        start.setOnAction(e -> pokreniKlijentaZadatak1());
        return gridPane;
    }
    public GridPane zadatak2(){
        GridPane gridPane = new GridPane();
        GridPane gp = new GridPane();
        cestitke=new Label();
        cestitke.setAlignment(Pos.CENTER);
        cestitke.setFont(new Font(25));
        cestitke.setTextFill(Color.GREEN);

        start2 = new Button("START");
        start2.setMinHeight(50);
        start2.setMinWidth(100);
        start2.setFont(new Font(20));

        BorderPane bpb = new BorderPane();
        bpb.setCenter(start2);

        btn2 = new Button[4][4];
        int br=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                btn2[i][j] = new Button();
                btn2[i][j].setMinWidth(50);
                btn2[i][j].setMinHeight(50);
                btn2[i][j].setStyle("-fx-background-radius: 0;"+
                        "-fx-font-size:20");
                String n = String.format("%02d", br);
                btn2[i][j].setText(n);
                br++;
                gp.add(btn2[i][j], j, i);
            }
        }
        BorderPane bp = new BorderPane();
        Label naslov = new Label("Slider 4x4");
        naslov.setStyle("-fx-font-size: 25px;");
        bp.setCenter(naslov);

        gridPane.add(new Label("   "), 0, 0);
        gridPane.add(bp, 0, 1);
        gridPane.add(new Label("   "), 0, 2);
        gridPane.add(gp, 0, 3);
        gridPane.add(new Label("   "), 0, 4);
        gridPane.add(bpb, 0, 10);
        gridPane.add(cestitke, 0, 11);
        start2.setOnAction(e -> pokreniKlijentaZadatak2());
        return gridPane;
    }
    public GridPane zadatak3(){
        GridPane gridPane = new GridPane();
        GridPane gp = new GridPane();
        cestitke2=new Label();
        cestitke2.setAlignment(Pos.CENTER);
        cestitke2.setFont(new Font(25));
        cestitke2.setTextFill(Color.GREEN);

        start3 = new Button("START");
        start3.setMinHeight(50);
        start3.setMinWidth(100);
        start3.setFont(new Font(20));

        BorderPane bpb = new BorderPane();
        bpb.setCenter(start3);

        btn3 = new Button[3][3];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                btn3[i][j] = new Button();
                btn3[i][j].setMinWidth(50);
                btn3[i][j].setMinHeight(50);
                btn3[i][j].setStyle("-fx-background-color: green;" +
                        "-fx-border-color: black;" +
                        "-fx-text-fill:transparent;");
                gp.add(btn3[i][j], j, i);
            }
        }
        BorderPane bp = new BorderPane();
        Label naslov = new Label("Sequence 3x3");
        naslov.setStyle("-fx-font-size: 25px;");
        bp.setCenter(naslov);

        gridPane.add(new Label("   "), 0, 0);
        gridPane.add(bp, 0, 1);
        gridPane.add(new Label("   "), 0, 2);
        gridPane.add(gp, 0, 3);
        gridPane.add(new Label("   "), 0, 4);
        gridPane.add(bpb, 0, 10);
        gridPane.add(cestitke2, 0, 11);
        start3.setOnAction(e -> pokreniKlijentaZadatak3());
        return gridPane;
    }
    public void pokreniKlijentaZadatak1(){
        cest.setText(" ");
        ArrayList<Integer> brojevi = new ArrayList<>();
        Random rnd = new Random();
        while(true){
            if(brojevi.size()==16) break;
            int broj = rnd.nextInt(16);
            if(brojevi.isEmpty()) {
                brojevi.add(broj);
            }
            if(!brojevi.contains(broj)) {
                brojevi.add(broj);
            }
        }

        int br=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                btn[i][j].setDisable(false);
                String n = String.format("%02d", brojevi.get(br));
                br++;
                btn[i][j].setText(n);
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dogadjaj(event);
                    }
                });
            }
        }
    }
    private void dogadjaj(ActionEvent event) {
        //System.out.println(((Button)event.getSource()).getText());

        int br=-1;
        if(!((Button)event.getSource()).getText().equals(".")) {
            br = Integer.parseInt(((Button) event.getSource()).getText());
        }

        Integer[] brojevi = new Integer[16];
        int b = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(btn[i][j].getText().equals(".")){
                    brojevi[b] = -1;
                }else{
                    brojevi[b] = Integer.parseInt(btn[i][j].getText());
                }
                b++;
            }
        }
        if(kliknutPrvi==true && kliknutDrugi==false){
            drugiBroj=br;
            kliknutDrugi=true;
            ((Button)event.getSource()).setStyle("-fx-background-radius: 0;"+
                    "-fx-font-size:20;"+
                    "-fx-border-color: Blue;");
        }

        if(kliknutPrvi == false && kliknutDrugi==false){
            prviBroj = br;
            kliknutPrvi = true;
            ((Button)event.getSource()).setStyle("-fx-background-radius: 0;"+
                    "-fx-font-size:20;"+
                    "-fx-border-color: Blue;");
        }

        if(kliknutPrvi==true && kliknutDrugi==true){
            int max = brojevi[0];
            int min = brojevi[0];
            for(int i=1; i<16; i++){
                if(max==-1){max=brojevi[i];}
                if(min==-1){min=brojevi[i];}
                if(brojevi[i] > max) {max = brojevi[i];}
                if(brojevi[i] < min && brojevi[i]!=-1) {min=brojevi[i];}
            }
            if(max==prviBroj && min==drugiBroj){
                for(int i=0; i<4; i++){
                    for(int j=0; j<4; j++){
                        if(!btn[i][j].getText().equals(".")) {
                            if (Integer.parseInt(btn[i][j].getText()) == max) {
                                btn[i][j].setText(".");
                                btn[i][j].setDisable(true);
                            }
                        }
                        if(!btn[i][j].getText().equals(".")) {
                            if (Integer.parseInt(btn[i][j].getText()) == min) {
                                btn[i][j].setText(".");
                                btn[i][j].setDisable(true);
                            }
                        }

                    }
                }
            }
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    btn[i][j].setStyle("-fx-background-radius: 0;"+
                            "-fx-font-size:20;"+
                            "-fx-border-color: Black;");
                }
            }
            kliknutPrvi=false;
            kliknutDrugi=false;
        }

        boolean sve = true;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(!btn[i][j].getText().equals(".")){
                    sve=false;
                }
            }
        }
        if(sve==true){
            cest.setText("Čestitamo!!!");
        }

    }

    public void pokreniKlijentaZadatak2(){
        cestitke.setText(" ");
        ArrayList<Integer> brojevi = new ArrayList<>();
        Random rnd = new Random();
        while(true){
            if(brojevi.size()==16) break;
            int broj = rnd.nextInt(16);
            if(brojevi.isEmpty()) {
                brojevi.add(broj);
            }
            if(!brojevi.contains(broj)) {
                brojevi.add(broj);
            }
        }

        int br=0;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                String n = String.format("%02d", brojevi.get(br));
                br++;
                btn2[i][j].setText(n);

                if(btn2[i][j].getText().equals("00")){
                    btn2[i][j].setStyle("-fx-background-color: yellow;"+
                            "-fx-background-radius: 0;"+
                            "-fx-font-size:20");
                }else{
                    btn2[i][j].setStyle("-fx-background-radius: 0;"+
                            "-fx-font-size:20");
                }
                btn2[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dogadjaj2(event);
                    }
                });
            }
        }
    }
    private void dogadjaj2(ActionEvent event) {
        //System.out.println(((Button)event.getSource()).getText());
        int br = Integer.parseInt(((Button)event.getSource()).getText());

        boolean provera=false;

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(Integer.parseInt(btn2[i][j].getText())==br){
                    if(i > 0 && i<4){
                        if(Integer.parseInt(btn2[i-1][j].getText()) == 0){
                            String t = btn2[i][j].getText();
                            btn2[i][j].setText(btn2[i-1][j].getText());
                            btn2[i-1][j].setText(t);
                            provera=true;

                            btn2[i][j].setStyle("-fx-background-color: yellow;"+
                                    "-fx-background-radius: 0;"+
                                    "-fx-font-size:20");
                            btn2[i-1][j].setStyle("-fx-background-radius: 0;"+
                                    "-fx-font-size:20");

                            if(provera==true) break;
                        }
                    }
                    if(i > -1 && i<3){
                        if(Integer.parseInt(btn2[i+1][j].getText()) == 0){
                            String t = btn2[i][j].getText();
                            btn2[i][j].setText(btn2[i+1][j].getText());
                            btn2[i+1][j].setText(t);
                            provera=true;

                            btn2[i][j].setStyle("-fx-background-color: yellow;"+
                                    "-fx-background-radius: 0;"+
                                    "-fx-font-size:20");
                            btn2[i+1][j].setStyle("-fx-background-radius: 0;"+
                                    "-fx-font-size:20");

                            if(provera==true) break;
                        }
                    }
                    if(j > 0 && j<4){
                        if(Integer.parseInt(btn2[i][j-1].getText()) == 0){
                            String t = btn2[i][j].getText();
                            btn2[i][j].setText(btn2[i][j-1].getText());
                            btn2[i][j-1].setText(t);
                            provera=true;

                            btn2[i][j].setStyle("-fx-background-color: yellow;"+
                                    "-fx-background-radius: 0;"+
                                    "-fx-font-size:20");
                            btn2[i][j-1].setStyle("-fx-background-radius: 0;"+
                                    "-fx-font-size:20");

                            if(provera==true) break;
                        }
                    }
                    if(j > -1 && j<3){
                        if(Integer.parseInt(btn2[i][j+1].getText()) == 0){
                            String t = btn2[i][j].getText();
                            btn2[i][j].setText(btn2[i][j+1].getText());
                            btn2[i][j+1].setText(t);
                            provera=true;

                            btn2[i][j].setStyle("-fx-background-color: yellow;"+
                                    "-fx-background-radius: 0;"+
                                    "-fx-font-size:20");
                            btn2[i][j+1].setStyle("-fx-background-radius: 0;"+
                                    "-fx-font-size:20");

                            if(provera==true) break;
                        }
                    }
                }
            }
            if(provera==true) break;
        }

        int uporedi=0;
        boolean poruka=true;
        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                if(uporedi!=Integer.parseInt(btn2[i][j].getText())){
                    poruka=false;
                }
                uporedi++;
            }
        }
        if(poruka==true){
            cestitke.setText("Čestitamo!!!");
        }

    }


    private int mapirajBrojZaI (int broj) {
        switch (broj) {
            default:
            case 1:
            case 2:
            case 3:
                return 0;
            case 4:
            case 5:
            case 6:
                return 1;
            case 7:
            case 8:
            case 9:
                return 2;
        }
    }

    private int mapirajBrojZaJ (int broj) {
        switch (broj) {
            default:
            case 1:
            case 4:
            case 7:
                return 0;
            case 2:
            case 5:
            case 8:
                return 1;
            case 3:
            case 6:
            case 9:
                return 2;
        }
    }

    public void pokreniKlijentaZadatak3(){
        System.out.println();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                btn3[i][j].setStyle("-fx-background-color: green;" +
                        "-fx-border-color: black;"+
                        "-fx-text-fill:transparent;");
            }
        }
        cestitke2.setText("");
        brojeviRnd = new ArrayList<>();
        Random rnd = new Random();
        while(true){
            if(brojeviRnd.size()==9) break;
            int broj = rnd.nextInt(9) + 1;
            if(brojeviRnd.isEmpty()) {
                brojeviRnd.add(broj);
            }
            if(!brojeviRnd.contains(broj)) {
                brojeviRnd.add(broj);
            }
        }

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                btn3[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ((Button)event.getSource()).setStyle("-fx-background-color: green;" +
                                "-fx-border-color: black;"+
                                "-fx-text-fill:transparent;");
                        dogadjaj3(event);
                    }
                });
            }
        }

        for(int k = 0; k < brojeviRnd.size(); k++) {
            System.out.println("(" + brojeviRnd.get(k) + ")");

            int brojac = k;
            int i = mapirajBrojZaI(brojeviRnd.get(k));
            int j = mapirajBrojZaJ(brojeviRnd.get(k));

            Timeline lightAnimation = new Timeline(
                new KeyFrame(Duration.seconds((k + 1) * 0.25), event -> {
                    btn3[i][j].setStyle("-fx-base: yellow;" +
                            "-fx-border-color: black;"+
                            "-fx-text-fill:transparent;");
                    btn3[i][j].setText(brojeviRnd.get(brojac)+"");
                })
            );
            lightAnimation.play();
        }
    }
    private void dogadjaj3(ActionEvent event) {
        redosled.add(Integer.parseInt(((Button)event.getSource()).getText()));

        //System.out.println("Poslat: " + Integer.parseInt(((Button)event.getSource()).getText()));

        if(redosled.size()==brojeviRnd.size()) {
            boolean provera=true;
            for (int i = 0; i < brojeviRnd.size(); i++) {
                if(redosled.get(i)!=brojeviRnd.get(i)){
                    provera=false;
                    break;
                }
            }
            if(provera==true){
                cestitke2.setText("Čestitamo!!!");
            }else{
                cestitke2.setText("Niste uspeli!!!");
            }
            redosled = new ArrayList<>();
        }
    }
}
