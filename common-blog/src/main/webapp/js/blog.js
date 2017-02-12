$(function () {
    var basePath = "http://common.wsh.org";
    $.ajax({
        url:"/apis/resteasy/blog/list",
        type:"GET",
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".content").append("<div class=\"col-sm-12 col-md-12\">" +
                        "<div class=\"single-blog single-column\">" +
                        "<div class=\"post-thumb\">" +
                        "<a href=\"blogdetails.html?blogId=" + obj[i].id + "\"><img src=\"" + obj[i].coverUrl + "\" class=\"img-responsive\" alt=\"\"></a>" +
                        "<div class=\"post-overlay\">" +
                        "<span class=\"uppercase\"><a href=\"#\">" + obj[i].day + "<br><small>" + obj[i].month + "</small></a></span>" +
                        "</div>" +
                        "</div>" +
                        "<div class=\"post-content overflow\">" +
                        "<h2 class=\"post-title bold\"><a href=\"blogdetails.html?blogId=" + obj[i].id + "\">" + obj[i].title + "</a></h2>" +
                        "<h3 class=\"post-author\"><a href=\"#\">来自 " + obj[i].authorName + "</a></h3>" +
                        "<p>" + obj[i].contentId +"[...]</p>" +
                        "<a href=\"\" class=\"read-more\">查看更多</a>" +
                        "<div class=\"post-bottom overflow\">" +
                        "<ul class=\"nav navbar-nav post-nav\">" +
                        "<li><a href=\"#\"><i class=\"fa fa-tag\"></i>Creative</a></li>" +
                        "<li><a href=\"#\"><i class=\"fa fa-heart\"></i>" + obj[i].praiseNum + " 赞</a></li>" +
                        "<li><a href=\"#\"><i class=\"fa fa-comments\"></i>" + obj[i].commentNum + " 评论</a></li>" +
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

    $.ajax({
        url:"/apis/resteasy/blog/tags/list",
        type:"GET",
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".tags-list").append("<li><a href=\"" + obj[i].id + "\">" + obj[i].name + "</a></li>");
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

/**
 * 添加博客
 */
function addBlog() {
    var title = $("#title").val();
    var content = ue.getContent();
    var tags = $("#tags").val();
    var privacy = $("#privacy").val();
    $.ajax({
        url:"/apis/resteasy/blog/add",
        data:{'title':title,'content':content,'tags':tags,'privacy':privacy},
        type:"POST",
        success:function(data){
            if(data.success){
                alert("添加成功");
            }else{
                alert(data);
            }
        },
        error:function(request){
            alert(data);
        }
    },"json");
}