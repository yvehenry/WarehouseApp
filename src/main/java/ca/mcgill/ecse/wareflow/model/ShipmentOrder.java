/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/

package ca.mcgill.ecse.wareflow.model;
import java.util.*;
import java.sql.Date;

// line 44 "../../../../../WareFlow.ump"
public class ShipmentOrder
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum TimeEstimate { LessThanADay, OneToThreeDays, ThreeToSevenDays, OneToThreeWeeks, ThreeOrMoreWeeks }
  public enum PriorityLevel { Urgent, Normal, Low }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, ShipmentOrder> shipmentordersById = new HashMap<Integer, ShipmentOrder>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShipmentOrder Attributes
  private int id;
  private Date placedOnDate;
  private String description;
  private int quantity;
  private TimeEstimate timeToFullfill;
  private PriorityLevel priority;

  //ShipmentOrder Associations
  private List<ShipmentNote> shipmentNotes;
  private WareFlow wareFlow;
  private User orderPlacer;
  private WarehouseStaff orderPicker;
  private ItemContainer container;
  private Manager orderApprover;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShipmentOrder(int aId, Date aPlacedOnDate, String aDescription, int aQuantity, WareFlow aWareFlow, User aOrderPlacer)
  {
    placedOnDate = aPlacedOnDate;
    description = aDescription;
    quantity = aQuantity;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    shipmentNotes = new ArrayList<ShipmentNote>();
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create order due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddOrderPlacer = setOrderPlacer(aOrderPlacer);
    if (!didAddOrderPlacer)
    {
      throw new RuntimeException("Unable to create placedOrder due to orderPlacer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    Integer anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      shipmentordersById.remove(anOldId);
    }
    shipmentordersById.put(aId, this);
    return wasSet;
  }

  public boolean setPlacedOnDate(Date aPlacedOnDate)
  {
    boolean wasSet = false;
    placedOnDate = aPlacedOnDate;
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

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeToFullfill(TimeEstimate aTimeToFullfill)
  {
    boolean wasSet = false;
    timeToFullfill = aTimeToFullfill;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriority(PriorityLevel aPriority)
  {
    boolean wasSet = false;
    priority = aPriority;
    wasSet = true;
    return wasSet;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static ShipmentOrder getWithId(int aId)
  {
    return shipmentordersById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(int aId)
  {
    return getWithId(aId) != null;
  }

  public Date getPlacedOnDate()
  {
    return placedOnDate;
  }

  public String getDescription()
  {
    return description;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public TimeEstimate getTimeToFullfill()
  {
    return timeToFullfill;
  }

  public PriorityLevel getPriority()
  {
    return priority;
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
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetOne */
  public User getOrderPlacer()
  {
    return orderPlacer;
  }
  /* Code from template association_GetOne */
  public WarehouseStaff getOrderPicker()
  {
    return orderPicker;
  }

  public boolean hasOrderPicker()
  {
    boolean has = orderPicker != null;
    return has;
  }
  /* Code from template association_GetOne */
  public ItemContainer getContainer()
  {
    return container;
  }

  public boolean hasContainer()
  {
    boolean has = container != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Manager getOrderApprover()
  {
    return orderApprover;
  }

  public boolean hasOrderApprover()
  {
    boolean has = orderApprover != null;
    return has;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfShipmentNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ShipmentNote addShipmentNote(Date aDate, String aDescription, WarehouseStaff aNoteTaker)
  {
    return new ShipmentNote(aDate, aDescription, this, aNoteTaker);
  }

  public boolean addShipmentNote(ShipmentNote aShipmentNote)
  {
    boolean wasAdded = false;
    if (shipmentNotes.contains(aShipmentNote)) { return false; }
    ShipmentOrder existingOrder = aShipmentNote.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aShipmentNote.setOrder(this);
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
    //Unable to remove aShipmentNote, as it must always have a order
    if (!this.equals(aShipmentNote.getOrder()))
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
      existingWareFlow.removeOrder(this);
    }
    wareFlow.addOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrderPlacer(User aOrderPlacer)
  {
    boolean wasSet = false;
    if (aOrderPlacer == null)
    {
      return wasSet;
    }

    User existingOrderPlacer = orderPlacer;
    orderPlacer = aOrderPlacer;
    if (existingOrderPlacer != null && !existingOrderPlacer.equals(aOrderPlacer))
    {
      existingOrderPlacer.removePlacedOrder(this);
    }
    orderPlacer.addPlacedOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setOrderPicker(WarehouseStaff aOrderPicker)
  {
    boolean wasSet = false;
    WarehouseStaff existingOrderPicker = orderPicker;
    orderPicker = aOrderPicker;
    if (existingOrderPicker != null && !existingOrderPicker.equals(aOrderPicker))
    {
      existingOrderPicker.removeShipmentOrder(this);
    }
    if (aOrderPicker != null)
    {
      aOrderPicker.addShipmentOrder(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setContainer(ItemContainer aContainer)
  {
    boolean wasSet = false;
    ItemContainer existingContainer = container;
    container = aContainer;
    if (existingContainer != null && !existingContainer.equals(aContainer))
    {
      existingContainer.removeShipmentOrder(this);
    }
    if (aContainer != null)
    {
      aContainer.addShipmentOrder(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToMany */
  public boolean setOrderApprover(Manager aOrderApprover)
  {
    boolean wasSet = false;
    Manager existingOrderApprover = orderApprover;
    orderApprover = aOrderApprover;
    if (existingOrderApprover != null && !existingOrderApprover.equals(aOrderApprover))
    {
      existingOrderApprover.removeOrdersForApproval(this);
    }
    if (aOrderApprover != null)
    {
      aOrderApprover.addOrdersForApproval(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    shipmentordersById.remove(getId());
    while (shipmentNotes.size() > 0)
    {
      ShipmentNote aShipmentNote = shipmentNotes.get(shipmentNotes.size() - 1);
      aShipmentNote.delete();
      shipmentNotes.remove(aShipmentNote);
    }
    
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeOrder(this);
    }
    User placeholderOrderPlacer = orderPlacer;
    this.orderPlacer = null;
    if(placeholderOrderPlacer != null)
    {
      placeholderOrderPlacer.removePlacedOrder(this);
    }
    if (orderPicker != null)
    {
      WarehouseStaff placeholderOrderPicker = orderPicker;
      this.orderPicker = null;
      placeholderOrderPicker.removeShipmentOrder(this);
    }
    if (container != null)
    {
      ItemContainer placeholderContainer = container;
      this.container = null;
      placeholderContainer.removeShipmentOrder(this);
    }
    if (orderApprover != null)
    {
      Manager placeholderOrderApprover = orderApprover;
      this.orderApprover = null;
      placeholderOrderApprover.removeOrdersForApproval(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "description" + ":" + getDescription()+ "," +
            "quantity" + ":" + getQuantity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "placedOnDate" + "=" + (getPlacedOnDate() != null ? !getPlacedOnDate().equals(this)  ? getPlacedOnDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeToFullfill" + "=" + (getTimeToFullfill() != null ? !getTimeToFullfill().equals(this)  ? getTimeToFullfill().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "priority" + "=" + (getPriority() != null ? !getPriority().equals(this)  ? getPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderPlacer = "+(getOrderPlacer()!=null?Integer.toHexString(System.identityHashCode(getOrderPlacer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderPicker = "+(getOrderPicker()!=null?Integer.toHexString(System.identityHashCode(getOrderPicker())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "container = "+(getContainer()!=null?Integer.toHexString(System.identityHashCode(getContainer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderApprover = "+(getOrderApprover()!=null?Integer.toHexString(System.identityHashCode(getOrderApprover())):"null");
  }
}