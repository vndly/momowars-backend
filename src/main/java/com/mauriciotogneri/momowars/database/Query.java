package com.mauriciotogneri.momowars.database;

import com.mauriciotogneri.momowars.utils.Resources;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Query
{
    private final String queryFile;

    public Query(String queryFile)
    {
        this.queryFile = queryFile;
    }

    protected String query() throws Exception
    {
        return Resources.content(queryFile);
    }

    protected PreparedStatement preparedStatement() throws Exception
    {
        Connection connection = Database.connection();

        return connection.prepareStatement(query());
    }

    protected PreparedStatement preparedStatement(Object... parameters) throws Exception
    {
        PreparedStatement statement = preparedStatement();

        for (int i = 0; i < parameters.length; i++)
        {
            Object parameter = parameters[i];
            int index = i + 1;

            if (parameter.getClass().equals(String.class))
            {
                statement.setString(index, (String) parameter);
            }
            else if (parameter.getClass().equals(Boolean.class))
            {
                statement.setBoolean(index, (Boolean) parameter);
            }
            else if (parameter.getClass().equals(Integer.class))
            {
                statement.setInt(index, (Integer) parameter);
            }
            else if (parameter.getClass().equals(Long.class))
            {
                statement.setLong(index, (Long) parameter);
            }
            else if (parameter.getClass().equals(Float.class))
            {
                statement.setFloat(index, (Float) parameter);
            }
            else if (parameter.getClass().equals(Double.class))
            {
                statement.setDouble(index, (Double) parameter);
            }
        }

        return statement;
    }
}