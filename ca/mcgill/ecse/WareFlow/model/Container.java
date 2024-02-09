/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;
import java.sql.Date;

// line 57 "../../../../../WareFlow.ump"
// line 117 "../../../../../WareFlow.ump"
public class Container
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<int, Container> containersByContainerNumber = new HashMap<int, Container>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Container Attributes
  private int containerNumber;
  private Date addedDate;

  //Container Associations
  private WareFlow wareFlow;
  private Item itemsInContainer;
  private ShipmentOrder linkedOrders;
  private Area containerArea;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Container(int aContainerNumber, Date aAddedDate, WareFlow aWareFlow, Item aItemsInContainer, ShipmentOrder aLinkedOrders, Area aContainerArea)
  {
    addedDate = aAddedDate;
    if (!setContainerNumber(aContainerNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate containerNumber. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create container due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddItemsInContainer = setItemsInContainer(aItemsInContainer);
    if (!didAddItemsInContainer)
    {
      throw new RuntimeException("Unable to create container due to itemsInContainer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddLinkedOrders = setLinkedOrders(aLinkedOrders);
    if (!didAddLinkedOrders)
    {
      throw new RuntimeException("Unable to create containersPerOrder due to linkedOrders. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddContainerArea = setContainerArea(aContainerArea);
    if (!didAddContainerArea)
    {
      throw new RuntimeException("Unable to create containerInArea due to containerArea. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setContainerNumber(int aContainerNumber)
  {
    boolean wasSet = false;
    int anOldContainerNumber = getContainerNumber();
    if (anOldContainerNumber != null && anOldContainerNumber.equals(aContainerNumber)) {
      return true;
    }
    if (hasWithContainerNumber(aContainerNumber)) {
      return wasSet;
    }
    containerNumber = aContainerNumber;
    wasSet = true;
    if (anOldContainerNumber != null) {
      containersByContainerNumber.remove(anOldContainerNumber);
    }
    containersByContainerNumber.put(aContainerNumber, this);
    return wasSet;
  }

  public boolean setAddedDate(Date aAddedDate)
  {
    boolean wasSet = false;
    addedDate = aAddedDate;
    wasSet = true;
    return wasSet;
  }

  public int getContainerNumber()
  {
    return containerNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Container getWithContainerNumber(int aContainerNumber)
  {
    return containersByContainerNumber.get(aContainerNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithContainerNumber(int aContainerNumber)
  {
    return getWithContainerNumber(aContainerNumber) != null;
  }

  public Date getAddedDate()
  {
    return addedDate;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetOne */
  public Item getItemsInContainer()
  {
    return itemsInContainer;
  }
  /* Code from template association_GetOne */
  public ShipmentOrder getLinkedOrders()
  {
    return linkedOrders;
  }
  /* Code from template association_GetOne */
  public Area getContainerArea()
  {
    return containerArea;
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
      existingWareFlow.removeContainer(this);
    }
    wareFlow.addContainer(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setItemsInContainer(Item aItemsInContainer)
  {
    boolean wasSet = false;
    if (aItemsInContainer == null)
    {
      return wasSet;
    }

    Item existingItemsInContainer = itemsInContainer;
    itemsInContainer = aItemsInContainer;
    if (existingItemsInContainer != null && !existingItemsInContainer.equals(aItemsInContainer))
    {
      existingItemsInContainer.removeContainer(this);
    }
    itemsInContainer.addContainer(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setLinkedOrders(ShipmentOrder aLinkedOrders)
  {
    boolean wasSet = false;
    if (aLinkedOrders == null)
    {
      return wasSet;
    }

    ShipmentOrder existingLinkedOrders = linkedOrders;
    linkedOrders = aLinkedOrders;
    if (existingLinkedOrders != null && !existingLinkedOrders.equals(aLinkedOrders))
    {
      existingLinkedOrders.removeContainersPerOrder(this);
    }
    linkedOrders.addContainersPerOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setContainerArea(Area aContainerArea)
  {
    boolean wasSet = false;
    if (aContainerArea == null)
    {
      return wasSet;
    }

    Area existingContainerArea = containerArea;
    containerArea = aContainerArea;
    if (existingContainerArea != null && !existingContainerArea.equals(aContainerArea))
    {
      existingContainerArea.removeContainerInArea(this);
    }
    containerArea.addContainerInArea(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    containersByContainerNumber.remove(getContainerNumber());
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeContainer(this);
    }
    Item placeholderItemsInContainer = itemsInContainer;
    this.itemsInContainer = null;
    if(placeholderItemsInContainer != null)
    {
      placeholderItemsInContainer.removeContainer(this);
    }
    ShipmentOrder placeholderLinkedOrders = linkedOrders;
    this.linkedOrders = null;
    if(placeholderLinkedOrders != null)
    {
      placeholderLinkedOrders.removeContainersPerOrder(this);
    }
    Area placeholderContainerArea = containerArea;
    this.containerArea = null;
    if(placeholderContainerArea != null)
    {
      placeholderContainerArea.removeContainerInArea(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "containerNumber" + ":" + getContainerNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "addedDate" + "=" + (getAddedDate() != null ? !getAddedDate().equals(this)  ? getAddedDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "itemsInContainer = "+(getItemsInContainer()!=null?Integer.toHexString(System.identityHashCode(getItemsInContainer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "linkedOrders = "+(getLinkedOrders()!=null?Integer.toHexString(System.identityHashCode(getLinkedOrders())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "containerArea = "+(getContainerArea()!=null?Integer.toHexString(System.identityHashCode(getContainerArea())):"null");
  }
}