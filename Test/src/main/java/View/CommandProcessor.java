package View;

import java.util.Scanner;

public class CommandProcessor {
    private Scanner scanner;

    public CommandProcessor() {
        scanner = new Scanner(System.in);
    }

    public void runWithMenu() {
        Menu.setScanner(this.scanner);
        Menu currentMenu = new MainMenu();
        currentMenu.show();
        currentMenu.execute();
    }
}
