/*
comparing estimate for asset class crypto 
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EstimateCompareCrypto extends JFrame implements ActionListener
{
  // label 
  private JLabel estimateCompareDisplayText;
  private JLabel estimateCompareDisplayText2;

  // button
  private JButton returnButton;

  // panel 
  private JPanel buttonPanel;

  double sumCurrentCrypto;
    
  public EstimateCompareCrypto()
  {
    super("Display the Estimate Faring for Stocks");
    this.setBounds(511, 489, 425, 344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());
    
    // creating objects
    EstimateCalc objEstCalc = new EstimateCalc();
    InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
    
    String currentMoneyDb = "Investment Portfolio";
    String currentMoneyTable = "CurrentMoney";
    String[] currentMoneyColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Total", "Constant"
    };
    
    JavaDatabase objDb = new JavaDatabase(currentMoneyDb);
    Connection myDbConn = objDb.getDbConn();
    
    ArrayList<ArrayList<String>> currentMoneyData = objDb.getData(currentMoneyTable, currentMoneyColumn);
    
    // getting current money for fixed
    sumCurrentCrypto = Double.parseDouble(currentMoneyData.get(0).get(2));
    
    // constructing text
    this.estimateCompareDisplayText = new JLabel("<html><center> Your Estimate Was: " 
      + objEstCalc.getCryptoEstimateMoney() +" </center></html>", SwingConstants.CENTER); 
    estimateCompareDisplayText.setForeground(Welcome.EGGSHELL);
    estimateCompareDisplayText.setFont(Welcome.WORD_FONT);

    this.estimateCompareDisplayText2 = new JLabel("<html><center> The Actual Value Is: " 
      + sumCurrentCrypto +" </center></html>", SwingConstants.CENTER); 
    estimateCompareDisplayText2.setForeground(Welcome.EGGSHELL);
    estimateCompareDisplayText2.setFont(Welcome.WORD_FONT);
    
    // constructing buttons
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // adding components
    this.add(estimateCompareDisplayText, BorderLayout.NORTH);
    this.add(estimateCompareDisplayText2, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new EstimateCompareCrypto();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons
    Object command = e.getSource();
    if (command == returnButton)
    {
      this.dispose();
      new EstimateCompare();
    }
  }
}
