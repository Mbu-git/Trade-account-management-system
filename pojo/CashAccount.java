package pojo;

import java.math.BigDecimal;


public class CashAccount extends TradeAccount{
    private BigDecimal cashBalance;

    public BigDecimal getcashBalance() {                             // Getter voor cashBalance
        return cashBalance;
    }
    public void setcashBalance(BigDecimal balance) {                 // Setter voor cashBalance
        this.cashBalance = balance;
    }

    public CashAccount(String id, BigDecimal cashBalance) {          // Constructor voor CashAccount   
        super(id);                                                   // Aanroep van de constructor van de superklasse TradeAccount
        this.cashBalance = cashBalance;
    }

    @Override
    public TradeAccount clone() {                                    // Implementatie van de abstracte clone-methode
        return new CashAccount(this.getId(), this.cashBalance);
    }
    // De clone-methode maakt een nieuwe CashAccount aan met dezelfde id en cashBalance als het huidige object
    // Dit is handig voor het maken van kopieën van objecten zonder de originele te beïnvloeden
    @Override
    public String toString() {                                       // Override van de toString-methode om de CashAccount-gegevens weer te geven als String
        return "CashAccount{" +
                "id='" + getId() + '\'' +
                ", cashBalance=" + cashBalance +
                '}';
    }

}
