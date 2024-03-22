package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Employee;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.model.WarehouseStaff;

import java.util.List;

public class OrderController {


    private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * This method is called to assign a WareFlow staff member to a shipment Order.
     *
     * @param orderId          Id of an existing shipment order.
     * @param employeeUsername Username of the staff member that is being assigned to the order.
     * @param timeEstimate     TimeEstimate of the order.
     * @param priorityLevel    PriorityLevel of the order.
     * @param requiresApproval boolean value indicating if the order requires approval from Manager before closing.
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the assignment of a staff member to a ticket was successful.
     * @author Al-Faysal Haidar
     */
    public static String assign(int orderId, String employeeUsername, TimeEstimate timeEstimate, PriorityLevel priorityLevel, Boolean requiresApproval) {
        String errorMessage = "";
        if (!wareFlow.hasOrders()) {
            errorMessage += "There are no orders in the system";
            return errorMessage;
        }
        if (!wareFlow.hasEmployees()) {
            errorMessage += "There are no employees in the system";
            return errorMessage;
        }
        if (!wareFlow.hasManager()) {
            errorMessage += "There is no manager in the system";
            return errorMessage;
        }

        List<Employee> employees = wareFlow.getEmployees();
        List<ShipmentOrder> shipmentOrders = wareFlow.getOrders();
        ShipmentOrder rightOrder = null;
        boolean foundOrder = false;
        for (ShipmentOrder shipmentOrder : shipmentOrders) {
            if (shipmentOrder.getId() == orderId) {
                rightOrder = shipmentOrder;
                foundOrder = true;
                break;
            }
        }

        if (!foundOrder) {
            errorMessage += "Shipment order does not exist";
            return errorMessage;
        }
        if (rightOrder.getTicketStatus().equals(ShipmentOrder.TicketStatus.Assigned)) {
            errorMessage += "The ticket order is already assigned";
        } else if (rightOrder.getTicketStatus().equals(ShipmentOrder.TicketStatus.Completed)) {
            errorMessage += "The ticket order is already completed";
        } else if (rightOrder.getTicketStatus().equals(ShipmentOrder.TicketStatus.Closed)) {
            errorMessage += "The ticket order is already closed";
        } else if (rightOrder.getTicketStatus().equals(ShipmentOrder.TicketStatus.InProgress)) {
            errorMessage += "The ticket order is already in progress";
        }
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }
        if (wareFlow.getManager().getUsername().equals(employeeUsername)) {
            WarehouseStaff manager = wareFlow.getManager();
            try {
                rightOrder.setOrderApprover(wareFlow.getManager());
                rightOrder.assign(manager, priorityLevel, timeEstimate, requiresApproval);
            } catch (Exception e) {
                errorMessage += "Unknown exception";
            }
            return errorMessage;
        }
        WarehouseStaff rightEmployee = null;
        boolean foundEmployee = false;
        for (Employee employee : employees) {
            if (employee.getUsername().equals(employeeUsername)) {
                rightEmployee = employee;
                foundEmployee = true;
                break;
            }
        }
        if (!foundEmployee) {
            errorMessage += "Employee to assign does not exist";
            return errorMessage;
        }
        try {
            rightOrder.setOrderApprover(wareFlow.getManager());
            rightOrder.assign(rightEmployee, priorityLevel, timeEstimate, requiresApproval);
        } catch (Exception e) {
            errorMessage += "Unknown error";
        }
        return errorMessage;
    }

    /**
     * This method is called to start the work on a shipment order.  This method will be called by the staff member assigned to the order.
     *
     * @param startOrder ShipmentOrder object that needs to be worked on.
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the assignment of a staff member to a ticket was successful.
     * @author Neeshal Imrit
     */
    public static String startShipmentOrder(ShipmentOrder startOrder) {
        String errorMessage = "";

        if (startOrder == null || !ShipmentOrder.hasWithId(startOrder.getId())) {
            errorMessage += "shipment order does not exist.";
        } else if (startOrder.getTicketStatusFullName().equalsIgnoreCase("open")) {
            errorMessage += "Cannot start a shipment order which is open.";
        } else if (startOrder.getTicketStatusFullName().equalsIgnoreCase("completed")) {
            errorMessage += "Cannot start a shipment order which is completed.";
        } else if (startOrder.getTicketStatusFullName().equalsIgnoreCase("closed")) {
            errorMessage += "Cannot start a shipment order which is closed.";
        } else if (startOrder.getTicketStatusFullName().equalsIgnoreCase("InProgress")) {
            errorMessage += "The shipment order is already in progress.";
        }

        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        try {
            startOrder.startWork();
        } catch (Exception e) {
            errorMessage += "Other exception";
        }

        return errorMessage;
    }


    /**
     * This method is called to complete the work on a shipment order.  This method will be called by the staff member assigned to the order.
     *
     * @param completeOrder ShipmentOrder object that needs to be completed.
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the assignment of a staff member to a ticket was successful.
     * @author Ben Bouhdana
     */
    public static String completeShipmentOrder(ShipmentOrder completeOrder) {
        String errorMessage = "";

        if (completeOrder == null) {
            errorMessage += "Shipment order does not exist.";
        } else if (!ShipmentOrder.hasWithId(completeOrder.getId())) {
            errorMessage += "Shipment order does not exist.";
        } else if (completeOrder.getTicketStatusFullName().equalsIgnoreCase("open")) {
            errorMessage += "Cannot complete a shipment order which is open.";
        } else if (completeOrder.getTicketStatusFullName().equalsIgnoreCase("assigned")) {
            errorMessage += "Cannot complete a shipment order which is assigned.";
        } else if (completeOrder.getTicketStatusFullName().equalsIgnoreCase("closed")) {
            errorMessage += "The shipment order is already closed.";
        } else if (completeOrder.getTicketStatusFullName().equalsIgnoreCase("resolved")) {
            errorMessage += "The shipment order is already resolved.";
        }

        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        try {
            completeOrder.markAsResolved();
        } catch (Exception e) {
            errorMessage += "Other exception";
        }

        return errorMessage;
    }


    /**
     * This method is called to approve the work on a shipment order.  This method will be called by a manager to approve an order.
     *
     * @param approveOrder ShipmentOrder object that needs to be approved.
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the assignment of a staff member to a ticket was successful.
     * @author Jason Shao
     */
    public static String approveShipmentOrder(ShipmentOrder approveOrder) {
        return "";
    }

    /**
     * This method is called to disapprove the work on a shipment order.  This method will be called by a manager to disapprove an order.
     *
     * @param dissaproveOrder ShipmentOrder object that needs to be dissaproved.
     * @param reason          String containing the reason for disapproval.
     * @param date            Date of disapproval.
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the assignment of a staff member to a ticket was successful.
     * @author Jason Shao
     */

    public static String disapproveShipmentOrder(ShipmentOrder dissaproveOrder, String reason, String date) {
        return "";
    }


}
