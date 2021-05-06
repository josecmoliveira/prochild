$(document).ready(function () {
    getNoticiasList();
});

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
                       var row = $('<tr><td>' + json.noticia[i].noticiaId+ '</td><td>' + json.noticia[i].nome + '</td><td>' + json.noticia[i].link + '</td><td>' + json.noticia[i].descricao + '</td></tr>');
                       $('#tabelanoticias').append(row);
                       console.log(json.lenght);
    }   
            }
        });
};