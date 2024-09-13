/*
frame to input bond percents 
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

public class TurnoverInputBond extends JFrame implements ActionListener
{
  // label and textfield 
  private JLabel turnoverInputText;
  private JTextField turnoverInputTextField;
  
  // label 
  private JLabel turnoverInputLabel;
  
  // panel 
  private JPanel buttonPanel;
  private JPanel inputPanel;
  
  // button 
  private JButton enterButton;
  private JButton returnButton;
  
  public TurnoverInputBond()
  {
    super("Turnover Input");
    this.setBounds(511,489,425,344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());
    
    // constructing label 
    this.turnoverInputText = new JLabel ("<html><center>Enter New Turnover "
      + "Percentage</center></html>", SwingConstants.CENTER);
    turnoverInputText.setForeground(Welcome.NAVY);
    turnoverInputText.setFont(Welcome.WORD_FONT);
    
    // constructing label and textfield 
    turnoverInputLabel = new JLabel("Enter Input");
    turnoverInputLabel.setForeground(Welcome.NAVY);
    turnoverInputLabel.setFont(Welcome.SMALL_FONT);
    turnoverInputTextField = new JTextField(7);
    
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
    
    // constructing panel for textfields 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(turnoverInputLabel);
    inputPanel.add(turnoverInputTextField);
    inputPanel.setBackground(Welcome.EGGSHELL);
    
    // adding components 
    this.add(turnoverInputText, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new TurnoverInputBond();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    String number1String;
    double turnoverPercentage;
    
    // db info for turnover 
    String dbName = "Investment Portfolio";
    String tableName = "Turnover";
    String[] columnNames =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };

    int constant = 0;
    double updateBondTurn;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    
    if (command == enterButton)
    {
      try
      {
        // parsing string to integer 
        number1String = turnoverInputTextField.getText();
        turnoverPercentage = Double.parseDouble(number1String);
        
        // printing user input 
        System.out.println("your turnover percentage is " + turnoverPercentage + "%");
        
        String query = "UPDATE Turnover SET Bond = ? WHERE Constant = ?";

        updateBondTurn = Double.parseDouble(turnoverInputTextField.getText());
        this.turnoverInputTextField.setText("");
        
        try
        {
          // prepare data 
          PreparedStatement ps = myDbConn.prepareStatement(query);

          // enter data into query 
          ps.setDouble(1, updateBondTurn);
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
      // robustness (catching user error) 
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
    // dispose and open turnover frame 
    else if (command == returnButton)
    {
      this.dispose();
      new Turnover();
    }
  }
}
