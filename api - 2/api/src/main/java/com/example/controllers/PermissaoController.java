package com.example.controllers;

import com.example.models.PermissaoUsuario;
import com.example.service.PermissaoService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/permissoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissaoController {

    @Inject
    PermissaoService permissaoService;

    @GET
    @Path("/verificar")
    public Response verificarPermissao(@QueryParam("usuario") String usuario,
                                       @QueryParam("permissao") String permissao) {

        boolean temPermissao = permissaoService.verificarPermissao(usuario, permissao);

        return Response.ok().entity(
                temPermissao
                    ? "Usuário tem permissão"
                    : "Usuário NÃO tem permissão"
        ).build();
    }

    @POST
    @Path("/criar")
    public Response adicionarPermissao(PermissaoUsuario permissao) {
        PermissaoUsuario salva = permissaoService.salvarPermissao(permissao);
        return Response.status(Response.Status.CREATED).entity(salva).build();
    
    }

}