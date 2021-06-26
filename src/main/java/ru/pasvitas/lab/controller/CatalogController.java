package ru.pasvitas.lab.controller;

import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.pasvitas.lab.model.Catalog;
import ru.pasvitas.lab.service.CatalogService;

@Path("/catalogs")
public class CatalogController {

    @Inject
    private CatalogService catalogService;

    @GET
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalogs() {
        return Response.ok(catalogService.getAllCatalogs()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalog(@PathParam("id") long id) {
        Optional<Catalog> catalogOptional = catalogService.getCatalog(id);
        return catalogOptional.map(catalog -> Response.ok(catalog).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCart(Catalog catalog) {
        return Response.ok(catalogService.getCatalog(catalogService.addCatalog(catalog))).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(@PathParam("id") long id, Catalog catalog) {
        if (catalogService.getCatalog(id).isPresent()) {
            return Response.ok(catalogService.editCatalog(catalog)).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCart(@PathParam("id") long id) {
        Optional<Catalog> catalogOptional = catalogService.getCatalog(id);
        if (catalogOptional.isPresent()) {
            catalogService.deleteCatalog(catalogOptional.get());
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
