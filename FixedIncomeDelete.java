/*
area for users to enter the asset name they wish to delete (to delete from db)
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

public class FixedIncomeDelete extends JFrame implements ActionListener
{

  // textfield and label 
  private JTextField deleteText;
  private JLabel deleteLabel;

  // panel
  private JPanel inputPanel;
  private JPanel buttonPanel;

  // button 
  private JButton enterButton;
  private JButton returnButton;

  public FixedIncomeDelete()
  {
    super("Delete Asset for Fixed Income");
    this.setBounds(368, 192, 704, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
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
    buttonPanel.setBackground(Welcome.NAVY);

    // constructing label and textfield 
    deleteLabel = new JLabel("Input Asset Name");
    deleteLabel.setForeground(Welcome.NAVY);
    deleteLabel.setFont(Welcome.WORD_FONT);
    deleteText = new JTextField(7);

    // construct panel for textfield 
    this.inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(deleteLabel);
    inputPanel.add(deleteText);
    inputPanel.setBackground(Welcome.EGGSHELL);

    // adding components 
    this.add(inputPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new FixedIncomeDelete();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // commands for buttons 
    Object command = e.getSource();

    // db info 
    String dbName = "Investment Portfolio";
    String tableName = "FixedIncome";
    String[] columnNames =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };

    String name;

    // connection to db 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();

    if (command == enterButton)
    {
      // sql statemtn 
      String query = "DELETE FROM FixedIncome WHERE Name = ?";
      name = deleteText.getText();
      this.deleteText.setText("");
      try
      {
        // prepare statement 
        PreparedStatement ps = myDbConn.prepareStatement(query);
        
        // enter data into query 
        ps.setString(1, name);
        
        // execute query  
        ps.executeUpdate();
        System.out.println("Data deleted succesfully");
        this.dispose();
        
        // calculating these classes to make sure these are false
        InvestmentPortfolioCalc objIP = new InvestmentPortfolioCalc();
        
        TurnoverCalc objTC = new TurnoverCalc();

        if (objIP.getSumCurrentFixed() > objTC.getFixedTurnover())
        {
          System.out.println("turnover reached");
          new TurnoverWarning("The fixed income class has reached target turnover !");
        }
        
        DangerDecreaseCalc objDC = new DangerDecreaseCalc();

        if (objDC.getFixedDecrease() > objIP.getSumCurrentFixed())
        {
          System.out.println("decrease reached");
          new DangerDecreaseWarning("The fixed income class has dangerously decreased ...");
        }
      }
      // robustness 
      catch (SQLException se)
      {
        System.out.println("Error deleting data");
      }
    }
    // disposing frame 
    else if (command == returnButton)
    {
      this.dispose();
    }
  }
}
