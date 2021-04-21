/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getVideosList();
});

function getVideosList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/VideoController',
            data: {'pwhat': 'findAllVideos'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.video).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<div style="padding-bottom: 40px"><i class="fas fa-trash-alt"></i><h>'+ json.video[i].videoId +' ' +json.video[i].nome +'</h><a role="button" class="botaover">' + json.video[i].link + json.video[i].descricao +  '</a></div>');
                       $('#grid').append(row);
                       console.log(json.lenght);
        }
            }
        });
}

