

var id;

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
                       var row = $('<tr id="12"><td style="display:none;">' + json.direito[i].direitoId + '</td><td>' + '<a id="link1" href="Tópicos2.html">' + json.direito[i].nome + '</a></td></tr>');
                       $("#link1").click(function(){
                           var row1 = document.getElementById("tabelatemas").rows[i].cells.item(0).innerHTML;
                           console.log(row1);
                       })
                       $('#tabelatemas').append(row);
                       console.log(json.lenght);
        }
            }
        });
};

function setId() {
   var row1 = document.getElementById("tabelatemas").rows[i].cells.item(0).innerHTML;
   
   console.log(row1);
}


function getDireito() {
    $.ajax({
            url: 'http://localhost:8080/prochild/DireitoController',
            data: {'pwhat': 'findDireitoById', 'direitoId': 3},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                console.log(data);
                var json = $.parseJSON(data);
                console.log(json);
                    $("#nome").val(json.direito[0].nome);
                    $("#descricao").val(json.direito[0].descricao);
                    console.log(json.direito[0].nome);
            }
        });
}