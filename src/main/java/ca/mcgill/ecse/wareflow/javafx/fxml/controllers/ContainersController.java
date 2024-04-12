package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.controller.ItemTypeController;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;

public class ContainersController {

    @FXML
    private TextField addContainerNumtextfield;

    @FXML
    private TextField addAreaNumtextfield;

    @FXML
    private TextField addSlotNumtextfield;

    @FXML
    private TextField addDateAddedtextfield;

    @FXML
    private TextField updateContainerNumtextfield;

    @FXML
    private TextField updateAreaNumtextfield;

    @FXML
    private TextField updateSlotNumtextfield;

    @FXML
    private TextField updateDateAddedtextfield;

    @FXML
    private TextField deleteContainerNumtextfield;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button refreshButton;

    @FXML
    private ChoiceBox<String> addItemTypechoicebox;

    @FXML
    private ChoiceBox<String> updateItemTypechoicebox;

    @FXML
    private Label errorLabel;

    int containernum;
    int areanum;
    int slotnum;
    Date addedondate;
    String itemtype;

    public void initialize() {
        addItemTypechoicebox.addEventHandler(WareFlowFxmlView.REFRESH_EVENT, e -> {
            addItemTypechoicebox.setItems(ItemTypeController.getItemTypes());
            addItemTypechoicebox.setValue(null);
        });

        updateItemTypechoicebox.addEventHandler(WareFlowFxmlView.REFRESH_EVENT, e -> {
            updateItemTypechoicebox.setItems(ItemTypeController.getItemTypes());
            updateItemTypechoicebox.setValue(null);
        });

        WareFlowFxmlView.getInstance().registerRefreshEvent(addItemTypechoicebox);
        WareFlowFxmlView.getInstance().registerRefreshEvent(updateItemTypechoicebox);

    }

    @FXML
    public void addcontainer(ActionEvent event) {
        try {
            containernum = Integer.parseInt(addContainerNumtextfield.getText());
            areanum = Integer.parseInt(addAreaNumtextfield.getText());
            slotnum = Integer.parseInt(addSlotNumtextfield.getText());
            addedondate = Date.valueOf(addDateAddedtextfield.getText());
            itemtype = addItemTypechoicebox.getValue();

            String message = ca.mcgill.ecse.wareflow.controller.ItemContainerController.addItemContainer(containernum, areanum, slotnum, addedondate, itemtype);
            errorLabel.setText(message);

        } catch (NumberFormatException e) {

        } catch (IllegalArgumentException e) {

        } catch (Exception e) {

        }


    }

    @FXML
    public void updatecontainer(ActionEvent event) {
        try {
            containernum = Integer.parseInt(updateContainerNumtextfield.getText());
            areanum = Integer.parseInt(updateAreaNumtextfield.getText());
            slotnum = Integer.parseInt(updateSlotNumtextfield.getText());
            addedondate = Date.valueOf(updateDateAddedtextfield.getText());
            itemtype = updateItemTypechoicebox.getValue();

            String message = ca.mcgill.ecse.wareflow.controller.ItemContainerController.addItemContainer(containernum, areanum, slotnum, addedondate, itemtype);
            errorLabel.setText(message);

        } catch (NumberFormatException e) {

        } catch (IllegalArgumentException e) {

        } catch (Exception e) {

        }
    }

    @FXML
    public void deletecontainer(ActionEvent event) {
        try {
            containernum = Integer.parseInt(deleteContainerNumtextfield.getText());

            ca.mcgill.ecse.wareflow.controller.ItemContainerController.deleteItemContainer(containernum);

        } catch (NumberFormatException e) {

        } catch (IllegalArgumentException e) {

        } catch (Exception e) {

        }

    }
}
