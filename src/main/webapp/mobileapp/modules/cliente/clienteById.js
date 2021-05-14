$(document).ready(function () {
    getCliente();
});

function getCliente() {
    $.ajax({
            url: 'http://localhost:8080/prochild/ClienteController',
            data: {'pwhat': 'findClienteById', 'cliente_userId':localStorage.getItem("userIdE")},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                    $("#inputName").val(json.cliente[0].nome);
                    $('#inputEmail').val(json.cliente[0].email);
                    $('#inputUsername').val(json.cliente[0].user_id);
                    console.log(json.cliente[0].nome);
            }
        });
}