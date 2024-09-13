/*
Frame for user to input their values for new rebalancing 
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Portion extends JFrame implements ActionListener
{

  // label 
  private JLabel portionText;

  // label and textfield 
  private JLabel stockLabel;
  private JTextField stockTextField;

  private JLabel bondLabel;
  private JTextField bondTextField;

  private JLabel cryptoLabel;
  private JTextField cryptoTextField;

  private JLabel fixIncLabel;
  private JTextField fixIncTextField;

  // panel 
  private JPanel inputPanel;
  private JPanel buttonPanel;

  // button
  private JButton enterButton;
  private JButton returnButton;

  // menu components 
  private JMenuBar menuBar;
  private JMenu navigationMenu;
  private JMenuItem helpItem;

  public Portion()
  {
    super("Portion");
    this.setBounds(374, 95, 701, 319);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // constructing label 
    this.portionText = new JLabel("Enter new portion percentages",
      SwingConstants.CENTER);
    portionText.setFont(Welcome.WORD_FONT);
    portionText.setForeground(Welcome.EGGSHELL);

    // constructing textfield and labels
    stockLabel = new JLabel("Stock");
    stockLabel.setFont(Welcome.SMALL_FONT);
    stockLabel.setForeground(Welcome.EGGSHELL);
    stockTextField = new JTextField(7);

    bondLabel = new JLabel("Bond");
    bondLabel.setFont(Welcome.SMALL_FONT);
    bondLabel.setForeground(Welcome.EGGSHELL);
    bondTextField = new JTextField(7);

    cryptoLabel = new JLabel("Crypto");
    cryptoLabel.setFont(Welcome.SMALL_FONT);
    cryptoLabel.setForeground(Welcome.EGGSHELL);
    cryptoTextField = new JTextField(7);

    fixIncLabel = new JLabel("Fixed Income");
    fixIncLabel.setFont(Welcome.SMALL_FONT);
    fixIncLabel.setForeground(Welcome.EGGSHELL);
    fixIncTextField = new JTextField(7);

    // constructing buttons
    this.enterButton = new JButton("Enter");
    enterButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panels 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(enterButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // constructing panel for textfields 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(stockLabel);
    inputPanel.add(stockTextField);
    inputPanel.add(bondLabel);
    inputPanel.add(bondTextField);
    inputPanel.add(cryptoLabel);
    inputPanel.add(cryptoTextField);
    inputPanel.add(fixIncLabel);
    inputPanel.add(fixIncTextField);
    inputPanel.setBackground(Welcome.NAVY);

    // constructing menu components 
    this.menuBar = new JMenuBar();
    this.navigationMenu = new JMenu("Navigation");
    this.helpItem = new JMenuItem("Help");
    helpItem.addActionListener(this);
    navigationMenu.add(helpItem);
    menuBar.add(navigationMenu);
    this.setJMenuBar(menuBar);

    // adding components 
    this.add(portionText, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Portion();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    String number1String;
    double stockPercentage;
    double bondPercentage;
    double cryptoPercentage;
    double fixIncPercentage;
    double total;
    String message;

    // db info for portion 
    String dbName = "Investment Portfolio";
    String tableName = "Portion";
    String[] columnNames =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };

    int constant;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    
    // dispose and open welcome frame 
    if (command == returnButton)
    {
      this.dispose();
      new Welcome();
    }

    // open help frame 
    if (command == helpItem)
    {
      new HelpPortion();
    }

    else if (command == enterButton)
    {
      try
      {
        // parsing strings into integers 
        number1String = stockTextField.getText();
        stockPercentage = Double.parseDouble(number1String);
        bondPercentage = Double.parseDouble(bondTextField.getText());
        cryptoPercentage = Double.parseDouble(cryptoTextField.getText());
        fixIncPercentage = Double.parseDouble(fixIncTextField.getText());

        // adding up percentages 
        total = fixIncPercentage + cryptoPercentage + bondPercentage
          + stockPercentage;

        // making sure that percentages = 100%
        if (total == 100)
        {
          new RebalanceWarning();
          
          String query = "UPDATE Portion SET Stock = ?, Bond = ?, Crypto = ?, Fixed = ?"
            + " WHERE Constant = ?";
          
          this.stockTextField.setText("");
          this.bondTextField.setText("");
          this.cryptoTextField.setText("");
          this.fixIncTextField.setText("");
          
          constant = 0;
          
          try
        {
          // prepare data 
          PreparedStatement ps = myDbConn.prepareStatement(query);

          // enter data into query 
          ps.setDouble(1, stockPercentage);
          ps.setDouble(2, bondPercentage);
          ps.setDouble(3, cryptoPercentage);
          ps.setDouble(4, fixIncPercentage);
          ps.setInt(5, constant);

          // execute data 
          ps.executeUpdate();
          System.out.println("Data updated succesfully");
        }
        catch (SQLException se)
        {
          System.out.println("Error updating class data ");
        }
        }
        else
        {
          // in case percentages do not add to 100 
          new Warning("<html><center> Looks like your percentages don't add "
            + "up to 100...</center></html>");
        }
      }
      // robustness 
      catch (NumberFormatException nfe)
      {
        new Warning("<html><center>Invalid Entry, please enter a number !!!"
          + "</center></html>");
      }
      catch (Exception exc)
      {
        new Warning("<html><center>Unexpected error, please try again !!!"
          + "</center></html>");
      }
      this.validate();
      this.repaint();
    }
  }
}
