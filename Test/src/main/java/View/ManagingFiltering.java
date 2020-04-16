package View;

public class ManagingFiltering extends Menu {
    public ManagingFiltering( Menu parentMenu) {
        super("Filtering", parentMenu);
        //other options
    }
    protected Menu showAvailableFilters(){
        return new Menu("Show Available Filters",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu filter(){
        return new Menu("Filter",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu currentFilter(){
        return new Menu("Current Filter",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu disableFilters(){
        return new Menu("Filtering",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
