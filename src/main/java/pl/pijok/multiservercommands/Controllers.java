package pl.pijok.multiservercommands;

import pl.pijok.multiservercommands.redis.RedisController;

public class Controllers {

    private static RedisController redisController;

    public static void createControllers(){

        redisController = new RedisController();

    }

    public static RedisController getRedisController() {
        return redisController;
    }

}
