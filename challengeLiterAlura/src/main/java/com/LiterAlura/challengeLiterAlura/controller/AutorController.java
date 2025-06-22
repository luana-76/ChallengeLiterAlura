package com.LiterAlura.challengeLiterAlura.controller;


import org.springframework.web.bind.annotation.*;
import com.LiterAlura.challengeLiterAlura.autor.Autor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private List<Autor> autores = new ArrayList<>();
    private AtomicLong contador = new AtomicLong();

    @GetMapping
    public List<Autor> listarTodos() {
        return autores;
    }

    @PostMapping
    public Autor salvar(@RequestBody Autor autor) {
        autor.setId(contador.incrementAndGet());
        autores.add(autor);
        return autor;
    }
}
