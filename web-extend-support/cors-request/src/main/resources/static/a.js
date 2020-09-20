// 测试cors
//http://localhost:8080/myCors
//http://www.pinzhi365.com:8080/myCors
function testCors(url){
    var xhr = new XMLHttpRequest() ;
    xhr.open("GET", url, true) ;
    xhr.send() ;
}


function testCors2(url){
    var xhr = new XMLHttpRequest() ;
    xhr.open("GET", url, true) ;
    xhr.withCredentials = true ;
    xhr.send() ;
}

//http://localhost:8080/myComplexCors
function myComplexCors(url){
    var xhr = new XMLHttpRequest() ;
    xhr.open("PUT", url, true) ;
    xhr.withCredentials = true ;
    xhr.setRequestHeader("X-Custom-Header","Complex Cors") ;
    xhr.send() ;
}
