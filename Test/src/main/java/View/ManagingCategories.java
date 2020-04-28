package View;

public class ManagingCategories extends Menu {
    public ManagingCategories( Menu parentMenu) {
        super("Manage Categories", parentMenu);
    }
    protected Menu editCategory(){
        return new Menu("Edit Category",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu addCategory(){
        return new Menu("Add Category",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu removeCategory(){
        return new Menu("Remove Category",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
}
