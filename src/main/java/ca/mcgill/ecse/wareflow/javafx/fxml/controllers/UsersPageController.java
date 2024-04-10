package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import java.sql.Date;
import ca.mcgill.ecse.wareflow.controller.*;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ChoiceBox;



public class UsersPageController {
    @FXML private TextField createUsernameTextField;
    @FXML private TextField createNameTextField;
    @FXML private TextField createPhoneNumberTextField;
    @FXML private TextField createAddressTextField;
    @FXML private TextField createUserPasswordTextField;
    @FXML private Button createEmployeeButton;
    @FXML private Button createClientButton;

    @FXML private TextField findUsernameTextField;
    @FXML private TextField updateNameTextField;
    @FXML private TextField updatePhoneNumberTextField;
    @FXML private TextField updateAddressTextField;
    @FXML private TextField updateUserPasswordTextField;
    @FXML private Button updateEmployeeButton;
    @FXML private Button deleteEmployeeButton;
    @FXML private Button updateClientButton;
    @FXML private Button deleteClientButton;
    

    @FXML private TextField confirmOldManagerPasswordTextField;
    @FXML private TextField updateManagerPasswordTextField;
    @FXML private TextField confirmNewManagerPasswordTextField;
    @FXML private Button updateManagerButton;

    @FXML

    public void createClientClicked(ActionEvent event) {
        String createAccountString = createUsernameTextField.getText();
        String createNameString = createNameTextField.getText();
        String createPhoneNumberString = createPhoneNumberTextField.getText();
        String createAddressString = createAddressTextField.getText();
        String createUserPasswordString = createUserPasswordTextField.getText();
        Boolean isEmployee = false;

        if (createAccountString == null){
            ViewUtils.showError("Username cannot be empty");
            return;
        }
        if(createNameString == null){
            createNameString = "";
        }
        if(createPhoneNumberString == null){
            createPhoneNumberString = "";
        }
        if(createAddressString == null){
            createAddressString = "";
        }
        if(createUserPasswordString == null){
            ViewUtils.showError("Password cannot be empty");
            return;
        }
    

        else {
            String errorMessage = UserController.addEmployeeOrClient(createAccountString, createUserPasswordString, 
            createNameString, createPhoneNumberString, isEmployee, createAddressString);

            if(errorMessage.isEmpty()){
                createUsernameTextField.setText("");
                createNameTextField.setText("");
                createPhoneNumberTextField.setText("");
                createAddressTextField.setText("");
                createUserPasswordTextField.setText("");
            }
    
            else {
            
            ViewUtils.showError(errorMessage);
            return;
            
            }
        WareFlowFxmlView.getInstance().refresh();
        return;
        }
    }
    
    @FXML

    public void createEmployeeClicked(ActionEvent event) {
        String createAccountString = createUsernameTextField.getText();
        String createNameString = createNameTextField.getText();
        String createPhoneNumberString = createPhoneNumberTextField.getText();
        String createAddressString = createAddressTextField.getText();
        String createUserPasswordString = createUserPasswordTextField.getText();
        Boolean isEmployee = true;

        if (createAccountString == null){
            ViewUtils.showError("Username cannot be empty");
            return;
        }
        if(createNameString == null){
            createNameString = "";
        }
        if(createPhoneNumberString == null){
            createPhoneNumberString = "";
        }
        if(createAddressString == null){
            createAddressString = "";
        }
        if(createUserPasswordString == null){
            ViewUtils.showError("Password cannot be empty");
            return;
        }
    

        else {
            String errorMessage = UserController.addEmployeeOrClient(createAccountString, createUserPasswordString, 
            createNameString, createPhoneNumberString, isEmployee, createAddressString);

            if(errorMessage.isEmpty()){
                createUsernameTextField.setText("");
                createNameTextField.setText("");
                createPhoneNumberTextField.setText("");
                createAddressTextField.setText("");
                createUserPasswordTextField.setText("");
            }
    
            else {
            
            ViewUtils.showError(errorMessage);
            return;
            
            }
        WareFlowFxmlView.getInstance().refresh();
        return;
        }
    }

    @FXML

    public void updateEmployeeOrClientClicked(ActionEvent event) {

        String findUsernameString = findUsernameTextField.getText();
        String updateNameString = updateNameTextField.getText();
        String updatePhoneNumberString = updatePhoneNumberTextField.getText();
        String updateAdressString = updateAddressTextField.getText();
        String updateUserPasswordString = updateUserPasswordTextField.getText();

        if(findUsernameString == null || findUsernameString.isEmpty()){
            ViewUtils.showError("The account email cannot be empty");
            return;
        }
        if(updateNameString == null){
            updateNameString = "";
        }
        if(updatePhoneNumberString == null){
            updateNameString = "";
        }
        if(updateAdressString == null){
            updateAdressString = "";
        }
        if(updateUserPasswordString == null){
            ViewUtils.showError("The new user password cannot be empty");
            return;
        }

        else{

        String errorMessage = UserController.updateEmployeeOrClient(findUsernameString,updateUserPasswordString,updateNameString,updatePhoneNumberString, updateAdressString);
        if(!errorMessage.isEmpty()){
            ViewUtils.showError(errorMessage);
            return;
        }
        else {
            findUsernameTextField.setText("");
            updateNameTextField.setText("");
            updatePhoneNumberTextField.setText("");
            updateAddressTextField.setText("");
            updateUserPasswordTextField.setText("");
            WareFlowFxmlView.getInstance().refresh();
            return;
        }
        }
    }
    
    @FXML
    
    public void updateManagerClicked(ActionEvent event) {
        String confirmOldManagerPasswordTextString = confirmOldManagerPasswordTextField.getText();
        String updateManagerPasswordString = updateManagerPasswordTextField.getText();
        String confirmNewManagerPasswordString = confirmNewManagerPasswordTextField.getText();
  
        if(confirmOldManagerPasswordTextString == null){
          ViewUtils.showError("The old manager password cannot be empty");
          return;
        }
        if(updateManagerPasswordString == null){
          ViewUtils.showError("The new password cannot be empty");
          return;
        }
        if(confirmNewManagerPasswordString == null){
          ViewUtils.showError("The confirm new password cannot be empty");
          return;
        }
        if(!updateManagerPasswordString.equals(confirmNewManagerPasswordString)){
          ViewUtils.showError("The new password must be equal to the confirm new password");
          return;
        }
        String errorString = UserController.updateManager(updateManagerPasswordString);
        if (!errorString.isEmpty()){
          ViewUtils.showError(errorString);
        }
        else {
          //clearing text fields
          confirmOldManagerPasswordTextField.setText("");
          updateManagerPasswordTextField.setText("");
          confirmNewManagerPasswordTextField.setText("");
        
          WareFlowFxmlView.getInstance().refresh();
          return;
        }
  

    }
    
    @FXML

    public void deleteEmployeeOrClientClicked(ActionEvent event) {
        String findUsernameString = findUsernameTextField.getText();
    
        if(findUsernameString == null || findUsernameString.isEmpty()){
          ViewUtils.showError("The account email cannot be empty");
          return;
        }
        
        else{
            UserController.deleteEmployeeOrClient(findUsernameString);
          // clearing text fields
          findUsernameTextField.setText("");
          updateNameTextField.setText("");
          updatePhoneNumberTextField.setText("");
          updateAddressTextField.setText("");
          updateUserPasswordTextField.setText("");
          WareFlowFxmlView.getInstance().refresh();
          return;
        }
    }
}
