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
import ru.pasvitas.lab.model.Item;
import ru.pasvitas.lab.service.ItemService;

@Path("/items")
public class ItemController {

    @Inject
    private ItemService itemService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") long id) {
        Optional<Item> itemOptional = itemService.getItemById(id);
        return itemOptional.map(item -> Response.ok(item).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createItem(Item item) {
        return Response.ok(itemService.getItemById(itemService.createItem(item))).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateItem(@PathParam("id") long id, Item item) {
        if (itemService.getItemById(id).isPresent()) {
            return Response.ok(itemService.updateItem(item)).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") long id) {
        Optional<Item> itemOptional = itemService.getItemById(id);
        if (itemOptional.isPresent()) {
            itemService.deleteItem(itemOptional.get());
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
