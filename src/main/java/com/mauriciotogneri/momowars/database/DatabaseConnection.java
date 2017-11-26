package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.inquiry.queries.InsertQuery;
import com.mauriciotogneri.inquiry.queries.SelectQuery;
import com.mauriciotogneri.inquiry.queries.UpdateQuery;
import com.mauriciotogneri.javautils.Resource;
import com.mauriciotogneri.jerry.exceptions.server.InternalServerErrorException;
import com.mauriciotogneri.momowars.Main;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseConnection
{
    private final Connection connection;

    public DatabaseConnection(Connection connection) throws Exception
    {
        this.connection = connection;
    }

    public DatabaseConnection() throws Exception
    {
        this(Main.database.newConnection());
    }

    public <T> SelectQuery<T> selectQuery(String queryPath, Class<T> clazz)
    {
        return new SelectQuery<>(connection, Resource.string(queryPath), clazz);
    }

    public InsertQuery insertQuery(String queryPath)
    {
        return new InsertQuery(connection, Resource.string(queryPath));
    }

    public UpdateQuery updateQuery(String queryPath)
    {
        return new UpdateQuery(connection, Resource.string(queryPath));
    }

    public DeleteQuery deleteQuery(String queryPath)
    {
        return new DeleteQuery(connection, Resource.string(queryPath));
    }

    public void executeQuery(String sqlFile) throws Exception
    {
        try (Statement statement = connection.createStatement())
        {
            statement.execute(Resource.string(sqlFile));
        }
    }

    public void commit() throws DatabaseException
    {
        try
        {
            connection.commit();
        }
        catch (Exception e)
        {
            throw new DatabaseException(e);
        }
    }

    public void close()
    {
        Resource.close(connection);
    }

    public void rollback()
    {
        try
        {
            connection.rollback();
        }
        catch (Exception e)
        {
            throw new InternalServerErrorException(e);
        }
    }
}