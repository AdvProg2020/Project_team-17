package View;

public class ManagingDiscountCode extends Menu {
        public ManagingDiscountCode( Menu parentMenu) {
            super("View Discount Code", parentMenu);
            //other options
        }
        protected Menu viewDiscountCode(){
            return new Menu("View Discount Menu" ,this) {
                @Override
                public void show() {

                }

                @Override
                public void execute() {

                }
            };
        }
        protected Menu editDiscountCode(){
            return new Menu("Edit Discount Code",this) {
                @Override
                public void show() {

                }

                @Override
                public void execute() {

                }
            };
        }
        protected Menu removeDiscountCode(){
            return new Menu("Remove Discount Code",this) {
                @Override
                public void show() {

                }

                @Override
                public void execute() {

                }
            };
        }
    }


