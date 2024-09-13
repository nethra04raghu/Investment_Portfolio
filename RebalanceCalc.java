/*
Calculating the rebalancing values for class "Rebalance"
 */
//package investmentportfolio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RebalanceCalc
{
  double stockPortion;
  double bondPortion;
  double cryptoPortion;
  double fixedPortion;
  
  double stockPortionDecimal;
  double bondPortionDecimal;
  double cryptoPortionDecimal;
  double fixedPortionDecimal;
  
  double sumCurrentTotal;
  
  double rebalanceStock;
  double rebalanceBond;
  double rebalanceCrypto;
  double rebalanceFixed;
  
  int constant = 0;
  
  public RebalanceCalc()
  {
    // db info for portion 
    String portionName = "Investment Portfolio";
    String portionTable = "Portion";
    String[] portionColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    // connection to db 
    JavaDatabase objDb = new JavaDatabase(portionName);
    Connection myDbConn = objDb.getDbConn();
    
    // arraylist for portion data 
    ArrayList<ArrayList<String>> portionData = objDb.getData(portionTable, portionColumn);
    
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // parsing values 
    stockPortion = Double.parseDouble(portionData.get(0).get(0));
    bondPortion = Double.parseDouble(portionData.get(0).get(1));
    cryptoPortion = Double.parseDouble(portionData.get(0).get(2));
    fixedPortion = Double.parseDouble(portionData.get(0).get(3));

    // calculate rebalance values 
    rebalanceStock = (stockPortion / 100) * objIP.getSumCurrentTotal();
    rebalanceBond = (bondPortion / 100) * objIP.getSumCurrentTotal();
    rebalanceCrypto = (cryptoPortion / 100) * objIP.getSumCurrentTotal();
    rebalanceFixed = (fixedPortion / 100) * objIP.getSumCurrentTotal();
  }
  public void setRebalanceStock()
  {
    this.rebalanceStock = rebalanceStock;
  }
  
  public double getRebalanceStock()
  {
    return this.rebalanceStock;
  }
    public void setRebalanceBond()
  {
    this.rebalanceBond = rebalanceBond;
  }
  
  public double getRebalanceBond()
  {
    return this.rebalanceBond;
  }
    public void setRebalanceCrypto()
  {
    this.rebalanceCrypto = rebalanceCrypto;
  }
  
  public double getRebalanceCrypto()
  {
    return this.rebalanceCrypto;
  }
    public void setRebalanceFixed()
  {
    this.rebalanceFixed = rebalanceFixed;
  }
  
  public double getRebalanceFixed()
  {
    return this.rebalanceFixed;
  }
   public static void main(String[] args)
  {
    RebalanceCalc objRC = new RebalanceCalc();
  }
}
