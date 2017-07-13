use RestaurantAuto

select * from Staff
select * from StaffRole
select * from [Table]
select * from TableStatus
select * from Food
select * from FoodType
select * from OrderDetail
select * from [Order]
select * from ChangeStatusTable

--Doanh thu
select sum(Cost) AS  'Total Cost' from [Order]
where BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000';

select sum(Cost) AS  'Total Cost' from [Order]
where BeginTime between '2017-06-01 00:00:00.000' and '2017-06-01 23:59:59.999'
--Món ăn
--Danh sách món ăn theo giá tiền
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity', SUM(Food.Cost) as 'Total Cost'
from [Order],OrderDetail,Food
where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by FoodId, Food.Name
order by SUM(Food.Cost) desc, SUM(OrderDetail.Quantity) desc;

--Thong ke mon an goi nhieu nhat
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from [Order],OrderDetail,Food
where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by FoodId, Food.Name
having SUM(OrderDetail.Quantity) >= ALL (select SUM(OrderDetail.Quantity) as 'Total Quantity'
										from [Order],OrderDetail,Food
										where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ 
												and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
										group by FoodId)

--Danh sách thức ăn theo độ ưa thích
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from [Order],OrderDetail,Food
where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by FoodId, Food.Name
order by SUM(OrderDetail.Quantity) desc;

--Món ăn được ưa thích nhất
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from [Order],OrderDetail,Food
where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by FoodId, Food.Name
having SUM(OrderDetail.Quantity) >= ALL (select SUM(OrderDetail.Quantity)
										from [Order],OrderDetail,Food
										where [Order].SEQ = OrderDetail.OrderID and OrderDetail.FoodId = Food.SEQ 
												and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
										group by FoodId)

--Hiệu suất cá nhân
--Waiter
select st.SEQ, st.FristName, st.LastName, COUNT([Order].SEQ) AS 'Number Of Order'
from [Order], Staff st
where [Order].WaiterID = st.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by st.SEQ, st.FristName, st.LastName
having COUNT([Order].SEQ) >= ALL (select COUNT([Order].SEQ)
									from [Order], Staff st
									where [Order].WaiterID = st.SEQ and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
									group by st.SEQ, st.FristName, st.LastName
									)

--Busboy
--select *
--from ChangeStatusTable tb,Staff st
--where tb.StaffID = st.SEQ and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')

select st.SEQ, st.FristName, st.LastName, count(tb.SEQ)
from ChangeStatusTable tb,Staff st
where tb.StaffID = st.SEQ and (tb.ChangeTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000' )
		and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')
group by st.SEQ, FristName, LastName
having count(tb.SEQ) >= ALL (select count(tb.SEQ)
							from ChangeStatusTable tb,Staff st
							where tb.StaffID = st.SEQ and (tb.ChangeTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000' )
									and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')
							group by st.SEQ)


--Cooker
--select s.SEQ, s.FristName, s.LastName, count(o.SEQ)
--from OrderDetail o, Staff s
--where o.CookID = s.SEQ
--group by s.SEQ, s.FristName, s.LastName

select s.SEQ, s.FristName, s.LastName, count(o.SEQ) as 'Dish'
from [Order],OrderDetail o, Staff s
where [Order].SEQ = o.OrderID and o.CookID = s.SEQ
	and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
group by s.SEQ, s.FristName, s.LastName
having count(o.SEQ) >= ALL (select count(o.SEQ)
								from [Order],OrderDetail o, Staff s
								where [Order].SEQ = o.OrderID and o.CookID = s.SEQ
										and ([Order].BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000')
								group by s.SEQ)

--thoi gian phuc vu
select BeginTime, BeginEatTime, EndTime from [Order]
where BeginTime between '2017-06-01 00:00:00.000' and '2017-06-07 23:59:00.000';