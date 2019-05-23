package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.ResponsablesflujosDao;
import com.flujos.flujosbd.model.Responsablesflujos;
import com.flujos.flujosbd.services.ResponsablesFlujosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class ResponsablesFlujosServiceImpl implements ResponsablesFlujosService {

    @Autowired
    private ResponsablesflujosDao responsablesflujosDao;

    public Page<Responsablesflujos> listResponsables(int pag , int size){

        List<Responsablesflujos> lista = responsablesflujosDao.findAll();

        PageRequest page_req2 = new PageRequest(pag, size, Sort.Direction.ASC, "fnpaisdest");
        int start = (int) page_req2.getOffset();
        int end = (start + page_req2.getPageSize()) > lista.size() ? lista.size() : (start + page_req2.getPageSize());

        Page<Responsablesflujos> pages;
        pages = new PageImpl<Responsablesflujos>(lista.subList(start, end), page_req2, lista.size());



        return pages;
    }


}
