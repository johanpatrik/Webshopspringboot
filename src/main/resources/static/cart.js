$(document).ready(function () {
    loadCartTable();
    loadOrderTotal();

    function loadCartTable() {

        let $cart = $("#cart-body");

        fetch("http://localhost:8080/cart")
            .then(response => response.json())
            .then(data => {
                let text = "";
                data.forEach(item => {

                    text += "<tr><td><img class='cart-img' src = 'images/" + item.record.imgURL + "'/></td>" +
                        "<td class='cart-text'>" + item.record.title + "</td>" +
                        "<td class='cart-text'>" + item.record.artist + "</td>" +
                        "<td class='cart-text'>" + item.quantity + "</td>" +
                        "<td class='cart-text'>" + item.totalPrice + "</td></tr>"
                    $cart.html(text);
                })
            });

    }

    function loadOrderTotal() {

        fetch("http://localhost:8080/users/test")
            .then(response => response.json())
            .then(data => {
                $("#display-total").text(data.orderTotal);
            })
    }

    $("#empty-btn").click(function () {
        fetch("http://localhost:8080/cart/empty");

        localStorage.setItem("cartCount",JSON.stringify(0));
        window.location.href = "shop.html";

    });

    $("#continue-btn").click(function () {
        window.location.href = "http://localhost:8080/shop.html";
    })

    $("#checkOut-btn").click(function () {
        window.location.href = "http://localhost:8080/shop.html";
    })


    $("#logout-btn").click(function () {
        fetch("http://localhost:8080/users/logout")
            .then(response => response.json())
            .then(data => console.log(data))
            .then(() => window.location.href = "login.html");
    });
});