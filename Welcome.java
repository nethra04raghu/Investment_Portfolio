/*
This is the welcome frame for client to navigate via buttons 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Welcome extends JFrame implements ActionListener
{

  // label 
  private JLabel welcomeText;

  // colors
  public static final Color EGGSHELL = new Color(244, 242, 220);
  public static final Color TERRA_COTTA = new Color(237, 116, 82);
  public static final Color NAVY = new Color(62, 62, 93);
  public static final Color BEIGE = new Color(247, 205, 132);

  // fonts 
  public static final Font WORD_FONT = new Font("Alegreya", Font.BOLD, 24);
  public static final Font SMALL_FONT = new Font("Alegreya", Font.BOLD, 18);

  // button
  private JButton ipButton;
  private JButton rebalanceButton;
  private JButton portionButton;
  private JButton turnoverButton;
  private JButton estimatebutton;
  private JButton ddButton;

  // panel 
  private JPanel buttonPanel;
  private JPanel mainButtonPanel;

  // menu components 
  private JMenuBar menuBar;
  private JMenu navigationMenu;
  private JMenuItem helpItem;

  public Welcome()
  {
    super("Welcome");
    this.setBounds(426, 304, 606, 339);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.getContentPane().setBackground(EGGSHELL);
    this.setLayout(new BorderLayout());

    // constructing label 
    this.welcomeText = new JLabel("<html><center>Welcome To Your Investment "
      + "Portfolio</center></html>", SwingConstants.CENTER);
    welcomeText.setForeground(NAVY);
    welcomeText.setFont(WORD_FONT);

    // constructing menu components 
    this.menuBar = new JMenuBar();
    this.navigationMenu = new JMenu("Navigation");
    this.helpItem = new JMenuItem("Help");
    helpItem.addActionListener(this);
    navigationMenu.add(helpItem);
    menuBar.add(navigationMenu);
    this.setJMenuBar(menuBar);

    // constructing buttons 
    this.ipButton = new JButton("See Investment Portfolio");
    ipButton.addActionListener(this);
    this.rebalanceButton = new JButton("Rebalance");
    rebalanceButton.addActionListener(this);
    this.portionButton = new JButton("Portion");
    portionButton.addActionListener(this);
    this.turnoverButton = new JButton("Turnover");
    turnoverButton.addActionListener(this);
    this.estimatebutton = new JButton("Estimate");
    estimatebutton.addActionListener(this);
    this.ddButton = new JButton("Danger Decrease");
    ddButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(portionButton);
    buttonPanel.add(turnoverButton);
    buttonPanel.add(estimatebutton);
    buttonPanel.add(ddButton);
    buttonPanel.setBackground(NAVY);

    // constructing button panel 
    this.mainButtonPanel = new JPanel(new FlowLayout());
    mainButtonPanel.add(ipButton);
    mainButtonPanel.setBackground(EGGSHELL);

    // adding components 
    this.add(welcomeText, BorderLayout.NORTH);
    this.add(mainButtonPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Welcome();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    Object command = e.getSource();
    // opening object of Investment Portfolio 
    if (command == ipButton)
    {
      this.dispose();
      new InvestmentPortfolio();
    }
    // open object for rebalance 
    else if (command == rebalanceButton)
    {
      new Rebalance();
    }
    // open object for new portion class 
    else if (command == portionButton)
    {
      this.dispose();
      new Portion();
    }
    // open object for new turnover class
    else if (command == turnoverButton)
    {
      this.dispose();
      new Turnover();
    }
    // open estimate frame 
    else if (command == estimatebutton)
    {
      this.dispose();
      new Estimate();
    }
    // open object for danger decrease class 
    else if (command == ddButton)
    {
      this.dispose();
      new DangerDecrease();
    }
    // open object of help item 
    else if (command == helpItem)
    {
      new Help();
    }
  }
}
