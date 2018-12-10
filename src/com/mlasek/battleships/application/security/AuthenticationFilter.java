package com.mlasek.battleships.application.security;



import com.mlasek.battleships.application.ejb.GameEJBLocal;
import com.mlasek.battleships.application.model.ErrorResponse;
import com.mlasek.battleships.application.validation.ValidationResources;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

import static com.mlasek.battleships.application.rest.GameWebservice.AUTH_TOKEN_HEADER;
import static com.mlasek.battleships.application.rest.GameWebservice.GAME_ID_PATH_PARAM;


@TokenSecured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    private GameEJBLocal gameEJB;

    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if(null!=containerRequestContext.getHeaderString(AUTH_TOKEN_HEADER)&&
                null!=containerRequestContext.getUriInfo().getPathParameters().getFirst(GAME_ID_PATH_PARAM)) {
            UUID token =
                    UUID.fromString(containerRequestContext.getHeaderString(AUTH_TOKEN_HEADER));
            UUID gameId =
                    UUID.fromString(containerRequestContext.getUriInfo().getPathParameters().getFirst(GAME_ID_PATH_PARAM));
            if(!isValidGame(token,gameId)) {
                abortAsUnauthorized(containerRequestContext, ValidationResources.WRONG_GAME_ID);
                return;
            }
            if(!isValidPlayerInGame(token,gameId)){
                abortAsUnauthorized(containerRequestContext,ValidationResources.WRONG_AUTH);
            }
        }else{
            abortAsUnauthorized(containerRequestContext, ValidationResources.NO_AUTH);
        }
    }

    private boolean isValidGame(UUID token, UUID gameId) throws ClassCastException {
        return null != gameEJB.getGame(gameId);
    }

    private boolean isValidPlayerInGame(UUID token, UUID gameId) throws ClassCastException {
        return null != gameEJB.getGame(gameId).getTokens().get(token);
    }

    private void abortAsUnauthorized(ContainerRequestContext containerRequestContext, String message) {
        containerRequestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponse(message))
                        .build());
    }
}