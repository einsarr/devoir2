/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import models.Compte;
import models.Utilisateur;
import services.RessourceService;
import services.TransactionService;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_bloquer_user implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXButton btnBloquer;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtSearchLogin;
    @FXML
    private Button btnOk;
    Utilisateur user;
    TransactionService transS;
    RessourceService  ressS;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transS = new TransactionService();
        ressS = new RessourceService();
    }    

    @FXML
    private void handleRechercheUser(ActionEvent event) {
        user= ressS.chercherUtilisateur(txtSearchLogin.getText());
        if(user!=null)
        {
            txtNom.setText(user.getNom());
            txtPrenom.setText(user.getPrenom());
        }
    }
     @FXML
    private void handleBloquerUser(ActionEvent event) {
         user.setEtat("inactif");
         int id= ressS.bloquerUtilisateur(user);
         if(id==1){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Utlisateur bloqué avec succès");
            alert.showAndWait();
         }
         
    }
    
}
