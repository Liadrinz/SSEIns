
var $button = $('#upload'),
        //选择文件按钮
        $file = $("#choose-file"),
        //回显的列表
        $list = $('#file-list'),
        URL = prefix.substr(0, prefix.length - 1),
        //选择要上传的所有文件
        fileList = [],
        sendList = [],
        filebase64 = [];
//当前选择上传的文件
var curFile;
$file.on('change', function () {
    //原生的文件对象，相当于$file.get(0).files[0];
    console.log("hello1");
    curFile = this.files;
    //将FileList对象变成数组
    fileList = fileList.concat(Array.from(curFile));
    for (var i = 0, len = curFile.length; i < len; i++) {
        reviewFile(curFile[i]);
    }

});
function reviewFile(file) {
    //实例化fileReader,
    let  fd = new FileReader();
    //获取当前选择文件的类型
    let fileType = file.type;
    //调它的readAsDataURL并把原生File对象传给它，
    fd.readAsDataURL(file); //base64
    //监听它的onload事件，load完读取的结果就在它的result属性里了

    fd.onload = function () {

        if (/^image\/[jpeg|png|jpg|gif]/.test(fileType)) {
            filebase64.push(this.result);
            $list.append('<li class="file-item"><img style="height:100px" src="' + this.result + '" alt=""><span class="file-name">' + file.name + '</span></li>');
        } else {
            $list.append('<li class="file-item"><span class="file-name">' + file.name + '</span></li>');
        }
    };
}

function toJson(xml) {
    var x2js = new X2JS();
    return x2js.xml_str2json("<results>" + xml.documentElement.innerHTML + "</results>")["results"];
}

$button.on('click', function () {

    if (fileList.length > 0) {
        for (var i = 0, len = fileList.length; i < len; i++) {
            var arr = filebase64[i].split(",");
            $.ajax({
                url: URL + "/Backends1/rest-api/filesse/upload/",
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({type: "jpg", base64: arr[1]}),
                success: function (message) {
                    var json = toJson(message);
                    var alb = window.location.href;
                    var ar = alb.split("=")
                    $.ajax({
                        url: URL+"/Backends1/rest-api/filesse/"+json["FId"],
                        method:"PUT",
                        headers:{
                            "Content-Type":"application/json"
                        },
                        data:JSON.stringify({inAlbum:ar[1],inStudent:"",inNotice:""})
                    })
                }
            });
        }

    } else {
        alert("请选择文件！");
    }
    return false;
});
    