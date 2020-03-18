package com.desafio.vibe.desafio.api;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.servicos.ServicoDeputados;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000")
public class DeputadosController {

    private ServicoDeputados servico;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("teste")
    public ResponseEntity<List<RecursoDeputado>> getDeputados() {
        return new ResponseEntity<>(servico.obterTodosDeputados(), HttpStatus.OK);
    }

}
