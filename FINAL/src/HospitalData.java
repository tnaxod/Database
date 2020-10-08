import java.awt.BorderLayout;
import java.awt.event.*;
//import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class HospitalData extends JFrame implements ActionListener {
	JButton btnD1, btnD2; // Doctors 검색, 입력 버튼
	JButton btnN1, btnN2; // Nurses 검색, 입력 버튼
	JButton btnP1, btnP2; // Patients 검색, 입력 버튼
	JButton btnT1, btnT2; // Treatments 검색, 입력 버튼
	JButton btnC1, btnC2; // Charts 검색, 입력 버튼
	JButton btnS1, btnS2; // 검색1, 2 버튼
	JButton btnRS; // 초기화 버튼
	JButton btnEx; // 입력 예시 버튼
	JLabel labInfo; // 입력 설명

	JTextField input; // 입력창
	JTextArea txtRes; // 결과창
	JPanel pn1; // 검색창
	JPanel pn2; // 입력버튼창
	JPanel pn3; // 전체 입력창

	static Connection con;
	Statement stmt;
	ResultSet rs;
	String Driver = "";
	String url = "jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false";
	String userid = "madang";
	String pwd = "madang";

	public HospitalData() {
		super("16010945 노수민");
		layInit();
		conDB();
		setVisible(true);
		setBounds(200, 100, 1100, 650); // 가로위치,세로위치,가로길이,세로길이
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void deleteTable() {
		try {
			stmt.execute("DROP TABLE IF EXISTS Charts CASCADE;"); // 차트 테이블 삭제
			stmt.execute("DROP TABLE IF EXISTS Treatments CASCADE;"); // 진료 테이블 삭제
			stmt.execute("DROP TABLE IF EXISTS Patients CASCADE;"); // 환자 테이블 삭제
			stmt.execute("DROP TABLE IF EXISTS Nurses CASCADE;"); // 간호사 테이블 삭제
			stmt.execute("DROP TABLE IF EXISTS Doctors CASCADE;"); // 의사 테이블 삭제

			System.out.println("테이블 삭제 성공!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("테이블 삭제 실패!" + e);
		}
	}

	void createTable() {
		try {
			// 의사 테이블 생성
			stmt.execute("CREATE TABLE Doctors (doc_id INTEGER NOT NULL, major_treat VARCHAR(25) NOT NULL, "
					+ "doc_name VARCHAR(20) NOT NULL, doc_gen CHAR(1) NOT NULL, "
					+ "doc_phone VARCHAR(15) NULL, doc_email VARCHAR(50) UNIQUE, "
					+ "doc_position VARCHAR(20) NOT NULL);");
			stmt.execute("ALTER TABLE Doctors ADD CONSTRAINT doc_id_pk PRIMARY KEY (doc_id);");
			System.out.println("Doctors Table 생성 완료");

			// 간호사 테이블 생성
			stmt.execute("CREATE TABLE Nurses (nur_id INTEGER NOT NULL, major_job VARCHAR(25) NOT NULL, "
					+ "nur_name VARCHAR(20) NOT NULL, nur_gen CHAR(1) NOT NULL, "
					+ "nur_phone VARCHAR(15) UNIQUE, nur_email VARCHAR(50) UNIQUE, "
					+ "nur_position VARCHAR(20) NOT NULL);");
			stmt.execute("ALTER TABLE Nurses ADD CONSTRAINT nur_id_pk PRIMARY KEY (nur_id);");
			System.out.println("Nurses Table 생성 완료");

			// 환자 테이블 생성
			stmt.execute("CREATE TABLE Patients (pat_id INTEGER NOT NULL, nur_id INTEGER NOT NULL, "
					+ "doc_id INTEGER NOT NULL, pat_name VARCHAR(20) NOT NULL, "
					+ "pat_gen CHAR(1) NOT NULL, pat_jumin VARCHAR(14) NOT NULL, "
					+ "pat_addr VARCHAR(100) NOT NULL, pat_phone VARCHAR(15) NULL, "
					+ "pat_email VARCHAR(50) UNIQUE, pat_job VARCHAR(20) NOT NULL);");
			stmt.execute("ALTER TABLE Patients ADD CONSTRAINT pat_id_pk PRIMARY KEY (pat_id);");
			stmt.execute("ALTER TABLE Patients ADD (CONSTRAINT R_2 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));");
			stmt.execute("ALTER TABLE Patients ADD (CONSTRAINT R_3 FOREIGN KEY (nur_id) REFERENCES Nurses (nur_id));");
			System.out.println("Patients Table 생성 완료");

			// 진료 테이블 생성
			stmt.execute("CREATE TABLE Treatments (treat_id INTEGER NOT NULL, pat_id INTEGER NOT NULL, "
					+ "doc_id INTEGER NOT NULL, treat_contents VARCHAR(1000) NOT NULL, "
					+ "treat_date DATE NOT NULL);");
			stmt.execute(
					"ALTER TABLE Treatments ADD CONSTRAINT treat_pat_doc_id_pk PRIMARY KEY (treat_id, pat_id, doc_id);");
			stmt.execute(
					"ALTER TABLE Treatments ADD (CONSTRAINT R_5 FOREIGN KEY (pat_id) REFERENCES Patients (pat_id));");
			stmt.execute(
					"ALTER TABLE Treatments ADD (CONSTRAINT R_6 FOREIGN KEY (doc_id) REFERENCES Doctors (doc_id));");
			System.out.println("Treatments Table 생성 완료");

			// 차트 테이블 생성
			stmt.execute("CREATE TABLE Charts (chart_id VARCHAR(20) NOT NULL, treat_id INTEGER NOT NULL, "
					+ "doc_id INTEGER NOT NULL, pat_id INTEGER NOT NULL, "
					+ "nur_id INTEGER NOT NULL, chart_contents VARCHAR(1000) NOT NULL);");
			stmt.execute(
					"ALTER TABLE Charts ADD CONSTRAINT chart_treat_doc_pat_id_pk PRIMARY KEY (chart_id, treat_id, doc_id, pat_id);");
			stmt.execute("ALTER TABLE Charts ADD (CONSTRAINT R_4 FOREIGN KEY (nur_id) REFERENCES Nurses (nur_id));");
			stmt.execute(
					"ALTER TABLE Charts ADD (CONSTRAINT R_7 FOREIGN KEY (treat_id, pat_id, doc_id) REFERENCES Treatments (treat_id, pat_id, doc_id));");
			System.out.println("Charts Table 생성 완료");

			System.out.println("테이블 생성 성공!\n");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("테이블 생성 실패!\n");
		}
	}

	void insertTableData() {
		try {
			// Doctors Table Data(doc_id, major_treat, doc_name, doc_gen, doc_phone,
			// doc_email, doc_position)
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('980312', '소아과', '이태정', 'M', '010-333-1340', 'ltj@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('000601', '내과', '안성기', 'M', '011-222-0987', 'ask@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('001208', '외과', '김민종', 'M', '010-333-8743', 'kmj@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('020403', '피부과', '이태서', 'M', '019-777-3764', 'lts@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('050900', '소아과', '김연아', 'F', '010-555-3746', 'kya@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('050101', '내과', '차태현', 'M', '011-222-7643', 'cth@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('062019', '소아과', '전지현', 'F', '010-999-1265', 'jjh@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('070576', '피부과', '홍길동', 'M', '016-333-7263', 'hgd@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('080543', '방사선과', '유재석', 'M', '010-222-1263', 'yjs@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('091001', '외과', '김병만', 'M', '010-555-3542', 'kbm@hanbh.com', '전문의');");
			// new data
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('030310', '안과', '이찬원', 'M', '010-222-1101', 'icw@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('032034', '피부과', '변백현', 'M', '010-444-0506', 'bbh@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('040206', '산부인과', '이지은', 'F', '010-111-0516', 'lje@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('041534', '외과', '권보아', 'F', '010-777-1105', 'kba@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('061039', '안과', '도경수', 'M', '010-333-0112', 'dks@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('071023', '산부인과', '김광석', 'M', '010-777-0122', 'kks@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('081029', '방사선과', '김필', 'M', '010-888-0827', 'kp@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('090521', '외과', '김종현', 'M', '010-999-0408', 'kjh@hanbh.com', '과장');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('091513', '내과', '안지영', 'F', '010-111-0914', 'ajy@hanbh.com', '전문의');");
			stmt.executeUpdate(
					"INSERT INTO Doctors VALUES('090203', '소아과', '김준면', 'M', '010-444-0522', 'kjm@hanbh.com', '과장');");

			System.out.println("Doctors Data 입력 완료");

			// Nurses Table Data(nur_id, major_job, nur_name, nur_gen, nur_phone, nur_email,
			// nur_position)
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('050302', '소아과', '김은영', 'F', '010-555-8751', 'key@hanbh.com', '수간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('050021', '내과', '윤성애', 'F', '016-333-8745', 'ysa@hanbh.com', '수간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('040089', '피부과', '신지원', 'M', '010-666-7646', 'sjw@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('070605', '방사선과', '유정화', 'F', '010-333-4588', 'yjh@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('070804', '내과', '라하나', 'F', '010-222-1340', 'nhn@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('071018', '소아과', '김화경', 'F', '019-888-4116', 'khk@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('100356', '소아과', '이선용', 'M', '010-777-1234', 'lsy@hanbh.com', '간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('104145', '외과', '김현', 'M', '010-999-8520', 'kh@hanbh.com', '간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('120309', '피부과', '박성완', 'M', '010-777-4996', 'psw@hanbh.com', '간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('130211', '외과', '이서언', 'F', '010-222-3214', 'lsy2@hanbh.com', '간호사');");
			// new data
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('050710', '소아과', '김태연', 'F', '010-555-0309', 'kty@hanbh.com', '수간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('052031', '안과', '이수현', 'F', '010-111-0504', 'ish@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('041039', '방사선과', '배주현', 'F', '010-444-1120', 'pjh@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('071635', '산부인과', '조정석', 'M', '016-222-4342', 'jjs@hanbh.com', '수간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('071204', '산부인과', '전미도', 'F', '016-333-0327', 'jmd@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('071230', '내과', '박효신', 'M', '019-888-0901', 'phs@hanbh.com', '수간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('101232', '안과', '유연석', 'M', '010-222-5232', 'yys@hanbh.com', '간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('104110', '외과', '존노', 'M', '010-333-6332', 'jn@hanbh.com', '간호사');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('122013', '피부과', '유채훈', 'M', '010-222-4536', 'ych@hanbh.com', '주임');");
			stmt.executeUpdate(
					"INSERT INTO Nurses VALUES('131034', '내과', '황건하', 'M', '010-999-3458', 'hgh@hanbh.com', '간호사');");

			System.out.println("Nurses Data 입력 완료");

			// Patients Table Data(pat_id, nur_id, doc_id, pat_name, pat_gen, pat_jumin,
			// pat_addr, pat_phone, pat_email, pat_job)
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('2345', '050302', '980312', '안상건', 'M', '232345', '서울', '010-555-7845', 'ask@ab.com', '회사원');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('3545', '040089', '020403', '김성룡', 'M', '543545', '서울', '010-333-7812', 'ksr@bb.com', '자영업');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('3424', '070605', '080543', '이종진', 'M', '433424', '부산', '019-888-4859', 'ljj@ab.com', '회사원');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('7675', '100356', '050900', '최광석', 'M', '677675', '당진', '010-222-4847', 'cks@cc.com', '회사원');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('4533', '070804', '000601', '정한경', 'M', '744533', '강릉', '010-777-9630', 'jhk@ab.com', '교수');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('5546', '120309', '070576', '유원현', 'M', '765546', '대구', '016-777-0214', 'ywh@cc.com', '자영업');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('4543', '070804', '050101', '최재정', 'M', '454543', '부산', '010-555-4187', 'cjj@bb.com', '회사원');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('9768', '130211', '091001', '이진희', 'F', '119768', '서울', '010-888-3675', 'ljh@ab.com', '교수');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('4234', '130211', '091001', '오나미', 'F', '234234', '속초', '010-999-6541', 'onm@cc.com', '학생');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('7643', '071018', '062019', '송성묵', 'M', '987643', '서울', '010-222-5874', 'ssm@bb.com', '학생');");
			// new data
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('1539', '050710', '090203', '루피', 'M', '123221', '파주', '010-333-1645', 'lp@ab.com', '학생');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('2574', '052031', '061039', '나미', 'F', '342352', '서울', '010-111-6812', 'nm@bb.com', '자영업');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('3823', '104110', '090521', '상디', 'M', '311526', '장흥','019-222-2319', 'sd@ab.com', '요리사');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('7125', '101232', '061039', '조로', 'M', '404630', '충주','010-555-1847', 'jr@cc.com', '학생');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('3583', '071230', '050101', '쵸파', 'M', '204916', '강릉','010-666-9633', 'cp@ab.com', '교수');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('5036', '041039', '081029', '로빈', 'F', '820394', '대구','016-333-0312', 'rb@cc.com', '자영업');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('4103', '122013', '032034', '핸콕', 'F', '302962', '대구','010-777-4487', 'hk@bb.com', '교수');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('9168', '050710', '062019', '레일리', 'M', '134263', '서울','010-111-2675', 'lil@ab.com', '요리사');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('8044', '041039', '080543', '에이스', 'M', '534273', '서울','010-444-8541', 'ace@cc.com', '교수');");
			stmt.executeUpdate(
					"INSERT INTO Patients VALUES('6343', '104145', '091001', '사보', 'M', '922426', '파주','010-888-1874', 'sb@bb.com', '교수');");

			System.out.println("Patients Data 입력 완료");

			// Treatments Table Data(treat_id, pat_id, doc_id, treat_contents, treat_date)
			stmt.executeUpdate("INSERT INTO Treatments VALUES('130516023', '2345', '980312', '감기, 몸살', '2013-05-16');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('130628100', '3545', '020403', '피부 트러블 치료', '2013-06-28');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('131205056', '3424', '080543', '목 디스크로 MRI 촬영', '2013-12-05');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('131218024', '7675', '050900', '중이염', '2013-12-18');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('131224012', '4533', '000601', '장염', '2013-12-24');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('140103001', '5546', '070576', '여드름 치료', '2014-01-03');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('140109026', '4543', '050101', '위염', '2014-01-09');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('140226102', '9768', '091001', '화상치료', '2014-02-26');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('140303003', '4234', '091001', '교통사고 외상치료', '2014-03-03');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('140308087', '7643', '062019', '장염', '2014-03-08');");
			// new data
			stmt.executeUpdate("INSERT INTO Treatments VALUES('13102133', '1539', '090203', '감기', '2013-05-22');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('13020720', '2574', '061039', '결막염', '2013-07-18');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('13110206', '3823', '090521', '목 디스크로 MRI 촬영', '2013-10-20');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('13121324', '7125', '061039', '알러지', '2013-11-15');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('14021412', '3583', '050101', '위염', '2013-12-18');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('14030201', '5036', '081029', '허리 디스크 MRI 촬영', '2014-01-12');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('14010906', '4103', '032034', '여드름 치료', '2014-01-14');");
			stmt.executeUpdate("INSERT INTO Treatments VALUES('14062622', '9168', '062019', '장염', '2014-01-16');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('14080213', '8044', '080543', '무릎 MRI 촬영', '2014-02-17');");
			stmt.executeUpdate(
					"INSERT INTO Treatments VALUES('14100817', '6343', '091001', '교통사고 외상치료', '2014-09-11');");
			System.out.println("Treatments Data 입력 완료");

			// Charts Table Data(chart_id, treat_id, doc_id, pat_id, nur_id, chart_contents)
			stmt.executeUpdate("INSERT INTO Charts VALUES('A0125', '130516023', '980312', '2345', '050302', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1023', '130628100', '020403', '3545', '040089', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B0521', '131205056', '080543', '3424', '070605', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1235', '131218024', '050900', '7675', '100356', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1437', '131224012', '000601', '4533', '070804', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1745', '140103001', '070576', '5546', '120309', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A2125', '140109026', '050101', '4543', '070804', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B1527', '140226102', '091001', '9768', '130211', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B2551', '140303003', '091001', '4234', '130211', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A3512', '140308087', '062019', '7643', '071018', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A0243', '13102133', '090203', '1539', '050710', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1403', '13020720', '061039', '2574', '052031', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B0932', '13110206', '090521', '3823', '104110', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1034', '13121324', '061039', '7125', '101232', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A1930', '14021412', '050101', '3583', '071230', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B1928', '14030201', '081029', '5036', '041039', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A2640', '14010906', '032034', '4103', '122013', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('A3026', '14062622', '062019', '9168', '050710', '약 처방');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B3174', '14080213', '080543', '8044', '041039', '입원 치료');");
			stmt.executeUpdate("INSERT INTO Charts VALUES('B3202', '14100817', '091001', '6343', '104145', '입원 치료');");
			System.out.println("Charts Data 입력 완료");

			System.out.println("데이터 입력 성공!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 입력 실패!\n");
		}
	}

	void initDB() {
		String query;
		try {
			stmt = con.createStatement();

			deleteTable(); // 테이블 삭제
			createTable(); // 테이블 생성
			insertTableData(); // 기본 데이터 입력

			System.out.println("테이블 초기화 성공!\n");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("테이블 초기화 실패!\n");
		}
	}

	public void layInit() {
		btnD1 = new JButton("Doctors 조회");
		btnD2 = new JButton("Doctors 입력");
		btnN1 = new JButton("Nurses 조회");
		btnN2 = new JButton("Nurses 입력");
		btnP1 = new JButton("Patients 조회");
		btnP2 = new JButton("Patients 입력");
		btnT1 = new JButton("Treatments 조회");
		btnT2 = new JButton("Treatments 입력");
		btnC1 = new JButton("Charts 조회");
		btnC2 = new JButton("Charts 입력");
		btnS1 = new JButton("검색 1");
		btnS2 = new JButton("검색 2");
		btnRS = new JButton("전체 초기화");
		btnEx = new JButton("입력 예시");
		input = new JTextField(20);
		txtRes = new JTextArea();
		labInfo = new JLabel("데이터를 추가하고 싶으면 상단의 입력 예시 버튼을 통해 참고하세요.");

		pn1 = new JPanel();
		pn1.add(btnD1);
		pn1.add(btnN1);
		pn1.add(btnP1);
		pn1.add(btnT1);
		pn1.add(btnC1);
		pn1.add(btnS1);
		pn1.add(btnS2);
		pn1.add(btnRS);
		pn1.add(btnEx);
		add("North", pn1);

		txtRes.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(txtRes);
		add("Center", scrollPane);

		pn2 = new JPanel();
		pn2.setLayout(new BorderLayout());
		pn2.add("North", labInfo);
		pn2.add("Center", input);

		pn3 = new JPanel();
		pn3.add(btnD2);
		pn3.add(btnN2);
		pn3.add(btnP2);
		pn3.add(btnT2);
		pn3.add(btnC2);
		pn2.add("South", pn3);
		add("South", pn2);

		btnD1.addActionListener(this);
		btnD2.addActionListener(this);
		btnN1.addActionListener(this);
		btnN2.addActionListener(this);
		btnP1.addActionListener(this);
		btnP2.addActionListener(this);
		btnT1.addActionListener(this);
		btnT2.addActionListener(this);
		btnC1.addActionListener(this);
		btnC2.addActionListener(this);
		btnS1.addActionListener(this);
		btnS2.addActionListener(this);
		btnRS.addActionListener(this);
		btnEx.addActionListener(this);

	}

	public void conDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로드 성공\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try { /* 데이터베이스를 연결하는 과정 */
			System.out.println("데이터베이스 연결 준비...\n");
			con = DriverManager.getConnection(url, userid, pwd);
			System.out.println("데이터베이스 연결 성공!\n");
			initDB();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("데이터베이스 연결 실패!\n");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			stmt = con.createStatement();
			String query;
			// SELECT
			if (e.getSource() == btnD1) {
				query = "SELECT * FROM Doctors;";
				txtRes.setText("");
				txtRes.setText("num\tdoc_id\tmajor_treat\tdoc_name\tdoc_gen\tdoc_phone\t\tdoc_email\t\tdoc_position\n");
				rs = stmt.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					String str = i + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
							+ "\t" + rs.getString(5) + "\t\t" + rs.getString(6) + "\t" + rs.getString(7) + "\n";
					txtRes.append(str);
					i++;
				}
			} else if (e.getSource() == btnN1) {
				query = "SELECT * FROM Nurses;";
				txtRes.setText("");
				txtRes.setText("num\tnur_id\tmajor_job\tnur_name\tnur_gen\tnur_phone\t\tnur_email\t\tnur_position\n");
				rs = stmt.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					String str = i + "\t" + rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
							+ "\t" + rs.getString(5) + "\t\t" + rs.getString(6) + "\t" + rs.getString(7) + "\n";
					txtRes.append(str);
					i++;
				}
			} else if (e.getSource() == btnP1) {
				query = "SELECT * FROM Patients;";
				txtRes.setText("");
				txtRes.setText(
						"num\tpat_id\tnur_id\tdoc_id\tpat_name\tpat_gen\tpat_jumin\tpat_addr\tpat_phone\t\tpat_email\tpat_job\n");
				rs = stmt.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					String str = i + "\t" + rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4)
							+ "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getString(7) + "\t"
							+ rs.getString(8) + "\t\t" + rs.getString(9) + "\t" + rs.getString(10) + "\n";
					txtRes.append(str);
					i++;
				}
			} else if (e.getSource() == btnT1) {
				query = "SELECT * FROM Treatments;";
				txtRes.setText("");
				txtRes.setText("num\ttreat_id\tpat_id\tdoc_id\ttreat_contents\t\treat_date\n");
				rs = stmt.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					String str = i + "\t" + rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4)
							+ "\t\t" + rs.getString(5) + "\n";
					txtRes.append(str);
					i++;
				}
			} else if (e.getSource() == btnC1) {
				query = "SELECT * FROM Charts;";
				txtRes.setText("");
				txtRes.setText("num\tchart_id\ttreat_id\tdoc_id\tpat_id\tnur_id\tchart_contents\n");
				rs = stmt.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					String str = i + "\t" + rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
							+ "\t" + rs.getInt(5) + "\t" + rs.getString(6) + "\n";
					txtRes.append(str);
					i++;
				}
			}
			// Other SELECT
			else if (e.getSource() == btnS1) {
				query = "SELECT DISTINCT Nurses.major_job AS MAJOR, count(Nurses.major_job) AS NUM "
						+ "FROM Nurses WHERE EXISTS (SELECT Doctors.major_treat FROM Doctors) "
						+ "GROUP BY Nurses.major_job ORDER BY NUM DESC;";
				txtRes.setText("");
				txtRes.setText("#의사의 전공 별로 존재하는 간호사의 인원 수 (내림차순)\n\n");
				txtRes.append("SELECT DISTINCT Nurses.major_job AS MAJOR, count(Nurses.major_job) AS NUM\n"
						+ "FROM Nurses WHERE EXISTS (SELECT Doctors.major_treat FROM Doctors)\n"
						+ "GROUP BY Nurses.major_job ORDER BY NUM DESC;\n\n");
				txtRes.append("MAJOR\tNUM\n");
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String str = rs.getString(1) + "\t" + rs.getInt(2) + "\n";
					txtRes.append(str);
				}
			} else if (e.getSource() == btnS2) {
				query = "SELECT DATE_FORMAT(Treatments.treat_date, '%Y') AS TREAT_YEAR, count(Treatments.pat_id) AS NUM "
						+ "FROM Treatments, Patients WHERE Treatments.pat_id = Patients.pat_id AND Patients.pat_gen = 'M' "
						+ "AND Treatments.doc_id IN (SELECT doc_id FROM Doctors WHERE doc_gen = 'M') "
						+ "GROUP BY DATE_FORMAT(Treatments.treat_date, '%Y');";
				txtRes.setText("");
				txtRes.setText("#연도별 남자 의사에게 남자 환자가 진료 받은 수\n\n");
				txtRes.append("SELECT DATE_FORMAT(Treatments.treat_date, '%Y') AS TREAT_YEAR, count(Treatments.pat_id) AS NUM\n"
							+ "FROM Treatments, Patients\nWHERE Treatments.pat_id = Patients.pat_id AND Patients.pat_gen = 'M'\n"
							+ "AND Treatments.doc_id IN (SELECT doc_id FROM Doctors WHERE doc_gen = 'M')\n"
							+ "GROUP BY DATE_FORMAT(Treatments.treat_date, '%Y');\n\n");
				txtRes.append("YEAR\tNUm\t\n");
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					String str = rs.getString(1) + "\t" + rs.getInt(2) + "\n";
					txtRes.append(str);
				}
			}
			
			// Reset
			else if (e.getSource() == btnRS) {
				txtRes.setText("");
				JOptionPane.showMessageDialog(null, "초기화 시작!");
				initDB();
				JOptionPane.showMessageDialog(null, "초기화 완료!");
			}
			
			// INSERT 안내
			else if (e.getSource() == btnEx) {
				txtRes.setText("");
				txtRes.setText("#입력 예시처럼 입력 후 해당 테이블 입력 버튼을 누르세요.\n#입력 조건이 틀렸거나, 해당 데이터가 틀린 경우 데이터 추가는 불가능합니다.\n");
				txtRes.append("\n[TABLE 데이터 추가 방법 ]\n\n");
				txtRes.append("Doctors Table 입력 예시\n");
				txtRes.append("doc_id(INT) / major_treat(CHAR) / doc_name(CHAR) / doc_gen(CHAR) / doc_phone(CHAR) / doc_email(CHAR) / doc_position(CHAR)\n");
				txtRes.append("980312/소아과/이태정/M/010-333-1340/ltj@hanbh.com/과장\n");
				txtRes.append("\nNurses Table 입력 예시\n");
				txtRes.append("nur_id(INT) / major_job(CHAR) / nur_name(CHAR) / nur_gen(CHAR) / nur_phone(CHAR) / nur_email(CHAR) / nur_position(CHAR)\n");
				txtRes.append("050302/소아과/김은영/F/010-555-8751/key@hanbh.com/수간호사\n");
				txtRes.append("\nPatients Table 입력 예시\n");
				txtRes.append("pat_id(INT) / nur_id(INT) / doc_id(INT) / pat_name(CHAR) / pat_gen(CHAR) / "
						+ "pat_jumin(CHAR) / pat_addr(CHAR) / pat_phone(CHAR) / pat_email(CHAR) / pat_job(CHAR)\n");
				txtRes.append("2345/050302/980312/안상건/M/232345/서울/010-555-7845/ask@ab.com/회사원\n");
				txtRes.append("\nTreatmets Table 입력 예시\n");
				txtRes.append("treat_id(INT) / pat_id(INT) / doc_id(INT) / treat_content(CHAR) / treat_date(DATE)\n");
				txtRes.append("14080213/8044/080543/무릎 MRI 촬영/2014-02-17\n");
				txtRes.append("\nCharts Table 입력 예시\n");
				txtRes.append("chart_id(CHAR) / treat_id(INT) / doc_id(INT) / pat_id(INT) / nur_id(INT) / chart_contents(CHAR)\n");
				txtRes.append("A0125/130516023/980312/2345/050302/약 처방\n");
			}

			// INSERT
			else if (e.getSource() == btnD2) {
				txtRes.setText("");
				txtRes.append("Doctors Table 입력 예시\n");
				txtRes.append("doc_id(INT) / major_treat(CHAR) / doc_name(CHAR) / doc_gen(CHAR) / doc_phone(CHAR) / doc_email(CHAR) / doc_position(CHAR)\n");
				txtRes.append("980312/소아과/이태정/M/010-333-1340/ltj@hanbh.com/과장\n");
				String[] val = input.getText().split("/");
				if (val.length < 6 || val.length > 7) {
					JOptionPane.showMessageDialog(null, "예시에 맞춰서 데이터를 입력해주세요!\n");
					System.out.println("알맞은 데이터를 입력해주세요.\n");
				} else if (val.length == 6 || val.length == 7) {
					int doc_id = 0;
					String major_treat = null, doc_name = null, doc_gen = null, doc_phone = null, doc_email = null,
							doc_position = null;
					doc_id = Integer.parseInt(val[0]);
					major_treat = val[1];
					doc_name = val[2];
					doc_gen = val[3];
					doc_phone = val[4];
					doc_email = val[5];
					doc_position = val[6];
					try {
						if(doc_gen.charAt(0) == 'F' || doc_gen.charAt(0) == 'M') {
						query = "INSERT INTO Doctors VALUES ('" + doc_id + "', '" + major_treat + "', '" + doc_name
								+ "', '" + doc_gen + "', '" + doc_phone + "', '" + doc_email + "', '" + doc_position
								+ "');";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "데이터가 추가되었습니다.!\n");
						}
						else {
							JOptionPane.showMessageDialog(null, "성별 입력이 잘못 되었습니다.");
							}
					} catch (SQLException e5) {
						e5.printStackTrace();
						JOptionPane.showMessageDialog(null, "알맞은 데이터 입력을 해야합니다.");
						System.out.println("데이터 입력 실패!\n");
					}
				}
			} else if (e.getSource() == btnN2) {
				txtRes.setText("");
				txtRes.append("Nurses Table 입력 예시\n");
				txtRes.append("nur_id(INT) / major_job(CHAR) / nur_name(CHAR) / nur_gen(CHAR) / nur_phone(CHAR) / nur_email(CHAR) / nur_position(CHAR)\n");
				txtRes.append("050302/소아과/김은영/F/010-555-8751/key@hanbh.com/수간호사\n");
				String[] val = input.getText().split("/");
				if (val.length < 6 || val.length > 7) {
					JOptionPane.showMessageDialog(null, "예시에 맞춰서 데이터를 입력해주세요!\n");
					System.out.println("알맞은 데이터를 입력해주세요.\n");
				} else if (val.length == 6 || val.length == 7) {
					int nur_id = 0;
					String major_job = null, nur_name = null, nur_gen = null, nur_phone = null, nur_email = null,
							nur_position = null;
					nur_id = Integer.parseInt(val[0]);
					major_job = val[1];
					nur_name = val[2];
					nur_gen = val[3];
					nur_phone = val[4];
					nur_email = val[5];
					nur_position = val[6];
					try {
						if(nur_gen.charAt(0) == 'F' || nur_gen.charAt(0) == 'M') {
						query = "INSERT INTO Nurses VALUES ('" + nur_id + "', '" + major_job + "', '" + nur_name
								+ "', '" + nur_gen + "', '" + nur_phone + "', '" + nur_email + "', '" + nur_position
								+ "');";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "데이터가 추가되었습니다.!\n");
						}
						else {
							JOptionPane.showMessageDialog(null, "성별 입력이 잘못 되었습니다.");
							}
					} catch (SQLException e5) {
						e5.printStackTrace();
						JOptionPane.showMessageDialog(null, "알맞은 데이터 입력을 해야합니다.");
						System.out.println("데이터 입력 실패!\n");
					}
				}
			} else if (e.getSource() == btnP2) {
				txtRes.setText("");
				txtRes.append("Patients Table 입력 예시\n");
				txtRes.append("pat_id(INT) / nur_id(INT) / doc_id(INT) / pat_name(CHAR) / pat_gen(CHAR) / "
						+ "pat_jumin(CHAR) / pat_addr(CHAR) / pat_phone(CHAR) / pat_email(CHAR) / pat_job(CHAR)\n");
				txtRes.append("2345/050302/980312/안상건/M/232345/서울/010-555-7845/ask@ab.com/회사원\n");
				String[] val = input.getText().split("/");
				if (val.length < 9 || val.length > 10) {
					JOptionPane.showMessageDialog(null, "예시에 맞춰서 데이터를 입력해주세요!\n");
					System.out.println("알맞은 데이터를 입력해주세요.\n");
				} else if (val.length == 9 || val.length == 10) {
					int pat_id = 0, nur_id = 0, doc_id = 0;
					String pat_name = null, pat_gen = null, pat_jumin = null, pat_addr = null, pat_phone = null,
							pat_email = null, pat_job = null;
					pat_id = Integer.parseInt(val[0]);
					nur_id = Integer.parseInt(val[1]);
					doc_id = Integer.parseInt(val[2]);
					pat_name = val[3];
					pat_gen = val[4];
					pat_jumin = val[5];
					pat_addr = val[6];
					pat_phone = val[7];
					pat_email = val[8];
					pat_job = val[9];
					try {
						if(pat_gen.charAt(0) == 'F' || pat_gen.charAt(0) == 'M') {
						query = "INSERT INTO Patients VALUES ('" + pat_id + "', '" + nur_id + "', '" + doc_id + "', '"
								+ pat_name + "', '" + pat_gen + "', '" + pat_jumin + "', '" + pat_addr + "', '"
								+ pat_phone + "', '" + pat_email + "', '" + pat_job + "');";
						stmt.executeUpdate(query);
						}
						else {
							JOptionPane.showMessageDialog(null, "성별 입력이 잘못 되었습니다.");
							}
					} catch (SQLException e5) {
						e5.printStackTrace();
						JOptionPane.showMessageDialog(null, "알맞은 데이터 입력을 해야합니다.");
						System.out.println("데이터 입력 실패!\n");
					}
				}
			} else if (e.getSource() == btnT2) {
				txtRes.setText("");
				txtRes.append("Treatments Table 입력 예시\n");
				txtRes.append("treat_id(INT) / pat_id(INT) / doc_id(INT) / treat_content(CHAR) / treat_date(DATE)\n");
				txtRes.append("14080213/8044/080543/무릎 MRI 촬영/2014-02-17\n");
				String[] val = input.getText().split("/");
				if (val.length < 5 || val.length > 5) {
					JOptionPane.showMessageDialog(null, "예시에 맞춰서 데이터를 입력해주세요!\n");
					System.out.println("알맞은 데이터를 입력해주세요.\n");
				} else if (val.length == 5) {
					int treat_id = 0, pat_id = 0, doc_id = 0;
					String treat_contents = null, treat_date = null;
					treat_id = Integer.parseInt(val[0]);
					pat_id = Integer.parseInt(val[1]);
					doc_id = Integer.parseInt(val[2]);
					treat_contents = val[3];
					treat_date = val[4];
					try {
						query = "INSERT INTO Treatments VALUES ('" + treat_id + "', '" + pat_id + "', '" + doc_id + "', '"
								+ treat_contents + "', '" + treat_date + "');";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "데이터가 추가되었습니다.!\n");
					} catch (SQLException e5) {
						e5.printStackTrace();
						JOptionPane.showMessageDialog(null, "알맞은 데이터 입력을 해야합니다.");
						System.out.println("데이터 입력 실패!\n");
					}
				}
			} else if (e.getSource() == btnC2) {
				txtRes.setText("");
				txtRes.append("Charts Table 입력 예시\n");
				txtRes.append("chart_id(CHAR) / treat_id(INT) / doc_id(INT) / pat_id(INT) / nur_id(INT) / chart_contents(CHAR)\n");
				txtRes.append("A0125/130516023/980312/2345/050302/약 처방\n");
				String[] val = input.getText().split("/");
				if (val.length < 6 || val.length > 6) {
					JOptionPane.showMessageDialog(null, "예시에 맞춰서 데이터를 입력해주세요!\n");
					System.out.println("알맞은 데이터를 입력해주세요.\n");
				} else if (val.length == 6) {
					int treat_id = 0, doc_id = 0, pat_id = 0, nur_id = 0;
					String chart_id = null, chart_contents = null;
					chart_id = val[0];
					treat_id = Integer.parseInt(val[1]);
					doc_id = Integer.parseInt(val[2]);
					pat_id = Integer.parseInt(val[3]);
					nur_id = Integer.parseInt(val[4]);
					chart_contents = val[5];
					try {
						query = "INSERT INTO Charts VALUES ('" + chart_id + "', '" + treat_id + "', '" + doc_id
								+ "', '" + pat_id + "', '" + nur_id + "', '" + chart_contents + "');";
						stmt.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "데이터가 추가되었습니다.!\n");
					} catch (SQLException e5) {
						e5.printStackTrace();
						JOptionPane.showMessageDialog(null, "알맞은 데이터 입력을 해야합니다.");
						System.out.println("데이터 입력 실패!\n");
					}
				}
			}

		} catch (Exception e2) {
			System.out.println("쿼리 읽기 실패 :" + e2);
		}

	}

	public static void main(String[] args) {
		HospitalData HD = new HospitalData();
		// HD.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// HD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HD.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					con.close();
				} catch (Exception e4) {
				}
				System.out.println("프로그램 완전 종료!\n");
				System.exit(0);
			}
		});
	}
}
