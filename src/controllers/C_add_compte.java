/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
public class C_add_compte implements Initializable {
    private utilitaire.UtilitaireFX utilitaire =  new UtilitaireFX();
    private Button btnAddCompte;
    @FXML
    private AnchorPane frmCompte;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXComboBox<String> cboEtat;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnAnnuler;
    @FXML
    private JFXTextField txtPrenom;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXComboBox<Profil> cboProfil;
    @FXML
    private JFXTextField txtCNI;
    @FXML
    private JFXComboBox<String> cboTypeCompte;
    @FXML
    private JFXTextField txtAdresse;
    @FXML
    private JFXTextField txtTelephone;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtFrais;
    @FXML
    private JFXTextField txtTaux;
    @FXML
    private JFXTextField txtSearchNumero;
    @FXML
    private Button btnOk;
    TransactionService transS;
    RessourceService  ressS;
    @FXML
    private JFXDatePicker dptDateFinBlocage;
    @FXML
    private JFXTextField txtSolde;
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transS = new TransactionService();
        ressS = new RessourceService();
        chargerComboProfil();
        chargerComboString();
        DesactiverChamps();
    } 
    public void chargerComboString()
    {
        cboEtat.getItems().add("Actif");
        cboEtat.getItems().add("Inactif");
        cboTypeCompte.getItems().add("COMPTE CHECK");
        cboTypeCompte.getItems().add("COMPTE EPARGNE");
    }
    public void chargerComboProfil()
    {
        ressS = new RessourceService();
        List<Profil> profils=ressS.listeProfils();
        ObservableList<Profil> ObListProfil = FXCollections.observableArrayList(profils);
        cboProfil.setItems(ObListProfil);
    }
    public void DesactiverChamps()
    {
        txtFrais.setDisable(true);
        txtTaux.setDisable(true);
        dptDateFinBlocage.setDisable(true);
        
    }
    @FXML
    public void onSelectCboType() {
        if(cboTypeCompte.getSelectionModel().getSelectedItem().compareToIgnoreCase("COMPTE CHECK")==0)
        {
           txtFrais.setDisable(false);
        }
        else{
           txtTaux.setDisable(false);
           dptDateFinBlocage.setDisable(false);
           txtFrais.setDisable(true);
        }
    }
    @FXML
    private void handleRechercheCompte(ActionEvent event) {
        String numero = txtSearchNumero.getText();
        Compte cpt= transS.rechercherCompteParNumero(numero);
        if(cpt!=null)
        {
            txtNom.setText(cpt.getClient().getUser().getNom());
            txtPrenom.setText(cpt.getClient().getUser().getPrenom());
            txtLogin.setText(cpt.getClient().getUser().getLogin());
            txtPassword.setText(cpt.getClient().getUser().getPassword());
            txtCNI.setText(cpt.getClient().getCni());
            txtTelephone.setText(cpt.getClient().getTelephone());
            txtAdresse.setText(cpt.getClient().getAdresse());
            txtEmail.setText(cpt.getClient().getEmail());
            cboEtat.getSelectionModel().select(cpt.getEtat());
            cboProfil.getSelectionModel().select(cpt.getClient().getUser().getProfil().getId()-1);
        }
    }

    @FXML
    private void handleSaveCompte(ActionEvent event) throws ParseException {
        String numero = txtSearchNumero.getText();
        Compte cpt= transS.rechercherCompteParNumero(numero);
        float solde=0,taux=0;
        int frais =0;
        Date date = new Date();
        String nom = txtNom.getText(),prenom=txtPrenom.getText(),login = txtLogin.getText(),password=txtPassword.getText(),
                cni = txtCNI.getText(),telephone=txtTelephone.getText(),adresse=txtAdresse.getText(),email=txtEmail.getText(),
                etat=cboEtat.getSelectionModel().getSelectedItem(),typeCompte = cboTypeCompte.getSelectionModel().getSelectedItem();
        
        if(txtFrais.getText().compareToIgnoreCase("")!=0){
            frais = Integer.parseInt(txtFrais.getText());
        }
        if(txtTaux.getText().compareToIgnoreCase("")!=0){
            solde = Float.parseFloat(txtSolde.getText());
            taux=Float.parseFloat(txtTaux.getText());
            
            LocalDate createdAt = dptDateFinBlocage.getValue();
            date = Date.from(createdAt.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        Profil p = cboProfil.getSelectionModel().getSelectedItem();
        Client client = new Client(adresse, cni, telephone, email, nom, prenom, login, password, etat, p);
        Compte compte = new Compte(numero, solde, date, client, etat);
        if(cpt!=null)
        {
            if(cpt.getId()!=0)
            {
                if(typeCompte.compareToIgnoreCase("COMPTE CHECK")==0)
                {
                    CompteCheck cptC = new CompteCheck(frais, compte);
                    transS.creerCompte(cptC);
                }
                else{
                    CompteEpargne cptE = new CompteEpargne(taux, compte);
                    transS.creerCompte(cptE);
                }
            }
        }
        else{
            if(typeCompte.compareToIgnoreCase("COMPTE CHECK")==0)
            {
                CompteCheck cptC = new CompteCheck(frais, compte, client, etat);
                transS.creerCompte(cptC);
            }
            else{
                CompteEpargne cptE = new CompteEpargne(taux, compte, client, etat);
                transS.creerCompte(cptE);
            }
        }
    }
}
