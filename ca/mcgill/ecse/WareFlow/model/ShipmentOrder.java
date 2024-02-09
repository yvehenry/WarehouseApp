/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;
import java.sql.Date;

// line 48 "../../../../../WareFlow.ump"
// line 112 "../../../../../WareFlow.ump"
public class ShipmentOrder
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum OrderStatus { CLOSED, IN_PROGRESS, RETURNED }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShipmentOrder Attributes
  private int orderNumber;
  private String description;
  private String shipmentAddress;
  private String receiver;
  private int quantity;
  private OrderStatus orderStatus;

  //ShipmentOrder Associations
  private WareFlow wareFlow;
  private List<Employee> assignedEmployees;
  private List<Client> orderClients;
  private List<Container> containersPerOrder;
  private ShipmentNote note;
  private ManagerTask managerTask;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShipmentOrder(int aOrderNumber, String aDescription, String aShipmentAddress, String aReceiver, int aQuantity, OrderStatus aOrderStatus, WareFlow aWareFlow, ShipmentNote aNote, ManagerTask aManagerTask)
  {
    orderNumber = aOrderNumber;
    description = aDescription;
    shipmentAddress = aShipmentAddress;
    receiver = aReceiver;
    quantity = aQuantity;
    orderStatus = aOrderStatus;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create shipmentOrder due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignedEmployees = new ArrayList<Employee>();
    orderClients = new ArrayList<Client>();
    containersPerOrder = new ArrayList<Container>();
    if (aNote == null || aNote.getOrderToBeNoted() != null)
    {
      throw new RuntimeException("Unable to create ShipmentOrder due to aNote. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    note = aNote;
    if (aManagerTask == null || aManagerTask.getShipmentOrder() != null)
    {
      throw new RuntimeException("Unable to create ShipmentOrder due to aManagerTask. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    managerTask = aManagerTask;
  }

  public ShipmentOrder(int aOrderNumber, String aDescription, String aShipmentAddress, String aReceiver, int aQuantity, OrderStatus aOrderStatus, WareFlow aWareFlow, Date aDateForNote, String aNoteForNote, WareFlow aWareFlowForNote, boolean aRequiresManagerApprovalForManagerTask, Priority aPriorityForManagerTask, TimeEstimate aTimeEstimateForManagerTask, WareFlow aWareFlowForManagerTask, Manager aManagerForManagerTask)
  {
    orderNumber = aOrderNumber;
    description = aDescription;
    shipmentAddress = aShipmentAddress;
    receiver = aReceiver;
    quantity = aQuantity;
    orderStatus = aOrderStatus;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create shipmentOrder due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    assignedEmployees = new ArrayList<Employee>();
    orderClients = new ArrayList<Client>();
    containersPerOrder = new ArrayList<Container>();
    note = new ShipmentNote(aDateForNote, aNoteForNote, aWareFlowForNote, this);
    managerTask = new ManagerTask(aRequiresManagerApprovalForManagerTask, aPriorityForManagerTask, aTimeEstimateForManagerTask, aWareFlowForManagerTask, aManagerForManagerTask, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderNumber(int aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setShipmentAddress(String aShipmentAddress)
  {
    boolean wasSet = false;
    shipmentAddress = aShipmentAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setReceiver(String aReceiver)
  {
    boolean wasSet = false;
    receiver = aReceiver;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderStatus(OrderStatus aOrderStatus)
  {
    boolean wasSet = false;
    orderStatus = aOrderStatus;
    wasSet = true;
    return wasSet;
  }

  public int getOrderNumber()
  {
    return orderNumber;
  }

  public String getDescription()
  {
    return description;
  }

  public String getShipmentAddress()
  {
    return shipmentAddress;
  }

  public String getReceiver()
  {
    return receiver;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public OrderStatus getOrderStatus()
  {
    return orderStatus;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetMany */
  public Employee getAssignedEmployee(int index)
  {
    Employee aAssignedEmployee = assignedEmployees.get(index);
    return aAssignedEmployee;
  }

  public List<Employee> getAssignedEmployees()
  {
    List<Employee> newAssignedEmployees = Collections.unmodifiableList(assignedEmployees);
    return newAssignedEmployees;
  }

  public int numberOfAssignedEmployees()
  {
    int number = assignedEmployees.size();
    return number;
  }

  public boolean hasAssignedEmployees()
  {
    boolean has = assignedEmployees.size() > 0;
    return has;
  }

  public int indexOfAssignedEmployee(Employee aAssignedEmployee)
  {
    int index = assignedEmployees.indexOf(aAssignedEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Client getOrderClient(int index)
  {
    Client aOrderClient = orderClients.get(index);
    return aOrderClient;
  }

  public List<Client> getOrderClients()
  {
    List<Client> newOrderClients = Collections.unmodifiableList(orderClients);
    return newOrderClients;
  }

  public int numberOfOrderClients()
  {
    int number = orderClients.size();
    return number;
  }

  public boolean hasOrderClients()
  {
    boolean has = orderClients.size() > 0;
    return has;
  }

  public int indexOfOrderClient(Client aOrderClient)
  {
    int index = orderClients.indexOf(aOrderClient);
    return index;
  }
  /* Code from template association_GetMany */
  public Container getContainersPerOrder(int index)
  {
    Container aContainersPerOrder = containersPerOrder.get(index);
    return aContainersPerOrder;
  }

  public List<Container> getContainersPerOrder()
  {
    List<Container> newContainersPerOrder = Collections.unmodifiableList(containersPerOrder);
    return newContainersPerOrder;
  }

  public int numberOfContainersPerOrder()
  {
    int number = containersPerOrder.size();
    return number;
  }

  public boolean hasContainersPerOrder()
  {
    boolean has = containersPerOrder.size() > 0;
    return has;
  }

  public int indexOfContainersPerOrder(Container aContainersPerOrder)
  {
    int index = containersPerOrder.indexOf(aContainersPerOrder);
    return index;
  }
  /* Code from template association_GetOne */
  public ShipmentNote getNote()
  {
    return note;
  }
  /* Code from template association_GetOne */
  public ManagerTask getManagerTask()
  {
    return managerTask;
  }
  /* Code from template association_SetOneToMany */
  public boolean setWareFlow(WareFlow aWareFlow)
  {
    boolean wasSet = false;
    if (aWareFlow == null)
    {
      return wasSet;
    }

    WareFlow existingWareFlow = wareFlow;
    wareFlow = aWareFlow;
    if (existingWareFlow != null && !existingWareFlow.equals(aWareFlow))
    {
      existingWareFlow.removeShipmentOrder(this);
    }
    wareFlow.addShipmentOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignedEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignedEmployee(Employee aAssignedEmployee)
  {
    boolean wasAdded = false;
    if (assignedEmployees.contains(aAssignedEmployee)) { return false; }
    assignedEmployees.add(aAssignedEmployee);
    if (aAssignedEmployee.indexOfAssignedOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignedEmployee.addAssignedOrder(this);
      if (!wasAdded)
      {
        assignedEmployees.remove(aAssignedEmployee);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignedEmployee(Employee aAssignedEmployee)
  {
    boolean wasRemoved = false;
    if (!assignedEmployees.contains(aAssignedEmployee))
    {
      return wasRemoved;
    }

    int oldIndex = assignedEmployees.indexOf(aAssignedEmployee);
    assignedEmployees.remove(oldIndex);
    if (aAssignedEmployee.indexOfAssignedOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignedEmployee.removeAssignedOrder(this);
      if (!wasRemoved)
      {
        assignedEmployees.add(oldIndex,aAssignedEmployee);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedEmployeeAt(Employee aAssignedEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedEmployee(aAssignedEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedEmployees()) { index = numberOfAssignedEmployees() - 1; }
      assignedEmployees.remove(aAssignedEmployee);
      assignedEmployees.add(index, aAssignedEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedEmployeeAt(Employee aAssignedEmployee, int index)
  {
    boolean wasAdded = false;
    if(assignedEmployees.contains(aAssignedEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedEmployees()) { index = numberOfAssignedEmployees() - 1; }
      assignedEmployees.remove(aAssignedEmployee);
      assignedEmployees.add(index, aAssignedEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedEmployeeAt(aAssignedEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderClients()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOrderClient(Client aOrderClient)
  {
    boolean wasAdded = false;
    if (orderClients.contains(aOrderClient)) { return false; }
    orderClients.add(aOrderClient);
    if (aOrderClient.indexOfOrdersOpened(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrderClient.addOrdersOpened(this);
      if (!wasAdded)
      {
        orderClients.remove(aOrderClient);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOrderClient(Client aOrderClient)
  {
    boolean wasRemoved = false;
    if (!orderClients.contains(aOrderClient))
    {
      return wasRemoved;
    }

    int oldIndex = orderClients.indexOf(aOrderClient);
    orderClients.remove(oldIndex);
    if (aOrderClient.indexOfOrdersOpened(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrderClient.removeOrdersOpened(this);
      if (!wasRemoved)
      {
        orderClients.add(oldIndex,aOrderClient);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderClientAt(Client aOrderClient, int index)
  {  
    boolean wasAdded = false;
    if(addOrderClient(aOrderClient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderClients()) { index = numberOfOrderClients() - 1; }
      orderClients.remove(aOrderClient);
      orderClients.add(index, aOrderClient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderClientAt(Client aOrderClient, int index)
  {
    boolean wasAdded = false;
    if(orderClients.contains(aOrderClient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderClients()) { index = numberOfOrderClients() - 1; }
      orderClients.remove(aOrderClient);
      orderClients.add(index, aOrderClient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderClientAt(aOrderClient, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContainersPerOrder()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Container addContainersPerOrder(int aContainerNumber, Date aAddedDate, WareFlow aWareFlow, Item aItemsInContainer, Area aContainerArea)
  {
    return new Container(aContainerNumber, aAddedDate, aWareFlow, aItemsInContainer, this, aContainerArea);
  }

  public boolean addContainersPerOrder(Container aContainersPerOrder)
  {
    boolean wasAdded = false;
    if (containersPerOrder.contains(aContainersPerOrder)) { return false; }
    ShipmentOrder existingLinkedOrders = aContainersPerOrder.getLinkedOrders();
    boolean isNewLinkedOrders = existingLinkedOrders != null && !this.equals(existingLinkedOrders);
    if (isNewLinkedOrders)
    {
      aContainersPerOrder.setLinkedOrders(this);
    }
    else
    {
      containersPerOrder.add(aContainersPerOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContainersPerOrder(Container aContainersPerOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aContainersPerOrder, as it must always have a linkedOrders
    if (!this.equals(aContainersPerOrder.getLinkedOrders()))
    {
      containersPerOrder.remove(aContainersPerOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addContainersPerOrderAt(Container aContainersPerOrder, int index)
  {  
    boolean wasAdded = false;
    if(addContainersPerOrder(aContainersPerOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainersPerOrder()) { index = numberOfContainersPerOrder() - 1; }
      containersPerOrder.remove(aContainersPerOrder);
      containersPerOrder.add(index, aContainersPerOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainersPerOrderAt(Container aContainersPerOrder, int index)
  {
    boolean wasAdded = false;
    if(containersPerOrder.contains(aContainersPerOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainersPerOrder()) { index = numberOfContainersPerOrder() - 1; }
      containersPerOrder.remove(aContainersPerOrder);
      containersPerOrder.add(index, aContainersPerOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainersPerOrderAt(aContainersPerOrder, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeShipmentOrder(this);
    }
    ArrayList<Employee> copyOfAssignedEmployees = new ArrayList<Employee>(assignedEmployees);
    assignedEmployees.clear();
    for(Employee aAssignedEmployee : copyOfAssignedEmployees)
    {
      aAssignedEmployee.removeAssignedOrder(this);
    }
    ArrayList<Client> copyOfOrderClients = new ArrayList<Client>(orderClients);
    orderClients.clear();
    for(Client aOrderClient : copyOfOrderClients)
    {
      aOrderClient.removeOrdersOpened(this);
    }
    for(int i=containersPerOrder.size(); i > 0; i--)
    {
      Container aContainersPerOrder = containersPerOrder.get(i - 1);
      aContainersPerOrder.delete();
    }
    ShipmentNote existingNote = note;
    note = null;
    if (existingNote != null)
    {
      existingNote.delete();
    }
    ManagerTask existingManagerTask = managerTask;
    managerTask = null;
    if (existingManagerTask != null)
    {
      existingManagerTask.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "orderNumber" + ":" + getOrderNumber()+ "," +
            "description" + ":" + getDescription()+ "," +
            "shipmentAddress" + ":" + getShipmentAddress()+ "," +
            "receiver" + ":" + getReceiver()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderStatus" + "=" + (getOrderStatus() != null ? !getOrderStatus().equals(this)  ? getOrderStatus().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "note = "+(getNote()!=null?Integer.toHexString(System.identityHashCode(getNote())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "managerTask = "+(getManagerTask()!=null?Integer.toHexString(System.identityHashCode(getManagerTask())):"null");
  }
}