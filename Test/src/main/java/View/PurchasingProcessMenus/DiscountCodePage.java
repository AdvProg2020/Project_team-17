package View.PurchasingProcessMenus;

import Controller.AccountsManager.CustomerAbilitiesManager;
import Models.DiscountCode;
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

public class DiscountCodePage extends Menu {
    private static String discountCode = "";

    public DiscountCodePage(Menu parentMenu) {
        super("Discount code", parentMenu);
    }

    @Override
    public void show() {
        setDiscountCodeScene();
    }

    public void setDiscountCodeScene() {
        BorderPane pane = new BorderPane();
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.TOP_LEFT);
        Button back = new Button("Back");
        back.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                parentMenu.show();
            }
        });
        Label title = new Label("discount code");
        pane.setTop(title);
        vBox.getChildren().addAll(back, title);

        VBox vBox1 = new VBox(10);
        Label notify = new Label();
        TextField textField = new TextField();
        textField.setPromptText("discount code");
        Button next = new Button("done/next");
        Button nextWO = new Button("no discount code/next");

        next.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    CustomerAbilitiesManager.checkDiscountCodeValidation(textField.getText());
                    discountCode = textField.getText();
                    DiscountCode.getDiscountCodeWithCode(textField.getText()).setUsageOfDiscountCode();
                    handlePaymentPage();
                } catch (Exception e) {
                    notify.setStyle("-fx-text-fill: #ff4f59");
                    notify.setText(e.getMessage());
                }
            }
        });
        nextWO.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handlePaymentPage();
            }
        });
        vBox1.getChildren().addAll(textField, next, nextWO, notify);
        pane.setCenter(vBox1);
        Scene scene = new Scene(pane, 400, 400);
        Menu.window.setScene(scene);
    }

    public void handlePaymentPage() {
        PaymentPage paymentPage = new PaymentPage(this);
        paymentPage.show();
    }

    public static String getCodeOfDiscountCode() {
        return discountCode;
    }

    public static DiscountCode getDiscountCode() {
        return DiscountCode.getDiscountCodeWithCode(getCodeOfDiscountCode());
    }
}