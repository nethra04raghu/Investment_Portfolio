/*
Frame for user to navigate what they want to do for asset class stockss 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Stocks extends JFrame implements ActionListener
{

  // label 
  private JLabel stocksText;
  private JLabel moneyText;

  // panel 
  private JPanel buttonPanel;

  // button 
  private JButton seeTable;
  private JButton addAsset;
  private JButton deleteAsset;
  private JButton updateAsset;

  public Stocks()
  {
    super("Stocks");
    this.setBounds(368, 446, 704, 341);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // creating objects 
    RebalanceCalc objRC = new RebalanceCalc();
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();

    // construct header 
    this.moneyText = new JLabel("<html><center> Current Stock Money: "
      + objIP.getSumCurrentStock() + "        " + " Allocated Stock Money: " + objRC.getRebalanceStock()
      + " </center></html>", SwingConstants.CENTER);
    moneyText.setFont(Welcome.WORD_FONT);
    moneyText.setForeground(Welcome.EGGSHELL);

    // construct header
    this.stocksText = new JLabel("Stocks Portfolio", SwingConstants.CENTER);
    stocksText.setFont(Welcome.WORD_FONT);
    stocksText.setForeground(Welcome.EGGSHELL);

    // constructing buttons 
    this.seeTable = new JButton("See Table");
    seeTable.addActionListener(this);
    this.addAsset = new JButton("Add Asset");
    addAsset.addActionListener(this);
    this.deleteAsset = new JButton("Delete Asset");
    deleteAsset.addActionListener(this);
    this.updateAsset = new JButton("Update Asset");
    updateAsset.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(seeTable);
    buttonPanel.add(addAsset);
    buttonPanel.add(deleteAsset);
    buttonPanel.add(updateAsset);
    buttonPanel.setBackground(Welcome.NAVY);

    // making sure that program checks these conditionals
    TurnoverCalc objTC = new TurnoverCalc();

    if (objIP.getSumCurrentStock() > objTC.getStockTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The stock class has reached target turnover !");
    }

    DangerDecreaseCalc objDC = new DangerDecreaseCalc();
    
    if (objDC.getStockDecrease() > objIP.getSumCurrentStock())
    {
      System.out.println("decrease reached");
      new DangerDecreaseWarning("The stock class has dangerously decreased ...");
    }
    
    // adding components 
    this.add(stocksText, BorderLayout.NORTH);
    this.add(moneyText, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Stocks();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();

    double stockDanger;
    double stockSum;

    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    TurnoverCalc objT = new TurnoverCalc();
    DangerDecreaseCalc objD = new DangerDecreaseCalc();

    stockSum = objIP.getSumCurrentStock();
    stockDanger = objD.getStockDecrease();

    if (command == seeTable)
    {
      // db info 
      String dbName = "Investment Portfolio";
      String tableName = "Stocks";
      String[] columnNames =
      {
        "Name", "Date_Bought", "Quantity", "Initial_Price",
        "Current_Price"
      };

      new StockTable(dbName, tableName, columnNames);
    }
    // open frame adding asset for stock 
    else if (command == addAsset)
    {
      new StockAdd();
    }
    // open frame for deleting asset for stock 
    else if (command == deleteAsset)
    {
      new StockDelete();
    }
    // open for updating asset for stock 
    else if (command == updateAsset)
    {
      new StockUpdate();
    }
  }
}
