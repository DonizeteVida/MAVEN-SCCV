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

function testaSenha(campoSenha){
	var primeiraSenha = document.getElementById("formCadastro:campoSenha");
	if(!(campoSenha.value == primeiraSenha.value)){
		campoSenha.value = "";
		
		PF('growlWV').renderMessage({
			"summary" : "SENHA INCORRETA !",
			"detail" : "Por favor, insira senhas corretas !",
			"severity" : "error"
		})
	}
}
