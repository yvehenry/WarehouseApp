package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import java.sql.Date;
import java.util.List;
import java.util.Map;;

/**
 * @author Jason Shao
 * This method is used to add, update, and delete the shipmentnotes
 * @param date The date that the shipmentnote is created
 * @param description The description of the shipment note
 * @param orderID The ID of the shipment order that the shipmentnote is attached to
 * @param username The username of the WarehouseStaff that created the shipment note
 * @return Returns a string that is empty if addShipmentNote was successful and no errors were raised. Returns a string with an error message otherwise.
 */
public class ShipmentNoteController {
  public static String addShipmentNote(Date date, String description, int orderID,
      String username) {
        String errorMessage = "";
        if (description == null || description.isEmpty()) {
          errorMessage += "Note description cannot be empty";
        }
        if (!WarehouseStaff.hasWithUsername(username)) {
          errorMessage += "The note placer does not exist";
        }
        if (!ShipmentOrder.hasWithId(orderID)){
          errorMessage += "The shipment order does not exist";
        }
        if (!errorMessage.isEmpty()) {
          return errorMessage;
        }
        try {
          ShipmentNote newNote = new ShipmentNote(date, description,ShipmentOrder.getWithId(orderID),(WarehouseStaff) User.getWithUsername(username));
          newNote.setOrder(ShipmentOrder.getWithId(orderID));

        }
        catch (RuntimeException e) {
          if (e.getMessage().contains("duplicate")){
            errorMessage += "Cannot create due to duplicate id";
          }
          if (e.getMessage().contains("wareFlow")){
            errorMessage += "Unable to create shipmentNote due to wareFlow";
          }
          if (e.getMessage().contains("takedNote")){
            errorMessage += "Unable to create takedNote due to noteTaker";
          }
        }
        return errorMessage;
  }

  /**
 * @author Jason Shao
 * This method is used to add, update, and delete the shipmentnotes
 * @param orderID The ID of the shipment order that the shipmentnote is attached to
 * @param index The index of the shipment note attached to the shipment order
 * @param newDate The newDate for the shipmentnote
 * @param newDescription The new description of the shipmentnote
 * @param newUsername The enew username of the WarehouseStaff for shipment note
 * @return Similar to addShipmentNote, it returns an empty string if the method is successful by updating the changes to the shipment note, otherwise it returns the error message in the form of a string.
 */
  public static String updateShipmentNote(int orderID, int index, Date newDate,
      String newDescription, String newUsername) {
        String errorMessage = "";
        if (!WarehouseStaff.hasWithUsername(newUsername)){
          errorMessage += "The note taker does not exist";
        }
        if (newDescription == null || newDescription.isEmpty()) {
          errorMessage += "Note description cannot be empty";
        }
        if (ShipmentOrder.hasWithId(orderID) && ShipmentOrder.getWithId(orderID).hasShipmentNotes()) {
          errorMessage += "The shipment order or notes does not exist";
        }
        if (!ShipmentOrder.hasWithId(orderID)){
          errorMessage += "The shipment order does not exist";
        }
        if (index < 0){
          errorMessage += "Note index does not exist";
        }
        if (!errorMessage.isEmpty()) {
          return errorMessage;
        }
        try {
          ShipmentNote noteToUpdate = ShipmentOrder.getWithId(orderID).getShipmentNote(index);
          noteToUpdate.setDate(newDate);
          noteToUpdate.setDescription(newDescription);
          noteToUpdate.setNoteTaker( (WarehouseStaff) User.getWithUsername(newUsername));

        }
        catch (RuntimeException e) {
          return e.getMessage();
        }
        return errorMessage;
  }

  /**
 * @author Jason Shao
 * This method is used to add, update, and delete the shipmentnotes
 * @param orderID The ID of the shipment order that the shipmentnote is attached to
 * @param index The index of the shipment note attached to the shipment order
 * @return The number of the order that is to be deleted If the id given does not have a corresponding ticket, then nothing happens.
 */
  public static void deleteShipmentNote(int orderID, int index) {
    try {
      if (ShipmentOrder.hasWithId(orderID) && ShipmentOrder.getWithId(orderID).hasShipmentNotes()) {
          ShipmentNote removeNote = ShipmentOrder.getWithId(orderID).getShipmentNote(index);
          ShipmentOrder.getWithId(orderID).removeShipmentNote(removeNote);
      }
    }
    catch (RuntimeException e) {}
  }
}
