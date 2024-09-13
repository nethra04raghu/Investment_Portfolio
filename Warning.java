/*
Frame to inform users of a warning regarding their inputted values 
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
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class Warning extends JFrame implements ActionListener
{

  // label 
  private JLabel warningMessage;

  // panel 
  private JPanel buttonPanel;

  // button
  private JButton exitButton;
  private JButton closeButton;

  public Warning(String message)
  {
    super("Warning");
    this.setBounds(21, 47, 450, 400);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane().setBackground(Welcome.TERRA_COTTA);
    this.setLayout(new BorderLayout());

    //message construction 
    this.warningMessage = new JLabel(message, SwingConstants.CENTER);
    warningMessage.setFont(Welcome.WORD_FONT);
    warningMessage.setForeground(Welcome.EGGSHELL);

    //button construction 
    this.exitButton = new JButton("Exit");
    exitButton.addActionListener(this);
    this.closeButton = new JButton("Close Pop-up");
    closeButton.addActionListener(this);

    //button panel construction + adding buttons 
    this.buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.add(exitButton);
    buttonPanel.add(closeButton);
    buttonPanel.setBackground(Welcome.EGGSHELL);

    this.add(warningMessage, BorderLayout.NORTH);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setVisible(true);
  }

  public static void main(String[] args)
  {
    new Warning("<html><center> I'm sorry, something's not right :/ </center></html>");
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    // exit program 
    Object command = e.getSource();
    if (command == exitButton)
    {
      System.exit(0);
    }
    // dipose frame 
    else if (command == closeButton)
    {
      this.dispose();
    }
  }
}
