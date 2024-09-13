/*
Frame if user needs help navigating the general investment portfolio  
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Help extends JFrame
{

  // label 
  private JLabel helpMessage;
  private JLabel help2Message;

  public Help()
  {
    super("Help");
    this.setBounds(926, 40, 501, 242);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing label + formatting label 
    this.helpMessage = new JLabel("<html><center>This is a program meant to "
      + "help track your investment portfolio</center></html>", SwingConstants.CENTER);
    helpMessage.setFont(Welcome.WORD_FONT);
    helpMessage.setForeground(Welcome.EGGSHELL);

    this.help2Message = new JLabel("<html><center>Use the buttons provided "
      + "to navigate this your portfolio</center></html>",
       SwingConstants.CENTER);
    help2Message.setFont(Welcome.SMALL_FONT);
    help2Message.setForeground(Welcome.EGGSHELL);

    // adding components 
    this.add(helpMessage, BorderLayout.NORTH);
    this.add(help2Message, BorderLayout.CENTER);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Help();
  }
}