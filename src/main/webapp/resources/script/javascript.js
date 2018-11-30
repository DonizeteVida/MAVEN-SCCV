/**
 * 
 */

function TestaCPF(cpf) {
	cpf = cpf.replace(".", "");
	cpf = cpf.replace("-", "");
	cpf = cpf.replace(".", "");

	if (cpf.length !== 11
			|| [ '00000000000', '11111111111', '22222222222', '33333333333',
					'44444444444', '55555555555', '66666666666', '77777777777',
					'88888888888', '99999999999' ].includes(cpf)) {
		return false;
	}

	soma = 0;
	for (i = 0; i < 9; i++) {
		soma += parseInt(cpf.charAt(i)) * (10 - i);
	}
	resto = 11 - (soma % 11);
	if (resto == 10 || resto == 11) {
		resto = 0;
	}
	if (resto != parseInt(cpf.charAt(9))) {
		return false;
	}
	soma = 0;
	for (i = 0; i < 10; i++) {
		soma += parseInt(cpf.charAt(i)) * (11 - i);
	}
	resto = 11 - (soma % 11);
	if (resto == 10 || resto == 11) {
		resto = 0;
	}
	if (resto != parseInt(cpf.charAt(10))) {
		return false;
	}
	return true;
}

function avisoGrowl(bool) {
	if (!bool) {
		PF('growlWV').renderMessage({
			"summary" : "CPF incorreto !!!",
			"detail" : "Por favor, insira um CPF válido",
			"severity" : "warn"
		})
	}
}

function desligaBtCriarUsuario() {
	PF('btCriarUsuario').disable();
}

function ligaBtCriarUsuario() {
	PF('btCriarUsuario').enable();
}

function testaSenha(campoSenha) {
	var primeiraSenha;

	if (document.getElementById("formCadastro:campoSenha") == null) {
		primeiraSenha = document.getElementById("formSenha:campoSenha");
	} else {
		primeiraSenha = document.getElementById("formCadastro:campoSenha");
	}

	if (!(campoSenha.value == primeiraSenha.value)) {
		campoSenha.value = "";

		PF('growlWV').renderMessage({
			"summary" : "SENHA INCORRETA !",
			"detail" : "Por favor, insira senhas corretas !",
			"severity" : "error"
		})
	}
}

function verifica() {
	var senha = null;

	if (document.getElementById("formSenha:campoSenha") == null) {
		senha = document.getElementById("formCadastro:campoSenha").value;
	} else {
		senha = document.getElementById("formSenha:campoSenha").value;
	}

	forca = 0;

	if ((senha.length >= 6) && (senha.length <= 8)) {
		forca += 10;
	} else if (senha.length > 8) {
		forca += 25;
	}
	if (senha.match(/[a-z]+/)) {
		forca += 10;
	}
	if (senha.match(/[A-Z]+/)) {
		forca += 20;
	}
	if (senha.match(/d+/)) {
		forca += 20;
	}
	if (senha.match(/W+/)) {
		forca += 25;
	}
	return mostra_res(forca);
}

function mostra_res(forca) {

	var result = 0;

	if (forca < 30) {
		result = 1;
	} else if ((forca >= 30) && (forca < 60)) {
		result = 2;
	} else if ((forca >= 60) && (forca < 85)) {
		result = 3;
	} else {
		result = 4;
	}

	switch (result) {
	case 1:
		PF('growlWV').renderMessage({
			"summary" : "Campo senha",
			"detail" : "Senha fraca !",
			"severity" : "error"
		});

		PF('growlWV')
				.renderMessage(
						{
							"summary" : "Regra à senha: ",
							"detail" : "As regras buscam dar força à senha. Conter mais de 8 caracteres,"
									+ " letras maiúsculas, números e simbolos podem ajudar a construir uma"
									+ " senha mais segura.",
							"severity" : "info"
						});

		if (document.getElementById("formSenha:campoSenha") == null) {
			document.getElementById("formCadastro:campoSenha").value = "";
		} else {
			document.getElementById("formSenha:campoSenha").value = "";
		}

		break;

	case 2:
		PF('growlWV').renderMessage({
			"summary" : "Campo senha",
			"detail" : "Senha justa !",
			"severity" : "warn"
		});

		break;

	case 3:
		PF('growlWV').renderMessage({
			"summary" : "Campo senha",
			"detail" : "Senha forte !",
			"severity" : "warn"
		});

		break;

	case 4:

		PF('growlWV').renderMessage({
			"summary" : "Campo senha",
			"detail" : "Senha excelente !",
			"severity" : "info"
		});

		break;

	}

}
