package ca.mcgill.ecse.wareflow.controller;

import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;
import java.util.ArrayList;

public class ShipmentOrderController {
  // containerNumber -1 means that no container is specified and quantity has to be -1 as well
  public static String addShipmentOrder(int id, Date placedOnDate, String description,
      String username, int containerNumber, int quantity) {
    // Remove this exception when you implement this method
      throw new UnsupportedOperationException("Not Implemented!");
  }

  // newContainerNumber -1 means that no container is specified and newQuantity has to be -1 as well
  public static String updateShipmentOrder(int id, Date newPlacedOnDate, String newDescription,
      String newUsername, int newContainerNumber, int newQuantity) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static void deleteShipmentOrder(int id) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }


  /**
   * @author Neeshal Imrit
   * The method is used to get all shipment orders.
   * @return Returns a list of TOShipmentOrder objects representing all ShipmentOrders
   * in the WareFlow System.
   */
  public static List<TOShipmentOrder> getOrders() {
    WareFlow wareFlow = WareFlowApplication.getWareFlow();
    List<TOShipmentOrder> shipmentOrders = new ArrayList<TOShipmentOrder>();
    if (wareFlow.hasOrders()){

      
      List<ShipmentOrder> orders = wareFlow.getOrders();


      // If there are no orders, return an empty list
      if (orders.isEmpty()){
        return shipmentOrders;
      }

      for (ShipmentOrder order : orders){

        // Initialize the variables to be used in the TOShipmentOrder constructor.
        int id = order.getId();
        int quantity = order.getQuantity();
        Date placedOnDate = order.getPlacedOnDate();
        String description = order.getDescription();
        String orderPlacer = order.getOrderPlacer().getUsername();
        String itemName = order.getContainer().getItemType().getName();
        int expectedLifeSpanInDays = order.getContainer().getItemType().getExpectedLifeSpanInDays();
        Date addedOnDate = order.getContainer().getAddedOnDate();
        int areaNumber = order.getContainer().getAreaNumber();
        int slotNumber = order.getContainer().getSlotNumber();

        // Generate ToShipmentNote objects for each note in the shipment order
        List<TOShipmentNote> notes = new ArrayList<TOShipmentNote>();
        List<ShipmentNote> shipmentNotes = order.getShipmentNotes();
        
        if (shipmentNotes != null){
          for (ShipmentNote note : shipmentNotes){
            Date date = note.getDate();
            String noteTakerUsername = note.getNoteTaker().getUsername();
            String noteDescription = note.getDescription();
            TOShipmentNote shipmentNote = new TOShipmentNote(date, noteDescription, noteTakerUsername);
            notes.add(shipmentNote);
          }
        }

        // Create a new TOShipmentOrder object and add it to the list of shipment orders
        TOShipmentNote[] noteArray = new TOShipmentNote[notes.size()];
        TOShipmentOrder shipmentOrder = new TOShipmentOrder(id, quantity, placedOnDate, description, orderPlacer, itemName, expectedLifeSpanInDays, addedOnDate, areaNumber, slotNumber, noteArray);
        shipmentOrders.add(shipmentOrder);
      }
    }
    return shipmentOrders;
  }
}
