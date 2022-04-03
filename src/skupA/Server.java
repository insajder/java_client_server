package skupA;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    TextArea txt;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();

        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(100);
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(100);
        gp.getColumnConstraints().add(col);
        gp.getRowConstraints().add(row);
        txt = new TextArea();

        gp.setVgap(1);
        gp.setHgap(1);
        gp.setPadding(new Insets(5,5,5,5));
        gp.add(txt, 0, 0);

        primaryStage.setTitle("SERVER");
        Scene scene = new Scene(gp, 500,600, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(()->{pokreniServer();}).start();
    }

    public void pokreniServer(){
        try {
            ServerSocket ss = new ServerSocket(9000);
            txt.appendText("Server je pokrenut\n");
            while(true){
                Socket soket = ss.accept();
                new ServerThread(soket, txt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
