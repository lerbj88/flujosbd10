package com.flujos.flujosbd;

import com.flujos.flujosbd.dao.UsuarioDao;
import com.flujos.flujosbd.model.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlujosbdApplicationTests {



    @Autowired
    UsuarioDao usuarioDao;



    @Test
    public void test () throws IOException {
     //   clientDao.insertarCliente("jose1","jose1");

        //System.out.println("insert exitoso");

       // clienteDao.insertarCliente2("jose21","jose21");

        //tiendaDao.listTiendas2();

        //opencsv.Readercsv();
    //    opencsv.listClientes();
        //usuarioDao.findAll();

    //    usuarioDao.findByUsuario(345310);
        usuarioDao.crearUsuario(171024, "123", 1);
        //usuarioDao.encoder();

    }

}

