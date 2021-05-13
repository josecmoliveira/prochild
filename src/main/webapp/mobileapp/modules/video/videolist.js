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
                       var row = $('<tr><td><iframe width="100%" height="100%" margin="20px" padding="35px" src="'+ json.video[i].link 
                               +'" title="YouTube video player" frameborder="0" allow="accelerometer; \n\
                                autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></td></tr>');
                       $('#conteudo').append(row);
                       console.log(json.lenght);
                }   
            }
        });
}

