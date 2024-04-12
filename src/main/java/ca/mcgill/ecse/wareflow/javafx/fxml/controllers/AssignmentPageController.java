package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.OrderController;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Date;

/**
 * <h1>AssignOrderController</h1>
 * This class is the controller for the 'AssignOrder.fxml' view.
 *
 * @author Jason Shao
 */

public class AssignmentPageController {
    private static final WareFlow wflow = WareFlowApplication.getWareFlow();

    @FXML
    private TextField assignOrderIDTextField, assignEmployeeUsernameTextField, noteDescriptionTextField, noteDateTextField;
    @FXML
    private Button assignOrderButton;
    @FXML
    private Button startChangeOrderButton;
    @FXML
    private Button completeChangeOrderButton;
    @FXML
    private Button approveChangeOrderButton;
    @FXML
    private Button disapproveChangeOrderButton;
    @FXML
    private ChoiceBox<String> timeEstimateChoiceBox, priorityChoiceBox, requiresApprovalChoiceBox, employeeUsernameChoiceBox;

    @FXML
    /**
     * <h2>initialize</h2>
     * This method is called to update the dropdown menus in the asset page.
     *
     * @author Jason Shao
     */
    public void initialize() {
        // the driver and assignment choice boxes are refreshable
        ObservableList<String> priorities =
                FXCollections.observableArrayList("Low", "Normal", "Urgent");

        ObservableList<String> timeEstimate =
                FXCollections.observableArrayList("LessThanADay", "OneToThreeDays", "ThreeToSevenDays", "OneToThreeWeeks", "ThreeOrMoreWeeks");

        ObservableList<String> approvalRequired =
                FXCollections.observableArrayList("Yes", "No");

        requiresApprovalChoiceBox.setItems(approvalRequired);
        requiresApprovalChoiceBox.setValue("No");

        priorityChoiceBox.setItems(priorities);
        priorityChoiceBox.setValue(null);

        timeEstimateChoiceBox.setItems(timeEstimate);
        timeEstimateChoiceBox.setValue(null);

    }

    @FXML
    /**
     * <h2>assignButtonClicked</h2>
     * This method is called whenever the "assign" button is clicked on the assignment page.
     *
     * @param event This is the action of the "assign" button being clicked.
     * @author Jason Shao
     */
    public void assignButtonClicked(ActionEvent event) {
        String orderIdString = assignOrderIDTextField.getText();
        String employeeUsername = assignEmployeeUsernameTextField.getText();
        String priorityString = priorityChoiceBox.getValue();
        String timeEstimateString = timeEstimateChoiceBox.getValue();
        String approvalString = requiresApprovalChoiceBox.getValue();

        if (orderIdString == null || orderIdString.trim().isEmpty()) {
            ViewUtils.showError("The order number cannot be empty");
        } else if (employeeUsername == null) {
            ViewUtils.showError("An employee must be specified");
        } else if (priorityString == null || priorityString.trim().isEmpty()) {
            ViewUtils.showError("Please select a priority");
        } else if (timeEstimateString == null || timeEstimateString.trim().isEmpty()) {
            ViewUtils.showError("Please select a time estimate");
        } else {
            boolean approval = ("Yes".compareToIgnoreCase(approvalString) == 0);
            int orderId = Integer.parseInt(orderIdString);
            TimeEstimate timeEstimate = TimeEstimate.valueOf(timeEstimateString);
            PriorityLevel priority = PriorityLevel.valueOf(priorityString);
            String errorMessage = OrderController.assign(orderId, employeeUsername, timeEstimate,
                    priority, approval);
            if (errorMessage.isEmpty()) {
                assignOrderIDTextField.setText("");
            } else {
                ViewUtils.showError(errorMessage);
            }
        }
    }

    @FXML
    /**
     * <h2>startWorkButtonClicked</h2>
     * This method is called whenever the "start work" button is clicked on the assignment page.
     *
     * @param event This is the action of the "start work" button being clicked.
     *
     * @author Jason Shao
     */
    public void startWorkButtonClicked(ActionEvent event) {
        String orderIdString = assignOrderIDTextField.getText();
        if (orderIdString == null || orderIdString.trim().isEmpty()) {
            ViewUtils.showError("The order number cannot be empty");
        } else {
            int orderId = Integer.parseInt(orderIdString);
            String errorMessage = OrderController.startShipmentOrder(wflow.getOrder(orderId));
            if (errorMessage.isEmpty()) {
                assignOrderIDTextField.setText("");
            } else {
                ViewUtils.showError(errorMessage);
            }
        }
    }


    @FXML
    /**
     * <h2>completeWorkButtonClicked</h2>
     * This method is called whenever the "complete work" button is clicked on the assignment page.
     *
     * @param event This is the action of the "complete work" button being clicked.
     *
     * @author Jason Shao
     */
    public void completeWorkButtonClicked(ActionEvent event) {
        String orderIdString = assignOrderIDTextField.getText();
        if (orderIdString == null || orderIdString.trim().isEmpty()) {
            ViewUtils.showError("The order number cannot be empty");
        } else {
            int orderId = Integer.parseInt(orderIdString);
            String errorMessage = OrderController.completeShipmentOrder(wflow.getOrder(orderId));
            if (errorMessage.isEmpty()) {
                assignOrderIDTextField.setText("");
            } else {
                ViewUtils.showError(errorMessage);
            }
        }
    }

    @FXML
    /**
     * <h2>approveButtonClicked</h2>
     * This method is called whenever the "approve" button is clicked on the assignment page.
     *
     * @param event This is the action of the "approve" button being clicked.
     * @author Jason Shao
     */
    public void approveButtonClicked(ActionEvent event) {
        String orderIdString = assignOrderIDTextField.getText();
        if (orderIdString == null || orderIdString.trim().isEmpty()) {
            ViewUtils.showError("The order number cannot be empty");
        } else {
            int orderId = Integer.parseInt(orderIdString);
            String errorMessage = OrderController.approveShipmentOrder(wflow.getOrder(orderId));
            if (errorMessage.isEmpty()) {
                assignOrderIDTextField.setText("");
            } else {
                ViewUtils.showError(errorMessage);
            }
        }
    }

    @FXML
    /**
     * <h2>disapproveButtonClicked</h2>
     * This method is called whenever the "disapprove" button is clicked on the assignment page.
     *
     * @param event This is the action of the "disapprove" button being clicked.
     * @author Jason Shao
     */
    public void disapproveButtonClicked(ActionEvent event) {
        String orderIdString = assignOrderIDTextField.getText();
        String note = noteDescriptionTextField.getText();
        String dateString = noteDateTextField.getText();

        if (orderIdString == null || orderIdString.trim().isEmpty()) {
            ViewUtils.showError("The order number cannot be empty");
            return;
        }
        if (note == null || note.trim().isEmpty()) {
            ViewUtils.showError("The order note cannot be empty");
            return;
        }
        if (dateString == null || dateString.trim().isEmpty()) {
            ViewUtils.showError("The date cannot be empty");
        } else {
            int orderId = Integer.parseInt(orderIdString);
            Date date = Date.valueOf(dateString);
            String errorMessage = OrderController.disapproveShipmentOrder(wflow.getOrder(orderId), note, date);
            if (errorMessage.isEmpty()) {
                assignOrderIDTextField.setText("");
                noteDescriptionTextField.setText("");
                noteDateTextField.setText("");
            } else {
                ViewUtils.showError(errorMessage);
            }
        }
    }
}

