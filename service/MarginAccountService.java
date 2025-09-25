package service;
import java.math.BigDecimal;
import pojo.MarginAccount;
import pojo.TradeAccount;
import repository.TradeAccountRepository;

public class MarginAccountService implements TradeAccountService {
    private TradeAccountRepository repository;

    public MarginAccountService (TradeAccountRepository repository) {           // Constructor voor MarginAccountService
        this.repository = repository;   
    }

    public void createTradeAccount(MarginAccount account) {                     // Methode om een nieuwe MarginAccount aan te maken en op te slaan in de repository
        repository.createTradeAccount(account);                                 // Voegt de account toe aan de repository   
    }

    public MarginAccount retrieveTradeAccount(String id) {                          // Methode om een MarginAccount op te halen uit de repository 
        TradeAccount account = repository.retrieveTradeAccount(id);                 // Haalt de account op uit de repository
        return (account instanceof MarginAccount) ? (MarginAccount) account : null; // Controleert of de account een MarginAccount is en retourneert het, anders null
    }

    public void updateTradeAccount(MarginAccount account) {                       // Methode om een bestaande MarginAccount bij te werken in de repository
        repository.updateTradeAccount(account);                                   // Update de account in de repository   
    }

    public void deleteTradeAccount(String id) {                                   // Methode om een MarginAccount te verwijderen uit de repository
        repository.deleteTradeAccount(id);                                        // Verwijdert de account uit de repository
    }

    @Override
    public void deposit(String id, BigDecimal amount) {                                                // Methode voor het storten van een bedrag op een MarginAccount
        TradeAccount account = repository.retrieveTradeAccount(id);                                    // Haal de TradeAccount op uit de repository
        if (account instanceof MarginAccount) {                                                        // Controleer of het account een MarginAccount is
            ((MarginAccount) account).setMargin(((MarginAccount) account).getMargin().add(amount));    // Voeg het bedrag toe aan de margin
            repository.updateTradeAccount(account);                                                          // Update de account in de repository
        } else {
            throw new IllegalArgumentException("Account is not a Margin Account");
        }
    }

    @Override
    public void withdraw(String id, BigDecimal amount){
        TradeAccount account = repository.retrieveTradeAccount(id);                                               // Haal de TradeAccount op uit de repository
        if (account instanceof MarginAccount) {                                                                   // Controleer of het account een MarginAccount is
            if (((MarginAccount) account).getMargin().compareTo(amount) >= 0) {                                   // Controleer of er voldoende margin is om het bedrag op te nemen
                ((MarginAccount) account).setMargin(((MarginAccount) account).getMargin().subtract(amount));      // Trek het bedrag af van de margin
                repository.updateTradeAccount(account);                                                            // Update de account in de repository
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } else {
            throw new IllegalArgumentException("Account is not a Margin Account");
        }
    }

   




}