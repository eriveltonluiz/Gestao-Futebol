function recarregarPagina(){
	document.location.reload(true);
}

function abrirMenupop() {
	$('#menupop').show('slow').mouseleave(function() {
		fecharMenupop();
	});
}

function fecharMenupop() {
	if ($("#menupop").is(":visible")) {
		$('#menupop').hide('slow');
	}
}