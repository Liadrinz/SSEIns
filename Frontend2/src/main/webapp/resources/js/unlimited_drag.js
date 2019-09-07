/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//滚动条在Y轴上的滚动距离
/*endlesspage.js*/
//思路如下：
      /*<![CDATA[*/ 
var  bodyObj=document.documentElement||document.body;

   scrollTop=bodyObj.scrollTop,

   browseHeight=bodyObj.clientHeight||window.innerHeight;

 

   window.onscroll=function(){

debugger;

       var currentScrollTop=bodyObj.scrollTop,

       pageHeight=bodyObj.scrollHeight;

       document.title=currentScrollTop+"|"+browseHeight;
       if(pageHeight-(browseHeight+currentScrollTop)<100){

        var div = document.createElement("div");
    
         div.id = "viewPointDiv";
         //为div创建属性class = "viewPoint"  
         var divattr = document.createAttribute("class");
         divattr.value = "box";
         //把属性class = "viewPoint"添加到div  
         div.setAttributeNode(divattr);
         div.innerHTML ='<ui:decorate template="./notice.xhtml"></ui:decorate> ';
         
         
         //div.style.z-index = "99999";	//iframe的z-index设为-1
         
         
        document.getElementsByTagName("body").item(0).appendChild(div);
        
       }else{

           document.title="还没有到了！";

       }
 /*]]>*/
   };   
 


 

