package com.jimenez.app.api.controllers;

import com.google.gson.Gson;
import com.jimenez.app.api.models.Direccion;
import com.jimenez.app.api.repositories.DireccionRepository;
import com.jimenez.app.api.repositories.IDireccionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/api/direccion")
public class ApiDireccionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        IDireccionRepository repositorio = new DireccionRepository(conn);
        String calle = req.getParameter("calle"); //boulevard san felipe
        String numero = req.getParameter("numero"); //224
        String colonia = req.getParameter("colonia"); //valle del angel
        String ciudad = req.getParameter("ciudad"); //eroica puebla de zaragosa
        String estado = req.getParameter("estado"); //puebla
        String cp = req.getParameter("cp"); //45
        Direccion direccion = new Direccion();
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setColonia(colonia);
        direccion.setCiudad(ciudad);
        direccion.setEstado(estado);
        direccion.setCp(cp);

        try{
            Thread.sleep(6000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        resp.setContentType("application/json");
        try(PrintWriter out = resp.getWriter()){
            Long id = repositorio.guardarReturnId(direccion);
            Map<String, String> respuesta = new HashMap<>();
            resp.setStatus(201);
            respuesta.put("message", id.toString());
            respuesta.put("estatus", "success");
            String json = new Gson().toJson(respuesta);
            out.println(json);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
