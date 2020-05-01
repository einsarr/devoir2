package services;

import dao.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.*;

public class TransactionService implements ITransaction{
    TransactionDao tdao ;
    CompteDao cdao;
    CompteCheckDao ccdao;
    CompteEpargneDao cddao;
    ClientDao clDao;
    public TransactionService()
    {
        tdao = new TransactionDao(new DaoMysql());
        cdao = new CompteDao(new DaoMysql());
        ccdao = new CompteCheckDao(new DaoMysql());
        cddao = new CompteEpargneDao(new DaoMysql());
        clDao = new ClientDao(new DaoMysql());
        cddao = new CompteEpargneDao(new DaoMysql());
    }
    @Override
    public int faireTransaction(List<Transaction> transactions) {
        List<Transaction> liste = new ArrayList<>();
        for(Transaction t : transactions)
        {
            if(t.getType().compareToIgnoreCase("retrait")==0)
            {
                if(t.getCompte().getEtat().compareToIgnoreCase("actif")==0)
                cdao.debiterCompte(t.getCompte(), t.getMontant());
                else System.err.print("Le compte est désactivé");
            }
            if(t.getType().compareToIgnoreCase("depot")==0)
            {
                if(t.getCompte().getEtat().compareToIgnoreCase("actif")==0)
                cdao.crediterCompte(t.getCompte(), t.getMontant());
                else System.err.print("Le compte est désactivé");
            }
            /*
            if(t.getType().compareToIgnoreCase("virement")==0)
            {
               if(t.getLesComptes().get(0).getEtat().compareToIgnoreCase("actif")==0 || t.getLesComptes().get(1).getEtat().compareToIgnoreCase("actif")==0)
               cdao.virement(t.getLesComptes().get(0), t.getLesComptes().get(1), t.getMontant());
               else System.err.print("Vérifiez si l'un des comptes n'est pas désactivé");
            }*/
            liste.add(t);
        }
        return tdao.faireTransaction(liste);
    }
    
    
    public int faireDepot(Transaction transaction)
    {
        cdao.crediterCompte(transaction.getCompte(), transaction.getMontant());
        return tdao.create(transaction);
    }
    public int faireRetrait(Transaction transaction)
    {
        cdao.crediterCompte(transaction.getCompte(), transaction.getMontant());
        return tdao.create(transaction);
    }
    @Override
    public int creerCompte(Compte compte) {
        int ok=0;
        if(compte.getId()!=0){
            if(compte instanceof CompteCheck)
            {
                ok = ccdao.ajouterCompteCheck(((CompteCheck) compte));
            }else if(compte instanceof CompteEpargne){
                ok = cddao.ajouterCompteEpargne((CompteEpargne)compte);
            }
        }
        else {
            if(compte instanceof CompteCheck)
            {
                ok = ccdao.create((CompteCheck)compte);
            }else if(compte instanceof CompteEpargne){
                ok = cddao.create((CompteEpargne)compte);
            }
        }
        return ok;
    }

    @Override
    public int bloquerCompte(Compte compte) {
        if(compte.getEtat().compareToIgnoreCase("actif")==0){
            compte.setEtat("bloque");
        }else{
            compte.setEtat("actif");
        }
        return cdao.bloqueCompte(compte);
    }

   
    public int faireTransactionVir(Transaction transaction1,Transaction transaction2) {
        cdao.virement(transaction1.getCompte(), transaction2.getCompte(), transaction1.getMontant());
        tdao.create(transaction1);
        return tdao.create(transaction2);
    }

    @Override
    public List<Transaction> etatPeriodTransAgenceCompteCaisse(Date dateD, Date dateF, Agence agence, Compte compte, Utilisateur caissier) {
        List<Transaction> resultats=new ArrayList<>();
        List<Transaction> listeTransac = tdao.findAll();
        for(Transaction t : listeTransac)
        {
            if(t.getCreatedAt().before(dateD) && t.getCreatedAt().after(dateF) && t.getGuichet().getAgence().equals(agence) && t.getCompte().equals(compte)){
                listeTransac.add(t);
            }
        }
        return resultats;
    }

    @Override
    public List<Transaction> historiqueTransacCptClient(Client client, Compte compte) {
        List<Transaction> resultats=new ArrayList<>();
        List<Transaction> listeTransac = tdao.findAll();
        for(Transaction t : listeTransac)
        {
            if(t.getCompte().getClient().getId()==client.getId() && t.getCompte().getId()==compte.getId())
            {
                resultats.add(t);
            }
        }
        return resultats;
    }

    @Override
    public Compte rechercherCompteParNumero(String numero) {
        return cdao.findByNumero(numero);
    }

    @Override
    public List<Compte> listeComptes() {
        return cdao.findAll();
    }

    @Override
    public List<Transaction> listeTransactions() {
        return tdao.findAll();
    }

    @Override
    public List<Client> listeClients() {
        return clDao.findAll();
    }

    @Override
    public List<Transaction> listeTransactionsByClient(Client client) {
        return tdao.mesOperations(client);
    }

    @Override
    public Client clientByUser(int user_id) {
        return clDao.findByUser(user_id);
    }
    
}
