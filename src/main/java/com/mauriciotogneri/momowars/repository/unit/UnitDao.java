package com.mauriciotogneri.momowars.repository.unit;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.inquiry.queries.DeleteQuery;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.database.SQL.UnitQueries;
import com.mauriciotogneri.momowars.model.Unit;

import java.util.Arrays;
import java.util.List;

public class UnitDao
{
    private final DatabaseConnection connection;

    public UnitDao(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Unit> byCell(Long cellId) throws DatabaseException
    {
        return Arrays.asList();
    }

    public void delete(Long playerId) throws DatabaseException
    {
        DeleteQuery query = connection.deleteQuery(UnitQueries.DELETE);

        int rowsAffected = query.execute(playerId);

        if (rowsAffected != 1)
        {
            throw new DatabaseException();
        }
    }
}