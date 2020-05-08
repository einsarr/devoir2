/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Agence;
import models.Guichet;
import models.Transaction;
import services.RessourceService;
import services.TransactionService;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_guichet implements Initializable {

    @FXML
    private TextField txtDescription;
    @FXML
    private TableColumn<Guichet, String> colDescription;
    @FXML
    private Button btnAjout;
    @FXML
    private TableView<Guichet> tvGuichets;
    @FXML
    private JFXComboBox<Agence> cboAgence;
    @FXML
    private JFXTextField txtNumero;
    @FXML
    private TableColumn<Guichet, String> colId;
    @FXML
    private TableColumn<Guichet, String> colNumero;
    @FXML
    private TableColumn<Guichet, String> colAgence;
    ObservableList<Guichet> obsGuichets;
    RessourceService ress;
    TransactionService transs;
    utilitaire.UtilitaireFX utilit;
    @FXML
    private JFXTextField txtSearchAgence;
    @FXML
    private Button btnOk;
    Agence agence;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ress = new RessourceService();
       transs = new TransactionService();
       utilit = new UtilitaireFX();
       loadTableView();
       loadComboAgence();
    }    

    @FXML
    private void handleSaveProfil(ActionEvent event) {
        int numero = Integer.parseInt(txtNumero.getText());
        String description=txtDescription.getText();
        Agence agence = cboAgence.getSelectionModel().getSelectedItem();
        Guichet guichet = new Guichet(numero, agence, description);
        int id = ress.creerGuichet(guichet);
        if(id!=0)
        {
            utilit.messagesuccess("Guichet enrégistrer avec succès");
            guichet.setId(id);
            obsGuichets.add(guichet);
            clear();
        }
    }
    public void loadComboAgence()
    {
        List<Agence> agences = ress.listeAgences();
        ObservableList<Agence> obsAgences = FXCollections.observableArrayList(agences);
        cboAgence.setItems(obsAgences);
    }
    public void loadTableView()
    {
        obsGuichets = FXCollections.observableArrayList(ress.listeGuichets());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colAgence.setCellValueFactory(new PropertyValueFactory<>("agence"));
        tvGuichets.setItems(obsGuichets);
    }
    public void clear()
    {
        txtNumero.clear();
        txtDescription.clear();
        cboAgence.getSelectionModel().select(null);
    }

    @FXML
    private void handleRechercheGuichet(ActionEvent event) {
        agence =  ress.recherchergenceParLibelle(txtSearchAgence.getText());
        if(agence!=null)
        loadTableViewRecherche();
        else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("L'agence saisie n'existe pas");
            alert.showAndWait();
        }
    }
    public void loadTableViewRecherche()
    {
        obsGuichets =FXCollections.observableArrayList(ress.guichets_agence(agence));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colAgence.setCellValueFactory(new PropertyValueFactory<>("agence"));
        tvGuichets.setItems(obsGuichets);
    }

   
}
