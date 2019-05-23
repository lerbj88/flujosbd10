package com.flujos.flujosbd.services.Impl;

import com.flujos.flujosbd.dao.ControlDao;
import com.flujos.flujosbd.dao.Det_transacDao;
import com.flujos.flujosbd.dao.Taex_cataltdasDao;
import com.flujos.flujosbd.dao.TransacDao;
import com.flujos.flujosbd.model.Control;
import com.flujos.flujosbd.model.Det_transac;
import com.flujos.flujosbd.model.Transac;
import com.flujos.flujosbd.services.TransaccionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

import org.w3c.dom.Node;


@Repository
@Transactional
public class TransaccionesServiceImpl implements TransaccionesService {

    @Autowired
    Taex_cataltdasDao taex_cataltdasDao;

    @Autowired
    TransacDao transacDao;

    @Autowired
    Det_transacDao det_transacDao;

    @Autowired
    ControlDao controlDao;

    private Document doc0;
    private Document doc;
    private List <Control> lista = null;
    private List <Transac> lista2 = null;
    private List <Det_transac> lista3 = null;
    private ArrayList<Document> transactions;
    static List<Node> listOfNodes = new ArrayList<Node>();
    private Node root = null;


    public List<Transac> ConsultarCabecero( Integer fnsucursaldest, Integer fitranno)
    {
        List <Transac> lista = null;

        String ip = taex_cataltdasDao.getIpSucursal(fnsucursaldest);

        lista = transacDao.getTransaccion(ip, fitranno);

        return lista;
    }

    public List<Det_transac> ConsultarDetalle(Integer fnsucursaldest, Integer fitranno)
    {
        List <Det_transac> lista = null;

        String ip = taex_cataltdasDao.getIpSucursal(fnsucursaldest);

        lista = det_transacDao.getDetalle(ip, fitranno);

        return lista;
    }

    public List<Control> ConsultarSucursal(Short fnsucursaldest)
    {
        List <Control> lista = null;

        String ip = taex_cataltdasDao.getIpSucursal(fnsucursaldest);

        lista = controlDao.getSucursal(ip, fnsucursaldest );

        return lista;
    }


