$(document).ready(function () {
    getDireitosList();
});

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
                    var button = document.createElement("button");
                    var button1 = (button.innerHTML = '<a id="link1" target="_self" href="tema2.html">' + json.direito[i].nome + '</a>');
                       var row = $('<tr><td>' + json.direito[i].direitoId+ '</td><td>' + button1 + '</td><td>' + json.direito[i].assistenteId + '</td>\n\
                       <td><p style="white-space: nowrap; max-width: 100px; overflow: hidden; text-overflow: ellipsis; ">' + json.direito[i].descricao + '</p></td></tr>');
                       $('#tabelatopicos').append(row);
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


function getDireito() {
    var newid = localStorage.getItem("id");
    $.ajax({
            url: 'http://localhost:8080/prochild/DireitoController',
            data: {'pwhat': 'findDireitoById', 'direitoId': newid},
            beforeSend: function (xhr) {                
                console.log("teste_before");
            },
            success: function (data) {
                console.log("teste_success");
                console.log(data);
                var json = $.parseJSON(data);
                console.log(json);
                    document.getElementById("nomeDireito").textContent += json.direito[0].nome;
                    document.getElementById("descricaoDireito").textContent += json.direito[0].descricao;
                    
                    console.log(json.direito[0].descricao);
            }
        });
}


function updateDescricao(){
            var newid = localStorage.getItem("id");
            $.ajax({
                url: 'http://localhost:8080/prochild/DireitoController',
                data: {'pwhat': 'updateDescricao', "direitoId": newid, "descricao": document.getElementById("descricaoDireito").value},
                beforeSend: function (xhr) {                
                    console.log("A descrição vai ser atualizada");
                    console.log(document.getElementById("descricaoDireito").value);
            },
                success: function (data) {
                    console.log("Descrição Atualizada");
            }
        });
    }