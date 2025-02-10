package com.desafioitau.transacoes_estatisticas.controller;

import java.time.OffsetDateTime;

public record TransacoesRequestDTO(Double valor, OffsetDateTime dataHora) {
	
}
