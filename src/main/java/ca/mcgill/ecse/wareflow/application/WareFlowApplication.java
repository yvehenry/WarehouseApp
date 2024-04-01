package ca.mcgill.ecse.wareflow.application;

import ca.mcgill.ecse.wareflow.model.WareFlow;
import ca.mcgill.ecse.wareflow.persistence.WareFlowPersistence;

public class WareFlowApplication {

  private static WareFlow wareFlow;

  public static void main(String[] args) {
    
  }

  public static WareFlow getWareFlow() {
    if (wareFlow == null) {
      
      wareFlow = WareFlowPersistence.load();
    }
    return wareFlow;
  }

}
