package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;

public class UserController {

/**
 * @author Yvehenry Julsain
 * This method is used to update the manager's password within the WareFlow application.
 * @param password the new password of the manager's account.
 * @return Returns if the password has been successfully updated.
 */
public static String updateManager(String password) {
    WareFlow wareFlow = WareFlowApplication.getWareFlow();
    Manager manager = wareFlow.getManager();
    manager.setPassword(password);
    return "Password successfully updated!";
}

// address is ignored if the isEmployee is true
public static String addEmployeeOrClient(String username, String password, String name,
                                         String phoneNumber, boolean isEmployee, String address) {

    throw new UnsupportedOperationException("Not Implemented!");
}

    // newAddress is ignored if the user is an employee
    public static String updateEmployeeOrClient(String username, String newPassword, String newName,
                                                String newPhoneNumber, String newAddress) {

        throw new UnsupportedOperationException("Not Implemented!");
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
