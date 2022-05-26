/* Student Name: Kendrick Kee */
/* Student Number: 7366814 */
create 'SOLUTION1','EMPLOYEE', 'DEPARTMENT' , 'PROJECT'






put 'SOLUTION1' , 'project|312' , 'PROJECT:code', '312'
put 'SOLUTION1' , 'project|312' , 'PROJECT:title', 'DBMS project'
put 'SOLUTION1' , 'project|312' , 'PROJECT:salary' , '8000'

put 'SOLUTION1' , 'project|314' , 'PROJECT:code', '314'
put 'SOLUTION1' , 'project|314' , 'PROJECT:title', 'Azure project'
put 'SOLUTION1' , 'project|314' , 'PROJECT:salary' , '7500'

put 'SOLUTION1' , 'employee|312|ICT|001', 'EMPLOYEE:enumber', '001'
put 'SOLUTION1' , 'employee|312|ICT|001', 'EMPLOYEE:first-name', 'Kendrick'
put 'SOLUTION1' , 'employee|312|ICT|001', 'EMPLOYEE:last-name', 'Kee'
put 'SOLUTION1' , 'employee|312|ICT|001', 'EMPLOYEE:salary', '67000'
put 'SOLUTION1' , 'employee|312|ICT|001', 'PROJECT:code' , '312'
put 'SOLUTION1' , 'employee|312|ICT|001', 'DEPARTMENT:name' , 'Infocom Technology'

put 'SOLUTION1' , 'employee|314|ICT|002', 'EMPLOYEE:enumber', '002'
put 'SOLUTION1' , 'employee|314|ICT|002', 'EMPLOYEE:first-name', 'John'
put 'SOLUTION1' , 'employee|314|ICT|002', 'EMPLOYEE:last-name', 'Smith'
put 'SOLUTION1' , 'employee|314|ICT|002', 'EMPLOYEE:salary', '100000'
put 'SOLUTION1' , 'employee|314|ICT|002', 'PROJECT:code' , '314'
put 'SOLUTION1' , 'employee|314|ICT|002', 'DEPARTMENT:name' , 'Infocom Technology'

put 'SOLUTION1' , 'employee|314|ICT|003', 'EMPLOYEE:enumber', '003'
put 'SOLUTION1' , 'employee|314|ICT|003', 'EMPLOYEE:first-name', 'Adam'
put 'SOLUTION1' , 'employee|314|ICT|003', 'EMPLOYEE:last-name', 'Swanson'
put 'SOLUTION1' , 'employee|314|ICT|003', 'EMPLOYEE:salary', '1000000'
put 'SOLUTION1' , 'employee|314|ICT|003', 'PROJECT:code' , '314'
put 'SOLUTION1' , 'employee|314|ICT|003', 'DEPARTMENT:name' , 'Infocom Technology'


put 'SOLUTION1' , 'department|003|ICT', 'DEPARTMENT:name', 'Infocom Technology'
put 'SOLUTION1' , 'department|003|ICT', 'DEPARTMENT:budget', '1000000'
put 'SOLUTION1' , 'department|003|ICT', 'EMPLOYEE:enumber' , '003'



describe 'SOLUTION1'

scan 'SOLUTION1'

disable 'SOLUTION1'

drop 'SOLUTION1'
