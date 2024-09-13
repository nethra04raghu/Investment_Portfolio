/*
Frame for users to see their turnover % and navigate accordingly 
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Turnover extends JFrame implements ActionListener
{

  // label 
  private JLabel turnoverText;
  private JLabel stockLabel;
  private JLabel bondLabel;
  private JLabel cryptoLabel;
  private JLabel fixIncLabel;

  // button 
  private JButton editButton;
  private JButton returnButton;

  // panel 
  private JPanel buttonPanel;
  private JPanel assetClassPanel;

  // dummy values 
  private double stockTurnover = 3;
  private double bondTurnover = 6;
  private double cryptoTurnover = 21;
  private double fixIncTurnover = 7;

  // menu components 
  private JMenuBar menuBar;
  private JMenu navigationMenu;
  private JMenuItem helpItem;
  
  double turnoverStock;
  double turnoverBond;
  double turnoverCrypto;
  double turnoverFixed;

  public Turnover()
  {
    super("Turnover");
    this.setBounds(374, 95, 701, 319);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // db info for turnover 
    String turnoverDb = "Investment Portfolio";
    String turnoverTable = "Turnover";
    String[] turnoverColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    // connection 
    JavaDatabase objDb = new JavaDatabase(turnoverDb);
    Connection myDbConn = objDb.getDbConn();

    // turnover arraylist
    ArrayList<ArrayList<String>> turnoverData = objDb.getData(turnoverTable, turnoverColumn);
    
    // parsing values 
    turnoverStock = (Double.parseDouble(turnoverData.get(0).get(0))) / 100;
    turnoverBond = (Double.parseDouble(turnoverData.get(0).get(1))) / 100;
    turnoverCrypto = (Double.parseDouble(turnoverData.get(0).get(2))) / 100;
    turnoverFixed = (Double.parseDouble(turnoverData.get(0).get(3))) / 100;
    
    // constructing label 
    this.turnoverText = new JLabel("Here are your turnover percentages",
       SwingConstants.CENTER);
    turnoverText.setForeground(Welcome.EGGSHELL);
    turnoverText.setFont(Welcome.WORD_FONT);

    // constructing labels for displaying values 
    this.stockLabel = new JLabel("     " + "Stocks: " + turnoverStock + "     ",
       SwingConstants.CENTER);
    stockLabel.setForeground(Welcome.EGGSHELL);
    stockLabel.setFont(Welcome.SMALL_FONT);

    this.bondLabel = new JLabel("     " + "Bonds: " + turnoverBond + "     ",
       SwingConstants.CENTER);
    bondLabel.setForeground(Welcome.EGGSHELL);
    bondLabel.setFont(Welcome.SMALL_FONT);

    this.cryptoLabel = new JLabel("     " + "Crypto: " + turnoverCrypto + "     ",
       SwingConstants.CENTER);
    cryptoLabel.setForeground(Welcome.EGGSHELL);
    cryptoLabel.setFont(Welcome.SMALL_FONT);

    this.fixIncLabel = new JLabel("     " + "Fixed Inc: " + turnoverFixed + "     ",
       SwingConstants.CENTER);
    fixIncLabel.setForeground(Welcome.EGGSHELL);
    fixIncLabel.setFont(Welcome.SMALL_FONT);

    // constructing panel for labels 
    this.assetClassPanel = new JPanel(new FlowLayout());
    assetClassPanel.add(stockLabel);
    assetClassPanel.add(bondLabel);
    assetClassPanel.add(cryptoLabel);
    assetClassPanel.add(fixIncLabel);
    assetClassPanel.setBackground(Welcome.NAVY);

    // constructing buttons
    this.editButton = new JButton("Edit");
    editButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(editButton);
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // constructing menu components 
    this.menuBar = new JMenuBar();
    this.navigationMenu = new JMenu("Navigation");
    this.helpItem = new JMenuItem("Help");
    helpItem.addActionListener(this);
    navigationMenu.add(helpItem);
    menuBar.add(navigationMenu);
    this.setJMenuBar(menuBar);

    // adding components 
    this.add(turnoverText, BorderLayout.NORTH);
    this.add(assetClassPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Turnover();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    // disposing and open frame choosing turnover percentage to edit 
    if (command == editButton)
    {
      this.dispose();
      new TurnoverChoose();
    }
    // dispose and open welcome frame 
    else if (command == returnButton)
    {
      this.dispose();
      new Welcome();
    }
    // open help frame 
    else if (command == helpItem)
    {
      new HelpTurnover();
    }
  }
}
