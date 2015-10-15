$(document).ready(function(){
    var username;
    var topic;

    $("form.username-form").submit(function(event){
        var value = $(this).find("input[name=username]").val();
        username = $.trim(value);
        event.preventDefault();
        if(username) {
            registerMqtt(username);
            loadMessages(username);
        }
    });

    var source   = $("#message-template").html();
    var template = Handlebars.compile(source);

    var client  = mqtt.connect("mqtt://localhost:9001");

    function registerMqtt(username) {
        if(topic) {
            client.unsubscribe(topic);
        }
        topic = "user/" + username + "/messages";
        client.subscribe(topic);

        client.on("error", function(){
            console.log("error occurs");
        });

        client.on('message', function (topic, message) {
            $("#notification").show().fadeIn(100);
            loadMessages(username);
        });
    }


    function loadMessages(username){
        $.get('http://localhost:8080/whiz/api/users/' + username+'/messages', function(data, status){
            var html = template({messages: data});
            $("#messages").html(html);
            $("#notification").fadeOut(3000);
        });
    }
});