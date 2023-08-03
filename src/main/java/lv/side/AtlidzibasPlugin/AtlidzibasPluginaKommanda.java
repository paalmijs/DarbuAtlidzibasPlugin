package lv.side.AtlidzibasPlugin;

import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.container.ActionType;
import com.gamingmesh.jobs.container.Job;
import com.gamingmesh.jobs.container.JobInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class AtlidzibasPluginaKommanda implements CommandExecutor, TabCompleter {

    private final AtlidzibasPluginMain atlidzibasPluginMain;

    public AtlidzibasPluginaKommanda(AtlidzibasPluginMain atlidzibasPluginMain) {
        this.atlidzibasPluginMain = atlidzibasPluginMain;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage("Usage: /darbuatlidziba <Job>");
            return true;
        }

        String jobName = args[0];
        Job job = Jobs.getJob(jobName);

        if (job == null) {
            player.sendMessage("Invalid job name.");
            return true;
        }

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + jobName + " Break Rewards");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String blockType = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();

                ItemStack item = createGuiItem(Material.DIAMOND, ChatColor.YELLOW + blockType,
                        ChatColor.GREEN + "Income: " + income, ChatColor.BLUE + "Experience: " + experience);
                gui.addItem(item);
            }
        }

        player.openInventory(gui);

        return true;
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(java.util.Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
