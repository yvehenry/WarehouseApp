package ca.mcgill.ecse.wareflow.features;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.OrderController;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentNote;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TicketStatus;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShipmentOrderStepDefinitions {

    private List<TOShipmentOrder> orders;
    private final WareFlow wareFlow = WareFlowApplication.getWareFlow();


    /**
     * This step checks if the following employees exist in the system.
     *
     * @param dataTable This is a table containing usernames, names, passwords, and phone numbers associated to different employees.
     * @author Al-Faysal Haidar
     */
    @Given("the following employees exist in the system")
    public void the_following_employees_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String username = row.get("username");
            String password = row.get("password");
            String name = row.get("name");
            String phoneNumber = row.get("phoneNumber");
            new Employee(username, name, password, phoneNumber, wareFlow);
        }
    }

    /**
     * This step initializes the manager account with the information found in the dataTable.
     *
     * @param dataTable This table contains the ID and password of the manager to be initialized
     * @author Benjamin Bouhdana
     */
    @Given("the following manager exists in the system")
    public void the_following_manager_exists_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        for (var row : rows) {
            new Manager(row.get("username"), "", row.get("password"), "", wareFlow);
        }
    }

    /**
     * @param dataTable Cucumber DataTable containing the name and expectedLifeSpanInDays of the item
     *                  that exist in the system.
     * @author Jordan Buchanan
     */
    @Given("the following items exist in the system")
    public void the_following_items_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String name = row.get("name");
            if (row.get("expectedLifeSpanInDays") != null) {
                int expectedLifeSpanInDays = Integer.parseInt(row.get("expectedLifeSpanInDays"));
                new ItemType(name, expectedLifeSpanInDays, wareFlow);
            } else {
                new ItemType(name, -1, wareFlow);
            }
        }
    }

    /**
     * Gherkin step definition method to assign the order with orderId a marking marking,
     * and if necessary, a ticket approver.
     *
     * @param orderId          String containing the order ID for a specific shipment order.
     * @param marking          String containing the state of the order.
     * @param requiresApproval String containing "true" if the order needs the manager's approval and "false" otherwise.
     * @author Yvehenry Julsain
     */
    @Given("order {string} is marked as {string} with requires approval {string}")
    public void order_is_marked_as_with_requires_approval(String orderId, String marking,
                                                          String requiresApproval) {
        ShipmentOrder markedOrderApproval = ShipmentOrder.getWithId(Integer.parseInt(orderId));

        WarehouseStaff orderFixer = wareFlow.getManager();
        PriorityLevel priorityLevel = PriorityLevel.Low;
        TimeEstimate timeEstimate = TimeEstimate.LessThanADay;

        if (requiresApproval.equalsIgnoreCase("true")) {
            markedOrderApproval.setOrderApprover(wareFlow.getManager());
        }
        boolean requiresApprovalBoolean = Boolean.parseBoolean(requiresApproval);

        if (marking.equalsIgnoreCase("assigned")) {
            markedOrderApproval.assign(orderFixer, priorityLevel, timeEstimate, requiresApprovalBoolean);
        } else if (marking.equalsIgnoreCase("inprogress")) {
            markedOrderApproval.assign(orderFixer, priorityLevel, timeEstimate, requiresApprovalBoolean);
            markedOrderApproval.startWork();
        } else if (marking.equalsIgnoreCase("completed")) {
            markedOrderApproval.assign(orderFixer, priorityLevel, timeEstimate, requiresApprovalBoolean);
            markedOrderApproval.startWork();
            markedOrderApproval.markAsResolved();
        } else if (marking.equalsIgnoreCase("closed")) {
            markedOrderApproval.assign(orderFixer, priorityLevel, timeEstimate, requiresApprovalBoolean);
            markedOrderApproval.startWork();
            markedOrderApproval.markAsResolved();
        }
    }

    /**
     * Initializes shipment order with a specific containerNumber, type, purchaseDate, areaNumber, and slotNumber for future testing
     *
     * @param dataTable containing container number, type, purchase date, area number, and slot number
     * @author Jason Shao
     */
    @Given("the following containers exist in the system")
    public void the_following_containers_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(); // Getting Data
        for (var row : rows) {
            int containerNumber = Integer.parseInt(row.get("containerNumber"));
            ItemType type = ItemType.getWithName(row.get("type"));
            Date purchaseDate = Date.valueOf(row.get("purchaseDate"));
            int areaNumber = Integer.parseInt(row.get("areaNumber"));
            int slotNumber = Integer.parseInt(row.get("slotNumber"));

            new ItemContainer(containerNumber, areaNumber, slotNumber, purchaseDate, wareFlow, type);
        }
    }

    /**
     * Initialize the orders in the system with the given data table.
     *
     * @param dataTable This table contains an id, orderPlacer, placedOnDate, description, quantity, containerNumber.
     * @author Neeshal Imrit
     */
    @Given("the following orders exist in the system")
    public void the_following_orders_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {

            int id = Integer.parseInt(row.get("id"));
            String orderPlacer = row.get("orderPlacer");
            Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
            String description = row.get("description");
            int quantity = Integer.parseInt(row.get("quantity"));
            ShipmentOrder newOrder = new ShipmentOrder(id, placedOnDate, description, quantity, wareFlow, User.getWithUsername(orderPlacer));

            int containerNumber = Integer.parseInt(row.get("containerNumber"));
            newOrder.setContainer(ItemContainer.getWithContainerNumber(containerNumber));

            // Check status of shipment order
            TicketStatus status = TicketStatus.valueOf(row.get("status"));
            if (status != TicketStatus.Open) {

                User orderPicker = User.getWithUsername(row.get("processedBy"));
                TimeEstimate timeEstimate = TimeEstimate.valueOf(row.get("timeToResolve"));
                PriorityLevel priorityLevel = PriorityLevel.valueOf(row.get("priority"));
                boolean requiresApproval = Boolean.parseBoolean(row.get("approvalRequired"));

                if (status == TicketStatus.Assigned) {
                    newOrder.assign((WarehouseStaff) orderPicker, priorityLevel, timeEstimate, requiresApproval);
                } else if (status == TicketStatus.InProgress) {
                    newOrder.assign((WarehouseStaff) orderPicker, priorityLevel, timeEstimate, requiresApproval);
                    newOrder.startWork();
                } else if (status == TicketStatus.Completed) {
                    newOrder.assign((WarehouseStaff) orderPicker, priorityLevel, timeEstimate, requiresApproval);
                    newOrder.startWork();
                    newOrder.markAsResolved();
                } else if (status == TicketStatus.Closed) {
                    newOrder.assign((WarehouseStaff) orderPicker, priorityLevel, timeEstimate, requiresApproval);
                    newOrder.startWork();
                    newOrder.markAsResolved();
                    newOrder.approveWork();
                }
            }
        }
    }

    /**
     * Checks if the notes with the corresponding order ID, date, description and note taker exist in the system.
     *
     * @param dataTable This is a table containing orderIDs, addedOnDates, descriptions and noteTakers of different notes.
     * @author Al-Faysal Haidar
     */
    @Given("the following notes exist in the system")
    public void the_following_notes_exist_in_the_system(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            if (row.get("orderId") != null) {
                ShipmentOrder noteToAdd = ShipmentOrder.getWithId(Integer.parseInt(row.get("orderId")));
                noteToAdd.addShipmentNote(Date.valueOf(row.get("addedOnDate")),
                        String.format(row.get("description")),
                        (WarehouseStaff) WarehouseStaff.getWithUsername(row.get("noteTaker")));
            }
        }
    }

    /**
     * This step sets the Shipment Order with the provided ID to the provided state.
     *
     * @param orderID  The order ID number
     * @param orderState The state of the order
     * @author Benjamin Bouhdana
     */
    @Given("order {string} is marked as {string}")
    public void order_is_marked_as(String orderID, String orderState) {
        ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(orderID));

        
        TimeEstimate timeEstimate = TimeEstimate.OneToThreeDays;
        WarehouseStaff orderPicker = wareFlow.getManager(); 
        boolean requiresApproval = order.hasOrderApprover();
        PriorityLevel priorityLevel = PriorityLevel.Low;

        if (orderState.equalsIgnoreCase("assigned")) {
            order.assign(orderPicker, priorityLevel, timeEstimate, requiresApproval);
        }
        if (orderState.equalsIgnoreCase("inprogress")) {
            order.assign(orderPicker, priorityLevel, timeEstimate, requiresApproval);
            order.startWork();
        }
        if (orderState.equalsIgnoreCase("completed")) {
            requiresApproval = true;
            order.assign(orderPicker, priorityLevel, timeEstimate, requiresApproval);
            order.startWork();
            order.markAsResolved();
        }
        if (orderState.equalsIgnoreCase("closed")) {
            order.assign(orderPicker, priorityLevel, timeEstimate, false);
            order.startWork();
            order.markAsResolved();
        }
    }

    /**
     * @author Jordan Buchanan
     */
    @When("the manager attempts to view all shipment orders in the system")
    public void the_manager_attempts_to_view_all_shipment_orders_in_the_system() {
        orders = ShipmentOrderController.getOrders();
    }

    /**
     * This method sets the order status to inProgress
     *
     * @param string ID of the given shipment order
     * @author Jason Shao
     */
    @When("the warehouse staff attempts to start the order {string}")
    public void the_warehouse_staff_attempts_to_start_the_order(String string) {
        ShipmentOrder startOrder = ShipmentOrder.getWithId(Integer.parseInt(string));
        OrderController.startShipmentOrder(startOrder);
    }

    /**
     * Assigns WareHouse Staff member to an order with a time estimate, a priority and whether it requires manager approval to be closed.
     *
     * @param string  id of the shipment order.
     * @param string2 the username of the employee that will be assigned to the ticket.
     * @param string3 time estimate of the given order.
     * @param string4 priority of the given order.
     * @param string5 whether or not the ticket that will be assigned need manager approval.
     * @author Neeshal Imrit
     */
    @When("the manager attempts to assign the order {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
    public void the_manager_attempts_to_assign_the_order_to_with_estimated_time_priority_and_requires_approval(
            String string, String string2, String string3, String string4, String string5) {

        int id = Integer.parseInt(string);
        TimeEstimate timeEstimate = TimeEstimate.valueOf(string3);
        PriorityLevel priorityLevel = PriorityLevel.valueOf(string4);
        boolean requiresApproval = Boolean.parseBoolean(string5);

        OrderController.assign(id, string2, timeEstimate, priorityLevel, requiresApproval);
    }

    /**
     * Gherkin step definition method to set the order status as completed.
     *
     * @param string String containing the order ID of the shipment order to mark as completed.
     * @author Yvehenry Julsain
     */
    @When("the warehouse staff attempts to complete the order {string}")
    public void the_warehouse_staff_attempts_to_complete_the_order(String string) {
        ShipmentOrder completedOrder = ShipmentOrder.getWithId(Integer.parseInt(string));
        OrderController.completeShipmentOrder(completedOrder);
    }

    /**
     * Attempts to disapprove work on an order on a specific date and for a specific reason.
     *
     * @param orderID The order ID
     * @param date    The date of the disapproval
     * @param reason  The reason for the disapproval
     * @author Al-Faysal Haidar
     */
    @When("the manager attempts to disapprove the order {string} on date {string} and with reason {string}")
    public void the_manager_attempts_to_disapprove_the_order_on_date_and_with_reason(String orderID,
                                                                                     String date, String reason) {
        ShipmentOrder orderToDisapprove = ShipmentOrder.getWithId(Integer.parseInt(orderID));
        if (orderToDisapprove != null) {
            orderToDisapprove.disapproveWork(Date.valueOf(date), reason);
        }
    }

    /**
     * Sets the shipment order status to approved
     *
     * @param orderID order ID of desired ticket
     * @author Benjamin Bouhdana
     */
    @When("the manager attempts to approve the order {string}")
    public void the_manager_attempts_to_approve_the_order(String orderID) {
        ShipmentOrder approvalOrder = ShipmentOrder.getWithId(Integer.parseInt(orderID));
        OrderController.approveShipmentOrder(approvalOrder);
    }

    /**
     * Checks if the shipment order are present in the system. Checks if they have all of the same features (ie ticketID, raisedByEmail, etc).
     *
     * @param dataTable
     * @author Jason Shao
     */
    @Then("the following shipment orders shall be presented")
    public void the_following_shipment_orders_shall_be_presented(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        int i = 0;

        for (var row : rows) {
            TOShipmentOrder curOrder = orders.get(i);
            //check if each attribute is equal

            //check ID
            int ID = Integer.parseInt(row.get("id"));
            Assert.assertEquals(ID, curOrder.getId());

            String orderPlacer = row.get("orderPlacer");
            Assert.assertEquals(orderPlacer, curOrder.getOrderPlacer());

            Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
            Assert.assertEquals(placedOnDate, curOrder.getPlacedOnDate());

            String description = row.get("description");
            Assert.assertEquals(description, curOrder.getDescription());


            String expectedLifeSpanString = row.get("expectLifeSpanInDays");
            int expectedLifeSpan = -1;
            if (expectedLifeSpanString != null) {
                expectedLifeSpan = Integer.parseInt(expectedLifeSpanString);
            }
            Assert.assertEquals(expectedLifeSpan, curOrder.getExpectedLifeSpanInDays());

            String areaNumberString = row.get("areaNumber");
            int areaNumber = -1;
            if (areaNumberString != null) {
                areaNumber = Integer.parseInt(areaNumberString);
            }
            Assert.assertEquals(areaNumber, curOrder.getAreaNumber());

            String slotNumberString = row.get("slotNumber");
            int slotNumber = -1;
            if (slotNumberString != null) {
                slotNumber = Integer.parseInt(slotNumberString);
            }
            Assert.assertEquals(slotNumber, curOrder.getSlotNumber());

            //check if status is the same

            String status = row.get("status");
            Assert.assertEquals(status, curOrder.getStatus());

            //check if time to resolve is the same
            String timeToResolve = row.get("timeToResolve");
            Assert.assertEquals(timeToResolve, curOrder.getTimeToResolve());

            //check if priority to resolve is the same
            String priority = row.get("priority");
            Assert.assertEquals(priority, curOrder.getPriority());

            String approvalRequiredString = row.get("approvalRequired");

            if (approvalRequiredString != null) {
                if (approvalRequiredString.equalsIgnoreCase("true")) {
                    Assert.assertTrue(curOrder.getApprovalRequired());
                } else if (approvalRequiredString.equalsIgnoreCase("false")) {
                    Assert.assertFalse(curOrder.getApprovalRequired());
                } else {
                    Assert.assertNull(curOrder.getApprovalRequired());
                }
            }

            i++;
        }
    }

    /**
     * @param string    the orderId of a specific order in the system.
     * @param dataTable Cucumber DataTable containing the noteTaker, addedOnDate and description of
     *                  the notes of the order with the provided orderId.
     * @author Jordan Buchanan
     */
    @Then("the order with id {string} shall have the following notes")
    public void the_order_with_id_shall_have_the_following_notes(String string,
                                                                 io.cucumber.datatable.DataTable dataTable) {
        int orderID = Integer.parseInt(string);

        TOShipmentOrder currOrder = null;
        if (orders != null) {
            for (var order : orders) {
                if (order.getId() == orderID) {
                    currOrder = order;
                }
            }

            assertNotNull(currOrder);

            List<TOShipmentNote> currShipmentNotes = currOrder.getNotes();
            List<Map<String, String>> rows = dataTable.asMaps();
            int i = 0;
            for (var row : rows) {
                TOShipmentNote currNote = currShipmentNotes.get(i);
                String noteTaker = row.get("noteTaker");
                assertEquals(noteTaker, currNote.getNoteTakerUsername());
                Date date = Date.valueOf(row.get("addedOnDate"));
                assertEquals(date, currNote.getDate());
                String description = row.get("description");
                assertEquals(description, currNote.getDescription());
                i++;
            }
        }
    }

    /**
     * Gherkin step definition method to verify if the order with the ID "string" has 0 notes.
     *
     * @param string String containing the order ID of the shipment order containing 0 notes.
     * @author Yvehenry Julsain
     */
    @Then("the order with id {string} shall have no notes") //TODO
    public void the_order_with_id_shall_have_no_notes(String string) {
        ShipmentOrder orderWithNoNotes = ShipmentOrder.getWithId(Integer.parseInt(string));
        if (orderWithNoNotes.numberOfShipmentNotes() != 0)
            throw new AssertionError();
    }

    /**
     * Gherkin step definition method to verify if an order with the ID "string" exists.
     *
     * @param string String containing the order ID for the shipment order which is being searched.
     * @author Yvehenry Julsain
     */
    @Then("the order {string} shall not exist in the system") //TODO
    public void the_order_shall_not_exist_in_the_system(String string) {
        //ShipmentOrder nonExistantOrder = ShipmentOrder.getWithId(Integer.parseInt(string));
        if (ShipmentOrder.hasWithId(Integer.parseInt(string)))
            throw new AssertionError();
    }

    /**
     * This step checks that the number of orders in the system is the expected amount
     *
     * @param string The expected number of tickets in the system
     * @author Jason Shao
     */

    @Then("the number of orders in the system shall be {string}")
    public void the_number_of_orders_in_the_system_shall_be(String string) {
        int expectedNumOfTickets = Integer.parseInt(string);
        int actualNumOfTickets = wareFlow.numberOfOrders();   // get the actual number of orders
        Assertions.assertEquals(expectedNumOfTickets, actualNumOfTickets);
    }

    /**
     * Checks if the error message returned from a method is empty
     *
     * @param string Error message generated from the system
     * @author Jason Shao
     */
    @Then("the system shall raise the error {string}")
    public void the_system_shall_raise_the_error(String string) {
        Assert.assertFalse(string.isEmpty());
    }

    /**
     * Checks if the order has the correct status.
     *
     * @param string  the order id.
     * @param string2 the employee username.
     * @author Neeshal Imrit
     */
    @Then("the order {string} shall be marked as {string}")
    public void the_order_shall_be_marked_as(String string, String string2) {
        ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(string));
        String orderStatus = order.getTicketStatus().toString();
        assertEquals(string2, orderStatus);
    }

    /**
     * Checks if the order has been assigned to the right employee.
     *
     * @param string  the order id.
     * @param string2 the employee username.
     * @author Neeshal Imrit
     */
    @Then("the order {string} shall be assigned to {string}")
    public void the_order_shall_be_assigned_to(String string, String string2) {
        ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(string));
        String orderFixer = order.getOrderPicker().getUsername();
        assertEquals(string2, orderFixer);
    }

    /**
     * Verifies that the order in question has the appropriate estimated time, priority, and approval requirement.
     *
     * @param orderID          The order ID
     * @param estimatedTime    The estimated time for order fulfillment
     * @param priorityLevel    The priority level of the order
     * @param requiresApproval The requirement of an approval or not
     * @author Al-Faysal Haidar
     */
    @Then("the order {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
    public void the_order_shall_have_estimated_time_priority_and_requires_approval(String orderID,
                                                                                   String estimatedTime, String priorityLevel, String requiresApproval) {
        ShipmentOrder timePriorityAndApprovalOrder = ShipmentOrder.getWithId(Integer.parseInt(orderID));
        Assert.assertEquals(estimatedTime, timePriorityAndApprovalOrder.getTimeToFullfill().toString());
        Assert.assertEquals(priorityLevel, timePriorityAndApprovalOrder.getPriority().toString());
        Assert.assertEquals(Boolean.parseBoolean(requiresApproval), timePriorityAndApprovalOrder.hasOrderApprover());
    }
}