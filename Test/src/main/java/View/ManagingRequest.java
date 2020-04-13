package View;
//method haye in class ro motmaen nitam dorost neveshtam ya na
public class ManagingRequest extends Menu {
    public ManagingRequest(Menu parentMenu) {
        super("Manage Request", parentMenu);
        //other option
    }
    protected Menu details(){
        return new Menu("Details",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu accept(){
        return new Menu("Accept",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu decline(){
        return new Menu("Decline",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
