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
});
