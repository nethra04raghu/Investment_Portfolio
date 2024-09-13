/*
danger decrease input for asset class stock 
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
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class DangerDecreaseInputStock extends JFrame implements ActionListener
{
  // label 
  private JLabel dangerDecreaseInputText;
  private JPanel buttonPanel;
  private JPanel inputPanel;
  
  // textfield + label
  private JTextField dangerDecreaseInputTextField;
  private JLabel dangerDecreaseInputLabel;
  
  // button
  private JButton enterButton;
  private JButton returnButton;
  
  public DangerDecreaseInputStock()
  {
    super("Danger Decrease Input");
    this.setBounds(511,489,425,344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());
    
    // constructing text 
    this.dangerDecreaseInputText = new JLabel ("<html><center>Enter New Decrease "
      + "Percentage</center></html>", SwingConstants.CENTER);
    dangerDecreaseInputText.setForeground(Welcome.NAVY);
    dangerDecreaseInputText.setFont(Welcome.WORD_FONT);
    
    // constructing textfield + labels
    dangerDecreaseInputLabel = new JLabel("Enter Input");
    dangerDecreaseInputLabel.setForeground(Welcome.NAVY);
    dangerDecreaseInputLabel.setFont(Welcome.SMALL_FONT);
    dangerDecreaseInputTextField = new JTextField(7);
    
    // constructing buttons
    this.enterButton = new JButton("Enter");
    enterButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);
    
    // constructing button panel
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(enterButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);
    
    // constructing panel for textfield 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(dangerDecreaseInputLabel);
    inputPanel.add(dangerDecreaseInputTextField);
    inputPanel.setBackground(Welcome.EGGSHELL);
    
    // adding components
    this.add(dangerDecreaseInputText, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new DangerDecreaseInputStock();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    
    String number1String;
    double ddPercentage;
    String message;
    
    // db info for danger decrease 
    String dbName = "Investment Portfolio";
    String tableName = "DangerDecrease";
    String[] columnNames =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };

    int constant = 0;
    double updateBondDD;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    
    if (command == enterButton)
    {
      try
      {
        /// parsing string to text 
        number1String = dangerDecreaseInputTextField.getText();
        ddPercentage = Double.parseDouble(number1String);
        // outputting users input 
        System.out.println("your decrease percentage is " + ddPercentage + "%");
        // query 
        String query = "UPDATE DangerDecrease SET Stock = ? WHERE Constant = ?";
        // parsing and setting values 
        updateBondDD = Double.parseDouble(dangerDecreaseInputTextField.getText());
        this.dangerDecreaseInputTextField.setText("");
        
        try
        {
          // prepare data 
          PreparedStatement ps = myDbConn.prepareStatement(query);

          // enter data into query 
          ps.setDouble(1, updateBondDD);
          ps.setInt(2, constant);

          // execute data 
          ps.executeUpdate();
          System.out.println("Data updated succesfully");
        }
        catch (SQLException se)
        {
          System.out.println("Error updating class data ");
        }
      }
      // robustness (sends warning frame) 
      catch (NumberFormatException nfe)
      {
        new Warning ("<html><center>Invalid Entry, please enter a number !!!"
            + "</center></html>");
      }
      catch (Exception exc)
      {
        new Warning ("<html><center>Unexpected error, please try again !!!"
            + "</center></html>");
      }
    }
    // dispose and open danger decrease 
    else if (command == returnButton)
    {
      this.dispose();
      new DangerDecrease();
    }
  }
}
