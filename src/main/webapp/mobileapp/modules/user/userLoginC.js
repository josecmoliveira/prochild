function userLoginC() {
    $.ajax({
            url: 'http://localhost:8080/prochild/UserController',
            data: {'pwhat': 'userLoginC', "username": document.getElementById("inputUsername").value, "password": document.getElementById("inputPassword").value},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(typeof json.result);
                
                if (json.result){
                    if(!json.isCliente){
                        alert("User não é um Cliente");
                        return;
                    }
                    window.location.replace('PagInicial.html');
                    localStorage.setItem("userIdE", json.userId);
                }else{
                    alert("Password errada");
                }
            }
            
        });
};

