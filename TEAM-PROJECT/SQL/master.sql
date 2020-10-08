#수리필요한 캠핑카 고유정비번호, 캠핑카등록ID, 고객운전면허증번호, 캠핑카대여회사ID 조회
SELECT CarInspectionList.rentNum, CarInspectionList.carRegistId, CarRentalBasicInfo.custLicenseNum, CarRentalBasicInfo.rentCoId
FROM CarRentalBasicInfo, CarInspectionList
WHERE CarInspectionList.repairRequired = 'N'
		AND CarInspectionList.carRegistId = CarRentalBasicInfo.carRegistId
        AND CarInspectionList.rentNum = CarRentalBasicInfo.rentNum;

#캠핑카를 대여한 적 없는 고객의 이름 검색
SELECT DISTINCT CustomerInfo.custName
FROM CustomerInfo, CarRentalBasicInfo
WHERE CustomerInfo.custLicenseNum = CarRentalBasicInfo.custLicenseNum;

#캠핑카회사 추가
INSERT INTO CampingCarCompany VALUES ('16', 'taxi', 'addr', '02-1111-1111', 'hj', 'Doby1@gmalc.ocm');

#캠핑카회사 변경
UPDATE CampingCarCompany SET coName = '릴리', coAddr = '제임스', coDigit = '02-1111-2222', coManagerName = '포터', coManagerEmail = 'Doby16@isFree.com' WHERE rentCoId = 16; 

#캠핑카회사 삭제
DELETE FROM CampingCarCompany
WHERE coName = '릴리' AND coDigit = '02-1111-2222' AND coManagerName = '포터';

UPDATE CampingCarBasicInfo SET rentCoId = '1', carName = 'sfs3', carSeatNum = '3', rentPrice = '500', carRegistDate = '2020-01-11' WHERE carRegistId = ' 16';

SELECT * FROM CampingCarBasicInfo WHERE CampingCarBasicInfo.carPossibleRent = 'Y';
