/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;

// line 71 "../../../../../WareFlow.ump"
// line 132 "../../../../../WareFlow.ump"
public class Client extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Client Attributes
  private String address;

  //Client Associations
  private List<ShipmentOrder> ordersOpened;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Client(WareFlow aWareFlow, User aUser, String aAddress)
  {
    super(aWareFlow, aUser);
    address = aAddress;
    ordersOpened = new ArrayList<ShipmentOrder>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getAddress()
  {
    return address;
  }
  /* Code from template association_GetMany */
  public ShipmentOrder getOrdersOpened(int index)
  {
    ShipmentOrder aOrdersOpened = ordersOpened.get(index);
    return aOrdersOpened;
  }

  public List<ShipmentOrder> getOrdersOpened()
  {
    List<ShipmentOrder> newOrdersOpened = Collections.unmodifiableList(ordersOpened);
    return newOrdersOpened;
  }

  public int numberOfOrdersOpened()
  {
    int number = ordersOpened.size();
    return number;
  }

  public boolean hasOrdersOpened()
  {
    boolean has = ordersOpened.size() > 0;
    return has;
  }

  public int indexOfOrdersOpened(ShipmentOrder aOrdersOpened)
  {
    int index = ordersOpened.indexOf(aOrdersOpened);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrdersOpened()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOrdersOpened(ShipmentOrder aOrdersOpened)
  {
    boolean wasAdded = false;
    if (ordersOpened.contains(aOrdersOpened)) { return false; }
    ordersOpened.add(aOrdersOpened);
    if (aOrdersOpened.indexOfOrderClient(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrdersOpened.addOrderClient(this);
      if (!wasAdded)
      {
        ordersOpened.remove(aOrdersOpened);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOrdersOpened(ShipmentOrder aOrdersOpened)
  {
    boolean wasRemoved = false;
    if (!ordersOpened.contains(aOrdersOpened))
    {
      return wasRemoved;
    }

    int oldIndex = ordersOpened.indexOf(aOrdersOpened);
    ordersOpened.remove(oldIndex);
    if (aOrdersOpened.indexOfOrderClient(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrdersOpened.removeOrderClient(this);
      if (!wasRemoved)
      {
        ordersOpened.add(oldIndex,aOrdersOpened);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrdersOpenedAt(ShipmentOrder aOrdersOpened, int index)
  {  
    boolean wasAdded = false;
    if(addOrdersOpened(aOrdersOpened))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrdersOpened()) { index = numberOfOrdersOpened() - 1; }
      ordersOpened.remove(aOrdersOpened);
      ordersOpened.add(index, aOrdersOpened);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrdersOpenedAt(ShipmentOrder aOrdersOpened, int index)
  {
    boolean wasAdded = false;
    if(ordersOpened.contains(aOrdersOpened))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrdersOpened()) { index = numberOfOrdersOpened() - 1; }
      ordersOpened.remove(aOrdersOpened);
      ordersOpened.add(index, aOrdersOpened);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrdersOpenedAt(aOrdersOpened, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<ShipmentOrder> copyOfOrdersOpened = new ArrayList<ShipmentOrder>(ordersOpened);
    ordersOpened.clear();
    for(ShipmentOrder aOrdersOpened : copyOfOrdersOpened)
    {
      aOrdersOpened.removeOrderClient(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "address" + ":" + getAddress()+ "]";
  }
}