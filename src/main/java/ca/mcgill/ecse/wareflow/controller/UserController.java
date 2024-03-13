package ca.mcgill.ecse.wareflow.controller;
import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;

import java.util.List;

public class UserController {
    private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

/**
   * @author Yvehenry Julsain
   * This method is used to update the manager's password within the WareFlow application.
   * @param password the new password of the manager's account.
   * @return Returns if the password has been sucessfully updated.
   */
  public static String updateManager(String password) { //TODO
    Manager manager = wareFlow.getManager();
    manager.setPassword(password);
    return "Password sucessfully updated!";
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
  // address is ignored if the isEmployee is true
  public static String addEmployeeOrClient(String username, String password, String name, String phoneNumber, boolean isEmployee, String address) { //TODO
    List<Employee> employees = wareFlow.getEmployees();
    for (Employee employee:employees) {
      if (employee.getUsername() == username) {
        return "This username is already taken.";
      }
    }
    List<Client> clients = wareFlow.getClients();
    for (Client client:clients) {
      if (client.getUsername() == username) {
        return "This username is already taken.";
      }
    }
    char[] usernameArray = username.toCharArray();
    for (char letter:usernameArray) {
      if (!("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890".contains(String.valueOf(letter)))) {
        return "The username contains an invalid character. The username can only contain letters from a to z, letters from A to Z, and numbers from 0 to 9.";
      }
    }
    if (isEmployee) {
      //Employee nEmployee = new Employee(username, name, password, phoneNumber, wareFlow);
      wareFlow.addEmployee(username, name, password, phoneNumber);
      return "The employee has been sucessfully added!";
    }
    else {
      wareFlow.addClient(username, name, password, phoneNumber, address);
      return "The client has been sucessfully added!";
    }
  }

    /**
     * @param username       the username associated to the employee or client account we want to make modifications in
     * @param newPassword    the new password to be updated in the employee or client's account
     * @param newName        the new name to be updated in the employee or client's account
     * @param newPhoneNumber the new phone number to be updated in the employee or client's account
     * @param newAddress     if the account is a client account, the new address to be updated in the client's account
     * @return Returns if the information associated to an employee or a client has been sucessfully updated in the WareFlow Application.
     * @author Yvehenry Julsain
     * This method is used to update the information associated to an employee or a client account within the WareFlow application.
     */
    // newAddress is ignored if the user is an employee
    public static String updateEmployeeOrClient(String username, String newPassword, String newName, String newPhoneNumber, String newAddress) { //TODO
        WareFlow wareFlow = WareFlowApplication.getWareFlow();
        List<Employee> employees = wareFlow.getEmployees();
        for (Employee employee : employees) {
            if (employee.getUsername() == username) {
                employee.setPassword(newPassword);
                employee.setName(newName);
                employee.setPhoneNumber(newPhoneNumber);
                return "Employee account information sucessfully updated!";
            }
        }
        List<Client> clients = wareFlow.getClients();
        for (Client client : clients) {
            if (client.getUsername() == username) {
                client.setPassword(newPassword);
                client.setName(newName);
                client.setPhoneNumber(newPhoneNumber);
                client.setAddress(newAddress);
                return "Client account information sucessfully updated!";
            }
        }
        return "The account was not found in the WareFlow application.";
    }

    /**
     * @param username The employee or guest with the given unique username to be deleted.
     * @author Neeshal Imrit
     * This method is used to delete an Employee or a Guest within the WareFlow application.
     */
    public static void deleteEmployeeOrClient(String username) {

        WareFlow wareFlow = WareFlowApplication.getWareFlow();

        // if the application contains employees or clients, try to delete the user
        if (wareFlow.hasEmployees() || wareFlow.hasClients()) {
            User user = User.getWithUsername(username);
            if (user != null) {
                if (user instanceof Employee employee) {
                    employee.delete();
                } else if (user instanceof Client client) {
                    client.delete();
                }
            }
        }
    }
}
