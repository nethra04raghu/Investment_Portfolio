/*
Calculation class for danger decrease 
 */
//package investmentportfolio;

import java.sql.Connection;
import java.util.ArrayList;

public class DangerDecreaseCalc
{

  double stockDecreasePercent;
  double bondDecreasePercent;
  double cryptoDecreasePercent;
  double fixedDecreasePercent;

  double stockDecrease;
  double bondDecrease;
  double cryptoDecrease;
  double fixedDecrease;

  double stockInitialSum;
  double bondInitialSum;
  double cryptoInitialSum;
  double fixedInitialSum;

  public DangerDecreaseCalc()
  {
    // db info for danger decrease 
    String decreaseDb = "Investment Portfolio";
    String decreaseTable = "DangerDecrease";
    String[] decreaseColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };

    // db connection 
    JavaDatabase objDb = new JavaDatabase(decreaseDb);
    Connection myDbConn = objDb.getDbConn();

    // arraylist for danger decrease 
    ArrayList<ArrayList<String>> decreaseData = objDb.getData(decreaseTable,
      decreaseColumn);

    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // parsing relavent data 
    stockDecreasePercent = Double.parseDouble(decreaseData.get(0).get(0));
    bondDecreasePercent = Double.parseDouble(decreaseData.get(0).get(1));
    cryptoDecreasePercent = Double.parseDouble(decreaseData.get(0).get(2));
    fixedDecreasePercent = Double.parseDouble(decreaseData.get(0).get(3));
    
    stockInitialSum = objIP.getSumInitialStock();
    bondInitialSum = objIP.getSumInitialBond();
    cryptoInitialSum = objIP.getSumInitialCrypto();
    fixedInitialSum = objIP.getSumInitialFixed();
    
    // calculating the multiplier 
    stockDecrease = (1 - (stockDecreasePercent / 100)) * objIP.getSumInitialStock();
    bondDecrease = (1 - (bondDecreasePercent / 100)) * objIP.getSumInitialBond();
    cryptoDecrease = (1 - (cryptoDecreasePercent / 100)) * objIP.getSumInitialCrypto();
    fixedDecrease = (1 - (fixedDecreasePercent / 100)) * objIP.getSumInitialCrypto();
    
    // open warning frame for each asset class if danger decrease level reached 
    if (stockDecrease == stockInitialSum)
    {
      new DangerDecreaseWarning("Asset class stock has reached dangerous levels");
    }
    else if (bondDecrease == bondInitialSum)
    {
      new DangerDecreaseWarning("Asset class bond has reached dangerous levels");
    }
    else if (cryptoDecrease == cryptoInitialSum)
    {
      new DangerDecreaseWarning("Asset class crypto has reached dangerous levels");
    }
    else if (fixedDecrease == fixedInitialSum)
    {
      new DangerDecreaseWarning("Asset class fixed income has reached dangerous levels");
    }
  }
  public void setStockDecrease()
  {
    this.stockDecrease = stockDecrease;
  }
  
  public double getStockDecrease()
  {
    return this.stockDecrease;
  }
  public void setBondDecrease()
  {
    this.bondDecrease = bondDecrease;
  }
  
  public double getBondDecrease()
  {
    return this.bondDecrease;
  }
  public void setCryptoDecrease()
  {
    this.cryptoDecrease = cryptoDecrease;
  }
  
  public double getCryptoDecrease()
  {
    return this.cryptoDecrease;
  }
    public void setFixedDecrease()
  {
    this.fixedDecrease = fixedDecrease;
  }
  
  public double getFixedDecrease()
  {
    return this.fixedDecrease;
  }
  public static void main(String[] args)
  {
    DangerDecreaseCalc objDC = new DangerDecreaseCalc();
  }
}
