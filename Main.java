import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import pojo.CashAccount;
import pojo.MarginAccount;
import repository.TradeAccountRepository;
import service.CashAccountService;
import service.MarginAccountService;
import java.util.List;


public class Main {
    // Bestanden waarin accounts en transacties staan
    static Path[] paths = new Path[] {
        Paths.get("data/accounts.txt"),
        Paths.get("data/transactions.txt")
    };

    static TradeAccountRepository tradeAccountRepository = new TradeAccountRepository();
    static CashAccountService cashAccountService = new CashAccountService(tradeAccountRepository);
    static MarginAccountService marginAccountService = new MarginAccountService(tradeAccountRepository);

    public static void main(String[] args){
        try {
            loadTradeAccounts();
            applyTransactions();
            finalTest();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    public static void finalTest() throws IOException {
        System.out.println("Account A1234B Cash Balance: " + cashAccountService.retrieveTradeAccount("A1234B").getcashBalance());
        System.out.println("Account E3456F Cash Balance: " + cashAccountService.retrieveTradeAccount("E3456F").getcashBalance());
        System.out.println("Account I5678J Cash Balance: " + cashAccountService.retrieveTradeAccount("I5678J").getcashBalance());
        System.out.println("Account C2345D Margin: " + marginAccountService.retrieveTradeAccount("C2345D").getMargin());
        System.out.println("Account G4567H Margin: " + marginAccountService.retrieveTradeAccount("G4567H").getMargin());
    }

    public static void loadTradeAccounts(){
        try {
           List<String> lines = Files.readAllLines(Paths.get("data/accounts.txt"));     // Lees alle regels van het bestand accounts.txt
            for(String line : lines){
                String[] parts = line.split(" ");                               // Verdeel de lijn op basis van een scheidingsteken (bijv. komma)

                String accountType= parts[0];                                         // Het type account (CashAccount of MarginAccount)
                String id = parts[1];                                                 // De ID van het account 
                BigDecimal balance = new BigDecimal(parts[2]);                        // Het saldo van het account

                if(accountType.equals("CASH")){
                    CashAccount cashAccount = new CashAccount(id, balance);            // Maak een nieuw CashAccount object aan
                    cashAccountService.createTradeAccount(cashAccount);                // Voeg het account toe aan de repository
                } else if(accountType.equals("MARGIN")){
                    MarginAccount marginAccount = new MarginAccount(id, balance);      // Maak een nieuw MarginAccount object aan
                    marginAccountService.createTradeAccount(marginAccount);            // Voeg het account toe aan de repository
                } 
            }
        }catch (IOException e){
            System.out.println("Error reading accounts file: " + e.getMessage());       // Geef een foutmelding als er een probleem is met het lezen van het bestand
        }
    }

    public static void applyTransactions(){
        try{
            List<String> lines2 = Files.readAllLines(Paths.get("data/transactions.txt"));     // Lees alle regels van het bestand transactions.txt
            for(String line : lines2){
                String[] parts = line.split(" ");                                              // Verdeel de lijn op basis van een scheidingsteken (bijv. spatie)

                String accountType = parts[0];                                             // Het type account (CASH of MARGIN)
                String id = parts[1];                                                      // De ID van het account
                String transactionType = parts[2];                                         // Het type transactie (DEPOSIT of WITHDRAW)
                BigDecimal amount = new BigDecimal(parts[3]);                              // Het bedrag van de transactie

                if (accountType.equals("CASH")) {                                     // Controleer of het accounttype CASH is
                    if (transactionType.equals("DEPOSIT")) {                          // Controleer of het transactie type DEPOSIT is
                        cashAccountService.deposit(id, amount);                                // Voer een storting uit
                    } else if (transactionType.equals("WITHDRAWAL")) {
                        cashAccountService.withdraw(id, amount);                               // Voer een opname uit
                    } else {
                        System.out.println("Unknown transaction type: " + transactionType);    // Foutmelding bij onbekend transactie type
                    }
                    } else if (accountType.equals("MARGIN")) {                        // Controleer of het accounttype MARGIN is    
                    if (transactionType.equals("DEPOSIT")) {                          // Controleer of het transactie type DEPOSIT is   
                        marginAccountService.deposit(id, amount);                              // Voer een storting uit
                    } else if (transactionType.equals("WITHDRAWAL")) {
                        marginAccountService.withdraw(id, amount);                             // Voer een opname uit
                    } else {
                        System.out.println("Unknown transaction type: " + transactionType);    // Foutmelding bij onbekend transactie type
                    }
                }            
            }

        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage()); // Geef een foutmelding als er een probleem is met het lezen van het bestand
        }
    }
}
    

