/*
warning frame to inform user for turnover 
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class TurnoverWarning extends JFrame implements ActionListener
{
  // labels 
  private JLabel turnoverText;
  private JLabel turnoverMessage;

  // panel 
  private JPanel buttonPanel;
  private JButton returnButton;
  
  public TurnoverWarning(String flag)
  {
    super("Rebalance");
    this.setBounds(17, 37, 477, 250);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing labels 
    this.turnoverText = new JLabel("TURNOVER WARNING", SwingConstants.CENTER);
    turnoverText.setFont(Welcome.WORD_FONT);
    turnoverText.setForeground(Welcome.EGGSHELL);

    this.turnoverMessage = new JLabel(flag);
    turnoverMessage.setFont(Welcome.SMALL_FONT);
    turnoverMessage.setForeground(Welcome.EGGSHELL);

    // constructing button 
    this.returnButton = new JButton("Return");
    returnButton.addActionListener(this);

    // constructing button panel 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(returnButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    // adding components 
    this.add(turnoverText, BorderLayout.NORTH);
    this.add(turnoverMessage, BorderLayout.CENTER);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  public static void main(String[] args)
  {
    new TurnoverWarning("");
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // command for buttons 
    Object command = e.getSource();
    // dipose frame 
    if (command == returnButton)
    {
      this.dispose();
    }
  }
}
