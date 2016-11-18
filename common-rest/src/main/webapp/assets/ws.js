$(function(){
    var path = "$!{SERVER_HOST}";
    var fromName='$!{request.getSession().getAttribute("user").userName}';
    var websocket;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://" + path + "/ws");
    } else if ('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://" + path + "/ws");
    } else {
        websocket = new SockJS("http://" + path + "/ws/sockjs");
    }
    websocket.onopen = function(event) {
        console.log("WebSocket:已连接");
        console.log(event);
    };
    websocket.onmessage = function(event) {
        var data=JSON.parse(event.data);
        console.log("WebSocket:收到一条消息",data);
        // $("#content").append("<div class='itemdiv dialogdiv'><div class='user'><img alt='Bob's Avatar' src='/assets/admin/avatars/user.jpg' /></div><div class='body'><div class='time'><i class='icon-time'></i><span class='orange'>"+ new Date().Format("yyyy-MM-dd hh:mm:ss") +"</span></div><div class='name'><a href='#'>" + data.fromUserName + "</a><span class='label label-info arrowed arrowed-in-right'>admin</span></div><div class='text'>" + data.textMessage + "</div><div class='tools'><a href='#' class='btn btn-minier btn-info'><i class='icon-only icon-share-alt'></i> </a></div></div>");
    };
    websocket.onerror = function(event) {
        console.log("WebSocket:发生错误 ");
        console.log(event);
    };
    websocket.onclose = function(event) {
        console.log("WebSocket:已关闭");
        console.log(event);
    }
});