import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class CampingCarRent extends JFrame {

   // private static final LayoutManager BorderLayout = null;
   // DB관련 요소
   private DataBaseAccess dbAccess; // DataBase 접근하는 객체 생성

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
   // UI구성요소 선언
   // private ButtonGroup modeSelect; // RadioButton모음z
   private JPanel statePanel; // 상태확인
   private JRadioButton radioBtn_Sudo; // 관리자용 RadioButton
   private JRadioButton radioBtn_User; // 일반사용자용 RadioButton
   private ButtonGroup modeGroup; // 버튼 그룹화 용
   private JToolBar modeSelect; // radioButton 표시용 툴박스

   private JTabbedPane menuTab; // 메뉴 텝
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
      super("CAMPING CAR RENT (16010945 / 16011059)"); // 16010945 노수민, 16011059 안형진
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
      radioBtn_Sudo = new JRadioButton("관리자 모드");
      radioBtn_Sudo.setSelected(true);
      radioBtn_Sudo.addItemListener(new MyItemListener());
      radioBtn_User = new JRadioButton("사용자 모드");
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

      labelCoName = new JLabel("회사 명");
      labelCoAddr = new JLabel("회사 주소");
      labelDigit = new JLabel("회사 전화번호");
      labelManagerName = new JLabel("담당자 이름");
      labelManagerEmail = new JLabel("담당자 이메일");

      btn_SignUp = new JButton("등록");
      btn_Enrollment = new JButton("등록");
      btn_Cust = new JButton("등록");
      btn_Shop = new JButton("등록");
      btn_User = new JButton("대여");
      btn_Delete = new JButton("삭제");
      btn_Update = new JButton("변경");
      
      optionPanel_Search1 = new JPanel();
      labelSearch1Info = new JLabel("#캠핑카 대여 시 정비 기록이 있는 고객 정보 검색(내림차순)");
      optionPanel_Search2 = new JPanel();
      labelSearch2Info = new JLabel("#차량 정비가 이루어진 정비소들 정보");
      optionPanel_Search3 = new JPanel();
      labelSearch3Info = new JLabel("#대여 가능한 캠핑카의 대여 회사 정보 조회");
      optionPanel_Search4 = new JPanel();
      labelSearch4Info = new JLabel("#차량 정비가 이루어진 캠핑카 이름, 캠핑카 등록일, 대여가능");
      
      optionPanel6 = new JPanel();
      txtCarRegistId1 = new JTextField(5);
      txtFront = new JTextField(5);
      txtRight = new JTextField(5);
      txtLeft = new JTextField(5);
      txtBack = new JTextField(5);
      
      labelCarRegist1 = new JLabel("고유대여번호ID");
      labelFront = new JLabel("앞");
      labelRight = new JLabel("오른쪽");
      labelLeft = new JLabel("왼쪽");
      labelBack = new JLabel("뒤");
      btn_return = new JButton("반납");

      optionPanel5 = new JPanel();
      txtCarRegistId = new JTextField(5);
      txtRentCoId2 = new JTextField(5);
      txtLicenseNum2 = new JTextField(8);
      txtStart = new JTextField(7);
      txtPeriod = new JTextField(7);
      txtClaims = new JTextField(5);
      labelCarRegistId = new JLabel("캠핑카 등록ID");
      labelRentCoId2 = new JLabel("캠핑카 대여회사ID");
      labelLicenseNum2 = new JLabel("운전면허 번호");
      labelStart = new JLabel("대여 시작일");
      labelPeriod = new JLabel("대여 기간");
      labelClaims = new JLabel("기타 청구내역");

      optionPanel4 = new JPanel();
      txtWorkShopName = new JTextField(7);
      txtShopAddr = new JTextField(15);
      txtShopDigit = new JTextField(7);
      txtShopManagerName = new JTextField(5);
      txtShopManagerEmail = new JTextField(10);
      labelWorkShopName = new JLabel("정비소 이름");
      labelShopAddr = new JLabel("정비소 주소");
      labelShopDigit = new JLabel("정비소 전화번호");
      labelShopManagerName = new JLabel("담당자 이름");
      labelShopManagerEmail = new JLabel("담당자 이메일");

      optionPanel3 = new JPanel();
      txtLicenseNum = new JTextField(10);
      txtCustName = new JTextField(5);
      txtCustAddr = new JTextField(10);
      txtCustDigit = new JTextField(7);
      txtCustEmail = new JTextField(10);
      labelLicenseNum = new JLabel("운전면허 번호");
      labelCustName = new JLabel("이 름");
      labelCustAddr = new JLabel("주 소");
      labelCustDigit = new JLabel("전화번호");
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

      labelRentCoId = new JLabel("업체 ID");
      labelCarName = new JLabel("차 이름");
      labelSeatNum = new JLabel("승차 인원 수");
      labelRentalPrice = new JLabel("가격");
      labelRegistDate = new JLabel("등록일자");
      labelCarNum = new JLabel("차량 번호");
      labelmaufactCo = new JLabel("제조사");
      labelmanufactYear = new JLabel("제조년도");
      labelodo = new JLabel("주행거리");

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
         menuTab.addTab("캠핑카 회사 정보", new CompanyTable(dbAccess));
         menuTab.addTab("캠핑카 기본 정보", new CarBasicInfoTable(dbAccess)); //
         menuTab.addTab("캠핑카 상세 정보", new CarDetailsTable(dbAccess)); //
         menuTab.addTab("고객 정보", new CustomerInfoTable(dbAccess)); //
         menuTab.addTab("캠핑카 대여 기본 정보", new RentalBasicTable(dbAccess));
         menuTab.addTab("캠핑카 대여 상세 정보", new RentalDetailsTable(dbAccess));
         menuTab.addTab("캠핑카 정검 내역", new InspectionTable(dbAccess));
         menuTab.addTab("정비소 정보", new WorkShopTable(dbAccess));
         menuTab.addTab("캠핑카 정비 내역", new HistoryTable(dbAccess));
         menuTab.addTab("검색1", new Search1Table(dbAccess));
         menuTab.addTab("검색2", new Search2Table(dbAccess));
         menuTab.addTab("검색3", new Search3Table(dbAccess));
         menuTab.addTab("검색4", new Search4Table(dbAccess));
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
         menuTab.addTab("캠핑카 기본 정보", new UserCarBasicInfoTable(dbAccess)); //
         menuTab.addTab("캠핑카 상세 정보", new UserCarDetailsTable(dbAccess)); //
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

      modeGroup.add(radioBtn_Sudo); // 관리자 그룹화
      modeGroup.add(radioBtn_User); // 일반 사용자 그룹화

      statePanel.add(radioBtn_Sudo);
      statePanel.add(radioBtn_User);
      statePanel.add(btn_Delete);
      statePanel.add(btn_Update);
      
      modeSelect.add(statePanel);

      add(modeSelect, BorderLayout.NORTH);

      addTab();

      // 대여 회사 등록
      btn_SignUp.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("등록") && !txtCoName.getText().equals("") && !txtCoAddr.getText().equals("")
                  && !txtCoDigit.getText().equals("") && !txtManagerName.getText().equals("")
                  && !txtManagerEmail.getText().equals("")) {

               int returnCode = dbAccess.carCompanySignUp(txtCoName.getText(), txtCoAddr.getText(),
                     txtCoDigit.getText(), txtManagerName.getText(), txtManagerEmail.getText());

               if (returnCode == dbAccess.rentCoId_Company.size()) {
                  JOptionPane.showMessageDialog(null, "등록 완료!!\n 등록번호 :" + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "등록 실패!!");
               }
            } else {
               JOptionPane.showMessageDialog(null, "데이터를 입력해 주세요");
            }
         }
      });

      // 캠핑카 등록
      btn_Enrollment.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            if (btn.getText().equals("등록") && !txtRentCoId.getText().equals("") && !txtCarName.getText().equals("")
                  && !txtSeatNum.getText().equals("") && !txtRentalPrice.getText().equals("")
                  && !txtRegistDate.getText().equals("") && !txtCarNum.getText().equals("")
                  && !txtmaufactCo.getText().equals("") && !txtmanufactYear.getText().equals("")
                  && !txtodo.getText().equals("")) {
               int returnCode = dbAccess.carEnrollment(txtRentCoId.getText(), txtCarName.getText(),
                     txtSeatNum.getText(), txtRentalPrice.getText(), txtRegistDate.getText(),
                     txtCarNum.getText(), txtmaufactCo.getText(), txtmanufactYear.getText(), txtodo.getText());

               if (returnCode == dbAccess.carRegistId_CarBasic.size()) {
                  JOptionPane.showMessageDialog(null, "등록완료!!\n 등록번호 : " + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "등록 실패!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "데이터를 입력해 주세요");
            }

         }
      });

      // 고객 등록
      btn_Cust.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("등록") && !txtLicenseNum.getText().equals("")
                  && !txtCustName.getText().equals("") && !txtCustAddr.getText().equals("")
                  && !txtCustDigit.getText().equals("") && !txtCustEmail.getText().equals("")) {
               int returnCode = dbAccess.custInfo(txtLicenseNum.getText(), txtCustName.getText(),
                     txtCustAddr.getText(), txtCustDigit.getText(), txtCustEmail.getText());
               if (returnCode == dbAccess.custLicenseNum_CustInfo.size()) {
                  JOptionPane.showMessageDialog(null, "등록완료!!\n");
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "등록 실패!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "데이터를 입력해 주세요");
            }
         }

      });
      // 정비소 등록
      btn_Shop.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();

            if (btn.getText().equals("등록") && !txtWorkShopName.getText().equals("")
                  && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                  && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
               int returnCode = dbAccess.shopInfo(txtWorkShopName.getText(), txtShopAddr.getText(),
                     txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
               if (returnCode == dbAccess.workShopId_ShopInfo.size()) {
                  JOptionPane.showMessageDialog(null, "등록완료!!\n 등록번호 : " + returnCode);
                  // companyTable.makeTable();
                  // menuTab.removeAll();

                  addTab();
               } else {
                  JOptionPane.showMessageDialog(null, "등록 실패!! ");
               }
            } else {
               JOptionPane.showMessageDialog(null, "데이터를 입력해 주세요");
            }
         }

      });

      // !_ 삭제 _!
      btn_Delete.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
         public void actionPerformed(ActionEvent e) {
            JButton btn = (JButton) e.getSource();
            int returnCode;
            if (btn.getText().equals("삭제")) {

               if (checkScreen[0]) { // 대여회사 정보 삭제
                  returnCode = dbAccess.deleteRowData("CampingCarCompany", "rentCoId",
                        Integer.toString(deleteRow + 1));

                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "삭제완료!! ");
                  else
                     JOptionPane.showMessageDialog(null, "삭제실패!! ");
               }

               else if (checkScreen[1]) { // 캠핑카 삭제 (캠핑카 기본정보에서 삭제 누르면)
                  // Details에서 삭제 먼저 한 후, BasicInfo도 삭제
                  returnCode = dbAccess.deleteRowData("CampingCarDetails", "carRegistId",
                        Integer.toString(deleteRow + 1));
                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "상세정보 삭제완료!! ");
                  else
                     JOptionPane.showMessageDialog(null, "상세정보 삭제실패!! ");
                  returnCode = dbAccess.deleteRowData("CampingCarBasicInfo", "carRegistId",
                        Integer.toString(deleteRow + 1));
                  if (returnCode == 1)
                     JOptionPane.showMessageDialog(null, "기본정보 삭제완료!! ");
                  else
                     JOptionPane.showMessageDialog(null, "기본정보 삭제실패!! ");
               } else if (checkScreen[3]) { // 고객 삭제 - 고객운전면허증번호
                  returnCode = dbAccess.deleteRowData("CustomerInfo", "custLicenseNum", Integer.toString(deleteRow + 1));
                  if(returnCode == 1) JOptionPane.showMessageDialog(null, "고객정보 삭제완료!! ");
                  else JOptionPane.showMessageDialog(null, "고객정보 삭제실패!! ");
               } else if (checkScreen[7]) { // 정비소 삭제
                  returnCode = dbAccess.deleteRowData("WorkShopInfo", "workShopId", Integer.toString(deleteRow) + 1);
                  if(returnCode == 1) JOptionPane.showMessageDialog(null, "정비소 정보 삭제완료!! ");
                  else JOptionPane.showMessageDialog(null, "정비소 정보 삭제실패!! ");
               } else { // 삭제 불가능

               }

               addTab();
            }
         }

      });

         // !_변경_!
         btn_Update.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
            public void actionPerformed(ActionEvent e) {
               JButton btn = (JButton) e.getSource();
               // String Row, String coName, String coAddr, String coDigit, String coMName,
               // String coMEmail
               if (btn.getText().equals("변경")) {

                  if (checkScreen[0]) { // 대여회사 정보 변경
                     if (!txtCoName.getText().equals("") && !txtCoAddr.getText().equals("")
                           && !txtCoDigit.getText().equals("") && !txtManagerName.getText().equals("")
                           && !txtManagerEmail.getText().equals("")) {

                        int returnCode = dbAccess.updateCompanyData(Integer.toString(deleteRow + 1),
                              txtCoName.getText(), txtCoAddr.getText(), txtCoDigit.getText(),
                              txtManagerName.getText(), txtManagerEmail.getText());
                        if (returnCode == 1) {
                           JOptionPane.showMessageDialog(null, "변경 완료!! ");
                        } else {
                           JOptionPane.showMessageDialog(null, "변경 실패!! ");
                        }
                     } else {
                        JOptionPane.showMessageDialog(null, "변경 실패!! ");
                     }
                  }

                  else if (checkScreen[1]) { // 캠핑카 변경
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
                           JOptionPane.showMessageDialog(null, "변경 완료!! ");
                        } else {
                           JOptionPane.showMessageDialog(null, "변경 실패!! ");
                        }
                     } else {
                        JOptionPane.showMessageDialog(null, "변경 실패!! ");
                     }
                  }

                  else if (checkScreen[3]) { // 고객 정보 변경
                     if (!txtCustName.getText().equals("") && !txtCustAddr.getText().equals("")
                             && !txtCustDigit.getText().equals("") && !txtCustEmail.getText().equals("")) {
                          int returnCode = dbAccess.updateCustData(Integer.toString(deleteRow + 1), txtCustName.getText(),
                                txtCustAddr.getText(), txtCustDigit.getText(), txtCustEmail.getText());
                          if (returnCode == 1) {
                              JOptionPane.showMessageDialog(null, "변경 완료!! ");
                           } else {
                              JOptionPane.showMessageDialog(null, "변경 실패!! ");
                           }
                        } else {
                           JOptionPane.showMessageDialog(null, "변경 실패!! ");
                        }
                  } 
                  
                  else if (checkScreen[7]) { // 정비소 정보 변경
                   
                      if (!txtWorkShopName.getText().equals("") && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                            && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
                         int returnCode = dbAccess.updateShopData(Integer.toString(deleteRow + 1), txtWorkShopName.getText(), txtShopAddr.getText(),
                               txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
                         if (returnCode == 1) {
                             JOptionPane.showMessageDialog(null, "변경 완료!! ");
                          } else {
                             JOptionPane.showMessageDialog(null, "변경 실패!! ");
                          }
                       } else {
                          JOptionPane.showMessageDialog(null, "변경 실패!! ");
                       }
                 } 
                  
                  else { // 변경 불가능
                      
                  }
                  addTab();
               }
            }

         });
         
         // !_변경_!
         btn_return.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
            public void actionPerformed(ActionEvent e) {
               JButton btn = (JButton) e.getSource();
               // String Row, String coName, String coAddr, String coDigit, String coMName,
               // String coMEmail
               if (btn.getText().equals("반납")) {
            	   
            	  if(!txtCarRegistId1.getText().equals(null)) {
            		  //int returnCode = dbAccess.insertInspection((deleteRow + 1), txtCarRegistId1.getText(),txtFront.getText(), txtRight.getText(), txtLeft.getText(), txtBack.getText());
            		  //if(returnCode == 1) JOptionPane.showMessageDialog(null, "반납 성공!!");
            		  //else JOptionPane.showMessageDialog(null, "반납 실패!!");
            	  }
            	  else {
            		  JOptionPane.showMessageDialog(null, "반납 ID를 입력하세요!");
            	  }
                  
                  if (checkScreen[7]) { // 정비소 정보 변경
                   
                      if (!txtWorkShopName.getText().equals("") && !txtShopAddr.getText().equals("") && !txtShopDigit.getText().equals("")
                            && txtShopManagerName.getText().equals("") && !txtShopManagerEmail.getText().equals("")) {
                         int returnCode = dbAccess.updateShopData(Integer.toString(deleteRow + 1), txtWorkShopName.getText(), txtShopAddr.getText(),
                               txtShopDigit.getText(), txtShopManagerName.getText(), txtShopManagerEmail.getText());
                         if (returnCode == 1) {
                             JOptionPane.showMessageDialog(null, "변경 완료!! ");
                          } else {
                             JOptionPane.showMessageDialog(null, "변경 실패!! ");
                          }
                       } else {
                          JOptionPane.showMessageDialog(null, "변경 실패!! ");
                       }
                 } 
                  
                  else { // 변경 불가능
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

      // DataBaseAccess dbAccess = new DataBaseAccess(); // BD접근 활용을 위해 메인 메소드에서 선언함 (
      // 저 안에 Static변수가 있어서 순서가 틀어지면 오류 발생
      // )
      CampingCarRent CCR = new CampingCarRent(); // 먼저 생성한 DB접근 객체를 CampingCarRent객체와 공유하는 방식

      CCR.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent we) {
            try {
               // CCR.dbAccess.deleteData(); // 프로그램 종료시 호출되는경우 false를 전달하여 창을 띄우지 않음.
               // dbAccess.con.close(); // 여기서 사용하는 DB Connection변수 con이 Static이라 순서 바꾸기가 까다로움.
            } catch (Exception e4) {
            }
            System.out.println("프로그램 완전 종료!");
            System.exit(0);
         }
      });
   }

   public class CompanyTable extends JPanel {

      private DataBaseAccess dbAccess;
      private String coInfo[] = { "대여회사ID", "회사명", "주소", "전화번호", "담당자 이름", "담당자 이메일" };

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
      private String coInfo[] = { "캠핑카 등록 ID", "캠핑카 대여회사 ID", "캠핑카 이름", "승차가능인원", "대여비용", "등록일자", "대여가능여부" };

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
      private String coInfo[] = { "캠핑카 등록 ID", "캠핑카 차량번호", "제조회사", "제조연도", "누적 주행거리" };

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
      private String coInfo[] = { "캠핑카 등록 ID", "캠핑카 대여회사 ID", "캠핑카 이름", "승차가능인원", "대여비용", "등록일자" };

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
      private String coInfo[] = { "캠핑카 등록 ID", "캠핑카 차량번호", "제조회사", "제조연도", "누적 주행거리" };

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
      private String coInfo[] = { "운전자 면허번호", "운전자", "운전자 주소", "운전자 전화번호", "운전자 Email" };

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
      private String coInfo[] = { "고유 대여 번호", "캠핑카 등록 ID", "캠핑카 대여회사ID", "운전면허증 번호" };

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
      private String coInfo[] = { "고유 대여 번호", "대여시작일", "대여기간", "청구요금", "납입기한", "기타 청구내역", "기타 청구요금" };

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
      private String coInfo[] = { "고유 대여 번호", "캠핑카 등록ID", "앞부분 이상유무", "우측 이상유무", "좌측 이상유무", "뒷부분 이상유무", "수리필요 여부" };

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
      private String coInfo[] = { "캠핑카 정비소ID", "정비소 이름", "정비소 주소", "정비소 전화번호", "담당자 이름", "담당자 이메일" };

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
      private String coInfo[] = { "고유 정비 번호", "캠핑카 등록ID", "고객 운전면허증 번호", "캠핑카 대여회사ID", "캠핑카 정비소ID", "정비 내역", "수리 날짜",
            "수리 비용", "납입 기한", "기타 정비 내역" };

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
      private String coInfo[] = { "정비 횟수", "운전자 면허 번호", "운전자 이름"};

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
      private String coInfo[] = { "정비소 ID", "정비소 이름", "정비소 주소", "정비소 번호", "담당자 이름", "담당자 이메일" };

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
      private String coInfo[] = { "대여 회사 ID", "회사 이름", "회사 주소", "회사 전화번호", "담당자 이름", "담당자 이메일" };

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
      private String coInfo[] = { "자동차 이름", "자동차 등록일", "대여가능 여부"};
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