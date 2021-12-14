package pl.pijok.multiservercommands;

import org.bukkit.plugin.java.JavaPlugin;
import pl.pijok.multiservercommands.commands.MultiServerCommand;
import pl.pijok.multiservercommands.essentials.ChatUtils;
import pl.pijok.multiservercommands.essentials.ConfigUtils;
import pl.pijok.multiservercommands.essentials.Debug;

public class MultiServerCommands extends JavaPlugin {

    private static MultiServerCommands instance;

    @Override
    public void onEnable() {

        instance = this;

        Debug.setPrefix("[MultiServerCommands] ");
        ConfigUtils.setPlugin(this);
        ChatUtils.setPrefix("&7[&eMSC&7] ");

        Controllers.createControllers();

        getCommand("multiservercommand").setExecutor(new MultiServerCommand());

        loadStuff(false);
    }

    @Override
    public void onDisable() {

    }

    public void loadStuff(boolean reload){

        Debug.log("&aLoading plugin " + this.getDescription().getName());

        if(!reload){
            Controllers.getRedisController().loadConfig();
            Controllers.getRedisController().initJedis();
        }

    }

    public static MultiServerCommands getInstance() {
        return instance;
    }

}
