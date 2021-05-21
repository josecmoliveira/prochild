/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var id;
var id2;

/*function getAssistentesList() {
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
                       var row = $('<tr><td style="display:none;">' + json.assistente[i].user_id + '</td><td><button class="block">' + json.assistente[i].nome + '</button></td></tr>');
                       $('#tabelaassistentes').append(row);
                       console.log(json.lenght);
                       $('button').click ( function() {
                            var row = $(this).closest('tr');
                            row1 = row.find('td:eq(0)').text();
                            id = row1;
                            setId(id);
                            console.log(row1);
                            window.location.replace('Chat2.html');
                        })
        }
            }
        });
}*/

function getConversasList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/ChatController',
            data: {'pwhat': 'findAllConversasForUser', 'userId': localStorage.getItem("userIdE") },
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.conversas).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<tr><td style="display:none;">' + json.conversas[i].conversa_id + '</td><td><button class="block">' + json.conversas[i].other_user.nome + '</button></td></tr>');
                       $('#tabelaassistentes').append(row);
                       console.log(json.lenght);
                       $('button').click ( function() {
                            var row = $(this).closest('tr');
                            row1 = row.find('td:eq(0)').text();
                            row2 = row.find('td:eq(1)').text();
                            id = row1;
                           setNome(row2); 
                           setId(id);
                            console.log(row1);
                            window.location.replace('Chat2.html');
                        })
        }
            }
        });
}

function setId(val) {
    id = val;
    localStorage.setItem("id", id);
           
}

function setNome(val) {
    id2 = val;
    localStorage.setItem("idAssis", id2);
                     
}

function nomeAssis(){
    var assis = localStorage.getItem("idAssis");
    document.getElementById("nomeA").innerHTML = assis;
}


function getMensagensList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/ChatController',
            data: {'pwhat': 'findAllMensagensInConversa', 'conversaId': localStorage.getItem("id") },
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.conversas).length;
                console.log(count);
                console.log(localStorage.getItem("userIdE"));
                for (var i=0; i<count; i++) {
                    if (json.conversas[i].user.user_id == localStorage.getItem("userIdE")) {
                           console.log(json.conversas[i].user.user_id == localStorage.getItem("userIdE"));
                           console.log(json.conversas[i]);
                    control = '<li style="width:100%">' +
                            '<div class="msj-rta macro">' +
                            '<div class="text text-l">' +
                            '<p>' + json.conversas[i].conteudo + '</p>' +
                            '<p><small>bruh</small></p>' +
                            '</div>' +
                            '</div>' +
                            '</li>';
                    $("ul").append(control);
                } else {
                    control = '<li style="width:100%;">' +
                            '<div class="msj macro">' +
                            '<div class="text text-r">' +
                            '<p>' + json.conversas[i].conteudo + '</p>' +
                            '<p><small></small></p>' +
                            '</div>' +
                            '<div class="avatar" style="padding:0px 0px 0px 10px !important"></div>' +
                            '</li>';
                    $("ul").append(control);
                }            
        }
          
            }
        });
}


function submitMensagem(){
            $.ajax({
                url: 'http://localhost:8080/prochild/ChatController',
                data: {'pwhat': 'sendMensagem', "userId": localStorage.getItem("userIdE"), "message": document.getElementById("mensagem").value, "conversaId": localStorage.getItem("id")},
                beforeSend: function (xhr) {                
                    console.log("Vai ser enviada uma mensagem");
                    console.log(document.getElementById("mensagem").value);
                    console.log(localStorage.getItem("userIdE"));
                    console.log(localStorage.getItem("id"));
            },
                success: function (data) {
                    console.log("Mensagem adicionado");
                    document.getElementById("mensagem").value = '';
            }
        });
    }