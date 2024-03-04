package ca.mcgill.ecse.wareflow.features;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.controller.ShipmentNoteController;
import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewStatusOfShipmentOrderStepDefinitions {
    private List<TOShipmentOrder> orders;
    private final static WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * Gherkin Scenario: Create employees
     *
     * @param dataTable Cucumber data table containing username, name, password, and phone number
     * @author Neeshal Imrit
     */
    @Given("the following employees exist in the system \\(f14)")
    public void the_following_employees_exist_in_the_system_f14(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : list) {
            wareFlow.addEmployee(row.get("username"), row.get("name"), row.get("password"),
                    row.get("phoneNumber"));
        }
    }

    /**
     * Gherkin Scenario: Create a manager
     *
     * @param dataTable Cucumber data table containing username and password of manager
     * @author Neeshal Imrit
     */
    @Given("the following manager exists in the system \\(f14)")
    public void the_following_manager_exists_in_the_system_f14(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        Manager manager = new Manager(list.get(0).get("username"), null, list.get(0).get("password"), null, wareFlow);
        wareFlow.setManager(manager);
    }

    /**
     * Gherkin step definition to add item types to the WareFlow app.
     *
     * @param dataTable Cucumber DataTable with the name and lifespan of the items
     * @author Yvehenry Samee Julsain
     */
    @Given("the following item types exist in the system \\(f14)")
    public void the_following_item_types_exist_in_the_system_f14(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> items = dataTable.asMaps();

        for (Map<String, String> item : items) {
            wareFlow.addItemType(item.get("name"), Integer.parseInt(item.get("expectedLifeSpanInDays")));
        }
    }

    /**
     * Gherkin step definition to add containers to the WareFlow app.
     *
     * @param dataTable Cucumber DataTable with the container number, item type in container, date the container was added, area number, and slot number
     * @author Yvehenry Samee Julsain
     */
    @Given("the following containers exist in the system \\(f14)")
    public void the_following_containers_exist_in_the_system_f14(
            io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> containers = dataTable.asMaps();

        for (Map<String, String> container : containers) {
            wareFlow.addItemContainer(Integer.parseInt(container.get("containerNumber")), Integer.parseInt(container.get("areaNumber")), Integer.parseInt(container.get("slotNumber")), Date.valueOf(container.get("addedOnDate")), ItemType.getWithName(container.get("type")));
        }
    }

    /**
     * Gherkin Scenario: Check if the following order(s) exist in the WareFlow application.
     *
     * @param dataTable Data Table containing an ID, an order placer, a placed-on-date, a description, a container number and a quantity.
     * @author Al-Faysal Haidar
     */
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
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            int id = Integer.parseInt(row.get("id"));
            String orderPlacer = row.get("orderPlacer");
            Date placedOnDate = Date.valueOf(row.get("placedOnDate"));
            String description = row.get("description");
            int containerNumber = Integer.parseInt(row.get("containerNumber"));
            int quantity = Integer.parseInt(row.get("quantity"));
            ShipmentOrderController.addShipmentOrder(id, placedOnDate, description, orderPlacer, containerNumber, quantity);
        }
    }

    /**
     * Gherkin Scenario: Check if following note(s) exist in the WareFlow application.
     *
     * @param dataTable Data Table containing a note taker, an order ID, a date and a description.
     * @author Al-Faysal Haidar
     */
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
        List<Map<String, String>> rows = dataTable.asMaps();
        for (var row : rows) {
            String noteTaker = row.get("noteTaker");
            int orderId = Integer.parseInt(row.get("orderId"));
            Date date = Date.valueOf(row.get("date"));
            String description = row.get("description");
            ShipmentNoteController.addShipmentNote(date, description, orderId, noteTaker);
        }
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
     *
     * @param dataTable Cucumber data table containing note taker, date and description of order ID
     * @author Ben Bouhdana
     */
    @Then("the order with id {string} shall have the following notes \\(f14)")
    public void the_order_with_id_shall_have_the_following_notes_f14(String string,
                                                                     io.cucumber.datatable.DataTable dataTable) {
        int orderID = Integer.parseInt(string);
        ShipmentOrder currentOrder = ShipmentOrder.getWithId(orderID);

        assertNotNull(currentOrder);

        List<ShipmentNote> currentOrderNotes = currentOrder.getShipmentNotes();
        List<Map<String, String>> rows = dataTable.asMaps();
        int i = 0;
        for (var row : rows) {
            ShipmentNote currentNote = currentOrderNotes.get(i);
            String noteTaker = row.get("noteTaker");
            Date date = Date.valueOf(row.get("date"));
            String description = row.get("description");
            assertEquals(noteTaker, String.valueOf(currentNote.getNoteTaker()));
            assertEquals(date, currentNote.getDate());
            assertEquals(description, currentNote.getDescription());
            i++;
        }
    }

    /**
     * Gherkin Scenario: Search for order ID in the table
     *
     * @param string Cucumber containing shipment order information
     * @author Jordan Buchanan
     */
    @Then("the order with id {string} shall have no notes \\(f14)")
    public void the_order_with_id_shall_have_no_notes_f14(String string) {
        int checkId = Integer.parseInt(string);
        ShipmentOrder aShipmentOrder = ShipmentOrder.getWithId(checkId);
        assertNotNull(aShipmentOrder);
        assertEquals(aShipmentOrder.getShipmentNotes().size(), 0);
    }
}
