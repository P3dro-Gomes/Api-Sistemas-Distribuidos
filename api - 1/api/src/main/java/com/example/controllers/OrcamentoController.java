package com.example.controllers;

import java.math.BigDecimal;
import java.util.List;

import com.example.models.Orcamento;
import com.example.service.OrcamentoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/orcamento")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrcamentoController {

     @Inject
    OrcamentoService orcamentoService;

    @GET
    public List<Orcamento> listarTodos() {
        return orcamentoService.listarTodos();
    }

    @GET
    @Path("/{area}")
    public Orcamento buscarPorArea(@PathParam("area") String area) {
        return orcamentoService.buscarPorArea(area);
    }

    @POST
    public Response criarOrcamento(Orcamento orcamento) {
        Orcamento criado = orcamentoService.criarOrcamento(orcamento);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }


    @PUT
    @Path("/{area}/saldo")
    public Response atualizarSaldo(@PathParam("area") String area, String novoSaldo) {
        BigDecimal saldo = new BigDecimal(novoSaldo);
        Orcamento atualizado = orcamentoService.atualizarSaldo(area, saldo);
        return Response.ok(atualizado).build();
    }

 
    @PUT
    @Path("/{area}/adicionar")
    public Response adicionarSaldo(@PathParam("area") String area, String valor) {
        BigDecimal adicionar = new BigDecimal(valor);
        Orcamento atualizado = orcamentoService.adicionarSaldo(area, adicionar);
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{area}")
    public Response removerOrcamento(@PathParam("area") String area) {
        orcamentoService.removerOrcamento(area);
        return Response.noContent().build();
    }
}