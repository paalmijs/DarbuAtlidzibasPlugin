package lv.side.AtlidzibasPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AtlidzibasPluginaKommanda implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)){
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        player.sendMessage("You executed the command /darbuatlidziba!");

        return true;
    }
}
