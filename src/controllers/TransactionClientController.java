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
import javafx.scene.layout.AnchorPane;
import models.Client;
import models.Transaction;
import models.Utilisateur;
import services.RessourceService;
import services.TransactionService;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class TransactionClientController implements Initializable {
private static Utilisateur userConnect;
    @FXML
    private AnchorPane tblTranastionClient;
    @FXML
    private TableColumn<Transaction, String> colId;
    @FXML
    private TableColumn<Transaction, String> colNumero;
    @FXML
    private TableColumn<Transaction, String> colType;
    ObservableList<Transaction> obsTransactions;
    @FXML
    private TableColumn<Transaction, String> colMontant;
    TransactionService  tserv;
    Client client;
    @FXML
    private TableView<Transaction> tblTransactionsClient;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userConnect = C_login.getInstance().getUser();
        loadTableView();
    }    
    public void loadTableView()
    {
        client = tserv.clientByUser(userConnect.getId());
        if(client!=null)
        {
            obsTransactions =FXCollections.observableArrayList(tserv.listeTransactionsByClient(client));
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
            colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            tblTransactionsClient.setItems(obsTransactions);
        }
    }
    
}
