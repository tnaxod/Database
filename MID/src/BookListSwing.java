import java.awt.*;
import java.util.*;
import java.util.Date;
import java.awt.Font;
import java.text.*;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;


public class BookListSwing extends JFrame implements ActionListener {
   JButton btnReset, btnInput, btnOrder1, btnOrder2, btnOrder3;
   JTextArea txtResult;
   JPanel pn1, pn2;
   JTextField tf1, tf2, tf3, tf4, tf5;
   int ordId = 20, bookId = 20, custId = 10;

   static Connection con;
   Statement stmt;

   ResultSet rs;
   String Driver = "";
   String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
   String userid = "madang";
   String pwd = "madang";      
  
   public BookListSwing() {
      super("16010945 �����");
      layInit();
      conDB();
      setVisible(true);
      setBounds(100, 100, 1000, 800); //������ġ,������ġ,���α���,���α���
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void layInit() {
      btnReset = new JButton("DB �ʱ�ȭ");
      btnInput = new JButton("INSERT"); //INSERT INTO ORDERS VALUES
      btnOrder1 = new JButton("Book ��ȸ"); //SELECT * FROM Book
      btnOrder2 = new JButton("ORDERS ��ȸ"); //SELECT * FROM ORDERS
      btnOrder3 = new JButton("CUSTOMER ��ȸ"); //SELECT * FROM CUSTOMER
      tf1 = new JTextField(3); //ORDER ID
      tf2 = new JTextField(3); //CUST ID
      tf3 = new JTextField(4); //BOOK ID
      tf4 = new JTextField(6); //SALE PRICE
      tf5 = new JTextField(12); //ORDER DATE
      txtResult = new JTextArea();
      pn1 = new JPanel();
      pn2 = new JPanel();
      
      pn2.add(new JLabel("ORDER ID(CHECK NO)"));
      pn2.add(tf1);
      pn2.add(new JLabel("CUST ID"));
      pn2.add(tf2);
      pn2.add(new JLabel("BOOK ID"));
      pn2.add(tf3);
      pn2.add(new JLabel("SALE PRICE"));
      pn2.add(tf4);
      pn2.add(new JLabel("ORDER DATE (YYYYMMDD)"));
      pn2.add(tf5);
      pn2.add(btnInput);
      pn1.add(btnOrder1);
      pn1.add(btnOrder2);
      pn1.add(btnOrder3);
      pn1.add(btnReset);
      
      Font font = new Font("Serief", Font.PLAIN, 14);
      txtResult.setFont(font);
      txtResult.setEditable(false);
     // txtResult.fontSize(15);
      JScrollPane scrollPane = new JScrollPane(txtResult);
      add("North", pn2);
      add("South", pn1);
      add("Center", scrollPane);
      btnOrder1.addActionListener(this);
      btnOrder2.addActionListener(this);
      btnOrder3.addActionListener(this);
      btnInput.addActionListener(this);
      btnReset.addActionListener(this);
   }

   public void conDB() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         System.out.println("����̹� �ε� ����");
      }
      catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
      
