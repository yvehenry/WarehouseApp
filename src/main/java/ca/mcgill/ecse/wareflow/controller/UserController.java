package ca.mcgill.ecse.wareflow.controller;

import ca.mcgill.ecse.wareflow.application.WareFlowApplication;
import ca.mcgill.ecse.wareflow.model.*;

import java.util.List;

public class UserController {
    private static final WareFlow wareFlow = WareFlowApplication.getWareFlow();

    /**
     * @param password the new password of the manager's account.
     * @return Returns if the password has been successfully updated.
     * @author Yvehenry Julsain
     * This method is used to update the manager's password within the WareFlow application.
     */
    public static String updateManager(String password) {
        Manager manager = wareFlow.getManager();
        String message = "";
        if (password.isEmpty()) {
            message += "Password cannot be empty";
        }
        if (!(password.isEmpty())) {
            if (password.length() < 4) {
                message += "Password must be at least four characters long.";
            }
            if (!(password.contains("!") || password.contains("#") || password.contains("$"))) {
                message += "Password must contain a special character out of <!#$> ";
            }
            char[] passwordArray = password.toCharArray();
            boolean containsUppercase = false;
            boolean containsLowercase = false;
            for (char letter : passwordArray) {
                if (Character.isUpperCase(letter)) {
                    containsUppercase = true;
                    break;
                }
            }
            for (char letter : passwordArray) {
                if (Character.isLowerCase(letter)) {
                    containsLowercase = true;
                    break;
                }
            }
            if (!containsUppercase) {
                message += "Password must contain one upper-case character. ";
            }
            if (!containsLowercase) {
                message += "Password must contain one lower-case character. ";
            }
        }

        if (message.isEmpty()) {
            manager.setPassword(password);
            return "Password successfully updated!";
        }
        return message;
    }

    /**
     * @param username    the username associated to the new employee or client account
     * @param password    the password associated to the new employee or client account
     * @param name        the name associated to the new employee or client account
     * @param phoneNumber the phone number associated to the new employee or client account
     * @param isEmployee  if the account is an employee or a client account; parameter is true if the account created is an employee account
     * @param address     if the account is a client account, the address associated to the new client account
     * @return Returns if an employee or a client successfully been added to the WareFlow Application.
     * @author Yvehenry Julsain
     * This method is used to add an employee or a client within the WareFlow application.
     */
    public static String addEmployeeOrClient(String username, String password, String name, String phoneNumber, boolean isEmployee, String address) { //TODO

        StringBuilder message = new StringBuilder();
        if (username.equals("manager")) {
            message.append("Username cannot be manager");
        }
        if (username.isEmpty()) {
            message.append("Username cannot be empty");
        }

        if (!username.isEmpty()) {
            char[] usernameArray = username.toCharArray();
            for (char letter : usernameArray) {
                if (("!@#$%^&*( )".contains(String.valueOf(letter)))) {
                    message.append("Invalid username");
                }
            }
        }

        if (password.isEmpty()) {
            message.append("The password must not be empty. ");
        }
        if (!password.isEmpty()) {
            if (password.length() < 4) {
                message.append("Password must be at least four characters long. ");
            }
            if (!(password.contains("!") || password.contains("#") || password.contains("$"))) {
                message.append("Password must contain a special character out of <!#$> ");
            }
            char[] passwordArray = password.toCharArray();
            boolean containsUppercase = false;
            boolean containsLowercase = false;
            for (char letter : passwordArray) {
                if (Character.isUpperCase(letter)) {
                    containsUppercase = true;
                    break;
                }
            }
            for (char letter : passwordArray) {
                if (Character.isLowerCase(letter)) {
                    containsLowercase = true;
                    break;
                }
            }
            if (!containsUppercase) {
                message.append("Password must contain an upper case character. ");
            }
            if (!containsLowercase) {
                message.append("Password must contain an lower case character. ");
            }
        }

        if (name == null) {
            message.append("The name must not be null. ");
        }
        if (phoneNumber.isEmpty()) {
            message.append("The phone number must not be empty. ");
        }

        List<Employee> employees = wareFlow.getEmployees();
        for (Employee employee : employees) {
            if (username.equals("manager") || username.isEmpty()) break;
            if (employee.getUsername().equals(username)) {
                message.append("Username already linked to an employee account");
                break;
            }
        }
        List<Client> clients = wareFlow.getClients();
        for (Client client : clients) {
            if (client.getUsername().equals(username) && message.isEmpty()) {
                message.append("Username already linked to a client account");
                break;
            }
        }

        if (message.isEmpty()) {
            if (isEmployee) {
                wareFlow.addEmployee(username, name, password, phoneNumber);
                return "Employee added successfully.";
            } else {
                wareFlow.addClient(username, name, password, phoneNumber, address);
                return "Client added successfully.";
            }
        }
        return message.toString();
    }

