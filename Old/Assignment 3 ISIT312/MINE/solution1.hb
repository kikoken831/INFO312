create 'MAINTENANCE','OWNER','VEHICLE','REPAIR','TIME'

put 'MAINTENANCE','owner:007','OWNER:licence-number','007'
put 'MAINTENANCE','owner:007','OWNER:first-name','James'
put 'MAINTENANCE','owner:007','OWNER:last-name','Bond'
put 'MAINTENANCE','owner:007','OWNER:phone','007007007'

put 'MAINTENANCE','vehicle:MI6-007|007','VEHICLE:registration','MI6-007'
put 'MAINTENANCE','vehicle:MI6-007|007','VEHICLE:make','Jaguar'
put 'MAINTENANCE','vehicle:MI6-007|007','VEHICLE:model','F-Pace'
put 'MAINTENANCE','vehicle:MI6-007|007','OWNER:licence-number','007'

put 'MAINTENANCE','vehicle:ABC-123|007','VEHICLE:registration','ABC-123'
put 'MAINTENANCE','vehicle:ABC-123|007','VEHICLE:make','Aston Martin'
put 'MAINTENANCE','vehicle:ABC-123|007','VEHICLE:model','DB5'
put 'MAINTENANCE','vehicle:ABC-123|007','OWNER:licence-number','007'

put 'MAINTENANCE','repair:MI6-007','REPAIR:labour-cost','1000000'
put 'MAINTENANCE','repair:MI6-007','REPAIR:parts-cost','2000000'
put 'MAINTENANCE','repair:MI6-007','REPAIR:complexity-level','7'
put 'MAINTENANCE','repair:MI6-007','VEHICLE:registration','MI6-007'
put 'MAINTENANCE','repair:MI6-007','TIME:start-day','17'
put 'MAINTENANCE','repair:MI6-007','TIME:start-month','Oct'
put 'MAINTENANCE','repair:MI6-007','TIME:start-year','2020'
put 'MAINTENANCE','repair:MI6-007','TIME:end-day','10'
put 'MAINTENANCE','repair:MI6-007','TIME:end-month','Jul'
put 'MAINTENANCE','repair:MI6-007','TIME:end-year','2021'

put 'MAINTENANCE','repair:ABC-123','REPAIR:labour-cost','3000000'
put 'MAINTENANCE','repair:ABC-123','REPAIR:parts-cost','2500000'
put 'MAINTENANCE','repair:ABC-123','REPAIR:complexity-level','8'
put 'MAINTENANCE','repair:ABC-123','VEHICLE:registration','ABC-123'
put 'MAINTENANCE','repair:ABC-123','TIME:start-day','14'
put 'MAINTENANCE','repair:ABC-123','TIME:start-month','Nov'
put 'MAINTENANCE','repair:ABC-123','TIME:start-year','2021'
put 'MAINTENANCE','repair:ABC-123','TIME:end-day','15'
put 'MAINTENANCE','repair:ABC-123','TIME:end-month','Nov'
put 'MAINTENANCE','repair:ABC-123','TIME:end-year','2021'

desc 'MAINTENANCE'

scan 'MAINTENANCE'

disable 'MAINTENANCE'

drop 'MAINTENANCE'