      try { /* �����ͺ��̽��� �����ϴ� ���� */
          System.out.println("�����ͺ��̽� ���� �غ�...");
          con = DriverManager.getConnection(url, userid, pwd);
          System.out.println("�����ͺ��̽� ���� ����\n!- INSERT �Է� �� ���� �ϱ� -!");
          
          stmt = con.createStatement();
          //CUSTOMERS
          stmt.executeUpdate("INSERT INTO CUSTOMER VALUES('6', '��ȿ��', '���ѹα� ����', '010-1981-1201')");
          stmt.executeUpdate("INSERT INTO CUSTOMER VALUES('7', '���¿�', '���ѹα� ����', '010-1989-0309')");
          stmt.executeUpdate("INSERT INTO CUSTOMER VALUES('8', '������', '���ѹα� ������', '010-1992-0506')");
          stmt.executeUpdate("INSERT INTO CUSTOMER VALUES('9', '������', '���ѹα� �뱸', '010-1996-1101')");
          stmt.executeUpdate("INSERT INTO CUSTOMER VALUES('10', '���̳�', '���ѹα� ����', '010-1979-0427')");
          //BOOK
          stmt.executeUpdate("INSERT INTO BOOK VALUES('11', 'GIFT', '�۷���', '33000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('12', 'DREAMER', '�۷���', '40000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('13', 'PURPOSE', 'SNSD', '30000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('14', 'MY VOICE', 'SNSD', '38000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('15', 'WHY', 'SNSD', '25000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('16', 'CITY LIGHTS', 'EXO', '27000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('17', 'MONSTER', 'EXO', '29000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('18', '18�� ����', '����', '25000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('19', '���ǹ��', '����', '17000')");
          stmt.executeUpdate("INSERT INTO BOOK VALUES('20', 'LOVE ME RIGHT', 'EXO', '19000')");
          //ORDERS
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('11', '6', '14', '13000', '2020-04-29');");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('12', '6', '12', '18000', '2020-04-27')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('13', '7', '13', '10000', '2020-04-20')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('14', '10', '14', '12000', '2020-04-28')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('15', '9', '12', '17000', '2020-04-21')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('16', '8', '18', '3000', '2020-04-05')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('17', '7', '19', '2000', '2020-04-17')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('18', '8', '15', '6000', '2020-04-19')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('19', '6', '11', '15000', '2020-04-20')");
          stmt.executeUpdate("INSERT INTO ORDERS VALUES('20', '10', '20', '4000', '2020-04-27')");
          
       }
      catch (SQLException e1) {
          e1.printStackTrace();
       }
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      try {
         stmt = con.createStatement();
       
         rs = stmt.executeQuery("select count(*) as orderid from orders;");
         rs.next();
         ordId = rs.getInt(1);
                 
         String query1 = "SELECT * FROM Book";
         String query2 = "SELECT * FROM Orders";
         String query3 = "SELECT * FROM Customer";
         
         if (e.getSource() == btnOrder1) {//SELECT * FROM Book
            txtResult.setText("");
            txtResult.setText("BOOKID\tBOOK NAME\t\tPUBLISHER\tPRICE\n");
            rs = stmt.executeQuery(query1);
            while (rs.next()) {
               String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t" + rs.getInt(4)
                     + "\n";
               txtResult.append(str);
            }
         }//if (e.getSource() == btnOrder1)
         else if(e.getSource() == btnOrder2) {//SELECT * FROM Orders
        	 txtResult.setText("");
             txtResult.setText("ORDERID\tCUSTID\tBOOKID\tSALE PRICE\tORDER DATE\n");
             rs = stmt.executeQuery(query2);
             while (rs.next()) {
                String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "  \t" + rs.getInt(4)
                		+ " \t" + rs.getString(5) + "\n";
                txtResult.append(str);
             }
         }//else if(e.getSource() == btnOrder2)
         else if(e.getSource() == btnOrder3) {//SELECT * FROM Customer
        	 txtResult.setText("");
             txtResult.setText("CUSTID\tNAME\tADDRESS\t\tPHONE\n");
             rs = stmt.executeQuery(query3);
             while (rs.next()) {
                String str = "    " + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                      + "\n";
                txtResult.append(str);
             }
         }//else if(e.getSource() == btnOrder3)
         else if(e.getSource() == btnInput) {//INSERT ORDERS TABLE
        	 txtResult.setText("");
        	 int tot = 0;
        	 String orderid = tf1.getText();
        	 if(Integer.parseInt(orderid) != ordId + 1)
        		 System.out.println("�˸��� orderid�� �ƴմϴ�. orerid�� ��ȣ�� Ȯ���ϰ� �Է����ּ���.");
        	 else
        		 tot++;
        	
        	 String custid = tf2.getText();
        	 if(tf2.getText().isEmpty())
        		 System.out.println("custid�� �Է����ּ���.");
        	 else if(1 <= Integer.parseInt(custid) && Integer.parseInt(custid) <= 10)
        		 tot++;
        	 else
        		 System.out.println("�˸��� custid�� �ƴմϴ�.");
        	
        	 String bookid = tf3.getText();
        	 if(tf3.getText().isEmpty())
        		 System.out.println("bookid�� �Է����ּ���.");
        	 else if(1 <= Integer.parseInt(bookid) && Integer.parseInt(bookid) <= 20)
        		 tot++;        		 
        	 else
        		 System.out.println("�˸��� bookid�� �ƴմϴ�.");
        	 
        	 String saleprice = tf4.getText();
        	 if(Integer.parseInt(saleprice) > 0)
        		 tot++;
        	 else
        		 System.out.println("�˸��� saleprice�� �ƴմϴ�.");
        	 
        	 String orderdate = tf5.getText();
        	 SimpleDateFormat dateIn = new SimpleDateFormat("yyyyMMdd");
        	 SimpleDateFormat dateOut = new SimpleDateFormat("yyyy-MM-dd");
         	
        	 if(orderdate.length() == 8) {
        		 Date date = dateIn.parse(orderdate);
        		 orderdate = dateOut.format(date);
        		 tot++;
        		 if(tot == 5) {
            		 ordId++;
            		 stmt.executeUpdate("INSERT INTO ORDERS VALUES('"+ordId+"', '"+custId+"', '"+bookId+"', '"+saleprice+"','"+orderdate+"')");
            		 System.out.println("�����Ͱ� �߰� �Ǿ����ϴ�.");
            	 }
        		 else
        			 System.out.println("������ �߰��� �����Ͽ����ϴ�.");
        	 }
        	 else {
        		 System.out.println("�˸��� orderdate�� �ƴմϴ�.");
        		 System.out.println("������ �߰��� �����Ͽ����ϴ�.");
        	 }      
         }//else if(e.getSource() == btnInput)
         else if (e.getSource() == btnReset) {
            txtResult.setText("");
            for(int i = ordId; i > 20 ; i--)
            	stmt.executeUpdate("DELETE FROM orders WHERE orderid = '"+i+"'");
            System.out.println("Orders ���̺� ������ �ʱ�ȭ �Ϸ�!");
         }
         
      } catch (Exception e2) {
         System.out.println("���� �б� ���� :" + e2);
/*      } finally {
         try {
            if (rs != null)
               rs.close();
            if (stmt != null)
               stmt.close();
            if (con != null)
               con.close();
         } catch (Exception e3) {
            // TODO: handle exception
         }
  */
      }

   }

   public static void main(String[] args) {
      BookListSwing BLS = new BookListSwing();
      
      //BLS.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //BLS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      BLS.addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent we) {
    		try {
    			Statement stmt;
    			stmt = con.createStatement();
    			for(int i = 20; i > 10 ; i--)
                	stmt.executeUpdate("DELETE FROM orders WHERE orderid = '"+i+"'");
    			for(int i = 20; i > 10 ; i--)
    				stmt.executeUpdate("DELETE FROM book WHERE bookid = '"+i+"'");
    			for(int i = 10; i > 5; i--) 
    				stmt.executeUpdate("DELETE FROM customer WHERE custid = '"+i+"'");
    			con.close();
    			
    		} catch (Exception e4) { 	}
    		System.out.println("madnag data �⺻ �� �ʱ�ȭ �Ϸ�!");
    		System.out.println("���α׷� ���� ����!");
    	
    	  }
    	});
   }
}