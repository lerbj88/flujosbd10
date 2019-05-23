package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.FlujosDao;
import com.flujos.flujosbd.model.Flujos;
import com.flujos.flujosbd.services.FlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class FlujosServiceImpl implements FlujosService {

    @Autowired
    FlujosDao flujosDao;

    public Page<Flujos> Paginado(int pag, int size) {

        List<Flujos> lista = flujosDao.findAll();

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "id_flujo");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Flujos> pages;
        pages = new PageImpl<Flujos>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }

    public Page<Flujos> Paginado(int pag, int size, String texto) {

        List<Flujos> lista = flujosDao.consultarFlujos(texto);

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "id_flujo");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Flujos> pages;
        pages = new PageImpl<Flujos>(lista.subList(start, end), page_req2, lista.size());

        return pages;
    }


}
