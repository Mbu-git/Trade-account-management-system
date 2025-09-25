package repository;

import java.util.HashMap;
import java.util.Map;

import pojo.TradeAccount;

public class TradeAccountRepository {

     private Map<String, TradeAccount> datastore = new HashMap<>();

     public void createTradeAccount(TradeAccount tradeAccount){
        TradeAccount clonedAccount = tradeAccount.clone();          // Maak een kloon van het TradeAccount object
        datastore.put(clonedAccount.getId(), clonedAccount);        // Voeg de gekloonde account toe aan de datastore met de id als key
     }

     public TradeAccount retrieveTradeAccount(String id){
        return this.datastore.get(id) == null ? null : this.datastore.get(id).clone(); // Haal de TradeAccount op uit de datastore en maak een kloon van het object. Als de TradeAccount niet bestaat, retourneer null
     }

     public void updateTradeAccount(TradeAccount tradeAccount){
        TradeAccount clonedAccount = tradeAccount.clone();             // Maak een kloon van het TradeAccount object
        datastore.put(clonedAccount.getId(), clonedAccount);          // Update de datastore met de gekloonde account
     }

     public void deleteTradeAccount(String id){
        datastore.remove(id);                                         // Verwijder de TradeAccount uit de datastore met de id
     }




}
