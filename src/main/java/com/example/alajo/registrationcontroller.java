package com.example.alajo;

import com.example.alajo.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class registrationcontroller {
    @FXML
    private TextField phfield;
    @FXML
    private TextField idfield;
    @FXML
    private TextField emailfield;
    @FXML
    private TextField passfield;
    @FXML
    private TextField addressfield;
    @FXML
    private TextField cpassfield;
    @FXML
    private Label hiddenerror;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void registerme(ActionEvent event)throws IOException  {
        String regfaith = "Registration Successful";
        String opout= "";
        String userid ="";
        String phone = phfield.getText();
        String username = idfield.getText();
        String password = passfield.getText();
        String confirm = cpassfield.getText();
        String address = addressfield.getText();
        String email = emailfield.getText();
        if (password.equals(confirm)){
            try {
                opout = User.createuser(username,password,address,email,phone);
                if(opout.equals(regfaith)){
                    hiddenerror.setText(regfaith);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
                    root = loader.load();
                    userlogincontroller scene3Controller = loader.getController();
                    scene3Controller.setusername(username);
                    //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }else{
                    hiddenerror.setText("Username or Email or Password is already Taken");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

        }

    }
   /* public void loginutility(ActionEvent event)throws IOException {
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/
}
