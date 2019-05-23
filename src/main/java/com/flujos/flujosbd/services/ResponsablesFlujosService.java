package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Responsablesflujos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ResponsablesFlujosService  {
    Page<Responsablesflujos> listResponsables(int pag, int size);

}
