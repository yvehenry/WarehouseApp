/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;
import java.sql.Date;

// line 3 "../../../../../WareFlow.ump"
public class WareFlow
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum OrderStatus { CLOSED, IN_PROGRESS, RETURNED }
  public enum Priority { URGENT, NORMAL, LOW }
  public enum TimeEstimate { LESS_THAN_A_DAY, ONE_TO_THREE_DAYS, THREE_TO_SEVEN_DAYS, ONE_TO_THREE_WEEKS, MORE_THAN_THREE_WEEKS }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //WareFlow Associations
  private List<User> users;
  private List<UserRole> userRoles;
  private List<ShipmentOrder> shipmentOrders;
  private List<Area> areas;
  private List<Container> containers;
  private List<Item> items;
  private List<ShipmentNote> shipmentNotes;
  private List<ManagerTask> managerTasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public WareFlow()
  {
    users = new ArrayList<User>();
    userRoles = new ArrayList<UserRole>();
    shipmentOrders = new ArrayList<ShipmentOrder>();
    areas = new ArrayList<Area>();
    containers = new ArrayList<Container>();
    items = new ArrayList<Item>();
    shipmentNotes = new ArrayList<ShipmentNote>();
    managerTasks = new ArrayList<ManagerTask>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public UserRole getUserRole(int index)
  {
    UserRole aUserRole = userRoles.get(index);
    return aUserRole;
  }

  public List<UserRole> getUserRoles()
  {
    List<UserRole> newUserRoles = Collections.unmodifiableList(userRoles);
    return newUserRoles;
  }

  public int numberOfUserRoles()
  {
    int number = userRoles.size();
    return number;
  }

  public boolean hasUserRoles()
  {
    boolean has = userRoles.size() > 0;
    return has;
  }

  public int indexOfUserRole(UserRole aUserRole)
  {
    int index = userRoles.indexOf(aUserRole);
    return index;
  }
  /* Code from template association_GetMany */
  public ShipmentOrder getShipmentOrder(int index)
  {
    ShipmentOrder aShipmentOrder = shipmentOrders.get(index);
    return aShipmentOrder;
  }

  public List<ShipmentOrder> getShipmentOrders()
  {
    List<ShipmentOrder> newShipmentOrders = Collections.unmodifiableList(shipmentOrders);
    return newShipmentOrders;
  }

  public int numberOfShipmentOrders()
  {
    int number = shipmentOrders.size();
    return number;
  }

  public boolean hasShipmentOrders()
  {
    boolean has = shipmentOrders.size() > 0;
    return has;
  }

  public int indexOfShipmentOrder(ShipmentOrder aShipmentOrder)
  {
    int index = shipmentOrders.indexOf(aShipmentOrder);
    return index;
  }
  /* Code from template association_GetMany */
  public Area getArea(int index)
  {
    Area aArea = areas.get(index);
    return aArea;
  }

  public List<Area> getAreas()
  {
    List<Area> newAreas = Collections.unmodifiableList(areas);
    return newAreas;
  }

  public int numberOfAreas()
  {
    int number = areas.size();
    return number;
  }

  public boolean hasAreas()
  {
    boolean has = areas.size() > 0;
    return has;
  }

  public int indexOfArea(Area aArea)
  {
    int index = areas.indexOf(aArea);
    return index;
  }
  /* Code from template association_GetMany */
  public Container getContainer(int index)
  {
    Container aContainer = containers.get(index);
    return aContainer;
  }

  public List<Container> getContainers()
  {
    List<Container> newContainers = Collections.unmodifiableList(containers);
    return newContainers;
  }

  public int numberOfContainers()
  {
    int number = containers.size();
    return number;
  }

  public boolean hasContainers()
  {
    boolean has = containers.size() > 0;
    return has;
  }

  public int indexOfContainer(Container aContainer)
  {
    int index = containers.indexOf(aContainer);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetMany */
  public ShipmentNote getShipmentNote(int index)
  {
    ShipmentNote aShipmentNote = shipmentNotes.get(index);
    return aShipmentNote;
  }

  public List<ShipmentNote> getShipmentNotes()
  {
    List<ShipmentNote> newShipmentNotes = Collections.unmodifiableList(shipmentNotes);
    return newShipmentNotes;
  }

  public int numberOfShipmentNotes()
  {
    int number = shipmentNotes.size();
    return number;
  }

  public boolean hasShipmentNotes()
  {
    boolean has = shipmentNotes.size() > 0;
    return has;
  }

  public int indexOfShipmentNote(ShipmentNote aShipmentNote)
  {
    int index = shipmentNotes.indexOf(aShipmentNote);
    return index;
  }
  /* Code from template association_GetMany */
  public ManagerTask getManagerTask(int index)
  {
    ManagerTask aManagerTask = managerTasks.get(index);
    return aManagerTask;
  }

  public List<ManagerTask> getManagerTasks()
  {
    List<ManagerTask> newManagerTasks = Collections.unmodifiableList(managerTasks);
    return newManagerTasks;
  }

  public int numberOfManagerTasks()
  {
    int number = managerTasks.size();
    return number;
  }

  public boolean hasManagerTasks()
  {
    boolean has = managerTasks.size() > 0;
    return has;
  }

  public int indexOfManagerTask(ManagerTask aManagerTask)
  {
    int index = managerTasks.indexOf(aManagerTask);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aPassword)
  {
    return new User(aPassword, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    WareFlow existingWareFlow = aUser.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aUser.setWareFlow(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a wareFlow
    if (!this.equals(aUser.getWareFlow()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRoles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addUserRole(UserRole aUserRole)
  {
    boolean wasAdded = false;
    if (userRoles.contains(aUserRole)) { return false; }
    WareFlow existingWareFlow = aUserRole.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aUserRole.setWareFlow(this);
    }
    else
    {
      userRoles.add(aUserRole);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserRole(UserRole aUserRole)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRole, as it must always have a wareFlow
    if (!this.equals(aUserRole.getWareFlow()))
    {
      userRoles.remove(aUserRole);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRoleAt(UserRole aUserRole, int index)
  {  
    boolean wasAdded = false;
    if(addUserRole(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRoleAt(UserRole aUserRole, int index)
  {
    boolean wasAdded = false;
    if(userRoles.contains(aUserRole))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRoles()) { index = numberOfUserRoles() - 1; }
      userRoles.remove(aUserRole);
      userRoles.add(index, aUserRole);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRoleAt(aUserRole, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShipmentOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ShipmentOrder addShipmentOrder(int aOrderNumber, String aDescription, String aShipmentAddress, String aReceiver, int aQuantity, OrderStatus aOrderStatus, ShipmentNote aNote, ManagerTask aManagerTask)
  {
    return new ShipmentOrder(aOrderNumber, aDescription, aShipmentAddress, aReceiver, aQuantity, aOrderStatus, this, aNote, aManagerTask);
  }

  public boolean addShipmentOrder(ShipmentOrder aShipmentOrder)
  {
    boolean wasAdded = false;
    if (shipmentOrders.contains(aShipmentOrder)) { return false; }
    WareFlow existingWareFlow = aShipmentOrder.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aShipmentOrder.setWareFlow(this);
    }
    else
    {
      shipmentOrders.add(aShipmentOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShipmentOrder(ShipmentOrder aShipmentOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aShipmentOrder, as it must always have a wareFlow
    if (!this.equals(aShipmentOrder.getWareFlow()))
    {
      shipmentOrders.remove(aShipmentOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShipmentOrderAt(ShipmentOrder aShipmentOrder, int index)
  {  
    boolean wasAdded = false;
    if(addShipmentOrder(aShipmentOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShipmentOrders()) { index = numberOfShipmentOrders() - 1; }
      shipmentOrders.remove(aShipmentOrder);
      shipmentOrders.add(index, aShipmentOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShipmentOrderAt(ShipmentOrder aShipmentOrder, int index)
  {
    boolean wasAdded = false;
    if(shipmentOrders.contains(aShipmentOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShipmentOrders()) { index = numberOfShipmentOrders() - 1; }
      shipmentOrders.remove(aShipmentOrder);
      shipmentOrders.add(index, aShipmentOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShipmentOrderAt(aShipmentOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAreas()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Area addArea(String aAreaName)
  {
    return new Area(aAreaName, this);
  }

  public boolean addArea(Area aArea)
  {
    boolean wasAdded = false;
    if (areas.contains(aArea)) { return false; }
    WareFlow existingWareFlow = aArea.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aArea.setWareFlow(this);
    }
    else
    {
      areas.add(aArea);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeArea(Area aArea)
  {
    boolean wasRemoved = false;
    //Unable to remove aArea, as it must always have a wareFlow
    if (!this.equals(aArea.getWareFlow()))
    {
      areas.remove(aArea);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAreaAt(Area aArea, int index)
  {  
    boolean wasAdded = false;
    if(addArea(aArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAreas()) { index = numberOfAreas() - 1; }
      areas.remove(aArea);
      areas.add(index, aArea);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAreaAt(Area aArea, int index)
  {
    boolean wasAdded = false;
    if(areas.contains(aArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAreas()) { index = numberOfAreas() - 1; }
      areas.remove(aArea);
      areas.add(index, aArea);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAreaAt(aArea, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContainers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Container addContainer(int aContainerNumber, Date aAddedDate, Item aItemsInContainer, ShipmentOrder aLinkedOrders, Area aContainerArea)
  {
    return new Container(aContainerNumber, aAddedDate, this, aItemsInContainer, aLinkedOrders, aContainerArea);
  }

  public boolean addContainer(Container aContainer)
  {
    boolean wasAdded = false;
    if (containers.contains(aContainer)) { return false; }
    WareFlow existingWareFlow = aContainer.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aContainer.setWareFlow(this);
    }
    else
    {
      containers.add(aContainer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContainer(Container aContainer)
  {
    boolean wasRemoved = false;
    //Unable to remove aContainer, as it must always have a wareFlow
    if (!this.equals(aContainer.getWareFlow()))
    {
      containers.remove(aContainer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addContainerAt(Container aContainer, int index)
  {  
    boolean wasAdded = false;
    if(addContainer(aContainer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainers()) { index = numberOfContainers() - 1; }
      containers.remove(aContainer);
      containers.add(index, aContainer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainerAt(Container aContainer, int index)
  {
    boolean wasAdded = false;
    if(containers.contains(aContainer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainers()) { index = numberOfContainers() - 1; }
      containers.remove(aContainer);
      containers.add(index, aContainer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainerAt(aContainer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aItemType, int aLifeSpan)
  {
    return new Item(aItemType, aLifeSpan, this);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    WareFlow existingWareFlow = aItem.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aItem.setWareFlow(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a wareFlow
    if (!this.equals(aItem.getWareFlow()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShipmentNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ShipmentNote addShipmentNote(Date aDate, String aNote, ShipmentOrder aOrderToBeNoted)
  {
    return new ShipmentNote(aDate, aNote, this, aOrderToBeNoted);
  }

  public boolean addShipmentNote(ShipmentNote aShipmentNote)
  {
    boolean wasAdded = false;
    if (shipmentNotes.contains(aShipmentNote)) { return false; }
    WareFlow existingWareFlow = aShipmentNote.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aShipmentNote.setWareFlow(this);
    }
    else
    {
      shipmentNotes.add(aShipmentNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeShipmentNote(ShipmentNote aShipmentNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aShipmentNote, as it must always have a wareFlow
    if (!this.equals(aShipmentNote.getWareFlow()))
    {
      shipmentNotes.remove(aShipmentNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addShipmentNoteAt(ShipmentNote aShipmentNote, int index)
  {  
    boolean wasAdded = false;
    if(addShipmentNote(aShipmentNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShipmentNotes()) { index = numberOfShipmentNotes() - 1; }
      shipmentNotes.remove(aShipmentNote);
      shipmentNotes.add(index, aShipmentNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveShipmentNoteAt(ShipmentNote aShipmentNote, int index)
  {
    boolean wasAdded = false;
    if(shipmentNotes.contains(aShipmentNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfShipmentNotes()) { index = numberOfShipmentNotes() - 1; }
      shipmentNotes.remove(aShipmentNote);
      shipmentNotes.add(index, aShipmentNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addShipmentNoteAt(aShipmentNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfManagerTasks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ManagerTask addManagerTask(boolean aRequiresManagerApproval, Priority aPriority, TimeEstimate aTimeEstimate, Manager aManager, ShipmentOrder aShipmentOrder)
  {
    return new ManagerTask(aRequiresManagerApproval, aPriority, aTimeEstimate, this, aManager, aShipmentOrder);
  }

  public boolean addManagerTask(ManagerTask aManagerTask)
  {
    boolean wasAdded = false;
    if (managerTasks.contains(aManagerTask)) { return false; }
    WareFlow existingWareFlow = aManagerTask.getWareFlow();
    boolean isNewWareFlow = existingWareFlow != null && !this.equals(existingWareFlow);
    if (isNewWareFlow)
    {
      aManagerTask.setWareFlow(this);
    }
    else
    {
      managerTasks.add(aManagerTask);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeManagerTask(ManagerTask aManagerTask)
  {
    boolean wasRemoved = false;
    //Unable to remove aManagerTask, as it must always have a wareFlow
    if (!this.equals(aManagerTask.getWareFlow()))
    {
      managerTasks.remove(aManagerTask);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addManagerTaskAt(ManagerTask aManagerTask, int index)
  {  
    boolean wasAdded = false;
    if(addManagerTask(aManagerTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfManagerTasks()) { index = numberOfManagerTasks() - 1; }
      managerTasks.remove(aManagerTask);
      managerTasks.add(index, aManagerTask);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveManagerTaskAt(ManagerTask aManagerTask, int index)
  {
    boolean wasAdded = false;
    if(managerTasks.contains(aManagerTask))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfManagerTasks()) { index = numberOfManagerTasks() - 1; }
      managerTasks.remove(aManagerTask);
      managerTasks.add(index, aManagerTask);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addManagerTaskAt(aManagerTask, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (userRoles.size() > 0)
    {
      UserRole aUserRole = userRoles.get(userRoles.size() - 1);
      aUserRole.delete();
      userRoles.remove(aUserRole);
    }
    
    while (shipmentOrders.size() > 0)
    {
      ShipmentOrder aShipmentOrder = shipmentOrders.get(shipmentOrders.size() - 1);
      aShipmentOrder.delete();
      shipmentOrders.remove(aShipmentOrder);
    }
    
    while (areas.size() > 0)
    {
      Area aArea = areas.get(areas.size() - 1);
      aArea.delete();
      areas.remove(aArea);
    }
    
    while (containers.size() > 0)
    {
      Container aContainer = containers.get(containers.size() - 1);
      aContainer.delete();
      containers.remove(aContainer);
    }
    
    while (items.size() > 0)
    {
      Item aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    while (shipmentNotes.size() > 0)
    {
      ShipmentNote aShipmentNote = shipmentNotes.get(shipmentNotes.size() - 1);
      aShipmentNote.delete();
      shipmentNotes.remove(aShipmentNote);
    }
    
    while (managerTasks.size() > 0)
    {
      ManagerTask aManagerTask = managerTasks.get(managerTasks.size() - 1);
      aManagerTask.delete();
      managerTasks.remove(aManagerTask);
    }
    
  }

}