/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Agence;
import models.Utilisateur;
import services.RessourceService;
import services.TransactionService;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_agence implements Initializable {

    @FXML
    private TextField txtLibelle;
    @FXML
    private TableColumn<Agence, String> colNum;
    @FXML
    private TableColumn<Agence, String>  colLibelle;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<Agence> tvAgences;
    utilitaire.UtilitaireFX util;
    TransactionService transS;
    RessourceService  ressS;
    ObservableList<Agence> obsAgences;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private TableColumn<Agence, String>  colEmail;
    @FXML
    private TableColumn<Agence, String>  colAdresse;
    @FXML
    private TableColumn<Agence, String>  colTelephone;
    utilitaire.UtilitaireFX utilit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transS = new TransactionService();
        ressS = new RessourceService();
        utilit = new UtilitaireFX();
        loadTableView();
    }    
    private void clear()
    {
        txtLibelle.clear();
        txtTelephone.clear();
        txtEmail.clear();
        txtAdresse.clear();
    }
    @FXML
    private void handleSaveProfil(ActionEvent event) {
        String libelle=txtLibelle.getText(),telephone=txtTelephone.getText(),email=txtEmail.getText(),adresse=txtAdresse.getText();
        Agence agence = new Agence(libelle, telephone, email, adresse);
        int id = ressS.creerAgence(agence);
        if(id!=0)
        {
            utilit.messagesuccess("Agence enrégistrer avec succès");
            agence.setId(id);
            obsAgences.add(agence);
            clear();
        }
    }
    public void loadTableView()
    {
        obsAgences =FXCollections.observableArrayList(ressS.listeAgences());
        colNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        tvAgences.setItems(obsAgences);
    }
}
