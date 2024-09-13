/*
This creates the frame so that user can input the info needed for adding asset
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

public class CryptocurrencyAdd extends JFrame implements ActionListener
{

  // textfield + label
  private JTextField nameText;
  private JLabel nameLabel;

  private JTextField dateText;
  private JLabel dateLabel;

  private JTextField quantityText;
  private JLabel quantityLabel;

  private JTextField initialPriceText;
  private JLabel initialPriceLabel;

  private JTextField currentPriceText;
  private JLabel currentPriceLabel;

  // panel
  private JPanel inputPanel1;
  private JPanel inputPanel2;
  private JPanel buttonPanel;

  // button
  private JButton enterButton;
  private JButton returnButton;

  public CryptocurrencyAdd()
  {
    super("Add Asset for Cryptocurrency");
    this.setBounds(368, 192, 704, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // contructing buttons
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);
    this.enterButton = new JButton("Enter");
    enterButton.addActionListener(this);

    // constructing button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(enterButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // contructing label + textfields
    nameLabel = new JLabel("Asset Name");
    nameLabel.setFont(Welcome.SMALL_FONT);
    nameLabel.setForeground(Welcome.EGGSHELL);
    nameText = new JTextField(7);

    dateLabel = new JLabel("Date Bought");
    dateLabel.setFont(Welcome.SMALL_FONT);
    dateLabel.setForeground(Welcome.EGGSHELL);
    dateText = new JTextField(7);

    quantityLabel = new JLabel("Quantity Bought");
    quantityLabel.setFont(Welcome.SMALL_FONT);
    quantityLabel.setForeground(Welcome.EGGSHELL);
    quantityText = new JTextField(7);

    initialPriceLabel = new JLabel("Initial Price");
    initialPriceLabel.setFont(Welcome.SMALL_FONT);
    initialPriceLabel.setForeground(Welcome.EGGSHELL);
    initialPriceText = new JTextField(7);

    currentPriceLabel = new JLabel("Current Price");
    currentPriceLabel.setFont(Welcome.SMALL_FONT);
    currentPriceLabel.setForeground(Welcome.EGGSHELL);
    currentPriceText = new JTextField(7);

    // construct label for textfields 
    this.inputPanel1 = new JPanel(new FlowLayout());
    inputPanel1.add(nameLabel);
    inputPanel1.add(nameText);
    inputPanel1.add(dateLabel);
    inputPanel1.add(dateText);
    inputPanel1.setBackground(Welcome.NAVY);

    this.inputPanel2 = new JPanel(new FlowLayout());
    inputPanel2.add(quantityLabel);
    inputPanel2.add(quantityText);
    inputPanel2.add(initialPriceLabel);
    inputPanel2.add(initialPriceText);
    inputPanel2.add(currentPriceLabel);
    inputPanel2.add(currentPriceText);
    inputPanel2.setBackground(Welcome.NAVY);

    // adding components
    this.add(inputPanel1, BorderLayout.NORTH);
    this.add(inputPanel2, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new CryptocurrencyAdd();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    // db info
    String dbName = "Investment Portfolio";
    String tableName = "Crypto";
    String[] columnNames =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };
    String dbQuery = "INSERT INTO Crypto VALUES (?,?,?,?,?)";

    // connect to dbs
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();

    String assetName;
    String date;
    int quantity;
    double initialPrice;
    double currentPrice;

    // getting and printing user inputs 
    assetName = nameText.getText();
    date = dateText.getText();
    nameText.setText("");
    dateText.setText("");

    // command for return  
    if (command == returnButton)
    {
      this.dispose();
    }

    // command for entering values 
    if (command == enterButton)
    {
      // assigning values so that i can print values
      quantity = Integer.parseInt(quantityText.getText());
      initialPrice = Double.parseDouble(initialPriceText.getText());
      currentPrice = Double.parseDouble(currentPriceText.getText());
      quantityText.setText("");
      initialPriceText.setText("");
      currentPriceText.setText("");

      try
      {
        // prepare statement 
        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);
        // enter data into query
        ps.setString(1, assetName);
        ps.setString(2, date);
        ps.setInt(3, quantity);
        ps.setDouble(4, initialPrice);
        ps.setDouble(5, currentPrice);

        // execute the query 
        ps.executeUpdate();
        System.out.println("Data inserted successfully");

        // checking if these are true or false and acting accordingly
        
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
      catch (SQLException se)
      {
        System.out.println("Error inserting data");
      }
      // if the user did not enter number inform user to use numbers 
      catch (NumberFormatException nfe)
      {
        new Warning("<html><center> Please enter numbers for quantity and "
          + "initial price</center></html>");
      }

      // robustness (general exceptions) 
      catch (Exception exc)
      {
        new Warning("<html><center>Unexpected error, please try again !!!"
          + "</center></html>");
      }
    }
    this.validate();
    this.repaint();
  }
}
