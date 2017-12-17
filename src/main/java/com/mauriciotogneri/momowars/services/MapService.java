package com.mauriciotogneri.momowars.services;

import com.mauriciotogneri.inquiry.DatabaseException;
import com.mauriciotogneri.momowars.database.DatabaseConnection;
import com.mauriciotogneri.momowars.exceptions.ApiException;
import com.mauriciotogneri.momowars.model.Map;
import com.mauriciotogneri.momowars.repository.MapDao;

import java.util.List;

public class MapService
{
    private final DatabaseConnection connection;

    public MapService(DatabaseConnection connection)
    {
        this.connection = connection;
    }

    public List<Map> maps() throws DatabaseException
    {
        MapDao mapDao = new MapDao(connection);

        return mapDao.maps();
    }

    public Map byId(Long mapId) throws DatabaseException, ApiException
    {
        MapDao mapDao = new MapDao(connection);

        return mapDao.byId(mapId);
    }
}