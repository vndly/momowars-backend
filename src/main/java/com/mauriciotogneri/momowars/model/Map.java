package com.mauriciotogneri.momowars.model;

import java.util.List;

public class Map
{
    private final String name;
    private final Integer width;
    private final Integer height;
    private final List<Cell> cells;

    public Map(String name, Integer width, Integer height, List<Cell> cells)
    {
        this.name = name;
        this.width = width;
        this.height = height;
        this.cells = cells;
    }
}