function submitTema(){
            $.ajax({
                url: 'http://localhost:8080/prochild/DireitoController',
                data: {'pwhat': 'insertDireito', "nome": document.getElementById("nome").value, "descricao": document.getElementById("descricao").value, "assistente_id": 1},
                beforeSend: function (xhr) {                
                    console.log("Vai ser adicionado um tema");
            },
                success: function (data) {
                    console.log("Tema adicionado");
            }
        });
    }