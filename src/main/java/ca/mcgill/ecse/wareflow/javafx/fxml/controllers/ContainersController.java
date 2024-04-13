package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;

import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.ItemTypeController;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.List;

public class ContainersController {
	private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();
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
        String message = "";
		try {
            if (addContainerNumtextfield.getText() == null || addContainerNumtextfield.getText().trim().isEmpty()){
                message += "The container number field in the container to add cannot be empty.\n";
            }
			if (addAreaNumtextfield.getText() == null || addAreaNumtextfield.getText().trim().isEmpty()){
                message += "The area number field in the container to add cannot be empty.\n";
            }
			if (addSlotNumtextfield.getText() == null || addSlotNumtextfield.getText().trim().isEmpty()){
                message += "The slot number field in the container to add cannot be empty.\n";
            }
			if (addDateAddedtextfield.getText() == null || addDateAddedtextfield.getText().trim().isEmpty()){
                message += "The date field in the container to add cannot be empty.\n";
            }
			if (addItemTypechoicebox.getValue() == null || addItemTypechoicebox.getValue().trim().isEmpty()){
                message += "An item type in the container to add needs to be selected.\n";
            }
			
			if (!message.isEmpty())
				ViewUtils.showError(message);
			
			else {
				containernum = Integer.parseInt(addContainerNumtextfield.getText());
				areanum = Integer.parseInt(addAreaNumtextfield.getText());
				slotnum = Integer.parseInt(addSlotNumtextfield.getText());
				addedondate = Date.valueOf(addDateAddedtextfield.getText());
				itemtype = addItemTypechoicebox.getValue();

				message = ca.mcgill.ecse.wareflow.controller.ItemContainerController.addItemContainer(containernum, areanum, slotnum, addedondate, itemtype);
				errorLabel.setText(message);
				if (!message.isEmpty())
					ViewUtils.showError(message);
				else {
					ViewUtils.makePopupWindow("Result", "Container: "+ containernum + " updated successfully!");
					addContainerNumtextfield.setText("");
					addAreaNumtextfield.setText("");
					addSlotNumtextfield.setText("");
					addDateAddedtextfield.setText("");
					addItemTypechoicebox.setValue("");
					WareFlowFxmlView.getInstance().refresh();
				}
			}

        } 
		catch (NumberFormatException e) {
			message = "Please make sure the container number, the area number, and the slot number consist of only numbers (from 0 to 9).";
			ViewUtils.showError(message);
        } 
		catch (IllegalArgumentException e) {
			message = "Please enter a valid date.";
			ViewUtils.showError(message);
        } 
		catch (Exception e) {
			ViewUtils.showError(e.getMessage());
        }


    }

    @FXML
    public void updatecontainer(ActionEvent event) {
        String message = "";
		try {
			if (updateContainerNumtextfield.getText() == null || updateContainerNumtextfield.getText().trim().isEmpty()){
                message += "The container number field in the container to update cannot be empty.\n";
            }
			if (updateAreaNumtextfield.getText() == null || updateAreaNumtextfield.getText().trim().isEmpty()){
                message += "The area number field in the container to update cannot be empty.\n";
            }
			if (updateSlotNumtextfield.getText() == null || updateSlotNumtextfield.getText().trim().isEmpty()){
                message += "The slot number field in the container to update cannot be empty.\n";
            }
			if (updateDateAddedtextfield.getText() == null || updateDateAddedtextfield.getText().trim().isEmpty()){
                message += "The date field in the container to update cannot be empty.\n";
            }
			if (updateItemTypechoicebox.getValue() == null || updateItemTypechoicebox.getValue().trim().isEmpty()){
                message += "An item type in the container to update needs to be selected.\n";
            }
			
			if (!message.isEmpty())
				ViewUtils.showError(message);
			else {
				containernum = Integer.parseInt(updateContainerNumtextfield.getText());
            	areanum = Integer.parseInt(updateAreaNumtextfield.getText());
				slotnum = Integer.parseInt(updateSlotNumtextfield.getText());
				addedondate = Date.valueOf(updateDateAddedtextfield.getText());
				itemtype = updateItemTypechoicebox.getValue();

				message = ca.mcgill.ecse.wareflow.controller.ItemContainerController.addItemContainer(containernum, areanum, slotnum, addedondate, itemtype);
				if (!message.isEmpty())
					ViewUtils.showError(message);
				else {
					ViewUtils.makePopupWindow("Result", "Container: "+ containernum + " updated successfully!");
					updateContainerNumtextfield.setText("");
					updateAreaNumtextfield.setText("");
					updateSlotNumtextfield.setText("");
					updateDateAddedtextfield.setText("");
					updateItemTypechoicebox.setValue("");
					WareFlowFxmlView.getInstance().refresh();
				}
			}

        }
		catch (NumberFormatException e) {
			message = "Please make sure the container number, the area number, and the slot number consist of only numbers (from 0 to 9).";
			ViewUtils.showError(message);
        } 
		catch (IllegalArgumentException e) {
			message = "Please enter a valid date.";
			ViewUtils.showError(message);
        } 
		catch (Exception e) {
			ViewUtils.showError(e.getMessage());
        }
    }

    @FXML
    public void deletecontainer(ActionEvent event) {
        String message = "";
		try {
			if (deleteContainerNumtextfield.getText() == null || deleteContainerNumtextfield.getText().trim().isEmpty()){
                message += "The container number field in the container to delete cannot be empty.\n";
            }
			if (!message.isEmpty())
				ViewUtils.showError(message);
			else {
				containernum = Integer.parseInt(deleteContainerNumtextfield.getText());
				List<Integer> containers = wareFlow.getItemContainers().stream().map(ItemContainer::getContainerNumber).toList();
				for (Integer container : containers) {
					if (container.equals(containernum)) {
						ca.mcgill.ecse.wareflow.controller.ItemContainerController.deleteItemContainer(containernum);
						deleteContainerNumtextfield.setText("");
						WareFlowFxmlView.getInstance().refresh();
						ViewUtils.makePopupWindow("Result", "Container: "+ containernum + " deleted successfully!");
						return;
					  }
					}
				ViewUtils.showError("The container you tried to delete does not exist.");
			}

        } 
		catch (NumberFormatException e) {
			message = "Please make sure the container number consists of only numbers (from 0 to 9).";
			ViewUtils.showError(message);
        } 
		catch (Exception e) {
			ViewUtils.showError(e.getMessage());
        }

    }
}
