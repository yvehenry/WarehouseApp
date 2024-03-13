package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.WareFlow;

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
            errorMessage += "The Item must have a name.";
        }

        if (ItemType.hasWithName(name)) {
            errorMessage += "This Item Type already exists in the system.";
        }

        if (expectedLifeSpanInDays <= 0) {
            errorMessage += "The lifespan of the item must be greater than 0 days.";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ItemType newItemType = new ItemType(name, expectedLifeSpanInDays, wareFlow);
            wareFlow.addItemType(newItemType);
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
            errorMessage += "The Item type must have a name.";
        }

        if (!ItemType.hasWithName(oldName)) {
            errorMessage += "This old Item type does not exist.";
        }

        if (ItemType.hasWithName(newName) && !(newName.equals(oldName))) {
            errorMessage += "The new Item type already exists";
        }

        if (newExpectedLifeSpanInDays <= 0) {
            errorMessage += "The lifespan of the item must be greater than 0 days.";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ItemType anItemType = ItemType.getWithName(oldName);
            anItemType.setName(newName);
            anItemType.setExpectedLifeSpanInDays(newExpectedLifeSpanInDays);
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
        } catch (RuntimeException ignored) {
        }
    }
}