#Kendrick Kee
#7366814

#Task 1
#find all information about a student number 007

get 'task2' , 'student|007', {COLUMN=>'STUDENT',VERSIONS=>1}

#Task 2
#find all information about a submission of assignment 1 performed by a student 006 in a subject 312

get 'task2' , 'submission|007|312|assignment|1', {COLUMNS=>['SUBMISSION','STUDENT','SUBJECT','FILES'],VERSIONS=>1}

#Task 3
#Find the first and last names of all students

scan 'task2', {COLUMNS=>['STUDENT:fname','STUDENT:lname'], VERSIONS=>1}

#Task 4
#Find all information about a student whose lastname is Potter, list one version per cell.

scan 'task2' ,{COLUMNS=>['STUDENT'],FILTER=>"SingleColumnValueFilter('STUDENT','lname',=,'binary:Potter',true,false)"}


#Task 5
#Delete a column family FILES.

alter 'task2' , 'delete' => 'FILES'


#Task 6
#Add a column family ENROLMENT that contains information about dates when the subjects have been enrolled by the students and allow for 2 versions in each cell of the column family.
alter 'task2' , {NAME => 'ENROLMENT' , VERSIONS=>2}

#Task 7
#Insert information about at least two enrolments performed by the students

put 'task2' , 'enrolment|007|312' , 'ENROLMENT:enrolled-on' , '31/12/2021'
put 'task2' , 'enrolment|666|314' , 'ENROLMENT:enrolled-on' , '30/11/2021'

#Task 8
#List information about all enrolments performed by the students.

scan 'task2' , {COLUMN => 'ENROLMENT'}

#Task 9
#Increase the total number of versions in each cell of a column family ENROLMENT
alter 'task2' , {NAME => 'ENROLMENT' , VERSIONS=>5}

#Task 10
#Delete HBase table task2
disable 'task2'
drop 'task2'
