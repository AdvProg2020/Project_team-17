import View.MainMenu;
import View.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window = new Stage();
    public static void main(String[] args){
        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();

    }
}
