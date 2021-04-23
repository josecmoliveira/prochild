function submitNoticia(){
            $.ajax({
                url: 'http://localhost:8080/prochild/NoticiaController',
                data: {'pwhat': 'insertNoticia', "nome": document.getElementById("nome").value, "link": document.getElementById("link").value, "descricao": document.getElementById("descricao").value, "assistente_id": 1},
                beforeSend: function (xhr) {                
                    console.log("Vai ser adicionado uma noticia");
            },
                success: function (data) {
                    console.log("Noticia adicionada");
            }
        });
    }