    /**
     * @param username       the username associated to the employee or client account we want to make modifications in
     * @param newPassword    the new password to be updated in the employee or client's account
     * @param newName        the new name to be updated in the employee or client's account
     * @param newPhoneNumber the new phone number to be updated in the employee or client's account
     * @param newAddress     if the account is a client account, the new address to be updated in the client's account
     * @return Returns if the information associated to an employee or a client has been successfully updated in the WareFlow Application.
     * @author Yvehenry Julsain
     * This method is used to update the information associated to an employee or a client account within the WareFlow application.
     */
    public static String updateEmployeeOrClient(String username, String newPassword, String newName, String newPhoneNumber, String newAddress) { //TODO
        StringBuilder message = new StringBuilder();
        if (username.equals("manager")) {
            message.append("Username cannot be manager");
        }
        if (username.isEmpty()) {
            message.append("Username cannot be empty");
        }

        if (!username.isEmpty()) {
            char[] usernameArray = username.toCharArray();
            for (char letter : usernameArray) {
                if (("!@#$%^&*( )".contains(String.valueOf(letter)))) {
                    message.append("Invalid username");
                }
            }
        }
        if (newPassword.isEmpty()) {
            message.append("Password cannot be empty");
            return message.toString();
        }

        List<Employee> employees = wareFlow.getEmployees();
        for (Employee employee : employees) {
            if (employee.getUsername().equals(username)) {
                if (!(newPassword == null || newPassword.isEmpty())) {
                    if (newPassword.length() < 4) {
                        message.append("Password must be at least four characters long. ");
                    }
                    if (!(newPassword.contains("!") || newPassword.contains("#") || newPassword.contains("$"))) {
                        message.append("Password must contain a special character out of <!#$> ");
                    }
                    char[] passwordArray = newPassword.toCharArray();
                    boolean containsUppercase = false;
                    boolean containsLowercase = false;
                    for (char letter : passwordArray) {
                        if (Character.isUpperCase(letter)) {
                            containsUppercase = true;
                            break;
                        }
                    }
                    for (char letter : passwordArray) {
                        if (Character.isLowerCase(letter)) {
                            containsLowercase = true;
                            break;
                        }
                    }
                    if (!containsUppercase) {
                        message.append("Password must contain an upper case character. ");
                    }
                    if (!containsLowercase) {
                        message.append("Password must contain an lower case character. ");
                    }
                    if (message.isEmpty()) employee.setPassword(newPassword);
                }
                if (!(newName.isEmpty())) {
                    employee.setName(newName);
                }
                if ((newPhoneNumber.isEmpty())) {
                    employee.setPhoneNumber("");
                }
                if (!(newPhoneNumber.isEmpty())) {
                    employee.setPhoneNumber(newPhoneNumber);
                }
                if (!message.isEmpty()) {
                    return message.toString();
                }
            }
        }

        List<Client> clients = wareFlow.getClients();
        for (Client client : clients) {
            if (client.getUsername().equals(username)) {
                if (!(newPassword == null || newPassword.isEmpty())) {
                    if (newPassword.length() < 4) {
                        message.append("Password must be at least four characters long. ");
                    }
                    if (!(newPassword.contains("!") || newPassword.contains("#") || newPassword.contains("$"))) {
                        message.append("Password must contain a special character out of <!#$> ");
                    }
                    char[] passwordArray = newPassword.toCharArray();
                    boolean containsUppercase = false;
                    boolean containsLowercase = false;
                    for (char letter : passwordArray) {
                        if (Character.isUpperCase(letter)) {
                            containsUppercase = true;
                            break;
                        }
                    }
                    for (char letter : passwordArray) {
                        if (Character.isLowerCase(letter)) {
                            containsLowercase = true;
                            break;
                        }
                    }
                    if (!containsUppercase) {
                        message.append("Password must contain an upper case character. ");
                    }
                    if (!containsLowercase) {
                        message.append("Password must contain an lower case character. ");
                    }
                    if (message.isEmpty()) client.setPassword(newPassword);
                }
                if (!(newName.isEmpty())) {
                    client.setName(newName);
                }
                if ((newPhoneNumber.isEmpty())) {
                    client.setPhoneNumber("");
                }
                if (!(newPhoneNumber.isEmpty())) {
                    client.setPhoneNumber(newPhoneNumber);
                }
                if ((newAddress.isEmpty())) {
                    client.setAddress("");
                }
                if (!(newAddress.isEmpty())) {
                    client.setAddress(newAddress);
                }
                if (!message.isEmpty()) {
                    return message.toString();
                }
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
