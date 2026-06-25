package com.example;

import io.agroal.api.AgroalDataSource;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Path("/agendamento-page")
public class AgendamentoPageResource {

    @Inject
    AgroalDataSource dataSource;

    @Inject
    Template appointment; // nome do arquivo HTML

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String pagina() {

        List<String> services = new ArrayList<>();

        String sql = "SELECT service_name FROM service_type";

        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                services.add(rs.getString("service_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // envia lista para o HTML
        return appointment.data("services", services).render();
    }
}
