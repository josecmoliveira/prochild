/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getClientesList();
});

function getNoticiasList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/NoticiaController',
            data: {'pwhat': 'findAllNoticias'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.noticia).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<tr><td>' + json.noticia[i].clienteId+ '</td><td>' + json.cliente[i].nome + '</td><td>' + json.cliente[i].email + '</td><td>' + json.cliente[i].genero + '</td><td>' + json.cliente[i].tipo + '</td></tr>');
                       $('#tabelaclientes').append(row);
                       console.log(json.lenght);
                }   
            }
        });
}