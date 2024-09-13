/*
Displaying the table for users to look at their assets for fixed income 
 */
//package investmentportfolio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class FixedIncomeTable extends JFrame implements ActionListener
{

  private JButton closeButton;
  private JPanel buttonPanel;

  private ArrayList<ArrayList<String>> dataList;
  private Object[][] data;
  private JTable dbTable;
  private JScrollPane scrollTable;
  private JTableHeader header;
  private TableColumn column;

  public FixedIncomeTable(String dbName, String tableName, String[] columnNames)
  {
    super("Fixed Income Table");
    this.setBounds(227, 101, 1000, 500);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.getContentPane();
    this.setLayout(new BorderLayout());

    // constructing button
    closeButton = new JButton("Close");
    closeButton.addActionListener(this);
    
    // constructing panel
    buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Welcome.NAVY);
    buttonPanel.add(closeButton);

    // get data by:
    // creating object od javadb & set connection 
    JavaDatabase objDb = new JavaDatabase(dbName);
    // use the object to get the data in arraylist form 
    dataList = objDb.getData(tableName, columnNames);
    // convert the arrayList to 2d array for JTable 
    data = objDb.to2dArray(dataList);

    dbTable = new JTable(data, columnNames);
    // format the cells 
    dbTable.setGridColor(Welcome.NAVY);
    dbTable.setBackground(Welcome.EGGSHELL);

    // format the header 
    header = dbTable.getTableHeader();
    header.setBackground(Welcome.EGGSHELL);
    header.setForeground(Welcome.NAVY);
    header.setFont(Welcome.WORD_FONT);
    // format rows
    dbTable.setRowHeight(25);

    // put the Table into Scroll Panel 
    scrollTable = new JScrollPane();
    scrollTable.getViewport().add(dbTable);
    dbTable.setFillsViewportHeight(true);

    column = dbTable.getColumnModel().getColumn(0);
    column.setPreferredWidth(15);
    column = dbTable.getColumnModel().getColumn(1);
    column.setPreferredWidth(15);
    column = dbTable.getColumnModel().getColumn(2);
    column.setPreferredWidth(50);

    // place components and make frame visible 
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.add(scrollTable, BorderLayout.CENTER);
    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String command = e.getActionCommand();
    // dispose frame 
    if (command.equals("Close"))
    {
      this.dispose();
    }
  }

  public static void main(String[] args)
  {
    // db info 
    String dbName = "Investment Portfolio";
    String tableName = "FixedIncome";
    String[] columnNames =
    {
      "Name", "Date_Bought", "Quantity", "Initial_Price",
      "Current_Price"
    };
    String dbQuery = "INSERT INTO FixedIncome VALUES (?,?,?,?,?)";

    new BondTable(dbName, tableName, columnNames);
  }
}
