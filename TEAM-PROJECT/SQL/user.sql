#대여 가능한 캠핑카 기본정보 검색
SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y';

#대여 가능한 캠핑카의 세부정보 검색
SELECT * FROM CampingCarDetails
WHERE EXISTS ( SELECT *
				FROM CampingCarBasicInfo
				WHERE CampingCarBasicInfo.carPossibleRent = 'Y'
                AND CampingCarBasicInfo.carRegistId = CampingCarDetails.carRegistId );

#차량 정비가 이루어진 정비소들 정보
SELECT * FROM WorkShopInfo WHERE EXISTS (SELECT * FROM CarMaintenanceHistory WHERE CarMaintenanceHistory.workShopId = WorkShopInfo.workShopId);

#캠핑카 정비 많이한 순으로 고객 정보 검색
SELECT count(CarMaintenanceHistory.maintenanceNum) AS Fix, CarMaintenanceHistory.custLicenseNum, CustomerInfo.custName
FROM CarMaintenanceHistory, CustomerInfo
WHERE CustomerInfo.custLicenseNum = CarMaintenanceHistory.custLicenseNUm GROUP BY custLicenseNum ORDER BY FIX DESC;

#대여 가능한 캠핑카의 대여 회사 정보 조회
SELECT * FROM CampingCarCompany WHERE EXISTS (SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y' AND CampingCarBasicInfo.rentCoId = CampingCarCompany.rentCoId );

#차량 정비가 이루어진 캠핑카 이름, 캠핑카 등록일, 대여가능
SELECT DISTINCT (CampingCarBasicInfo.carName), CampingCarBasicInfo.carRegistDate, CampingCarBasicInfo.carPossibleRent
FROM CampingCarBasicInfo, CarMaintenanceHistory
WHERE CarMaintenanceHistory.carRegistId = CampingCarBasicInfo.carRegistId AND CampingCarBasicInfo.rentCoId = CarMaintenanceHistory.rentCoId;
INSERT INTO CampingCarCompany VALUES ('16', 'taxi', 'addr', '02-1111-1111', 'hj', 'Doby1@gmalc.ocm');
INSERT INTO CarInspectionList VALUES (')