package View;

import java.util.Scanner;

public class CommandProcessor {
    private Scanner scanner;

    public CommandProcessor() {
        scanner = new Scanner(System.in);
    }

    public void runWithMenu() {
        Menu currentMenu = new MainMenu();
        Menu.setScanner(this.scanner);
        currentMenu.show();
        currentMenu.execute();
    }
}
