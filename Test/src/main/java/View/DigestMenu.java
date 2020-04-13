package View;

public class DigestMenu extends Menu {
    public DigestMenu(Menu parentMenu) {
        super("Digest Menu", parentMenu);
        //other options
    }
    protected Menu addToCart(){
        return new Menu("Add To Cart",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }

}
