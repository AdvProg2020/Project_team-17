import Client.Client;
import Models.Bank.BankAccount;
import Models.ShopBankAccount;
import View.MainMenu;
import View.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Stage window = new Stage();

    public static void main(String[] args) throws IOException {
        Client.run();
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception {
        //LoadFromFile.load(new Gson());
        ShopBankAccount.getInstance();
        System.out.println(BankAccount.getAllBankAccounts().size());
        Menu.setWindow(window);
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}
