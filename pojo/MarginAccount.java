package pojo;

import java.math.BigDecimal;

public class MarginAccount extends TradeAccount  {

     private BigDecimal margin;

     public BigDecimal getMargin() {                             // Getter voor margin
         return margin;
     }

     public void setMargin(BigDecimal margin) {                 // Setter voor margin
         this.margin = margin;
     }

     public MarginAccount(String id, BigDecimal margin) {          // Constructor voor MarginAccount   
         super(id);                                                // Aanroep van de constructor van de superklasse TradeAccount
         this.margin = margin;
     }

     @Override
     public TradeAccount clone(){                                    // Implementatie van de abstracte clone-methode
        return new MarginAccount(this.getId(), this.margin);         // De clone-methode maakt een nieuwe MarginAccount aan met dezelfde id en margin als het huidige object
     }
     
     @Override
     public String toString() {                                       // Override van de toString-methode om de MarginAccount-gegevens weer te geven als String
         return "MarginAccount{" +
                 "id='" + getId() + '\'' +
                 ", margin=" + margin +
                 '}';
     }


}
