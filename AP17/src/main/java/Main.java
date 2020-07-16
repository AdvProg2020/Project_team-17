import Models.Accounts.Manager;
import View.MainMenu;
import View.Menu;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Main extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        //LoadFromFile.load(new Gson());
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();

    }


}
