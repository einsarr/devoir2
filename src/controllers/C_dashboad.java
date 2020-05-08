/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.Utilisateur;
import utilitaire.UtilitaireFX;

/**
 * FXML Controller class
 *
 * @author SARR
 */
public class C_dashboad implements Initializable {

    @FXML
    private Text lblLogin;
    @FXML
    private Hyperlink linkProfil;
    @FXML
    private Hyperlink linkCompte;
    @FXML
    private Hyperlink linkUtilisateur;
    @FXML
    private Hyperlink linkClient;
    @FXML
    private Hyperlink linkGuichet;
    @FXML
    private Hyperlink linkAgence;
    @FXML
    private Hyperlink linkAffectationAgence;
    @FXML
    private Hyperlink linkAffectationGuichet;
    @FXML
    private Hyperlink linkTransaction;
    @FXML
    private Text lblLogin2;
    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Text lblProfil;
    private static Utilisateur userConnect;
    @FXML
    private Pane panelSecurite;
    @FXML
    private Pane panelParametrage;
    @FXML
    private Pane panelTransaction;
    private utilitaire.UtilitaireFX utilitaire =  new UtilitaireFX();
    private Hyperlink buttonDeconnecter;
    @FXML
    private Label btnDeconnection;
    @FXML
    private Hyperlink linkAddCompte;
    @FXML
    private Pane panelClient;
    @FXML
    private Hyperlink linkMesComptes;
    @FXML
    private Hyperlink linkBloquerUser;
    @FXML
    private Hyperlink linkBloquerCompte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblLogin.setText(C_login.getInstance().getUser().getPrenom()+" "+C_login.getInstance().getUser().getNom());
        lblProfil.setText(C_login.getInstance().getUser().getProfil().getLibelle());
        userConnect = C_login.getInstance().getUser();
        verifProfil();
    }    

    private void verifProfil()
    {
        boolean boolCaissier = userConnect.getProfil().getLibelle().compareToIgnoreCase("caissier")==0; 
        boolean boolRespAg = userConnect.getProfil().getLibelle().compareToIgnoreCase("Responsable_agence")==0;
        boolean boolClient = userConnect.getProfil().getLibelle().compareToIgnoreCase("Client")==0;
        boolean boolAdmin_syst = userConnect.getProfil().getLibelle().compareToIgnoreCase("Admin_systeme")==0;
        panelParametrage.setDisable(true);
        panelSecurite.setDisable(true);
        panelTransaction.setDisable(true);
        panelClient.setVisible(false);
        if(boolAdmin_syst)
        {
            panelParametrage.setDisable(false);
            panelSecurite.setDisable(false);
            panelTransaction.setDisable(false);
        }else if(boolCaissier)
        {
           panelTransaction.setDisable(false);
        }
        else if(boolRespAg)
        {
            panelParametrage.setDisable(false);
            panelTransaction.setDisable(false);
        }else if(boolClient)
        {
            panelClient.setVisible(true);
            panelParametrage.setVisible(false);
            panelSecurite.setVisible(false);
            panelTransaction.setVisible(false);
        }
    }


    @FXML
    private void handleShowViewProfil(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_profil");
    }

    @FXML
    private void handleDeconnection(MouseEvent event) throws IOException{
        utilitaire.changeView(btnDeconnection, "V_login");
    }

    @FXML
    private void handleShowViewUser(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_utilisateur");
    }

    @FXML
    private void handleShowViewGuichet(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_guichet");
    }

    @FXML
    private void handleShowViewAgence(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_agence");
    }

    @FXML
    private void handleShowViewAffectAgence(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_affectation_agence");
    }

    @FXML
    private void handleShowViewAffectGuichet(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_affectation_guichet");
    }

    @FXML
    private void handleShowViewClient(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_client");
    }

    @FXML
    private void handleShowViewCompte(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "V_compte");
    }

    @FXML
    private void handleShowViewTransaction(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "V_transaction");
    }

    @FXML
    private void handleShowViewAddCompte(ActionEvent event) throws IOException{
        utilitaire.showView(anchorContent, "V_add_compte");
    }

    @FXML
    private void handleShowViewTransactionClient(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "handleShowViewTransactionClient");
    }

    @FXML
    private void handleShowViewBloquerUser(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "V_Bloquer_user");
    }

    @FXML
    private void handleShowViewBloquerCompte(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "V_bloquer_compte");
    }

    private void handleShowViewGuichetsAgence(ActionEvent event) throws IOException {
        utilitaire.showView(anchorContent, "V_guichet_agence");
    }
    
}
