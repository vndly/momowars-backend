package com.mauriciotogneri.momowars.api;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.jerry.EndPoint;
import com.mauriciotogneri.jerry.exceptions.client.BadRequestException;
import com.mauriciotogneri.jerry.exceptions.client.ConflictException;
import com.mauriciotogneri.jerry.exceptions.client.UnauthorizedException;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.AccountAlreadyExistsException;
import com.mauriciotogneri.momowars.exceptions.AccountNotFoundException;
import com.mauriciotogneri.momowars.exceptions.InvalidCredentialsException;
import com.mauriciotogneri.momowars.exceptions.InvalidParametersException;
import com.mauriciotogneri.momowars.exceptions.InvalidSessionTokenException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BaseEndPoint extends EndPoint
{
    protected Response process(EndPointImplementation endPoint) throws Exception
    {
        try
        {
            DatabaseConnection connection = new DatabaseConnection();

            try
            {
                Response response = endPoint.response(connection);

                connection.commit();

                return response;
            }
            catch (DatabaseException e)
            {
                connection.rollback();

                throw new InternalServerErrorException(e);
            }
            finally
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            throw processException(e);
        }
    }

    private Exception processException(Exception exception) throws Exception
    {
        try
        {
            throw exception;
        }
        catch (InvalidParametersException e)
        {
            return new BadRequestException(e);
        }
        catch (AccountAlreadyExistsException e)
        {
            return new ConflictException(e);
        }
        catch (AccountNotFoundException e)
        {
            return new NotFoundException(e);
        }
        catch (InvalidCredentialsException | InvalidSessionTokenException e)
        {
            return new UnauthorizedException(e);
        }
        catch (InternalServerErrorException e)
        {
            e.printStackTrace();

            return e;
        }
        catch (WebApplicationException e)
        {
            return e;
        }
        catch (Exception e)
        {
            e.printStackTrace();

            return e;
        }
    }

    public interface EndPointImplementation
    {
        Response response(DatabaseConnection connection) throws Exception;
    }
}