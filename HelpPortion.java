/*
Frame if user needs help navigating portion frame 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class HelpPortion extends JFrame
{

  // labels 
  private JLabel helpMessage;
  private JLabel help2Message;

  public HelpPortion()
  {
    super("Help");
    this.setBounds(926, 40, 501, 242);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    // constructing + formatting labels 
    this.helpMessage = new JLabel("<html><center>This section allows you to edit the "
      + "percentage of your total money allocated per class", SwingConstants.CENTER);
    helpMessage.setFont(Welcome.WORD_FONT);
    helpMessage.setForeground(Welcome.EGGSHELL);

    this.help2Message = new JLabel("<html><center>Note: your inputted "
      + "percentages has to equal 100</center></html>", SwingConstants.CENTER);
    help2Message.setFont(Welcome.SMALL_FONT);
    help2Message.setForeground(Welcome.EGGSHELL);

    // adding components 
    this.add(helpMessage, BorderLayout.NORTH);
    this.add(help2Message, BorderLayout.CENTER);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new HelpPortion();
  }
}
