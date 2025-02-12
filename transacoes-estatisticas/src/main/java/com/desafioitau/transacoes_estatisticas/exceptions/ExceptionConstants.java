package com.desafioitau.transacoes_estatisticas.exceptions;

public enum ExceptionConstants {

	DATAHORA_INVALIDA("DataHora invalida. Maior que o momento"),
	DATAHORA_VALOR_NULLO("Favor inserir um valor e dataHora valida"),
	VALOR_NEGATIVO("Favor inserir um valor maior ou igual a zero");

    private final String message;

    ExceptionConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}