$(function(){
    $.ajax({
        url:"/apis/resteasy/blog/tags/list",
        type:"GET",
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".tags-list").append("<li><a id=\"" + obj[i].id + "\" title=\"" + obj[i].name + "\" class=\"tags-"+ obj[i].id + "\" href=\"javascript:void(0);\">" + obj[i].name + "</a></li>");
                    tagClick();
                }
            }else{
                layer.alert(data.errorMsg, {
                    icon: 2
                });
            }
        },
        error:function(request){
            layer.alert("上传异常!", {
                icon: 2
            });
        }
    },"json");

    function tagClick() {
        $("ul.tags-list li").click(function(){
            $(this).addClass("active").siblings().removeClass("active");
        });
    }
});


// 选择图片
function uploadtrig(file_id){
    $("#" + file_id +"").trigger("click");
}

// 图片上传
function upload_file(show_id,file_url_id) {

    var pic = $("#imgfile").get(0).files[0];
    var formData = new FormData();
    formData.append("imgfile" , pic);
    $.ajax({
        type: "POST",
        url: "/apis/resteasy/file/upload/image",
        data: formData ,
        processData : false,
        //必须false才会自动加上正确的Content-Type
        contentType : false ,
        xhr: function(){
            $(".upload-progress").html("0%" );
            $(".upload-progress").css("width" ,"0");
            $(".progressbar-content").show();
            var xhr = $.ajaxSettings.xhr();
            if(onprogress && xhr.upload) {
                xhr.upload.addEventListener("progress" , onprogress, false);
                return xhr;
            }
        },
        success : function(data) {
            $(".progressbar-content").hide();
            if(data.success){
                $("#" + show_id+ "").attr("src",data.data.filePath);
                $("#filePath").val(data.data.filePath);
            }else{
                layer.alert(data.errorMsg, {
                    icon: 2
                });
            }
        }
    });
}

/**
 * 侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
 */
function onprogress(evt){
    var loaded = evt.loaded;     //已经上传大小情况
    var tot = evt.total;      //附件总大小
    var per = Math.floor(100*loaded/tot);  //已经上传的百分比
    $(".upload-progress").html( per +"%" );
    $(".upload-progress").css("width" , per +"%");
}

/**
 * 作品发布
 */
function addPaint(){
    var name = $("#fileName").val();
    var tagsId = $(".tags-list .active a").attr("id");
    var filePath = $("#filePath").val();
    if(isNull(name)){
        layer.alert("SB,写一下作品名称!", {
            icon: 2
        });
        return;
    }else if(isNull(tagsId)){
        layer.alert("SB,选择作品标签啦!", {
            icon: 2
        });
        return;
    }else if(isNull(filePath)){
        layer.alert("SB,作品图片都不上传,你发布毛线啊!", {
            icon: 2
        });
        return;
    }
    $.ajax({
        url:"/apis/resteasy/file/add",
        data:{"name":name,"tagsId":tagsId,"filePath":filePath},
        type:"POST",
        success:function(data){
            if(data.success){
                layer.alert("添加成功", {
                    icon: 1
                }, function(){
                    goPortfolio();
                });
            }else{
                layer.alert(data.errorMsg, {
                    icon: 2
                });
            }
        },
        error:function(request){
            layer.alert("添加异常!", {
                icon: 2
            });
        }
    },"json");
}

function goPortfolio() {
    window.location.href = "/portfolio.html";
}