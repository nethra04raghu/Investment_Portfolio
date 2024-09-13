/*
Frame for user to choose which asset class they want to see comparisons for 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EstimateCompare extends JFrame implements ActionListener
{

  // label 
  private JLabel estimateCompareText;

  // radiobuttons
  private JRadioButton stockButton;
  private JRadioButton bondButton;
  private JRadioButton cryptoButton;
  private JRadioButton fixedIncButton;

  // buttongroup
  private ButtonGroup assetClassButtons;

  // button
  private JButton returnButton;

  // panel 
  private JPanel radioButtonPanel;
  private JPanel buttonPanel;

  public EstimateCompare()
  {
    super("Comparing Estimates");
    this.setBounds(511, 489, 425, 344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());

    // constructing text for frame
    this.estimateCompareText = new JLabel("<html><center>Which estimates "
      + "would you like to compare?</center></html>", SwingConstants.CENTER);
    estimateCompareText.setForeground(Welcome.NAVY);
    estimateCompareText.setFont(Welcome.WORD_FONT);

    // constructing radiobuttons
    this.stockButton = new JRadioButton("Stock");
    stockButton.addActionListener(this);
    this.bondButton = new JRadioButton("Bond");
    bondButton.addActionListener(this);
    this.cryptoButton = new JRadioButton("Crypto");
    cryptoButton.addActionListener(this);
    this.fixedIncButton = new JRadioButton("Fixed Income");
    fixedIncButton.addActionListener(this);

    // construct button group
    this.assetClassButtons = new ButtonGroup();
    assetClassButtons.add(stockButton);
    assetClassButtons.add(bondButton);
    assetClassButtons.add(cryptoButton);
    assetClassButtons.add(fixedIncButton);

    // contructing panel and adding radiobuttons
    this.radioButtonPanel = new JPanel(new FlowLayout());
    radioButtonPanel.add(stockButton);
    radioButtonPanel.add(bondButton);
    radioButtonPanel.add(cryptoButton);
    radioButtonPanel.add(fixedIncButton);
    radioButtonPanel.setBackground(Welcome.EGGSHELL);

    // constructing button 
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel and add button
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);

    // adding components 
    this.add(estimateCompareText, BorderLayout.NORTH);
    this.add(radioButtonPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new EstimateCompare();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    double stockEstimateRaw;
    double bondEstimateRaw;
    double cryptoEstimateRaw;
    double fixedEstimateRaw;
    String message;
    
    // dispose and open estimate frame 
    if (command == returnButton)
    {
      this.dispose();
      new Estimate();
    }
    // dispose and open new estimate compare for stock 
    else if (command == stockButton)
    {
      this.dispose();
      new EstimateCompareStock();
    }
    // dispose and open new estimate compare for bond 
    else if (command == bondButton)
    {
      this.dispose();
      new EstimateCompareBond();
    }
    // dispose and open new estimate compare for crypto 
    else if (command == cryptoButton)
    {
      this.dispose();
      new EstimateCompareCrypto();
    }
    // dispose and open new estimate compare for fixed income 
    else if (command == fixedIncButton)
    {
      this.dispose();
      new EstimateCompareFixed();
    }
  }
}
