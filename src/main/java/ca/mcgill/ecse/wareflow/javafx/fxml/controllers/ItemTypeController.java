package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import ca.mcgill.ecse.wareflow.model.ItemType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;

/**
 * Controller for ItemType Page
 *
 * @author Yvehenry Julsain
 */
public class ItemTypeController {
    @FXML
    private TextField addItemName;
    @FXML
    private TextField addItemLifespan;
    @FXML
    private Button addItemButton;
    @FXML
    private Text addItemMessage;

    @FXML
    private TextField oldItemName;
    @FXML
    private TextField newItemName;
    @FXML
    private TextField newItemLifespan;
    @FXML
    private Button updateItemButton;
    @FXML
    private Text updateItemMessage;

    @FXML
    private TextField deleteItemName;
    @FXML
    private Button deleteItemButton;
    @FXML
    private Text deleteItemMessage;

    @FXML
    public void addItemType(ActionEvent event) {
        String itemName = addItemName.getText();
        int lifespan = Integer.parseInt(addItemLifespan.getText());
        String message = ca.mcgill.ecse.wareflow.controller.ItemTypeController.addItemType(itemName, lifespan);
        addItemMessage.setText(message);
        if (message.isEmpty()) {
            addItemName.setText("");
            addItemLifespan.setText("");
            addItemMessage.setText("Item Type successfully added! ");
            WareFlowFxmlView.getInstance().refresh();
        }
    }

    @FXML
    public void updateItemType(ActionEvent event) {
        String oldName = oldItemName.getText();
        String newName = newItemName.getText();
        int newLifespan = Integer.parseInt(newItemLifespan.getText());
        String message = ca.mcgill.ecse.wareflow.controller.ItemTypeController.updateItemType(oldName, newName, newLifespan);
        updateItemMessage.setText(message);
        if (message.isEmpty()) {
            oldItemName.setText("");
            newItemName.setText("");
            newItemLifespan.setText("");
            updateItemMessage.setText("Item Type has been successfully upddated! ");
            WareFlowFxmlView.getInstance().refresh();
        }

    }

    @FXML
    public void deleteItemType(ActionEvent event) {
        String itemTypeToDelete = deleteItemName.getText();
        if (itemTypeToDelete == null || itemTypeToDelete.trim().isEmpty())
            deleteItemMessage.setText("The Item Type field cannot be empty. ");

        else {
            List<ItemType> itemTypes = WareFlowApplication.getWareFlow().getItemTypes();
            for (ItemType itemType : itemTypes) {
                if (itemType.getName().equalsIgnoreCase(itemTypeToDelete)) {
                    ca.mcgill.ecse.wareflow.controller.ItemTypeController.deleteItemType(itemTypeToDelete);
                    deleteItemMessage.setText("The ItemType has successfully been deleted! ");
                    return;
                }
            }
            deleteItemMessage.setText("The Item Type does not exist in the WareFlow system. ");
        }

    }
}
