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

function populeazaListaClienti() {
	$.getJSON('listaClientiJson.json', function(clientiDest) {
		$.each(clientiDest, function(index, cont) {
			$('#selectClientDestinatie').append($('<option/>', {
				value : cont.Id,
				text : cont.Nume + " " + cont.Prenume
			}));
		});
	});
};

function populeazaListaConturiDestinatar(clientId) {
	$.ajax({
		type : "GET",
		url : "listaConturiDestinatarJson.json",
		data : {
			"clientId" : clientId
		},
		datatype : "json",
		success : function(clientiDest) {
			$('#selectConturiDestinatie').empty();
			$.each(clientiDest, function(index, data) {
				$('#selectConturiDestinatie').append($('<option/>', {
					value : data.NrCont,
					text : data.NrCont
				}));
			});
		},
		error: function(ts) {console.log(ts);alert(ts.responseText); 
		}

	});
};
$(document).ready(function() {

	populeazaListaNumereConturi();
console.log("conturi:");
	$('#selectConturi').on('change', function() {
		var idContSelectat = $(this).val();
		completeazaSumaCont(idContSelectat);
	});

	populeazaListaClienti();
	$('#selectClientDestinatie').on('change', function() {
		var idClientSelectat = $(this).val();
		populeazaListaConturiDestinatar(idClientSelectat);
		
	});

});