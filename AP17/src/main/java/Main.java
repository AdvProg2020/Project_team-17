import Client.Client;
import Controller.LoadFromFile;
import Controller.WriteIntoFile;
import View.MainMenu;
import View.Menu;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args) throws IOException {
        Client.run();
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        LoadFromFile.load(new Gson());
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();

        stage.setOnCloseRequest(e -> {
            try {
                WriteIntoFile.writeIntoFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Platform.exit();
            System.exit(0);
        });
    }


}
