/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */     
     function function1(){
          var text;
          var text1;
          var text2=document.getElementById("classname")
          var text3=text2.value;
          var name;
           var result;
           var result2;
           var i;
           var father=document.getElementById("roww");
           father.innerHTML="";
//           var childList = document.getElementById('roww').childNodes;
//           alert(childList.length);
//            for(i=0;i<childList.length;i++){
//              document.getElementById('roww').removeChild(childList[i]);
//                     }      
                  $.ajax({
            url: "http://10.128.216.20:8080/Backends1/rest-api/student/klassNum/"+text3,
            method: "get",
            async: false,
            headers: {
                "Content-Type": "application/json"
            },
            data: text1,
            success: function (data) {
                text=data;
                text=text.toString();
            }
        });
        console.log(text);
        result=text.charAt(text.length-1);
        result=parseInt(result);
        result2=text.charAt(0);
        result2=parseInt(result2);
        if(result2===0)
            result2++;
        for(i=result;i<=result;i++){
            var doc;
            var doc2;
            var doc3;
            var url;
             $.ajax({
            url: "http://10.128.216.20:8080/Backends1/rest-api/filesse/student/"+i,
            method: "get",
            async: false,
            headers: {
                "Content-Type": "application/json"
            },
            data: text1,
            success: function (data) {
              console.log(data);
                doc=toJson(data);
                console.log(doc);
            }
        });
        doc2=doc.filesse;
        doc3=doc2[(doc2.length)-1];
        url=doc3.url;
        name = doc3.inStudent.name;

              var div = document.createElement("div");
                    div.id = "viewPointDiv"+i;
         //为div创建属性class = "viewPoint"  
         var divattr = document.createAttribute("class");
         divattr.value = "col-sm-6 col-md-3";
         //把属性class = "viewPoint"添加到div  
         div.setAttributeNode(divattr);
         
         //为div添加样式  
         var style = document.createAttribute("style");
         div.setAttributeNode(style);
         div.style.width = "200px";
         div.style.height = "200px";
         div.style.position="relative";
         div.style.float="left";
         div.style.boxShadow = "5px 20px 20px -20px #5E5E5E";
                  document.getElementById("roww").appendChild(div);
                  var a = document.createElement("a");
                    a.id = "viewPointA"+i;  
         var aattr = document.createAttribute("class");
         var aattr2 = document.createAttribute("href");
         aattr.value = "thumbnail";
         aattr2.value = "http://10.128.216.20:8080"+url;
         a.setAttributeNode(aattr);
         a.setAttributeNode(aattr2);
                  document.getElementById("viewPointDiv"+i).appendChild(a);
                  var img = document.createElement("img");
                    img.id = "viewPointIMG"+i;  
         var imgattr = document.createAttribute("src");
         var imgattr2 = document.createAttribute("alt");
         imgattr.value = "http://10.128.216.20:8080"+url;
         imgattr2.value = "暂无头像";
         img.setAttributeNode(imgattr);
         img.setAttributeNode(imgattr2);
         img.style.width = "160px";
        img.style.height = "200px";

                  document.getElementById("viewPointA"+i).appendChild(img);

                   var div2 = document.createElement("div");
                   var style2 = document.createAttribute("style");
                                 div2.id = "viewPointdiv2"+i;  
                       div2.setAttributeNode(style2);
                       div2.className="nbs";
                         div2.innerHTML=name;
                   document.getElementById("viewPointA"+i).appendChild(div2);

        }
        }
        
        
        function toJson(xml) {
    var x2js = new X2JS();
    return x2js.xml_str2json("<results>" + xml.documentElement.innerHTML + "</results>")["results"];
};

      
      
       
    
