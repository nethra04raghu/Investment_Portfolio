/*
Frame for user to see their estimated growth % and can naviate
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

public class Estimate extends JFrame implements ActionListener
{

  // label 
  private JLabel estimateText;
  private JLabel stockLabel;
  private JLabel bondLabel;
  private JLabel cryptoLabel;
  private JLabel fixIncLabel;

  // button
  private JButton estimateChangeButton;
  private JButton estimateCompareButton;
  private JButton returnButton;

  // panel
  private JPanel buttonPanel;
  private JPanel assetClassPanel;

  // dummy estimates 
  private double stockEstimate;
  private double bondEstimate;
  private double cryptoEstimate;
  private double fixIncEstimate;

  // menu components 
  private JMenuBar menuBar;
  private JMenu navigationMenu;
  private JMenuItem helpItem;

  public Estimate()
  {
    super("Estimate");
    this.setBounds(374, 95, 701, 319);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.NAVY);
    this.setLayout(new BorderLayout());

    // constructing text
    this.estimateText = new JLabel("Here are your estimates", SwingConstants.CENTER);
    estimateText.setForeground(Welcome.EGGSHELL);
    estimateText.setFont(Welcome.WORD_FONT);

    // db info estimate 
    String estimateDb = "Investment Portfolio";
    String bondTable = "Estimate";
    String[] bondColumn =
    {
      "Stock", "Bond", "Crypto", "Fixed", "Constant"
    };
    
    // db connection 
    JavaDatabase objDb = new JavaDatabase(estimateDb);
    Connection myDbConn = objDb.getDbConn();

    // bonds arraylist
    ArrayList<ArrayList<String>> estimateData = objDb.getData(bondTable, bondColumn);

    // parsing values for estimate values 
    stockEstimate = (Double.parseDouble(estimateData.get(0).get(0))) / 100;
    bondEstimate = (Double.parseDouble(estimateData.get(0).get(1))) / 100;
    cryptoEstimate = (Double.parseDouble(estimateData.get(0).get(2))) / 100;
    fixIncEstimate = (Double.parseDouble(estimateData.get(0).get(3))) / 100;
    
    // constructing labels 
    this.stockLabel = new JLabel("     " + "Stocks: " + stockEstimate + "     ",
       SwingConstants.CENTER);
    stockLabel.setForeground(Welcome.EGGSHELL);
    stockLabel.setFont(Welcome.SMALL_FONT);

    this.bondLabel = new JLabel("     " + "Bonds: " + bondEstimate + "     ",
       SwingConstants.CENTER);
    bondLabel.setForeground(Welcome.EGGSHELL);
    bondLabel.setFont(Welcome.SMALL_FONT);

    this.cryptoLabel = new JLabel("     " + "Crypto: " + cryptoEstimate + "     ",
       SwingConstants.CENTER);
    cryptoLabel.setForeground(Welcome.EGGSHELL);
    cryptoLabel.setFont(Welcome.SMALL_FONT);

    this.fixIncLabel = new JLabel("     " + "Fixed Inc: " + fixIncEstimate + "     ",
       SwingConstants.CENTER);
    fixIncLabel.setForeground(Welcome.EGGSHELL);
    fixIncLabel.setFont(Welcome.SMALL_FONT);

    // construct panel for labels
    this.assetClassPanel = new JPanel(new FlowLayout());
    assetClassPanel.add(stockLabel);
    assetClassPanel.add(bondLabel);
    assetClassPanel.add(cryptoLabel);
    assetClassPanel.add(fixIncLabel);
    assetClassPanel.setBackground(Welcome.NAVY);

    // constructing buttons
    this.estimateChangeButton = new JButton("Change Your Estimates");
    estimateChangeButton.addActionListener(this);
    this.estimateCompareButton = new JButton("Compare Your Estimates");
    estimateCompareButton.addActionListener(this);
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel and add buttons
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(estimateChangeButton);
    buttonPanel.add(estimateCompareButton);
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
    this.add(estimateText, BorderLayout.NORTH);
    this.add(assetClassPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Estimate();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    // dispose and open estimate choose frame 
    if (command == estimateChangeButton)
    {
      this.dispose();
      new EstimateChoose();
    }
    // dispose and open compare frame 
    else if (command == estimateCompareButton)
    {
      this.dispose();
      new EstimateCompare();
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
      new HelpEstimate();
    }
  }

}
