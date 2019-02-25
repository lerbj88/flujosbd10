package com.flujos.flujosbd;

import com.flujos.flujosbd.dao.UsuariosDao;
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
    UsuariosDao usuariosDao;



    @Test
    public void test () throws IOException {
     //   clientDao.insertarCliente("jose1","jose1");

        //System.out.println("insert exitoso");

       // clienteDao.insertarCliente2("jose21","jose21");

        //tiendaDao.listTiendas2();

        //opencsv.Readercsv();
    //    opencsv.listClientes();
        usuariosDao.findAll();
    }

}

