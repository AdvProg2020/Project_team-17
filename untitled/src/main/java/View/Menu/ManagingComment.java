package View.Menu;

public class ManagingComment extends Menu {
    public ManagingComment( Menu parentMenu) {
        super("Comment", parentMenu);
        //other options
    }
    protected Menu addComment(){
        return new Menu("Add Comment",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
