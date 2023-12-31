package com.example.alajo;

import com.example.alajo.model.CSVHandler;
import com.example.alajo.model.User;
import com.example.alajo.model.ajohandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class homepagecontroller {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField searchfield;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label myLabel;
    @FXML
    private Label nopf;
    @FXML
    private Label ajonamef;
    @FXML
    private Label invisibleerror;
    @FXML
    private Label caf;
    @FXML
    private Label cbf;
    @FXML
    private Label everyf;
    @FXML
    private Button joinbutton;
    public static String user_id;
    private static String filePath = "src/main/resources/com/example/alajo/csvs/ajo.csv";
    public static List<String[]> availableajo= new ArrayList<>();

    public void toajoform(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ajoformcontroller.fxml"));
        root = loader.load();
        ajoformcontroller scene3Controller = loader.getController();
        scene3Controller.fetchuserid(user_id);
        //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void searchajo(ActionEvent event)throws IOException {
        List<String> foundajo= new ArrayList<>();
        String searchfaith = "";
        String namesearch = searchfield.getText().toLowerCase();
        foundajo = ajohandler.fetchajo(namesearch);
        if (foundajo.size()==1){
            searchfaith=foundajo.get(0);
            invisibleerror.setText(searchfaith);
        }else{
            ajonamef.setText("Ajo Name :" + foundajo.get(1));
            nopf.setText("Nuumber of participants :" + foundajo.get(2));
            caf.setText("Contribution Amount :"+foundajo.get(3));
            everyf.setText("Every When :"+foundajo.get(4));
            joinbutton.setText("Join : " + foundajo.get(1));
            cbf.setText("Created By : " + User.returnusername(foundajo.get(5)));
        }

    }
    public void updateLabelText(String newText) {
        myLabel.setText(newText);
    }

    public static void getuserid(String arg) {
        user_id = arg;

    }
    private Button createButton() {
        // Create a Button
        Button button = new Button("Join");

        // Set layout parameters or styles if needed
        // button.setLayoutX(50);
        // button.setLayoutY(50);

        return button;
    }

    public static void listajo(String[] args) {
        try {
            availableajo = CSVHandler.readCSV(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < availableajo.size(); i++) {
            //String line = availableajo[i];

            // Create a Label for each line
            //Label label = new Label(line);

            // Set layout parameters (you can customize these based on your needs)
            //AnchorPane.setTopAnchor(label, i * 20.0);  // Adjust the vertical position
            //AnchorPane.setLeftAnchor(label, 20.0);     // Adjust the horizontal position

            // Add the Label to the AnchorPane
            //anchorPane.getChildren().add(label);

        }

    }
}
