/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var id;

$(document).ready(function () {

    $("#send").click(function () {

        var ta = document.getElementById("ta");
        var text = ta.value;
        var lsid = localStorage.getItem("loginsid");
        var text1 = JSON.stringify({
            text: text,
            publisher: lsid
        });


        $.ajax({
            url: "http://10.128.216.20:8080/Backends1/rest-api/notice",
            method: "post",
            async: false,
            headers: {
                "Content-Type": "application/json"
            },
            data: text1,
            success: function (xmlDoc) {
                console.log(xmlDoc);

                var jsonStr = (new XMLSerializer()).serializeToString(xmlDoc);
                //console.log(jsonStr);

                id = jsonStr.substring(jsonStr.search("<NId>") + 5, jsonStr.search("</NId>"));

            }
        });



        for (i = 1; i <= 200; i++)
        {

            var img = document.getElementById("img" + i);
            if (img === null)
                continue;
            var a = img.src;
            //console.log(a);
            var imgtype = a.substring(a.search("/") + 1, a.indexOf(";base64,"));
            var po = a.search("base64,");
            var substr = a.substr(po + 7, a.length);
            var str = JSON.stringify({
                base64: substr,
                type: imgtype
            });
            //  console.log(data);


            $.ajax({
                url: "http://10.128.216.20:8080/Backends1/rest-api/filesse/upload",
                method: "post",
                headers: {
                    "Content-Type": "application/json"
                },
                data: str,
                async: false,
                success: function (xmlDoc) {
                    console.log("动态id:" + id);

                    var jsonStr = (new XMLSerializer()).serializeToString(xmlDoc);

                    console.log(jsonStr);
                    var imgid = jsonStr.substring(jsonStr.search("<FId>") + 5, jsonStr.search("</FId>"));

                    console.log("图片id : " + imgid);

                    var blind = JSON.stringify({
                        inAlbum: "",
                        inNotice: id,
                        inStudent: ""
                    });

                    $.ajax({
                        url: "http://10.128.216.20:8080/Backends1/rest-api/filesse/" + imgid,
                        method: "put",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        data: blind,
                        async: false,
                        success: function () {
                            //alert("ok");
                        }
                    });

                }
            });
        }

    });


    $("#final").click(function () {

        var ethnic = document.getElementById("ethnic").value;
        var tel = document.getElementById("tel").value;
        var email = document.getElementById("email").value;
        var sex = document.getElementById("sex").value;
        var company = document.getElementById("company").value;
        var location = document.getElementById("location").value;
        var relationship = document.getElementById("relationship").value;
        var status = document.getElementById("status").value;
        var bd = document.getElementById("birthday").value;
        var intro = document.getElementById("intro").value;
        var name = document.getElementById("name").placeholder;



        if (ethnic === "")
        {
            ethnic = document.getElementById("ethnic").placeholder;
        }

        if (tel === "")
        {
            tel = document.getElementById("tel").placeholder;
        }

        if (email === "")
        {
            email = document.getElementById("email").placeholder;
        }

        if (company === "")
        {
            company = document.getElementById("company").placeholder;
        }

        if (bd === "")
        {
            bd = document.getElementById("birthday").placeholder;
        }

        if (intro === "")
        {
            intro = document.getElementById("intro").placeholder;
        }


        var message = JSON.stringify({
            name: name,
            tel: tel,
            location: location,
            email: email,
            sex: sex,
            status: status,
            birthday: bd,
            ethnic: ethnic,
            company: company,
            relationship: relationship,
            intro: intro
        });

        console.log(message);


        var id = document.getElementById("sid").placeholder;

        //  + "?token=" + "#{loginBean.token}"
        // #{loginBean.sId}
        // localStorage.getItem("token");
        $.ajax({
            url: "http://10.128.216.20:8080/Backends1/rest-api/student/" + id,
            method: "put",
            headers: {
                "Content-Type": "application/json"
            },
            data: message,
            success: function () {
                alert("修改成功！");
                window.location.reload(); // 刷新当前页面.
            }
        });

    })




});



var img = document.getElementById("mainImg");
var id = document.getElementById("sid").placeholder;

console.log(id);
$.ajax({
    url: "http://10.128.216.20:8080/Backends1/rest-api/filesse/student/" + id,
    method: "get",
    headers: {
        "Content-Type": "application/json"
    },
    success: function (xmlDoc) {
       // console.log(xmlDoc);

        var jsonStr = (new XMLSerializer()).serializeToString(xmlDoc);
        //console.log(jsonStr);

        var result = jsonStr.substring(jsonStr.lastIndexOf("<url>") + 5, jsonStr.lastIndexOf("</url>"));
        
        result = "http://10.128.216.20:8080"+result;

        img.src = result;
       // console.log(jsonStr);



    }
});

