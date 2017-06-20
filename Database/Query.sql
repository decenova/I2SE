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
select sum(Cost) AS  'Total Cost' from [Order];

--Món ăn
--Danh sách món ăn theo giá tiền
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity', SUM(Food.Cost) as 'Total Cost'
from OrderDetail,Food
where OrderDetail.FoodId = Food.SEQ
group by FoodId, Food.Name
order by SUM(Food.Cost) desc, SUM(OrderDetail.Quantity) desc;

--Thong ke mon an goi nhieu nhat
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from OrderDetail,Food
where OrderDetail.FoodId = Food.SEQ
group by FoodId, Food.Name
having SUM(OrderDetail.Quantity) >= ALL (select SUM(OrderDetail.Quantity) as 'Total Quantity'
										from OrderDetail,Food
										where OrderDetail.FoodId = Food.SEQ
										group by FoodId)

--Danh sách thức ăn theo độ ưa thích
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from OrderDetail,Food
where OrderDetail.FoodId = Food.SEQ
group by FoodId, Food.Name
order by SUM(OrderDetail.Quantity) desc;

--Món ăn được ưa thích nhất
select Food.Name,SUM(OrderDetail.Quantity) as 'Total Quantity'
from OrderDetail,Food
where OrderDetail.FoodId = Food.SEQ
group by FoodId, Food.Name
having SUM(OrderDetail.Quantity) >= ALL (select SUM(OrderDetail.Quantity)
										from OrderDetail,Food
										where OrderDetail.FoodId = Food.SEQ
										group by FoodId)

--Hiệu suất cá nhân

--Busboy
--select *
--from ChangeStatusTable tb,Staff st
--where tb.StaffID = st.SEQ and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')

select st.SEQ, st.FristName, st.LastName, count(tb.SEQ)
from ChangeStatusTable tb,Staff st
where tb.StaffID = st.SEQ and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')
group by st.SEQ, FristName, LastName
having count(tb.SEQ) >= ALL (select count(tb.SEQ)
							from ChangeStatusTable tb,Staff st
							where tb.StaffID = st.SEQ and tb.TableStatusID = (select SEQ from TableStatus where TableStatus = N'Clean')
							group by st.SEQ)

--Cooker

---------select s.SEQ, s.FristName, s.LastName, count(o.SEQ)
--from OrderDetail o, Staff s
--where o.CookID = s.SEQ
--group by s.SEQ, s.FristName, s.LastName

select s.SEQ, s.FristName, s.LastName, count(o.SEQ)
from OrderDetail o, Staff s
where o.CookID = s.SEQ
group by s.SEQ, s.FristName, s.LastName
having count(o.SEQ) >= ALL (select count(o.SEQ)
								from OrderDetail o, Staff s
								where o.CookID = s.SEQ
								group by s.SEQ)


