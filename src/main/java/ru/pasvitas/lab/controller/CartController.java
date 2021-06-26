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
import ru.pasvitas.lab.model.Cart;
import ru.pasvitas.lab.repository.CartRepository;

@Path("/carts")
public class CartController {

    @Inject
    private CartRepository cartRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCart(@PathParam("id") long id) {
        Optional<Cart> cartItem = cartRepository.getCartById(id);
        return cartItem.map(cart -> Response.ok(cart).build()).orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCart(Cart cart) {
        return Response.ok(cartRepository.saveCart(cart)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(@PathParam("id") long id, Cart cart) {
        if (cartRepository.getCartById(id).isPresent()) {
            return Response.ok(cartRepository.saveCart(cart)).build();
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
        Optional<Cart> cartOptional = cartRepository.getCartById(id);
        if (cartOptional.isPresent()) {
            cartRepository.deleteCart(cartOptional.get());
            return Response.ok().build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
