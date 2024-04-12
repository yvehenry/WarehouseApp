package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Date;

public class ShipmentOrderControl {

    @FXML
    private Button AddNoteButton;

    @FXML
    private Button AddOrderButton;

    @FXML
    private Text ContainerNumberText;

    @FXML
    private TextField ContainerNumberTextField;

    @FXML
    private Text DateText;

    @FXML
    private TextField DateTextField;

    @FXML
    private Button DeleteNoteButton;

    @FXML
    private Button DeleteOrderButton;

    @FXML
    private Text NoteDescriptionText;

    @FXML
    private TextField NoteDescriptionTextField;

    @FXML
    private Text NoteIndexText;

    @FXML
    private TextField NoteIndexTextField;

    @FXML
    private Text NoteMessage;

    @FXML
    private Text NoteOrderIDText;

    @FXML
    private TextField NoteOrderIDTextField;

    @FXML
    private Text NoteTakerText;

    @FXML
    private TextField NoteTakerTextField;

    @FXML
    private Text NoteToShipmentOrderText;

    @FXML
    private Text OrderDescriptionText;

    @FXML
    private TextField OrderDescriptionTextField;

    @FXML
    private Text OrderIDText;

    @FXML
    private TextField OrderIDTextField;

    @FXML
    private Text OrderMessage;

    @FXML
    private Text OrderPlacerText;

    @FXML
    private TextField OrderPlacerTextField;

    @FXML
    private Text PlacedOnDateText;

    @FXML
    private TextField PlacedOnDateTextField;

    @FXML
    private Text QuantityText;

    @FXML
    private TextField QuantityTextField;

    @FXML
    private Text ShipmentOrderText;

    @FXML
    private Button UpdateNoteButton;

    @FXML
    private Button UpdateOrderButton;

    int orderID;

    String orderPlacer;

    Date placedOnDate;

    String description;

    int containerNumber;

    int quantity;

    String noteTaker;

    Date date;

    int noteIndex;

    @FXML
    void addNote(ActionEvent event) {
        try {
            orderID = Integer.parseInt(NoteOrderIDTextField.getText());
            noteTaker = NoteTakerTextField.getText();
            date = Date.valueOf(DateTextField.getText());
            description = NoteDescriptionTextField.getText();
            String message = ca.mcgill.ecse.wareflow.controller.ShipmentNoteController
                    .addShipmentNote(date, description, orderID, noteTaker);
            if (message.isEmpty()) {
                message = "Note has been added successfully!";
            }
            NoteMessage.setText(message);
        } catch (NumberFormatException e) {
            NoteMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            NoteMessage.setText("Enter a correct date format.");
        } catch (Exception e) {
            NoteMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    void addOrder(ActionEvent event) {
        try {
            orderID = Integer.parseInt(OrderIDTextField.getText());
            orderPlacer = OrderPlacerTextField.getText();
            placedOnDate = Date.valueOf(PlacedOnDateTextField.getText());
            description = OrderDescriptionTextField.getText();
            containerNumber = Integer.parseInt(ContainerNumberTextField.getText());
            quantity = Integer.parseInt(QuantityTextField.getText());
            String message = ca.mcgill.ecse.wareflow.controller.ShipmentOrderController
                    .addShipmentOrder(orderID, placedOnDate, description, orderPlacer, containerNumber, quantity);
            if (message.isEmpty()) {
                message = "Order has been added successfully!";
            }
            OrderMessage.setText(message);
        } catch (NumberFormatException e) {
            OrderMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            OrderMessage.setText("Enter a correct date format.");
        } catch (Exception e) {
            OrderMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    void deleteNote(ActionEvent event) {
        try {
            orderID = Integer.parseInt(NoteOrderIDTextField.getText());
            noteIndex = Integer.parseInt(NoteIndexTextField.getText());
            ca.mcgill.ecse.wareflow.controller.ShipmentNoteController
                    .deleteShipmentNote(orderID, noteIndex);
            if (ShipmentOrder.hasWithId(orderID)) {
                NoteMessage.setText("Order ID doesn't exist.");
            } else if (ShipmentOrder.getWithId(orderID).hasShipmentNotes()) {
                NoteMessage.setText("Note doesn't exist.");
            } else {
                NoteMessage.setText("Note has been deleted successfully!");
            }
        } catch (NumberFormatException e) {
            NoteMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            NoteMessage.setText("Enter a correct date format.");
        } catch (IndexOutOfBoundsException e) {
            NoteMessage.setText("Note index doesn't exist.");
        } catch (Exception e) {
            NoteMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    void deleteOrder(ActionEvent event) {
        try {
            orderID = Integer.parseInt(OrderIDTextField.getText());
            ca.mcgill.ecse.wareflow.controller.ShipmentOrderController
                    .deleteShipmentOrder(orderID);
            if (ShipmentOrder.hasWithId(orderID)) {
                OrderMessage.setText("Order has been deleted successfully!");
            } else {
                OrderMessage.setText("Order ID doesn't exist.");
            }
        } catch (NumberFormatException e) {
            OrderMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            OrderMessage.setText("Enter a correct date format.");
        } catch (Exception e) {
            OrderMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    void updateNote(ActionEvent event) {
        try {
            orderID = Integer.parseInt(NoteOrderIDTextField.getText());
            noteTaker = NoteTakerTextField.getText();
            date = Date.valueOf(DateTextField.getText());
            description = NoteDescriptionTextField.getText();
            noteIndex = Integer.parseInt(NoteIndexTextField.getText());
            String message = ca.mcgill.ecse.wareflow.controller.ShipmentNoteController
                    .updateShipmentNote(orderID, noteIndex, date, description, noteTaker);
            if (message.isEmpty()) {
                message = "Note has been updated successfully!";
            }
            NoteMessage.setText(message);
        } catch (NumberFormatException e) {
            NoteMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            NoteMessage.setText("Enter a correct date format.");
        } catch (Exception e) {
            NoteMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    void updateOrder(ActionEvent event) {
        try {
            orderID = Integer.parseInt(OrderIDTextField.getText());
            orderPlacer = OrderPlacerTextField.getText();
            placedOnDate = Date.valueOf(PlacedOnDateTextField.getText());
            description = OrderDescriptionTextField.getText();
            containerNumber = Integer.parseInt(OrderIDTextField.getText());
            quantity = Integer.parseInt(QuantityTextField.getText());
            String message = ca.mcgill.ecse.wareflow.controller.ShipmentOrderController
                    .updateShipmentOrder(orderID, placedOnDate, description, orderPlacer, containerNumber, quantity);
            if (message.isEmpty()) {
                message = "Order has been updated successfully!";
            }
            OrderMessage.setText(message);
        } catch (NumberFormatException e) {
            OrderMessage.setText("Enter only a number in the corresponding box.");
        } catch (IllegalArgumentException e) {
            OrderMessage.setText("Enter a correct date format.");
        } catch (Exception e) {
            OrderMessage.setText(e.getMessage());
        }
        WareFlowFxmlView.getInstance().refresh();
    }
}