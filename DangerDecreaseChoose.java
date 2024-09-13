/*
Frame for user to choose which asset class % to update 
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

public class DangerDecreaseChoose extends JFrame implements ActionListener
{
  // label
  private JLabel ddText;
  
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
  
  public DangerDecreaseChoose()
  {
    super("Danger Decrease");
    this.setBounds(511,489,425,344);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.EGGSHELL);
    this.setLayout(new BorderLayout());
    
    // contructing text 
    this.ddText = new JLabel ("<html><center>Which decrease percentage would you like "
      + "to edit?</center></html>", SwingConstants.CENTER);
    ddText.setForeground(Welcome.NAVY);
    ddText.setFont(Welcome.WORD_FONT);
    
    // constructing radiobuttons
    this.stockButton = new JRadioButton ("Stock");
    stockButton.addActionListener(this);
    this.bondButton = new JRadioButton ("Bond");
    bondButton.addActionListener(this);
    this.cryptoButton = new JRadioButton ("Crypto");
    cryptoButton.addActionListener(this);
    this.fixedIncButton = new JRadioButton ("Fixed Income");
    fixedIncButton.addActionListener(this);
    
    // grouping with buttongroups 
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
    
    // contructing button
    this.returnButton = new JButton ("Return");
    returnButton.addActionListener(this);
    
    // constructing button panel 
    this.buttonPanel = new JPanel (new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.NAVY);
    
    // adding components
    this.add(ddText, BorderLayout.NORTH);
    this.add(radioButtonPanel, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  
  public static void main(String[] args)
  {
    new DangerDecreaseChoose();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // commands for button
    Object command = e.getSource();
    // dispose and open danger decrease frame 
    if (command == returnButton)
    {
      this.dispose();
      new DangerDecrease();
    }
    // dispose and open input for stock 
    else if(command == stockButton)
    {
      this.dispose();
      new DangerDecreaseInputStock();
    }
    // dispose and open input for bond 
    else if (command == bondButton)
    {
      this.dispose();
      new DangerDecreaseInputBond();
    }
    // dispose and open input for crypto 
    else if (command == cryptoButton)
    {
      this.dispose();
      new DangerDecreaseInputCrypto();
    }
    // dispose and open input for fixed income
    else if (command == fixedIncButton)
    {
      this.dispose();
      new DangerDecreaseInputFixed();
    }
  }
}
