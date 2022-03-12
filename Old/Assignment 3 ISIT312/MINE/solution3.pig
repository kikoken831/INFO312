accounts = load '/user/bigdata/account.tbl' using PigStorage('|') as
(account_number:long,bank_name:chararray,balance:float,account_type:chararray);

banks = load '/user/bigdata/bank.tbl' using PigStorage('|') as
(bank_name:chararray,hq_country:chararray,hq_city:chararray,hq_street:chararray,hq_building_number:int,website:chararray,email:chararray,bank_type:chararray);

transactions = load '/user/bigdata/transaction.tbl' using PigStorage('|') as
(account_number:long,bank_name:chararray,date_time:chararray,amount:float,transaction_type:chararray);


construction = filter banks by bank_type == 'construction';
accountnum = join construction by bank_name, accounts by bank_name;
results = foreach accountnum generate account_number;
dump results;


joinleftouter = join banks by bank_name left outer, accounts by bank_name;
bankswithoutacc = filter joinleftouter by account_number is null;
results = foreach bankswithoutacc generate banks::bank_name;
dump results;


japan = filter banks by hq_country == 'Japan';
jointoacc = join japan by bank_name, accounts by bank_name;
groupedjointoacc = group jointoacc all;
results = foreach groupedjointoacc generate COUNT(jointoacc);
dump results;


alltransactions = foreach transactions generate account_number;
results = DISTINCT alltransactions;
dump results;


deposits = filter transactions by transaction_type == 'deposit';
results = foreach deposits generate account_number;
disresults = DISTINCT results;
dump disresults;
