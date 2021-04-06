package com.yorisapp.notaria.service.impl;

import com.yorisapp.notaria.model.entity.LogConnection;
import com.yorisapp.notaria.model.repository.LogConnectionRepository;
import com.yorisapp.notaria.service.LogConnectionService;
import com.yorisapp.notaria.util.Security;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogConnectionServiceImpl implements LogConnectionService {
    private LogConnectionRepository connectionLogRepository;

    public LogConnectionServiceImpl(LogConnectionRepository logConnectionRepository){
        this.connectionLogRepository = logConnectionRepository;
    }

    @Override
    public void addConnectionLogin(long pUserId) {
        LogConnection vLogConnection = this.getConnectionLog(pUserId);
        if (vLogConnection != null){
            vLogConnection.setLogoutDate(new Date());
            connectionLogRepository.save(vLogConnection);
        }
        vLogConnection = new LogConnection();
        vLogConnection.setIdUsuario(pUserId);
        vLogConnection.setLoginDate(new Date());
        connectionLogRepository.save(vLogConnection);
    }
    private LogConnection getConnectionLog(long pUserId){
        LogConnection vLogConnection = connectionLogRepository.getConnectionLogByUserName(pUserId).orElse(null);
        return vLogConnection;
    }
}
