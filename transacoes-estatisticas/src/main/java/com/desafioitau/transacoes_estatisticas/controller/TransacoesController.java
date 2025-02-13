package com.desafioitau.transacoes_estatisticas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.transacoes_estatisticas.service.TransacoesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transacao")
public class TransacoesController {

    private final TransacoesService transacoesService;

    @PostMapping
    public ResponseEntity<TransacoesRequestDTO> criarTransacao(@RequestBody TransacoesRequestDTO dto) {

        TransacoesRequestDTO transacaoCriada = transacoesService.criarTransacao(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoCriada);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacao(){

        transacoesService.deletarTransacao();

        return ResponseEntity.ok().build();
    }

}
