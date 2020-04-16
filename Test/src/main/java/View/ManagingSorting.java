package View;

public class ManagingSorting extends Menu {
    public ManagingSorting( Menu parentMenu) {
        super("Sorting", parentMenu);
    }
    protected Menu showSorting(){
        return new Menu("Show Sorting",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu sort(){
        return new Menu("Sort",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu filtering(){
        return new Menu("Filtering",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu currentSort(){
        return new Menu("Current Sort",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu disableSort(){
        return new Menu("Disable Sort",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
