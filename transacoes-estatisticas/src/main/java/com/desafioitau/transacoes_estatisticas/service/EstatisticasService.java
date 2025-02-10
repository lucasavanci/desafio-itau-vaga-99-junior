package com.desafioitau.transacoes_estatisticas.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafioitau.transacoes_estatisticas.controller.EstatisticasResponseDTO;
import com.desafioitau.transacoes_estatisticas.controller.TransacoesRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class EstatisticasService {

    private final TransacoesService transacoesService;

    public EstatisticasResponseDTO calcularEstatistica(Integer intervalo) {

        List<TransacoesRequestDTO> transacoes = transacoesService.listarTransacoes(intervalo);

        if (transacoes.isEmpty()) {
            return new EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticaTransacoes = transacoes.stream().mapToDouble(TransacoesRequestDTO::valor)
                .summaryStatistics();

        return new EstatisticasResponseDTO(estatisticaTransacoes.getCount(),
                estatisticaTransacoes.getSum(),
                estatisticaTransacoes.getAverage(),
                estatisticaTransacoes.getMin(),
                estatisticaTransacoes.getMax());

    }

}
