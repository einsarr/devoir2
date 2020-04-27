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
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import services.*;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_utilisateur implements Initializable {

    @FXML
    private TableView<Utilisateur> tblvUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> colId;
    @FXML
    private TableColumn<Utilisateur, String> colNom;
    @FXML
    private TableColumn<Utilisateur, String> colPrenom;
    @FXML
    private TableColumn<Utilisateur, String> colLogin;
    @FXML
    private TableColumn<Utilisateur, String> colEtat;
    @FXML
    private TableColumn<Utilisateur, String> colProfil;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXComboBox<Profil> cboProfil;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXComboBox<String> cboEtat;
    @FXML
    private JFXPasswordField txtPassword;
    utilitaire.UtilitaireFX util;
    TransactionService transS;
    RessourceService  ressS;
    ObservableList<Utilisateur> obsUtilisateurs;
    @FXML
    private JFXComboBox<Agence> cboAgence;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util = new UtilitaireFX();
        chargerComboString();
        chargerComboProfil();
        loadTableView();
        chargerComboAgence();
    }    
    public void chargerComboProfil()
    {
        ressS = new RessourceService();
        List<Profil> profils=ressS.listeProfils();
        ObservableList<Profil> ObListProfil = FXCollections.observableArrayList(profils);
        cboProfil.setItems(ObListProfil);
    }
    public void chargerComboAgence()
    {
        List<Agence> agences=ressS.listeAgences();
        ObservableList<Agence> ObListAgence = FXCollections.observableArrayList(agences);
        cboAgence.setItems(ObListAgence);
    }
    public void chargerComboString()
    {
        cboEtat.getItems().add("Actif");
        cboEtat.getItems().add("Inactif");
    }
    @FXML
    private void handleSaveUser(ActionEvent event) {
        String nom = txtNom.getText(),prenom=txtPrenom.getText(),login=txtLogin.getText(),
                password=txtPassword.getText(),etat=cboEtat.getSelectionModel().getSelectedItem();
        Profil profil = cboProfil.getSelectionModel().getSelectedItem();
        Agence agence = cboAgence.getSelectionModel().getSelectedItem();
        if(nom!="" && prenom!="" && login!="" && password!="" && etat!="")
        {
           Utilisateur user = new Utilisateur(nom, prenom, login, password, etat, profil, agence);
           int id = ressS.creerUtilisateur(user);
           if(id!=0)
           {
               util.messagesuccess("utilisateur enrégistrer avec succès");
               user.setId(id);
               obsUtilisateurs.add(user);
               clear();
           }
        }
    }
    public void loadTableView()
    {
        obsUtilisateurs =FXCollections.observableArrayList(ressS.listeUtilisateurs());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colProfil.setCellValueFactory(new PropertyValueFactory<>("profil"));
        tblvUtilisateur.setItems(obsUtilisateurs);
    }
    private void clear()
    {
        txtNom.clear();
        txtPrenom.clear();
        txtLogin.clear();
        txtPassword.clear();
        cboAgence.getSelectionModel().select(null);
        cboProfil.getSelectionModel().select(null);
        cboEtat.getSelectionModel().select("");
    }
    
}
