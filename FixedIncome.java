/*
Frame for user to navigate what they want to do for asset class fixed income 
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

public class FixedIncome extends JFrame implements ActionListener
{

  // label 
  private JLabel fixIncText;
  private JLabel moneyText;  
  
  // panel 
  private JPanel buttonPanel;

  // buttons
  private JButton seeTable;
  private JButton addAsset;
  private JButton deleteAsset;
  private JButton updateAsset;

  public FixedIncome()
  {
    super("Fixed Income");
    this.setBounds(368, 446, 704, 341);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    RebalanceCalc objRC = new RebalanceCalc();
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // construct header 
    this.moneyText = new JLabel("<html><center> Current Fixed Money: " 
      + objIP.getSumCurrentFixed() + "        " + " Allocated Fixed Money: " + objRC.getRebalanceFixed()
      + " </center></html>", SwingConstants.CENTER);
    moneyText.setFont(Welcome.WORD_FONT);
    moneyText.setForeground(Welcome.EGGSHELL);
    
    this.fixIncText = new JLabel("Fixed Income Portfolio", SwingConstants.CENTER);
    fixIncText.setFont(Welcome.WORD_FONT);
    fixIncText.setForeground(Welcome.EGGSHELL);

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

    // checking conditionals to see if met 
    TurnoverCalc objTC = new TurnoverCalc();

    if (objIP.getSumCurrentFixed() > objTC.getFixedTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The fixed income class has reached target turnover !");
    }
    
    DangerDecreaseCalc objDC = new DangerDecreaseCalc();
    
    if (objDC.getFixedDecrease() > objIP.getSumCurrentFixed())
    {
      System.out.println("decrease reached");
      new DangerDecreaseWarning("The fixed income class has dangerously decreased ...");
    }
    
    // adding components 
    this.add(fixIncText, BorderLayout.NORTH);
    this.add(moneyText, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new FixedIncome();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons
    Object command = e.getSource();

    double fixedSum;

    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    TurnoverCalc objT = new TurnoverCalc();
    DangerDecreaseCalc objD = new DangerDecreaseCalc();

    fixedSum = objIP.getSumCurrentCrypto();
    
    if (command == seeTable)
    {
      // db info for fixed income 
      String dbName = "Investment Portfolio";
      String tableName = "FixedIncome";
      String[] columnNames =
      {
        "Name", "Date_Bought", "Quantity", "Initial_Price",
        "Current_Price"
      };

      new FixedIncomeTable(dbName, tableName, columnNames);
    }
    // open for adding asset for fixed income 
    else if (command == addAsset)
    {
      new FixedIncomeAdd();
    }
    // open frame for deleting asset for fixed income 
    else if (command == deleteAsset)
    {
      new FixedIncomeDelete();
    }
    // open frame for updating asset for fixed income 
    else if (command == updateAsset)
    {
      new FixedIncomeUpdate();
    }
  }
}
