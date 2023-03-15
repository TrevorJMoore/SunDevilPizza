# SunDevilPizza
A JavaFX visual application that enables ASU Students to place pickup orders at "Sun Devil Pizza".
## Description
The SunDevilPizza application is split into three distinct views: **Student**, **Processing Agent**, and **Chef** view.


### Student View
The SunDevilPizza application allows students to place customizeable pizza orders. Students can select their pickup time, pizza type, and pizza toppings. To finalize an order, students must provide their ASU ID's and have the application validate them.


Students can also view their placed orders to see order details such as pickup time, pizza type, and pizza toppings. Order details will also show order status which describes whether the pizza order is accepted, ready to cook, cooking, or ready for pickup.


### Processing Agent View
A Processing Agent may use the application to review orders placed and confirm them for the Chef. This agent is a middle man between the orders and the chef to ensure that the orders are possible and will change the status from ***ACCEPTED*** to ***READY TO COOK***. The Processing Agent will sign in through the appropriate screen and provide valid credentials.

### Chef View
A Chef may use the application to begin cooking confirmed orders and to finalize the orders for pickup. This agent will be responsible for changing the status of ***READY TO COOK*** orders to ***COOKING*** and, once done cooking, changing the status to ***READY***. Like the Processing Agent, the chef will sign in through the appropriate screen and provide their valid credentials into the login form.