    public void generarXML (HttpServletResponse response, Short finotiend, Integer fitrann) throws IOException {


        List <Control> lista = null;
        List <Transac> lista2 = null;
        List <Det_transac> lista3 = null;

        String ip = taex_cataltdasDao.getIpSucursal(finotiend);

        lista = controlDao.getSucursal(ip, finotiend );
        lista2 = transacDao.getTransaccion(ip, fitrann);
        lista3 = det_transacDao.getDetalle(ip,fitrann);

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Transaccion");
            doc.appendChild(rootElement);

            Element cabecero = doc.createElement("Cabecero");
            rootElement.appendChild(cabecero);

            Element fiidpais = doc.createElement("FIPAISID");
            fiidpais.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFiidpais())));
            cabecero.appendChild(fiidpais);

            Element fiidcanal = doc.createElement("FICANALID");
            fiidcanal.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFiidcanal())));
            cabecero.appendChild(fiidcanal);

            Element finotienda = doc.createElement("FINOTIENDA");
            finotienda.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFinotienda())));
            cabecero.appendChild(finotienda);

            Element fitranno = doc.createElement("FITRANNO");
            fitranno.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFitranno())));
            cabecero.appendChild(fitranno);

            Element fitrantipo = doc.createElement("FITRANTIPO");
            fitrantipo.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFitrantipo())));
            cabecero.appendChild(fitrantipo);

            Element ficonsectipo = doc.createElement("FICONSECTIPO");
            ficonsectipo.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFiconsectipo())));
            cabecero.appendChild(ficonsectipo);

            Element fdtranfechr = doc.createElement("FDTRANSFECHR");
            fdtranfechr.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFdtranfechr())));
            cabecero.appendChild(fdtranfechr);

            Element fctranws = doc.createElement("FCTRANWS");
            fctranws.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFctranws())));
            cabecero.appendChild(fctranws);

            Element fctranusr = doc.createElement("FCTRANUSR");
            fctranusr.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFctranusr())));
            cabecero.appendChild(fctranusr);

            Element fitrannoreg = doc.createElement("FITRANNOREG");
            fitrannoreg.appendChild(doc.createTextNode(String.valueOf(lista2.get(0).getFitrannoreg())));
            cabecero.appendChild(fitrannoreg);


            for(int i=1; i<=lista3.size(); i++) {

                Element detalle = doc.createElement("Detalle");
                rootElement.appendChild(detalle);

                Element ficonsdeta = doc.createElement("FICONSDETA");
                ficonsdeta.appendChild(doc.createTextNode(String.valueOf(lista3.get(i-1).getFiconsdeta())));
                detalle.appendChild(ficonsdeta);

                Element fitiporeg = doc.createElement("FITIPOREG");
                fitiporeg.appendChild(doc.createTextNode(String.valueOf(lista3.get(i-1).getFitiporeg())));
                detalle.appendChild(fitiporeg);

                Element fcdatodeta = doc.createElement("FCDATODETA");
                fcdatodeta.appendChild(doc.createTextNode(lista3.get(i-1).getFcdatodeta()));
                detalle.appendChild(fcdatodeta);
            }



            // escribimos el contenido en un archivo .xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new StringWriter());
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");


            transformer.transform(source, result);


            String xmlString = result.getWriter().toString();

            // Finally, send the response
            String filename= finotiend+" - " + fitrann+".ready";

            byte[] res = xmlString.getBytes(Charset.forName("UTF-8"));
            response.setCharacterEncoding("UTF-8");
            //byte[] res = xmlString.getBytes(Charset.forName("UTF-8"));
            //response.setCharacterEncoding("UTF-8");
            response.setHeader( "Content-Disposition", "attachment;filename="+filename);
            response.getOutputStream().write(res);
            response.flushBuffer();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

    }
    /*

    public void generarXML2 ( Short finotiend, Integer fitrann, Integer fitrann2) throws IOException, TransformerException {


        String ip = taex_cataltdasDao.getIpSucursal(finotiend);

        lista = controlDao.getSucursal(ip, finotiend );
        lista2 = transacDao.getTransaccionRango(ip, fitrann, fitrann2);
        lista3 = det_transacDao.getDetalleRango(ip,fitrann, fitrann2);

        generarDoc();
        generarXML();


    }


    private void generarDoc(){
        try {


            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc0 = docBuilder.newDocument();
            doc = docBuilder.newDocument();

            for (int a = 1; a <= lista2.size(); a++) {

                Element rootElement = doc.createElement("Transaccion");
                doc.appendChild(rootElement);

                Element cabecero = doc.createElement("Cabecero");
                rootElement.appendChild(cabecero);

                Element fiidpais = doc.createElement("FIPAISID");
                fiidpais.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFiidpais())));
                cabecero.appendChild(fiidpais);

                Element fiidcanal = doc.createElement("FICANALID");
                fiidcanal.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFiidcanal())));
                cabecero.appendChild(fiidcanal);

                Element finotienda = doc.createElement("FINOTIENDA");
                finotienda.appendChild(doc.createTextNode(String.valueOf(lista.get(0).getFinotienda())));
                cabecero.appendChild(finotienda);

                Element fitranno = doc.createElement("FITRANNO");
                fitranno.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFitranno())));
                cabecero.appendChild(fitranno);

                Element fitrantipo = doc.createElement("FITRANTIPO");
                fitrantipo.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFitrantipo())));
                cabecero.appendChild(fitrantipo);

                Element ficonsectipo = doc.createElement("FICONSECTIPO");
                ficonsectipo.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFiconsectipo())));
                cabecero.appendChild(ficonsectipo);

                Element fdtranfechr = doc.createElement("FDTRANSFECHR");
                fdtranfechr.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFdtranfechr())));
                cabecero.appendChild(fdtranfechr);

                Element fctranws = doc.createElement("FCTRANWS");
                fctranws.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFctranws())));
                cabecero.appendChild(fctranws);

                Element fctranusr = doc.createElement("FCTRANUSR");
                fctranusr.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFctranusr())));
                cabecero.appendChild(fctranusr);

                Element fitrannoreg = doc.createElement("FITRANNOREG");
                fitrannoreg.appendChild(doc.createTextNode(String.valueOf(lista2.get(a - 1).getFitrannoreg())));
                cabecero.appendChild(fitrannoreg);



/*
                for (int i = 1; i <= lista3.size(); i++) {

                    Element detalle = doc.createElement("Detalle");
                    rootElement.appendChild(detalle);

                    Element ficonsdeta = doc.createElement("FICONSDETA");
                    ficonsdeta.appendChild(doc.createTextNode(String.valueOf(lista3.get(i - 1).getFiconsdeta())));
                    detalle.appendChild(ficonsdeta);

                    Element fitiporeg = doc.createElement("FITIPOREG");
                    fitiporeg.appendChild(doc.createTextNode(String.valueOf(lista3.get(i - 1).getFitiporeg())));
                    detalle.appendChild(fitiporeg);

                    Element fcdatodeta = doc.createElement("FCDATODETA");
                    fcdatodeta.appendChild(doc.createTextNode(lista3.get(i - 1).getFcdatodeta()));
                    detalle.appendChild(fcdatodeta);
                }



                // escribimos el contenido en un archivo .xml
                doc0.appendChild(doc);

            }


            //  transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");




        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

    }

    private void generarXML() throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(root);


        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(new File("archivo.ready"));
        transformer.transform(source, result);


    }

    public void PostCurl() throws FileNotFoundException {

        // Get target URL
        String strURL = "http://10.80.204.83:14000";

        // Get file to be posted
        String strXMLFilename = "22583234.ready";
        File input = new File(strXMLFilename);

        // Prepare HTTP Post
        PostMethod post = new PostMethod(strURL);

        post.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));

        // Specify content type and encoding
        // If content encoding is not explicitly specified
        // ISO-8859-1 is assumed
        post.setRequestHeader("Content-type", "text/xml; charset=utf8");

        // Get HTTP client
        HttpClient httpclient = new HttpClient();

        // Execute Request
        try {
            int result = httpclient.executeMethod(post);

            // Display status code
            System.out.println("Response status code: "+ result);

            // Display response
            System.out.println("Response body: ");
            System.out.println("======================================");
            System.out.println(post.getResponseBodyAsString());

        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            post.releaseConnection();
        }

    }

*/


}

