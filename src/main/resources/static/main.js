$(document).ready(function () {

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

    $('#cart-btn').click(function () {
        document.getElementById("myForm").style.display = "block";
    });

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
