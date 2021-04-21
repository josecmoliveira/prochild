/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getAssistentesList();
    getAssistente();
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
            }
        });
}

function getAssistente() {
    $.ajax({
            url: 'http://localhost:8080/prochild/AssistenteSocialController',
            data: {'pwhat': 'findAssistenteById', 'assistenteId': 1},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                    $("#inputName").val(json.assistente[0].nome);
                    $('#inputUsername').val(json.assistente[0].user_id);
                    $('#inputEmailAddress').val(json.assistente[0].email);
                    $('#inputNIF').val(json.assistente[0].nif);
                    console.log(json.assistente[0].nome);
            }
        });
}