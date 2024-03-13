package ca.mcgill.ecse.wareflow.controller;

public class ItemTypeController {
  /**
   * @author Benjamin Bouhdana
   * This method is used to add an item type to the WareFlow application
   * @param name the name of the item type
   * @param expectedLifeSpanInDays the expected life span in days of the item type
   * @return returns an empty string if the item type was added successfully
   * with no raised errors
   * If there are errors, an error message is returned
  */
  public static String addItemType(String name, int expectedLifeSpanInDays) {

    if (name.isEmpty() || name == null) {
      return "The Item must have a name"
    }

    if (ItemType.hasWithName(name)) {
      return "This Item Type already exists in the system"
    }

    if (expectedLifeSpanInDays <= 0) {
      return "The lifespan of the item must be greater than 0 days"
    }

    try {
      ItemType newItemType = new ItemType(aName, expectedLifeSpanInDays, wareFlowApplication.getWareFlow());
    }

    catch (Exception e) {
      throw e;
    }
    return "";
  }

   /**
   * @author Benjamin Bouhdana
   * This method is used to update an item type to the WareFlow application
   * @param oldName the old name of the item type
   * @param newName the new name of the item type
   * @param expectedLifeSpanInDays the expected life span in days of the updated item type
   * @return returns an empty string if the item type was updated successfully
   * with no raised errors
   * If there are errors, an error message is returned
  */
  public static String updateItemType(String oldName, String newName, int newExpectedLifeSpanInDays) {

    if (newName.isEmpty() || name == null) {
      return "The Item type must have a name"
    }

    if (!ItemType.hasWithName(oldName)) {
      return "This old Item type does not exist"
    }

    if (ItemType.hasWithName(newName) && !(newName.equals(oldName))) {
      "The new Item type already exists"
    }

     if (newExpectedLifeSpanInDays <= 0) {
      return "The lifespan of the item must be greater than 0 days"
    }

    try {
      ItemType anItemType = ItemType.getWithName(oldName);
      anItemType.setName(newName);
      anItemType.setExpectedLifeSpanInDays(newExpectedLifeSpanInDays);
    }

    catch (Exception e) {
      throw e;
    }

    return "";
  }

  /**
   * @author Benjamin Bouhdana
   * This method is used to update an item type to the WareFlow application
   * @param name the name of the item type to be deleted
   * @return returns an empty string if the item type was deleted successfully
   * with no raised errors
   * If there are errors, an error message is returned
  */
  public static void deleteItemType(String name) {
    try {
      if (ItemType.hasWithName(name)) {
        ItemType.getWithName(name).delete();
      }
    }
    catch (RuntimeException e){
      throw e;
    }
    return "";
  }
}