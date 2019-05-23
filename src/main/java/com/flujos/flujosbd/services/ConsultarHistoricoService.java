package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Taex_histmensajestienda;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConsultarHistoricoService {
    List<Taex_histmensajestienda> ConsultarHistorico (Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta);
    Page<Taex_histmensajestienda> Paginado(int pag, int size,Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta);
}
