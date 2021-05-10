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
                    if(i==0){
                       var row = $('<div class="carousel-item active" style="margin: 40px"><div class="carousel-caption"><h3 style="color: black;"><a id="link1" target="_self" href="'
                               + json.noticia[i].link +'">' + json.noticia[i].nome+ '</h3><p style="color: black;">' +
                               json.noticia[i].descricao + '</p></div></div>');
                       $('#noticiaslide').append(row);
                   } else{var row = $('<div class="carousel-item"><div class="carousel-caption"><h3 style="color: black;"><a id="link1" target="_self" href="'
                               + json.noticia[i].link +'">' + json.noticia[i].nome+ '</h3><p style="color: black;">' +
                               json.noticia[i].descricao + '</p></div></div>');
                       $('#noticiaslide').append(row);}
                       
                       
    }   
            }
        });
};