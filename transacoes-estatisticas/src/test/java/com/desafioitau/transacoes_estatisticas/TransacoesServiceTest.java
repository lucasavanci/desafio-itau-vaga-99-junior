package com.desafioitau.transacoes_estatisticas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.desafioitau.transacoes_estatisticas.controller.TransacoesRequestDTO;
import com.desafioitau.transacoes_estatisticas.service.TransacoesService;

public class TransacoesServiceTest {
    
    private TransacoesService transacoesService = new TransacoesService();

    @Test
    public void criarTransacao(){
        TransacoesRequestDTO transacao = new TransacoesRequestDTO(123.45, OffsetDateTime.parse("2025-02-10T21:47:30.000Z"));
        transacoesService.criarTransacao(transacao);

        List<TransacoesRequestDTO> listaTransacoes = transacoesService.listarTransacoes(60);
        assertEquals(1, listaTransacoes.size());
    }

}
