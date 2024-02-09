/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.sql.Date;
import java.util.*;

// line 62 "../../../../../WareFlow.ump"
// line 122 "../../../../../WareFlow.ump"
public class ShipmentNote
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShipmentNote Attributes
  private Date date;
  private String note;

  //ShipmentNote Associations
  private WareFlow wareFlow;
  private List<Employee> noteAuthors;
  private ShipmentOrder orderToBeNoted;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShipmentNote(Date aDate, String aNote, WareFlow aWareFlow, ShipmentOrder aOrderToBeNoted)
  {
    date = aDate;
    note = aNote;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create shipmentNote due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    noteAuthors = new ArrayList<Employee>();
    if (aOrderToBeNoted == null || aOrderToBeNoted.getNote() != null)
    {
      throw new RuntimeException("Unable to create ShipmentNote due to aOrderToBeNoted. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    orderToBeNoted = aOrderToBeNoted;
  }

  public ShipmentNote(Date aDate, String aNote, WareFlow aWareFlow, int aOrderNumberForOrderToBeNoted, String aDescriptionForOrderToBeNoted, String aShipmentAddressForOrderToBeNoted, String aReceiverForOrderToBeNoted, int aQuantityForOrderToBeNoted, OrderStatus aOrderStatusForOrderToBeNoted, WareFlow aWareFlowForOrderToBeNoted, ManagerTask aManagerTaskForOrderToBeNoted)
  {
    date = aDate;
    note = aNote;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create shipmentNote due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    noteAuthors = new ArrayList<Employee>();
    orderToBeNoted = new ShipmentOrder(aOrderNumberForOrderToBeNoted, aDescriptionForOrderToBeNoted, aShipmentAddressForOrderToBeNoted, aReceiverForOrderToBeNoted, aQuantityForOrderToBeNoted, aOrderStatusForOrderToBeNoted, aWareFlowForOrderToBeNoted, this, aManagerTaskForOrderToBeNoted);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setNote(String aNote)
  {
    boolean wasSet = false;
    note = aNote;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public String getNote()
  {
    return note;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetMany */
  public Employee getNoteAuthor(int index)
  {
    Employee aNoteAuthor = noteAuthors.get(index);
    return aNoteAuthor;
  }

  public List<Employee> getNoteAuthors()
  {
    List<Employee> newNoteAuthors = Collections.unmodifiableList(noteAuthors);
    return newNoteAuthors;
  }

  public int numberOfNoteAuthors()
  {
    int number = noteAuthors.size();
    return number;
  }

  public boolean hasNoteAuthors()
  {
    boolean has = noteAuthors.size() > 0;
    return has;
  }

  public int indexOfNoteAuthor(Employee aNoteAuthor)
  {
    int index = noteAuthors.indexOf(aNoteAuthor);
    return index;
  }
  /* Code from template association_GetOne */
  public ShipmentOrder getOrderToBeNoted()
  {
    return orderToBeNoted;
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
      existingWareFlow.removeShipmentNote(this);
    }
    wareFlow.addShipmentNote(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNoteAuthors()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addNoteAuthor(Employee aNoteAuthor)
  {
    boolean wasAdded = false;
    if (noteAuthors.contains(aNoteAuthor)) { return false; }
    noteAuthors.add(aNoteAuthor);
    if (aNoteAuthor.indexOfNotesWritten(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aNoteAuthor.addNotesWritten(this);
      if (!wasAdded)
      {
        noteAuthors.remove(aNoteAuthor);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeNoteAuthor(Employee aNoteAuthor)
  {
    boolean wasRemoved = false;
    if (!noteAuthors.contains(aNoteAuthor))
    {
      return wasRemoved;
    }

    int oldIndex = noteAuthors.indexOf(aNoteAuthor);
    noteAuthors.remove(oldIndex);
    if (aNoteAuthor.indexOfNotesWritten(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aNoteAuthor.removeNotesWritten(this);
      if (!wasRemoved)
      {
        noteAuthors.add(oldIndex,aNoteAuthor);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNoteAuthorAt(Employee aNoteAuthor, int index)
  {  
    boolean wasAdded = false;
    if(addNoteAuthor(aNoteAuthor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNoteAuthors()) { index = numberOfNoteAuthors() - 1; }
      noteAuthors.remove(aNoteAuthor);
      noteAuthors.add(index, aNoteAuthor);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAuthorAt(Employee aNoteAuthor, int index)
  {
    boolean wasAdded = false;
    if(noteAuthors.contains(aNoteAuthor))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNoteAuthors()) { index = numberOfNoteAuthors() - 1; }
      noteAuthors.remove(aNoteAuthor);
      noteAuthors.add(index, aNoteAuthor);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAuthorAt(aNoteAuthor, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeShipmentNote(this);
    }
    ArrayList<Employee> copyOfNoteAuthors = new ArrayList<Employee>(noteAuthors);
    noteAuthors.clear();
    for(Employee aNoteAuthor : copyOfNoteAuthors)
    {
      aNoteAuthor.removeNotesWritten(this);
    }
    ShipmentOrder existingOrderToBeNoted = orderToBeNoted;
    orderToBeNoted = null;
    if (existingOrderToBeNoted != null)
    {
      existingOrderToBeNoted.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "note" + ":" + getNote()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderToBeNoted = "+(getOrderToBeNoted()!=null?Integer.toHexString(System.identityHashCode(getOrderToBeNoted())):"null");
  }
}