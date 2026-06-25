package com.example;


import io.agroal.api.AgroalDataSource;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("/barbers")
public class BarberResource {
    
@Inject
    AgroalDataSource dataSource;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String listarBarbeiros() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("Lista de Barbeiros:\n\n");

        String sql = "select barber_name, phone from barber";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                resultado.append(rs.getString("barber_name")).append("\t").append(rs.getString("phone")).append("\n");
            }

        } catch (Exception e) {
            return "Erro ao consultar banco: " + e.getMessage();
        }

        return resultado.toString();
    }

    ////////////////////////

    
}
