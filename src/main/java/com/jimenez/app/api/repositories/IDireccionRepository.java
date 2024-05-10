package com.jimenez.app.api.repositories;

import com.jimenez.app.api.models.Direccion;

import java.sql.SQLException;

public interface IDireccionRepository extends IRepository<Direccion> {
    Long guardarReturnId(Direccion direccion) throws SQLException;
}
