package com.mauriciotogneri.momowars.endpoint.account;

import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.EntityProvider;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.dao.AccountDao;
import com.mauriciotogneri.momowars.database.DatabaseException;
import com.mauriciotogneri.momowars.endpoint.session.CreateSession;
import com.mauriciotogneri.momowars.model.Account;
import com.mauriciotogneri.momowars.model.exceptions.AccountAlreadyExistsException;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/api")
public class CreateAccount extends EndPoint
{
    @POST
    @Path("/v1/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccount(CreateAccountRequest accountRequest) throws IOException
    {
        try
        {
            Account account = AccountDao.create(accountRequest.email,
                                                accountRequest.nickname,
                                                accountRequest.password,
                                                CreateSession.newSessionToken());

            return response(CREATED, account);
        }
        catch (AccountAlreadyExistsException e)
        {
            return response(CONFLICT);
        }
        catch (DatabaseException e)
        {
            // TODO
            throw new InternalServerErrorException();
        }
    }

    @Provider
    @Consumes(MediaType.APPLICATION_JSON)
    public static class CreateAccountProvider extends EntityProvider<CreateAccountRequest>
    {
        public CreateAccountProvider()
        {
            super(CreateAccountRequest.class);
        }
    }

    private static class CreateAccountRequest
    {
        private String email;
        private String nickname;
        private String password;
    }
}