

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
                    var num = document.createElement("p");
                    var num1 =(num.innerHTML = json.direito[i].direitoId);
                    //console.log(num);
                    var button = document.createElement("button");
                    var button1 = (button.innerHTML = '<a id="link1" href="Tópicos2.html">' + json.direito[i].nome + '</a>');
                    //console.log(button);
                    var fin = '<tr><td style="display:none;">' + num1 + '</td><td>' + button1 + '</td></tr>';
                    //console.log(fin);
                       $('#tabelatemas').append(fin);
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
                       
}


function getDireito() {
    $.ajax({
            url: 'http://localhost:8080/prochild/DireitoController',
            data: {'pwhat': 'findDireitoById', 'direitoId': id},
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