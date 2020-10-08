SELECT * FROM Doctors;
SELECT * FROM Nurses;
SELECT * FROM Patients;
SELECT * FROM Treatments;
SELECT * FROM Charts;

SELECT DATE_FORMAT(Treatments.treat_date, '%Y') AS TREAT_YEAR, count(Treatments.pat_id) AS NUM 
FROM Patients, Treatments, Doctors 
WHERE Treatments.pat_id = Patients.pat_id AND Patients.pat_gen = 'M' 
AND Doctors.doc_gen = 'M' AND Doctors.doc_id = Treatments.doc_id 
GROUP BY DATE_FORMAT(Treatments.treat_date, '%Y');

SELECT DATE_FORMAT(Treatments.treat_date, '%Y') AS TREAT_YEAR, count(Treatments.pat_id) AS NUM 
FROM Treatments, Patients
WHERE Treatments.pat_id = Patients.pat_id AND Patients.pat_gen = 'M'
AND Treatments.doc_id IN (SELECT doc_id FROM Doctors WHERE doc_gen = 'M')
GROUP BY DATE_FORMAT(Treatments.treat_date, '%Y');

#남자 의사에게 진료 받은 여자 환자 중 contents 별 환자의 수
select treatments.treat_contents , count(patients.pat_id) As cnt
from  patients, (Select doc_id, doc_gen FROM doctors where doc_gen='M') d , treatments
where patients.doc_id = d.doc_id 
And patients.pat_gen = 'F' and patients.pat_id = treatments.pat_id
group by treatments.treat_contents order by cnt desc;