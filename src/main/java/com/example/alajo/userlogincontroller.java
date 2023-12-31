package com.example.alajo;

import com.example.alajo.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class userlogincontroller{
    private static String username;
    @FXML
    private TextField useridfield;
    @FXML
    private TextField passwordfield;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void tohomepage(ActionEvent event)throws IOException {
        List<String> arrival = new ArrayList<>();
        String faith ="";
        String userid ="";
        String username = useridfield.getText();
        String password = passwordfield.getText();
        arrival= User.login(username,password);
        if (arrival.size()==2){
            userid =arrival.get(1);
            faith =arrival.get(0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            root = loader.load();
            homepagecontroller scene3Controller = loader.getController();
            scene3Controller.getuserid(userid);
            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            faith = arrival.get(0);
        }

        List<String> returns = new ArrayList<>();



        }

    public static void setusername(String args) {
        username = args;

    }
    public void toregistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    }

