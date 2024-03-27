package ca.mcgill.ecse.wareflow.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentNote;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.model.WareFlow;



import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.PriorityLevel;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder.TimeEstimate;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ca.mcgill.ecse.wareflow.controller.OrderController;

public class ShipmentOrderStepDefinitions {
	  
	private List<TOShipmentOrder> orders;
  private final WareFlow wareFlow = WareFlowApplication.getWareFlow();

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

  /**
   * @author Jordan Buchanan
   * @param dataTable Cucumber DataTable containing the name and expectedLifeSpanInDays of the item
   *        types that must exist in the system.
   */
  @Given("the following items exist in the system")
  public void the_following_items_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
	for (Map<String, String> row : rows) {
	      wareFlow.addItemType(row.get("name"), Integer.parseInt(row.get("expectedLifeSpanInDays")));
		}
    }

  @Given("order {string} is marked as {string} with requires approval {string}")
  public void order_is_marked_as_with_requires_approval(String string, String string2,
      String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following containers exist in the system")
  public void the_following_containers_exist_in_the_system(
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

  /**
   * Intialize the orders in the system with the given data table.
   * @author Neeshal
   * @param dataTable This table contains an id, orderPlacer, placedOnDate, description, quantity, containerNumber.
   */
  @Given("the following orders exist in the system")
  public void the_following_orders_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String,String>> rows = dataTable.asMaps();
    for (var row : rows) {

      int id = Integer.parseInt(row.get("id"));
      String orderPlacer = row.get("orderPlacer");
      Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
      String description = row.get("description");
      int quantity = Integer.parseInt(row.get("quantity"));
      ShipmentOrder newOrder = new ShipmentOrder(id, placedOnDate, description, quantity, wareFlow, User.getWithUsername(orderPlacer));
      
      if (newOrder != null) {
        int containerNumber = Integer.parseInt(row.get("containerNumber"));
        newOrder.setContainer(ItemContainer.getWithContainerNumber(containerNumber));
      }

    }
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

  /**
   * @author Jordan Buchanan
   */
  @When("the manager attempts to view all shipment orders in the system")
  public void the_manager_attempts_to_view_all_shipment_orders_in_the_system() {
	    orders = ShipmentOrderController.getOrders();
  }

  @When("the warehouse staff attempts to start the order {string}")
  public void the_warehouse_staff_attempts_to_start_the_order(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * Assigns WareHouse Staff member to an order with a time estimate, a priority and whether it requires manager approval to be closed.
   * @author Neeshal Imrit
   * @param string id of the shipment order.
   * @param string2 the username of the employee that will be assigned to the ticket.
   * @param string3 time estimate of the given order.
   * @param string4 priority of the given order.
   * @param string5 whether or not the ticket that will be assigned need manager approval.
   */
  @When("the manager attempts to assign the order {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_order_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {

    int id = Integer.parseInt(string);
    String orderFixer = string2;
    TimeEstimate timeEstimate = TimeEstimate.valueOf(string3);
    PriorityLevel priorityLevel = PriorityLevel.valueOf(string4);
    boolean requiresApproval = Boolean.parseBoolean(string5);

    OrderController.assign(id, orderFixer, timeEstimate, priorityLevel, requiresApproval);
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

  @Then("the following shipment orders shall be presented")
  public void the_following_shipment_orders_shall_be_presented(
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

  /**
   * @author Jordan Buchanan
   * 
   * @param string the orderId of a specific order in the system.
   * @param dataTable Cucumber DataTable containing the noteTaker, addedOnDate and description of
   *        the notes of the order with the provided orderId.
   */
  @Then("the order with id {string} shall have the following notes")
  public void the_order_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
	  int orderID = Integer.parseInt(string);
	    TOShipmentOrder currOrder = null;
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
	      Date date = Date.valueOf(row.get("date"));
	      assertEquals(date, currNote.getDate());
	      String description = row.get("description");
	      assertEquals(description, currNote.getDescription());
	      i++;
	    }
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

  @Then("the number of orders in the system shall be {string}")
  public void the_number_of_orders_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  /**
   * Checks if the order has the corrrct status.
   * @author Neeshal Imrit
   * @param string the order id.
   * @param string2 the employee username.
   */
  @Then("the order {string} shall be marked as {string}")
  public void the_order_shall_be_marked_as(String string, String string2) {
    ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(string));
    String orderStatus = order.getTicketStatusFullName();
    assertEquals(string2, orderStatus);
  }

  /**
   * Checks if the order has been assigned to the right employee.
   * @author Neeshal Imrit
   * @param string the order id.
   * @param string2 the employee username.
   */
  @Then("the order {string} shall be assigned to {string}")
  public void the_order_shall_be_assigned_to(String string, String string2) {
    ShipmentOrder order = ShipmentOrder.getWithId(Integer.parseInt(string));
    String orderFixer = order.getOrderPicker().getUsername();
    assertEquals(string2, orderFixer);
  }

  @Then("the order {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_order_shall_have_estimated_time_priority_and_requires_approval(String string,
      String string2, String string3, String string4) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
