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
import models.Affectation_guichet;
import models.*;
import services.RessourceService;
import services.TransactionService;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_affectation_guichet implements Initializable {

    @FXML
    private TableView<Affectation_guichet> tblvAffectationGuichet;
    @FXML
    private TableColumn<Affectation_guichet, String> colId;
    @FXML
    private TableColumn<Affectation_guichet, String> colUtilisateur;
    @FXML
    private TableColumn<Affectation_guichet, String> colAgence;
    @FXML
    private TableColumn<Affectation_guichet, String> colDateDebut;
    @FXML
    private TableColumn<Affectation_guichet, String> colDateFin;
    @FXML
    private JFXComboBox<Agence> cboAgence;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXComboBox<Utilisateur> cboUtilisateur;
    @FXML
    private JFXComboBox<Guichet> cboGuichet;
    @FXML
    private TableColumn<Affectation_guichet, String> colGuichet;
    private TableView<Affectation_agence> tblvAffectationAgence;
    RessourceService ress;
    TransactionService trans;
    utilitaire.UtilitaireFX utilit;
    ObservableList<Affectation_guichet> obsAffecGuichet;
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
        chargerComboGuichet();
        loadTableView();
    }    

    @FXML
    private void handleSave(ActionEvent event) {
        Utilisateur user = cboUtilisateur.getSelectionModel().getSelectedItem();
        Agence agence = cboAgence.getSelectionModel().getSelectedItem();
        Guichet guichet = cboGuichet.getSelectionModel().getSelectedItem();
        Affectation_guichet affguichet = new Affectation_guichet(user, guichet, agence);
        int id = ress.affecterCaissierGuichet(affguichet);
        if(id!=0)
        {
            utilit.messagesuccess("Affectation effectué avec succès");
            affguichet.setId(id);
            obsAffecGuichet.add(affguichet);
            clear();
        }
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
    public void chargerComboGuichet()
    {
        List<Guichet> guichets=ress.listeGuichets();
        ObservableList<Guichet> ObListGuichet = FXCollections.observableArrayList(guichets);
        cboGuichet.setItems(ObListGuichet);
    }
    public void loadTableView()
    {
        obsAffecGuichet =FXCollections.observableArrayList(ress.listeAffectationGuichets());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAgence.setCellValueFactory(new PropertyValueFactory<>("agence"));
        colUtilisateur.setCellValueFactory(new PropertyValueFactory<>("user"));
        colGuichet.setCellValueFactory(new PropertyValueFactory<>("guichet"));
        colDateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        colDateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        tblvAffectationGuichet.setItems(obsAffecGuichet);
    }
    private void clear()
    {
        cboAgence.getSelectionModel().select(null);
        cboUtilisateur.getSelectionModel().select(null);
        cboAgence.getSelectionModel().select(null);
    }
    
}
