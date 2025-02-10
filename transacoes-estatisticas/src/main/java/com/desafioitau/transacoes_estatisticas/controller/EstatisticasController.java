package com.desafioitau.transacoes_estatisticas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioitau.transacoes_estatisticas.service.EstatisticasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/estatistica")
public class EstatisticasController {
    
    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticasResponseDTO> calcularEstatistica(@RequestParam(value = "intervalo", required = false, defaultValue = "60") Integer intervalo){

        return ResponseEntity.ok(estatisticasService.calcularEstatistica(intervalo));
    }

}
