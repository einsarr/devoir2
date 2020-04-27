/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.*;
import services.RessourceService;
import services.TransactionService;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_compte implements Initializable {
    private utilitaire.UtilitaireFX utilitaire =  new UtilitaireFX();
    @FXML
    private TableColumn<Compte, String> colId;
    @FXML
    private TableColumn<Compte, String> colNumero;
    /**
     * Initializes the controller class.
     */
    TransactionService ts;
    RessourceService rs;
    @FXML
    private TableColumn<Compte, String>colClient;
    @FXML
    private TableColumn<Compte, String> colSolde;
    @FXML
    private TableColumn<Compte, String> colEtat;
    @FXML
    private TableView<Compte> tblvCompte;
    @FXML
    private TableColumn<Compte, String> colDateCreation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ts = new TransactionService();
        rs = new RessourceService();
        chargeTableView();
    }    

    public void chargeTableView()
    {
        List<Compte> comptes = ts.listeComptes();
        ObservableList<Compte> obsComptes = FXCollections.observableArrayList(comptes);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("client"));
        colEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        colSolde.setCellValueFactory(new PropertyValueFactory<>("solde"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        tblvCompte.setItems(obsComptes);
    }
}
