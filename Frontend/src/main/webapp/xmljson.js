function toJson(xml) {
    var x2js = new X2JS();
    return x2js.xml_str2json("<results>" + xml.documentElement.innerHTML + "</results>")["results"];
}
