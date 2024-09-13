/*
Frame for user to look at their decrease % and update if they wish 
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

public class DangerDecrease extends JFrame implements ActionListener
{

  // label
  private JLabel dangerDecreaseText;
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

  // decrease values 
  private double stockDangerDecrease;
  private double bondDangerDecrease;
  private double cryptoDangerDecrease;
  private double fixIncDangerDecrease;

  // menu components 
  private JMenuBar menuBar;
  private JMenu navigationMenu;
  private JMenuItem helpItem;

  public DangerDecrease()
  {
    super("Danger Decreae");
    this.setBounds(374, 95, 701, 319);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());
    
    // db info for danger decrease 
    String dbName = "Investment Portfolio";
    String tableName = "DangerDecrease";
    String[] columnNames =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    // connection 
    JavaDatabase objDb = new JavaDatabase(dbName);
    Connection myDbConn = objDb.getDbConn();
    
    // turnover arraylist
    ArrayList<ArrayList<String>> decreaseData = objDb.getData(tableName, columnNames);
    
    // parsing values 
    stockDangerDecrease = (Double.parseDouble(decreaseData.get(0).get(0))) / 100;
    bondDangerDecrease = (Double.parseDouble(decreaseData.get(0).get(1))) / 100;
    cryptoDangerDecrease = (Double.parseDouble(decreaseData.get(0).get(2))) / 100;
    fixIncDangerDecrease = (Double.parseDouble(decreaseData.get(0).get(3))) / 100;

    // constructing text 
    this.dangerDecreaseText = new JLabel("<html><center>Here are your decrease "
      + "percentages</center></html>", SwingConstants.CENTER);
    dangerDecreaseText.setForeground(Welcome.EGGSHELL);
    dangerDecreaseText.setFont(Welcome.WORD_FONT);

    // constructing text displaying %
    this.stockLabel = new JLabel("    " + "Stocks: " + stockDangerDecrease + "    ");
    stockLabel.setForeground(Welcome.EGGSHELL);
    stockLabel.setFont(Welcome.SMALL_FONT);
    this.bondLabel = new JLabel("    " + "Bonds: " + bondDangerDecrease + "    ");
    bondLabel.setForeground(Welcome.EGGSHELL);
    bondLabel.setFont(Welcome.SMALL_FONT);
    this.cryptoLabel = new JLabel("    " + "Crypto: " + cryptoDangerDecrease + "    ");
    cryptoLabel.setForeground(Welcome.EGGSHELL);
    cryptoLabel.setFont(Welcome.SMALL_FONT);
    this.fixIncLabel = new JLabel("    " + "Fixed Inc: " + fixIncDangerDecrease + "    ");
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
    this.add(dangerDecreaseText, BorderLayout.NORTH);
    this.add(assetClassPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new DangerDecrease();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // commands for buttons
    Object command = e.getSource();
    // disposing frame 
    if (command == editButton)
    {
      this.dispose();
      new DangerDecreaseChoose();
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
      new HelpDangerDecrease();
    }
  }
}
