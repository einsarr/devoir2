/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import models.Compte;
import services.RessourceService;
import services.TransactionService;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_bloquer_compte implements Initializable {

    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXTextField txtCNI;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtSearchNumero;
    @FXML
    private Button btnOk;
    @FXML
    private JFXTextField txtSolde;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnBloquer;
    TransactionService transS;
    RessourceService  ressS;
    Compte cpt;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transS = new TransactionService();
        ressS = new RessourceService();
    }    
    @FXML
    private void handleRechercheCompte(ActionEvent event) {
        cpt= transS.rechercherCompteParNumero(txtSearchNumero.getText());
        if(cpt!=null)
        {
            txtNom.setText(cpt.getClient().getUser().getNom());
            txtPrenom.setText(cpt.getClient().getUser().getPrenom());
            txtTelephone.setText(cpt.getClient().getTelephone());
            txtLogin.setText(cpt.getClient().getUser().getLogin());
            txtCNI.setText(cpt.getClient().getCni());
            txtEmail.setText(cpt.getClient().getEmail());
            txtAdresse.setText(cpt.getClient().getAdresse());
            float sold=cpt.getSolde();
            String solde=String.valueOf(sold);
            txtSolde.setText(solde);
        }
    }

    @FXML
    private void handleBloquerCompte(ActionEvent event) {
         int id= transS.bloquerCompte(cpt);
         if(id==1){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Compte bloqué avec succès");
            alert.showAndWait();
         }
    }
   
}
