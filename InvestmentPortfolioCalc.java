/*
calculation class to store the total asset money per class and total investment 
money 
 */
//package investmentportfolio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InvestmentPortfolioCalc
{

  ArrayList<Double> stockCurrent = new ArrayList<>(50);
  ArrayList<Double> bondCurrent = new ArrayList<>(50);
  ArrayList<Double> cryptoCurrent = new ArrayList<>(50);
  ArrayList<Double> fixedIncCurrent = new ArrayList<>(50);

  ArrayList<Double> stockInitial = new ArrayList<>(50);
  ArrayList<Double> bondInitial = new ArrayList<>(50);
  ArrayList<Double> cryptoInitial = new ArrayList<>(50);
  ArrayList<Double> fixedInitial = new ArrayList<>(50);

  public double sumCurrentStock = 0;
  public double sumCurrentBond = 0;
  public double sumCurrentCrypto = 0;
  public double sumCurrentFixInc = 0;
  public double sumCurrentTotal = 0;

  public double sumInitialStock = 0;
  public double sumInitialBond = 0;
  public double sumInitialCrypto = 0;
  public double sumInitialFixed = 0;
  public double sumInitialTotal = 0;

  public InvestmentPortfolioCalc()
  { 
    // info for stocks data
    String stockDb = "Investment Portfolio";
    String stockTable = "Stocks";
    String[] stockColumn =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    // connection to database
    JavaDatabase objDb = new JavaDatabase(stockDb);
    Connection myDbConn = objDb.getDbConn();

    // stocks arraylist
    ArrayList<ArrayList<String>> stockData = objDb.getData(stockTable, stockColumn);

    // getting the current stock price value from datatable into new arraylist 
    for (int i = 0; i < stockData.size(); i++)
    {
      stockCurrent.add(Double.parseDouble(stockData.get(i).get(4)));
    }
    //System.out.println(objThisClass.stockCurrent);

    // summation of all members of current stock prices 
    for (int i = 0; i < stockCurrent.size(); i++)
    {
      sumCurrentStock = sumCurrentStock
        + stockCurrent.get(i);
    }

    // getting the initial stock price value from datatable into new arraylist 
    for (int i = 0; i < stockData.size(); i++)
    {
      stockInitial.add(Double.parseDouble(stockData.get(i).get(3)));
    }

    // summation of all members of initial stock prices 
    for (int i = 0; i < stockInitial.size(); i++)
    {
      sumInitialStock = sumInitialStock
        + stockInitial.get(i);
    }

    // info for bonds data
    String bondDb = "Investment Portfolio";
    String bondTable = "Bonds";
    String[] bondColumn =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    // bonds arraylist
    ArrayList<ArrayList<String>> bondData = objDb.getData(bondTable, bondColumn);

    // getting the current bond price value from datatable into new arraylist 
    for (int i = 0; i < bondData.size(); i++)
    {
      bondCurrent.add(Double.parseDouble(bondData.get(i).get(4)));
    }
    //System.out.println(objThisClass.bondCurrent);

    // summation of all members of current stock prices 
    for (int i = 0; i < bondCurrent.size(); i++)
    {
      sumCurrentBond = sumCurrentBond
        + bondCurrent.get(i);
    }

    // getting the initial bond price value from datatable into new arraylist 
    for (int i = 0; i < bondData.size(); i++)
    {
      bondInitial.add(Double.parseDouble(bondData.get(i).get(3)));
    }

    // summation of all members of initial bond prices 
    for (int i = 0; i < bondInitial.size(); i++)
    {
      sumInitialBond = sumInitialBond
        + bondInitial.get(i);
    }

    // info for crypto data
    String cryptoDb = "Investment Portfolio";
    String cryptoTable = "Crypto";
    String[] cryptoColumn =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    // crypto arraylist
    ArrayList<ArrayList<String>> cryptoData = objDb.getData(cryptoTable, cryptoColumn);

    // getting the current crypto price value from datatable into new arraylist 
    for (int i = 0; i < cryptoData.size(); i++)
    {
      cryptoCurrent.add(Double.parseDouble(cryptoData.get(i).get(4)));
    }
    //System.out.println(objThisClass.cryptoCurrent);

    // summation of all members of current crypto prices 
    for (int i = 0; i < cryptoCurrent.size(); i++)
    {
      sumCurrentCrypto = sumCurrentCrypto
        + cryptoCurrent.get(i);
    }

    // getting the initial crypto price value from datatable into new arraylist 
    for (int i = 0; i < cryptoData.size(); i++)
    {
      cryptoInitial.add(Double.parseDouble(cryptoData.get(i).get(3)));
    }

    // summation of all members of initial crypto prices 
    for (int i = 0; i < cryptoInitial.size(); i++)
    {
      sumInitialCrypto = sumInitialCrypto
        + cryptoInitial.get(i);
    }

    // info for fixed income data 
    String fixIncDb = "Investment Portfolio";
    String fixIncTable = "FixedIncome";
    String[] fixIncColumn =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    // fixed income arraylist 
    ArrayList<ArrayList<String>> fixIncData = objDb.getData(fixIncTable, fixIncColumn);

    // getting the current fixed income price value from datatable into new arraylist 
    for (int i = 0; i < fixIncData.size(); i++)
    {
      fixedIncCurrent.add(Double.parseDouble(fixIncData.get(i).get(4)));
    }

    // summation of all members of current stock prices 
    for (int i = 0; i < fixedIncCurrent.size(); i++)
    {
      sumCurrentFixInc = sumCurrentFixInc
        + fixedIncCurrent.get(i);
    }

    // getting the initial fixed income price value from datatable into new arraylist 
    for (int i = 0; i < fixIncData.size(); i++)
    {
      fixedInitial.add(Double.parseDouble(fixIncData.get(i).get(3)));
    }

    // summation of all members of initial fixed income prices 
    for (int i = 0; i < fixedInitial.size(); i++)
    {
      sumInitialFixed = sumInitialFixed
        + fixedInitial.get(i);
    }

    // summation of all the current asset classes
    sumCurrentTotal = sumCurrentStock
      + sumCurrentBond + sumCurrentCrypto
      + sumCurrentFixInc;

    // summation of all the initial asset classes
    sumInitialTotal = sumInitialStock
      + sumInitialBond + sumInitialCrypto
      + sumInitialFixed;
  }
  
  // set and get methods 
  public void setSumCurrentStock()
  {
    this.sumCurrentStock = sumCurrentStock;
  }
  
  public double getSumCurrentStock()
  {
    return this.sumCurrentStock;
  }
  
  public void setSumCurrentBond()
  {
    this.sumCurrentBond = sumCurrentBond;
  }

  public double getSumCurrentBond()
  {
    return this.sumCurrentBond;
  }
  
  public void setSumCurrentCrypto()
  {
    this.sumCurrentCrypto = sumCurrentCrypto;
  }
  
  public double getSumCurrentCrypto()
  {
    return this.sumCurrentCrypto;
  }
  
  public void setSumCurrentFixed()
  {
    this.sumCurrentFixInc = sumCurrentFixInc;
  }
  
  public double getSumCurrentFixed()
  {
    return this.sumCurrentFixInc;
  }
  
  public void setSumCurrentTotal()
  {
    this.sumCurrentTotal = sumCurrentTotal;
  }
  
  public double getSumCurrentTotal()
  {
    return this.sumCurrentTotal;
  }
  
  public void setSumInitialStock()
  {
    this.sumInitialStock = sumInitialStock;
  }
  
  public double getSumInitialStock()
  {
    return this.sumInitialStock;   
  }
  
  public void setSumInitialBond()
  {
    this.sumInitialBond = sumInitialBond;
  }
  
  public double getSumInitialBond()
  {
    return this.sumInitialBond;
  }
  
  public void setSumInitialCrypto()
  {
    this.sumInitialCrypto = sumInitialCrypto;
  }
  
  public double getSumInitialCrypto()
  {
    return this.sumInitialCrypto;
  }
  
  public void setSumInitialFixed()
  {
    this.sumInitialFixed = sumInitialFixed;
  }
  
  public double getSumInitialFixed()
  {
    return this.sumInitialFixed;
  }
  
  public void setSumInitialTotal()
  {
    this.sumInitialTotal = sumInitialTotal;
  }
  
  public double getSumInitialTotal()
  {
    return this.sumInitialTotal;
  }
  public static void main(String[] args)
  {
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
  }
}
