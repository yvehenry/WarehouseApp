package ca.mcgill.ecse.wareflow.controller;

public class ItemTypeController {
  public static String addItemType(String name, int expectedLifeSpanInDays) {

    if (name.isEmpty()) {
      return "The Item must have a name"
    }

    if (ItemType.hasWithName(name)) {
      return "This Item Type already exists in the system"
    }

    if (expectedLifeSpanInDays <= 0) {
      return "The lifespan of the item must be greater than 0 days"
    }

    try {
      ItemType newItemType = new ItemType(aName, expectedLifeSpanInDays, wareFlowApplication.getItemContainer());
    }
    return "";
  }

  public static String updateItemType(String oldName, String newName, int newExpectedLifeSpanInDays) {

    if (newName.isEmpty()) {
      return "The Item type must have a name"
    }

    if (!ItemType.hasWithName(oldName)) {
      return "This old Item type does not  exist"
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

    return "";
  }

  public static void deleteItemType(String name) {
    try {
      if (ItemType.hasWithName(name)) {
        ItemType.getWithName(name).delete();
      }
    }
    catch (RuntimeException e){}
  }
}