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
                var count = Object.keys(json.direito).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<tr><td>' + json.direito[i].direitoId+ '</td><td>' + json.direito[i].nome + '</td><td>' + json.direito[i].assistenteId + '</td><td>' + json.direito[i].descricao + '</td></tr>');
                       $('#tabelatopicos').append(row);
                       console.log(json.lenght);
        }
            }
        });
}

