/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var Url = "http://10.128.216.20:8080";
$('div[name="lbimf_1"]').first().attr("class","carousel-item active");
function clearDate(data){
    var answer = data.substring(0,10)+"  "+data.substring(11,16);
    return answer;
}
function toJson(xml) {
    var x2js = new X2JS();
    return x2js.xml_str2json("<results>" + xml.documentElement.innerHTML + "</results>")["results"];
};
function getHeadimg(sid) {
    var fileurl = Url + "/Backends1/rest-api/filesse/student/" + sid;

    var headirl;
    $.ajax({
        type: "GET",
        url: fileurl,
        async: false,
        dataType: "xml",
        success: function (fileinfo) {
            var fileinfo_obj = toJson(fileinfo);
            var char = [];
            char = fileinfo_obj.filesse;
            headirl = char[fileinfo_obj.filesse.length - 1].url;

        }
    });
    return headirl;
};
function getNoticimg(nid){
    var imgurl = Url + "/Backends1/rest-api/filesse/notice/"+nid;
    var imglist = [];
    $.ajax({
        type: "GET",
        url: imgurl,
        async: false,
        dataType: "xml",
        success: function (imgurl) {
            var imgt = toJson(imgurl);
            var x = imgt.filesse;
            imglist = x;
            
        }
    });
    return imglist;
        
}
function getNotocecount(){
    var counturl = Url + "/Backends1/rest-api/notice/count";
    var ncount ;
    $.ajax({
        type: "GET",
        url: counturl,
        async: false,
        dataType: "text",
        success:function(xxx){
 
            localStorage.setItem("maxnnum",xxx-1);
        }
    });
        
}
getNotocecount();
function getCom(nid) {
    var comurl = Url + "/Backends1/rest-api/comment";
    var comlist = [];
    $.ajax({
        type: "GET",
        url: comurl,
        async: false,
        dataType: "xml",
        success: function (cominfo) {
            var com_obj = toJson(cominfo);

            var comments = com_obj.comment;

            for (var i = 0; i < com_obj.comment.length; i++) {
                if (comments[i].notice.NId === nid) {
                    comlist.push(comments[i]);

                }
            }


        }
    });
    return comlist;
}
;
$("#updatabtn").click(function () {
    var x = localStorage.getItem("maxnnum")-4;
    localStorage.setItem("maxnnum",x);
    var tt = parseInt(x);
    
    var newurl = Url + "/Backends1/rest-api/notice";
    if (x <= "0") {
        $("#updatabtn").html("<strong>没有更多动态了</strong>");
        newurl = "";

    } else {

        
        tt = x - 3;
        if (tt >= 0) {
            newurl = newurl + "/" + tt + "/" + x;
         
        } else {
            newurl = newurl + "/0" + "/" + x;
          
        }
    }
    $.ajax({
        type: 'GET',
        url: newurl,
        dataType: "xml",
        success: function (data) {
           ;
            var commenttext = "";
            var json_obj = toJson(data);
            var noticelist = json_obj.notice;
            var notinfo = [];
            for (var i in  noticelist) {
                var notitem = [];
                var puberid = noticelist[i].publisher.SId;
                var pubhead = getHeadimg(puberid);
                var pttime = clearDate(noticelist[i].publishTime);
                var nnid = noticelist[i].NId;
                var imddd = getNoticimg(nnid);
                var imd = [];
                var imdddlength = 0;
                for(var xx in imddd){
                    
                        imd.push(Url+imddd[xx].url);
                        imdddlength++;
                }
                for(var xxx = imdddlength;xxx<4;xxx++){
                    imd.push("");
                }
                var ninfo = {
                    ptime: pttime,
                    nid: nnid,
                    pname: noticelist[i].publisher.name,
                    ntext: noticelist[i].text,
                    phead: pubhead,
                    pimg:imd
                };
                
                notinfo.push(ninfo);
                console.log(notinfo);
                
            }


            for (var t = notinfo.length - 1; t >= 0; t--) {

                var comlist = getCom(notinfo[t].nid);
                var cominfo = [];
                for (var i in comlist) {

                    var conitem = [];
                    var csid;
                    csid = comlist[i].commenter.SId;
                     var cttime = clearDate(comlist[i].commentTime);
                    var cinfo = {
                        ctime: cttime,
                        cname: comlist[i].commenter.name,
                        ctext: comlist[i].content,
                        chead: getHeadimg(csid)
                    };
                    cominfo.push(cinfo);
                }

                for (var j in comlist) {


                    commenttext = commenttext + "<div class=\"notice_item_x \">\n" +
                            "\n" +
                            "                        <a class=\"col-md-1\">\n" +
                            "                            <img src=\"http://10.109.239.10:8080" + cominfo[j].chead + "\" class=\"notice_item_img visit1\"> \n" +
                            "                        </a>\n" +
                            "                        <div class=\"test\">\n" +
                            "                            <small class=\"col-md-8\" style=\"margin-right: 0px;\"><strong style=\"color:#0066CC\">" + cominfo[j].cname + "：</strong>" + cominfo[j].ctext + "</small>\n" +
                            "                            <p style=\"color:grey\"> " + cominfo[j].ctime + "</p>\n" +
                            "                        </div>\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "                    </div>";

                }
                ;
                $("#updatabtn").before("<div id=\"notice_item\" class=\"notice_item\" name=\"" + notinfo[t].nid + "\">\n" +
                        "                <div class=\"notice_item_s \">\n" +
                        "\n" +
                        "                    <a class=\"col-md-1\">\n" +
                        "                        <img src=\"http://10.109.239.10:8080" + notinfo[t].phead + "\" class=\"notice_item_img pub\"> \n" +
                        "                    </a>\n" +
                        "                    <div class=\"test notice_info\">\n" +
                        "                        <h5 class=\"col-md-8\" style=\"margin-right: 0px;\">\n" +
                        "                            <strong style=\"color:#0066CC\">" + notinfo[t].pname + "：</strong>\n" +
                        "                            <small>" + notinfo[t].ntext + "</small>\n" +
                        "                            <button data-toggle=\"collapse\" data-target=\"#photo" + notinfo[t].nid + "\" class=\"openphoto btn\">\n" +
                        "                                <span class=\"iconfont icon-icon-test\"></span>\n" +
                        "                            </button>\n" +
                        "                        </h5>\n" +
                        "\n" +
                        "                        <div class=\"photoshow\">\n" +
                        "                            <div id=\"photo" + notinfo[t].nid + "\" class=\"collapse\">\n" +
                        "                                <a class=\"col-md-1\">\n" +
                        "                                    <img src=\""+notinfo[t].pimg[0]+"\" class=\"notice_item_img-1\"> \n" +
                        "                                </a>\n" +
                        "                                <a class=\"col-md-1\">\n" +
                        "                                    <img src=\""+notinfo[t].pimg[1]+"\" class=\"notice_item_img-1 \"> \n" +
                        "                                </a>\n" +
                        "                                <a class=\"col-md-1\">\n" +
                        "                                    <img src=\""+notinfo[t].pimg[2]+"\" class=\"notice_item_img-1 \"> \n" +
                        "                                </a>\n" +
                        "                                <a class=\"col-md-1\">\n" +
                        "                                    <img src=\""+notinfo[t].pimg[3]+"\" class=\"notice_item_img-1\"> \n" +
                        "                                </a>\n" +
                        "                            </div>\n" +
                        "                        </div>\n" +
                        "                        <p style=\"color:grey\"> " + notinfo[t].ptime + "</p>\n" +
                        "                    </div>\n" +
                        "\n" +
                        "\n" +
                        "\n" + commenttext +
                        " </div>\n" +
                        "<div class=\"notice_item_write\">\n" +
                        "                    <input id=\"ctext" + notinfo[t].nid + "\">\n" +
                        "\n" +
                        "<button class=\"btn btn-outline-primary send\"  name =\""+notinfo[t].nid+"\" onclick=\"sendComment(this)\">发送</button>"+
                        "\n" +
                        "                </div>");
            }



        },
        error: function () {
            console.log("404");

        }

    });
});

function sendComment (tb) {


    var NID = $(tb).attr('name');
    var ctest = "#ctext" + NID;
    var loginsid = localStorage.getItem("loginsid");
    console.log("NID:" + NID);
    var schoolList = {
        notice: NID,
        content: $(ctest).val(),
        commenter: loginsid
    };
    console.log(schoolList);
    $.ajax({
        type: 'POST',
        url: "http://10.128.216.20:8080/Backends1/rest-api/comment",
        headers: {
            "Content-Type": "application/json"
        },
        data: JSON.stringify(schoolList),
        success: function (data) {
            console.log("data:" + data);
            alert("评论成功");
           location.reload();
        }
    });
};

$(".openphoto").click(function () {
    var x = $(this).children("span").attr('class');
    if (x === "iconfont icon-icon-test") {
        $(this).children("span").attr("class", "iconfont icon-icon-test1");
    } else {
        $(this).children("span").attr("class", "iconfont icon-icon-test");
    }
});

$("#searchsend").click(function(){
    var x = $(this).prev().val();
    window.location.href="searchresults-student.xhtml?keyword="+x;
   
});