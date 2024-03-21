package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;

public class OrderController {
    

private static WareFlow wareFlow = WareFlowApplication.getWareFlow();

  /**
  * This method is called to assign a WareFlow staff member to a shipment Order.
  * 
  * @param OrderId Id of an existing shipment order.
  * @param employeeUsername Username of the staff member that is being assigned to the order.
  * @param timeEstimate TimeEstimate of the order.
  * @param priorityLevel PriorityLevel of the order.
  * @param requiresApproval boolean value indicating if the order requires approval from Manager before closing.
  * @return String - Returns a string containing an error message gathered during execution, 
  *                   if this returned string is empty, the assignment of a staff member to a ticket was successful.
  *
  * @author Neeshal Imrit
  */
  public static String assign(int OrderId, String employeeUsername, TimeEstimate timeEstimate, PriorityLevel priorityLevel, Boolean requiresApproval){
    return "";
  }

  /**
  * This method is called to start the work on a shipment order.  This method will be called by the staff member assigned to the order.
  * 
  * @param startOrder ShipmentOrder object that needs to be worked on.
  * @return String - Returns a string containing an error message gathered during execution, 
  *                   if this returned string is empty, the assignment of a staff member to a ticket was successful.
  *
  * @author Neeshal Imrit
  */
  public static String startShipmentOrder(ShipmentOrder startOrder){
    return "";
  }


  /**
  * This method is called to complete the work on a shipment order.  This method will be called by the staff member assigned to the order.
  * 
  * @param completeOrder ShipmentOrder object that needs to be completed.
  * @return String - Returns a string containing an error message gathered during execution, 
  *                   if this returned string is empty, the assignment of a staff member to a ticket was successful.
  *
  * @author Ben Bouhdana
  */
  public static String completeShipmentOrder(ShipmentOrder completeOrder){
    return "";
  }


  /**
  * This method is called to approve the work on a shipment order.  This method will be called by a manager to approve an order.
  * 
  * @param approveOrder ShipmentOrder object that needs to be approved.
  * @return String - Returns a string containing an error message gathered during execution, 
  *                   if this returned string is empty, the assignment of a staff member to a ticket was successful.
  *
  * @author Jason Shao
  */
  public static String approveShipmentOrder(ShipmentOrder approveOrder){
    return "";
  }

  /**
  * This method is called to disapprove the work on a shipment order.  This method will be called by a manager to disapprove an order.
  * 
  * @param dissaproveOrder ShipmentOrder object that needs to be dissaproved.
  * @param reason String containing the reason for disapproval.
  * @param date Date of disapproval.
  * @return String - Returns a string containing an error message gathered during execution, 
  *                   if this returned string is empty, the assignment of a staff member to a ticket was successful.
  *
  * @author Jason Shao
  */

  public static String disapproveShipmentOrder(ShipmentOrder dissaproveOrder, String reason, String date){
    return "";
  }



}
