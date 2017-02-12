$(function () {
    var url = window.location.search;
    var blogId = url.split("blogId=")[1];
    $.ajax({
        url:"/apis/resteasy/blog/" + blogId,
        type:"GET",
        success:function(data){
            if(data.success){
                var obj = eval(data.data);
                $(".cover").html("<a href=\"#\"><img src=\"" + obj.coverUrl + "\" class=\"img-responsive\" alt=\"\"></a>" +
                "<div class=\"post-overlay\">" +
                "<span class=\"uppercase\"><a href=\"#\">" + obj.month +"<br><small>" + obj.day +"</small></a></span>" +
                "</div>");
                $("#title").text(obj.title);
                $("#authorName").text("来自 " + obj.authorName);
                $("#content").html(obj.content);

                $(".blog-navbar").html("<li><a href=\"#\"><i class=\"fa fa-tag\"></i>Creative</a></li>" +
                    "<li><a href=\"#\"><i class=\"fa fa-heart\"></i>" + obj.praiseNum + " 赞</a></li>" +
                    "<li><a href=\"#\"><i class=\"fa fa-comments\"></i>" + obj.commentNum + " 评论</a></li>");
                $.ajax({
                    url:"/apis/resteasy/blog/content/" + obj.contentId,
                    type:"GET",
                    success:function(data){
                        if(data.success){
                            var obj = eval(data.data);
                            $("#content").html(obj.content);
                        }else{
                            alert(data);
                        }
                    },
                    error:function(request){
                        alert(data);
                    }
                },"json");
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