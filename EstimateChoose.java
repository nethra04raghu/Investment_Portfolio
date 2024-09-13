/*
Frame for user to choose which asset class they want to edit their %s for 
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

public class EstimateChoose extends JFrame implements ActionListener
{

  // label
  private JLabel estimateChooseText;

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

  public EstimateChoose()
  {
    super("Estimate Choose");
    this.setBounds(511, 489, 425, 344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());

    // constructing text
    this.estimateChooseText = new JLabel("<html><center>Which estimate growth "
      + "percentage would you like to edit", SwingConstants.CENTER);
    estimateChooseText.setForeground(Welcome.NAVY);
    estimateChooseText.setFont(Welcome.WORD_FONT);

    // constructing radiobuttons
    this.stockButton = new JRadioButton("Stock");
    stockButton.addActionListener(this);
    this.bondButton = new JRadioButton("Bond");
    bondButton.addActionListener(this);
    this.cryptoButton = new JRadioButton("Crypto");
    cryptoButton.addActionListener(this);
    this.fixedIncButton = new JRadioButton("Fixed Income");
    fixedIncButton.addActionListener(this);

    // constructing button groups 
    this.assetClassButtons = new ButtonGroup();
    assetClassButtons.add(stockButton);
    assetClassButtons.add(bondButton);
    assetClassButtons.add(cryptoButton);
    assetClassButtons.add(fixedIncButton);

    // constructing panel for radiobuttons
    this.radioButtonPanel = new JPanel(new FlowLayout());
    radioButtonPanel.add(stockButton);
    radioButtonPanel.add(bondButton);
    radioButtonPanel.add(cryptoButton);
    radioButtonPanel.add(fixedIncButton);
    radioButtonPanel.setBackground(Welcome.EGGSHELL);

    // constructing buttons
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);

    // adding components 
    this.add(estimateChooseText, BorderLayout.NORTH);
    this.add(radioButtonPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new EstimateChoose();
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
    
    // dispose and open estimate frame 
    if (command == returnButton)
    {
      this.dispose();
      new Estimate();
    }
    // dispose and open input frame for stock 
    else if (command == stockButton)
    {
      this.dispose();
      new EstimateInputStock();
    }
    // dispose and open input frame for bond
    else if (command == bondButton)
    {
      this.dispose();
      new EstimateInputBond();
    }
    // dispose and open input frame for crypto 
    else if (command == cryptoButton)
    {
      this.dispose();
      new EstimateInputCrypto();
    }
    // dispose and open input frame for fixed income
    else if (command == fixedIncButton)
    {
      this.dispose();
      new EstimateInputFixed();
    }
  }
}
