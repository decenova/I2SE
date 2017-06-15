create database RestaurantAuto

use RestaurantAuto

create table Staff(
	SEQ int primary key identity,
	Id char(5) not null unique,
	FristName nvarchar(10),
	LastName nvarchar(30),
	DOB date,
	Addr nvarchar(100),
	Sex char(1) check (Sex in ('M','F','U')),
	Salary decimal not null,
	StaffRoleID int,
	LeadSEQ int
	constraint FK_STAFF_STAFFROLE foreign key (StaffRoleID) references StaffRole(SEQ)
)

create table StaffRole(
	SEQ int primary key identity,
	StaffRole nvarchar(10) unique
)
--alter table StaffRole add constraint UN_STAFFROLE unique (StaffRole)

create table FoodType(
	SEQ int primary key identity,
	FoodType int not null unique
)
alter table FoodType add constraint UN_FOODTYPE unique (FoodType)
alter table FoodType alter column FoodType nvarchar(20)
create table Food(
	SEQ int primary key identity,
	Id char(5) not null unique,
	Name nvarchar(30) not null,
	FoodTypeID int not null,
	Cost decimal not null,
	constraint FK_FOOD_FOODTYPE foreign key (FoodTypeID) references FoodType(SEQ)
)
--alter table Food drop column CostID
--alter table Food add Cost decimal

create table TableStatus(
	SEQ int primary key identity,
	TableStatus nvarchar(10)
)
alter table TableStatus add constraint UN_TABLESTATUS unique (TableStatus)
create table TableDetail(
	SEQ int primary key identity,
	Id char(5) not null unique,
	TableStatusID int,
	constraint FK_TABLEDETAIL_TABLESTATUS foreign key (TableStatusID) references TableStatus(SEQ)
)

--create table Customer(
--	SEQ int primary key identity,
--	Id char(5) not null unique,
--	FristName nvarchar(10),
--	LastName nvarchar(30),
--	DOB date,
--	Phone varchar(20),
--	Addr nvarchar(100),
--	Sex char(1) check (Sex in ('M','F','U'))
--)
--drop table Customer

create table OrderDetail(
	SEQ int primary key identity,
	Id char(5) not null unique,
	TableID int not null,
	CashierID int not null,
	WaiterID int not null,
	--CustomerID int,
	Cost decimal not null,
	BeginTime datetime not null,
	BeginEatTime datetime not null,
	EndTime datetime not null,
	constraint FK_BILL_TABLE foreign key (TableID) references TableDetail(SEQ),
	constraint FK_BILL_STAFF_CASHIER foreign key (CashierID) references Staff(SEQ),
	constraint FK_BILL_STAFF_WAITER foreign key (WaiterID) references Staff(SEQ),
	--constraint FK_BILL_CUSTOMER foreign key (CustomerID) references Customer(SEQ)
)
--alter table OrderDetail add Cost decimal
--alter table OrderDetail drop constraint FK_BILL_CUSTOMER
--alter table OrderDetail drop column CustomerID

create table OrderFood(
	SEQ int primary key identity,
	OrderID int not null,
	FoodId int not null,
	CookID int not null,
	Quantity int not null,
	constraint FK_ORDERFOOD_BILL foreign key (OrderID) references OrderDetail(SEQ),
	constraint FK_ORDERFOOD_FOOD foreign key (FoodId) references Food(SEQ),
	constraint FK_ORDERFOOD_STAFF foreign key (CookId) references Staff(SEQ)
)
alter table OrderFood add Quantity int not null

--create table Work(
--	SEQ int primary key identity,
--	StaffID int not null,
--	WorkTimeBegin datetime not null,
--	WorkTimeEnd datetime not null,
--	constraint FK_WORK_STAFF foreign key (StaffID) references Staff(SEQ)
--)
--drop table Work

create table ChangeStatusTable(
	SEQ int primary key identity,
	TableID int not null,
	StaffID int not null,
	ChangeTime datetime not null,
	TableStatusID int not null,
	constraint FK_CHANGESTATUSTABLE_TABLE foreign key (TableID) references TableDetail(SEQ),
	constraint FK_CHANGESTATUSTABLE_STAFF foreign key (StaffID) references Staff(SEQ),
	constraint FK_CHANGESTATUSTABLE_TABLESTATUS foreign key (TableStatusID) references TableStatus(SEQ)
)



