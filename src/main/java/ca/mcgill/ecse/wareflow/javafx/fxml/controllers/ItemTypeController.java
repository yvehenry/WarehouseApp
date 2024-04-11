package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import java.sql.Date;
import ca.mcgill.ecse.wareflow.controller.*;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * Controller for ItemType Page
 * 
 * @author Yvehenry Julsain
 */
public class ItemTypeController {
    @FXML private TextField addItemName;
    @FXML private TextField addItemLifespan;
    @FXML private Button addItemButton;
    @FXML private Text addItemMessage;

    @FXML private TextField oldItemName;
    @FXML private TextField newItemName;
    @FXML private TextField newItemLifespan;
    @FXML private Button updateItemButton;
    @FXML private Text updateItemMessage;

    @FXML private TextField deleteItemName;
    @FXML private Button deleteItemButton;
    @FXML private Text deleteItemMessage;

    /*public void initialize() {
        
    }*/

    @FXML
	public void addItemType(ActionEvent event) {
        try {
            String itemName = addItemName.getText();
            int lifespan = Integer.parseInt(addItemLifespan.getText());
            String message = ca.mcgill.ecse.wareflow.controller.ItemTypeController.addItemType(itemName, lifespan);
            addItemMessage.setText(message);
            if (message.isEmpty()) {
                addItemName.setText("");
                addItemLifespan.setText("");
                addItemMessage.setText("Item Type sucessfully added! ");;
                WareFlowFxmlView.getInstance().refresh();
            }
        }
        catch (NumberFormatException e) {

        }
        catch (IllegalArgumentException e) {

        }
        catch (Exception e) {

        }

    }
    
    @FXML
	public void updateItemType(ActionEvent event) {
        try {
            String oldName = oldItemName.getText();
            String newName = newItemName.getText();
            int newLifespan = Integer.parseInt(newItemLifespan.getText());
            String message = ca.mcgill.ecse.wareflow.controller.ItemTypeController.updateItemType(oldName, newName, newLifespan);
            updateItemMessage.setText(message);
            if (message.isEmpty()) {
                oldItemName.setText("");
                newItemName.setText("");
                newItemLifespan.setText("");
                updateItemMessage.setText("Item Type has been sucessfully upddated! ");
                WareFlowFxmlView.getInstance().refresh();
            }
        }
        catch (NumberFormatException e) {

        }
        catch (IllegalArgumentException e) {

        }
        catch (Exception e) {

        }
    }

    @FXML
	public void deleteItemType(ActionEvent event) {
        try {
            String itemType = deleteItemName.getText();
            ca.mcgill.ecse.wareflow.controller.ItemTypeController.deleteItemType(itemType);
        }

        catch (NumberFormatException e) {

        }
        catch (IllegalArgumentException e) {

        }
        catch (Exception e) {

        }
    }
}
