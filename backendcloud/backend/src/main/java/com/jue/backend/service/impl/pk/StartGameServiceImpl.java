package com.jue.backend.service.impl.pk;

import com.jue.backend.consumer.WebSocketServer;
import com.jue.backend.service.pk.StartGameService;
import org.springframework.stereotype.Service;

/**
 * @className: com.jue.backend.service.impl.pk.StartGameServiceImpl.java
 * @description:
 * @author: Juemuel
 * @createTime: 2023/4/18
 **/
@Service
public class StartGameServiceImpl implements StartGameService {
    @Override
    public String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId) {
        System.out.println("start Gaime："+aId +" " + bId);
        WebSocketServer.startGame(aId,aBotId,bId,bBotId);
        return "start Game success";
    }
}
