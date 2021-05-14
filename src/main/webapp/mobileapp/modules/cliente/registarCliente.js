function registarCliente() {
    $.ajax({
            url: 'http://localhost:8080/prochild/ClienteController',
            data: {'pwhat': 'registarCliente', "nome": document.getElementById("inputName").value, "email": document.getElementById("inputEmail").value, "genero": document.getElementById("inputGenero").value, "tipo": document.getElementById("inputTipo").value, "username": document.getElementById("inputUsername").value, "password": document.getElementById("inputPassword").value}, 
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                if (json.result === true){
                    console.log("Sucesso");
                    window.location.replace('Login.html');
                }else{
                    alert("Não foi possivel adicionar");
                }
                             
            }
            
        });
};
