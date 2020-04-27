/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
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
import models.Profil;
import services.RessourceService;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_profil implements Initializable {

    @FXML
    private TextField txtLibelle;
    @FXML
    private TextField txtDescription;
    @FXML
    private TableView<Profil> tvProfils;
    @FXML
    private TableColumn<Profil, String> colNum;
    @FXML
    private TableColumn<Profil, String> colLibelle;
    @FXML
    private TableColumn<Profil, String> colDescription;
    RessourceService rs;
    ObservableList<Profil> obProfils;
    @FXML
    private Button btnAjout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rs = new RessourceService();
        obProfils =FXCollections.observableArrayList(rs.listeProfils());
        colNum.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tvProfils.setItems(obProfils);
    }    
    private void clear()
    {
        txtDescription.clear();
        txtLibelle.clear();
    }

    @FXML
    private void handleSaveProfil(ActionEvent event) {
        Profil p=new Profil(txtLibelle.getText(),txtDescription.getText());
        int id=rs.creerProfil(p);
        if(id!=0){
           Alert alert=new Alert(Alert.AlertType.INFORMATION);
           alert.setContentText("Profil Enregistré avec succès");
           alert.showAndWait();
           p.setId(id);
           obProfils.add(p);
           clear();
        }
    }
    
}
