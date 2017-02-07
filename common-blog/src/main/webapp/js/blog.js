$(function () {
    var basePath = "http://common.wsh.org";
    $.ajax({
        url:"/apis/resteasy/hello/home",
        type:"GET",
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".content").append("<div class=\"col-sm-12 col-md-12\">" +
                        "<div class=\"single-blog single-column\">" +
                        "<div class=\"post-thumb\">" +
                        "<a href=\"blogdetails.html\"><img src=\"" + obj[i].coverUrl + "\" class=\"img-responsive\" alt=\"\"></a>" +
                        "<div class=\"post-overlay\">" +
                        "<span class=\"uppercase\"><a href=\"#\">14 <br><small>Feb</small></a></span>" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"post-content overflow\">" +
                        "<h2 class=\"post-title bold\"><a href=\"blogdetails.html\">" + obj[i].title + "</a></h2>" +
                        "<h3 class=\"post-author\"><a href=\"#\">来自 禅明 </a></h3>" +
                        "<p>Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber [...]</p>" +
                        "<a href=\"#\" class=\"read-more\">详情</a>" +
                        "<div class=\"post-bottom overflow\">" +
                        "<ul class=\"nav navbar-nav post-nav\">" +
                        "<li><a href=\"#\"><i class=\"fa fa-tag\"></i>Creative</a></li>" +
                        "<li><a href=\"#\"><i class=\"fa fa-heart\"></i>32 Love</a></li>" +
                        "<li><a href=\"#\"><i class=\"fa fa-comments\"></i>3 Comments</a></li>" +
                        "</ul>" +
                        "</div>" +
                        "</div>" +
                        "</div>" +
                        "</div>");
                }
            }else{
                alert(data);
            }
        },
        error:function(request){
            alert(data);
        }
    },"json");

    /**
     * 刷新页面
     */
    function refreshPage(){
        window.location.reload();
    }
});