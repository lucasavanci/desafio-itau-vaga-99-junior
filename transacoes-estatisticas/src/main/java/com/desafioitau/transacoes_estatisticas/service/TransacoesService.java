package com.desafioitau.transacoes_estatisticas.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafioitau.transacoes_estatisticas.controller.TransacoesRequestDTO;
import com.desafioitau.transacoes_estatisticas.exceptions.ExceptionConstants;
import com.desafioitau.transacoes_estatisticas.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TransacoesService {

	private final List<TransacoesRequestDTO> listaTransacoes = new ArrayList<>();

	// POST /transacao
	public void criarTransacao(TransacoesRequestDTO dto) {

		if (dto.dataHora() == null) {
			throw new UnprocessableEntity(ExceptionConstants.DATAHORA_NULLO);
		}

		if (dto.valor() == null) {
			throw new UnprocessableEntity(ExceptionConstants.VALOR_NULLO);
		}

		if (dto.valor() < 0) {
			throw new UnprocessableEntity(ExceptionConstants.VALOR_NEGATIVO);
		}

		if (dto.dataHora().isAfter(OffsetDateTime.now())) {
			throw new UnprocessableEntity(ExceptionConstants.DATAHORA_INVALIDA);
		}

		listaTransacoes.add(dto);

	}

	// DELETE /transacao
	public void deletarTransacao() {

		listaTransacoes.clear();

	}

	// GET /estatistica
	public List<TransacoesRequestDTO> listarTransacoes(Integer intervalo) {

		OffsetDateTime intervalo60 = OffsetDateTime.now().minusSeconds(intervalo);

		return listaTransacoes.stream().filter(transacoes -> transacoes.dataHora().isAfter(intervalo60)).toList();

	}
}