package com.example.controllers;

import com.example.models.Despesa;
import com.example.service.DespesaService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/despesas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DespesaController {

    @Inject
    DespesaService despesaService;

    @POST
    public Response criarDespesa(Despesa despesa) {
        Despesa criada = despesaService.criarDespesa(despesa);
        return Response.ok(criada).build();
    }
}