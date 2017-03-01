
COMMON.V.Ajax.json("/apis/resteasy/file/image/list",null,"GET",function(data) {
    if(data.success){
        var beforeDate = null;
        var num = 1;
        var position = "padding-right arrow-right wow fadeInLeft";
        var obj = eval(data.data);
        var content = null;
        for (var i= 0; i < obj.length; i++) {
            var date = new Date(obj[i].gmtCreated); //实例一个时间对象；
            var year = date.getFullYear();   //获取系统的年；
            var month = date.getMonth() + 1;   //获取系统月份，由于月份是从0开始计算，所以要加1
            var day = date.getDate(); // 获取系统日
            // var hour = date.getHours(); //获取系统时，
            // var minute = date.getMinutes(); //分
            // var second = date.getSeconds(); //秒
            // alert(year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second);
            var ym = year + "年" + month + "月";
            if (i == 0){
                content = "<div class=\"timeline-blog overflow padding-top\">"
                    +" <div class=\"timeline-date text-center\">"
                    +"<a href=\"#\" class=\"btn btn-common uppercase\">" + ym + "</a>"
                    +"</div><div class=\"timeline-divider overflow padding-bottom\">";
            }
            if(beforeDate != null && ym != beforeDate){
                content = content + "</div></div>";
                $(".online-content").append(content);
                content = "<div class=\"timeline-blog overflow\">"
                    +" <div class=\"timeline-date text-center\">"
                    +"<a href=\"#\" class=\"btn btn-common uppercase\">" + ym + "</a>"
                    +"</div><div class=\"timeline-divider overflow padding-bottom\">";
                num = 1;
            }
            if (num%2 == 0){
                position = "padding-left padding-top arrow-left wow fadeInRight";
            }else{
                position = "padding-right arrow-right wow fadeInLeft";
            }
            content = content
                +" <div class=\"col-sm-6 " + position + "\" data-wow-duration=\"1000ms\" data-wow-delay=\"300ms\">"
                +" <div class=\"single-blog timeline\">"
                +" <div class=\"single-blog-wrapper\">"
                +" <div class=\"post-thumb\">"
                +" <img src=\"" + obj[i].coverPath + "\" class=\"img-responsive\" alt=\"\">"
                +" <div class=\"post-overlay\">"
                +" <span class=\"uppercase\"><a href=\"#\">"+ day + "日<br><small>" + month + "月</small></a></span>"
                +" </div>"
                +" </div>"
                +" </div>"
                +" <div class=\"post-content overflow\"><h2 class=\"post-title bold\"><a href=\"blogdetails.html\">" + obj[i].name + "</a></h2>"
                +" <h3 class=\"post-author\"><a href=\"#\">来自细节狂</a></h3>"
                +" <p>"+ obj[i].description + "</p><a href=\"#\" class=\"read-more\">查看更多</a>"
                +" <div class=\"post-bottom overflow\"><span class=\"post-date pull-left\">" + year+"年"+month+"月"+day+"日" + "</span>"
                // +" <span class=\"comments-number pull-right\"><a href=\"#\">3comments</a></span>"
                +" </div>"
                +" </div>"
                +" </div>"
                +" </div>";
            if(i == obj.length-1){
                content = content + "</div></div>";
                $(".online-content").append(content);
            }else{
                beforeDate = ym;
                num++;
            }
        }
        // AJAX執行后加載此JS
        $.getScript("js/main.js");  //加载js文件
        $.getScript("js/wow.min.js");  //加载js文件
    }
}, "json");