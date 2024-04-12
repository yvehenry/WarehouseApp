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
    private TextField oldItemName;
    @FXML
    private TextField newItemName;
    @FXML
    private TextField newItemLifespan;
    @FXML
    private Button updateItemButton;
    

    @FXML
    private TextField deleteItemName;
    @FXML
    private Button deleteItemButton;


    @FXML
    public void addItemTypeClicked(ActionEvent event) {
        String itemName = addItemName.getText();
        int lifespan = Integer.parseInt(addItemLifespan.getText());
        

        if (itemName == null || itemName.trim().isEmpty()){
            ViewUtils.showError("The new item type field cannot be empty");
          } else {
            String errorMessage = ca.mcgill.ecse.wareflow.controller.ItemTypeController.addItemType(itemName, lifespan);
            if (errorMessage.isEmpty()){
              ViewUtils.makePopupWindow("Result", "Item type: "+ itemName + " added successfully!");
              addItemName.setText("");
              addItemLifespan.setText("");
            } else {
              ViewUtils.showError(errorMessage);
            }
            
          }
          WareFlowFxmlView.getInstance().refresh();
    }

    @FXML
    public void updateItemTypeClicked(ActionEvent event) {
        String oldName = oldItemName.getText();
        String newName = newItemName.getText();
        String newLifespan = newItemLifespan.getText();
        if (oldName== null || oldName.trim().isEmpty()){
            ViewUtils.showError("The Old Item Type field cannot be empty");
          } else if (newName  == null || newName .trim().isEmpty()){
            ViewUtils.showError("The New Item Type field cannot be empty");
          } else if (newLifespan== null || newLifespan.trim().isEmpty()){
            ViewUtils.showError("The Expected Lifespan field cannot be empty");
          } else {
            int expectedLifeSpanInDays = Integer.parseInt(newLifespan);
            String errorMessage = ca.mcgill.ecse.wareflow.controller.ItemTypeController.updateItemType(oldName, newName, expectedLifeSpanInDays);
            if (errorMessage.isEmpty()){
                ViewUtils.makePopupWindow("Result", "Item type: "+ oldName + " updated successfully!");
                oldItemName.setText(""); 
                newItemName.setText("");
                newItemLifespan.setText("");
              WareFlowFxmlView.getInstance().refresh();
            } else {
              ViewUtils.showError(errorMessage);
            }
          }
    }

    @FXML
    public void deleteItemTypeClicked(ActionEvent event) {
        String itemTypeToDelete = deleteItemName.getText();
        if (itemTypeToDelete  == null || itemTypeToDelete .trim().isEmpty()){
            ViewUtils.showError("The Old Item Type field cannot be empty");
          } else {
            List<String> itemTypes = ca.mcgill.ecse.wareflow.controller.ItemTypeController.getItemTypesAsList();
            for (String itemType : itemTypes ){
              if (itemType.equalsIgnoreCase(itemTypeToDelete )){
                ca.mcgill.ecse.wareflow.controller.ItemTypeController.deleteItemType(itemTypeToDelete);
                deleteItemName.setText("");
                WareFlowFxmlView.getInstance().refresh();
                ViewUtils.makePopupWindow("Result", "Item type: "+ itemTypeToDelete + " deleted successfully!");
                return;
              }
            }
            ViewUtils.showError("The item type you tried to delete does not exist");
          }
        

    }
}
