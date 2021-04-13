/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getDireitosList();
});

function getDireitosList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/DireitoController',
            data: {'pwhat': 'findAllDireitos'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                for (var i=0; i<data.length; i++) {
                       var row = $('<tr><td>' + data[i].direitoId+ '</td><td>' + data[i].nome + '</td><td>' + data[i].assistenteId + '</td><td>' + data[i].descricao + '</td></tr>');
                       $('#tabelatopicos').append(row);
        }
            }
        });
}

