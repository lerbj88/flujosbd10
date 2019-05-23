package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Flujos;
import org.springframework.data.domain.Page;

public interface FlujosService {
    Page<Flujos> Paginado(int pag , int size);
    Page<Flujos> Paginado(int pag , int size, String texto);
}
