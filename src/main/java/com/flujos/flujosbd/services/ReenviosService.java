package com.flujos.flujosbd.services;

import com.flujos.flujosbd.model.Taex_errormensajestienda;
import com.flujos.flujosbd.model.UsuariosFlujos;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ReenviosService {

    List <Taex_errormensajestienda> Obtenercadenas (Integer fiusuario);
    List <Taex_errormensajestienda> Obtenercadenasporsucursal (Integer fnflujoid);
    List <Taex_errormensajestienda> Obtenercadenasporsucursalflujo (Integer fnflujoid, Integer fnsucursaldest);
    public String EnviarHistorico(Integer fnfluoid, String fnfolioconsec);
    public String EnviarPendientes(Integer fnfluoid, String fnfolioconsec);
    List<Taex_errormensajestienda> filtrarCadenas(Number fnflujoid, int esquema);
    List<Taex_errormensajestienda> filtrarCadenas(Number fnflujoid, String fnfolioconsec, String fcdescerror, Number fnsucursaldest, String desde, String hasta, int esquema);
    Page<Taex_errormensajestienda> Paginado(int pag, int size,Number fnflujoid, int esquema);
    Page<Taex_errormensajestienda> PaginadoFiltrado(int pag, int size,Number fnflujoid, String fnfolioconsec, String fcdescerror, Number fnsucursaldest, String desde, String hasta, int esquema);
    //Page<Taex_errormensajestienda> filtrarCadenasPaginas(Integer pages1, Number fnflujoid, String fnfolioconsec, String fcdescerror, Number fnsucursaldest, String desde, String hasta);
    public String EnviarHistorico(Integer fnflujoid, String [] flujosids);
    public String EnviarPendientes(Integer fnflujoid, String [] flujosids);
    public String getNamepc();

}
