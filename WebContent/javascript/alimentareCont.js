function completeazaSumaCont(idCont) {
	$.ajax({
		type : "GET",
		url : "listaConturiJson.json",
		data : {
			"idCont" : idCont
		},
		datatype : "json",
		success : function(conturiClientLogat) {
			$.each(conturiClientLogat, function(index, cont) {
				if (cont.ID == idCont) {
					$("#sumaCont").val(cont.Suma);
					$("#sumaCont").text(cont.Suma);
				}
			});
		},
		error : function() {
			alert("Erroare!!!!");
		}
	});
};

function populeazaListaNumereConturi() {
	$.getJSON('listaConturiJson.json', function(conturiClientLogat) {
		$.each(conturiClientLogat, function(index, cont) {
			$('#selectConturi').append($('<option/>', {
				value : cont.ID,
				text : cont.NrCont
			}));
		});
	});
};

$(document).ready(function() {

	populeazaListaNumereConturi();

	$('#selectConturi').on('change', function() {
		var idContSelectat = $(this).val();
		completeazaSumaCont(idContSelectat);
	});

});
