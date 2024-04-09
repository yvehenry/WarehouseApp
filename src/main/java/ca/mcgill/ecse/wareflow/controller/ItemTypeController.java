package ca.mcgill.ecse.wareflow.controller;

import java.util.List;


import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ItemTypeController {

    private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * @param name                   the name of the item type
     * @param expectedLifeSpanInDays the expected life span in days of the item type
     * @return returns an empty string if the item type was added successfully
     * with no raised errors
     * If there are errors, an error message is returned
     * @author Benjamin Bouhdana
     * This method is used to add an item type to the WareFlow application
     */
    public static String addItemType(String name, int expectedLifeSpanInDays) {
        String errorMessage = "";

        if (name.isEmpty()) {
            errorMessage += "The name must not be empty";
        }

        if (ItemType.hasWithName(name)) {
            errorMessage += "The item type already exists";
        }

        if (expectedLifeSpanInDays <= 0) {
            errorMessage += "The expected life span must be greater than 0 days";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ItemType newItemType = new ItemType(name, expectedLifeSpanInDays, wareFlow);
            wareFlow.addItemType(newItemType);
            WareFlowPersistence.save();
        } catch (Exception e) {
            return e.getMessage();
        }

        return errorMessage;
    }

    /**
     * @param oldName                   the old name of the item type
     * @param newName                   the new name of the item type
     * @param newExpectedLifeSpanInDays the expected life span in days of the updated item type
     * @return returns an empty string if the item type was updated successfully
     * with no raised errors
     * If there are errors, an error message is returned
     * @author Benjamin Bouhdana
     * This method is used to update an item type to the WareFlow application
     */
    public static String updateItemType(String oldName, String newName, int newExpectedLifeSpanInDays) {
        String errorMessage = "";

        if (newName.isEmpty()) {
            errorMessage += "The name must not be empty";
        }

        if (!ItemType.hasWithName(oldName)) {
            errorMessage += "The item type does not exist";
        }

        if (ItemType.hasWithName(newName) && !(newName.equals(oldName))) {
            errorMessage += "The item type already exists";
        }

        if (newExpectedLifeSpanInDays <= 0) {
            errorMessage += "The expected life span must be greater than 0 days";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ItemType anItemType = ItemType.getWithName(oldName);
            anItemType.setName(newName);
            anItemType.setExpectedLifeSpanInDays(newExpectedLifeSpanInDays);
            WareFlowPersistence.save();
        } catch (Exception e) {
            return e.getMessage();
        }

        return errorMessage;
    }

    /**
     * @param name the name of the item type to be deleted
     * @author Benjamin Bouhdana
     * This method is used to update an item type to the WareFlow application
     */
    public static void deleteItemType(String name) {
        try {
            if (ItemType.hasWithName(name)) {
                ItemType.getWithName(name).delete();
            }
            WareFlowPersistence.save();
        } catch (RuntimeException ignored) {
        }
    }
    
    public static ObservableList<String> getItemTypes() {
        List<String> itemTypeStrings = wareFlow.getItemTypes().stream().map(ItemType::getName).toList();
        return FXCollections.observableList(itemTypeStrings);
      }
    
    public static List<String> getItemTypesAsList() {
        List<String> itemTypeStrings = wareFlow.getItemTypes().stream().map(ItemType::getName).toList();
        return itemTypeStrings;
      }
}