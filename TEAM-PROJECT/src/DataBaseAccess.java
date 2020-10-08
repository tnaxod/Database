import java.sql.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DataBaseAccess {

   private static Connection con; // MySQL과 연결해주는 Connection
   private Statement stmt; // SQL문을 전달하기 위한 statement 객체
   private ResultSet resultSet; // DB명령 수행후 반환값을 가지고 오는 객체

   private String Driver; // MySQL 연결 드라이버
   private String url; // Local DB접속하는 URL
   private String userid; // Login용 User Id
   private String pwd; // Login용 User PassWord

   // CampingCarCompany Table Data
   public List<String> rentCoId_Company;
   public List<String> coName_Company;
   public List<String> coAddr_Company;
   public List<String> coDigit_Company;
   public List<String> coManagerName_Company;
   public List<String> coManagerEmail_Company;

   // CampingCarBasicInfo Table Data
   public List<String> carRegistId_CarBasic;
   public List<String> rentCoId_CarBasic;
   public List<String> carName_CarBasic;
   public List<String> carSeatNum_CarBasic;
   public List<String> rentPrice_CarBasic;
   public List<String> carRegistDate_CarBasic;
   public List<String> carPossibleRent_CarBasic;

   // CampingCarDetails Table Data
   public List<String> carRegistId_CarDetails;
   public List<String> carNum_CarDetails;
   public List<String> manufactCo_CarDetails;
   public List<String> manufactYear_CarDetails;
   public List<String> odo_CarDetails;

   // UserCampingCarBasicInfo Table Data
   public List<String> carRegistId_UCarBasic;
   public List<String> rentCoId_UCarBasic;
   public List<String> carName_UCarBasic;
   public List<String> carSeatNum_UCarBasic;
   public List<String> rentPrice_UCarBasic;
   public List<String> carRegistDate_UCarBasic;

   // UserCampingCarDetails Table Data
   public List<String> carRegistId_UCarDetails;
   public List<String> carNum_UCarDetails;
   public List<String> manufactCo_UCarDetails;
   public List<String> manufactYear_UCarDetails;
   public List<String> odo_UCarDetails;

   // CustomerInfo Table Data
   public List<String> custLicenseNum_CustInfo;
   public List<String> custName_CustInfo;
   public List<String> custAddr_CustInfo;
   public List<String> custDigit_CustInfo;
   public List<String> custEmail_CustInfo;

   // CarRentalBasicInfo Table Data
   public List<String> rentNum_RentalBasic;
   public List<String> carRegistId_RentalBasic;
   public List<String> custLicenseNum_RentalBasic;
   public List<String> rentCoId_RentalBasic;

   // Data insert CarRentalDetails table
   public List<String> rentNum_RentalDetails;
   public List<String> startDate_RentalDetails;
   public List<String> rentPeriod_RentalDetails;
   public List<String> charge_RentalDetails;
   public List<String> payDeadline_RentalDetails;
   public List<String> otherClaims_RentalDetails;
   public List<String> otherCharges_RentalDetails;

   // CarInspectionList Table Data
   public List<String> rentNum_Inspection;
   public List<String> carRegistId_Inspection;
   public List<String> carFrontInfo_Inspection;
   public List<String> carRightInfo_Inspection;
   public List<String> carLeftInfo_Inspection;
   public List<String> carBackInfo_Inspection;
   public List<String> repairRequired_Inspection;

   // WorkShopInfo Table Data
   public List<String> workShopId_ShopInfo;
   public List<String> workShopName_ShopInfo;
   public List<String> workShopAddr_ShopInfo;
   public List<String> workShopDigit_ShopInfo;
   public List<String> shopManagerName_ShopInfo;
   public List<String> shopManagerEmail_ShopInfo;

   // CarMaintenanceHistory Table Data
   public List<String> maintenanceNum_History;
   public List<String> carRegistId_History;
   public List<String> rentCoId_History;
   public List<String> custLicenseNum_History;
   public List<String> workShopId_History;
   public List<String> maintenanceHistory_History;
   public List<String> repairDate_History;
   public List<String> repairPrice_History;
   public List<String> payDeadline_History;
   public List<String> otherDetails_History;
   
   // Search1 Table Data
   public List<String> fix_Search1;
   public List<String> custLicenseNum_Search1;
   public List<String> custName_Search1;
   
   // Search2 Table Data
   public List<String> workShopId_Search2;
   public List<String> workShopName_Search2;
   public List<String> workShopAddr_Search2;
   public List<String> workShopDigit_Search2;
   public List<String> shopManagerName_Search2;
   public List<String> shopManagerEmail_Search2;
   
   // Search3 Table Data
   public List<String> rentCoId_Search3;
   public List<String> coName_Search3;
   public List<String> coAddr_Search3;
   public List<String> coDigit_Search3;
   public List<String> coManagerName_Search3;
   public List<String> coManagerEmail_Search3;
   
   // Search4 Table Data
   public List<String> carName_Search4;
   public List<String> carRegistDate_Search4;
   public List<String> carPossibleRent_Search4;

   public DataBaseAccess() { // Class Constructor
      this.Driver = "";
      this.url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
      this.userid = "madang";
      this.pwd = "madang";

      // = new String[1][6];
      // company = new LinkedList<List>();
      coName_Company = new LinkedList<String>();
      rentCoId_Company = new LinkedList<String>();
      coAddr_Company = new LinkedList<String>();
      coDigit_Company = new LinkedList<String>();
      coManagerName_Company = new LinkedList<String>();
      coManagerEmail_Company = new LinkedList<String>();

      carRegistId_CarBasic = new LinkedList<String>();
      rentCoId_CarBasic = new LinkedList<String>();
      carName_CarBasic = new LinkedList<String>();
      carSeatNum_CarBasic = new LinkedList<String>();
      rentPrice_CarBasic = new LinkedList<String>();
      carRegistDate_CarBasic = new LinkedList<String>();
      carPossibleRent_CarBasic = new LinkedList<String>();

      carRegistId_CarDetails = new LinkedList<String>();
      carNum_CarDetails = new LinkedList<String>();
      manufactCo_CarDetails = new LinkedList<String>();
      manufactYear_CarDetails = new LinkedList<String>();
      odo_CarDetails = new LinkedList<String>();

      carRegistId_UCarBasic = new LinkedList<String>();
      rentCoId_UCarBasic = new LinkedList<String>();
      carName_UCarBasic = new LinkedList<String>();
      carSeatNum_UCarBasic = new LinkedList<String>();
      rentPrice_UCarBasic = new LinkedList<String>();
      carRegistDate_UCarBasic = new LinkedList<String>();

      carRegistId_UCarDetails = new LinkedList<String>();
      carNum_UCarDetails = new LinkedList<String>();
      manufactCo_UCarDetails = new LinkedList<String>();
      manufactYear_UCarDetails = new LinkedList<String>();
      odo_UCarDetails = new LinkedList<String>();

      custLicenseNum_CustInfo = new LinkedList<String>();
      custName_CustInfo = new LinkedList<String>();
      custAddr_CustInfo = new LinkedList<String>();
      custDigit_CustInfo = new LinkedList<String>();
      custEmail_CustInfo = new LinkedList<String>();

      rentNum_RentalBasic = new LinkedList<String>();
      carRegistId_RentalBasic = new LinkedList<String>();
      custLicenseNum_RentalBasic = new LinkedList<String>();
      rentCoId_RentalBasic = new LinkedList<String>();

      rentNum_RentalDetails = new LinkedList<String>();
      startDate_RentalDetails = new LinkedList<String>();
      rentPeriod_RentalDetails = new LinkedList<String>();
      charge_RentalDetails = new LinkedList<String>();
      payDeadline_RentalDetails = new LinkedList<String>();
      otherClaims_RentalDetails = new LinkedList<String>();
      otherCharges_RentalDetails = new LinkedList<String>();

      rentNum_Inspection = new LinkedList<String>();
      carRegistId_Inspection = new LinkedList<String>();
      carFrontInfo_Inspection = new LinkedList<String>();
      carRightInfo_Inspection = new LinkedList<String>();
      carLeftInfo_Inspection = new LinkedList<String>();
      carBackInfo_Inspection = new LinkedList<String>();
      repairRequired_Inspection = new LinkedList<String>();

      workShopId_ShopInfo = new LinkedList<String>();
      workShopName_ShopInfo = new LinkedList<String>();
      workShopAddr_ShopInfo = new LinkedList<String>();
      workShopDigit_ShopInfo = new LinkedList<String>();
      shopManagerName_ShopInfo = new LinkedList<String>();
      shopManagerEmail_ShopInfo = new LinkedList<String>();

      maintenanceNum_History = new LinkedList<String>();
      carRegistId_History = new LinkedList<String>();
      rentCoId_History = new LinkedList<String>();
      custLicenseNum_History = new LinkedList<String>();
      workShopId_History = new LinkedList<String>();
      maintenanceHistory_History = new LinkedList<String>();
      repairDate_History = new LinkedList<String>();
      repairPrice_History = new LinkedList<String>();
      payDeadline_History = new LinkedList<String>();
      otherDetails_History = new LinkedList<String>();

      fix_Search1 = new LinkedList<String>();
      custLicenseNum_Search1 = new LinkedList<String>();;
      custName_Search1 = new LinkedList<String>();
      
      workShopId_Search2 = new LinkedList<String>();
      workShopName_Search2 = new LinkedList<String>();
      workShopAddr_Search2 = new LinkedList<String>();
      workShopDigit_Search2 = new LinkedList<String>();
      shopManagerName_Search2 = new LinkedList<String>();
      shopManagerEmail_Search2 = new LinkedList<String>();
      
      rentCoId_Search3 = new LinkedList<String>();
      coName_Search3 = new LinkedList<String>();
      coAddr_Search3 = new LinkedList<String>();
      coDigit_Search3 = new LinkedList<String>();
      coManagerName_Search3 = new LinkedList<String>();
      coManagerEmail_Search3 = new LinkedList<String>();
      
      carName_Search4 = new LinkedList<String>();
      carRegistDate_Search4 = new LinkedList<String>();
      carPossibleRent_Search4 = new LinkedList<String>();
      
      conDB(); // DataBase 연결
      setDB();
   }

   private void setDB() {
      bringCampingCarCompany();
      bringCampingCarBasicInfo();
      bringCampingCarDetails();
      bringUserCampingCarBasicInfo();
      bringUserCampingCarDetails();
      bringCustomerInfo();
      bringRentalBasic();
      bringRentalDetails();
      bringCarInspectionList();
      bringWorkShopInfo();
      bringHistory();
      
      bringSearch1();
      bringSearch2(); 
      bringSearch3();
      bringSearch4();
   }

   public void deleteData() {
      try {
         stmt = con.createStatement();
         deleteDataTable(); // 데이터 테이블 삭제
         createDataTable(); // 데이터 테이블 생성
         insertData(); // 데이터 삽입
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("테이블 초기화 실패!");
      }
   }
   
   private void bringSearch1() {
	   String sql = "SELECT count(CarMaintenanceHistory.maintenanceNum) AS Fix, CarMaintenanceHistory.custLicenseNum, CustomerInfo.custName FROM CarMaintenanceHistory, CustomerInfo WHERE CustomerInfo.custLicenseNum = CarMaintenanceHistory.custLicenseNUm GROUP BY custLicenseNum ORDER BY FIX DESC;";
	  
	   try {
	         stmt = con.createStatement();
	         resultSet = stmt.executeQuery(sql);

	         fix_Search1.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
	         custLicenseNum_Search1.clear();
	         custName_Search1.clear();

	         while (resultSet.next()) {
	        	fix_Search1.add(resultSet.getString("fix"));
	        	custLicenseNum_Search1.add(resultSet.getString("custLicenseNum"));
	        	custName_Search1.add(resultSet.getString("custName"));
	         }

	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("Search1 Data Loading fail! " + e);
	      }
   }
	   
	   private void bringSearch2() {
		   String sql = "SELECT * FROM WorkShopInfo WHERE EXISTS (SELECT * FROM CarMaintenanceHistory WHERE CarMaintenanceHistory.workShopId = WorkShopInfo.workShopId);";
		  
		   try {
		         stmt = con.createStatement();
		         resultSet = stmt.executeQuery(sql);

		         workShopId_Search2.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
		         workShopName_Search2.clear();
		         workShopAddr_Search2.clear();
		         workShopDigit_Search2.clear();
		         shopManagerName_Search2.clear();
		         shopManagerEmail_Search2.clear();

		         while (resultSet.next()) {
		        	workShopId_Search2.add(resultSet.getString("workShopId"));
		        	workShopName_Search2.add(resultSet.getString("workShopName"));
		        	workShopAddr_Search2.add(resultSet.getString("workShopAddr"));
		        	workShopDigit_Search2.add(resultSet.getString("workShopDigit"));
		        	shopManagerName_Search2.add(resultSet.getString("shopManagerName"));
		        	shopManagerEmail_Search2.add(resultSet.getString("shopManagerEmail"));
		         }

		      } catch (SQLException e) {
		         e.printStackTrace();
		         System.out.println("Search2 Data Loading fail! " + e);
		      }
   }
	   
	   private void bringSearch3() {
		   String sql = "SELECT * FROM CampingCarCompany WHERE EXISTS (SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y' AND CampingCarBasicInfo.rentCoId = CampingCarCompany.rentCoId );";
		  
		   try {
		         stmt = con.createStatement();
		         resultSet = stmt.executeQuery(sql);
		         rentCoId_Search3.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
		         coName_Search3.clear();
		         coAddr_Search3.clear();
		         coDigit_Search3.clear();
		         coManagerName_Search3.clear();
		         coManagerEmail_Search3.clear();
		         

		         while (resultSet.next()) {
		        	 rentCoId_Search3.add(resultSet.getString("rentCoId"));
		        	 coName_Search3.add(resultSet.getString("coName"));
		        	 coAddr_Search3.add(resultSet.getString("coAddr"));
		        	 coDigit_Search3.add(resultSet.getString("coDigit"));
		        	 coManagerName_Search3.add(resultSet.getString("coManagerName"));
		        	 coManagerEmail_Search3.add(resultSet.getString("coManagerEmail"));
		        	
		         }

		      } catch (SQLException e) {
		         e.printStackTrace();
		         System.out.println("Search2 Data Loading fail! " + e);
		      }
   }
	   
	   private void bringSearch4() {
		   String sql = "SELECT DISTINCT (CampingCarBasicInfo.carName), CampingCarBasicInfo.carRegistDate, CampingCarBasicInfo.carPossibleRent FROM CampingCarBasicInfo, CarMaintenanceHistory WHERE CarMaintenanceHistory.carRegistId = CampingCarBasicInfo.carRegistId AND CampingCarBasicInfo.rentCoId = CarMaintenanceHistory.rentCoId;";
		   try {
		         stmt = con.createStatement();
		         resultSet = stmt.executeQuery(sql);
		      
		         carName_Search4.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
		         carRegistDate_Search4.clear();
		         carPossibleRent_Search4.clear();

		         while (resultSet.next()) {
		        	 carName_Search4.add(resultSet.getString("carName"));
		        	 carRegistDate_Search4.add(resultSet.getString("carRegistDate"));
		        	 carPossibleRent_Search4.add(resultSet.getString("carPossibleRent"));
		        	
		         }

		      } catch (SQLException e) {
		         e.printStackTrace();
		         System.out.println("Search2 Data Loading fail! " + e);
		      }
   }

   private void bringCampingCarCompany() {

      String sql = "SELECT * FROM CampingCarCompany;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         coManagerEmail_Company.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         coManagerName_Company.clear();
         coDigit_Company.clear();
         coAddr_Company.clear();
         rentCoId_Company.clear();
         coName_Company.clear();

         while (resultSet.next()) {
            coName_Company.add(resultSet.getString("coName"));
            rentCoId_Company.add(resultSet.getString("rentCoId"));
            coAddr_Company.add(resultSet.getString("coAddr"));
            coDigit_Company.add(resultSet.getString("coDigit"));
            coManagerName_Company.add(resultSet.getString("coManagerName"));
            coManagerEmail_Company.add(resultSet.getString("coManagerEmail"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Company Data Loading fail! " + e);
      }
   }

   private void bringCampingCarBasicInfo() {
      String sql = "SELECT * FROM CampingCarBasicInfo;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         carRegistId_CarBasic.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         rentCoId_CarBasic.clear();
         carName_CarBasic.clear();
         carSeatNum_CarBasic.clear();
         rentPrice_CarBasic.clear();
         carRegistDate_CarBasic.clear();
         carPossibleRent_CarBasic.clear();

         while (resultSet.next()) {
            carRegistId_CarBasic.add(resultSet.getString("carRegistId"));
            rentCoId_CarBasic.add(resultSet.getString("rentCoId"));
            carName_CarBasic.add(resultSet.getString("carName"));
            carSeatNum_CarBasic.add(resultSet.getString("carSeatNum"));
            rentPrice_CarBasic.add(resultSet.getString("rentPrice"));
            carRegistDate_CarBasic.add(resultSet.getString("carRegistDate"));
            carPossibleRent_CarBasic.add(resultSet.getString("carPossibleRent"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Car Basic Info Data Loading fail! " + e);
      }
   }

   private void bringCampingCarDetails() {

      String sql = "SELECT * FROM CampingCarDetails;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         carRegistId_CarDetails.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         carNum_CarDetails.clear();
         manufactCo_CarDetails.clear();
         manufactYear_CarDetails.clear();
         odo_CarDetails.clear();

         while (resultSet.next()) {
            carRegistId_CarDetails.add(resultSet.getString("carRegistId"));
            carNum_CarDetails.add(resultSet.getString("carNum"));
            manufactCo_CarDetails.add(resultSet.getString("manufactCo"));
            manufactYear_CarDetails.add(resultSet.getString("manufactYear"));
            odo_CarDetails.add(resultSet.getString("odo"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Car Details Data Loading fail! " + e);
      }
   }

   private void bringUserCampingCarBasicInfo() {
      String sql = "SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y';";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         carRegistId_UCarBasic.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         rentCoId_UCarBasic.clear();
         carName_UCarBasic.clear();
         carSeatNum_UCarBasic.clear();
         rentPrice_UCarBasic.clear();
         carRegistDate_UCarBasic.clear();

         while (resultSet.next()) {
            carRegistId_UCarBasic.add(resultSet.getString("carRegistId"));
            rentCoId_UCarBasic.add(resultSet.getString("rentCoId"));
            carName_UCarBasic.add(resultSet.getString("carName"));
            carSeatNum_UCarBasic.add(resultSet.getString("carSeatNum"));
            rentPrice_UCarBasic.add(resultSet.getString("rentPrice"));
            carRegistDate_UCarBasic.add(resultSet.getString("carRegistDate"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println(" User Car Basic Info Data Loading fail! " + e);
      }
   }

   private void bringUserCampingCarDetails() {

      String sql = "SELECT * FROM CampingCarDetails WHERE EXISTS ( SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y' AND CampingCarBasicInfo.carRegistId = CampingCarDetails.carRegistId );";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         carRegistId_UCarDetails.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         carNum_UCarDetails.clear();
         manufactCo_UCarDetails.clear();
         manufactYear_UCarDetails.clear();
         odo_UCarDetails.clear();

         while (resultSet.next()) {
            carRegistId_UCarDetails.add(resultSet.getString("carRegistId"));
            carNum_UCarDetails.add(resultSet.getString("carNum"));
            manufactCo_UCarDetails.add(resultSet.getString("manufactCo"));
            manufactYear_UCarDetails.add(resultSet.getString("manufactYear"));
            odo_UCarDetails.add(resultSet.getString("odo"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println(" User Car Details Data Loading fail! " + e);
      }
   }

   // CustomerInfoalBasicInfo Table Data
   private void bringCustomerInfo() {
      String sql = "SELECT * FROM CustomerInfo;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         custLicenseNum_CustInfo.clear(); // 혹시나 있을지 모르는 List내의 데이터들을 삭제후 사용
         custName_CustInfo.clear();
         custAddr_CustInfo.clear();
         custDigit_CustInfo.clear();
         custEmail_CustInfo.clear();

         while (resultSet.next()) {
            custLicenseNum_CustInfo.add(resultSet.getString("custLicenseNum"));
            custName_CustInfo.add(resultSet.getString("custName"));
            custAddr_CustInfo.add(resultSet.getString("custAddr"));
            custDigit_CustInfo.add(resultSet.getString("custDigit"));
            custEmail_CustInfo.add(resultSet.getString("custEmail"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("Customer Info Data Loading fail! " + e);
      }
   }

   // CarRentalBasicInfo Table Data
   private void bringRentalBasic() {
      String sql = "SELECT * FROM CarRentalBasicInfo;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         rentNum_RentalBasic.clear();
         carRegistId_RentalBasic.clear();
         custLicenseNum_RentalBasic.clear();
         rentCoId_RentalBasic.clear();

         while (resultSet.next()) {
            rentNum_RentalBasic.add(resultSet.getString("rentNum"));
            carRegistId_RentalBasic.add(resultSet.getString("carRegistId"));
            rentCoId_RentalBasic.add(resultSet.getString("rentCoId"));
            custLicenseNum_RentalBasic.add(resultSet.getString("custLicenseNum"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("RentalBasic Info Data Loading fail! " + e);
      }
   }

   // CarRentalDetails Table Data
   private void bringRentalDetails() {
      String sql = "SELECT * FROM CarRentalDetails;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         rentNum_RentalDetails.clear();
         startDate_RentalDetails.clear();
         rentPeriod_RentalDetails.clear();
         charge_RentalDetails.clear();
         payDeadline_RentalDetails.clear();
         otherClaims_RentalDetails.clear();
         otherCharges_RentalDetails.clear();

         while (resultSet.next()) {
            rentNum_RentalDetails.add(resultSet.getString("rentNum"));
            startDate_RentalDetails.add(resultSet.getString("startDate"));
            rentPeriod_RentalDetails.add(resultSet.getString("rentPeriod"));
            charge_RentalDetails.add(resultSet.getString("charge"));
            payDeadline_RentalDetails.add(resultSet.getString("payDeadline"));
            otherClaims_RentalDetails.add(resultSet.getString("otherClaims"));
            otherCharges_RentalDetails.add(resultSet.getString("otherCharges"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("RentalDetails Info Data Loading fail! " + e);
      }
   }

   // CarRentalDetails Table Data
   private void bringCarInspectionList() {
      String sql = "SELECT * FROM CarInspectionList;";

      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         rentNum_Inspection.clear();
         carRegistId_Inspection.clear();
         carFrontInfo_Inspection.clear();
         carRightInfo_Inspection.clear();
         carLeftInfo_Inspection.clear();
         carBackInfo_Inspection.clear();
         repairRequired_Inspection.clear();

         while (resultSet.next()) {
            rentNum_Inspection.add(resultSet.getString("rentNum"));
            carRegistId_Inspection.add(resultSet.getString("carRegistId"));
            carFrontInfo_Inspection.add(resultSet.getString("carFrontInfo"));
            carRightInfo_Inspection.add(resultSet.getString("carRightInfo"));
            carLeftInfo_Inspection.add(resultSet.getString("carLeftInfo"));
            carBackInfo_Inspection.add(resultSet.getString("carBackInfo"));
            repairRequired_Inspection.add(resultSet.getString("repairRequired"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("InspectionList Data Loading fail! " + e);
      }
   }

   // workShopId(), workShopName(), workShopAddr(), workShopDigit(),
   // shopManagerName(), shopManagerEmail()

   private void bringWorkShopInfo() {
      String sql = "SELECT * FROM WorkShopInfo;";

      // workShopId(), workShopName(), workShopAddr(), workShopDigit(),
      // shopManagerName(), shopManagerEmail()
      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         workShopId_ShopInfo.clear();
         workShopName_ShopInfo.clear();
         workShopAddr_ShopInfo.clear();
         workShopDigit_ShopInfo.clear();
         shopManagerName_ShopInfo.clear();
         shopManagerEmail_ShopInfo.clear();

         while (resultSet.next()) {
            workShopId_ShopInfo.add(resultSet.getString("workShopId"));
            workShopName_ShopInfo.add(resultSet.getString("workShopName"));
            workShopAddr_ShopInfo.add(resultSet.getString("workShopAddr"));
            workShopDigit_ShopInfo.add(resultSet.getString("workShopDigit"));
            shopManagerName_ShopInfo.add(resultSet.getString("ShopManagerName"));
            shopManagerEmail_ShopInfo.add(resultSet.getString("ShopManagerEmail"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("WorkShopInfo Data Loading fail! " + e);
      }
   }

   private void bringHistory() {
      String sql = "SELECT * FROM CarMaintenanceHistory;";

      // workShopId(), workShopName(), workShopAddr(), workShopDigit(),
      // shopManagerName(), shopManagerEmail()
      try {
         stmt = con.createStatement();
         resultSet = stmt.executeQuery(sql);

         maintenanceNum_History.clear();
         carRegistId_History.clear();
         rentCoId_History.clear();
         custLicenseNum_History.clear();
         workShopId_History.clear();
         maintenanceHistory_History.clear();
         repairDate_History.clear();
         repairPrice_History.clear();
         payDeadline_History.clear();
         otherDetails_History.clear();

         while (resultSet.next()) {
            maintenanceNum_History.add(resultSet.getString("maintenanceNum"));
            carRegistId_History.add(resultSet.getString("carRegistId"));
            rentCoId_History.add(resultSet.getString("rentCoId"));
            custLicenseNum_History.add(resultSet.getString("custLicenseNum"));
            workShopId_History.add(resultSet.getString("workShopId"));
            maintenanceHistory_History.add(resultSet.getString("maintenanceHistory"));
            repairDate_History.add(resultSet.getString("repairDate"));
            repairPrice_History.add(resultSet.getString("repairPrice"));
            payDeadline_History.add(resultSet.getString("payDeadline"));
            otherDetails_History.add(resultSet.getString("otherDetails"));
         }

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("History Data Loading fail! " + e);
      }
   }

   private void conDB() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("드라이버 로드 성공");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         System.out.println("드라이버 로드 실패!");
      }

      try { /* 데이터베이스를 연결하는 과정 */
         System.out.println("데이터베이스 연결 준비...");
         con = DriverManager.getConnection(url, userid, pwd);
         System.out.println("데이터베이스 연결 성공");
         iniData();
      } catch (SQLException e1) {
         e1.printStackTrace();
         System.out.println("데이터베이스 연결실패 !");
      }
   }

   private void iniData() { // 추가 데이터세팅.

      try {
         stmt = con.createStatement();
         deleteDataTable(); // 데이터 테이블 삭제
         createDataTable(); // 데이터 테이블 생성
         insertData(); // 데이터 삽입
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("테이블 초기화 실패!");
      }
   }

   // 대여회사 추가
   public int carCompanySignUp(String coName, String coAddr, String coDigit, String managerName, String managerEmail) {

      String sql = "INSERT INTO CampingCarCompany VALUES(" + (rentCoId_Company.size() + 1) + ", '" + coName + "', '"
            + coAddr + "', '" + coDigit + "', '" + managerName + "', '" + managerEmail + "');";

      try {
         stmt.executeUpdate(sql);
         bringCampingCarCompany();
      } catch (SQLException e) {
         System.out.println("Sign Up Error!" + e);
         return 0;
      }

      return rentCoId_Company.size();
   }

   // 캠핑카 추가
   public int carEnrollment(String rentCoId, String carName, String seatNum, String rentalPrice, String registDate,
         String carNum, String manufactCo, String manufactYear, String odo) {
      int ID = carRegistId_CarBasic.size() + 1;
      String sql = "INSERT INTO CampingCarBasicInfo VALUES(" + ID + ", '" + rentCoId
            + "','" + carName + "', '" + seatNum + "', '" + rentalPrice + "', '" + registDate + "', 'Y');";
      String sql2 = "INSERT INTO CampingCarDetails VALUES(" + ID + ", '" + carNum
            + "','" + manufactCo + "', '" + manufactYear + "', '" + odo + "');";

      try {
         stmt.executeUpdate(sql);
         stmt.executeUpdate(sql2);
         bringCampingCarBasicInfo();
         bringCampingCarDetails();
      } catch (SQLException e) {
         System.out.println("Sign Up Error!" + e);
         return 0;
      }

      return carRegistId_CarBasic.size();
   }

   // 고객 추가
   public int custInfo(String licenseNum, String CustName, String CustAddr, String CustDigit, String CustEmail) {

      String sql = "INSERT INTO CustomerInfo VALUES(" + licenseNum + ", '" + CustName + "','" + CustAddr + "', '"
            + CustDigit + "', '" + CustEmail + "');";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("Sign Up Error!" + e);
         return 0;
      }

      return custLicenseNum_CustInfo.size() + 1;
   }

   // 정비소 추가
   public int shopInfo(String shopName, String shopAddr, String shopDigit, String shopMangerName,
         String shopManagerEmail) {
      workShopId_ShopInfo.size();
      String sql = "INSERT INTO WorkShopInfo VALUES(" + (workShopId_ShopInfo.size() + 1) + ", '" + shopName + "', '"
            + shopAddr + "','" + shopDigit + "', '" + shopMangerName + "', '" + shopManagerEmail + "');";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("Sign Up Error!" + e);
         return 0;
      }

      return workShopId_ShopInfo.size();
   }
   

   /*public int insertInspection(int Row, String carRegistId, String front, String Right, String left, String back ) {
      _ShopInfo.size();
      String sql = "INSERT INTO CarInspectionList VALUES ('" + Row + "', '" + carRegistId + "', '"
            + front + "','" + Right + "', '" + left + "', '" + back + "');";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("insert Error!" + e);
         return 0;
      }

      return 1;
   }*/

   // 삭제
   public int deleteRowData(String tableName, String Id, String Row) {
      // 삭제시 외래키 조건이면, 삭제 불가능 예외 처리 필요
      String sql = "DELETE FROM " + tableName + " WHERE " + tableName + "." + Id + " = " + Row + ";";

      try {
         stmt.executeUpdate(sql);
      } catch (SQLException e) {
         System.out.println("Sign Up Error!" + e);
         return 0;
      }
      setDB();
      return 1;
   }

   // !-변경-!

   // 캠핑카 대여회사 변경
   public int updateCompanyData(String Row, String coName, String coAddr, String coDigit, String coMName,
         String coMEmail) {
      // UPDATE CampingCarCompany SET coName = '릴리', coAddr = '제임스', coDigit =
      // '02-1111-2222', coManagerName = '포터', coManagerEmail = 'Doby16@isFree.com'
      // WHERE rentCoId = 16;
      String sql = "UPDATE CampingCarCompany SET coName = '" + coName + "', coAddr = '" + coAddr + "', coDigit = '"
            + coDigit + "', coManagerName = '" + coMName + "', coManagerEmail = '" + coMEmail
            + "' WHERE rentCoId = '" + Row + "';";

      try {
         stmt.executeUpdate(sql);
         bringCampingCarCompany();
      } catch (SQLException e) {
         System.out.println("Update Error!" + e);
         return 0;
      }

      return 1;
   }

   // 캠핑카 정보 변경
   public int updateCarInfoData(String Row, String RentCoId, String CarName, String SeatNum, String RentalPrice,
         String RegistDate, String CarNum, String manufactCo, String manufactYear, String odo) {
      String sql1 = "UPDATE CampingCarBasicInfo SET rentCoId = '" + RentCoId + "', carName = '" + CarName
            + "', carSeatNum = '" + SeatNum + "', rentPrice = '" + RentalPrice + "', carRegistDate = '" + RegistDate
            + "' WHERE carRegistId = 'Row';";
      String sql2 = "UPDATE CampingCarDetails SET carNum = '" + CarNum + "', manufactCo = '" + manufactCo
            + "', manufactYear = '" + manufactYear + "', odo = '" + odo + "' WHERE carRegistId = '" + Row + "';";

      try {
         stmt.executeUpdate(sql2);
         bringCampingCarDetails();
         stmt.executeUpdate(sql1);
         bringCampingCarBasicInfo();
         //데베에서는 잘 돌아가는데 왜 안되는지를 모르겠다.
         
      } catch (SQLException e) {
         System.out.println("Update Error!" + e);
         return 0;
      }
      return 1;
   }

   // 고객 정보 변경
   public int updateCustData(String Row, String custName, String custAddr, String custDigit, String custMEmail) {
      String sql = "UPDATE CustomerInfo SET custName = '" + custName + "', custAddr = '" + custAddr + "', "
            + "custDigit = '" + custDigit + "', custEmail = '" + custMEmail + "'"
            + " WHERE custLicenseNum = '" + Row + "';";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("Update Error!" + e);
         return 0;
      }

      return 1;
   }

   // 정비소 정보 변경
   public int updateShopData(String Row, String ShopName, String ShopAddr, String ShopDigit, String ShopMName, String ShopMEmail) {
      String sql = "UPDATE WorkShopInfo SET workShopName = '" + ShopName + "', workShopAddr = '" + ShopAddr + "', "
            + "workShopDigit = '" + ShopDigit + "' shopManagerName = '" + ShopMName + "', shopManagerEmail = '" + ShopMEmail + "'"
            + " WHERE workShopId = '" + Row + "';";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("Update Error!" + e);
         return 0;
      }

      return 1;
   }
   // 캠핑카 반환 -> 정보 변경 (점검 내역 작성 후, 문제 있으면 -> 정비 내역 / 문제 없으면 -> 캠핑카 대여가능 Y로 바꾸기)
   public int returnCar(String Row, String coName, String coAddr, String coDigit, String coMName, String coMEmail) {
      String sql = "UPDATE CampingCarCompany SET coName = '" + coName + "', coAddr = '" + coAddr + "', "
            + "coDigit = '" + coDigit + "' coManagerName = '" + coMName + "', coManagerEmail = '" + coMEmail + "'"
            + " WHERE rentCoId = '" + Row + "';";

      try {
         stmt.executeUpdate(sql);
         bringCustomerInfo();
      } catch (SQLException e) {
         System.out.println("Update Error!" + e);
         return 0;
      }

      return 1;
   }

   private void insertData() {
      try {

         System.out.println();

         // Data insert CampingCarCompany Table
         // rentCoId(), coName(), coAddr(), coDigit(), coManagerName(), coManagerEmail()
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('1', '해리 캠핑카', '서울', '02-1234-5678', '김해리', 'Doby1@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('2', '론 캠핑카', '대전', '02-5678-1234', '박포터', 'Doby2@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('3', '말포이 캠핑카', '대구', '02-3409-8846', '장기보', 'Doby3@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('4', '볼드모트 캠핑카','부산', '02-2369-8745', '안휴식', 'Doby4@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('5', '위즐리 캠핑카', '광주', '02-2906-9913', '서지식', 'Doby5@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('6', '스네이프 캠핑카', '울산', '02-9636-1123', '이도성', 'Doby6@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('7', '세드릭 캠핑카', '충주', '02-1632-2356', '성기원', 'Doby7@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('8', '디고리 캠핑카', '청주', '02-6639-1542', '박무혁', 'Doby8@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('9', '헤르미온느 캠핑카', '서울', '02-3145-9657', '김태석', 'Doby9@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('10', '그레인저 캠핑카', '일산', '02-3241-6639', '박상태', 'Doby10@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('11', '세베루스 캠핑카', '인천', '02-9964-1654', '이정기', 'Doby11@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('12', '알버스 캠핑카', '야동리', '02-5672-1657', '차태식', 'Doby12@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('13', '맥고나걸 캠핑카', '제천', '02-3144-1633', '장태휴', 'Doby13@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('14', '덤블도어 캠핑카', '대구', '02-1577-1577', '박재식', 'Doby14@isFree.com');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarCompany VALUES('15', '루베우스 캠핑카', '서울', '02-3316-2344', '김수하', 'Doby15@isFree.com');");

         System.out.println("CampingCarCompany Data 생성 완료");

         // Data insert CampingCarBasicInfo table
         // carRegistId(), rentCoId(), carName(), carSeatNum(), rentPrice(),
         // carRegistDate(), carPossibleRent()
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('1', '1', '프리랜더 20CB', '6', '170000', '2004-03-10', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('2', '10', '프리랜더 21CB', '7', '180000', '2017-05-31', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('3', '13', '프리랜더 22CB', '4', '110000', '2008-06-01', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('4', '4', '프리랜더 23CB', '6', '200000', '2002-06-01', 'N');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('5', '5', '프리랜더 24CB', '8', '300000', '2018-06-04', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('6', '5', '프리랜더 25CB', '7', '150000', '2017-06-06', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('7', '7', '프리랜더 26CB', '9', '280000', '2005-06-06', 'N');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('8', '2', '프리랜더 27CB', '6', '130000', '2010-01-07', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('9', '9', '프리랜더 28CB', '5', '110000', '2003-06-10', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('10', '1', '프리랜더 29CB', '7', '190000', '2018-06-10', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('11', '13', '프리랜더 30CB', '8', '190000', '2010-11-10', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('12', '8', '프리랜더 31CB', '7', '200000', '2014-06-11', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('13', '6', '프리랜더 32CB', '5', '100000', '2012-06-13', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('14', '15', '프리랜더 33CB', '8', '180000', '2018-06-15', 'Y');");
         stmt.executeUpdate(
               "INSERT INTO CampingCarBasicInfo VALUES('15', '11', '프리랜더 34CB', '9', '240000', '2017-09-21', 'Y');");

         System.out.println("CampingCarBasicInfo Data 생성 완료");

         // Data insert CampingCarDetails table
         // carRegistId(), carNum(), manufactCo(), manufactYear(), odo()
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('1', '0106', '현대', '2004', '1372460');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('2', '0056', '기아', '2017', '1104596');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('3', '5123', '쌍용', '2005', '1001563');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('4', '6244', '기아', '2001', '906687');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('5', '1357', '현대', '2015', '991246');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('6', '3564', '기아', '2016', '468795');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('7', '9542', '쌍용', '2002', '1426400');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('8', '2364', '기아', '2009', '610234');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('9', '1269', '현대', '2002', '123654');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('10', '2358', '기아', '2018', '1620142');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('11', '9874', '쌍용', '2009', '1912354');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('12', '4123', '기아', '2013', '1642845');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('13', '6541', '현대', '2010', '3354624');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('14', '2567', '기아', '2018', '1657482');");
         stmt.executeUpdate("INSERT INTO CampingCarDetails VALUES('15', '2354', '쌍용', '2015', '984562');");

         System.out.println("CampingCarDetails Data 생성 완료");

         // Data insert CustomerInfo table
         // custLicenseNum(), custName(), custAddr(), custDigit(), custEmail()
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('1', '티치', '서울', '010-6359-4514', 'qwerty1@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('2', '에이스', '대전', '010-1235-4845', 'qwerty2@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('3', '뉴게이트', '대구', '010-1206-9633', 'qwerty3@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('4', '제파', '부산', '010-1234-4513', 'qwerty4@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('5', '볼살리노', '구미', '010-1534-8456', 'qwerty5@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('6', '사카즈키', '울산', '010-9683-9614', 'qwerty6@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('7', '샬롯링링', '안산', '010-7853-9624', 'qwerty7@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('8', '빈스모크', '수원', '010-7514-9684', 'qwerty8@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('9', '이치디', '화성', '010-1754-9466', 'qwerty9@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('10', '니디', '오산', '010-9369-9684', 'qwerty10@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('11', '상디', '횡성', '010-7968-4496', 'qwerty11@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('12', '욘디', '음성', '010-9436-1215', 'qwerty12@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('13', '시키', '제천', '010-7849-9686', 'qwerty13@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('14', '시저', '양양', '010-1219-9947', 'qwerty14@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO CustomerInfo VALUES('15', '베르고', '임남호', '010-1012-9976', 'qwerty15@jgmail.com');");

         System.out.println("CustomerInfo Data 생성 완료");

         // Data insert CarRentalBasicInfo table
         // rentNum(), carRegistId(), rentCoId(), custLicenseNum()
         // 22개 입력 (반납된 내역 20개, 반납 X 2개)
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('1', '4', '4', '11');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('2', '3', '13', '2');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('3', '1', '1', '7');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('4', '6', '5', '4');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('5', '5', '5', '8');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('6', '2', '10', '10');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('7', '8', '2', '6');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('8', '10', '1', '5');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('9', '11', '13', '9');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('10', '14', '15', '13');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('11', '12', '8', '11');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('12', '13', '6', '12');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('13', '9', '9', '3');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('14', '7', '7', '14');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('15', '15', '11', '4');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('16', '4', '4', '11');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('17', '8', '2', '2');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('18', '3', '13', '7');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('19', '1', '1', '4');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('20', '6', '5', '8');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('21', '4', '4', '11');");
         stmt.executeUpdate("INSERT INTO CarRentalBasicInfo VALUES('22', '7', '7', '2');");

         System.out.println("CarRentalBasicInfo Data 생성 완료");

         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('1', '2020-01-02', '3', '600000', '2020-01-02', 'AUX, 휴대폰 거치대', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('2', '2020-01-02', '2', '560000', '2020-01-02', 'AUX', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('3', '2020-01-20', '5', '550000', '2020-01-20', 'AUX', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('4', '2020-02-02', '3', '510000', '2020-02-02', 'AUX', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('5', '2020-02-06', '1', '150000', '2020-02-06', '휴대폰 거치대', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('6', '2020-02-10', '2', '360000', '2020-02-10', 'AUX, 목배게', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('7', '2020-02-15', '6', '780000', '2020-02-15', 'AUX, 파워핸들', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('8', '2020-02-16', '4', '760000', '2020-02-16', '목배게', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('9', '2020-02-22', '3', '900000', '2020-02-22', '없음', '0' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('10', '2020-04-01', '7', '1400000', '2020-04-01', '파워핸들', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('11', '2020-04-14', '1', '190000', '2020-04-14', '휴대폰 거치대, 목배게', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('12', '2020-04-16', '6', '1080000', '2020-04-16', 'AUX', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('13', '2020-04-17', '6', '660000', '2020-04-17', '없음', '0' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('14', '2020-04-19', '9', '12520000', '2020-04-19', '목배게, 파워핸들', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('15', '2020-04-19', '2', '480000', '2020-04-19', '없음', '0' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('16', '2020-04-22', '6', '780000', '2020-04-22', 'AUX, 파워핸들', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('17', '2020-04-25', '4', '760000', '2020-04-25', '목배게', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('18', '2020-05-16', '3', '900000', '2020-05-16', '없음', '0' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('19', '2020-05-20', '7', '1400000', '2020-05-20', '파워핸들', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('20', '2020-05-22', '1', '190000', '2020-05-22', '휴대폰 거치대, 목배게', '10000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('21', '2020-06-10', '6', '1080000', '2020-06-10', 'AUX', '5000' );");
         stmt.executeUpdate(
               "INSERT INTO CarRentalDetails VALUES('22', '2020-06-14', '6', '660000', '2020-06-14', '없음', '0' );");
         System.out.println("CarRentalDetails Data 생성 완료");

         // Data insert CarInspectionList table
         // rentNum(), carRegistId(), carFrontInfo(), carRightInfo(),
         // carLeftInfo(), carBackInfo(), repairRequired()
         // 20개 data (Y 15 N 5)
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('1', '4', 'O', 'X', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('2', '3', 'X', 'X', 'X', 'X', 'N');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('3', '1', 'X', 'X', 'O', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('4', '6', 'X', '', '', '', 'N');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('5', '5', 'X', 'O', 'X', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('6', '2', 'O', 'X', 'O', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('7', '8', 'X', '', '', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('8', '10', 'X', '', '', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('9', '11', 'X', '', '', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('10', '14', 'O', '', 'O', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('11', '12', 'X', 'X', 'X', 'X', 'N');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('12', '13', 'O', 'X', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('13', '9', 'O', 'X', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('14', '7', 'O', 'O', 'O', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('15', '15', 'X', 'O', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('16', '4', 'O', 'X', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('17', '8', '', '', '', '', 'N');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('18', '3', 'X', 'O', 'X', 'O', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('19', '1', 'O', 'X', 'X', 'X', 'Y');");
         stmt.executeUpdate("INSERT INTO CarInspectionList VALUES('20', '6', 'X', 'X', 'X', 'X', 'N');");

         System.out.println("CarInspectionList Data 생성 완료");

         // Data insert WorkShopInfo table
         // workShopId(), workShopName(), workShopAddr(), workShopDigit(),
         // shopManagerName(), shopManagerEmail()
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('1', '루피정비소', '서울', '02-1981-0901', '박성진', 'free1@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('2', '조로정비소', '대전', '02-2354-2454', '김성진', 'free2@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('3', '나미정비소', '대구', '02-1211-3548', '채성진', 'free3@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('4', '우솝정비소', '부산', '02-6542-2387', '최성진', 'free4@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('5', '쵸파정비소', '춘천', '02-3541-5164', '지성진', 'free5@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('6', '프랑키정비소', '양구', '02-1251-2145', '장성진', 'free6@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('7', '브룩정비소', '화천', '02-5524-2354', '안성진', 'free7@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('8', '징베정비소', '인제', '02-1212-2147', '이성진', 'free8@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('9', '비비정비소', '강릉', '02-2698-9852', '노성진', 'free9@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('10', '로빈정비소', '태안', '02-0244-1256', '홍성진', 'free10@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('11', '거프정비소', '충주', '02-0507-3254', '유성진', 'free11@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('12', '로저정비소', '청주', '02-0608-6523', '무성진', 'free12@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('13', '레일리정비소', '원주', '02-9568-7854', '예성진', 'free13@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('14', '불렛정비소', '상주', '02-1256-1698', '현성진', 'free14@jgmail.com');");
         stmt.executeUpdate(
               "INSERT INTO WorkShopInfo VALUES('15', '죠즈정비소', '여주', '02-4251-3426', '강성진', 'free15@jgmail.com');");

         System.out.println("WorkShopInfo Data 생성 완료");

         // Data insert CarMaintenanceHistory table
         // maintenanceNum(), carRegistId(), rentCoId(), custLicenseNum(), workShopId(),
         // maintenanceHistory(), repairDate(), repairPrice(), payDeadline(),
         // otherDetails()

         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('1', '4', '4', '11', '3', '앞범퍼 교체', '2020-01-06', '2274000', '2020-02-06', '타이어 공기압 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('2', '1', '1', '7', '7', '좌측 사이드미러 교체', '2020-01-26', '542000', '2020-02-26', '엔진오일 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('3', '5', '5', '8', '4', '우측 사이드미러 교체, 뒷범퍼 부분도색', '2020-02-08', '1082000', '2020-03-08','엔진오일 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('4', '2', '10', '10', '1', '좌측 전조등 및 운전석 Fender 교체', '2020-02-13', '667000', '2020-03-13','브레이크 패드 상태 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('5', '10', '1', '5', '9', '후미등 교체', '2020-02-21', '224000', '2020-03-21','차량내 스피커 이상유무 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('6', '8', '2', '6', '1', '뒷범퍼 부분도색', '2020-02-22', '486000', '2020-03-22','없음');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('7', '11', '13', '9', '8', '운전석 문 교체', '2020-02-26', '1123000', '2020-03-26','후미등 작동 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('8', '14', '15', '13', '5', '후미등 교체, 뒷범퍼 교체', '2020-04-09', '224000', '2020-05-09','차량내 스피커 이상유무 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('9', '15', '11', '4', '12', '조수석 Fender부분도색', '2020-04-22', '657000', '2020-05-22','엔진오일 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('10', '13', '6', '12', '5', '핸들 교체, 엔진그릴 교체', '2020-04-23', '2324000', '2020-05-23','타이어 마모상태 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('11', '9', '9', '3', '2', '앞범퍼 부분도색', '2020-04-24', '425000', '2020-05-24','타이어 공기압 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('12', '7', '7', '14', '13', '서스펜션 교체, 바퀴 축 교체', '2020-04-29', '8320000', '2019-01-10','미션오일 체크');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('13', '4', '4', '11', '11', 'A프레임 교체, 앞유리 교체', '2020-04-29', '7235000', '2020-05-29','없음');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('14', '3', '13', '7', '10', '우측 뒷부분 휠 교체', '2020-05-20', '406000', '2020-06-30','없음');");
         stmt.executeUpdate(
               "INSERT INTO CarMaintenanceHistory VALUES('15', '1', '1', '4', '9', '엔진룸 부품 전면 교체', '2020-05-28', '17845000', '2020-06-28','타이어 마모상태 체크');");

         System.out.println("CarMaintenanceHistory Data 생성 완료");

      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("데이터 삽입 실패!" + e);
      }
   }

   private void createDataTable() {
      try {

         stmt.execute("CREATE TABLE IF NOT EXISTS CampingCarCompany (" // 캠핑카 대여회사 테이블 생성
               + "rentCoId INT NOT NULL," + "coName VARCHAR(20) NOT NULL," + "coAddr VARCHAR(100) NOT NULL,"
               + "coDigit VARCHAR(15) NOT NULL," + "coManagerName VARCHAR(10) NOT NULL,"
               + "coManagerEmail VARCHAR(45) NOT NULL," + "PRIMARY KEY (rentCoId))");

         System.out.println("CampingCarCompany Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CampingCarBasicInfo (" // 캠핑카 기본정보 테이블 생성
               + "carRegistId INT NOT NULL," + "rentCoId INT NOT NULL," + "carName VARCHAR(20) NOT NULL,"
               + "carSeatNum INT NOT NULL," + "rentPrice INT NOT NULL," + "carRegistDate DATE NOT NULL,"
               + "carPossibleRent VARCHAR(5) NOT NULL," + "PRIMARY KEY (carRegistId),"
               + "FOREIGN KEY (rentCoId) REFERENCES CampingCarCompany(rentCoId))");

         System.out.println("CampingCarBasicInfo Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CampingCarDetails (" // 캠핑카 상세정보 테이블 생성
               + "carRegistId INT NOT NULL," + "carNum VARCHAR(10) NOT NULL," + "manufactCo VARCHAR(20) NOT NULL,"
               + "manufactYear VARCHAR(10) NOT NULL," + "odo INT NOT NULL," + "PRIMARY KEY (carRegistId),"
               + "FOREIGN KEY (carRegistId) REFERENCES CampingCarBasicInfo(carRegistId))");

         System.out.println("CampingCarDetails Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CustomerInfo (" // 고객정보 테이블 생성
               + "custLicenseNum INT NOT NULL," + "custName VARCHAR(10) NOT NULL,"
               + "custAddr VARCHAR(100) NOT NULL," + "custDigit VARCHAR(15) NOT NULL,"
               + "custEmail VARCHAR(45) NOT NULL," + "PRIMARY KEY (custLicenseNum))");

         System.out.println("CustomerInfo Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CarRentalBasicInfo (" // 캠핑카 대여 기본정보 테이블 생성
               + "rentNum INT NOT NULL," + "carRegistId INT NOT NULL," + "rentCoId INT NOT NULL,"
               + "custLicenseNum INT NOT NULL," + "PRIMARY KEY (rentNum),"
               + "FOREIGN KEY (carRegistId) REFERENCES CampingCarBasicInfo(carRegistId),"
               + "FOREIGN KEY (custLicenseNum) REFERENCES CustomerInfo(custLicenseNum),"
               + "FOREIGN KEY (rentCoId) REFERENCES CampingCarCompany(rentCoId))");

         System.out.println("CarRentalBasicInfo Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CarRentalDetails (" // 캠핑카 대여 상세정보 테이블 생성
               + "rentNum INT NOT NULL," + "startDate DATE NOT NULL," + "rentPeriod INT NOT NULL,"
               + "charge INT NOT NULL," + "payDeadline DATE NOT NULL," + "otherClaims VARCHAR(100) NOT NULL,"
               + "otherCharges INT NOT NULL," + "PRIMARY KEY (rentNum),"
               + "FOREIGN KEY (rentNum) REFERENCES CarRentalBasicInfo(rentNum))");

         System.out.println("CarRentalDetails Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CarInspectionList (" // 캠핑카 점검내역 테이블 생성
               + "rentNum INT NOT NULL," + "carRegistId INT NOT NULL," + "carFrontInfo VARCHAR(100) NULL,"
               + "carRightInfo VARCHAR(100) NULL," + "carLeftInfo VARCHAR(100) NULL,"
               + "carBackInfo VARCHAR(100) NULL," + "repairRequired VARCHAR(5) NOT NULL,"
               + "PRIMARY KEY (rentNum)," + "FOREIGN KEY (rentNum) REFERENCES CarRentalBasicInfo(rentNum),"
               + "FOREIGN KEY (rentNum) REFERENCES CarRentalDetails(rentNum),"
               + "FOREIGN KEY (carRegistId) REFERENCES CampingCarBasicInfo(carRegistId))");

         System.out.println("CarInspectionList Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS WorkShopInfo (" // 정비소정보 테이블 생성
               + "workShopId INT NOT NULL," + "workShopName VARCHAR(20) NOT NULL,"
               + "workShopAddr VARCHAR(100) NULL," + "workShopDigit VARCHAR(15) NOT NULL,"
               + "shopManagerName VARCHAR(10) NOT NULL," + "shopManagerEmail VARCHAR(45) NOT NULL,"
               + "PRIMARY KEY (workShopId))");

         System.out.println("WorkShopInfo Table 완료");

         stmt.execute("CREATE TABLE IF NOT EXISTS CarMaintenanceHistory (" // 캠핑카 정비내역 테이블 생성
               + "maintenanceNum INT NOT NULL," + "carRegistId INT NOT NULL," + "rentCoId INT NOT NULL,"
               + "custLicenseNum INT NOT NULL," + "workShopId INT NOT NULL,"
               + "maintenanceHistory VARCHAR(300) NOT NULL," + "repairDate DATE NOT NULL,"
               + "repairPrice INT NOT NULL," + "payDeadline DATE NOT NULL," + "otherDetails VARCHAR(300) NULL,"
               + "PRIMARY KEY (maintenanceNum),"
               + "FOREIGN KEY (carRegistId) REFERENCES CampingCarBasicInfo(carRegistId),"
               + "FOREIGN KEY (rentCoId) REFERENCES CampingCarCompany(rentCoId),"
               + "FOREIGN KEY (custLicenseNum) REFERENCES CustomerInfo(custLicenseNum),"
               + "FOREIGN KEY (workShopId) REFERENCES WorkShopInfo(workShopId))");

         System.out.println("CarMaintenanceHistory Table 완료");
      } catch (SQLException e) {
         e.printStackTrace();
         System.out.println("데이터 삽입 실패!" + e);
      }
   }

   private void deleteDataTable() { // 테이블 삭제 메소드
      try {
         stmt.execute("DROP TABLE IF EXISTS CarMaintenanceHistory CASCADE;"); // 캠핑카 정비내역
         stmt.execute("DROP TABLE IF EXISTS WorkShopInfo CASCADE;"); // 정비소 정보
         stmt.execute("DROP TABLE IF EXISTS CarInspectionList CASCADE;"); // 캠핑카 점검내역
         stmt.execute("DROP TABLE IF EXISTS CarRentalDetails CASCADE;"); // 캠핑카 대여 세부정보
         stmt.execute("DROP TABLE IF EXISTS CarRentalBasicInfo CASCADE;"); // 캠핑카 대여 기본정보
         stmt.execute("DROP TABLE IF EXISTS CustomerInfo CASCADE;"); // 대여고객 정보
         stmt.execute("DROP TABLE IF EXISTS CampingCarDetails CASCADE;"); // 캠핑카 세부정보
         stmt.execute("DROP TABLE IF EXISTS CampingCarBasicInfo CASCADE;"); // 캠핑카 기본정보
         stmt.execute("DROP TABLE IF EXISTS CampingCarCompany CASCADE;"); // 캠핑카 대여회사 테이블

         System.out.println("테이블 삭제 성공!");
      } // try
      catch (SQLException e) {
         e.printStackTrace();
         System.out.println("테이블 삭제 실패!" + e);
      } // catch ( SQLException e )
   } // deleteDataTable();

}