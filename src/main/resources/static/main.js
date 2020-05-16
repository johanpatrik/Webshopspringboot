$(document).ready(function () {


    $('#login-btn').click(function () {
        let url = 'http://localhost:8080/users/login?username=' + $('#username-input').val() + '&password=' + $('#password-input').val();
        $.getJSON(url, function(data) {
            console.log(data);
               if (data.verified) {
                   localStorage.setItem("user", JSON.stringify(data.user));
                   if (data.user.role === 'ADMIN') {
                       window.location.href = 'userOverview.html'
                   } else {
                       window.location.href = 'shop.html'
                   }
               } else {
                   document.getElementById("wrong-label").style.visibility = "visible";
                   $('#password-input').val("");
               }
            });

    });

    $('#register-btn').click(function () {
    window.location.href = 'register.html'
    });

    //present all records
    $.getJSON("http://localhost:8080/records",
        function(data) {
            $.each(data, function(index, object) {
                addProduct(object);
                $('#record-link-' + object.id).click(function () {
                    storeCurrentRecord(object);
                    window.location.href = 'productinfo.html';
                });
            });
        });

    //show cart
    $('#cart-btn').click(function () {
        document.getElementById("myForm").style.display = "block";
    });

    //hide cart
    $('#cart-cancel-btn').click(function () {
        document.getElementById("myForm").style.display = "none";
    });

    //store currentRecord in local storage
    function storeCurrentRecord(record) {
        localStorage.setItem('record',JSON.stringify(record));
    }
    //return currentRecord from local storage
    function getStoredCurrentRecord() {
        return JSON.parse(localStorage.getItem('record'));
    }

    function addProduct(record) {
        $('.box').append(`<a class="record-link" id="record-link-${record.id}">
            <div class="record" id="${record.id}">
            <img class="record-img-top" src="images/${record.imgURL}" alt="Card image cap"/>
            <div class="record-body">
            <h5 class="record-artist">${record.artist}</h5>
            <h5 class="record-title">${record.title}</h5>
        <h5 class="card-text">
            Price: ${record.price} kr
        </h5>
            </div>
            </div></a>`);
    }

    $('.info').ready(function () {
        let record = getStoredCurrentRecord();
        setProductInfo(record);
    });

    function setProductInfo(record) {
        $('.info').append(`
            <div class="record-info">
            <img class="record-img-info" src="images/${record.imgURL}" alt="Info image"/>
           <div class="info-description">
            <h3 class="info-artist">${record.artist}</h3>
            <h4 class="info-title">${record.title}</h4>
        <h4 class="info-price">Price: ${record.price} KR</h4>
        <button class="add-To-Cart-Btn">Add To Cart</button>
        </div>
            </div>`);

    }



});
