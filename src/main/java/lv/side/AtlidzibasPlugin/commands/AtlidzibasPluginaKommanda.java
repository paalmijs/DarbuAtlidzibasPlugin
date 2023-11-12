package lv.side.AtlidzibasPlugin.commands;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.Job;
import lv.side.AtlidzibasPlugin.AtlidzibasPluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class AtlidzibasPluginaKommanda implements CommandExecutor, Listener, TabCompleter {

    private final AtlidzibasPluginMain atlidzibasPluginMain;
    private final List<String> darbuOptions = new ArrayList<>();

    public AtlidzibasPluginaKommanda(AtlidzibasPluginMain atlidzibasPluginMain) {
        this.atlidzibasPluginMain = atlidzibasPluginMain;
        this.atlidzibasPluginMain.getServer().getPluginManager().registerEvents(this, atlidzibasPluginMain);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            atlidzibasPluginMain.getMenuManager().openMainGUI(player);
            return true;
        }

        String jobName = args[0];
        Job job = Jobs.getJob(jobName);

        if (job == null) {
            player.sendMessage("Invalid job name.");
            return true;
        }

        switch (jobName.toLowerCase()) {
            case "fermeris":
                atlidzibasPluginMain.getMenuManager().openJobGUI(player, "Fermeris");
                break;
            case "racejs":
                atlidzibasPluginMain.getMenuManager().openJobGUI(player, "Racejs");
                break;
            case "mednieks":
                atlidzibasPluginMain.getMenuManager().openJobGUI(player, "Mednieks");
                break;
            case "kokcirtejs":
                atlidzibasPluginMain.getMenuManager().openJobGUI(player, "Kokcirtejs");
                break;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            completions.addAll(darbuOptions);
            return completions;
        }
        return null;
    }
}