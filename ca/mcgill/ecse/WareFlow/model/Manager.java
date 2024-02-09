/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;

// line 76 "../../../../../WareFlow.ump"
// line 137 "../../../../../WareFlow.ump"
public class Manager extends Employee
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Priority { URGENT, NORMAL, LOW }
  public enum TimeEstimate { LESS_THAN_A_DAY, ONE_TO_THREE_DAYS, THREE_TO_SEVEN_DAYS, ONE_TO_THREE_WEEKS, MORE_THAN_THREE_WEEKS }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String password;
  private String username;

  //Manager Associations
  private List<ManagerTask> managerTasks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(WareFlow aWareFlow, User aUser)
  {
    super(aWareFlow, aUser);
    password = "manager";
    username = "manager";
    managerTasks = new ArrayList<ManagerTask>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    username = aUsername;
    wasSet = true;
    return wasSet;
  }

  public String getPassword()
  {
    return password;
  }

  public String getUsername()
  {
    return username;
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
  public static int minimumNumberOfManagerTasks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ManagerTask addManagerTask(boolean aRequiresManagerApproval, Priority aPriority, TimeEstimate aTimeEstimate, WareFlow aWareFlow, ShipmentOrder aShipmentOrder)
  {
    return new ManagerTask(aRequiresManagerApproval, aPriority, aTimeEstimate, aWareFlow, this, aShipmentOrder);
  }

  public boolean addManagerTask(ManagerTask aManagerTask)
  {
    boolean wasAdded = false;
    if (managerTasks.contains(aManagerTask)) { return false; }
    Manager existingManager = aManagerTask.getManager();
    boolean isNewManager = existingManager != null && !this.equals(existingManager);
    if (isNewManager)
    {
      aManagerTask.setManager(this);
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
    //Unable to remove aManagerTask, as it must always have a manager
    if (!this.equals(aManagerTask.getManager()))
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
    for(int i=managerTasks.size(); i > 0; i--)
    {
      ManagerTask aManagerTask = managerTasks.get(i - 1);
      aManagerTask.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "," +
            "username" + ":" + getUsername()+ "]";
  }
}