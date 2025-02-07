package com.desafioitau.transacoes_estatisticas.controller;

import java.time.OffsetDateTime;

import org.springframework.web.bind.annotation.RestController;

@RestController

public record TransacoesRequestDTO(Double valor, OffsetDateTime dataHora) {
	
}
