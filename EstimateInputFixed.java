/*
input estimate percent for fixed income asset class 
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

public class EstimateInputFixed extends JFrame implements ActionListener
{
  
  // label
  private JLabel estimateInputText;

  // textfield and label
  private JTextField estimateInputTextField;
  private JLabel estimateInputLabel;

  // buttons
  private JButton enterButton;
  private JButton returnButton;

  // panels
  private JPanel buttonPanel;
  private JPanel inputPanel;

  public double fixedEstimateRaw;
  
  public EstimateInputFixed()
  {
    super("Estimate Input Fixed");
    this.setBounds(511, 489, 425, 344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());

    // constructing text 
    this.estimateInputText = new JLabel("<html><center>Enter New Estimate "
      + "Growth Percentage</center></html>", SwingConstants.CENTER);
    estimateInputText.setForeground(Welcome.NAVY);
    estimateInputText.setFont(Welcome.WORD_FONT);

    // constructing label and textfield
    estimateInputLabel = new JLabel("<html><center>Enter Input</center>"
      + "</html>", SwingConstants.CENTER);
    estimateInputLabel.setForeground(Welcome.NAVY);
    estimateInputLabel.setFont(Welcome.SMALL_FONT);
    estimateInputTextField = new JTextField(7);

    // constructing buttons
    this.enterButton = new JButton("Enter");
    enterButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing the button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(enterButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);

    // constructing panel for textfields 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(estimateInputLabel);
    inputPanel.add(estimateInputTextField);
    inputPanel.setBackground(Welcome.EGGSHELL);

    // adding components 
    this.add(estimateInputLabel, BorderLayout.NORTH);
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new EstimateInputFixed();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    String number1String;
    String message;
    double stockEstimateRaw;
    double bondEstimateRaw;
    double cryptoEstimateRaw;
    
    // db info estimate 
    String dbName = "Investment Portfolio";
    String tableName = "Estimate";
    String[] columnNames =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };

    int constant;
    double updateFixedEst;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    
    if (command == enterButton)
    {
      try
      {
        // parsing string to integer 
        number1String = estimateInputTextField.getText();
        bondEstimateRaw = Double.parseDouble(number1String);
        // print the user input 
        System.out.println("your estimate percentage is " + bondEstimateRaw + "%");

        // sql statement 
        String query = "UPDATE Estimate SET Fixed = ? WHERE Constant = ?";

        updateFixedEst = Double.parseDouble(estimateInputTextField.getText());
        this.estimateInputTextField.setText("");

        constant = 0;

        try
        {
          // prepare data 
          PreparedStatement ps = myDbConn.prepareStatement(query);

          // enter data into query 
          ps.setDouble(1, updateFixedEst);
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
      // robustness (make sure the user inputs only integers) 
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
    }
    // dispose and open new estimate frame 
    else if (command == returnButton)
    {
      this.dispose();
      new Estimate();
    }
  }  
}