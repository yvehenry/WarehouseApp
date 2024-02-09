/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;
import java.sql.Date;

// line 26 "../../../../../WareFlow.ump"
public class Area
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Area Attributes
  private String areaName;

  //Area Associations
  private WareFlow wareFlow;
  private List<Container> containerInArea;
  private List<Slot> slots;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Area(String aAreaName, WareFlow aWareFlow)
  {
    areaName = aAreaName;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create area due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    containerInArea = new ArrayList<Container>();
    slots = new ArrayList<Slot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAreaName(String aAreaName)
  {
    boolean wasSet = false;
    areaName = aAreaName;
    wasSet = true;
    return wasSet;
  }

  public String getAreaName()
  {
    return areaName;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetMany */
  public Container getContainerInArea(int index)
  {
    Container aContainerInArea = containerInArea.get(index);
    return aContainerInArea;
  }

  public List<Container> getContainerInArea()
  {
    List<Container> newContainerInArea = Collections.unmodifiableList(containerInArea);
    return newContainerInArea;
  }

  public int numberOfContainerInArea()
  {
    int number = containerInArea.size();
    return number;
  }

  public boolean hasContainerInArea()
  {
    boolean has = containerInArea.size() > 0;
    return has;
  }

  public int indexOfContainerInArea(Container aContainerInArea)
  {
    int index = containerInArea.indexOf(aContainerInArea);
    return index;
  }
  /* Code from template association_GetMany */
  public Slot getSlot(int index)
  {
    Slot aSlot = slots.get(index);
    return aSlot;
  }

  public List<Slot> getSlots()
  {
    List<Slot> newSlots = Collections.unmodifiableList(slots);
    return newSlots;
  }

  public int numberOfSlots()
  {
    int number = slots.size();
    return number;
  }

  public boolean hasSlots()
  {
    boolean has = slots.size() > 0;
    return has;
  }

  public int indexOfSlot(Slot aSlot)
  {
    int index = slots.indexOf(aSlot);
    return index;
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
      existingWareFlow.removeArea(this);
    }
    wareFlow.addArea(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContainerInArea()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Container addContainerInArea(int aContainerNumber, Date aAddedDate, WareFlow aWareFlow, Item aItemsInContainer, ShipmentOrder aLinkedOrders)
  {
    return new Container(aContainerNumber, aAddedDate, aWareFlow, aItemsInContainer, aLinkedOrders, this);
  }

  public boolean addContainerInArea(Container aContainerInArea)
  {
    boolean wasAdded = false;
    if (containerInArea.contains(aContainerInArea)) { return false; }
    Area existingContainerArea = aContainerInArea.getContainerArea();
    boolean isNewContainerArea = existingContainerArea != null && !this.equals(existingContainerArea);
    if (isNewContainerArea)
    {
      aContainerInArea.setContainerArea(this);
    }
    else
    {
      containerInArea.add(aContainerInArea);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContainerInArea(Container aContainerInArea)
  {
    boolean wasRemoved = false;
    //Unable to remove aContainerInArea, as it must always have a containerArea
    if (!this.equals(aContainerInArea.getContainerArea()))
    {
      containerInArea.remove(aContainerInArea);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addContainerInAreaAt(Container aContainerInArea, int index)
  {  
    boolean wasAdded = false;
    if(addContainerInArea(aContainerInArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainerInArea()) { index = numberOfContainerInArea() - 1; }
      containerInArea.remove(aContainerInArea);
      containerInArea.add(index, aContainerInArea);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainerInAreaAt(Container aContainerInArea, int index)
  {
    boolean wasAdded = false;
    if(containerInArea.contains(aContainerInArea))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainerInArea()) { index = numberOfContainerInArea() - 1; }
      containerInArea.remove(aContainerInArea);
      containerInArea.add(index, aContainerInArea);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainerInAreaAt(aContainerInArea, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Slot addSlot(int aSlotNo)
  {
    return new Slot(aSlotNo, this);
  }

  public boolean addSlot(Slot aSlot)
  {
    boolean wasAdded = false;
    if (slots.contains(aSlot)) { return false; }
    Area existingSlotArea = aSlot.getSlotArea();
    boolean isNewSlotArea = existingSlotArea != null && !this.equals(existingSlotArea);
    if (isNewSlotArea)
    {
      aSlot.setSlotArea(this);
    }
    else
    {
      slots.add(aSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSlot(Slot aSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aSlot, as it must always have a slotArea
    if (!this.equals(aSlot.getSlotArea()))
    {
      slots.remove(aSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSlotAt(Slot aSlot, int index)
  {  
    boolean wasAdded = false;
    if(addSlot(aSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSlots()) { index = numberOfSlots() - 1; }
      slots.remove(aSlot);
      slots.add(index, aSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSlotAt(Slot aSlot, int index)
  {
    boolean wasAdded = false;
    if(slots.contains(aSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSlots()) { index = numberOfSlots() - 1; }
      slots.remove(aSlot);
      slots.add(index, aSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSlotAt(aSlot, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeArea(this);
    }
    for(int i=containerInArea.size(); i > 0; i--)
    {
      Container aContainerInArea = containerInArea.get(i - 1);
      aContainerInArea.delete();
    }
    while (slots.size() > 0)
    {
      Slot aSlot = slots.get(slots.size() - 1);
      aSlot.delete();
      slots.remove(aSlot);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "areaName" + ":" + getAreaName()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null");
  }
}