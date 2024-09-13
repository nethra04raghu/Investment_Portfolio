/*
Frame for user to navigate what they want to do for asset class cryptocurrency 
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

public class Cryptocurrency extends JFrame implements ActionListener
{

  // label
  private JLabel cryptoText;
  private JLabel moneyText;

  // panel
  private JPanel buttonPanel;

  // button
  private JButton seeTable;
  private JButton addAsset;
  private JButton deleteAsset;
  private JButton updateAsset;

  public Cryptocurrency()
  {
    super("Cryptocurrency");
    this.setBounds(368, 446, 704, 341);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    RebalanceCalc objRC = new RebalanceCalc();
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // construct header 
    this.moneyText = new JLabel("<html><center> Current Crypto Money: " 
      + objIP.getSumCurrentCrypto() + "        " + " Allocated Crypto Money: " + objRC.getRebalanceCrypto()
      + " </center></html>", SwingConstants.CENTER);
    moneyText.setFont(Welcome.WORD_FONT);
    moneyText.setForeground(Welcome.EGGSHELL);
    
    this.cryptoText = new JLabel("Cryptocurrency Portfolio", SwingConstants.CENTER);
    cryptoText.setFont(Welcome.WORD_FONT);
    cryptoText.setForeground(Welcome.EGGSHELL);

    // construct the buttons
    this.seeTable = new JButton("See Table");
    seeTable.addActionListener(this);
    this.addAsset = new JButton("Add Asset");
    addAsset.addActionListener(this);
    this.deleteAsset = new JButton("Delete Asset");
    deleteAsset.addActionListener(this);
    this.updateAsset = new JButton("Update Asset");
    updateAsset.addActionListener(this);

    // construct the button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(seeTable);
    buttonPanel.add(addAsset);
    buttonPanel.add(deleteAsset);
    buttonPanel.add(updateAsset);
    buttonPanel.setBackground(Welcome.NAVY);

    TurnoverCalc objTC = new TurnoverCalc();

    if (objIP.getSumCurrentCrypto()> objTC.getCryptoTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The crypto class has reached target turnover !");
    }
    
    DangerDecreaseCalc objDC = new DangerDecreaseCalc();
    
    if (objDC.getCryptoDecrease() > objIP.getSumCurrentCrypto())
    {
      System.out.println("decrease reached");
      new DangerDecreaseWarning("The crypto class has dangerously decreased ...");
    }
    // adding components
    this.add(cryptoText, BorderLayout.NORTH);
    this.add(moneyText, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Cryptocurrency();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for general buttons
    Object command = e.getSource();
    
    // if user wants to see the table 
    if (command == seeTable)
    {
      // db info 
      String dbName = "Investment Portfolio";
      String tableName = "Crypto";
      String[] columnNames =
      {
        "Name", "Date_Bought", "Quantity", "Initial_Price",
        "Current_Price"
      };

      new CryptocurrencyTable(dbName, tableName, columnNames);
    }
    // new object of crypto add class for user to add asset 
    else if (command == addAsset)
    {
      new CryptocurrencyAdd();
    }
    // new object of delete asset class for user to delete asset 
    else if (command == deleteAsset)
    {
      new CryptocurrencyDelete();
    }
    // new object of update asset class for user to 
    else if (command == updateAsset)
    {
      new CryptocurrencyUpdate();
    }
  }
}
