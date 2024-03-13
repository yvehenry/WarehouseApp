package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The ShipmentOrderController program is used
 * to control every action that has to do with the
 * creation, update or deletion of a Shipment Order in the WareFlow program.
 */
public class ShipmentOrderController {

    private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * @param id              This is the id number the user wants to give the new Shipment Order. Cannot exist in the system
     * @param placedOnDate    This is the Date the user attempts to add the new Shipment Order. Cannot be empty
     * @param description     This is the order description the user wants to add to the new Shipment Order. Cannot be empty
     * @param username        This is the username of the user adding a Shipment Order. Username must exist the system
     * @param containerNumber This the container number the user is assigning to a Shipment Order.
     *                        Cannot be less than 0 if quantity != to 0
     * @param quantity        This the quantity of items the user is assigning to a Shipment Order.
     *                        Cannot be 0 when the container number is less than 0
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the creation of a new asset type was successful.
     * @author Jordan Buchanan
     * This is the method called when a user attempts to add a new
     * Shipment Order to the WareFlow system
     */
    public static String addShipmentOrder(int id, Date placedOnDate, String description,
                                          String username, int containerNumber, int quantity) {
        String errorMessage = "";

        if (ShipmentOrder.hasWithId(id)) {
            errorMessage += "Order id already exists";
        }
        if (placedOnDate == null) {
            errorMessage += "Date cannot be empty";
        }
        if (description == null || description.isEmpty()) {
            errorMessage += "Order description cannot be empty";
        }
        if (!User.hasWithUsername(username)) {
            errorMessage += "The order placer does not exist";
        }
        if (containerNumber == -1 && quantity != -1) {
            errorMessage += "Order quantity must be -1 when container is not specified";
        }
        if (quantity < 0) {
            errorMessage += "Order quantity must be larger than 0 when container is specified";
        }
        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ShipmentOrder newOrder = new ShipmentOrder(id, placedOnDate, description, quantity, wareFlow, User.getWithUsername(username));
            newOrder.setContainer(ItemContainer.getWithContainerNumber(containerNumber));
        } catch (Exception e) {
            return e.getMessage();
        }
        return errorMessage;
    }

    /**
     * @param id                 This is the id number of the Shipment Order. Must exist in the system
     * @param newPlacedOnDate    This is the updated date for the Shipment Order. Cannot be empty
     * @param newDescription     This is the updated description for the Shipment Order. Cannot be empty
     * @param newUsername        This is the updated user for the Shipment Order. User must exist in the system
     * @param newContainerNumber This is the updated container number for the Shipment Order.
     *                           Cannot be less than 0 if quantity != to 0
     * @param newQuantity        This the updated quantity of items for the Shipment Order.
     *                           Cannot be 0 when the container number is less than 0
     * @return String - Returns a string containing an error message gathered during execution,
     * if this returned string is empty, the creation of a new asset type was successful.
     * @author Jordan Buchanan
     * This is the method called whenever a manager attempts
     * to update the date, description, user, container number and quantity
     * of a specific shipment order in the WareFlow system.
     */
    public static String updateShipmentOrder(int id, Date newPlacedOnDate, String newDescription,
                                             String newUsername, int newContainerNumber, int newQuantity) {
        String errorMessage = "";

        if (!ShipmentOrder.hasWithId(id)) {
            errorMessage += "The container does not exist";
        }
        if (newPlacedOnDate == null) {
            errorMessage += "Date cannot be empty";
        }
        if (newDescription.isEmpty()) {
            errorMessage += "Order description cannot be empty";
        }
        if (!User.hasWithUsername(newUsername)) {
            errorMessage += "The order placer does not exist";
        }
        if (newContainerNumber == -1 && newQuantity != -1) {
            errorMessage += "Order quantity must be -1 when container is not specified";
        }
        if (newQuantity < 0) {
            errorMessage += "Order quantity must be larger than 0 when container is specified";
        }

        if (!errorMessage.equalsIgnoreCase("")) {
            return errorMessage;
        }

        try {
            ShipmentOrder someShipmentOrder = ShipmentOrder.getWithId(id);
            someShipmentOrder.setPlacedOnDate(newPlacedOnDate);
            someShipmentOrder.setDescription(newDescription);
            someShipmentOrder.setOrderPlacer(User.getWithUsername(newUsername));

            someShipmentOrder.setContainer(ItemContainer.getWithContainerNumber(newContainerNumber));
            someShipmentOrder.setQuantity(newQuantity);

        } catch (Exception e) {
            return e.getMessage();
        }
        return errorMessage;
    }

    /**
     * This is the method called whenever a manager attempts to delete a specified Shipment Order in the WareFlow program.
     *
     * @param id This is the order id of the Shipment Order that is to be deleted. Must exist in the system
     * @author Jordan Buchanan
     */
    public static void deleteShipmentOrder(int id) {
        if (ShipmentOrder.hasWithId(id)) {
            try {
                ShipmentOrder.getWithId(id).delete();
            } catch (RuntimeException ignored) {
            }
        }
    }


    /**
     * @return Returns a list of TOShipmentOrder objects representing all ShipmentOrders in the WareFlow System.
     * @author Neeshal Imrit
     * The method is used to get all shipment orders.
     */
    public static List<TOShipmentOrder> getOrders() {
        final WareFlow wareFlow = WareFlowApplication.getWareFlow();
        List<TOShipmentOrder> shipmentOrders = new ArrayList<>();
        if (wareFlow.hasOrders()) {


            List<ShipmentOrder> orders = wareFlow.getOrders();


            // If there are no orders, return an empty list
            if (orders.isEmpty()) {
                return shipmentOrders;
            }

            for (ShipmentOrder order : orders) {

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
                List<TOShipmentNote> notes = new ArrayList<>();
                List<ShipmentNote> shipmentNotes = order.getShipmentNotes();

                if (shipmentNotes != null) {
                    for (ShipmentNote note : shipmentNotes) {
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
