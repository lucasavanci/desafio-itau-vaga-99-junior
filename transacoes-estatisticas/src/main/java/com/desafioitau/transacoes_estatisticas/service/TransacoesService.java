package com.desafioitau.transacoes_estatisticas.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafioitau.transacoes_estatisticas.controller.TransacoesRequestDTO;
import com.desafioitau.transacoes_estatisticas.exceptions.ExceptionConstants;
import com.desafioitau.transacoes_estatisticas.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class TransacoesService {

	private final List<TransacoesRequestDTO> listaTransacoes = new ArrayList<>();

	// POST /transacao
	public TransacoesRequestDTO criarTransacao(TransacoesRequestDTO dto) {

		log.info("Iniciando processo de validacao, valor: {} e dataHora: {}", dto.valor(), dto.dataHora());

		if (dto.dataHora() == null || dto.valor() == null) {
			log.info("Favor inserir um valor e dataHora valida");
			throw new UnprocessableEntity(ExceptionConstants.DATAHORA_VALOR_NULLO.getMessage());
		}

		if (dto.valor() < 0) {
			log.info("Favor inserir um valor maior ou igual a zero");
			throw new UnprocessableEntity(ExceptionConstants.VALOR_NEGATIVO.getMessage());
		}

		if (dto.dataHora().isAfter(OffsetDateTime.now())) {
			log.info("DataHora invalida. Maior que o momento");
			throw new UnprocessableEntity(ExceptionConstants.DATAHORA_INVALIDA.getMessage());
		}

		log.info("Adicionando transacao, valor: {} e dataHora: {}", dto.valor(), dto.dataHora());

		listaTransacoes.add(dto);

		return dto;
	}

	// DELETE /transacao
	public void deletarTransacao() {

		log.info("Apagando transacoes.");
		listaTransacoes.clear();
	}

	// GET /estatistica
	public List<TransacoesRequestDTO> listarTransacoes(Integer intervalo) {

		log.info("Calculando intervalo de {} segundos...", intervalo);
		OffsetDateTime intervalo60 = OffsetDateTime.now().minusSeconds(intervalo);
		log.info("Intervalo gerado: {}", intervalo60);

		log.info("Gerando lista...");
		return listaTransacoes.stream().filter(transacoes -> transacoes.dataHora().isAfter(intervalo60)).toList();
	}
}