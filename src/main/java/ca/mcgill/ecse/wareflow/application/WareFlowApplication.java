package ca.mcgill.ecse.wareflow.application;

import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;
import ca.mcgill.ecse.wareflow.javafx.fxml.WareFlowFxmlView;
import javafx.application.Application;

public class WareFlowApplication {

  private static WareFlow wareFlow;

  public static void main(String[] args) {
    Application.launch(WareFlowFxmlView.class, args);
    
  }

  public static WareFlow getWareFlow() {
    if (wareFlow == null) {
      
      wareFlow = WareFlowPersistence.load();
    }
    return wareFlow;
  }

}
