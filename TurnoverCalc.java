/*
Calculations that calculate turnover prices for Turnover class 
 */
//package investmentportfolio;

import java.sql.Connection;
import java.util.ArrayList;

public class TurnoverCalc
{
  double stockTurnoverPercent;
  double bondTurnoverPercent;
  double cryptoTurnoverPercent;
  double fixedTurnoverPercent;
  
  double stockInitialSum;
  double bondInitialSum;
  double cryptoInitialSum;
  double fixedInitialSum;
  
  double stockTurnover;
  double bondTurnover;
  double cryptoTurnover;
  double fixedTurnover;
  
  public TurnoverCalc()
  {
    // db for turnover 
    String turnoverDb = "Investment Portfolio";
    String turnoverTable = "Turnover";
    String[] turnoverColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    // connection to db
    JavaDatabase objDb = new JavaDatabase(turnoverDb);
    Connection myDbConn = objDb.getDbConn();
    
    // arraylist for turnover 
    ArrayList<ArrayList<String>> turnoverData = objDb.getData(turnoverTable, 
      turnoverColumn); 
    
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // parsing values 
    stockTurnoverPercent = Double.parseDouble(turnoverData.get(0).get(0));
    bondTurnoverPercent = Double.parseDouble(turnoverData.get(0).get(1));
    cryptoTurnoverPercent = Double.parseDouble(turnoverData.get(0).get(2));
    fixedTurnoverPercent = Double.parseDouble(turnoverData.get(0).get(3));
    
    // calcualting turnover 
    stockTurnover = ((stockTurnoverPercent / 100) + 1 ) * objIP.getSumInitialStock();
    bondTurnover = ((bondTurnoverPercent / 100) + 1 ) * objIP.getSumInitialBond();
    cryptoTurnover = ((cryptoTurnoverPercent / 100) + 1 ) * objIP.getSumInitialCrypto();
    fixedTurnover = ((fixedTurnoverPercent / 100) + 1 ) * objIP.getSumInitialFixed();
    
    stockInitialSum = objIP.getSumInitialStock();
    bondInitialSum = objIP.getSumInitialBond();
    cryptoInitialSum = objIP.getSumInitialCrypto();
    fixedInitialSum = objIP.getSumInitialFixed();
    
    // if current value equals turnover value open according frame 
    if (stockInitialSum > stockTurnover)
    {
      new TurnoverWarning("The stock class has reached target turnover !");
    }
    else if (bondTurnover == bondInitialSum && bondInitialSum > bondTurnover)
    {
      new TurnoverWarning("The bond class has reached target turnover !");
    }
    else if (cryptoTurnover == cryptoInitialSum && cryptoInitialSum > cryptoTurnover)
    {
      new TurnoverWarning("The crypto class has reached target turnover !");
    }
    else if (fixedTurnover == fixedInitialSum && fixedInitialSum > fixedTurnover)
    {
      new TurnoverWarning("The fixed class has reached target turnover !");
    }
  }
  // set and get methods 
  public void setStockTurnover()
  {
    this.stockTurnover = stockTurnover;
  }
  
  public double getStockTurnover()
  {
    return this.stockTurnover;
  }
  public void setBondTurnover()
  {
    this.bondTurnover = bondTurnover;
  }
  
  public double getBondTurnover()
  {
    return this.bondTurnover;
  }
  public void setCryptoTurnover()
  {
    this.cryptoTurnover = cryptoTurnover;
  }
  
  public double getCryptoTurnover()
  {
    return this.cryptoTurnover;
  }
  public void setFixedTurnover()
  {
    this.fixedTurnover = fixedTurnover;
  }
  
  public double getFixedTurnover()
  {
    return this.fixedTurnover;
  }
  public static void main(String[] args)
  {
    
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    TurnoverCalc objTC = new TurnoverCalc();
    
    if (objIP.getSumCurrentStock() > objTC.getStockTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The stock class has reached target turnover !");
    }
    if (objIP.getSumCurrentBond() > objTC.getBondTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The bond class has reached target turnover !");
    }
    if (objIP.getSumCurrentCrypto() > objTC.getCryptoTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The crypto class has reached target turnover !");
    }
    if (objIP.getSumCurrentFixed() > objTC.getFixedTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The fixed income class has reached target turnover !");
    }
  }
}
