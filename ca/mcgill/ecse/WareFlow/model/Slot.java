/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.WareFlow.model;
import java.util.*;

// line 30 "../../../../../WareFlow.ump"
public class Slot
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<int, Slot> slotsBySlotNo = new HashMap<int, Slot>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Slot Attributes
  private int slotNo;

  //Slot Associations
  private Area slotArea;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Slot(int aSlotNo, Area aSlotArea)
  {
    if (!setSlotNo(aSlotNo))
    {
      throw new RuntimeException("Cannot create due to duplicate slotNo. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddSlotArea = setSlotArea(aSlotArea);
    if (!didAddSlotArea)
    {
      throw new RuntimeException("Unable to create slot due to slotArea. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSlotNo(int aSlotNo)
  {
    boolean wasSet = false;
    int anOldSlotNo = getSlotNo();
    if (anOldSlotNo != null && anOldSlotNo.equals(aSlotNo)) {
      return true;
    }
    if (hasWithSlotNo(aSlotNo)) {
      return wasSet;
    }
    slotNo = aSlotNo;
    wasSet = true;
    if (anOldSlotNo != null) {
      slotsBySlotNo.remove(anOldSlotNo);
    }
    slotsBySlotNo.put(aSlotNo, this);
    return wasSet;
  }

  public int getSlotNo()
  {
    return slotNo;
  }
  /* Code from template attribute_GetUnique */
  public static Slot getWithSlotNo(int aSlotNo)
  {
    return slotsBySlotNo.get(aSlotNo);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithSlotNo(int aSlotNo)
  {
    return getWithSlotNo(aSlotNo) != null;
  }
  /* Code from template association_GetOne */
  public Area getSlotArea()
  {
    return slotArea;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSlotArea(Area aSlotArea)
  {
    boolean wasSet = false;
    if (aSlotArea == null)
    {
      return wasSet;
    }

    Area existingSlotArea = slotArea;
    slotArea = aSlotArea;
    if (existingSlotArea != null && !existingSlotArea.equals(aSlotArea))
    {
      existingSlotArea.removeSlot(this);
    }
    slotArea.addSlot(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    slotsBySlotNo.remove(getSlotNo());
    Area placeholderSlotArea = slotArea;
    this.slotArea = null;
    if(placeholderSlotArea != null)
    {
      placeholderSlotArea.removeSlot(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "slotNo" + ":" + getSlotNo()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "slotArea = "+(getSlotArea()!=null?Integer.toHexString(System.identityHashCode(getSlotArea())):"null");
  }
}