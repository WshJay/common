$(function () {
    /*fileElementId其实就是<input id="file" name="file">里的"id=file"
     上传多个只需要改变id="file1"即可,name还是"file",后台会去获取到的 @RequestParam("file")
     批量上传的时候 fileElementId:"[file1,file2]",数组arrayids
     后台获取方法:
     第一种:MultipartFile[] files
     第二种:
     MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
     // 取得request中的所有文件名
     Iterator<String> iter = multiRequest.getFileNames();
     while (iter.hasNext()) {
     String fileName = iter.next();
     // 取得上传文件
     MultipartFile file = multiRequest.getFile(fileName);
     第三种:批量上传
     <input type="file" multiple="true" name="file"/>
     MultipartFile[] files
     MultipartFile file = multiRequest.getFile(fileName);
     都可以接收
     }*/
    function upload_file(show_id,file_url_id) {
        $.ajaxFileUpload({
            url : $("#basePath").val() +  "/upload_file.do",
            secureuri : false,
            fileElementId : "file",
            dataType : "text",
            success : function(data) {
                var file_url=distResData(data);
                $("#" + show_id+ "").attr("src",file_url);
                $("#" + file_url_id+ "").val(file_url);
            }
        });
    }

    
});