package ca.mcgill.ecse.wareflow.controller;
import ca.mcgill.ecse.wareflow.model.*;

import java.util.List;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;



public class UserController {

  public static WareFlow wareFlow = WareFlowApplication.getWareFlow();

  /**
   * @author Yvehenry Julsain
   * This method is used to update the manager's password within the WareFlow application.
   * @param password the new password of the manager's account.
   * @return Returns if the password has been sucessfully updated.
   */

  public static String updateManager(String password) { //TODO
    Manager manager = wareFlow.getManager();
    String message = "";
    /*if (self.username != "manager") { //
      return "Username has to be manager.";
    }*/

    if (password == "" || password == null) {
      message += "The password must not be empty. ";
    }
    if (!(password == "" || password == null)) {
      if (password.length() < 4) {
        message += "Password must be at least four characters long. ";
      }
      if (!(password.contains("!") || password.contains("#") || password.contains("$"))) {
        message += "Password must contain a special character out of <!#$> ";
      }
      char[] passwordArray = password.toCharArray();
      boolean containsUppercase = false;
      boolean containsLowercase = false;
      for (char letter: passwordArray) {
        if (Character.isUpperCase(letter)) {
          containsUppercase = true;
          break;
        }
      }
      for (char letter: passwordArray) {
        if (Character.isLowerCase(letter)) {
          containsLowercase = true;
          break;
        }
      }
      if (!containsUppercase) {
        message += "Password must contain an upper case character. ";
      }
      if (!containsLowercase) {
        message += "Password must contain an lower case character. ";
      }
    }

    if (message.isEmpty()) {
      manager.setPassword(password);
      return "Password sucessfully updated!";
    }
    return message;
  }


/**
   * @author Yvehenry Julsain
   * This method is used to add an employee or a client within the WareFlow application.
   * @param username the username associated to the new employee or client account
   * @param password the password associated to the new employee or client account
   * @param name the name associated to the new employee or client account
   * @param phoneNumber the phone number associated to the new employee or client account
   * @param isEmployee if the the account is an employee or a client account; parameter is true if the account created is an employee account
   * @param address if the account is a client account, the address associated to the new client account
   * @return Returns if an employee or a client sucessfully been added to the WareFlow Application.
   */

  public static String addEmployeeOrClient(String username, String password, String name, String phoneNumber, boolean isEmployee, String address) { //TODO
    
    String message = "";
    if (username == "manager") {
      message += "The username cannot be 'manager'. ";
    }
    if (username == "" || username == null) {
      message += "The username must not be empty. ";
    }
    if (!(username == null || username.isEmpty())) {
      char[] usernameArray = username.toCharArray();
      for (char letter:usernameArray) {
        if (!("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890 ".contains(String.valueOf(letter)))) {
          message += "The username contains an invalid character. The username can only contain letters from a to z, letters from A to Z, and numbers from 0 to 9. ";
        }
      }
    }

    if (password == "" || password == null) {
      message += "The password must not be empty. ";
    }
    if (!(password == "" || password == null)) {
      if (password.length() < 4) {
        message += "Password must be at least four characters long. ";
      }
      if (!(password.contains("!") || password.contains("#") || password.contains("$"))) {
        message += "Password must contain a special character out of <!#$> ";
      }
      char[] passwordArray = password.toCharArray();
      boolean containsUppercase = false;
      boolean containsLowercase = false;
      for (char letter: passwordArray) {
        if (Character.isUpperCase(letter)) {
          containsUppercase = true;
          break;
        }
      }
      for (char letter: passwordArray) {
        if (Character.isLowerCase(letter)) {
          containsLowercase = true;
          break;
        }
      }
      if (!containsUppercase) {
        message += "Password must contain an upper case character. ";
      }
      if (!containsLowercase) {
        message += "Password must contain an lower case character. ";
      }
    }

    if (name == null) {
      message += "The name must not be null. ";
    }
    if (phoneNumber == "" || phoneNumber == null) {
      message += "The phone number must not be empty. ";
    }

    List<Employee> employees = wareFlow.getEmployees();
    for (Employee employee:employees) {
      if (username == "manager" || username == "" || username == null) break;
      if (employee.getUsername() == username) {
        message += "This username is already taken. ";
        break;
      }
    }
    List<Client> clients = wareFlow.getClients();
    for (Client client:clients) {
      if (client.getUsername() == username || message.isEmpty()) {
        message += "This username is already taken. ";
        break;
      }
    }

    if (isEmployee) {
      //Employee nEmployee = new Employee(username, name, password, phoneNumber, wareFlow);
      if (message.isEmpty()) {
        wareFlow.addEmployee(username, name, password, phoneNumber);
        return "The employee has been sucessfully added!";
      }
    }
    else {
      if (address == "" || address == null) {
        message += "The address must not be empty. ";
      }
      if (message.isEmpty()) {
        wareFlow.addClient(username, name, password, phoneNumber, address);
        return "The client has been sucessfully added!";
      }
    }
    return message;
  }


