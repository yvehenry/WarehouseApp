/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;

// line 95 "../../../../../WareFlow.ump"
// line 147 "../../../../../WareFlow.ump"
public class ManagerTask
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Priority { URGENT, NORMAL, LOW }
  public enum TimeEstimate { LESS_THAN_A_DAY, ONE_TO_THREE_DAYS, THREE_TO_SEVEN_DAYS, ONE_TO_THREE_WEEKS, MORE_THAN_THREE_WEEKS }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ManagerTask Attributes
  private boolean requiresManagerApproval;
  private Priority priority;
  private TimeEstimate timeEstimate;

  //ManagerTask Associations
  private WareFlow wareFlow;
  private Manager manager;
  private ShipmentOrder shipmentOrder;

  //Helper Variables
  private int cachedHashCode;
  private boolean canSetManager;
  private boolean canSetShipmentOrder;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ManagerTask(boolean aRequiresManagerApproval, Priority aPriority, TimeEstimate aTimeEstimate, WareFlow aWareFlow, Manager aManager, ShipmentOrder aShipmentOrder)
  {
    cachedHashCode = -1;
    canSetManager = true;
    canSetShipmentOrder = true;
    requiresManagerApproval = aRequiresManagerApproval;
    priority = aPriority;
    timeEstimate = aTimeEstimate;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create managerTask due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddManager = setManager(aManager);
    if (!didAddManager)
    {
      throw new RuntimeException("Unable to create managerTask due to manager. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aShipmentOrder == null || aShipmentOrder.getManagerTask() != null)
    {
      throw new RuntimeException("Unable to create ManagerTask due to aShipmentOrder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shipmentOrder = aShipmentOrder;
  }

  public ManagerTask(boolean aRequiresManagerApproval, Priority aPriority, TimeEstimate aTimeEstimate, WareFlow aWareFlow, Manager aManager, int aOrderNumberForShipmentOrder, String aDescriptionForShipmentOrder, String aShipmentAddressForShipmentOrder, String aReceiverForShipmentOrder, int aQuantityForShipmentOrder, OrderStatus aOrderStatusForShipmentOrder, WareFlow aWareFlowForShipmentOrder, ShipmentNote aNoteForShipmentOrder)
  {
    requiresManagerApproval = aRequiresManagerApproval;
    priority = aPriority;
    timeEstimate = aTimeEstimate;
    boolean didAddWareFlow = setWareFlow(aWareFlow);
    if (!didAddWareFlow)
    {
      throw new RuntimeException("Unable to create managerTask due to wareFlow. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddManager = setManager(aManager);
    if (!didAddManager)
    {
      throw new RuntimeException("Unable to create managerTask due to manager. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shipmentOrder = new ShipmentOrder(aOrderNumberForShipmentOrder, aDescriptionForShipmentOrder, aShipmentAddressForShipmentOrder, aReceiverForShipmentOrder, aQuantityForShipmentOrder, aOrderStatusForShipmentOrder, aWareFlowForShipmentOrder, aNoteForShipmentOrder, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRequiresManagerApproval(boolean aRequiresManagerApproval)
  {
    boolean wasSet = false;
    requiresManagerApproval = aRequiresManagerApproval;
    wasSet = true;
    return wasSet;
  }

  public boolean setPriority(Priority aPriority)
  {
    boolean wasSet = false;
    priority = aPriority;
    wasSet = true;
    return wasSet;
  }

  public boolean setTimeEstimate(TimeEstimate aTimeEstimate)
  {
    boolean wasSet = false;
    timeEstimate = aTimeEstimate;
    wasSet = true;
    return wasSet;
  }

  public boolean getRequiresManagerApproval()
  {
    return requiresManagerApproval;
  }

  public Priority getPriority()
  {
    return priority;
  }

  public TimeEstimate getTimeEstimate()
  {
    return timeEstimate;
  }
  /* Code from template association_GetOne */
  public WareFlow getWareFlow()
  {
    return wareFlow;
  }
  /* Code from template association_GetOne */
  public Manager getManager()
  {
    return manager;
  }
  /* Code from template association_GetOne */
  public ShipmentOrder getShipmentOrder()
  {
    return shipmentOrder;
  }
  /* Code from template association_SetOneToManyAssociationClass */
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
      existingWareFlow.removeManagerTask(this);
    }
    if (!wareFlow.addManagerTask(this))
    {
      wareFlow = existingWareFlow;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_SetOneToManyAssociationClass */
  public boolean setManager(Manager aManager)
  {
    boolean wasSet = false;
    if (!canSetManager) { return false; }
    if (aManager == null)
    {
      return wasSet;
    }

    Manager existingManager = manager;
    manager = aManager;
    if (existingManager != null && !existingManager.equals(aManager))
    {
      existingManager.removeManagerTask(this);
    }
    if (!manager.addManagerTask(this))
    {
      manager = existingManager;
      wasSet = false;
    }
    else
    {
      wasSet = true;
    }
    return wasSet;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) { return false; }
    if (!getClass().equals(obj.getClass())) { return false; }

    ManagerTask compareTo = (ManagerTask)obj;
  
    if (getManager() == null && compareTo.getManager() != null)
    {
      return false;
    }
    else if (getManager() != null && !getManager().equals(compareTo.getManager()))
    {
      return false;
    }

    if (getShipmentOrder() == null && compareTo.getShipmentOrder() != null)
    {
      return false;
    }
    else if (getShipmentOrder() != null && !getShipmentOrder().equals(compareTo.getShipmentOrder()))
    {
      return false;
    }

    return true;
  }

  public int hashCode()
  {
    if (cachedHashCode != -1)
    {
      return cachedHashCode;
    }
    cachedHashCode = 17;
    if (getManager() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getManager().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }
    if (getShipmentOrder() != null)
    {
      cachedHashCode = cachedHashCode * 23 + getShipmentOrder().hashCode();
    }
    else
    {
      cachedHashCode = cachedHashCode * 23;
    }

    canSetManager = false;
    canSetShipmentOrder = false;
    return cachedHashCode;
  }

  public void delete()
  {
    WareFlow placeholderWareFlow = wareFlow;
    this.wareFlow = null;
    if(placeholderWareFlow != null)
    {
      placeholderWareFlow.removeManagerTask(this);
    }
    Manager placeholderManager = manager;
    this.manager = null;
    if(placeholderManager != null)
    {
      placeholderManager.removeManagerTask(this);
    }
    ShipmentOrder existingShipmentOrder = shipmentOrder;
    shipmentOrder = null;
    if (existingShipmentOrder != null)
    {
      existingShipmentOrder.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "requiresManagerApproval" + ":" + getRequiresManagerApproval()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "priority" + "=" + (getPriority() != null ? !getPriority().equals(this)  ? getPriority().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeEstimate" + "=" + (getTimeEstimate() != null ? !getTimeEstimate().equals(this)  ? getTimeEstimate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "wareFlow = "+(getWareFlow()!=null?Integer.toHexString(System.identityHashCode(getWareFlow())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "manager = "+(getManager()!=null?Integer.toHexString(System.identityHashCode(getManager())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shipmentOrder = "+(getShipmentOrder()!=null?Integer.toHexString(System.identityHashCode(getShipmentOrder())):"null");
  }
}