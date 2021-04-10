/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    getClientesList();
});

function getClientesList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/ClienteController',
            data: {'pwhat': 'findAllClientes'},
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
