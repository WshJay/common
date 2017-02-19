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
                alert(data.errorMsg);
            }
        },
        error:function(request){
            alert(data.errorMsg);
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
    $.ajaxFileUpload({
        url : "/apis/resteasy/file/upload/image",
        secureuri : false,
        fileElementId : "imgfile",
        dataType : "json",
        success : function(data) {
            if(data.success){
                $("#" + show_id+ "").attr("src",data.data.filePath);
                $("#filePath").val(data.data.filePath);
            }else{
                alert("上传图片失败!");
            }
        }
    });
}

/**
 * 作品发布
 */
function addPaint(){
    var name = $("#fileName").val();
    var tagsId = $(".tags-list .active a").attr("id");
    var filePath = $("#filePath").val();
    if(isNull(name)){
        alert("SB,写一下作品名称!");
    }else if(isNull(tagsId)){
        alert("SB,选择作品标签啦!");
    }else if(isNull(filePath)){
        alert("SB,作品图片都不上传,你发布毛线啊!");
    }
    $.ajax({
        url:"/apis/resteasy/file/add",
        data:{"name":name,"tagsId":tagsId,"filePath":filePath},
        type:"POST",
        success:function(data){
            if(data.success){
                alert("发布成功!");
            }else{
                alert(data.errorMsg);
            }
        },
        error:function(request){
            alert(data);
        }
    },"json");
}
