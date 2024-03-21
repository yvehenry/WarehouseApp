/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/



// line 1 "WareFlowStates.ump"
public class ShipmentOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShipmentOrder State Machines
  public enum TicketStatus { Open, Assigned, InProgress, Completed, Closed }
  private TicketStatus ticketStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShipmentOrder()
  {
    setTicketStatus(TicketStatus.Open);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getTicketStatusFullName()
  {
    String answer = ticketStatus.toString();
    return answer;
  }

  public TicketStatus getTicketStatus()
  {
    return ticketStatus;
  }

  public boolean assign(WarehouseStaff orderFixer,PriorityLevel priorityLevel,TimeEstimate timeEstimate,boolean isRequired)
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Open:
        // line 4 "WareFlowStates.ump"
        assignTo(orderFixer);
            setPriorityLevel(priorityLevel);
            setTimeEstimate(timeEstimate);
            setRequiresManagerApproval(isRequired);
        setTicketStatus(TicketStatus.Assigned);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startWork()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Assigned:
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean markAsResolved()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case InProgress:
        if (!(requiresManagerApproval()))
        {
          setTicketStatus(TicketStatus.Closed);
          wasEventProcessed = true;
          break;
        }
        if (requiresManagerApproval())
        {
          setTicketStatus(TicketStatus.Completed);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean approveWork()
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Completed:
        setTicketStatus(TicketStatus.Closed);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean disapproveWork(Date date,String reason)
  {
    boolean wasEventProcessed = false;
    
    TicketStatus aTicketStatus = ticketStatus;
    switch (aTicketStatus)
    {
      case Completed:
        setTicketStatus(TicketStatus.InProgress);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setTicketStatus(TicketStatus aTicketStatus)
  {
    ticketStatus = aTicketStatus;
  }

  public void delete()
  {}

  // line 27 "WareFlowStates.ump"
   private void assignTo(WarehouseStaff warehouseStaff){
    setOrderPicker(warehouseStaff);
  }

  // line 30 "WareFlowStates.ump"
   private void setPriorityLevel(PriorityLevel priorityLevel){
    setPriority(priorityLevel);
  }

  // line 34 "WareFlowStates.ump"
   private void setTimeEstimate(TimeEstimate timeEstimate){
    setTimeToFullfill(timeEstimate);
  }

  // line 38 "WareFlowStates.ump"
   private void setRequiresManagerApproval(boolean isRequired){
    if (isRequired) {
            setOrderApprover(getWareFlow().getManager());
        }
  }

  // line 43 "WareFlowStates.ump"
   private boolean requiresManagerApproval(){
    return hasOrderApprover();
  }

}