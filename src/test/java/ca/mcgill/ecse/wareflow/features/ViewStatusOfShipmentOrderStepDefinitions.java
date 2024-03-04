package ca.mcgill.ecse.wareflow.features;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.model.ShipmentOrder;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ViewStatusOfShipmentOrderStepDefinitions {

    private final static WareFlow wareFlow = WareFlowApplication.getWareFlow();
    private List<TOShipmentOrder> orders;

  /**
   * Gherkin Scenario: Create employees
   * 
   * @author Neeshal Imrit
   * @param dataTable Cucumber data table containing username, name, password, and phone number
   */
  @Given("the following employees exist in the system \\(f14)")
  public void the_following_employees_exist_in_the_system_f14(
      io.cucumber.datatable.DataTable dataTable) {

    List <Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    for (Map<String, String> row : list) {
      wareFlow.addEmployee(row.get("username"), row.get("name"),row.get("password"),
          row.get("phoneNumber"));
    }
  }

  /**
   * Gherkin Scenario: Create a manager
   * @author Neeshal Imrit
   * @param dataTable Cucumber data table containing username and password of manager
   */
  @Given("the following manager exists in the system \\(f14)")
  public void the_following_manager_exists_in_the_system_f14(
      io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
    Manager manager = new Manager(list.get(0).get("username"), null, list.get(0).get("password"), null, wareFlow);
    wareFlow.setManager(manager);
    
  }

  @Given("the following item types exist in the system \\(f14)")
  public void the_following_item_types_exist_in_the_system_f14(
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

  @Given("the following containers exist in the system \\(f14)")
  public void the_following_containers_exist_in_the_system_f14(
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

  @Given("the following orders exist in the system \\(f14)")
  public void the_following_orders_exist_in_the_system_f14(
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

  @Given("the following notes exist in the system \\(f14)")
  public void the_following_notes_exist_in_the_system_f14(
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
     * Gherkin Scenario: Test the ShipmentOrderController by getting all the orders in the system.
     *
     * @author Jason Shao
     */
    @When("the manager attempts to view all shipment orders in the system \\(f14)")
    public void the_manager_attempts_to_view_all_shipment_orders_in_the_system_f14() {
        orders = ShipmentOrderController.getOrders();
    }

    /**
     * Gherkin Scenario: display the 3 shipment orders that should be presented when the manager attempts to view all the shipment orders in the system.
     *
     * @param dataTable Cucumber data table containing id, orderPlacer, placedOnDate, description, itemName, expectedLifeSpanInDays, addedOnData, areaNumber, slotNumber, quantity of the orders shown.
     * @author Jason Shao
     */
    @Then("the following shipment orders shall be presented \\(f14)")
    public void the_following_shipment_orders_shall_be_presented_f14(
            io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        int i = 0;
        for (var row : rows) {
            TOShipmentOrder cuOrder = orders.get(i);
            int id = Integer.parseInt(row.get("id"));
            assertEquals(id, cuOrder.getId());
            String orderPlacer = row.get("orderPlacer");
            assertEquals(orderPlacer, cuOrder.getOrderPlacer());
            Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
            assertEquals(placedOnDate, cuOrder.getPlacedOnDate());
            String description = row.get("description");
            assertEquals(description, cuOrder.getDescription());
            String itemName = row.get("itemName");
            assertEquals(itemName, cuOrder.getItemName());
            int expectedLifeSpanInDays = Integer.parseInt(row.get("expectedLifeSpanInDays"));
            assertEquals(expectedLifeSpanInDays, cuOrder.getExpectedLifeSpanInDays());

            String addedOnDateStr = row.get("addedOnDate");
            Date addedOnDate = null;
            if (addedOnDateStr != null) {
                addedOnDate = Date.valueOf(addedOnDateStr);
            }
            assertEquals(addedOnDate, cuOrder.getAddedOnDate());

            String areaNumberStr = row.get("areaNumber");
            int areaNumber = -1;
            if (areaNumberStr != null) {
                areaNumber = Integer.parseInt(areaNumberStr);
            }
            assertEquals(areaNumber, cuOrder.getAreaNumber());

            String slotNumberStr = row.get("slotNumber");
            int slotNumber = -1;
            if (slotNumberStr != null) {
                slotNumber = Integer.parseInt(slotNumberStr);
            }
            assertEquals(slotNumber, cuOrder.getSlotNumber());

            String quantityStr = row.get("quantity");
            int quantity = -1;
            if (quantityStr != null) {
                quantity = Integer.parseInt(quantityStr);
            }
            assertEquals(quantity, cuOrder.getQuantity());

            i++;
        }
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List[E], List[List[E]], List[Map[K,V]], Map[K,V] or
        // Map[K, List[V]]. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }

  
  /**
   * Gherkin Scenario: Look up order ID in the table
   * @author Ben Bouhdana
   * @param dataTable Cucumber data table containing note taker, date and description of order ID
   */
  @Then("the order with id {string} shall have the following notes \\(f14)")
  public void the_order_with_id_shall_have_the_following_notes_f14(String string,
      io.cucumber.datatable.DataTable dataTable) {
    int orderID = Integer.parseInt(string);
    TOShipmentOrder currentOrder = null;
    for (var order : orders) {
      if (order.getID() == orderID) {
        currentOrder = order;
      }
    }

    assertNotNull(currentOrder);

    List<TOShipmentNote> currentOrderNotes = currentOrder.getNotes();
    List<Map<String,String>> rows = dataTable.asMaps();
    int i = 0;
    for (var row : rows) {
      TOShipmentNote currentNote = currentOrderNotes.get(i);
      String noteTaker = row.get("noteTaker");
      Date date = Date.valueOf(row.get("date"));
      String description = row.get("description");
      assertEquals(noteTaker, currentNote.getNoteTakerUsername());
      assertEquals(date, currentNote.getDate());
      assertEquals(description, currentNote.getDescription());
      i++;
    }
  }

    /**
     * Gherkin Scenario: Search for order ID in the table
     *
     * @param dataTable Cucumber containing shipment order information
     * @author Jordan Buchanan
     */
    @Then("the order with id {string} shall have no notes \\(f14)")
    public void the_order_with_id_shall_have_no_notes_f14(String string) {
        Integer checkId = Integer.parseInt(string);
        aShipmentOrder = ShipmentOrder.getWithId(checkId);
        assertNotNull(aShipmentOrder);
        assertEquals(aShipmentOrder.getShipmentNotes.size(), 0);
    }
  
}


