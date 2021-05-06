$(document).ready(function () {
    getDireitosList();
});

function getDireitosList() {
    $.ajax({
            url: 'http://localhost:8080/prochild/DireitoController',
            data: {'pwhat': 'findAllDireitos'},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                var json = $.parseJSON(data);
                console.log(json);
                var count = Object.keys(json.direito).length;
                console.log(count);
                for (var i=0; i<count; i++) {
                       var row = $('<tr><td><a href="Tópicos2.html">' + json.direito[i].nome + '</a></td></tr>');
                       $('#tabelatemas').append(row);
                       console.log(json.lenght);
        }
            }
        });
};

