/*
Frame for user to input the asset name they wish to update the price for 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CryptocurrencyUpdate extends JFrame implements ActionListener
{

  //textfields and labels 
  private JTextField nameText;
  private JLabel nameLabel;

  private JTextField currentPriceText;
  private JLabel currentPriceLabel;

  // panels
  private JPanel inputPanel;
  private JPanel input2Panel;
  private JPanel buttonPanel;

  // buttons
  private JButton enterButton;
  private JButton returnButton;

  public CryptocurrencyUpdate()
  {
    super("Update Asset for Cryptocurrency");
    this.setBounds(368, 192, 704, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // constructing buttons
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);
    this.enterButton = new JButton("Enter");
    enterButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(enterButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // constructing label and textfields 
    nameLabel = new JLabel("Input Asset Name");
    nameLabel.setForeground(Welcome.EGGSHELL);
    nameLabel.setFont(Welcome.WORD_FONT);
    nameText = new JTextField(7);

    currentPriceLabel = new JLabel("New Price");
    currentPriceLabel.setForeground(Welcome.EGGSHELL);
    currentPriceLabel.setFont(Welcome.WORD_FONT);
    currentPriceText = new JTextField(7);

    // construct panel for textfield 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(nameLabel);
    inputPanel.add(nameText);
    inputPanel.setBackground(Welcome.NAVY);
    
    this.input2Panel = new JPanel(new FlowLayout());
    input2Panel.add(currentPriceLabel);
    input2Panel.add(currentPriceText);
    input2Panel.setBackground(Welcome.NAVY);

    // adding components 
    this.add(inputPanel, BorderLayout.NORTH);
    this.add(input2Panel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new FixedIncomeUpdate();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();

    // db info 
    String dbName = "Investment Portfolio";
    String tableName = "Crypto";
    String[] columnNames =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    String name;
    double currentPrice;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();

    if (command == enterButton)
    {
      // sql statement 
      String query = "UPDATE Crypto SET Current_Price = ? WHERE Name = ?";
      name = nameText.getText();
      currentPrice = Double.parseDouble(currentPriceText.getText());

      this.nameText.setText("");
      this.currentPriceText.setText("");
      try
      {
        // prepare statement 
        PreparedStatement ps = myDbConn.prepareStatement(query);

        // enter data into query 
        ps.setDouble(1, currentPrice);
        ps.setString(2, name);

        // execute query 
        ps.executeUpdate();
        System.out.println("Data updated succesfully");
        
        // calculating these classes to make sure these are false
        InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
        
        TurnoverCalc objTC = new TurnoverCalc();

        if (objIP.getSumCurrentCrypto() > objTC.getCryptoTurnover())
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
      }
      // robustness 
      catch (SQLException se)
      {
        System.out.println("Error updating class data ");
      }
    }
    // disposing frame 
    else if (command == returnButton)
    {
      this.dispose();
    }
  }
}
