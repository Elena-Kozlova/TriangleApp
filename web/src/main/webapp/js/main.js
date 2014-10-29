$(document).ready(function () {

    $("#checkTriangleButton").bind("click", function() {
        var host = location.protocol + '//' + location.host;

        var checkTriangleData = {};
        checkTriangleData.a = $("#checkTriangleA").val();
        checkTriangleData.b = $("#checkTriangleB").val();
        checkTriangleData.c = $("#checkTriangleC").val();

        $("#triangleResult").text("");

        $.ajax({
            type: "POST",
            url: host + "/triangle/checkTriangle",
            data: JSON.stringify(checkTriangleData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success : function(response) {
                var errorCode = response.errorCode;
                if(errorCode == 0)  {
                    var exists = response.data.exists;

                    if(exists == "YES") {
                        $("#triangleResult").text("Triangle exists!");
                    }
                    else if(exists == "NO") {
                        $("#triangleResult").text("Triangle doesn't exist!");
                    }
                }
                else {
                    $("#triangleResult").text(response.message);
                }
            },
            error: function (response) {
                $("#triangleResult").text("Error occurred");
            }
        });
    });

    $("#getVersionButton").bind("click", function() {
        var host = location.protocol + '//' + location.host;

        $("#applicationVersion").text("");

        $.ajax({
            type: "GET",
            url: host + "/triangle/version",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success : function(response) {
                var errorCode = response.errorCode;
                if(errorCode == 0)  {
                    $("#applicationVersion").text(response.data.version);
                }
                else {
                    $("#applicationVersion").text(response.message);
                }
            },
            error: function (response) {
                $("#applicationVersion").text("Error occurred");
            }
        });
    });
});