package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.ItemContainer;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.WareFlow;

import java.sql.Date;

public class ItemContainerController {

    private static WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * @param containerNumber the container number of the item in the container (cannot be less than 1).
     * @param areaNumber      the area number of the item in the container (cannot be less than 0).
     * @param slotNumber      the slot number of the item in the container (cannot be less than 0).
     * @param addedOnDate     the added-on date of the item in the container.
     * @param itemTypeName    the item type name.
     * @return Returns an empty string if the item was added successfully and no errors
     * were raised. If there are errors, an error message is returned.
     * @author Al-Faysal Haidar
     * This method is used to add an item in a container in WareFlow.
     */
    public static String addItemContainer(int containerNumber, int areaNumber, int slotNumber,
                                          Date addedOnDate, String itemTypeName) {
        String errorMessage = "";

        if (areaNumber < 0) {
            errorMessage += "The area number shall not be less than 0";
        } else if (slotNumber < 0) {
            errorMessage += "The slot number shall not be less than 0";
        } else if (containerNumber < 1) {
            errorMessage += "The container number shall not be less than 1";
        } else if (!ItemType.hasWithName(itemTypeName)) {
            errorMessage += "The item type does not exist";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            wareFlow.addItemContainer(containerNumber, areaNumber, slotNumber, addedOnDate, ItemType.getWithName(itemTypeName));
        } catch (Exception e) {
            return errorMessage;
        }

        return errorMessage;
    }

    /**
     * @param containerNumber the container number of the item in the container (cannot be less than 1).
     * @param newAreaNumber   the area number of the item in the container to be updated (cannot be less than 0).
     * @param newSlotNumber   the slot number of the item in the container to be updated (cannot be less than 0).
     * @param newAddedOnDate  the added-on date of the item in the container to be updated.
     * @param newItemTypeName the item type name to be updated.
     * @return Returns an empty string if the item was updated successfully and no errors
     * were raised. If there are errors, an error message is returned.
     * @author Al-Faysal Haidar
     * This method is used to update an item in a container in WareFlow.
     */
    public static String updateItemContainer(int containerNumber, int newAreaNumber,
                                             int newSlotNumber, Date newAddedOnDate, String newItemTypeName) {
        String errorMessage = "";

        if (newAreaNumber < 0) {
            errorMessage += "The area number shall not be less than 0";
        } else if (newSlotNumber < 0) {
            errorMessage += "The slot number shall not be less than 0";
        } else if (containerNumber < 1) {
            errorMessage += "The container number shall not be less than 1";
        } else if (!ItemType.hasWithName(newItemTypeName) || !ItemContainer.hasWithContainerNumber(containerNumber)) {
            errorMessage += "The item type does not exist";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ItemContainer itemInContainer = ItemContainer.getWithContainerNumber(containerNumber);
            itemInContainer.setAreaNumber(newAreaNumber);
            itemInContainer.setSlotNumber(newSlotNumber);
            itemInContainer.setItemType(ItemType.getWithName(newItemTypeName));
            itemInContainer.setAddedOnDate(newAddedOnDate);
        } catch (Exception e) {
            return errorMessage;
        }

        return errorMessage;
    }

    /**
     * @param containerNumber the container number of the item in the container to be deleted (cannot be less than 1).
     * @author Al-Faysal Haidar
     * This method is used to delete an item in a container in WareFlow.
     */
    public static void deleteItemContainer(int containerNumber) {
        if (ItemContainer.hasWithContainerNumber(containerNumber)) {
            try {
                ItemContainer.getWithContainerNumber(containerNumber).delete();
            } catch (Exception ignored) {
            }
        }
    }
}