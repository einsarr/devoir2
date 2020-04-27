package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dao.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Utilisateur;
import services.RessourceService;
import utilitaire.UtilitaireFX;

/**
 *
 * @author SARR
 */
public class C_login implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private JFXButton btnSeConnecter;
    @FXML
    private JFXTextField txtLogin;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnFermer;
    @FXML
    private Label txtErreur;
    RessourceService rs;
    private static C_login instance;
    private Utilisateur user;
    private utilitaire.UtilitaireFX utilitaire =  new UtilitaireFX();
    public static C_login getInstance() {
        return instance;
    }

    public Utilisateur getUser() {
        return user;
    }

    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtErreur.setVisible(false);
        rs = new RessourceService();
        instance = this;
    }    


    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void handleConnexion(MouseEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException {
         if(txtLogin.getText().isEmpty() || txtPassword.getText().isEmpty())
        {
            txtErreur.setVisible(true);
            txtErreur.setText("Veuillez saisir tous les champs svp.");
        }
        else{
            String login = txtLogin.getText();
            String password = txtPassword.getText();
            user = rs.seConnecter(login, password);
            if(user ==null)
            {
                txtErreur.setText("Login ou mot de passe incorrecte");
                txtErreur.setVisible(true);
            }else{
                if(user.getEtat().compareToIgnoreCase("Actif")==0)
                {
                    utilitaire.changeView(txtErreur, "V_dashboad");
                }
                else{
                    txtErreur.setText("Utilisateur bloqué");
                    txtErreur.setVisible(true);
                }
            }
        }
    }
    
}
