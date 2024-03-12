package ca.mcgill.ecse.wareflow.controller;
import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;

/**
   * @author Yvehenry Julsain
   * This method is used to update the manager's password within the WareFlow application.
   * @param password the new password of the manager's account.
   * @return Returns if the password has been sucessfully updated.
   */
public class UserController {
  public static String updateManager(String password) { //TODO
    WareFlow wareFlow = WareFlowApplication.getWareFlow();
    Manager manager = wareFlow.getManager();
    manager.setPassword(password);
    return "Password sucessfully updated!";
  }

  // address is ignored if the isEmployee is true
  public static String addEmployeeOrClient(String username, String password, String name,
      String phoneNumber, boolean isEmployee, String address) { //TODO
    
    throw new UnsupportedOperationException("Not Implemented!");
  }

  // newAddress is ignored if the user is an employee
  public static String updateEmployeeOrClient(String username, String newPassword, String newName,
      String newPhoneNumber, String newAddress) { //TODO
    
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static void deleteEmployeeOrClient(String username) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }
}
