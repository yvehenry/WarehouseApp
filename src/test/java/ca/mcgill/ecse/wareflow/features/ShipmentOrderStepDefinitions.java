package ca.mcgill.ecse.wareflow.features;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TicketStatus;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.OrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentNote;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.model.ItemType;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.User;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShipmentOrderStepDefinitions {
  private List<TOShipmentOrder> orders;


  @Given("the following employees exist in the system")
  public void the_following_employees_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following manager exists in the system")
  public void the_following_manager_exists_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following items exist in the system")
  public void the_following_items_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("order {string} is marked as {string} with requires approval {string}")
  public void order_is_marked_as_with_requires_approval(String string, String string2,
      String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
     * Initializes shipment order with a specific containerNumber, type, purchaseDate, areaNumber, and slotNumber for future testing
     * @author Jason Shao
     * @param dataTable
     */
  //   Given the following containers exist in the system
  //     | containerNumber | type        | purchaseDate | areaNumber | slotNumber |
  //     |               1 | NotFound TV |   2022-03-20 |          9 |         23 |
  //     |               2 | Bed         |   2010-01-30 |         10 |         35 |
  //     |               3 | Bed         |   2010-01-30 |          1 |         35 |
  @Given("the following containers exist in the system")
  public void the_following_containers_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(); // Getting Data
        for (var row: rows){
            int containerNumber = Integer.parseInt(row.get("containerNumber"));
            ItemType type = ItemType.getWithName(row.get("type"));
            Date purchaseDate = Date.valueOf(row.get("purchaseDate"));
            int areaNumber = Integer.parseInt(row.get("areaNumber"));
            int slotNumber = Integer.parseInt(row.get("slotNumber"));
        }
  }

  @Given("the following orders exist in the system")
  public void the_following_orders_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Given("order {string} is marked as {string}")
  public void order_is_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to view all shipment orders in the system")
  public void the_manager_attempts_to_view_all_shipment_orders_in_the_system() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
  * This method sets the order status to inProgress
  * @author Jason Shao
  * @param string ID of the given shipment order
  */
  @When("the warehouse staff attempts to start the order {string}")
  public void the_warehouse_staff_attempts_to_start_the_order(String string) {
    ShipmentOrder startOrder = ShipmentOrder.getWithId(Integer.parseInt(string));
    OrderController.startShipmentOrder(startOrder);
  }

  @When("the manager attempts to assign the order {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_order_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the warehouse staff attempts to complete the order {string}")
  public void the_warehouse_staff_attempts_to_complete_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @When("the manager attempts to disapprove the order {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_order_on_date_and_with_reason(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  @When("the manager attempts to approve the order {string}")
  public void the_manager_attempts_to_approve_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


  /**
     * Checks if the shipment order are present in the system. Checks if they have all of the same features (ie ticketID, raisedByEmail, etc).
     * @author Jason Shao
     * @param dataTable
     */
  @Then("the following shipment orders shall be presented")
  public void the_following_shipment_orders_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();

        int i = 0;

        for (var row : rows)  {
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

            if (approvalRequiredString!=null){
                if (approvalRequiredString.equalsIgnoreCase("true")){
                    Assert.assertTrue(curOrder.getApprovalRequired());
                } else if (approvalRequiredString.equalsIgnoreCase("false")){
                    Assert.assertFalse(curOrder.getApprovalRequired());
                }
                else {
                    Assert.assertNull(curOrder.getApprovalRequired());
                }
            }

            i++;
        }
  }

  @Then("the order with id {string} shall have the following notes")
  public void the_order_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
    // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order with id {string} shall have no notes")
  public void the_order_with_id_shall_have_no_notes(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall not exist in the system")
  public void the_order_shall_not_exist_in_the_system(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
/**
 * This step checks that the number of orders in the system is the expected amount
 * @author Jason Shao
 * @param string The expected number of tickets in the system
 */

  @Then("the number of orders in the system shall be {string}")
  public void the_number_of_orders_in_the_system_shall_be(String string) {
    int expectedNumOfTickets = Integer.parseInt(string);
    int actualNumOfTickets = WareFlow.numberOfOrders();   // get the actual number of orders
    Assertions.assertEquals(expectedNumOfTickets, actualNumOfTickets);
  }
/**
     * Checks if the error message returned from a method is empty
     * @author Jason Shao
     * @param string  Error message generated from the system
     */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    Assert.assertFalse(string.isEmpty());
  }

  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall be assigned to {string}")
  public void the_order_shall_be_assigned_to(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the order {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_order_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
