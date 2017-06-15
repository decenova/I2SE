use RestaurantAuto

select * from Staff
select * from StaffRole
select * from TableDetail
select * from TableStatus
select * from Food
select * from FoodType
select * from OrderDetail
select * from OrderFood
select * from ChangeStatusTable

--Doanh thu
select sum(Cost) AS  'Total Cost' from OrderDetail;

--Thong ke mon an, gia tien
select Food.Name,SUM(OrderFood.Quantity) as 'Total Quantity', SUM(Food.Cost) as 'Total Cost'
from OrderFood,Food
where OrderFood.FoodId = Food.SEQ
group by FoodId, Food.Name;

--Thong ke mon an goi nhieu nhat
select Food.Name,SUM(OrderFood.Quantity) as 'Total Quantity'
from OrderFood,Food
where OrderFood.FoodId = Food.SEQ
group by FoodId, Food.Name
having SUM(OrderFood.Quantity) >= ALL (select SUM(OrderFood.Quantity) as 'Total Quantity'
										from OrderFood,Food
										where OrderFood.FoodId = Food.SEQ
										group by FoodId)