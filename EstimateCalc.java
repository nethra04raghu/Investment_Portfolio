/*
Estimate calculation class 
 */
//package investmentportfolio;

import java.sql.Connection;
import java.util.ArrayList;

public class EstimateCalc
{
  double stockEstimateDecimal;
  double bondEstimateDecimal;
  double cryptoEstimateDecimal;
  double fixedEstimateDecimal;

  double stockEstimateMultiplier;
  double bondEstimateMultiplier;
  double cryptoEstimateMultiplier;
  double fixedEstimateMultiplier;

  double stockEstimateMoney;
  double bondEstimateMoney;
  double cryptoEstimateMoney;
  double fixedEstimateMoney;
  
  double sumInitialStock;
  double sumInitialBond;
  double sumInitialCrypto;
  double sumInitialFixed;

  public EstimateCalc()
  {
    InvestmentPortfolioCalc objIPC = new InvestmentPortfolioCalc();

    // db for estimate 
    String estimateDb = "Investment Portfolio";
    String bondTable = "Estimate";
    String[] bondColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    //db connection 
    JavaDatabase objDb = new JavaDatabase(estimateDb);
    Connection myDbConn = objDb.getDbConn();

    // estimate arraylist
    ArrayList<ArrayList<String>> estimateData = objDb.getData(bondTable, bondColumn);
    
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // parsing values 
    sumInitialStock = objIP.getSumInitialStock();
    sumInitialBond = objIP.getSumInitialBond();
    sumInitialCrypto = objIP.getSumInitialCrypto();
    sumInitialFixed = objIP.getSumInitialFixed();
    
    // turning percentage into decimal 
    stockEstimateDecimal = (Double.parseDouble(estimateData.get(0).get(0))) / 100;
    bondEstimateDecimal = (Double.parseDouble(estimateData.get(0).get(1))) / 100;
    cryptoEstimateDecimal = (Double.parseDouble(estimateData.get(0).get(2))) / 100;
    fixedEstimateDecimal = (Double.parseDouble(estimateData.get(0).get(3))) / 100;

    // calculating the multiplier 
    stockEstimateMultiplier = stockEstimateDecimal + 1;
    bondEstimateMultiplier = bondEstimateDecimal + 1;
    cryptoEstimateMultiplier = cryptoEstimateDecimal + 1;
    fixedEstimateMultiplier = fixedEstimateDecimal + 1;

    // calcuating estimate money for each asset class 
    stockEstimateMoney = (sumInitialStock * stockEstimateMultiplier);
    bondEstimateMoney = (sumInitialBond * bondEstimateMultiplier);
    cryptoEstimateMoney = (sumInitialCrypto * cryptoEstimateMultiplier);
    fixedEstimateMoney = (sumInitialFixed * fixedEstimateMultiplier);
  }

  // set and get methods 
  public void setStockEstimateMoney()
  {
    this.stockEstimateMoney = stockEstimateMoney;
  }
  
  public double getStockEstimateMoney()
  {
    return this.stockEstimateMoney;
  }

  public void setBondEstimateMoney()
  {
    this.bondEstimateMoney = bondEstimateMoney;
  }

  public double getBondEstimateMoney()
  {
    return this.bondEstimateMoney;
  }

  public void setCryptoEstimateMoney()
  {
    this.cryptoEstimateMoney = cryptoEstimateMoney;
  }

  public double getCryptoEstimateMoney()
  {
    return this.cryptoEstimateMoney;
  }

  public void setFixedEstimateMoney()
  {
    this.fixedEstimateMoney = fixedEstimateMoney;
  }

  public double getFixedEstimateMoney()
  {
    return this.fixedEstimateMoney;
  } 
  
  public static void main(String[] args)
  {
    EstimateCalc objEstCalc = new EstimateCalc();
  }
}
