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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            openMainGUI(player);
            return true;
        }

        String jobName = args[0];
        Job job = Jobs.getJob(jobName);

        if (job == null) {
            player.sendMessage("Invalid job name.");
            return true;
        }

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + jobName + "Rewards");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String blockType = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();

                ItemStack item = createGuiItem(Material.getMaterial(blockType.toUpperCase()), ChatColor.YELLOW + blockType,
                        ChatColor.GREEN + "Income: " + income, ChatColor.BLUE + "Experience: " + experience);
                gui.addItem(item);
            }
        }

        player.openInventory(gui);

        return true;
    }

    private void openMainGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Choose a Job");

        ItemStack farmerIcon = createGuiItem(Material.NETHERITE_HOE, ChatColor.YELLOW + "Fermeris");
        ItemStack minerIcon = createGuiItem(Material.NETHERITE_PICKAXE, ChatColor.YELLOW + "Racejs");
        ItemStack hunterIcon = createGuiItem(Material.NETHERITE_SWORD, ChatColor.YELLOW + "Mednieks");
        ItemStack woodcutterIcon = createGuiItem(Material.NETHERITE_AXE, ChatColor.YELLOW + "Kokcirtejs");

        gui.setItem(10, farmerIcon);
        gui.setItem(12, minerIcon);
        gui.setItem(14, hunterIcon);
        gui.setItem(16, woodcutterIcon);

        player.openInventory(gui);
    }

    private void openJobGui(Player player, String jobName) {
        Job job = Jobs.getJob(jobName);

        if (job == null) {
            player.sendMessage("Invalid job name.");
            return;
        }

        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.GOLD + jobName + " Rewards");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String blockType = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();

                ItemStack item = createGuiItem(Material.getMaterial(blockType.toUpperCase()), ChatColor.YELLOW + blockType,
                        ChatColor.GREEN + "Income: " + income, ChatColor.BLUE + "Experience: " + experience);
                gui.addItem(item);
            }
        }

        player.openInventory(gui);
    }

    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(java.util.Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ChatColor.GOLD + "Choose a Job")) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        switch (itemName) {
            case "Fermeris":
                openJobGui(player, "Fermeris");
                break;
            case "Racejs":
                openJobGui(player, "Racejs");
                break;
            case "Mednieks":
                openJobGui(player, "Mednieks");
                break;
            case "Kokcirtejs":
                openJobGui(player, "Kokcirtejs");
                break;
        }
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
