/*
Frame for user to navigate what they want to do for asset class bond 
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

public class Bonds extends JFrame implements ActionListener
{

  // label
  private JLabel bondsText;
  private JLabel moneyText;

  // panel 
  private JPanel buttonPanel;

  // button
  private JButton seeTable;
  private JButton addAsset;
  private JButton deleteAsset;
  private JButton updateAsset;

  public Bonds()
  {
    super("Bonds");
    this.setBounds(368, 446, 704, 341);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    RebalanceCalc objRC = new RebalanceCalc();
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // construct the text
    this.moneyText = new JLabel("<html><center> Current Bond Money: " 
      + objIP.getSumCurrentBond() + "        " + " \n Allocated Bond Money: " 
      + objRC.getRebalanceBond() + " </center></html>", SwingConstants.CENTER);
    moneyText.setFont(Welcome.WORD_FONT);
    moneyText.setForeground(Welcome.EGGSHELL);
    
    this.bondsText = new JLabel("Bonds Portfolio", SwingConstants.CENTER);
    bondsText.setFont(Welcome.WORD_FONT);
    bondsText.setForeground(Welcome.EGGSHELL);

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
    
    // calling calculation class to execute operation if met
    TurnoverCalc objTC = new TurnoverCalc();
    
    if (objIP.getSumCurrentBond() > objTC.getBondTurnover())
    {
      System.out.println("turnover reached");
      new TurnoverWarning("The bond class has reached target turnover !");
    }
    
    DangerDecreaseCalc objDC = new DangerDecreaseCalc();
    
    if (objDC.getBondDecrease() > objIP.getSumCurrentBond())
    {
      System.out.println("decrease reached");
      new DangerDecreaseWarning("The bond class has dangerously decreased ...");
    }

    // adding components
    this.add(bondsText, BorderLayout.NORTH);
    this.add(moneyText, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Bonds();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();

    // if user wants to see the table 
    if (command == seeTable)
    {
      // db info 
      String dbName = "Investment Portfolio";
      String tableName = "Bonds";
      String[] columnNames =
      {
        "Name", "Date_Bought", "Quantity", "Initial_Price",
        "Current_Price"
      };

      new BondTable(dbName, tableName, columnNames);
    }
    // new object of bond add class for user to add asset 
    else if (command == addAsset)
    {
      new BondAdd();
    }
    // new object of bond delete class for user to delete asset 
    else if (command == deleteAsset)
    {
      new BondDelete();
    }
    // new object of bond update for user to update asset 
    else if (command == updateAsset)
    {
      new BondUpdate();
    }
  }
}
