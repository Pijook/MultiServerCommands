package pl.pijok.multiservercommands.redis;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.pijok.multiservercommands.MultiServerCommands;
import pl.pijok.multiservercommands.essentials.ConfigUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisController {

    private String address;
    private String channel;

    private Jedis publisher;
    private Jedis subscriber;

    public void loadConfig(){
        YamlConfiguration configuration = ConfigUtils.load("redis.yml");

        address = configuration.getString("address");
        channel = configuration.getString("channel");
    }

    public void initJedis(){

        publisher = new Jedis(address);
        subscriber = new Jedis(address);

        startListening();
    }

    public void startListening(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                subscriber.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String c, String message) {
                        if(channel.equalsIgnoreCase(c)){
                            MultiServerCommands.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(MultiServerCommands.getInstance(), new Runnable() {
                                @Override
                                public void run() {
                                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
                                }
                            });
                        }
                    }
                });
            }
        }).start();
    }

    public void sendCommand(String command){
        publisher.publish(channel, command);
    }

}
