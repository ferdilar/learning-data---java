package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static BorderPane bp_content;
    private Stage stage_window;

    private static ControllerModel c_model;
    private static ControllerEvaluation c_evaluation;
    private static ControllerTesting c_testing;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage_window = primaryStage;
        this.stage_window.setTitle("SMS Spam Filtering");
        System.out.println("Starting ...");
        showMenu();
        showSceneModel();
    }
    private void showMenu() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view_menu.fxml"));
        bp_content = (BorderPane) loader.load();
        Scene scene =  new Scene(bp_content);
        stage_window.setScene(scene);
        stage_window.show();
    }
    public static void showSceneModel() throws Exception{
//        c_model = new ControllerModel();
//        c_model.model();

        System.out.println("Show Model");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view_scn_model.fxml"));
        BorderPane bp_main_content = (BorderPane) loader.load();
        bp_content.setCenter(bp_main_content);
        //proses data di controller model hingga table model.
        //tampilkan pada table view dari controller apakah bisa?
    }
    public static void showSceneEvaluation() throws Exception{
        System.out.println(">Processing Evaluation ...");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view_scn_evaluation.fxml"));
        BorderPane bp_main_content = (BorderPane) loader.load();
        bp_content.setCenter(bp_main_content);
        c_evaluation = new ControllerEvaluation();
        c_evaluation.evaluation();
        System.out.println("<Done Eval");
    }
    public static void showSceneTesting() throws Exception{
        System.out.println("Test");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view_scn_testing.fxml"));
        BorderPane bp_main_content = (BorderPane) loader.load();
        bp_content.setCenter(bp_main_content);
//        c_testing = new ControllerTesting();//ga bisa bikin construct ceklist root construct scene builder
    }
    public static void main(String[] args) {
        launch(args);
    }
}
