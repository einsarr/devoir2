/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DaoMysql;
import dao.GuichetDao;
import java.net.URL;
import java.util.ArrayList;
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
public class C_transaction implements Initializable {
private static Utilisateur userConnect;
    @FXML
    private TableView<Transaction> tblvTransaction;
    @FXML
    private TableColumn<Transaction, String> colId;
    @FXML
    private TableColumn<Transaction, String> colCompte;
    @FXML
    private TableColumn<Transaction, String> colMontant;
    @FXML
    private TableColumn<Transaction, String> colTypeOperation;
    @FXML
    private TableColumn<Transaction, String> colDateOperation;
    @FXML
    private JFXTextField txtMontant;
    @FXML
    private JFXComboBox<String> cboTypeOperation;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXComboBox<Compte> cboCompte2;
    @FXML
    private JFXComboBox<Compte> cboCompte1;
    /**
     * Initializes the controller class.
     */
    ObservableList<Transaction> obsTrans;
    RessourceService rs;
    TransactionService ts;
    UtilitaireFX util = new UtilitaireFX();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userConnect = C_login.getInstance().getUser();
        rs = new RessourceService();
        ts = new TransactionService();
        chargerTableView();
        chargeComboTypeOp();
        chargeComboCompte();
        Desactiver();
    }    
    public void clear()
    {
        cboCompte1.getSelectionModel().select(null);
        cboCompte2.getSelectionModel().select(null);
        cboTypeOperation.getSelectionModel().select("");
        txtMontant.clear();
    }
    public void chargeComboTypeOp()
    {
        cboTypeOperation.getItems().add("Depot");
        cboTypeOperation.getItems().add("Retrait");
        cboTypeOperation.getItems().add("Virement");
    }
    public void chargeComboCompte()
    {
        List<Compte> comptes = ts.listeComptes();
        ObservableList<Compte> obsCompte = FXCollections.observableArrayList(comptes);
        cboCompte1.setItems(obsCompte);
        cboCompte2.setItems(obsCompte);
    }
    public void chargerTableView()
    {
        List<Transaction> transactions = ts.listeTransactions();
        obsTrans = FXCollections.observableArrayList(transactions);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCompte.setCellValueFactory(new PropertyValueFactory<>("compte"));
        colTypeOperation.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDateOperation.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        colMontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        tblvTransaction.setItems(obsTrans);
    }
    public void Desactiver()
    {
       cboCompte2.setDisable(true);
    }

    @FXML
    private void handleSaveOperation(ActionEvent event) {
        String typeTypeOp = cboTypeOperation.getSelectionModel().getSelectedItem();
        Compte compte1 = cboCompte1.getSelectionModel().getSelectedItem();
        Compte compte2 = cboCompte2.getSelectionModel().getSelectedItem();
        int montant = Integer.parseInt(txtMontant.getText());
        GuichetDao gdao = new GuichetDao(new DaoMysql());
        Guichet g = gdao.findById(1);
        Guichet guichet = userConnect.getGuichet();
        
        Transaction trans1 = new Transaction(montant, typeTypeOp, g, compte1);
        Transaction trans2 = new Transaction(montant, typeTypeOp, g, compte2);
        List<Transaction> listeTrans = new ArrayList<>();
        listeTrans.add(trans1);
        
            
        int id=0;
        if(trans1.getType().compareToIgnoreCase("Virement")==0)
        {
            trans1.setType("virement-");
            trans2.setType("virement+");
            id = ts.faireTransactionVir(trans1,trans2); 
            
        }
        if(trans1.getType().compareToIgnoreCase("retrait")==0)
        {
            id = ts.faireRetrait(trans1);
        }
        if(trans1.getType().compareToIgnoreCase("depot")==0)
        {
            id = ts.faireDepot(trans1);
        }
        util.messagesuccess("Opération enrégistrer avec succès");
        trans1.setId(id);
        obsTrans.add(trans1);
        clear();
    }

    @FXML
    private void onSelect(ActionEvent event) {
        if(cboTypeOperation.getSelectionModel().getSelectedItem().compareToIgnoreCase("VIREMENT")==0)
        {
            cboCompte2.setDisable(false);
        }else Desactiver();
    }
}
