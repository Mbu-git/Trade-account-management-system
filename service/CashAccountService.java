package service;
import java.math.BigDecimal;

import pojo.CashAccount;
import pojo.TradeAccount;
import repository.TradeAccountRepository;

public class CashAccountService implements TradeAccountService {

    private TradeAccountRepository repository;

    public CashAccountService(TradeAccountRepository repository) {
        this.repository = repository;
    }
        
    public void createTradeAccount(CashAccount account) {                                  // Methode om een nieuwe CashAccount aan te maken en op te slaan in de repository
        repository.createTradeAccount(account);                                            // Voegt de account toe aan de repository   
    }

    public CashAccount retrieveTradeAccount(String id) {                                   // Methode om een CashAccount op te halen uit de repository 
        TradeAccount account = repository.retrieveTradeAccount(id);                        // Haalt de account op uit de repository
        return (account instanceof CashAccount) ? (CashAccount) account : null;            // Controleert of de account een CashAccount is en retourneert het, anders null
    }

    public void updateTradeAccount(CashAccount account) {                                  // Methode om een bestaande CashAccount bij te werken in de repository
        repository.updateTradeAccount(account);                                            // Update de account in de repository   
    }

    public void deleteTradeAccount(String id) {                                            // Methode om een CashAccount te verwijderen uit de repository
        repository.deleteTradeAccount(id);                                                 // Verwijdert de account uit de repository
    }


    @Override
    public void deposit(String id, BigDecimal amount){
        TradeAccount account = repository.retrieveTradeAccount(id);                       //Haalt de account op uit de repository.
        if(account instanceof CashAccount){                                               //Controleert of de account een CashAccount is. 
            CashAccount cashAccount = (CashAccount) account;
            cashAccount.setcashBalance(cashAccount.getcashBalance().add(amount));         //Voegt het bedrag toe aan de cash balance van de account.
            repository.updateTradeAccount(cashAccount);                                   //Update de account in de repository.
        } else {
            throw new IllegalArgumentException("Account is not a Cash Account");        //Exception als het account géén CashAccount is.
        }
    }

    @Override
    public void withdraw(String id, BigDecimal amount){
        TradeAccount account = repository.retrieveTradeAccount(id);                       //Haalt de account op uit de repository.
        if(account instanceof CashAccount){                                               //Controleert of de account een CashAccount is. 
            CashAccount cashAccount = (CashAccount) account;
            if(cashAccount.getcashBalance().compareTo(amount) >= 0){                       //Controleert of er voldoende saldo is om het bedrag op te nemen.
                cashAccount.setcashBalance(cashAccount.getcashBalance().subtract(amount)); //Trek het bedrag af van de cash balance van de account.
                repository.updateTradeAccount(cashAccount);                                //Update de account in de repository.
            } else {
                throw new IllegalArgumentException("Insufficient funds");                //Exception als er onvoldoende saldo is.
            }
        } else {
            throw new IllegalArgumentException("Account is not a Cash Account");         //Exception als het account géén CashAccount is.
        }
    }


}
