/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var id;

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
                        var button = document.createElement("button");
                        var button1 = (button.innerHTML = '<a id="link1" target="_self" href="video2.html">' + json.video[i].nome + '</a>');
                        
                       var row = $('<tr><td style="display:none;">' + json.video[i].videoId+ '</td><td style="float: left; clear:none; "><iframe width="400" height="280" src="'+ json.video[i].link +'" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></td><td style="clear:none; ">' + button1 + '</td>\n\
                       <td><p>' + json.video[i].descricao + '</p></td></tr>');
                       $('#tabelavideos').append(row);
                       console.log(json.lenght);
                       $('a').click ( function() {
                            var row = $(this).closest('tr');
                            row1 = row.find('td:eq(0)').text();
                            id = row1;
                            setId(id);
                            console.log(id);
                        })
                }   
            }
        });
}



function setId(val) {
    id = val;
    localStorage.setItem("id", id);
    console.log();    
}


function getVideo() {
    var newid = localStorage.getItem("id");
    $.ajax({
            url: 'http://localhost:8080/prochild/VideoController',
            data: {'pwhat': 'findVideoById', 'videoId': newid},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                console.log(data);
                var json = $.parseJSON(data);
                console.log(json);
                    document.getElementById("nomevideo").textContent += json.video[0].nome;
                     document.getElementById("linkvideo").textContent += json.video[0].link;
                    document.getElementById("descricaovideo").textContent += json.video[0].descricao;
                    
                    console.log(json.video[0].descricao);
                   
            }
        });
}


function updateVideo(){
            var newid = localStorage.getItem("id");
            $.ajax({
                url: 'http://localhost:8080/prochild/VideoController',
                data: {'pwhat': 'updateVideo', "videoId": newid, "nome": document.getElementById("nomevideo").value,
                    "link": document.getElementById("linkvideo").value, "descricao": document.getElementById("descricaovideo").value,
                    "disponivel": 1},
                beforeSend: function (xhr) {                
                    console.log("O video vai ser atualizado");
                    
            },
                success: function (data) {
                    console.log("Video Atualizado");
                    window.location.replace('videos.html');
            }
        });
    }