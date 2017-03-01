$(function(){
    $.ajax({
        url:"/apis/resteasy/file/tags/list/2",
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
    if(isNull(pic)){
        return;
    }
    var formData = new FormData();
    formData.append("imgfile" , pic);
    if (flag) {
        COMMON.V.Ajax.upload("/apis/resteasy/file/upload/image", formData, function (data) {
            flag = true;
            layer.close(layerId);
            if (data.success) {
                $("#" + show_id + "").attr("src", data.data.filePath);
                $("#filePath").val(data.data.filePath);
            } else {
                layer.alert(data.errorMsg, {
                    icon: 2
                });
            }
        }, "json");
    }
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

    if (flag){
        COMMON.V.Ajax.json("/apis/resteasy/file/add",{"name":name,"tagsId":tagsId,"filePath":filePath},"POST",function(data) {
            flag = true;
            layer.close(layerId);
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
        }, "json");
    }
}

function goPortfolio() {
    window.location.href = "/portfolio.html";
}

// var t_img; // 定时器
// var isLoad = true; // 控制变量
//
// function getImgSize(url){
//     $.ajax({
//         url:url,
//         cache:true,
//         beforeSend:function(xhr){
//             layerId = layer.open({
//                 type: 1,
//                 title: '上传进度',
//                 closeBtn: 0,
//                 shadeClose: true,
//                 area: ['780px', '108px'], //宽高
//                 content: progressHtml
//             });
//         },
//         complete: function(){
//             $(".upload-progress").html("100%" );
//             $(".upload-progress").css("width" , "100%");
//             layer.close(layerId);
//             clearTimeout(t_img); // 清除定时器
//         },
//         success:function(a,b,xhr){
//             total = xhr.getResponseHeader("Content-Length")/1024;
//             num++;
//             t_img = setTimeout(downloadProgress,1000);
//         }
//     })
// }
// getImgSize("/upload/images/1488123108251.png");
