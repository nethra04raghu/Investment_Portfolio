/*
This is the frame where user can navigate to each of the asset classes
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

public class InvestmentPortfolio extends JFrame implements ActionListener
{

  // label 
  private JLabel investmentText;
  private JLabel moneyText;

  // button 
  private JButton stocksButton;
  private JButton bondsButton;
  private JButton cryptoButton;
  private JButton fixedIncomeButton;
  private JButton returnButton;

  // panel
  private JPanel buttonPanel;

  double totalMoney;
  
  public InvestmentPortfolio()
  {
    super("IP");
    this.setBounds(368, 113, 704, 276);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());
    
    // creating object to obtain current money value 
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    // constructing label 
    this.investmentText = new JLabel("<html><center> Investment "
      + "Portfolio </center></html>", SwingConstants.CENTER);
    investmentText.setForeground(Welcome.NAVY);
    
    this.moneyText = new JLabel("<html><center> Current Portfolio Money: " 
      + objIP.getSumCurrentTotal() +" </center></html>", 
      SwingConstants.CENTER);
    investmentText.setFont(Welcome.WORD_FONT);
    moneyText.setFont(Welcome.WORD_FONT);
    moneyText.setForeground(Welcome.NAVY);

    // constructing buttons 
    this.stocksButton = new JButton("Stocks");
    stocksButton.addActionListener(this);
    this.bondsButton = new JButton("Bonds");
    bondsButton.addActionListener(this);
    this.cryptoButton = new JButton("Cryptocurrency");
    cryptoButton.addActionListener(this);
    this.fixedIncomeButton = new JButton("Fixed Income");
    fixedIncomeButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel for general buttons
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.add(stocksButton);
    buttonPanel.add(bondsButton);
    buttonPanel.add(cryptoButton);
    buttonPanel.add(fixedIncomeButton);
    buttonPanel.setBackground(Welcome.NAVY);

    // adding components 
    this.add(moneyText, BorderLayout.CENTER);
    this.add(investmentText, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new InvestmentPortfolio();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    // directs user to stocks 
    if (command == stocksButton)
    {
      Stocks objStocks = new Stocks();
    }
    // directs user to bonds
    else if (command == bondsButton)
    {
      Bonds objBonds = new Bonds();
    }
    // directs user to crypto 
    else if (command == cryptoButton)
    {
      Cryptocurrency objCryptocurrency = new Cryptocurrency();
    }
    // directs user to fixed income 
    else if (command == fixedIncomeButton)
    {
      FixedIncome objFixInc = new FixedIncome();
    }
    // directs user back to welcome frame
    else if (command == returnButton)
    {
      this.dispose();
      new Welcome();
    }
  }

}
