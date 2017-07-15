/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getFood(from, to){
    $.ajax({
            url: "/RestaurantAuto/FoodController",
            method:"POST",
            data: "fromDate=" + from + "&toDate=" + to,
            success: function (data) {
                console.log(JSON.stringify(data))
            }
        })
}
