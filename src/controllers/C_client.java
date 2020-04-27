/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.*;
import services.RessourceService;
import services.TransactionService;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_client implements Initializable {
    TransactionService ts;
    RessourceService rs;
    @FXML
    private TableView<Client> tblvClient;
    @FXML
    private TableColumn<Client, String> colId;
    @FXML
    private TableColumn<Client, String> colCNI;
    @FXML
    private TableColumn<Client, String> colTelephone;
    /**
     * Initializes the controller class.
     */
    ObservableList<Client> obsClient;
    @FXML
    private TableColumn<Client, String> colPrenomNom;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ts = new TransactionService();
        rs = new RessourceService();
        chargerTableView();
    }    
    public void chargerTableView()
    {
        List<Client> clients = ts.listeClients();
        obsClient = FXCollections.observableArrayList(clients);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPrenomNom.setCellValueFactory(new PropertyValueFactory<>("user"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colCNI.setCellValueFactory(new PropertyValueFactory<>("cni"));
        tblvClient.setItems(obsClient);
    }
    
}
