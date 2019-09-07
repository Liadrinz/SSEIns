
function warnning() {
    if (confirm("取消后之前输入的信息不会保存，是否确定取消？")) {
        $('#myModal').modal('hide');
        $(".modal-backdrop.fade").hide();
    } else {
        return false;
    }
}


var amount = 0;
var ids = 1;
var count = 1;
function resizeImg(length)
{

    if (count !== length)
    {
        count++;
        return;
    }


    if (amount === 1)
    {
        for (i = 1; i <= ids; i++)
        {
            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            console.log("amount :" + amount);
            //console.log("ids :" +ids);
            s.style.width = "360px";
            s.style.height = "360px";
        }


        count = 1;
    } else if (amount === 2 || amount === 4)
    {
        for (i = 1; i <= ids; i++)
        {
            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            //console.log("i : " + i);
            //console.log("amount :" + amount);
            //console.log(s);
            s.style.width = "180px";
            s.style.height = "180px";
        }
        count = 1;
    } else if (amount === 3)
    {
        for (i = 1; i <= ids; i++)
        {

            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            console.log("amount :" + amount);
            //  console.log(ids);
            s.style.width = "120px";
            s.style.height = "120px";
        }
        count = 1;
    }
}

function resizeAfterDel()
{

    if (amount === 1)
    {
        for (i = 1; i <= ids; i++)
        {
            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            else
            {
                console.log("amount :" + amount);
                //console.log("ids :" +ids);
                s.style.width = "360px";
                s.style.height = "360px";
            }
        }


    } else if (amount === 2 || amount === 4)
    {
        for (i = 1; i <= ids; i++)
        {
            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            else
            {
                console.log("amount :" + amount);
                //console.log("ids :" +ids);
                s.style.width = "180px";
                s.style.height = "180px";
            }
        }
    } else if (amount === 3)
    {
        for (i = 1; i <= ids; i++)
        {
            var s = document.getElementById("img" + i);
            if (s === null)
                continue;
            else
            {
                console.log("amount :" + amount);
                //console.log("ids :" +ids);
                s.style.width = "120px";
                s.style.height = "120px";
            }
        }
    }
}


$(document).ready(function () {
//为外面的盒子绑定一个点击事件
    $("#uploadImgBtn").click(function () {
        /*
         1、先获取input标签
         2、给input标签绑定change事件
         3、把图片回显
         */
//            1、先回去input标签
        var $input = $("#file");
        console.log($input)
        //            2、给input标签绑定change事件

        $input.on("change", function () {
            console.log(this)
            //补充说明：因为我们给input标签设置multiple属性，因此一次可以上传多个文件
            //获取选择图片的个数
            var files = this.files;
            var length = files.length;
            amount = amount + length;
            console.log("选择了" + length + "张图片");
            if (amount > 4) {
                alert("最多只能选择四张图片!");
                amount = amount - length;
                return;
            }


            //3、回显
            $.each(files, function (key, value) {
                //每次都只会遍历一个图片数据
                var div = document.createElement("div"),
                        img = document.createElement("img");
                div.className = "pic showandhide";
                img.id = "img" + ids;
                div.id = "div" + ids;
                ids++;
                console.log("id is " + div.id);
                img.style.padding = "3px";
                
                img.style.position = "relative";
                img.style.zIndex = 0;


                var deletes = document.createElement("span");
                deletes.className = "iconfont icon-shanchu ";
                deletes.style.position = "absolute";
                deletes.style.zIndex = 10000;
                deletes.onclick = function () {

                    this.parentNode.parentNode.removeChild(this.parentNode);
                    //console.log("before :" + amount);
                    amount--;
                    console.log("length :" + length);
                    resizeAfterDel();
                };
                div.appendChild(deletes);



                var fr = new FileReader();
                fr.onload = function () {
                    img.src = this.result;

                    div.appendChild(img);
                    div.style = "float:left";

                    document.getElementById("picture").appendChild(div);
                    resizeImg(length);
                    
                    
                }
                fr.readAsDataURL(value);
            })

        })

        //4、我们把当前input标签的id属性remove
        $input.removeAttr("id");
        //我们做个标记，再class中再添加一个类名就叫test
        var newInput = '<input class="uploadImg test" type="file" name="file" multiple="multiple" id ="file"></input>';
        $(this).append($(newInput));
    });


});







window.onload = function () {

    //获取文本内容和长度函数

    function txtCount(el) {

        var val = el.value;
        var eLen = val.length;
        return eLen;
    }



    var el = document.getElementById('ta');
    el.addEventListener('input', function () {

        var len = txtCount(this); //   调用函数 

        if (len >= 200)
            alert("最多输入200字！");
    });
    el.addEventListener('propertychange', function () {//兼容IE

        var len = txtCount(this); //   调用函数 

        if (len >= 200)
            alert("最多输入200字！");
    });
}


