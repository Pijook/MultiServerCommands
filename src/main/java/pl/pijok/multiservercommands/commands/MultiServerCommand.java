package pl.pijok.multiservercommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pijok.multiservercommands.Controllers;
import pl.pijok.multiservercommands.essentials.ChatUtils;

public class MultiServerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("multiservercommand.use")){
                ChatUtils.sendMessage(player, "&cPermission denied!");
                return true;
            }
        }

        if(args.length > 0){
            StringBuilder builder = new StringBuilder();
            for(int i = 1; i < args.length; i++){
                builder.append(args[i] + " ");
            }
            String finalCommand = builder.toString();
            Controllers.getRedisController().sendCommand(finalCommand);
            ChatUtils.sendMessage(sender, "&eCommand sent!");
            return true;
        }

        ChatUtils.sendMessage(sender, "&7/" + label + " <args>");
        return true;
    }

}
