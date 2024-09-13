/*
Frame if user needs help navigating estimate frame 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class HelpEstimate extends JFrame
{

  // labels 
  private JLabel helpMessage;
  private JLabel help2Message;

  public HelpEstimate()
  {
    super("Help");
    this.setBounds(926, 40, 501, 242);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing + formatting labels 
    this.helpMessage = new JLabel("<html><center>If you want to input your "
      + "estimated growth per asset class, do so here !", SwingConstants.CENTER);
    helpMessage.setFont(Welcome.WORD_FONT);
    helpMessage.setForeground(Welcome.EGGSHELL);

    this.help2Message = new JLabel("<html><center>Note: you can compare your "
      + "growth estimates with the actual growth</center></html>", SwingConstants.CENTER);
    help2Message.setFont(Welcome.SMALL_FONT);
    help2Message.setForeground(Welcome.EGGSHELL);

    // adding components 
    this.add(helpMessage, BorderLayout.NORTH);
    this.add(help2Message, BorderLayout.CENTER);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new HelpEstimate();
  }
}
