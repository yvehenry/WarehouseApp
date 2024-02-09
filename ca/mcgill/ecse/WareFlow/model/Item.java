/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;
import java.sql.Date;

// line 35 "../../../../../WareFlow.ump"
// line 142 "../../../../../WareFlow.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String itemType;
  private int lifeSpan;

  //Item Associations
  private WareFlow wareFlow;
  private List<Container> container;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aItemType, int aLifeSpan, WareFlow aWareFlow)
  {
    itemType = aItemType;
    lifeSpan = aLifeSpan;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create item due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    container = new ArrayList<Container>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setItemType(String aItemType)
  {
    boolean wasSet = false;
    itemType = aItemType;
    wasSet = true;
    return wasSet;
  }

  public boolean setLifeSpan(int aLifeSpan)
  {
    boolean wasSet = false;
    lifeSpan = aLifeSpan;
    wasSet = true;
    return wasSet;
  }

  public String getItemType()
  {
    return itemType;
  }

  public int getLifeSpan()
  {
    return lifeSpan;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetMany */
  public Container getContainer(int index)
  {
    Container aContainer = container.get(index);
    return aContainer;
  }

  public List<Container> getContainer()
  {
    List<Container> newContainer = Collections.unmodifiableList(container);
    return newContainer;
  }

  public int numberOfContainer()
  {
    int number = container.size();
    return number;
  }

  public boolean hasContainer()
  {
    boolean has = container.size() > 0;
    return has;
  }

  public int indexOfContainer(Container aContainer)
  {
    int index = container.indexOf(aContainer);
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
      existingWareFlow.removeItem(this);
    }
    wareFlow.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfContainer()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Container addContainer(int aContainerNumber, Date aAddedDate, WareFlow aWareFlow, ShipmentOrder aLinkedOrders, Area aContainerArea)
  {
    return new Container(aContainerNumber, aAddedDate, aWareFlow, this, aLinkedOrders, aContainerArea);
  }

  public boolean addContainer(Container aContainer)
  {
    boolean wasAdded = false;
    if (container.contains(aContainer)) { return false; }
    Item existingItemsInContainer = aContainer.getItemsInContainer();
    boolean isNewItemsInContainer = existingItemsInContainer != null && !this.equals(existingItemsInContainer);
    if (isNewItemsInContainer)
    {
      aContainer.setItemsInContainer(this);
    }
    else
    {
      container.add(aContainer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeContainer(Container aContainer)
  {
    boolean wasRemoved = false;
    //Unable to remove aContainer, as it must always have a itemsInContainer
    if (!this.equals(aContainer.getItemsInContainer()))
    {
      container.remove(aContainer);
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
      if(index > numberOfContainer()) { index = numberOfContainer() - 1; }
      container.remove(aContainer);
      container.add(index, aContainer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveContainerAt(Container aContainer, int index)
  {
    boolean wasAdded = false;
    if(container.contains(aContainer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfContainer()) { index = numberOfContainer() - 1; }
      container.remove(aContainer);
      container.add(index, aContainer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addContainerAt(aContainer, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeItem(this);
    }
    for(int i=container.size(); i > 0; i--)
    {
      Container aContainer = container.get(i - 1);
      aContainer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "itemType" + ":" + getItemType()+ "," +
            "lifeSpan" + ":" + getLifeSpan()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null");
  }
}