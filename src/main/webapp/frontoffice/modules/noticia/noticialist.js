var id;


function getNoticiasList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/NoticiaController',
            data: {'pwhat': 'findAllNoticias'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.noticia).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var button = document.createElement("button");
                    var button1 = (button.innerHTML = '<a id="link1" target="_self" href="noticia2.html">' + json.noticia[i].nome + '</a>');
                       var row = $('<tr><td>' + json.noticia[i].noticiaId+ '</td><td>' + button1 + '</td><td>' + json.noticia[i].link + '</td>\n\
                       <td><p style="white-space: nowrap; max-width: 100px; overflow: hidden; text-overflow: ellipsis; ">' + json.noticia[i].descricao + '</p></td></tr>');
                       $('#tabelanoticias').append(row);
                       console.log(json.lenght);
                       $('a').click ( function() {
                            var row = $(this).closest('tr');
                            row1 = row.find('td:eq(0)').text();
                            id = row1;
                            setId(id);
                            console.log(id);
                        })
    }   
            }
        });
};


function setId(val) {
    id = val;
    localStorage.setItem("id", id);
    console.log();    
}


function getNoticia() {
    var newid = localStorage.getItem("id");
    $.ajax({
            url: 'http://localhost:8080/prochild/NoticiaController',
            data: {'pwhat': 'findNoticiaById', 'noticiaId': newid},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                console.log(data);
                var json = $.parseJSON(data);
                console.log(json);
                    document.getElementById("nomenoticia").textContent += json.noticia[0].nome;
                     document.getElementById("linknoticia").textContent += json.noticia[0].link;
                    document.getElementById("descricaonoticia").textContent += json.noticia[0].descricao;
                   
            }
        });
}


function updateNoticia(){
            var newid = localStorage.getItem("id");
            $.ajax({
                url: 'http://localhost:8080/prochild/NoticiaController',
                data: {'pwhat': 'updateNoticia', "noticia_id": newid, "nome": document.getElementById("nomenoticia").value,
                    "link": document.getElementById("linknoticia").value, "descricao": document.getElementById("descricaonoticia").value,
                    "disponivel": 1},
                beforeSend: function (xhr) {                
                    console.log("O video vai ser atualizado");
                    
            },
                success: function (data) {
                    console.log("Video Atualizado");
                    window.location.replace('noticias.html');
            }
        });
    }
    
    function removerNoticia(){
            var newid = localStorage.getItem("id");
            $.ajax({
                url: 'http://localhost:8080/prochild/NoticiaController',
                data: {'pwhat': 'removeNoticia', "noticia_id": newid},
                beforeSend: function (xhr) {                
                    console.log("A noticia vai ser eliminada");
                    
            },
                success: function (data) {
                    console.log("Noticia Eliminada");
                    window.location.replace('noticias.html');
            }
        });
    }