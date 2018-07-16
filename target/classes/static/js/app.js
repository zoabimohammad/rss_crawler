$(document).ready(function(){
    var texts = [];
    $("#addurl").click(function(){
        var curl = $("#urltocrawl").val();
        texts.push(curl.toString());
        console.log("Current URL is " + curl);
        $("ul").append('<li>' + curl +'</li>');
    });

    $("#crawlurl").click(function(){
        $("#rssfeed").html("");
        $.ajax({
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8081/rss_feed",
            type: "POST",
            data: JSON.stringify(texts) ,
            dataType: "json",
            success: function (json) {
               $.each(json, function(idx, topic){
                     $("#rssfeed").append('<div><a href="' + topic.link + '">' + topic.title + "</a><h6>Published On: " + topic.publishedDate + "</h6><h6>By:" + topic.author + "</h6><hr></div>");
               });
            },
            error: function(jqXHR, textStatus, errorThrown) {
               console.log(textStatus, errorThrown);
            }
        });
    });
});