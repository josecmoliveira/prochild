/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function submitVideo(){
            $.ajax({
                url: 'http://localhost:8080/prochild/VideoController',
                data: {'pwhat': 'insertVideo', "nome": document.getElementById("nome").value, "link": document.getElementById("link").value, "descricao": document.getElementById("descricao").value, "disponivel": 1, "assistente_id": 1},
                beforeSend: function (xhr) {                
                    console.log("Vai ser adicionado um video");
            },
                success: function (data) {
                    console.log("Video adicionado");
            }
        });
    }

