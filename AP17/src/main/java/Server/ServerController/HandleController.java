package Server.ServerController;

public class HandleController {

    //System.out.println(command);
    public static void handleFunction(String command) throws Exception {
        switch (command) {
            case "Login Manager": {
                RegisterAndLoginController.loginManager();
                break;
            }
            case "Register Manager": {
                RegisterAndLoginController.registerManager();
                break;
            }

        }

    }
}