  /**
   * @author Yvehenry Julsain
   * This method is used to update the information associated to an employee or a client account within the WareFlow application.
   * @param username the username associated to the employee or client account we want to make modifications in
   * @param newPassword the new password to be updated in the employee or client's account
   * @param newName the new name to be updated in the employee or client's account
   * @param newPhoneNumber the new phone number to be updated in the employee or client's account
   * @param newAddress if the account is a client account, the new address to be updated in the client's account
   * @return Returns if the information associated to an employee or a client has been sucessfully updated in the WareFlow Application.
   */

  public static String updateEmployeeOrClient(String username, String newPassword, String newName, String newPhoneNumber, String newAddress) { //TODO

    String message = "";
    if (username == "manager") {
      message += "The username cannot be 'manager'. ";
    }
    if (username == "" || username == null) {
      message += "The username must not be empty. ";
    }
    if (!(username == null || username.isEmpty())) {
      char[] usernameArray = username.toCharArray();
      for (char letter:usernameArray) {
        if (!("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890 ".contains(String.valueOf(letter)))) {
          message += "The username contains an invalid character. The username can only contain letters from a to z, letters from A to Z, and numbers from 0 to 9. ";
        }
      }
    }

    List<Employee> employees = wareFlow.getEmployees();
    for (Employee employee:employees) {
      if (employee.getUsername() == username) {
        if (!(newPassword == null || newPassword.isEmpty())) {
          if (!(newPassword == "" || newPassword == null)) {
            if (newPassword.length() < 4) {
              message += "Password must be at least four characters long. ";
            }
            if (!(newPassword.contains("!") || newPassword.contains("#") || newPassword.contains("$"))) {
              message += "Password must contain a special character out of <!#$> ";
            }
            char[] passwordArray = newPassword.toCharArray();
            boolean containsUppercase = false;
            boolean containsLowercase = false;
            for (char letter: passwordArray) {
              if (Character.isUpperCase(letter)) {
                containsUppercase = true;
                break;
              }
            }
            for (char letter: passwordArray) {
              if (Character.isLowerCase(letter)) {
                containsLowercase = true;
                break;
              }
            }
            if (!containsUppercase) {
              message += "Password must contain an upper case character. ";
            }
            if (!containsLowercase) {
              message += "Password must contain an lower case character. ";
            }
          }
          if (message.isEmpty()) employee.setPassword(newPassword);
        }
        if (!(newName.isEmpty())) {
          employee.setName(newName);
        }
        if (!(newPhoneNumber.isEmpty())) {
          employee.setPhoneNumber(newPhoneNumber);
        }
        if (message.isEmpty()) return "Employee account information sucessfully updated.";
      }
    }
    List<Client> clients = wareFlow.getClients();
    for (Client client:clients) {
      if (client.getUsername() == username) {
        if (!(newPassword == null || newPassword.isEmpty())) {
          if (!(newPassword == "" || newPassword == null)) {
            if (newPassword.length() < 4) {
              message += "Password must be at least four characters long. ";
            }
            if (!(newPassword.contains("!") || newPassword.contains("#") || newPassword.contains("$"))) {
              message += "Password must contain a special character out of <!#$> ";
            }
            char[] passwordArray = newPassword.toCharArray();
            boolean containsUppercase = false;
            boolean containsLowercase = false;
            for (char letter: passwordArray) {
              if (Character.isUpperCase(letter)) {
                containsUppercase = true;
                break;
              }
            }
            for (char letter: passwordArray) {
              if (Character.isLowerCase(letter)) {
                containsLowercase = true;
                break;
              }
            }
            if (!containsUppercase) {
              message += "Password must contain an upper case character. ";
            }
            if (!containsLowercase) {
              message += "Password must contain an lower case character. ";
            }
          }
          if (message.isEmpty()) client.setPassword(newPassword);
        }
        if (!(newName.isEmpty())) {
          client.setName(newName);
        }
        if (!(newPhoneNumber.isEmpty())) {
          client.setPhoneNumber(newPhoneNumber);
        }
        if (!(newAddress.isEmpty())) {
          client.setAddress(newAddress);
        }
        if (message.isEmpty()) return "Client account information sucessfully updated.";
      }
    }
    return "The account was not found in the WareFlow application.";
  }


  public static void deleteEmployeeOrClient(String username) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }
}
