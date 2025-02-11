package com.desafioitau.transacoes_estatisticas.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafioitau.transacoes_estatisticas.controller.EstatisticasResponseDTO;
import com.desafioitau.transacoes_estatisticas.controller.TransacoesRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class EstatisticasService {

    private final TransacoesService transacoesService;

    public EstatisticasResponseDTO calcularEstatistica(Integer intervalo) {

        log.info("Iniciando processo de listar transacoes.");

        List<TransacoesRequestDTO> transacoes = transacoesService.listarTransacoes(intervalo);

        if (transacoes.isEmpty()) {
            log.info("Nenhuma transacao encontrada. Atribuindo valor zerado.");
            EstatisticasResponseDTO estatisticas = new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
            log.info("Estatisticas: {}", estatisticas);
            return estatisticas;
        }

        DoubleSummaryStatistics estatisticaTransacoes = transacoes.stream().mapToDouble(TransacoesRequestDTO::valor)
                .summaryStatistics();

        log.info("Calculando estatisticas. {}", estatisticaTransacoes);
        return new EstatisticasResponseDTO(estatisticaTransacoes.getCount(),
                estatisticaTransacoes.getSum(),
                estatisticaTransacoes.getAverage(),
                estatisticaTransacoes.getMin(),
                estatisticaTransacoes.getMax());
    }
}
