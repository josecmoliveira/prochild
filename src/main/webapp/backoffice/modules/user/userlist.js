/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    getUserList();
    //getUser();
});

function getUserList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/UserController',
            data: {'pwhat': 'findAllUsers'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
            }
        });
};

/*function getUser() {
    $.ajax({
            url: 'http://localhost:8080/prochild/UserController/',
            data: {'pwhat': 'findUser'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
            }
        });
};*/