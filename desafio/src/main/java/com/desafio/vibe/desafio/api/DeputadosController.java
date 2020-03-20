package com.desafio.vibe.desafio.api;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.api.recursos.RecursoDeputadoDetalhe;
import com.desafio.vibe.desafio.api.recursos.RecursoDespesa;
import com.desafio.vibe.desafio.dominio.servicos.ServicoDeputados;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("deputados")
public class DeputadosController {

    private ServicoDeputados servico;

    @GetMapping("/lista")
    public ResponseEntity<List<RecursoDeputado>> getDeputados(@RequestParam(value = "paginaAtual") Integer paginaAtual, @RequestParam(value = "itensPorPagina") Integer itensPorPagina) {
        return new ResponseEntity<>(servico.obterTodosDeputados(paginaAtual, itensPorPagina), HttpStatus.OK);
    }

    @GetMapping("/detalhe")
    public ResponseEntity<RecursoDeputadoDetalhe> getDetalheDeputado(@RequestParam(value = "deputadoId") String deputadoId, @RequestParam(value = "detalhada") boolean detalhada) {
        return new ResponseEntity<>(servico.obterDetalhesDeputado(deputadoId, detalhada), HttpStatus.OK);
    }

    @GetMapping("/mes")
    public ResponseEntity<List<RecursoDespesa>> getDetalheMes(@RequestParam(value = "deputadoId") String deputadoId, @RequestParam(value = "mes") String mes) {
        return new ResponseEntity<>(servico.obterDetalhesMes(deputadoId, mes), HttpStatus.OK);
    }


}
