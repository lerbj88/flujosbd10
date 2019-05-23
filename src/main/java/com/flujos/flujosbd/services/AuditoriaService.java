package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Taex_auditoria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuditoriaService {
    List<Taex_auditoria> ConsultarAuditoria(Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta);
    Page<Taex_auditoria> Paginado(int pag, int size, Number fnflujoid, Integer fnsucursaldest, String Desde , String Hasta);

}
