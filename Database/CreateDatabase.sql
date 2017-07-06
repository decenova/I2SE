create database RestaurantAuto;
go

use RestaurantAuto;
go


--

create table StaffRole(
	SEQ int primary key identity,
	StaffRole nvarchar(10) unique
)
go
create table Staff(
	SEQ int primary key identity,
	Id char(5) not null unique,
	[Password] varchar(30) not null,
	FristName nvarchar(10),
	LastName nvarchar(30),
	DOB date,
	Addr nvarchar(100),
	Sex char(1) check (Sex in ('M','F','U')),
	Salary decimal not null,
	Available bit not null,
	StaffRoleID int,
	LeadSEQ int,
	constraint FK_STAFF_STAFFROLE foreign key (StaffRoleID) references StaffRole(SEQ),
	constraint FK_STAFF_STAFF foreign key (LeadSEQ) references Staff(SEQ)
)

create table FoodType(
	SEQ int primary key identity,
	FoodType nvarchar(20) not null unique
)
go
create table Food(
	SEQ int primary key identity,
	Id char(5) not null unique,
	Name nvarchar(30) not null,
	FoodTypeID int not null,
	Cost decimal not null,
	Available bit not null,
	constraint FK_FOOD_FOODTYPE foreign key (FoodTypeID) references FoodType(SEQ)
)


create table TableStatus(
	SEQ int primary key identity,
	TableStatus nvarchar(10) unique
)
go
create table [Table](
	SEQ int primary key identity,
	Id char(5) not null unique,
	TableStatusID int,
	constraint FK_TABLEDETAIL_TABLESTATUS foreign key (TableStatusID) references TableStatus(SEQ)
)

create table [Order](
	SEQ int primary key identity,
	TableID int not null,
	CashierID int,
	WaiterID int,
	--CustomerID int,
	Cost decimal,
	BeginTime datetime,
	BeginEatTime datetime ,
	EndTime datetime ,
	constraint FK_BILL_TABLE foreign key (TableID) references [Table](SEQ),
	constraint FK_BILL_STAFF_CASHIER foreign key (CashierID) references Staff(SEQ),
	constraint FK_BILL_STAFF_WAITER foreign key (WaiterID) references Staff(SEQ),
)
go
create table OrderDetail(
	SEQ int primary key identity,
	OrderID int not null,
	FoodId int not null,
	CookID int,
	[Status] bit not null, 
	Quantity int not null,
	constraint FK_ORDERDETAIL_ORDER foreign key (OrderID) references [Order](SEQ),
	constraint FK_ORDERDETAIL_FOOD foreign key (FoodId) references Food(SEQ),
	constraint FK_ORDERDETAIL_STAFF foreign key (CookId) references Staff(SEQ)
)
go
create table ChangeStatusTable(
	SEQ int primary key identity,
	TableID int not null,
	StaffID int not null,
	ChangeTime datetime not null,
	TableStatusID int not null,
	constraint FK_CHANGESTATUSTABLE_TABLE foreign key (TableID) references [Table](SEQ),
	constraint FK_CHANGESTATUSTABLE_STAFF foreign key (StaffID) references Staff(SEQ),
	constraint FK_CHANGESTATUSTABLE_TABLESTATUS foreign key (TableStatusID) references TableStatus(SEQ)
)



