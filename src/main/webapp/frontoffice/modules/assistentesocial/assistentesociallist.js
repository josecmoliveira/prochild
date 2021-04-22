/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getAssistentesList();
});

function getAssistentesList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/AssistenteSocialController',
            data: {'pwhat': 'findAllAssistentes'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.assistente).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<tr><td>' + json.assistente[i].user_id+ '</td><td>' + json.assistente[i].nome + '</td><td>' + json.assistente[i].email + '</td><td>' + json.assistente[i].nif + '</td></tr>');
                       $('#tabelaassistente').append(row);
                       console.log(json.lenght);
        }
            }
        });
}