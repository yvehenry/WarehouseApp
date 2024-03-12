package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class ShipmentOrderController {
  private List<TOShipmentNote> orders;

  // containerNumber -1 means that no container is specified and quantity has to be -1 as well
  public static String addShipmentOrder(int id, Date placedOnDate, String description,
      String username, int containerNumber, int quantity) {
        String errorMessage = "";
        if (ShipmentOrder.hasWithId(id)) {
          errorMessage += "Order id already exists";
        }
        if (!ItemContainer.hasWithContainerNumber(containerNumber) && containerNumber != -1){
          errorMessage += "The asset does not exist";
        }
        if(description == null || description.isEmpty()){
          errorMessage += "Order description is empty";
        }
        if (!User.hasWithUsername(username)){
          errorMessage += "Username does not exist";
        }

        if (!errorMessage.isEmpty()) {
          return errorMessage;
        }
        try {
          ShipmentOrder newOrder = new ShipmentOrder(id, placedOnDate, description, quantity,WareFlowApplication.getWareFlow(), User.getWithUsername(username))
          if (containerNumber == -1){
            newOrder.setContainer(null);
          }
          else if (containerNumber > 0){
            newOrder.setContainer(ItemContainer.getWithContainerNumber(containerNumber));
          }
        }
        catch (Exception e){
          if (e.getMessage().contains("duplicate")){
            errorMessage += "Cannot create due to duplicate id";
          }
          if (e.getMessage().contains("wareFlow")){
            errorMessage += "Unable to create shipmentOrder due to wareFlow";
          }
          if (e.getMessage().contains("placedOrder")){
            errorMessage += "Unable to create placedOrder due to orderPlacer";
          }
        }
        return errorMessage;
  }

  // newContainerNumber -1 means that no container is specified and newQuantity has to be -1 as well
  public static String updateShipmentOrder(int id, Date newPlacedOnDate, String newDescription,
      String newUsername, int newContainerNumber, int newQuantity) {
        String errorMessage = "";
        if (!WarehouseStaff.hasWithUsername(newUsername)){
          errorMessage += "The order placer does not exist";
        }
        if (newDescription == null || newDescription.isEmpty()) {
          errorMessage += "Order description cannot be empty";
        }
        if (!ItemContainer.hasWithContainerNumber(newContainerNumber) && newContainerNumber != -1){
          errorMessage += "The asset does not exist";
        }

        if (!errorMessage.isEmpty()) {
          return errorMessage;
        }
        try{
          ShipmentOrder orderToChange = ShipmentOrder.getWithId(id);
          orderToChange.setDescription(newDescription);
          orderToChange.setPlacedOnDate(newPlacedOnDate);
          orderToChange.setOrderPlacer(User.getWithUsername(newUsername));
          if (newContainerNumber == -1) {
            orderToChange.setContainer(null)
          }
          else if (newContainerNumber > 0) {
            orderToChange.setContainer(ItemContainer.getWithContainerNumber(newContainerNumber));
          }
        }
        catch (RuntimeException e){
          return e.getMessage();
        }

        return errorMessage;
  }

  public static void deleteShipmentOrder(int id) {
    try {
      if (ShipmentOrder.hasWithId(id)) {
          ShipmentOrder.getWithId(id).delete(); 
      }
    }
    catch (RuntimeException e) {}
  }

  // returns all shipment orders
  public static List<TOShipmentOrder> getOrders() {
    Map<Integer, ShipmentOrder> shipmentordersmap = ShipmentOrder.shipmentordersById;
    //shipment order is not public ???
  }
}
