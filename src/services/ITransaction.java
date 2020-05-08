/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import java.util.List;
import models.*;

/**
 *
 * @author SARR
 */
public interface ITransaction {
    public int creerCompte(Compte compte);
    public int bloquerCompte(Compte compte);
    public String faireTransaction(List<Transaction> transactions);
    public List<Transaction> etatPeriodTransAgenceCompteCaisse(Date dateD,Date dateF,Agence agence,Compte compte,Utilisateur caissier);
    public List<Transaction> historiqueTransacCptClient(Client client,Compte compte);
    public Compte rechercherCompteParNumero(String numero);
    public List<Compte> listeComptes();
    public List<Transaction> listeTransactions();
    public List<Client> listeClients();
    public List<Transaction> listeTransactionsByClient(Client client);
    
    public Client clientByUser(int user_id);
}
