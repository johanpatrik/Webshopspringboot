$(document).ready(function () {

/*    if (localStorage.length !== 0 || localStorage.length !== null) {
        loadOrderTable();
    }*/

    fetch("http://localhost:8080/users")
        .then(response => response.json())
        .then(data => {
            getAccessToUsers(data);
        })

    function getAccessToUsers(data) {
        console.log(data);
        loadUserTable(data);
    }


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

    loadOrderTable();

    function getOrderByUser() {
        let id = $(this).attr("id");

        localStorage.clear();
        console.log("http://localhost:8080/orders/"+ id)
        fetch("http://localhost:8080/orders/"+ id)
            .then(response => response.json())
            .then(data => localStorage.setItem('orders', JSON.stringify(data)))
        window.location.replace("orderOverview.html")
    }

    function loadOrderTable() {
        let html1 = "";
        let orders = JSON.parse(localStorage.getItem('orders'));

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