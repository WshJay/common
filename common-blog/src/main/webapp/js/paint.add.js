$(function(){
    $.ajax({
        url:"/apis/resteasy/blog/tags/list",
        type:"GET",
        success:function(data){
            if(data.success){
                var content = null;
                var obj = eval(data.data);
                for (var i= 0; i < obj.length; i++) {
                    $(".tags-list").append("<li><a title=\"" + obj[i].name + "\" class=\"tags-"+ obj[i].id + "\" href=\"javascript:void(0);\">" + obj[i].name + "</a></li>");
                    tagClick();
                }
            }else{
                alert(data);
            }
        },
        error:function(request){
            alert(data);
        }
    },"json");

    function tagClick() {
        $("ul.tags-list li").click(function(){
            $(this).addClass("active").siblings().removeClass("active");
        });
    }
});