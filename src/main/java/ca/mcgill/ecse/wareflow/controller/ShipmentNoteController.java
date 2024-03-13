package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.model.ShipmentNote;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.User;
import ca.mcgill.ecse.wareflow.model.WarehouseStaff;

import java.sql.Date;

public class ShipmentNoteController {

    /**
     * @param date        The date that the shipment note is created
     * @param description The description of the shipment note
     * @param orderID     The ID of the shipment order that the shipment note is attached to
     * @param username    The username of the WarehouseStaff that created the shipment note
     * @return Returns a string that is empty if addShipmentNote was successful and no errors were raised.
     * Returns a string with an error message otherwise.
     * @author Jason Shao
     * This method is used to add, update, and delete the shipmentnotes
     */
    public static String addShipmentNote(Date date, String description, int orderID,
                                         String username) {
        String errorMessage = "";
        if (description == null || description.isEmpty()) {
            errorMessage += "Note description cannot be empty";
        }
        if (!WarehouseStaff.hasWithUsername(username)) {
            errorMessage += "The note placer does not exist";
        }
        if (!ShipmentOrder.hasWithId(orderID)) {
            errorMessage += "The shipment order does not exist";
        }
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }
        try {
            ShipmentNote newNote = new ShipmentNote(date, description, ShipmentOrder.getWithId(orderID), (WarehouseStaff) User.getWithUsername(username));
            newNote.setOrder(ShipmentOrder.getWithId(orderID));

        } catch (RuntimeException e) {
            if (e.getMessage().contains("duplicate")) {
                errorMessage += "Cannot create due to duplicate id";
            }
            if (e.getMessage().contains("wareFlow")) {
                errorMessage += "Unable to create shipmentNote due to wareFlow";
            }
            if (e.getMessage().contains("takedNote")) {
                errorMessage += "Unable to create takedNote due to noteTaker";
            }
        }
        return errorMessage;
    }

    /**
     * @param orderID        The ID of the shipment order that the shipment note is attached to
     * @param index          The index of the shipment note attached to the shipment order
     * @param newDate        The newDate for the shipment note
     * @param newDescription The new description of the shipment note
     * @param newUsername    The new username of the WarehouseStaff for shipment note
     * @return Similar to addShipmentNote, it returns an empty string if the method is successful by updating the changes to the shipment note, otherwise it returns the error message in the form of a string.
     * @author Jason Shao
     * This method is used to add, update, and delete the shipmentnotes
     */
    public static String updateShipmentNote(int orderID, int index, Date newDate,
                                            String newDescription, String newUsername) {
        String errorMessage = "";
        if (!WarehouseStaff.hasWithUsername(newUsername)) {
            errorMessage += "The note taker does not exist";
        }
        if (newDescription == null || newDescription.isEmpty()) {
            errorMessage += "Note description cannot be empty";
        }
        if (ShipmentOrder.hasWithId(orderID) && ShipmentOrder.getWithId(orderID).hasShipmentNotes()) {
            errorMessage += "The shipment order or notes does not exist";
        }
        if (!ShipmentOrder.hasWithId(orderID)) {
            errorMessage += "The shipment order does not exist";
        }
        if (index < 0) {
            errorMessage += "Note index does not exist";
        }
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }
        try {
            ShipmentNote noteToUpdate = ShipmentOrder.getWithId(orderID).getShipmentNote(index);
            noteToUpdate.setDate(newDate);
            noteToUpdate.setDescription(newDescription);
            noteToUpdate.setNoteTaker((WarehouseStaff) User.getWithUsername(newUsername));

        } catch (RuntimeException e) {
            return e.getMessage();
        }
        return errorMessage;
    }

    /**
     * @param orderID The ID of the shipment order that the shipment note is attached to
     * @param index   The index of the shipment note attached to the shipment order
     *                This method is used to add, update, and delete the shipment notes
     * @author Jason Shao
     */
    public static void deleteShipmentNote(int orderID, int index) {
        try {
            if (ShipmentOrder.hasWithId(orderID) && ShipmentOrder.getWithId(orderID).hasShipmentNotes()) {
                ShipmentNote removeNote = ShipmentOrder.getWithId(orderID).getShipmentNote(index);
                ShipmentOrder.getWithId(orderID).removeShipmentNote(removeNote);
            }
        } catch (RuntimeException ignored) {
        }
    }
}
