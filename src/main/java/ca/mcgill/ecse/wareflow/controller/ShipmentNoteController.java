package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import java.sql.Date;
import java.util.List;
import java.util.Map;;

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
          ShipmentNote newNote = new ShipmentNote(date, description,ShipmentOrder.getWithId(orderID),WarehouseStaff.getWithUsername(username));
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

  // index starts at 0
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
          noteToUpdate.setNoteTaker(User.getWithUsername(newUsername));

        }
        catch (RuntimeException e) {
          return e.getMessage();
        }
        return errorMessage;
  }

  // index starts at 0
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
