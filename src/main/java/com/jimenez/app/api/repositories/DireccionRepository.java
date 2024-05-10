package com.jimenez.app.api.repositories;

import com.jimenez.app.api.models.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DireccionRepository implements IDireccionRepository{

    //atributo
    private Connection conn;

    public DireccionRepository(Connection conn){
        this.conn = conn;
    }

    @Override
    public Long guardarReturnId(Direccion direccion) throws SQLException {
        String sql = "";
        int executeUpdate;
        long resultado = -1L;
        sql = "insert into direcciones (ID_DIRECCION, calle, numero, colonia, " +
                "ciudad, estado, cp) " +
                "values (SEQUENCE3.NEXTVAL,?,?,?,?,?,?)";
        try (PreparedStatement stmt =
                     conn.prepareStatement(sql, new String[]{"ID_DIRECCION"})) {


            stmt.setString(1, direccion.getCalle());
            stmt.setString(2, direccion.getNumero());
            stmt.setString(3, direccion.getColonia());
            stmt.setString(4, direccion.getCiudad());
            stmt.setString(5, direccion.getEstado());
            stmt.setString(6, direccion.getCp());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                resultado = rs.getLong(1); // 140
            }
        }
        return resultado;
    }

    @Override
    public List<Direccion> listar() throws SQLException {
        return null;
    }

    @Override
    public Direccion getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void guardar(Direccion direccion) throws SQLException {

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }
}
