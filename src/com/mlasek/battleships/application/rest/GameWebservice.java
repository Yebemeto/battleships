package com.mlasek.battleships.application.rest;

import com.mlasek.battleships.application.converter.CoordinatesConverter;
import com.mlasek.battleships.application.converter.ShotResponseConverter;
import com.mlasek.battleships.application.ejb.GameEJBLocal;
import com.mlasek.battleships.application.model.*;
import com.mlasek.battleships.application.security.TokenSecured;
import com.mlasek.battleships.application.validation.ValidationResources;
import com.mlasek.battleships.game.model.*;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/game")
public class GameWebservice {
    @Context
    private HttpServletRequest servletRequest;
    @EJB
    private GameEJBLocal gameEJB;
    private static final String SET_AUTH_TOKEN_HEADER = "Set-Auth-Token";
    public static final String AUTH_TOKEN_HEADER = "Auth-Token";
    public static final String GAME_ID_PATH_PARAM = "id";


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewGame() {
        UUID gameUuid = UUID.randomUUID();
        gameEJB.addGame(gameUuid);
        UUID token = gameEJB.setupGame(gameEJB.getGame(gameUuid));
        String gameInvitationUrl = servletRequest.getRequestURL()
                .append("/")
                .append(gameUuid.toString())
                .append("/join").toString();
        NewGameResponse game = new NewGameResponse(gameInvitationUrl);
        return Response.ok(game).header(SET_AUTH_TOKEN_HEADER, token).build();
    }

    @POST
    @Path("{id}/join")
    @Produces(MediaType.APPLICATION_JSON)
    public Response joinGame(@PathParam("id") UUID id) {
        AuthorizedGame game = gameEJB.getGame(id);
        if (null != game) {
            UUID token = gameEJB.joinGame(game);
            Player playerTwo = game.getPlayers().get(PlayerNumber.PLAYER_TWO);
            Player playerOne = game.getPlayers().get(PlayerNumber.PLAYER_ONE);
            GameStatusResponse state = new GameStatusResponse(playerTwo.getState(), playerTwo.getScore(), playerOne.getScore());
            return Response.ok(state).header(SET_AUTH_TOKEN_HEADER, token).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ValidationResources.WRONG_GAME_ID)).build();

        }
    }

    @TokenSecured
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGameStatus(@PathParam("id") UUID id) {
        UUID token =
                UUID.fromString(servletRequest.getHeader(AUTH_TOKEN_HEADER));
        AuthorizedGame game = gameEJB.getGame(id);
        if (null != game) {
            Player thisPlayer = game.getPlayerByToken(token);
            Player otherPlayer = game.getPlayers().get(thisPlayer.getNumber().other());
            GameStatusResponse state = new GameStatusResponse(thisPlayer.getState(), thisPlayer.getScore(), otherPlayer.getScore());
            return Response.ok(state).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ValidationResources.WRONG_GAME_ID)).build();
        }

    }

    @TokenSecured
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shoot(@PathParam("id") UUID id, @Valid ShotRequest shot) {
        UUID token =
                UUID.fromString(servletRequest.getHeader(AUTH_TOKEN_HEADER));
        AuthorizedGame game = gameEJB.getGame(id);
        if (null != game) {
            if(!gameEJB.isPlayerTurn(game,token)){
                return Response.status(Response.Status.OK).entity(new ErrorResponse(ValidationResources.NOT_YOUR_TURN)).build();
            }
            ShotResult result = gameEJB.shoot(game,token, CoordinatesConverter.convert(shot));
            if(ShotResultType.ALREADY_HIT.equals(result.getResultType())){
                return Response.status(Response.Status.OK).entity(new ErrorResponse(ValidationResources.ALREADY_HIT)).build();
            }else{
                ShotResponse response = ShotResponseConverter.convert(result);
                return Response.ok(response).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(ValidationResources.WRONG_GAME_ID)).build();
        }
    }

}