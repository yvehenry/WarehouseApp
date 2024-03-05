package ca.mcgill.ecse.wareflow.controller;
import ca.mcgill.ecse.wareflow.model.*;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;

public class UserController {
  public static String updateManager(String password) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  // address is ignored if the isEmployee is true
  public static String addEmployeeOrClient(String username, String password, String name,
      String phoneNumber, boolean isEmployee, String address) {
    // Remove this exception when you implement this method.
    throw new UnsupportedOperationException("Not Implemented!");
  }

  // newAddress is ignored if the user is an employee
  public static String updateEmployeeOrClient(String username, String newPassword, String newName,
      String newPhoneNumber, String newAddress) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }



  /**
   * @author Neeshal Imrit
   * This method is used to delete an Employee or a Guest within the WareFlow application.
   * @param username The employee or guest with the given unique username to be deleted.
   * @return Returns null
   */
  public static void deleteEmployeeOrClient(String username) {

    WareFlow wareFlow = WareFlowApplication.getWareFlow();

    // if the application contains employees or clients, try to delete the user
    if (wareFlow.hasEmployees() || wareFlow.hasClients()){
      User user = User.getWithUsername(username);
      if (user != null){
        if (user instanceof Employee) {
          Employee employee = (Employee) user;
          employee.delete();
        } else if (user instanceof Client) {
          Client client = (Client) user;
          client.delete();
        }
      }

    }
  }

}
