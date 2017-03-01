/**
 * jquery Form/Ajax
 */
// EDU.V.Ajax = {
//     // 异步提交form
//     submitForm : function(formId, url, beforeSubmit, success) {
//         jQuery(formId).ajaxSubmit({
//             // dataType:'script',
//             type : 'post',
//             url : url,
//             beforeSubmit : beforeSubmit,
//             success : success,
//             error : function() {
//                 alert("jquery.form.js 提交异常");
//             },
//             uploadProgress : function(event, position, total, percentComplete) { // 请求进度
//             },
//             resetForm : false,
//             clearForm : false
//         });
//     },
//
//     // 异步ajax提交
//     ajax : function(url, param, success) {
//         // ajax
//         jQuery.ajax({
//             type : "post",
//             data : param,
//             url : url,
//             cache : false,
//             contentType : false,
//             error : function(request) {
//                 alert("Ajax错误!");
//             },
//             success : success,
//             processData : false
//         }, "json"); // ajax end
//     },
//
//     // 异步ajax提交返回html
//     ajaxHtml : function(url, param, success) {
//         // ajax
//         jQuery.ajax({
//             url : url,
//             data : param,
//             type : "POST",
//             success : success,
//             error : function(request) {
//                 alert("Ajax错误!");
//             }
//         }, "html");
//     },
//
//     // 异步ajax提交返回Json
//     ajaxJson : function(url, param, success) {
//         // ajax
//         jQuery.ajax({
//             url : url,
//             data : param,
//             type : "POST",
//             success : success,
//             error : function(request) {
//                 alert("Ajax错误!");
//             }
//         }, "json");
//     }
//
// };
/**
 * 通用方法封装
 */
if (typeof (COMMON) == "undefined" || !COMMON) {
    var COMMON = {};
    COMMON.V = {};
}
/**
 * 通用配置函数
 */
COMMON.V.config = {
    server : "http://n.ecompus.edu/",
    version : "1.0.0"
};

/**
 * 常用类
 */
COMMON.V.common = {
    getRealLenex : {
        r0 : /[^\x00-\xFF]/g,
        r1 : /[\x00-\xFF]/g
    },
    getRealLen : function(s, isUTF8) {
        if (typeof (s) != 'string') {
            return 0;
        }
        if (!isUTF8) {
            return s.replace(EDU.V.common.getRealLenex.r0, "*").length;
        } else {
            var cc = s.replace(EDU.V.common.getRealLenex.r1, "");
            return (s.length - cc.length) + (encodeURI(cc).length / 3);
        }
    },

    // 日志输出
    showDebugMsg : function(msg) {
        if (document.getElementById("debugtx")) {
            document.getElementById("debugtx").innerHTML += "[JS]>>" + msg
                + "<br/>";
        }
    }
};

var progressHtml = "<div id=\"progressbar-container\" class=\"progressbar-content\">"
    + "<h2></h2>"
    + "<div class=\"progress\">"
    + "<div class=\"progress-bar progress-bar-info upload-progress\" role=\"progressbar\" aria-valuenow=\"0\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 0\">0%"
    + "</div>"
    + "</div>"
    + "</div>";

// $.ajax({
//     type: "POST",
//     url: "/apis/resteasy/file/upload/image",
//     data: formData ,
//     processData : false,
//     //必须false才会自动加上正确的Content-Type
//     contentType : false ,
//     xhr: function(){
//         layer.open({
//             type: 1,
//             title: '进度',
//             closeBtn: 0,
//             shadeClose: true,
//             area: ['780px', '108px'], //宽高
//             content: progressHtml
//         });
//         var xhr = $.ajaxSettings.xhr();
//         if(onprogress && xhr.upload) {
//             xhr.upload.addEventListener("progress" , onprogress, false);
//             return xhr;
//         }
//     },
//     success : function(data) {
//         $(".progressbar-content").hide();
//         if(data.success){
//             $("#" + show_id+ "").attr("src",data.data.filePath);
//             $("#filePath").val(data.data.filePath);
//         }else{
//             layer.alert(data.errorMsg, {
//                 icon: 2
//             });
//         }
//     }
// });

COMMON.V.Ajax = {
    // 异步ajax提交
    upload : function (url, param, success) {
        // ajax
        jQuery.ajax({
            type: "POST",
            data: param,
            url: url,
            processData : false,
            //必须false才会自动加上正确的Content-Type
            contentType : false,
            beforeSend:function(xhr){
                flag = false;
            },
            xhr: function(){
                layerId = layer.open({
                    type: 1,
                    title: '上传进度',
                    closeBtn: 0,
                    shadeClose: true,
                    area: ['780px', '108px'], //宽高
                    content: progressHtml
                });
                var xhr = $.ajaxSettings.xhr();
                if(uploadProgress && xhr.upload) {
                    xhr.upload.addEventListener("progress" , uploadProgress, false);
                    return xhr;
                }
            },
            success: success,
            error : function(request) {
                layer.alert("上传失败!");
            }
        }, "json"); // ajax end
    },
    json : function (url, param, type, success) {
        // ajax
        jQuery.ajax({
            type: type,
            data: param,
            url: url,
            beforeSend:function(xhr){
                if(type == "POST"){
                    flag = false;
                    layerId = layer.load();
                }
            },
            success: success,
            error : function(request) {
                flag = true;
                layer.close(layerId);
                layer.alert("提交失败!");
            }
        }, "json"); // ajax end
    }
};
// 弹框ID
var layerId;
// 提交標記
var flag = true;
/**
 * 侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
 */
function uploadProgress(evt){
    var loaded = evt.loaded;     //已经上传大小情况
    var tot = evt.total;      //附件总大小
    var per = Math.floor(100*loaded/tot);  //已经上传的百分比
    $(".upload-progress").html( per +"%" );
    $(".upload-progress").css("width" , per +"%");
}
var num = 0;
var total;
/**
 * 侦查附件上传情况 ,这个方法大概0.05-0.1秒执行一次
 */
function downloadProgress(){
    var loaded = num * 125;     //已经上传大小情况
    var tot = total;      //附件总大小
    var per = Math.floor(100*loaded/tot);  //已经上传的百分比
    $(".upload-progress").html( per +"%" );
    $(".upload-progress").css("width" , per +"%");
}