$(document).ready(function () {

    $("#checkTriangleButton").bind("click", function() {
        var host = location.protocol + '//' + location.host;

        var checkTriangleData = {};
        checkTriangleData.a = $("#checkTriangleA").val();
        checkTriangleData.b = $("#checkTriangleB").val();
        checkTriangleData.c = $("#checkTriangleC").val();

        $.ajax({
            type: "POST",
            url: host + "/triangle/checkTriangle",
            data: JSON.stringify(checkTriangleData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success : function(response) {
                alert("Success!");
                alert(response.data);
            },
            error: function (response) {
                alert("Error!");
                alert(response.data);
            }
        });
    })
});