package ca.mcgill.ecse.wareflow.controller;

import java.sql.Date;
import java.util.List;

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

  // returns all shipment orders
  public static List<TOShipmentOrder> getOrders() {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }
}
