function validarEnteros(evt) {

	keynum = (document.all) ? evt.keyCode : evt.which;
	// ESPACIO=32
	// BORRAR=8
	// ENTER=13
	// alert("keynum " + keynum);

	if ((keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13) {
		return true;
	} else {
		return false;
	}
}

function validarDoubles(evt) {

	keynum = (document.all) ? evt.keyCode : evt.which;
	// ESPACIO=32
	// BORRAR=8
	// ENTER=13
	// alert("keynum " + keynum);
	if ((keynum > 45 && keynum < 58) || keynum == 8 || keynum == 13) {
		return true;
	} else {

		return false;
	}
}

function validarNombres(evt) {
	keynum = (document.all) ? evt.keyCode : evt.which;
	// ESPACIO=32
	// BORRAR=8
	// ENTER=13
	// alert("keynum "+keynum);
	// digitos
	if (!(keynum > 47 && keynum < 58) || keynum == 8 || keynum == 13
			|| keynum == 32) {
		return true;
	} else {
		return false;
	}
}

function validarEmail(valor) {
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3,4})+$/.test(valor)) {
		alert("La dirección de email " + valor + " es correcta.");
	} else {
		alert("La dirección de email es incorrecta.");
	}
}
