use RestaurantAuto

--Staff
insert into StaffRole(StaffRole) values ('Manager');
insert into StaffRole(StaffRole) values ('Host');
insert into StaffRole(StaffRole) values ('Waiter');
insert into StaffRole(StaffRole) values ('Busboy');
insert into StaffRole(StaffRole) values ('Cook');
insert into StaffRole(StaffRole) values ('Casher');
--select * from StaffRole


insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('MA001','admin','Tung','Nui','1976-12-1','Q1, tpHCM','M',1000,1,1,null)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('HO001','staff','Vi','Deo','1990-1-6','Q4, tpHCM','F',300,1,2,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('HO002','staff','Vi','Na Miu','1989-5-3','Q5, tpHCM','F',300,1,2,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('WA001','staff','Dung','Chi To','1986-2-1','Q6, tpHCM','M',400,1,3,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('WA002','staff','Du','Cu Tham','1987-10-1','Q5, tpHCM','M',400,1,3,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('BU001','staff','Dac','Mit','1991-1-1','Q5, tpHCM','M',300,1,4,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('BU002','staff','Bu','Tan','1992-4-1','Q4, tpHCM','M',300,1,4,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('CO001','staff','Mat','To','1980-8-5','Q2, tpHCM','M',800,1,5,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('CO002','staff','Su','Po','1984-3-15','Q7, tpHCM','M',1000,1,5,1)
insert into Staff(Id,[Password],FristName,LastName,DOB,Addr,Sex,Salary,Available,StaffRoleID,LeadSEQ) values ('CA001','staff','Thu','Ngan','1990-3-5','Q3, tpHCM','F',300,1,6,1)
--select * from Staff

--Food
insert into FoodType(FoodType) values (N'Khai vị')
insert into FoodType(FoodType) values (N'Chính')
insert into FoodType(FoodType) values (N'Tráng miệng')
insert into FoodType(FoodType) values (N'Nước giải khát')
insert into FoodType(FoodType) values (N'Rượu')
insert into FoodType(FoodType) values (N'Bia')
--select * from FoodType

insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO001',N'Súp cua',1,1,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO002',N'Phở',2,2,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO003',N'Bún riêu',2,2,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO004',N'Bún chả',2,2,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO005',N'Xôi gà',2,2.5,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO006',N'Kem chiên',3,1,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO007',N'Coke',4,0.5,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO008',N'Sake',5,1,1);
insert into Food(Id,Name,FoodTypeID,Cost,Available) values ('FO009',N'Hen',6,0.8,1);
--select * from Food

--Table
insert into TableStatus(TableStatus) values (N'Sạch');
insert into TableStatus(TableStatus) values (N'Chưa gọi');
insert into TableStatus(TableStatus) values (N'Đang ăn');
insert into TableStatus(TableStatus) values (N'Cần dọn');
insert into TableStatus(TableStatus) values (N'Đã bỏ');
--select * from TableStatus

insert into [Table](Id,TableStatusID) values ('TB001',1)
insert into [Table](Id,TableStatusID) values ('TB002',1)
insert into [Table](Id,TableStatusID) values ('TB003',1)
insert into [Table](Id,TableStatusID) values ('TB004',1)
insert into [Table](Id,TableStatusID) values ('TB005',1)
insert into [Table](Id,TableStatusID) values ('TB006',1)
insert into [Table](Id,TableStatusID) values ('TB007',1)
insert into [Table](Id,TableStatusID) values ('TB008',1)
insert into [Table](Id,TableStatusID) values ('TB009',1)
insert into [Table](Id,TableStatusID) values ('TB010',1)
insert into [Table](Id,TableStatusID) values ('TB011',1)
insert into [Table](Id,TableStatusID) values ('TB012',1)
--select * from [Table]

--select * from Staff
--host change table status
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (1, 2, '2017-6-1 12:00:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 3, '2017-6-1 12:00:50', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 2, '2017-6-1 12:10:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 3, '2017-6-1 12:10:40', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (5, 2, '2017-6-1 12:15:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (6, 3, '2017-6-1 12:15:09', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (7, 2, '2017-6-1 12:20:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (8, 3, '2017-6-1 12:20:40', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (9, 2, '2017-6-1 12:30:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (10, 3, '2017-6-1 12:30:50', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (11, 2, '2017-6-1 12:40:00', 2)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (12, 2, '2017-6-1 12:45:00', 2)
--select * from ChangeStatusTable
--waiter change table status
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (1, 4, '2017-6-1 12:02:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 5, '2017-6-1 12:05:50', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 4, '2017-6-1 12:14:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 5, '2017-6-1 12:18:40', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (5, 4, '2017-6-1 12:20:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (6, 5, '2017-6-1 12:20:09', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (7, 4, '2017-6-1 12:25:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (8, 5, '2017-6-1 12:26:40', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (9, 4, '2017-6-1 12:37:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (10, 5, '2017-6-1 12:33:50', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (11, 4, '2017-6-1 12:44:00', 3)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (12, 4, '2017-6-1 12:48:00', 3)
--waiter change table status to dirty
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (1, 4, '2017-6-1 13:06:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 5, '2017-6-1 13:02:50', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 4, '2017-6-1 13:11:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 5, '2017-6-1 13:15:40', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (5, 4, '2017-6-1 13:24:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (6, 5, '2017-6-1 13:22:09', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (7, 4, '2017-6-1 13:26:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (8, 5, '2017-6-1 13:23:40', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (9, 4, '2017-6-1 13:35:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (10, 5, '2017-6-1 13:35:50', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (11, 4, '2017-6-1 13:42:00', 4)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (12, 4, '2017-6-1 13:41:00', 4)
--busboy change table status
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (1, 6, '2017-6-1 13:07:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (2, 7, '2017-6-1 13:03:50', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (3, 6, '2017-6-1 13:15:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (4, 7, '2017-6-1 13:16:40', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (5, 6, '2017-6-1 13:25:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (6, 7, '2017-6-1 13:24:09', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (7, 6, '2017-6-1 13:27:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (8, 7, '2017-6-1 13:24:40', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (9, 6, '2017-6-1 13:37:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (10, 7, '2017-6-1 13:35:50', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (11, 6, '2017-6-1 13:44:00', 1)
insert into ChangeStatusTable(TableID,StaffID,ChangeTime,TableStatusID) values (12, 6, '2017-6-1 13:43:00', 1)

select * from Food
select * from OrderDetail
select * from Staff
--Order
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0001',1,10,4,3,'2017-06-01 12:00:00.000','2017-06-01 12:02:00.000','2017-06-01 13:06:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0002',2,10,5,3,'2017-06-01 12:00:50.000','2017-06-01 12:05:50.000','2017-06-01 13:02:50.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0003',3,10,4,2,'2017-06-01 12:10:00.000','2017-06-01 12:14:00.000','2017-06-01 13:11:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0004',4,10,5,2,'2017-06-01 12:10:40.000','2017-06-01 12:18:40.000','2017-06-01 13:15:40.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0005',5,10,4,2,'2017-06-01 12:15:00.000','2017-06-01 12:20:00.000','2017-06-01 13:24:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0006',6,10,5,3,'2017-06-01 12:15:09.000','2017-06-01 12:20:09.000','2017-06-01 13:22:09.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0007',7,10,4,3,'2017-06-01 12:20:00.000','2017-06-01 12:25:00.000','2017-06-01 13:26:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0008',8,10,5,2,'2017-06-01 12:20:40.000','2017-06-01 12:26:40.000','2017-06-01 13:23:40.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0009',9,10,4,4,'2017-06-01 12:30:00.000','2017-06-01 12:37:00.000','2017-06-01 13:35:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0010',10,10,5,2,'2017-06-01 12:30:50.000','2017-06-01 12:33:50.000','2017-06-01 13:35:50.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0011',11,10,4,2,'2017-06-01 12:40:00.000','2017-06-01 12:44:00.000','2017-06-01 13:42:00.000')
insert into [Order](Id,TableID,CashierID,WaiterID,Cost,BeginTime,BeginEatTime,EndTime) 
values ('O0012',10,10,4,2,'2017-06-01 12:45:00.000','2017-06-01 12:48:00.000','2017-06-01 13:41:00.000')



insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (1,1,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (1,2,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (2,5,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (3,4,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (4,3,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (5,6,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (5,7,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (6,5,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (7,5,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (8,4,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (9,5,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (9,9,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (10,2,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (11,6,8,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (11,7,9,1,1)
insert into OrderDetail(OrderID,FoodId,CookID,Quantity,[Status]) values (12,5,8,1,1)


select * from Staff
select * from StaffRole
select * from [Table]
select * from TableStatus
select * from Food
select * from FoodType
select * from OrderDetail
select * from [Order]
select * from ChangeStatusTable
