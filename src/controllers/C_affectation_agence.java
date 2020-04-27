/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import services.RessourceService;
import services.TransactionService;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_affectation_agence implements Initializable {

    @FXML
    private TableColumn<Affectation_agence, String> colId;
    @FXML
    private TableColumn<Affectation_agence, String> colUtilisateur;
    @FXML
    private TableColumn<Affectation_agence, String> colAgence;
    @FXML
    private TableColumn<Affectation_agence, String> colDateDebut;
    @FXML
    private TableColumn<Affectation_agence, String> colDateFin;
    @FXML
    private JFXComboBox<Agence> cboAgence;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXComboBox<Utilisateur> cboUtilisateur;
    @FXML
    private TableView<Affectation_agence> tblvAffectationAgence;
    RessourceService ress;
    TransactionService trans;
    utilitaire.UtilitaireFX utilit;
    ObservableList<Affectation_agence> obsAffecAgence;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ress = new RessourceService();
        trans = new TransactionService();
        utilit = new UtilitaireFX();
        chargerComboAgence();
        chargerComboUtilisateur();
        loadTableView();
    }    
    public void chargerComboUtilisateur()
    {
        List<Utilisateur> users=ress.listeUtilisateurs();
        ObservableList<Utilisateur> ObListUser = FXCollections.observableArrayList(users);
        cboUtilisateur.setItems(ObListUser);
    }
    public void chargerComboAgence()
    {
        List<Agence> agences=ress.listeAgences();
        ObservableList<Agence> ObListAgence = FXCollections.observableArrayList(agences);
        cboAgence.setItems(ObListAgence);
    }
    public void loadTableView()
    {
        obsAffecAgence =FXCollections.observableArrayList(ress.listeAffectationAgences());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAgence.setCellValueFactory(new PropertyValueFactory<>("agence"));
        colUtilisateur.setCellValueFactory(new PropertyValueFactory<>("user"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        tblvAffectationAgence.setItems(obsAffecAgence);
    }
    private void clear()
    {
        cboAgence.getSelectionModel().select(null);
        cboUtilisateur.getSelectionModel().select(null);
    }
    @FXML
    private void handleSave(ActionEvent event) {
        Utilisateur user = cboUtilisateur.getSelectionModel().getSelectedItem();
        Agence agence = cboAgence.getSelectionModel().getSelectedItem();
        Affectation_agence affagence = new Affectation_agence(agence, user);
        int id = ress.affecterUserAgence(affagence);
        if(id!=0)
        {
            utilit.messagesuccess("Affectation effectué avec succès");
            affagence.setId(id);
            obsAffecAgence.add(affagence);
            clear();
        }
    }
}
