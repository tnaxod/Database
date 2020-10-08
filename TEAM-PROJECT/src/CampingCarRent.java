import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class CampingCarRent extends JFrame {

   // private static final LayoutManager BorderLayout = null;
   // DB���� ���
   private DataBaseAccess dbAccess; // DataBase �����ϴ� ��ü ����

   private CompanyTable companyTable;
   private CarBasicInfoTable carBasicTable;
   private CarDetailsTable carDetailsTable;
   private UserCarBasicInfoTable userCarBasicTable;
   private UserCarDetailsTable userCarDetailsTable;
   private CustomerInfoTable customerInfoTable;
   private RentalBasicTable rentalBasicTable;
   private RentalDetailsTable rentalDetailTable;
   private InspectionTable inspectionTable;
   private WorkShopTable workShopTable;
   private HistoryTable historyTable;
   private Search1Table search1Table;
   private Search2Table search2Table;
   private Search3Table search3Table;
   private Search4Table search4Table;
   // UI������� ����
   // private ButtonGroup modeSelect; // RadioButton����z
   private JPanel statePanel; // ����Ȯ��
   private JRadioButton radioBtn_Sudo; // �����ڿ� RadioButton
   private JRadioButton radioBtn_User; // �Ϲݻ���ڿ� RadioButton
   private ButtonGroup modeGroup; // ��ư �׷�ȭ ��
   private JToolBar modeSelect; // radioButton ǥ�ÿ� ���ڽ�

   private JTabbedPane menuTab; // �޴� ��
   private JScrollPane scroll;

   private JPanel control;
   private JPanel optionPanel0;

   private JPanel optionPanel1;
   private JTextField txtCoName;
   private JTextField txtCoAddr;
   private JTextField txtCoDigit;
   private JTextField txtManagerName;
   private JTextField txtManagerEmail;
   private JLabel labelCoName;
   private JLabel labelCoAddr;
   private JLabel labelDigit;
   private JLabel labelManagerName;
   private JLabel labelManagerEmail;

   private JPanel optionPanel2;
   private JTextField txtRentCoId;
   private JTextField txtCarName;
   private JTextField txtSeatNum;
   private JTextField txtRentalPrice;
   private JTextField txtRegistDate;
   private JTextField txtCarNum;
   private JTextField txtmaufactCo;
   private JTextField txtmanufactYear;
   private JTextField txtodo;
   private JLabel labelRentCoId;
   private JLabel labelCarName;
   private JLabel labelSeatNum;
   private JLabel labelRentalPrice;
   private JLabel labelRegistDate;
   private JLabel labelCarNum;
   private JLabel labelmaufactCo;
   private JLabel labelmanufactYear;
   private JLabel labelodo;

   private JPanel optionPanel3;
   private JTextField txtLicenseNum;
   private JTextField txtCustName;
   private JTextField txtCustAddr;
   private JTextField txtCustDigit;
   private JTextField txtCustEmail;
   private JLabel labelLicenseNum;
   private JLabel labelCustName;
   private JLabel labelCustAddr;
   private JLabel labelCustDigit;
   private JLabel labelCustEmail;

   private JPanel optionPanel4;
   private JTextField txtWorkShopName;
   private JTextField txtShopAddr;
   private JTextField txtShopDigit;
   private JTextField txtShopManagerName;
   private JTextField txtShopManagerEmail;
   private JLabel labelWorkShopName;
   private JLabel labelShopAddr;
   private JLabel labelShopDigit;
   private JLabel labelShopManagerName;
   private JLabel labelShopManagerEmail;
   
   private JPanel optionPanel6;
   private JTextField txtCarRegistId1;
   private JTextField txtFront;
   private JTextField txtRight;
   private JTextField txtLeft;
   private JTextField txtBack;
   private JLabel labelCarRegist1;
   private JLabel labelFront;
   private JLabel labelRight;
   private JLabel labelLeft;
   private JLabel labelBack;
   private JButton btn_return;
   
   private JPanel optionPanel5;
   private JTextField txtCarRegistId;
   private JTextField txtRentCoId2;
   private JTextField txtLicenseNum2;
   private JTextField txtStart;
   private JTextField txtPeriod;
   private JTextField txtClaims;
   private JLabel labelCarRegistId;
   private JLabel labelRentCoId2;
   private JLabel labelLicenseNum2;
   private JLabel labelStart;
   private JLabel labelPeriod;
   private JLabel labelClaims;
   
   private JPanel optionPanel_Search1;
   private JLabel labelSearch1Info;
   private JPanel optionPanel_Search2;
   private JLabel labelSearch2Info;
   private JPanel optionPanel_Search3;
   private JLabel labelSearch3Info;
   private JPanel optionPanel_Search4;
   private JLabel labelSearch4Info;
   
   private JButton btn_SignUp;
   private JButton btn_Enrollment;
   private JButton btn_Cust;
   private JButton btn_Shop;
   private JButton btn_User;
   private JButton btn_Delete;
   private JButton btn_Update;

   private int deleteRow;
   private int updateRow;
   private boolean checkScreen[];

   public CampingCarRent() { // Constructor
      super("CAMPING CAR RENT (16010945 / 16011059)"); // 16010945 �����, 16011059 ������
      dbAccess = new DataBaseAccess();
      companyTable = new CompanyTable(dbAccess);
      carBasicTable = new CarBasicInfoTable(dbAccess);
      carDetailsTable = new CarDetailsTable(dbAccess);
      userCarBasicTable = new UserCarBasicInfoTable(dbAccess);
      userCarDetailsTable = new UserCarDetailsTable(dbAccess);
      customerInfoTable = new CustomerInfoTable(dbAccess);
      rentalBasicTable = new RentalBasicTable(dbAccess);
      rentalDetailTable = new RentalDetailsTable(dbAccess);
      inspectionTable = new InspectionTable(dbAccess);
      workShopTable = new WorkShopTable(dbAccess);
      historyTable = new HistoryTable(dbAccess);

      search1Table = new Search1Table(dbAccess);
      search2Table = new Search2Table(dbAccess);
      search3Table = new Search3Table(dbAccess);
      search4Table = new Search4Table(dbAccess);

      modeSelect = new JToolBar("modeSelect");
      statePanel = new JPanel();
      radioBtn_Sudo = new JRadioButton("������ ���");
      radioBtn_Sudo.setSelected(true);
      radioBtn_Sudo.addItemListener(new MyItemListener());
      radioBtn_User = new JRadioButton("����� ���");
      modeGroup = new ButtonGroup();

      menuTab = new JTabbedPane(JTabbedPane.LEFT);
      menuTab.setBackground(Color.pink);

      control = new JPanel();
      optionPanel0 = new JPanel();

      optionPanel1 = new JPanel();
      txtCoName = new JTextField(7);
      txtCoAddr = new JTextField(15);
      txtCoDigit = new JTextField(7);
      txtManagerName = new JTextField(7);
      txtManagerEmail = new JTextField(15);

      optionPanel2 = new JPanel();

      labelCoName = new JLabel("ȸ�� ��");
      labelCoAddr = new JLabel("ȸ�� �ּ�");
      labelDigit = new JLabel("ȸ�� ��ȭ��ȣ");
      labelManagerName = new JLabel("����� �̸�");
      labelManagerEmail = new JLabel("����� �̸���");

      btn_SignUp = new JButton("���");
      btn_Enrollment = new JButton("���");
      btn_Cust = new JButton("���");
      btn_Shop = new JButton("���");
      btn_User = new JButton("�뿩");
      btn_Delete = new JButton("����");
      btn_Update = new JButton("����");
      
      optionPanel_Search1 = new JPanel();
      labelSearch1Info = new JLabel("#ķ��ī �뿩 �� ���� ����� �ִ� �� ���� �˻�(��������)");
      optionPanel_Search2 = new JPanel();
      labelSearch2Info = new JLabel("#���� ���� �̷���� ����ҵ� ����");
      optionPanel_Search3 = new JPanel();
      labelSearch3Info = new JLabel("#�뿩 ������ ķ��ī�� �뿩 ȸ�� ���� ��ȸ");
      optionPanel_Search4 = new JPanel();
      labelSearch4Info = new JLabel("#���� ���� �̷���� ķ��ī �̸�, ķ��ī �����, �뿩����");
      
      optionPanel6 = new JPanel();
      txtCarRegistId1 = new JTextField(5);
      txtFront = new JTextField(5);
      txtRight = new JTextField(5);
      txtLeft = new JTextField(5);
      txtBack = new JTextField(5);
      
      labelCarRegist1 = new JLabel("�����뿩��ȣID");
      labelFront = new JLabel("��");
      labelRight = new JLabel("������");
      labelLeft = new JLabel("����");
      labelBack = new JLabel("��");
      btn_return = new JButton("�ݳ�");

      optionPanel5 = new JPanel();
      txtCarRegistId = new JTextField(5);
      txtRentCoId2 = new JTextField(5);
      txtLicenseNum2 = new JTextField(8);
      txtStart = new JTextField(7);
      txtPeriod = new JTextField(7);
      txtClaims = new JTextField(5);
      labelCarRegistId = new JLabel("ķ��ī ���ID");
      labelRentCoId2 = new JLabel("ķ��ī �뿩ȸ��ID");
      labelLicenseNum2 = new JLabel("�������� ��ȣ");
      labelStart = new JLabel("�뿩 ������");
      labelPeriod = new JLabel("�뿩 �Ⱓ");
      labelClaims = new JLabel("��Ÿ û������");

      optionPanel4 = new JPanel();
      txtWorkShopName = new JTextField(7);
      txtShopAddr = new JTextField(15);
      txtShopDigit = new JTextField(7);
      txtShopManagerName = new JTextField(5);
      txtShopManagerEmail = new JTextField(10);
      labelWorkShopName = new JLabel("����� �̸�");
      labelShopAddr = new JLabel("����� �ּ�");
      labelShopDigit = new JLabel("����� ��ȭ��ȣ");
      labelShopManagerName = new JLabel("����� �̸�");
      labelShopManagerEmail = new JLabel("����� �̸���");

      optionPanel3 = new JPanel();
      txtLicenseNum = new JTextField(10);
      txtCustName = new JTextField(5);
      txtCustAddr = new JTextField(10);
      txtCustDigit = new JTextField(7);
      txtCustEmail = new JTextField(10);
      labelLicenseNum = new JLabel("�������� ��ȣ");
      labelCustName = new JLabel("�� ��");
      labelCustAddr = new JLabel("�� ��");
      labelCustDigit = new JLabel("��ȭ��ȣ");
      labelCustEmail = new JLabel("Email");

      optionPanel2 = new JPanel();
      txtRentCoId = new JTextField(5);
      txtCarName = new JTextField(7);
      txtSeatNum = new JTextField(5);
      txtRentalPrice = new JTextField(7);
      txtRegistDate = new JTextField(10);
      txtCarNum = new JTextField(4);
      txtmaufactCo = new JTextField(5);
      txtmanufactYear = new JTextField(4);
      txtodo = new JTextField(5);

      labelRentCoId = new JLabel("��ü ID");
      labelCarName = new JLabel("�� �̸�");
      labelSeatNum = new JLabel("���� �ο� ��");
      labelRentalPrice = new JLabel("����");
      labelRegistDate = new JLabel("�������");
      labelCarNum = new JLabel("���� ��ȣ");
      labelmaufactCo = new JLabel("������");
      labelmanufactYear = new JLabel("�����⵵");
      labelodo = new JLabel("����Ÿ�");

      checkScreen = new boolean[13];

      for (int i = 1; i < 13; i++)
         checkScreen[i] = false;
      checkScreen[0] = true;

      layInit();
      setSize(1200, 700);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   } // Constructor

   private void addTab() {
      menuTab.removeAll();

      if (radioBtn_Sudo.isSelected()) {
         menuTab.addTab("ķ��ī ȸ�� ����", new CompanyTable(dbAccess));
         menuTab.addTab("ķ��ī �⺻ ����", new CarBasicInfoTable(dbAccess)); //
         menuTab.addTab("ķ��ī �� ����", new CarDetailsTable(dbAccess)); //
         menuTab.addTab("�� ����", new CustomerInfoTable(dbAccess)); //
         menuTab.addTab("ķ��ī �뿩 �⺻ ����", new RentalBasicTable(dbAccess));
         menuTab.addTab("ķ��ī �뿩 �� ����", new RentalDetailsTable(dbAccess));
         menuTab.addTab("ķ��ī ���� ����", new InspectionTable(dbAccess));
         menuTab.addTab("����� ����", new WorkShopTable(dbAccess));
         menuTab.addTab("ķ��ī ���� ����", new HistoryTable(dbAccess));
         menuTab.addTab("�˻�1", new Search1Table(dbAccess));
         menuTab.addTab("�˻�2", new Search2Table(dbAccess));
         menuTab.addTab("�˻�3", new Search3Table(dbAccess));
         menuTab.addTab("�˻�4", new Search4Table(dbAccess));
         add(menuTab);

         menuTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               // TODO Auto-generated method stub
               // JOptionPane.showMessageDialog(null, "menuTab = " +
               // menuTab.getSelectedIndex());
               int index = menuTab.getSelectedIndex();

               for (int i = 0; i < 13; i++)
                  checkScreen[i] = false;
               if (radioBtn_Sudo.isSelected()) {
                  if (index == 0) {
                     checkScreen[0] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(true);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  } else if (index == 1) {
                     checkScreen[1] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(true);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  } else if (index == 3) {
                     checkScreen[3] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(true);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  }
                  else if(index == 4) {
                	  checkScreen[4] = true;
                      optionPanel0.setVisible(false);
                      optionPanel1.setVisible(false);
                      optionPanel2.setVisible(false);
                      optionPanel3.setVisible(false);
                      optionPanel4.setVisible(false);
                      optionPanel5.setVisible(false);
                      optionPanel6.setVisible(true);
                      optionPanel_Search1.setVisible(false);
                      optionPanel_Search2.setVisible(false);
                      optionPanel_Search3.setVisible(false);
                      optionPanel_Search4.setVisible(false);
                  }
                  else if (index == 7) {
                     checkScreen[7] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(true);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  }
                  else if(index == 9) {
                     checkScreen[9] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(true);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  }
                  else if(index == 10) {
                     checkScreen[10] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(true);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  }
                  else if(index == 11) {
                     checkScreen[11] = true;
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(true);
                     optionPanel_Search4.setVisible(false);
                  }
                  else if(index == 12) {
                	  checkScreen[12] = true;
                      optionPanel0.setVisible(false);
                      optionPanel1.setVisible(false);
                      optionPanel2.setVisible(false);
                      optionPanel3.setVisible(false);
                      optionPanel4.setVisible(false);
                      optionPanel5.setVisible(false);
                      optionPanel6.setVisible(false);
                      optionPanel_Search1.setVisible(false);
                      optionPanel_Search2.setVisible(false);
                      optionPanel_Search3.setVisible(false);
                      optionPanel_Search4.setVisible(true);
                  }
                  else {
                     optionPanel0.setVisible(true);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                     optionPanel_Search4.setVisible(false);
                  }
               }
               else if(radioBtn_User.isSelected()) {
                  
                  if(index == 0) {
                     optionPanel0.setVisible(false);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(true);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                  }
                  else {
                     optionPanel0.setVisible(true);
                     optionPanel1.setVisible(false);
                     optionPanel2.setVisible(false);
                     optionPanel3.setVisible(false);
                     optionPanel4.setVisible(false);
                     optionPanel5.setVisible(false);
                     optionPanel6.setVisible(false);
                     optionPanel_Search1.setVisible(false);
                     optionPanel_Search2.setVisible(false);
                     optionPanel_Search3.setVisible(false);
                  }
                     
               }
            }
         });
      } else {
         menuTab.addTab("ķ��ī �⺻ ����", new UserCarBasicInfoTable(dbAccess)); //
         menuTab.addTab("ķ��ī �� ����", new UserCarDetailsTable(dbAccess)); //
         add(menuTab);
         menuTab.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               // TODO Auto-generated method stub
               // JOptionPane.showMessageDialog(null, "menuTab = " +
               // menuTab.getSelectedIndex());
               int index = menuTab.getSelectedIndex();

               for (int i = 0; i < 2; i++)
                  checkScreen[i] = false;

               if (index == 0) {
                  checkScreen[0] = true;
                  optionPanel1.setVisible(false);
                  optionPanel2.setVisible(false);
                  optionPanel3.setVisible(false);
                  optionPanel4.setVisible(false);
                  optionPanel5.setVisible(true);
               } else { // if (index == 1)
                  checkScreen[1] = true;
                  optionPanel1.setVisible(false);
                  optionPanel2.setVisible(false);
                  optionPanel3.setVisible(false);
                  optionPanel4.setVisible(false);
                  optionPanel5.setVisible(false);
               }
            }
         });
      }

   }

   private void layInit() {
      statePanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
      statePanel.setVisible(true);

      modeGroup.add(radioBtn_Sudo); // ������ �׷�ȭ
      modeGroup.add(radioBtn_User); // �Ϲ� ����� �׷�ȭ

      statePanel.add(radioBtn_Sudo);
      statePanel.add(radioBtn_User);
      statePanel.add(btn_Delete);
      statePanel.add(btn_Update);
      
      modeSelect.add(statePanel);

      add(modeSelect, BorderLayout.NORTH);

      addTab();

      // �뿩 ȸ�� ���
      btn_SignUp.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("���") && !txtCoName.getText().equals("") && !txtCoAddr.getText().equals("")
                  && !txtCoDigit.getText().equals("") && !txtManagerName.getText().equals("")
                  && !txtManagerEmail.getText().equals("")) {

               int returnCode = dbAccess.carCompanySignUp(txtCoName.getText(), txtCoAddr.getText(),
                     txtCoDigit.getText(), txtManagerName.getText(), txtManagerEmail.getText());

               if (returnCode == dbAccess.rentCoId_Company.size()) {
                  JOptionPane.showMessageDialog(null, "��� �Ϸ�!!\n ��Ϲ�ȣ :" + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "��� ����!!");
               }
            } else {
               JOptionPane.showMessageDialog(null, "�����͸� �Է��� �ּ���");
            }
         }
      });

      // ķ��ī ���
      btn_Enrollment.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("���") && !txtRentCoId.getText().equals("") && !txtCarName.getText().equals("")
                  && !txtSeatNum.getText().equals("") && !txtRentalPrice.getText().equals("")
                  && !txtRegistDate.getText().equals("") && !txtCarNum.getText().equals("")
                  && !txtmaufactCo.getText().equals("") && !txtmanufactYear.getText().equals("")
                  && !txtodo.getText().equals("")) {
               int returnCode = dbAccess.carEnrollment(txtRentCoId.getText(), txtCarName.getText(),
                     txtSeatNum.getText(), txtRentalPrice.getText(), txtRegistDate.getText(),
                     txtCarNum.getText(), txtmaufactCo.getText(), txtmanufactYear.getText(), txtodo.getText());

               if (returnCode == dbAccess.carRegistId_CarBasic.size()) {
                  JOptionPane.showMessageDialog(null, "��ϿϷ�!!\n ��Ϲ�ȣ : " + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "��� ����!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "�����͸� �Է��� �ּ���");
            }

         }
      });

      // �� ���
      btn_Cust.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("���") && !txtLicenseNum.getText().equals("")
                  && !txtCustName.getText().equals("") && !txtCustAddr.getText().equals("")
                  && !txtCustDigit.getText().equals("") && !txtCustEmail.getText().equals("")) {
               int returnCode = dbAccess.custInfo(txtLicenseNum.getText(), txtCustName.getText(),
                     txtCustAddr.getText(), txtCustDigit.getText(), txtCustEmail.getText());
               if (returnCode == dbAccess.custLicenseNum_CustInfo.size()) {
                  JOptionPane.showMessageDialog(null, "��ϿϷ�!!\n");
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "��� ����!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "�����͸� �Է��� �ּ���");
            }
         }

      });
      // ����� ���
      btn_Shop.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("���") && !txtWorkShopName.getText().equals("")
                  && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                  && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
               int returnCode = dbAccess.shopInfo(txtWorkShopName.getText(), txtShopAddr.getText(),
                     txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
               if (returnCode == dbAccess.workShopId_ShopInfo.size()) {
                  JOptionPane.showMessageDialog(null, "��ϿϷ�!!\n ��Ϲ�ȣ : " + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "��� ����!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "�����͸� �Է��� �ּ���");
            }
         }

      });

      // !_ ���� _!
      btn_Delete.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            int returnCode;
            if (btn.getText().equals("����")) {

               if (checkScreen[0]) { // �뿩ȸ�� ���� ����
                  returnCode = dbAccess.deleteRowData("CampingCarCompany", "rentCoId",
                        Integer.toString(deleteRow + 1));

                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "�����Ϸ�!! ");
                  else
                     JOptionPane.showMessageDialog(null, "��������!! ");
               }

               else if (checkScreen[1]) { // ķ��ī ���� (ķ��ī �⺻�������� ���� ������)
                  // Details���� ���� ���� �� ��, BasicInfo�� ����
                  returnCode = dbAccess.deleteRowData("CampingCarDetails", "carRegistId",
                        Integer.toString(deleteRow + 1));
                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "������ �����Ϸ�!! ");
                  else
                     JOptionPane.showMessageDialog(null, "������ ��������!! ");
                  returnCode = dbAccess.deleteRowData("CampingCarBasicInfo", "carRegistId",
                        Integer.toString(deleteRow + 1));
                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "�⺻���� �����Ϸ�!! ");
                  else
                     JOptionPane.showMessageDialog(null, "�⺻���� ��������!! ");
               } else if (checkScreen[3]) { // �� ���� - ��������������ȣ
                  returnCode = dbAccess.deleteRowData("CustomerInfo", "custLicenseNum", Integer.toString(deleteRow + 1));
                  if(returnCode == 1) JOptionPane.showMessageDialog(null, "������ �����Ϸ�!! ");
                  else JOptionPane.showMessageDialog(null, "������ ��������!! ");
               } else if (checkScreen[7]) { // ����� ����
                  returnCode = dbAccess.deleteRowData("WorkShopInfo", "workShopId", Integer.toString(deleteRow) + 1);
                  if(returnCode == 1) JOptionPane.showMessageDialog(null, "����� ���� �����Ϸ�!! ");
                  else JOptionPane.showMessageDialog(null, "����� ���� ��������!! ");
               } else { // ���� �Ұ���

               }

               addTab();
            }
         }

      });

         // !_����_!
         btn_Update.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
            public void actionPerformed(ActionEvent e) {
               JButton btn = (JButton) e.getSource();
               // String Row, String coName, String coAddr, String coDigit, String coMName,
               // String coMEmail
               if (btn.getText().equals("����")) {

                  if (checkScreen[0]) { // �뿩ȸ�� ���� ����
                     if (!txtCoName.getText().equals("") && !txtCoAddr.getText().equals("")
                           && !txtCoDigit.getText().equals("") && !txtManagerName.getText().equals("")
                           && !txtManagerEmail.getText().equals("")) {

                        int returnCode = dbAccess.updateCompanyData(Integer.toString(deleteRow + 1),
                              txtCoName.getText(), txtCoAddr.getText(), txtCoDigit.getText(),
                              txtManagerName.getText(), txtManagerEmail.getText());
                        if (returnCode == 1) {
                           JOptionPane.showMessageDialog(null, "���� �Ϸ�!! ");
                        } else {
                           JOptionPane.showMessageDialog(null, "���� ����!! ");
                        }
                     } else {
                        JOptionPane.showMessageDialog(null, "���� ����!! ");
                     }
                  }

                  else if (checkScreen[1]) { // ķ��ī ����
                     if (!txtRentCoId.getText().equals("") && !txtCarName.getText().equals("")
                           && !txtSeatNum.getText().equals("") && !txtRentalPrice.getText().equals("")
                           && !txtRegistDate.getText().equals("") && !txtCarNum.getText().equals("")
                           && !txtmaufactCo.getText().equals("") && !txtmanufactYear.getText().equals("")
                           && !txtodo.getText().equals("")) {
                        int returnCode = dbAccess.updateCarInfoData(Integer.toString(deleteRow + 1),
                              txtRentCoId.getText(), txtCarName.getText(), txtSeatNum.getText(),
                              txtRentalPrice.getText(), txtRegistDate.getText(), txtCarNum.getText(),
                              txtmaufactCo.getText(), txtmanufactYear.getText(), txtodo.getText());
                        if (returnCode == 1) {
                           JOptionPane.showMessageDialog(null, "���� �Ϸ�!! ");
                        } else {
                           JOptionPane.showMessageDialog(null, "���� ����!! ");
                        }
                     } else {
                        JOptionPane.showMessageDialog(null, "���� ����!! ");
                     }
                  }

                  else if (checkScreen[3]) { // �� ���� ����
                     if (!txtCustName.getText().equals("") && !txtCustAddr.getText().equals("")
                             && !txtCustDigit.getText().equals("") && !txtCustEmail.getText().equals("")) {
                          int returnCode = dbAccess.updateCustData(Integer.toString(deleteRow + 1), txtCustName.getText(),
                                txtCustAddr.getText(), txtCustDigit.getText(), txtCustEmail.getText());
                          if (returnCode == 1) {
                              JOptionPane.showMessageDialog(null, "���� �Ϸ�!! ");
                           } else {
                              JOptionPane.showMessageDialog(null, "���� ����!! ");
                           }
                        } else {
                           JOptionPane.showMessageDialog(null, "���� ����!! ");
                        }
                  } 
                  
                  else if (checkScreen[7]) { // ����� ���� ����
                   
                      if (!txtWorkShopName.getText().equals("") && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                            && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
                         int returnCode = dbAccess.updateShopData(Integer.toString(deleteRow + 1), txtWorkShopName.getText(), txtShopAddr.getText(),
                               txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
                         if (returnCode == 1) {
                             JOptionPane.showMessageDialog(null, "���� �Ϸ�!! ");
                          } else {
                             JOptionPane.showMessageDialog(null, "���� ����!! ");
                          }
                       } else {
                          JOptionPane.showMessageDialog(null, "���� ����!! ");
                       }
                 } 
                  
                  else { // ���� �Ұ���
                      
                  }
                  addTab();
               }
            }

         });
         
         // !_����_!
         btn_return.addActionListener(new ActionListener() { // �͸�Ŭ������ ������ �ۼ�
            public void actionPerformed(ActionEvent e) {
               JButton btn = (JButton) e.getSource();
               // String Row, String coName, String coAddr, String coDigit, String coMName,
               // String coMEmail
               if (btn.getText().equals("�ݳ�")) {
            	   
            	  if(!txtCarRegistId1.getText().equals(null)) {
            		  //int returnCode = dbAccess.insertInspection((deleteRow + 1), txtCarRegistId1.getText(),txtFront.getText(), txtRight.getText(), txtLeft.getText(), txtBack.getText());
            		  //if(returnCode == 1) JOptionPane.showMessageDialog(null, "�ݳ� ����!!");
            		  //else JOptionPane.showMessageDialog(null, "�ݳ� ����!!");
            	  }
            	  else {
            		  JOptionPane.showMessageDialog(null, "�ݳ� ID�� �Է��ϼ���!");
            	  }
                  
                  if (checkScreen[7]) { // ����� ���� ����
                   
                      if (!txtWorkShopName.getText().equals("") && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                            && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
                         int returnCode = dbAccess.updateShopData(Integer.toString(deleteRow + 1), txtWorkShopName.getText(), txtShopAddr.getText(),
                               txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
                         if (returnCode == 1) {
                             JOptionPane.showMessageDialog(null, "���� �Ϸ�!! ");
                          } else {
                             JOptionPane.showMessageDialog(null, "���� ����!! ");
                          }
                       } else {
                          JOptionPane.showMessageDialog(null, "���� ����!! ");
                       }
                 } 
                  
                  else { // ���� �Ұ���
                	  txtCarRegistId1 = new JTextField(5);
                      txtFront = new JTextField(5);
                      txtRight = new JTextField(5);
                      txtLeft = new JTextField(5);
                      txtBack = new JTextField(5);
                      
                  }
                  addTab();
               }
            }

         });
      add(control, BorderLayout.SOUTH);
      control.setLayout(new CardLayout(0, 0));

      optionPanel_Search1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel_Search1.add(labelSearch1Info);
      optionPanel_Search2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel_Search2.add(labelSearch2Info);
      optionPanel_Search3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel_Search3.add(labelSearch3Info);
      optionPanel_Search4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel_Search4.add(labelSearch4Info);
      
      optionPanel6.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel6.add(labelCarRegist1);
      optionPanel6.add(txtCarRegistId1);
      optionPanel6.add(labelFront);
      optionPanel6.add(txtFront);
      optionPanel6.add(labelRight);
      optionPanel6.add(txtRight); 
      optionPanel6.add(labelLeft);
      optionPanel6.add(txtLeft);
      optionPanel6.add(labelBack);
      optionPanel6.add(txtBack);
      optionPanel6.add(btn_return);
      
      optionPanel5.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel5.setVisible(false);

      optionPanel5.add(labelCarRegistId);
      optionPanel5.add(txtCarRegistId);
      optionPanel5.add(labelRentCoId2);
      optionPanel5.add(txtRentCoId2);
      optionPanel5.add(labelLicenseNum2);
      optionPanel5.add(txtLicenseNum2);
      optionPanel5.add(labelStart);
      optionPanel5.add(txtStart);
      optionPanel5.add(labelPeriod);
      optionPanel5.add(txtPeriod);
      optionPanel5.add(labelClaims);
      optionPanel5.add(txtClaims);
      optionPanel5.add(btn_User);

      optionPanel4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel4.setVisible(false);
      optionPanel4.add(labelWorkShopName);
      optionPanel4.add(txtWorkShopName);
      optionPanel4.add(labelShopAddr);
      optionPanel4.add(txtShopAddr);
      optionPanel4.add(labelShopDigit);
      optionPanel4.add(txtShopDigit);
      optionPanel4.add(labelShopManagerName);
      optionPanel4.add(txtShopManagerName);
      optionPanel4.add(labelShopManagerEmail);
      optionPanel4.add(txtShopManagerEmail);
      optionPanel4.add(btn_Shop);

      optionPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
      optionPanel3.setVisible(false);
      optionPanel3.add(labelLicenseNum);
      optionPanel3.add(txtLicenseNum);
      optionPanel3.add(labelCustName);
      optionPanel3.add(txtCustName);
      optionPanel3.add(labelCustAddr);
      optionPanel3.add(txtCustAddr);
      optionPanel3.add(labelCustDigit);
      optionPanel3.add(txtCustDigit);
      optionPanel3.add(labelCustEmail);
      optionPanel3.add(txtCustEmail);
      optionPanel3.add(btn_Cust);

      optionPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 10));
      optionPanel2.setVisible(false);
      optionPanel2.add(labelRentCoId);
      optionPanel2.add(txtRentCoId);
      optionPanel2.add(labelCarName);
      optionPanel2.add(txtCarName);
      optionPanel2.add(labelSeatNum);
      optionPanel2.add(txtSeatNum);
      optionPanel2.add(labelRentalPrice);
      optionPanel2.add(txtRentalPrice);
      optionPanel2.add(labelRegistDate);
      optionPanel2.add(txtRegistDate);
      optionPanel2.add(labelCarNum);
      optionPanel2.add(txtCarNum);
      optionPanel2.add(labelmaufactCo);
      optionPanel2.add(txtmaufactCo);
      optionPanel2.add(labelmanufactYear);
      optionPanel2.add(txtmanufactYear);
      optionPanel2.add(labelodo);
      optionPanel2.add(txtodo);
      optionPanel2.add(btn_Enrollment);

      optionPanel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
      optionPanel1.setVisible(true);
      optionPanel1.setSize(1000, 200);
      optionPanel1.add(labelCoName);
      optionPanel1.add(txtCoName);
      optionPanel1.add(labelCoAddr);
      optionPanel1.add(txtCoAddr);
      optionPanel1.add(labelDigit);
      optionPanel1.add(txtCoDigit);
      optionPanel1.add(labelManagerName);
      optionPanel1.add(txtManagerName);
      optionPanel1.add(labelManagerEmail);
      optionPanel1.add(txtManagerEmail);
      optionPanel1.add(btn_SignUp);

      control.add(optionPanel1, BorderLayout.SOUTH);
      control.add(optionPanel2, BorderLayout.SOUTH);
      control.add(optionPanel3, BorderLayout.SOUTH);
      control.add(optionPanel4, BorderLayout.SOUTH);
      control.add(optionPanel5, BorderLayout.SOUTH);
      control.add(optionPanel6, BorderLayout.SOUTH);
      control.add(optionPanel_Search1, BorderLayout.SOUTH);
      control.add(optionPanel_Search2, BorderLayout.SOUTH);
      control.add(optionPanel_Search3, BorderLayout.SOUTH);
      control.add(optionPanel_Search4, BorderLayout.SOUTH);
      control.add(optionPanel0, BorderLayout.SOUTH);

      /*
       * menuTab.addChangeListener(new ChangeListener() {
       * 
       * @Override public void stateChanged(ChangeEvent e) { // TODO Auto-generated
       * method stub // JOptionPane.showMessageDialog(null, "menuTab = " + //
       * menuTab.getSelectedIndex()); int index = menuTab.getSelectedIndex();
       * 
       * if (index == 0) { optionPanel1.setVisible(true);
       * optionPanel2.setVisible(false); optionPanel3.setVisible(false);
       * optionPanel4.setVisible(false); } else if (index == 1) {
       * optionPanel1.setVisible(false); optionPanel2.setVisible(true);
       * optionPanel3.setVisible(false); optionPanel4.setVisible(false); } else if
       * (index == 3) { optionPanel1.setVisible(false);
       * optionPanel2.setVisible(false); optionPanel3.setVisible(true);
       * optionPanel4.setVisible(false); } else if (index == 7) {
       * optionPanel1.setVisible(false); optionPanel2.setVisible(false);
       * optionPanel3.setVisible(false); optionPanel4.setVisible(true); } else {
       * optionPanel0.setVisible(true); optionPanel1.setVisible(false);
       * optionPanel2.setVisible(false); optionPanel3.setVisible(false);
       * optionPanel4.setVisible(false); } } });
       */
   }

   class MyItemListener implements ItemListener {

      @Override
      public void itemStateChanged(ItemEvent e) {
         // TODO Auto-generated method stub
         addTab();
      }

   }

   public static void main(String[] args) {

      // DataBaseAccess dbAccess = new DataBaseAccess(); // BD���� Ȱ���� ���� ���� �޼ҵ忡�� ������ (
      // �� �ȿ� Static������ �־ ������ Ʋ������ ���� �߻�
      // )
      CampingCarRent CCR = new CampingCarRent(); // ���� ������ DB���� ��ü�� CampingCarRent��ü�� �����ϴ� ���

      CCR.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent we) {
            try {
               // CCR.dbAccess.deleteData(); // ���α׷� ����� ȣ��Ǵ°�� false�� �����Ͽ� â�� ����� ����.
               // dbAccess.con.close(); // ���⼭ ����ϴ� DB Connection���� con�� Static�̶� ���� �ٲٱⰡ ��ٷο�.
            } catch (Exception e4) {
            }
            System.out.println("���α׷� ���� ����!");
            System.exit(0);
         }
      });
   }

   public class CompanyTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "�뿩ȸ��ID", "ȸ���", "�ּ�", "��ȭ��ȣ", "����� �̸�", "����� �̸���" };

      private DefaultTableModel tableModel;
      private JTable table;
      private JScrollPane scroll;

      CompanyTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });

         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);

         setLayout(null);
         String companyRow[] = new String[6];
         for (int i = 0; i < dbAccess.rentCoId_Company.size(); i++) {
            companyRow[0] = dbAccess.rentCoId_Company.get(i);
            companyRow[1] = dbAccess.coName_Company.get(i);
            companyRow[2] = dbAccess.coAddr_Company.get(i);
            companyRow[3] = dbAccess.coDigit_Company.get(i);
            companyRow[4] = dbAccess.coManagerName_Company.get(i);
            companyRow[5] = dbAccess.coManagerEmail_Company.get(i);
            tableModel.addRow(companyRow);
         }

         // addOptionPanel(1);
      }
   }

   public class CarBasicInfoTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "ķ��ī ��� ID", "ķ��ī �뿩ȸ�� ID", "ķ��ī �̸�", "���������ο�", "�뿩���", "�������", "�뿩���ɿ���" };

      private DefaultTableModel tableModel;
      private JTable table;

      CarBasicInfoTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);
         // carRegistId(), rentCoId(), carName(), carSeatNum(), rentPrice(),
         // carRegistDate(), carPossibleRent()
         String Row[] = new String[7];
         for (int i = 0; i < dbAccess.carRegistId_CarBasic.size(); i++) {
            Row[0] = dbAccess.carRegistId_CarBasic.get(i);
            Row[1] = dbAccess.rentCoId_CarBasic.get(i);
            Row[2] = dbAccess.carName_CarBasic.get(i);
            Row[3] = dbAccess.carSeatNum_CarBasic.get(i);
            Row[4] = dbAccess.rentPrice_CarBasic.get(i);
            Row[5] = dbAccess.carRegistDate_CarBasic.get(i);
            Row[6] = dbAccess.carPossibleRent_CarBasic.get(i);
            tableModel.addRow(Row);
         }
         // addOptionPanel(2);
      }
   }

   public class CarDetailsTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "ķ��ī ��� ID", "ķ��ī ������ȣ", "����ȸ��", "��������", "���� ����Ÿ�" };

      private DefaultTableModel tableModel;
      private JTable table;

      CarDetailsTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[5];
         for (int i = 0; i < dbAccess.carRegistId_CarDetails.size(); i++) {
            Row[0] = dbAccess.carRegistId_CarDetails.get(i);
            Row[1] = dbAccess.carNum_CarDetails.get(i);
            Row[2] = dbAccess.manufactCo_CarDetails.get(i);
            Row[3] = dbAccess.manufactYear_CarDetails.get(i);
            Row[4] = dbAccess.odo_CarDetails.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class UserCarBasicInfoTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "ķ��ī ��� ID", "ķ��ī �뿩ȸ�� ID", "ķ��ī �̸�", "���������ο�", "�뿩���", "�������" };

      private DefaultTableModel tableModel;
      private JTable table;

      UserCarBasicInfoTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);
         // carRegistId(), rentCoId(), carName(), carSeatNum(), rentPrice(),
         // carRegistDate(), carPossibleRent()
         String Row[] = new String[6];
         for (int i = 0; i < dbAccess.carRegistId_UCarBasic.size(); i++) {
            Row[0] = dbAccess.carRegistId_UCarBasic.get(i);
            Row[1] = dbAccess.rentCoId_UCarBasic.get(i);
            Row[2] = dbAccess.carName_UCarBasic.get(i);
            Row[3] = dbAccess.carSeatNum_UCarBasic.get(i);
            Row[4] = dbAccess.rentPrice_UCarBasic.get(i);
            Row[5] = dbAccess.carRegistDate_UCarBasic.get(i);
            tableModel.addRow(Row);
         }
         // addOptionPanel(2);
      }
   }

   public class UserCarDetailsTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "ķ��ī ��� ID", "ķ��ī ������ȣ", "����ȸ��", "��������", "���� ����Ÿ�" };

      private DefaultTableModel tableModel;
      private JTable table;

      UserCarDetailsTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[5];
         for (int i = 0; i < dbAccess.carRegistId_UCarDetails.size(); i++) {
            Row[0] = dbAccess.carRegistId_UCarDetails.get(i);
            Row[1] = dbAccess.carNum_UCarDetails.get(i);
            Row[2] = dbAccess.manufactCo_UCarDetails.get(i);
            Row[3] = dbAccess.manufactYear_UCarDetails.get(i);
            Row[4] = dbAccess.odo_UCarDetails.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class CustomerInfoTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "������ �����ȣ", "������", "������ �ּ�", "������ ��ȭ��ȣ", "������ Email" };

      private DefaultTableModel tableModel;
      private JTable table;

      CustomerInfoTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[5];
         for (int i = 0; i < dbAccess.custLicenseNum_CustInfo.size(); i++) {
            Row[0] = dbAccess.custLicenseNum_CustInfo.get(i);
            Row[1] = dbAccess.custName_CustInfo.get(i);
            Row[2] = dbAccess.custAddr_CustInfo.get(i);
            Row[3] = dbAccess.custDigit_CustInfo.get(i);
            Row[4] = dbAccess.custEmail_CustInfo.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class RentalBasicTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "���� �뿩 ��ȣ", "ķ��ī ��� ID", "ķ��ī �뿩ȸ��ID", "���������� ��ȣ" };

      private DefaultTableModel tableModel;
      private JTable table;

      RentalBasicTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[4];
         for (int i = 0; i < dbAccess.carRegistId_RentalBasic.size(); i++) {
            Row[0] = dbAccess.rentNum_RentalBasic.get(i);
            Row[1] = dbAccess.carRegistId_RentalBasic.get(i);
            Row[2] = dbAccess.rentCoId_RentalBasic.get(i);
            Row[3] = dbAccess.custLicenseNum_RentalBasic.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class RentalDetailsTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "���� �뿩 ��ȣ", "�뿩������", "�뿩�Ⱓ", "û�����", "���Ա���", "��Ÿ û������", "��Ÿ û�����" };

      private DefaultTableModel tableModel;
      private JTable table;

      RentalDetailsTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[7];
         for (int i = 0; i < dbAccess.rentNum_RentalDetails.size(); i++) {
            Row[0] = dbAccess.rentNum_RentalDetails.get(i);
            Row[1] = dbAccess.startDate_RentalDetails.get(i);
            Row[2] = dbAccess.rentPeriod_RentalDetails.get(i);
            Row[3] = dbAccess.charge_RentalDetails.get(i);
            Row[4] = dbAccess.payDeadline_RentalDetails.get(i);
            Row[5] = dbAccess.otherClaims_RentalDetails.get(i);
            Row[6] = dbAccess.otherCharges_RentalDetails.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class InspectionTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "���� �뿩 ��ȣ", "ķ��ī ���ID", "�պκ� �̻�����", "���� �̻�����", "���� �̻�����", "�޺κ� �̻�����", "�����ʿ� ����" };

      private DefaultTableModel tableModel;
      private JTable table;

      InspectionTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[7];
         for (int i = 0; i < dbAccess.rentNum_Inspection.size(); i++) {
            Row[0] = dbAccess.rentNum_Inspection.get(i);
            Row[1] = dbAccess.carRegistId_Inspection.get(i);
            Row[2] = dbAccess.carFrontInfo_Inspection.get(i);
            Row[3] = dbAccess.carRightInfo_Inspection.get(i);
            Row[4] = dbAccess.carLeftInfo_Inspection.get(i);
            Row[5] = dbAccess.carBackInfo_Inspection.get(i);
            Row[6] = dbAccess.repairRequired_Inspection.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class WorkShopTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "ķ��ī �����ID", "����� �̸�", "����� �ּ�", "����� ��ȭ��ȣ", "����� �̸�", "����� �̸���" };

      private DefaultTableModel tableModel;
      private JTable table;

      WorkShopTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[6];
         for (int i = 0; i < dbAccess.workShopId_ShopInfo.size(); i++) {
            Row[0] = dbAccess.workShopId_ShopInfo.get(i);
            Row[1] = dbAccess.workShopName_ShopInfo.get(i);
            Row[2] = dbAccess.workShopAddr_ShopInfo.get(i);
            Row[3] = dbAccess.workShopDigit_ShopInfo.get(i);
            Row[4] = dbAccess.shopManagerName_ShopInfo.get(i);
            Row[5] = dbAccess.shopManagerEmail_ShopInfo.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class HistoryTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "���� ���� ��ȣ", "ķ��ī ���ID", "�� ���������� ��ȣ", "ķ��ī �뿩ȸ��ID", "ķ��ī �����ID", "���� ����", "���� ��¥",
            "���� ���", "���� ����", "��Ÿ ���� ����" };

      private DefaultTableModel tableModel;
      private JTable table;

      HistoryTable(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[10];
         for (int i = 0; i < dbAccess.maintenanceNum_History.size(); i++) {
            Row[0] = dbAccess.maintenanceNum_History.get(i);
            Row[1] = dbAccess.carRegistId_History.get(i);
            Row[2] = dbAccess.custLicenseNum_History.get(i);
            Row[3] = dbAccess.rentCoId_History.get(i);
            Row[4] = dbAccess.workShopId_History.get(i);
            Row[5] = dbAccess.maintenanceHistory_History.get(i);
            Row[6] = dbAccess.repairDate_History.get(i);
            Row[7] = dbAccess.repairPrice_History.get(i);
            Row[8] = dbAccess.payDeadline_History.get(i);
            Row[9] = dbAccess.otherDetails_History.get(i);
            tableModel.addRow(Row);

         }

      }
   }

   public class Search1Table extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "���� Ƚ��", "������ ���� ��ȣ", "������ �̸�"};

      private DefaultTableModel tableModel;
      private JTable table;
   
      Search1Table(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);
      
         String Row[] = new String[3];
         for (int i = 0; i < dbAccess.fix_Search1.size(); i++) {
            Row[0] = dbAccess.fix_Search1.get(i);
            Row[1] = dbAccess.custLicenseNum_Search1.get(i);
            Row[2] = dbAccess.custName_Search1.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class Search2Table extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "����� ID", "����� �̸�", "����� �ּ�", "����� ��ȣ", "����� �̸�", "����� �̸���" };

      private DefaultTableModel tableModel;
      private JTable table;
      
      Search2Table(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);

         String Row[] = new String[6];
         for (int i = 0; i < dbAccess.workShopId_Search2.size(); i++) {
            Row[0] = dbAccess.workShopId_Search2.get(i);
            Row[1] = dbAccess.workShopName_Search2.get(i);
            Row[2] = dbAccess.workShopAddr_Search2.get(i);
            Row[3] = dbAccess.workShopDigit_Search2.get(i);
            Row[4] = dbAccess.shopManagerName_Search2.get(i);
            Row[5] = dbAccess.shopManagerEmail_Search2.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class Search3Table extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "�뿩 ȸ�� ID", "ȸ�� �̸�", "ȸ�� �ּ�", "ȸ�� ��ȭ��ȣ", "����� �̸�", "����� �̸���" };

      private DefaultTableModel tableModel;
      private JTable table;
      
      Search3Table(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);
         
         String Row[] = new String[6];
         for (int i = 0; i < dbAccess.rentCoId_Search3.size(); i++) {
            Row[0] = dbAccess.rentCoId_Search3.get(i);
            Row[1] = dbAccess.coName_Search3.get(i);
            Row[2] = dbAccess.coAddr_Search3.get(i);
            Row[3] = dbAccess.coDigit_Search3.get(i);
            Row[4] = dbAccess.coManagerName_Search3.get(i);
            Row[5] = dbAccess.coManagerEmail_Search3.get(i);
            tableModel.addRow(Row);
         }

      }
   }

   public class Search4Table extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "�ڵ��� �̸�", "�ڵ��� �����", "�뿩���� ����"};
      private DefaultTableModel tableModel;
      private JTable table;
      
      Search4Table(DataBaseAccess dbAccess) {
         this.dbAccess = dbAccess;
         makeTable();
      }

      public void makeTable() {
         tableModel = new DefaultTableModel(coInfo, 0);
         table = new JTable(tableModel);
         table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               JTable table = (JTable) e.getSource();
               deleteRow = table.getSelectedRow();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

         });
         scroll = new JScrollPane(table);
         scroll.setSize(1000, 500);
         add(scroll);
         setLayout(null);
         
         String Row[] = new String[3];
         for (int i = 0; i < dbAccess.carName_Search4.size(); i++) {
            Row[0] = dbAccess.carName_Search4.get(i);
            Row[1] = dbAccess.carRegistDate_Search4.get(i);
            Row[2] = dbAccess.carPossibleRent_Search4.get(i);
            tableModel.addRow(Row);
         }

      }
   }

}