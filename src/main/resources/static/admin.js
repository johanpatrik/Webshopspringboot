$(document).ready(function () {


    let orders = JSON.parse(localStorage.orders || "[]");
    loadOrderTable();

    fetch("http://localhost:8080/users")
        .then(response => response.json())
        .then(data => {
            loadUserTable(data);
        })


    function loadUserTable(data) {
        let html = "";
        let customers = data.filter(function (user) {
            return user.role !== 'ADMIN';
        });


        customers.forEach(customer => {
            html += "<tr class='clickable-row' tabindex='0' id=" + customer.id + "><td>" + customer.userName + "</td>" +
                "<td>" + customer.firstName + "</td>" +
                "<td>" + customer.lastName + "</td>" +
                "<td>" + customer.formattedTotalSpent + "</td>" +
                "<td>" + customer.role + "</td>" +
                "</tr>"
        })
        $(".tbody-user").html(html);
        $('.clickable-row').click(getOrderByUser);
    }

    function getOrderByUser() {

        let id = $(this).attr("id");

        fetch("http://localhost:8080/orders/"+ id)
            .then(response => response.json())
            .then(data => localStorage.setItem('orders', JSON.stringify(data)))
            .then(() => window.location.replace("orderOverview.html"))
    }

    function loadOrderTable() {
        let html1 = "";
        orders.forEach(order => {
            html1 += "<tr class='clickable-row' tabindex='0' id=" + order.id + ">" +
                "<td>" + order.id + "</td>" +
                "<td>" + order.date + "</td>" +
                "<td>" + "ANTAL" + "</td>" +
                "<td>" + order.orderTotal + "</td>" +
                "</tr>"
        })
        $(".tbody-order").html(html1);

        /*$('.clickable-row').click();*/
    }

});