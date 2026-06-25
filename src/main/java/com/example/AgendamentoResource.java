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

import com.example.Barber;

@Path("/agendamento")
public class AgendamentoResource {
    @Inject
    AgroalDataSource dataSource;

    @Inject
    Template agendamento; // nome do arquivo HTML

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance pagina() {

        List<Barber> barbers = new ArrayList<>();
        List<ServiceType> service = new ArrayList<>();
        
        String sql = "select barber_id, barber_name, phone from barber";

        //Buscar barbeiros
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Barber barberend = new Barber();
                barberend.setNome(rs.getString("barber_name"));
                barbers.add(barberend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Buscar serviços
        String queryService = "SELECT service_id, service_name, service_cost FROM service_type";
        try (
            Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(queryService);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                ServiceType serviceEnd = new ServiceType();
                serviceEnd.setId(rs.getInt("service_id"));
                serviceEnd.setServiceName(rs.getString("service_name"));
                serviceEnd.setCost(rs.getFloat("service_cost"));
                service.add(serviceEnd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return agendamento.data("barbers", barbers)
                          .data("services", service);
    }

    //Calcular total dos serviços
    /*
    
        @POST
        @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        @Produces(MediaType.TEXT_PLAIN)
        public String salvar(

                @FormParam("barbeiro") String barbeiro,
                @FormParam("cliente") String cliente,
                @FormParam("celular") String celular,
                @FormParam("total") double total ✅

        ) {

            System.out.println("Total recebido: " + total);

            return "OK";
        }

    */
}
