package com.mauriciotogneri.momowars.model;

import com.mauriciotogneri.momowars.json.PlayerJson;
import com.mauriciotogneri.momowars.types.PlayerStatus;

public class Player
{
    public Long id;
    public Long matchId;
    public Long accountId;
    public String nickname;
    public Integer resources;
    public PlayerStatus status;

    public boolean isPlaying()
    {
        return (status == PlayerStatus.PLAYING);
    }

    public boolean isWaiting()
    {
        return (status == PlayerStatus.WAITING);
    }

    public boolean isVictorious()
    {
        return (status == PlayerStatus.VICTORIOUS);
    }

    public boolean isDefeated()
    {
        return (status == PlayerStatus.DEFEATED);
    }

    public boolean isSurrendered()
    {
        return (status == PlayerStatus.SURRENDERED);
    }

    public PlayerJson json(Long forAccountId)
    {
        if (accountId.equals(forAccountId))
        {
            return new PlayerJson(id, nickname, resources, status);
        }
        else
        {
            return new PlayerJson(nickname, status);
        }
    }
}