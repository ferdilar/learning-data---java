package code;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by Ferdila Rahmi on 9/3/2016.
 */
public class TableViewController implements Initializable{
    @FXML
    private Button btn_update;
    @FXML
    private TableView<TableViewModel> table_view1;
    @FXML
    private TableColumn<TableViewModel, Integer> col_id1;
    @FXML
    private TableColumn<TableViewModel, String> col_term1;
    @FXML
    private TableColumn<TableViewModel, Double> col_prob_true1;
    @FXML
    private TableColumn<TableViewModel, Double> col_prob_false1;
    @FXML
    private TableView table_view2;
    @FXML
    private TableColumn col_id2;
    @FXML
    private TableColumn col_term2;
    @FXML
    private TableColumn col_prob_true2;
    @FXML
    private TableColumn col_prob_false2;

    private ObservableList<TableViewModel> list_data1;
    private ObservableList<TableViewModel> list_data2;

    @FXML
    public void btnUpdateClick() throws Exception {
        btn_update.setDisable(true);

        System.out.println(">Processing Training Model ...");
        ControllerModel c_model = new ControllerModel();
        c_model = new ControllerModel();
        c_model.model();
        System.out.println("<Done Model");

        Main.showSceneModel();

        btn_update.setDisable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            list_data1 = FXCollections.observableArrayList();
            this.tableData1(list_data1);

            table_view1.setItems(list_data1);
            col_id1.setCellValueFactory(new PropertyValueFactory<TableViewModel, Integer>("id_data"));
            col_term1.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("term"));
            col_prob_true1.setCellValueFactory(new PropertyValueFactory<TableViewModel, Double>("prob_true"));
            col_prob_false1.setCellValueFactory(new PropertyValueFactory<TableViewModel, Double>("prob_false"));

            /*model2*/
            list_data2 = FXCollections.observableArrayList();
            this.tableData2(list_data2);

            table_view2.setItems(list_data2);
            col_id2.setCellValueFactory(new PropertyValueFactory<TableViewModel, Integer>("id_data"));
            col_term2.setCellValueFactory(new PropertyValueFactory<TableViewModel, String>("term"));
            col_prob_true2.setCellValueFactory(new PropertyValueFactory<TableViewModel, Double>("prob_true"));
            col_prob_false2.setCellValueFactory(new PropertyValueFactory<TableViewModel, Double>("prob_false"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tableData1(ObservableList<TableViewModel> list_data1) throws Exception {
        String query = "SELECT * FROM MODEL1";
        int id_data;
        String term;
        double prob_true;
        double prob_false;
        try {
            DBModel db_model = new DBModel();
            db_model.getDataModel(query);
            while (db_model.getResult().next()) {
                id_data = db_model.getResult().getInt(1);
                term = db_model.getResult().getString(2);
                prob_true = db_model.getResult().getDouble(3);
                prob_false = db_model.getResult().getDouble(4);

                list_data1.add(new TableViewModel(id_data, term, prob_true, prob_false));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void tableData2(ObservableList<TableViewModel> list_data2) throws Exception {
        String query = "SELECT * FROM MODEL2";
        int id_data;
        String term;
        double prob_true;
        double prob_false;
        try {
            DBModel db_model = new DBModel();
            db_model.getDataModel(query);
            while (db_model.getResult().next()) {
                id_data = db_model.getResult().getInt(1);
                term = db_model.getResult().getString(2);
                prob_true = db_model.getResult().getDouble(3);
                prob_false = db_model.getResult().getDouble(4);

                list_data2.add(new TableViewModel(id_data, term, prob_true, prob_false));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
