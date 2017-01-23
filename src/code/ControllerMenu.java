package code;

import javafx.fxml.FXML;

/**
 * Created by Ferdila Rahmi on 8/22/2016.
 */
public class ControllerMenu {
    private Main main;

    @FXML
    private void toModel() throws Exception {
        main.showSceneModel();
    }

    @FXML
    private void toEvaluation() throws Exception {
        main.showSceneEvaluation();
    }

    @FXML
    private void toTesting() throws Exception {
        main.showSceneTesting();
    }

}
