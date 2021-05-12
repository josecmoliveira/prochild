
function registarAssistente() {
    $.ajax({
            url: 'http://localhost:8080/prochild/AssistenteSocialController',
            data: {'pwhat': 'registarAssistente', "nome": document.getElementById("inputName").value, "email": document.getElementById("inputEmail").value, "nif": document.getElementById("inputNif").value, "username": document.getElementById("inputUsername").value, "password": document.getElementById("inputPassword").value}, 
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                if (json.result === true){
                    console.log("Sucesso");
                    window.location.replace('login.html');
                }else{
                    alert("Não foi possivel adicionar");
                }
                             
            }
            
        });
};

