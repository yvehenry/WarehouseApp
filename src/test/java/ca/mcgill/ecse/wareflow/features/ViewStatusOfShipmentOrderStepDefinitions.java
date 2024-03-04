package ca.mcgill.ecse.wareflow.features;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.Manager;
import ca.mcgill.ecse.wareflow.model.WareFlow;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewStatusOfShipmentOrderStepDefinitions {

  private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

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

  @When("the manager attempts to view all shipment orders in the system \\(f14)")
  public void the_manager_attempts_to_view_all_shipment_orders_in_the_system_f14() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following shipment orders shall be presented \\(f14)")
  public void the_following_shipment_orders_shall_be_presented_f14(
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

  @Then("the order with id {string} shall have the following notes \\(f14)")
  public void the_order_with_id_shall_have_the_following_notes_f14(String string,
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

  @Then("the order with id {string} shall have no notes \\(f14)")
  public void the_order_with_id_shall_have_no_notes_f14(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }


}
