package View;

import Controller.AccountsManager.SellerAbilitiesManager;
import Controller.ViewAndEditAccountManager;

public class SellerMenu extends Menu {
    private ViewAndEditAccountManager viewAndEditAccountManager;
    private SellerAbilitiesManager sellerAbilitiesManager;
    public SellerMenu( Menu parentMenu) {
        super("Seller Menu", parentMenu);
        //other options
    }


    protected Menu LogoutAccount() {
        return new Menu("Logout", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewPersonalInfo(){
        return new Menu("View Personal Info",this) {
            @Override
            public void show() {
            }

            @Override
            public void execute() {
            }
        };
    }
    protected Menu editField(){
        return new Menu("Edit Field",this) {
            @Override
            public void show() {
                super.show();
            }

            @Override
            public void execute() {
                super.execute();
            }
        };
    }
    protected Menu viewCompanyInformation(){
        return new Menu("View Company Information" ,this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewSalesHistory(){
        return new Menu("View Sales History", this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu addProduct(){
        return new Menu("Add Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu deleteProduct(){
        return new Menu("Delete Product",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu showCategories(){
        return new Menu("Show Categories",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }
    protected Menu viewBalance(){
        return new Menu("View Balance",this) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {

            }
        };
    }


}
