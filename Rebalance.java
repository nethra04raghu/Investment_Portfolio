/*
Frame to inform user that investment has been rebalanced  
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Rebalance extends JFrame implements ActionListener
{

  // labels 
  private JLabel rebalanceText;
  private JLabel rebalanceMessage;

  // panel 
  private JPanel buttonPanel;
  private JButton returnButton;

  public Rebalance()
  {
    super("Rebalance");
    this.setBounds(17, 37, 477, 250);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing labels 
    this.rebalanceText = new JLabel("REBALANCE", SwingConstants.CENTER);
    rebalanceText.setFont(Welcome.WORD_FONT);
    rebalanceText.setForeground(Welcome.EGGSHELL);

    this.rebalanceMessage = new JLabel("Your investments have been "
      + "rebalanced!", SwingConstants.CENTER);
    rebalanceMessage.setFont(Welcome.SMALL_FONT);
    rebalanceMessage.setForeground(Welcome.EGGSHELL);

    // constructing button 
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // adding components 
    this.add(rebalanceText, BorderLayout.NORTH);
    this.add(rebalanceMessage, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new RebalanceCalc();
    new Rebalance();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    // dispose frame 
    if (command == returnButton)
    {
      this.dispose();
    }
  }

}
