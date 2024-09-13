/*
Frame that pops up to ensure user would like to rebalance 
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class RebalanceWarning extends JFrame implements ActionListener
{

  // label 
  private JLabel rebalanceWarningText;

  // panel 
  private JPanel buttonPanel;

  // button 
  private JButton yesButton;
  private JButton noButton;

  public RebalanceWarning()
  {
    super("Rebalance Warning");
    this.setBounds(28, 588, 503, 280);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing label 
    this.rebalanceWarningText = new JLabel("Would you like to rebalance?",
       SwingConstants.CENTER);
    rebalanceWarningText.setFont(Welcome.WORD_FONT);
    rebalanceWarningText.setForeground(Welcome.EGGSHELL);

    // constructing buttons
    this.yesButton = new JButton("Yes");
    yesButton.addActionListener(this);
    this.noButton = new JButton("No");
    noButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(yesButton);
    buttonPanel.add(noButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // adding components 
    this.add(rebalanceWarningText, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new RebalanceWarning();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons
    Object command = e.getSource();
    if (command == yesButton)
    {
      // running the rebalance calculation class 
      new RebalanceCalc();
      System.out.println("rebalance success");
      this.dispose();
    }
    // dispose frame 
    else if (command == noButton)
    {
      this.dispose();
    }
  }
}
