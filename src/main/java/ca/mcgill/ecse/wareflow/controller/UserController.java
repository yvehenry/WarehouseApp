package ca.mcgill.ecse.wareflow.controller;

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

  public static void deleteEmployeeOrClient(String username) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }
}
