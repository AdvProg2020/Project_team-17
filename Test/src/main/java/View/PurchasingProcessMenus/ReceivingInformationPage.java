package View.PurchasingProcessMenus;

import View.Menu;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ReceivingInformationPage extends Menu {
    private static String addressString;
    private static String phoneNumString;

    public ReceivingInformationPage(Menu parentMenu) {
        super("Receiver Information", parentMenu);
    }

    @Override
    public void show() {
        setReceivingInfoScene();
    }


    public void setReceivingInfoScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        Label title = new Label("Receiving information to continue purchasing");
        VBox vBox1 = new VBox(10);
        TextField address = new TextField();
        address.setPromptText("address");

        TextField phoneNumber = new TextField();
        phoneNumber.setPromptText("phone number");

        Button next = new Button("next");
        next.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addressString = address.getText();
                phoneNumString = phoneNumber.getText();
                handleDiscountCodePage();
                address.clear();
                phoneNumber.clear();
            }
        });
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parentMenu.show();
                address.clear();
                phoneNumber.clear();
            }
        });
        vBox.getChildren().addAll(back);
        pane.setLeft(vBox);
        vBox1.getChildren().addAll(title, address, phoneNumber, next);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 400);
        Menu.window.setScene(scene);
    }

    public void handleDiscountCodePage() {
        DiscountCodePage discountCodePage = new DiscountCodePage(this);
        discountCodePage.show();
    }

    public static String getAddress() {
        return addressString;
    }

    public static String getPhoneNum() {
        return phoneNumString;
    }
}