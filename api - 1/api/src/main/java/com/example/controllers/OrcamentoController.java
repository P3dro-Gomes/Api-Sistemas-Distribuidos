package com.example.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    public Response criarOrcamento(Orcamento orcamento) throws Exception{
        if(!this.permitirUsuario(orcamento.getSolicitante())){
            return Response.status(Response.Status.FORBIDDEN).entity("\"error\": \"usuario sem permissao para criar orçamento!\"").build();
        }

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

    public boolean permitirUsuario(String solicitante) throws Exception {
        HttpClient client = HttpClient.newHttpClient(); 
        String url = "http://localhost:7074/permissoes/verificar?permissao="+"GERENCIAR_ORCAMENTO"+"&usuario="+solicitante;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.body().equals("Usuario tem permissao");
        
    }

}