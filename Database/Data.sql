use RestaurantAuto

--Staff
insert into StaffRole(StaffRole) values ('Manager');
insert into StaffRole(StaffRole) values ('Host');
insert into StaffRole(StaffRole) values ('Waiter');
insert into StaffRole(StaffRole) values ('Busboy');
insert into StaffRole(StaffRole) values ('Cook');
insert into StaffRole(StaffRole) values ('Casher');
select * from StaffRole

insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('MA001','Tung','Nui','1976-12-1','Q1, tpHCM','M',1000,1,null)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('HO001','Vi','Deo','1990-1-6','Q4, tpHCM','F',300,2,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('HO002','Vi','Na Miu','1989-5-3','Q5, tpHCM','F',300,2,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('WA001','Dung','Chi To','1986-2-1','Q6, tpHCM','M',400,3,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('WA002','Du','Cu Tham','1987-10-1','Q5, tpHCM','M',400,3,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('BU001','Dac','Mit','1991-1-1','Q5, tpHCM','M',300,4,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('BU002','Bu','Tan','1992-4-1','Q4, tpHCM','M',300,4,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('CO001','Mat','To','1980-8-5','Q2, tpHCM','M',800,5,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('CO002','Su','Po','1984-3-15','Q7, tpHCM','M',1000,5,1)
insert into Staff(Id,FristName,LastName,DOB,Addr,Sex,Salary,StaffRoleID,LeadSEQ) values ('CA001','Thu','Ngan','1990-3-5','Q3, tpHCM','F',300,12,1)
select * from Staff

--Food
insert into FoodType(FoodType) values (N'Khai vị')
insert into FoodType(FoodType) values (N'Chính')
insert into FoodType(FoodType) values (N'Tráng miệng')
insert into FoodType(FoodType) values (N'Nước giải khát')
insert into FoodType(FoodType) values (N'Rượu')
insert into FoodType(FoodType) values (N'Bia')
select * from FoodType

insert into Food(Id,Name,FoodTypeID,Cost) values ('FO001',N'Súp cua',1,1);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO002',N'Phở',2,2);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO003',N'Bún riêu',2,2);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO004',N'Bún chả',2,2);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO005',N'Xôi gà',2,2.5);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO006',N'Kem chiên',3,1);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO007',N'Coke',4,0.5);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO008',N'Sake',5,1);
insert into Food(Id,Name,FoodTypeID,Cost) values ('FO009',N'Hen',6,0.8);
select * from Food

--Table
insert into TableStatus(TableStatus) values (N'Sạch');
insert into TableStatus(TableStatus) values (N'Có người');
insert into TableStatus(TableStatus) values (N'Cần dọn');
insert into TableStatus(TableStatus) values (N'Hủy');
select * from TableStatus

insert into TableDetail(Id,TableStatusID) values ('TB001',1)
insert into TableDetail(Id,TableStatusID) values ('TB002',2)
insert into TableDetail(Id,TableStatusID) values ('TB003',3)
insert into TableDetail(Id,TableStatusID) values ('TB004',1)
select * from TableDetail

select * from Staff
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 3, '2017-6-1 12:00:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 4, '2017-6-1 11:10:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 6, '2017-6-1 11:40:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 3, '2017-6-1 10:45:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 5, '2017-6-1 11:40:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 8, '2017-6-1 11:45:00', 1)
--insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 5, '2017-6-1 12:00:00', 2)
select * from ChangeStatusTable

select * from Food
select * from OrderDetail
select * from Staff
--Order
insert into OrderDetail(Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0001',3,12,5,2,'2017-6-1 11:10:00','2017-6-1 11:20:00','2017-6-1 11:40:00')
insert into OrderDetail(Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0002',4,12,6,5,'2017-6-1 10:45:00','2017-6-1 11:00:00','2017-6-1 11:40:00')
insert into OrderDetail(Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0003',4,12,6,5,'2017-6-1 10:45:00','2017-6-1 11:00:00','2017-6-1 11:40:00')


insert into OrderFood(OrderID,FoodId,CookID,Quantity) values (1,2,9,1)
insert into OrderFood(OrderID,FoodId,CookID,Quantity) values (2,4,10,1)
insert into OrderFood(OrderID,FoodId,CookID,Quantity) values (2,5,9,1)

select * from Staff
select * from StaffRole
select * from TableDetail
select * from TableStatus
select * from Food
select * from FoodType
select * from OrderDetail
select * from OrderFood
select * from ChangeStatusTable
