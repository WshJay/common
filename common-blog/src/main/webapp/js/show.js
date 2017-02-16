$(function () {
    $.ajax({
        url:"/apis/resteasy/file/tags/list/2",
        type:"GET",
        async: false,  // true为异步，false为同步
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".tags-list").append("<li><a class=\"btn btn-default\" href=\"#\" data-filter=\"." + obj[i].id + "\">" + obj[i].name + "</a></li>");
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
        url:"/apis/resteasy/file/image/list",
        type:"GET",
        async: false,  // true为异步，false为同步
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".image-list").append("<div class=\"col-xs-6 col-sm-4 col-md-3 portfolio-item " +  obj[i].tagsId + "\" >" +
                    "<div class=\"portfolio-wrapper\">" +
                    "<div class=\"portfolio-single\">" +
                    "<div class=\"portfolio-thumb\">" +
                    "<img class=\"cover-" + obj[i].id + "\" src=\"" + obj[i].coverPath + "\" class=\"img-responsive\" alt=\"\">" +
                    "</div>" +
                    "<div class=\"portfolio-view\">" +
                    "<ul class=\"nav nav-pills\">" +
                    "<li><a href=\"portfolio-details.html?id=" + obj[i].id + "\"><i class=\"fa fa-link\"></i></a></li>" +
                    "<li><a class=\"file-" + obj[i].id + "\" href=\"" + obj[i].filePath + "\" data-lightbox=\"example-set\"><i class=\"fa fa-eye\"></i></a></li>" +
                    "</ul>" +
                    "</div>" +
                    "</div>" +
                    "<div class=\"portfolio-info \">" +
                    "<h2>" + obj[i].name + "</h2>" +
                    "</div>" +
                    "</div>" +
                    "</div>");
                }
                // AJAX執行后加載此JS
                $.getScript("js/main.js");  //加载js文件
            }else{
                alert(data);
            }
        },
        error:function(request){
            alert(data);
        }
    },"json");
});