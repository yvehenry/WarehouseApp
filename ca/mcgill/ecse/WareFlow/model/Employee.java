/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;

// line 67 "../../../../../WareFlow.ump"
// line 127 "../../../../../WareFlow.ump"
public class Employee extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Associations
  private List<ShipmentOrder> assignedOrders;
  private List<ShipmentNote> notesWritten;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Employee(WareFlow aWareFlow, User aUser)
  {
    super(aWareFlow, aUser);
    assignedOrders = new ArrayList<ShipmentOrder>();
    notesWritten = new ArrayList<ShipmentNote>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public ShipmentOrder getAssignedOrder(int index)
  {
    ShipmentOrder aAssignedOrder = assignedOrders.get(index);
    return aAssignedOrder;
  }

  public List<ShipmentOrder> getAssignedOrders()
  {
    List<ShipmentOrder> newAssignedOrders = Collections.unmodifiableList(assignedOrders);
    return newAssignedOrders;
  }

  public int numberOfAssignedOrders()
  {
    int number = assignedOrders.size();
    return number;
  }

  public boolean hasAssignedOrders()
  {
    boolean has = assignedOrders.size() > 0;
    return has;
  }

  public int indexOfAssignedOrder(ShipmentOrder aAssignedOrder)
  {
    int index = assignedOrders.indexOf(aAssignedOrder);
    return index;
  }
  /* Code from template association_GetMany */
  public ShipmentNote getNotesWritten(int index)
  {
    ShipmentNote aNotesWritten = notesWritten.get(index);
    return aNotesWritten;
  }

  public List<ShipmentNote> getNotesWritten()
  {
    List<ShipmentNote> newNotesWritten = Collections.unmodifiableList(notesWritten);
    return newNotesWritten;
  }

  public int numberOfNotesWritten()
  {
    int number = notesWritten.size();
    return number;
  }

  public boolean hasNotesWritten()
  {
    boolean has = notesWritten.size() > 0;
    return has;
  }

  public int indexOfNotesWritten(ShipmentNote aNotesWritten)
  {
    int index = notesWritten.indexOf(aNotesWritten);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignedOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAssignedOrder(ShipmentOrder aAssignedOrder)
  {
    boolean wasAdded = false;
    if (assignedOrders.contains(aAssignedOrder)) { return false; }
    assignedOrders.add(aAssignedOrder);
    if (aAssignedOrder.indexOfAssignedEmployee(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAssignedOrder.addAssignedEmployee(this);
      if (!wasAdded)
      {
        assignedOrders.remove(aAssignedOrder);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAssignedOrder(ShipmentOrder aAssignedOrder)
  {
    boolean wasRemoved = false;
    if (!assignedOrders.contains(aAssignedOrder))
    {
      return wasRemoved;
    }

    int oldIndex = assignedOrders.indexOf(aAssignedOrder);
    assignedOrders.remove(oldIndex);
    if (aAssignedOrder.indexOfAssignedEmployee(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAssignedOrder.removeAssignedEmployee(this);
      if (!wasRemoved)
      {
        assignedOrders.add(oldIndex,aAssignedOrder);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignedOrderAt(ShipmentOrder aAssignedOrder, int index)
  {  
    boolean wasAdded = false;
    if(addAssignedOrder(aAssignedOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedOrders()) { index = numberOfAssignedOrders() - 1; }
      assignedOrders.remove(aAssignedOrder);
      assignedOrders.add(index, aAssignedOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignedOrderAt(ShipmentOrder aAssignedOrder, int index)
  {
    boolean wasAdded = false;
    if(assignedOrders.contains(aAssignedOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignedOrders()) { index = numberOfAssignedOrders() - 1; }
      assignedOrders.remove(aAssignedOrder);
      assignedOrders.add(index, aAssignedOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignedOrderAt(aAssignedOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotesWritten()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addNotesWritten(ShipmentNote aNotesWritten)
  {
    boolean wasAdded = false;
    if (notesWritten.contains(aNotesWritten)) { return false; }
    notesWritten.add(aNotesWritten);
    if (aNotesWritten.indexOfNoteAuthor(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aNotesWritten.addNoteAuthor(this);
      if (!wasAdded)
      {
        notesWritten.remove(aNotesWritten);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeNotesWritten(ShipmentNote aNotesWritten)
  {
    boolean wasRemoved = false;
    if (!notesWritten.contains(aNotesWritten))
    {
      return wasRemoved;
    }

    int oldIndex = notesWritten.indexOf(aNotesWritten);
    notesWritten.remove(oldIndex);
    if (aNotesWritten.indexOfNoteAuthor(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aNotesWritten.removeNoteAuthor(this);
      if (!wasRemoved)
      {
        notesWritten.add(oldIndex,aNotesWritten);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNotesWrittenAt(ShipmentNote aNotesWritten, int index)
  {  
    boolean wasAdded = false;
    if(addNotesWritten(aNotesWritten))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotesWritten()) { index = numberOfNotesWritten() - 1; }
      notesWritten.remove(aNotesWritten);
      notesWritten.add(index, aNotesWritten);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNotesWrittenAt(ShipmentNote aNotesWritten, int index)
  {
    boolean wasAdded = false;
    if(notesWritten.contains(aNotesWritten))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotesWritten()) { index = numberOfNotesWritten() - 1; }
      notesWritten.remove(aNotesWritten);
      notesWritten.add(index, aNotesWritten);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNotesWrittenAt(aNotesWritten, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<ShipmentOrder> copyOfAssignedOrders = new ArrayList<ShipmentOrder>(assignedOrders);
    assignedOrders.clear();
    for(ShipmentOrder aAssignedOrder : copyOfAssignedOrders)
    {
      aAssignedOrder.removeAssignedEmployee(this);
    }
    ArrayList<ShipmentNote> copyOfNotesWritten = new ArrayList<ShipmentNote>(notesWritten);
    notesWritten.clear();
    for(ShipmentNote aNotesWritten : copyOfNotesWritten)
    {
      aNotesWritten.removeNoteAuthor(this);
    }
    super.delete();
  }

}