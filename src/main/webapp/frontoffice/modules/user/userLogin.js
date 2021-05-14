
function userLogin() {
    $.ajax({
            url: 'http://localhost:8080/prochild/UserController',
            data: {'pwhat': 'userLogin', "username": document.getElementById("inputUsername").value, "password": document.getElementById("inputPassword").value},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(typeof json.result);
                
                if (json.result){
                    if(!json.isAssistenteSocial){
                        alert("User não é um Assistente Social");
                        return;
                    }
                    window.location.replace('utilizadores.html');
                    localStorage.setItem("userId", json.userId);
                }else{
                    alert("Password errada");
                }
            }
            
        });
};
