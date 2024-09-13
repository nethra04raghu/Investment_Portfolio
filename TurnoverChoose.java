/*
Frame for users to choose which asset class % they wish to edit 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
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

public class TurnoverChoose extends JFrame implements ActionListener
{

  // label 
  private JLabel turnoverText;

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

  public TurnoverChoose()
  {
    super("Turnover");
    this.setBounds(511, 489, 425, 344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());

    // constructing label 
    this.turnoverText = new JLabel("<html><center>Which turnover percentage would you like "
      + "to edit</center></html>", SwingConstants.CENTER);
    turnoverText.setForeground(Welcome.NAVY);
    turnoverText.setFont(Welcome.WORD_FONT);

    // constructing radiobuttons
    this.stockButton = new JRadioButton("Stock");
    stockButton.addActionListener(this);
    this.bondButton = new JRadioButton("Bond");
    bondButton.addActionListener(this);
    this.cryptoButton = new JRadioButton("Crypto");
    cryptoButton.addActionListener(this);
    this.fixedIncButton = new JRadioButton("Fixed Income");
    fixedIncButton.addActionListener(this);

    // constructing buttongroup for radiobuttons
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

    // constructing button panel for general buttons
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);

    // adding commponents 
    this.add(turnoverText, BorderLayout.NORTH);
    this.add(radioButtonPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new TurnoverChoose();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    // dispose and open welcome frame 
    if (command == returnButton)
    {
      this.dispose();
      new Welcome();
    }
    // dipose and open input turnover frame for stock perents 
    else if (command == stockButton)
    {
      this.dispose();
      new TurnoverInputStock();
    }
    // dispose and open input turnover frame for bond percents 
    else if (command == bondButton)
    {
      this.dispose();
      new TurnoverInputBond();
    }
    // dispose and open input turnover frame for crypto percents 
    else if (command == cryptoButton)
    {
      this.dispose();
      new TurnoverInputCrypto();
    }
    // dipose and open input turnover for fixed income percents 
    else if (command == fixedIncButton)
    {
      this.dispose();
      new TurnoverInputFixed();
    }
  }

}
