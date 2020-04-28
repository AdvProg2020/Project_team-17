import View.CommandProcessor;
import View.MainMenu;

import java.util.Scanner;

public class Main {
    //...
    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.runWithMenu();
    }
}
