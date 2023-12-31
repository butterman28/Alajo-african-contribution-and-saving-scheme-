package com.example.alajo;

import com.example.alajo.model.CSVHandler;
import com.example.alajo.model.User;
import com.example.alajo.model.ajohandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ajoformcontroller {
    private static String id ;
    @FXML
    private TextField ajoidfield;
    @FXML
    private TextField numberpfield;
    @FXML
    private TextField amountfield;
    @FXML
    private TextField everywhenf;
    @FXML
    private Label myLabel;
    @FXML
    private Label errorlabel;
    private static String filePath = "src/main/resources/com/example/alajo/csvs/ajo.csv";
    public static String createUniqueIndex() throws IOException{
        String uniqueId = UUID.randomUUID().toString();
        return uniqueId;
    }

    public void createajo(ActionEvent event) throws IOException {
        String userid = id;
        String ajoid = createUniqueIndex();
        String ajoname = ajoidfield.getText().toLowerCase();
        String noofparticipants= numberpfield.getText().toLowerCase();
        String contribamount = amountfield.getText().toLowerCase();
        String everywhen = everywhenf.getText().toLowerCase();
        String payeverywhen = "";
        String exist = ajohandler.isajonameunique(ajoname);
        List<String> newajoList = new ArrayList<>();
        if (exist.equals("false")) {
            newajoList.add(ajoid);
            newajoList.add(ajoname);
            newajoList.add(noofparticipants);
            newajoList.add(contribamount);
            newajoList.add(everywhen);
            newajoList.add(payeverywhen);
            newajoList.add("no");
            newajoList.add(userid);
            CSVHandler.appendtoCSV(filePath, newajoList);
        }else{
            errorlabel.setText("Ajo Name Already Exist");
        }
    }

    public static void fetchuserid(String args) {
        id = args;

    }


}
