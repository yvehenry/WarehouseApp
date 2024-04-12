package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class ViewUtils {

    /**
     * Calls the controller and shows an error, if applicable.
     */
    public static boolean callController(String result) {
        if (result.isEmpty()) {
            WareFlowFxmlView.getInstance().refresh();
            return true;
        }
        showError(result);
        return false;
    }

    /**
     * Calls the controller and returns true on success. This method is included for readability.
     */
    public static boolean successful(String controllerResult) {
        return callController(controllerResult);
    }

    /**
     * Creates a popup window.
     *
     * @param title:   title of the popup window
     * @param message: message to display
     */
    public static void makePopupWindow(String title, String message) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogPane = new VBox();

        // create UI elements
        Text text = new Text(message);
        Button okButton = new Button("OK");
        okButton.setOnAction(a -> dialog.close());

        // display the popup window
        int innerPadding = 10; // inner padding/spacing
        int outerPadding = 100; // outer padding
        dialogPane.setSpacing(innerPadding);
        dialogPane.setAlignment(Pos.CENTER);
        dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
        dialogPane.getChildren().addAll(text, okButton);
        Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
        dialog.setScene(dialogScene);
        dialog.setTitle(title);
        dialog.show();
    }

    public static void showError(String message) {
        makePopupWindow("Error", message);
    }


    /**
     * Gets the shipment orders from the controller
     *
     * @return ObservableList<TOShipmentOrder> - Returns an ObservableList containing all shipment order as shipment order objects.
     * @author Neeshal Imrit
     */
    public static ObservableList<TOShipmentOrder> getOrders() {
        List<TOShipmentOrder> TOShipmentOrder = ShipmentOrderController.getOrders();
        // as javafx works with observable list, we need to convert the java.util.List to
        // javafx.collections.observableList
        return FXCollections.observableList(TOShipmentOrder);
    }

}