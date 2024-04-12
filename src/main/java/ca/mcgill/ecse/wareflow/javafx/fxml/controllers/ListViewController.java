package ca.mcgill.ecse.wareflow.javafx.fxml.controllers;


import ca.mcgill.ecse.wareflow.controller.ShipmentOrderController;
import ca.mcgill.ecse.wareflow.controller.TOShipmentOrder;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for ListView
 *
 * @author Neeshal Imrit
 */
public class ListViewController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button allOrdersButton;

    @FXML
    private TableView<TOShipmentOrder> orderTable;

    @FXML
    private Button findOrderButton;

    @FXML
    private Button orderRaiserButton;

    @FXML
    private TextField orderRaiserTextField;

    @FXML
    private TextField searchOrderTextField;

    /**
     * Initializes the controller.
     *
     * @author Neeshal Imrit
     */
    public void initialize() {

        var idcolumn = createTableColumn("Order ID", "id");
        orderTable.getColumns().add(idcolumn);


        //STATUS COLUMN (string)
        var statusColumn = createTableColumn("Status", "status");
        orderTable.getColumns().add(statusColumn);

        //RAISED BY USER COLUMN (customizable string)
        var raisedColumn = new TableColumn<TOShipmentOrder, String>("Order Raiser");
        raisedColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getOrderPlacer()));
        orderTable.getColumns().add(raisedColumn);

        //DESCRIPTION COLUMN (customizable string)
        var descriptionColumn = new TableColumn<TOShipmentOrder, String>("Description");
        descriptionColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getDescription()));
        orderTable.getColumns().add(descriptionColumn);

        //FIXED BY USER COLUMN (customizable string)
        var fixedempColumn = new TableColumn<TOShipmentOrder, String>("Assigned Employee");
        fixedempColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getProcessedBy()));
        orderTable.getColumns().add(fixedempColumn);

        var timeResColumn = new TableColumn<TOShipmentOrder, String>("Time To Resolve");
        timeResColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> {
                    TOShipmentOrder order = data.getValue();
                    if (order != null && order.getTimeToResolve() != null) {
                        return order.getTimeToResolve();
                    } else {
                        return ""; // or any default value or appropriate handling
                    }
                }
        ));
        orderTable.getColumns().add(timeResColumn);

        //PRIORITY COLUMN (string)
        orderTable.getColumns().add(createTableColumn("Priority", "priority"));

        //APPROVAL REQUIRED (Boolean)
        orderTable.getColumns().add(createTableColumn("Approval Required", "approvalRequired"));

        //Item NAME (customizable string)
        var itemNameColumn = new TableColumn<TOShipmentOrder, String>("Item Name");
        itemNameColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getItemName()));
        orderTable.getColumns().add(itemNameColumn);

        //LIFESPAN COLUMN (string)
        orderTable.getColumns().add(createTableColumn("Lifespan", "expectLifeSpanInDays"));

        //Added on date (Date)
        var addedOnDateColumn = new TableColumn<TOShipmentOrder, String>("Added On");
        addedOnDateColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getAddedOnDate().toString()));
        orderTable.getColumns().add(addedOnDateColumn);

        //AreaNumber int
        orderTable.getColumns().add(createTableColumn("Area Number", "areaNumber"));

        //RoomNumber int
        orderTable.getColumns().add(createTableColumn("Slot Number", "slotNumber"));


        //Order Notes 
        var notesColumn = new TableColumn<TOShipmentOrder, String>("Notes");
        notesColumn.setCellValueFactory(data -> Bindings.createStringBinding(
                () -> data.getValue().getNotes().toString()));
        orderTable.getColumns().add(notesColumn);


        //Set colors 
        orderTable.setRowFactory(tv -> new TableRow<TOShipmentOrder>() {
            @Override
            protected void updateItem(TOShipmentOrder item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || item.getStatus() == null) {
                    // If the status is null, set the default row color
                    setStyle("");
                } else {
                    // Set row color based on the value of the "Status" column
                    switch (item.getStatus()) {
                        case "Open":
                            setStyle("-fx-background-color: lightgreen;");
                            break;
                        case "InProgress":
                            setStyle("-fx-background-color: #FFFFE0;"); //Light yellow
                            break;
                        case "Assigned":
                            setStyle("-fx-background-color: #FFDAB9;");  //light orange
                            break;
                        case "Closed":
                            setStyle("-fx-background-color: #FF0000;"); //light red 
                            break;
                        case "Completed":
                            setStyle("-fx-background-color: #E6E6FA;"); //light purple
                            break;
                        default:
                            // Set the default row color for other statuses
                            setStyle("");
                            break;
                    }
                }
            }
        });


    }

    /**
     * Creates a TableColumn for the TableView.
     *
     * @param header       The header text for the column.
     * @param propertyName The property name associated with the column.
     * @return TableColumn<TOShipmentOrder, String> - Returns a TableColumn instance.
     * @author Neeshal Imrit
     */
    public static TableColumn<TOShipmentOrder, String> createTableColumn(String header, String propertyName) {
        TableColumn<TOShipmentOrder, String> column = new TableColumn<>(header);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    /**
     * Event handler for the "See All Orders" button.
     *
     * @param event The ActionEvent triggered by the button click.
     * @author Neeshal Imrit
     */
    @FXML
    void allOrdersButtonClicked(ActionEvent event) {
        orderTable.setItems(FXCollections.observableList(getAllOrders()));
        WareFlowFxmlView.getInstance().registerRefreshEvent(orderTable);
        WareFlowFxmlView.getInstance().refresh();
    }

    /**
     * Event handler for the "Search Raiser" button.
     *
     * @param event The ActionEvent triggered by the button click.
     * @author Neeshal Imrit
     */
    @FXML
    void orderRaiserButtonClicked(ActionEvent event) {
        String selectedRaiser = orderRaiserTextField.getText().trim();
        ObservableList<TOShipmentOrder> filteredOrders = getAllOrders().filtered(order -> order.getOrderPlacer().equalsIgnoreCase(selectedRaiser));

        if (filteredOrders.isEmpty()) {
            ViewUtils.showError("No orders found with the specified Raiser");
        } else {
            orderTable.setItems(FXCollections.observableList(filteredOrders));
            WareFlowFxmlView.getInstance().registerRefreshEvent(orderTable);
        }

    }

    /**
     * Event handler for the "Search" button (for Order ID).
     *
     * @param event The ActionEvent triggered by the button click.
     * @author Neeshal Imrit
     */
    @FXML
    void findOrderButtonClicked(ActionEvent event) {
        String stringid = searchOrderTextField.getText().trim();
        if (stringid.isEmpty()) {
            ViewUtils.showError("Please input a valid Order ID");
        } else {
            int id = Integer.parseInt(stringid);
            ObservableList<TOShipmentOrder> filteredOrders = getAllOrders().filtered(order -> order.getId() == id);

            if (filteredOrders.isEmpty()) {
                ViewUtils.showError("No Orders found with the specified ID");
            } else {
                orderTable.setItems(filteredOrders);
                WareFlowFxmlView.getInstance().registerRefreshEvent(orderTable);
            }
        }
    }

    /**
     * Retrieves all shipment orders.
     *
     * @return ObservableList<TOShipmentOrder> - An ObservableList containing all shipment orders.
     * @author Neeshal Imrit
     */
    public ObservableList<TOShipmentOrder> getAllOrders() {
        ShipmentOrderController.getOrders();
        return FXCollections.observableList(ShipmentOrderController.getOrders());
    }
}
