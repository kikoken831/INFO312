create 'task1', 'APPLICATION','EMPLOYER','APPLICANT','POSITION'

put 'task1','employer|SIM','EMPLOYER:ename','Singapore Institute of Management'
put 'task1','employer|SIM','EMPLOYER:phone','1234567890'

put 'task1','employer|UOW','EMPLOYER:ename','University of Wollongong'
put 'task1','employer|UOW','EMPLOYER:Phone','0987654321'

put 'task1','position|312','POSITION:position-number','312'
put 'task1','position|312','POSITION:title','Big Data Manager'
put 'task1','position|312','POSITION:salary','600000'

put 'task1','position|313','POSITION:position-number','313'
put 'task1','position|313','POSITION:title','Very Big Data Manager'
put 'task1','position|313','POSITION:salary','700000'

put 'task1','applicant|312','APPLICANT:number','007'
put 'task1','applicant|312','APPLICANT:full-name','James Bond'
put 'task1','applicant|312','APPLICANT:date-of-birth','01-01-1960'

put 'task1','applicant|313','APPLICANT:number','666'
put 'task1','applicant|313','APPLICANT:full-name','Very Big Data'
put 'task1','applicant|313','APPLICANT:date-of-birth','01-01-1999'

put 'task1','application|007|312|SIM|01','APPLICATION:application-number','01'
put 'task1','application|007|312|SIM|01','APPLICATION:total-skills','5'
put 'task1','application|007|312|SIM|01','APPLICATION:education-level','high'
put 'task1','application|007|312|SIM|01','POSITION:position-number','312'
put 'task1','application|007|312|SIM|01','APPLICANT:number','007'
put 'task1','application|007|312|SIM|01','EMPLOYER:ename','SIM'

put 'task1','application|666|312|SIM|02','APPLICATION:application-number','02'
put 'task1','application|666|312|SIM|02','APPLICATION:total-skills','5'
put 'task1','application|666|312|SIM|02','APPLICATION:education-level','low'
put 'task1','application|666|312|SIM|02','POSITION:position-number','312'
put 'task1','application|666|312|SIM|02','APPLICANT:number','666'
put 'task1','application|666|312|SIM|02','EMPLOYER:ename','SIM'

put 'task1','application|007|313|UOW|03','APPLICATION:application-number','01'
put 'task1','application|007|313|UOW|03','APPLICATION:total-skills','5'
put 'task1','application|007|313|UOW|03','APPLICATION:education-level','high'
put 'task1','application|007|313|UOW|03','POSITION:position-number','313'
put 'task1','application|007|313|UOW|03','APPLICANT:number','007'
put 'task1','application|007|313|UOW|03','EMPLOYER:ename','UOW'


get 'task1','position|312',{COLUMN=>'POSITION',VERSIONS=>1}


get 'task1','application|007|312|SIM|01',{COLUMN=>['APPLICATION','EMPLOYER','APPLICANT','POSITION'],VERSIONS=>1}


alter 'task1', 'delete' => 'EMPLOYER'


put 'task1','position|313','POSITION:total-number-of-applications','2'
put 'task1','position|312','POSITION:total-number-of-applications','1'


alter 'task1', {NAME=>'APPLICANT', VERSIONS=>'3'}



desc 'task1'

scan 'task1'

disable 'task1'

drop 'task1'
