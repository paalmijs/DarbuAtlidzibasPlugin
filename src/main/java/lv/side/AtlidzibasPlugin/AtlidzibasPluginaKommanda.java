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

        Inventory gui = Bukkit.createInventory(player, 27, ChatColor.GOLD + jobName + "Rewards");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String rewardName = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();
                Material rewardMaterial = Material.getMaterial(rewardName.toUpperCase());

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GREEN + "Income: " + income);
                lore.add(ChatColor.BLUE + "Experience: " + experience);

                if (jobName.equalsIgnoreCase("mednieks")) {
                    switch (rewardName.toLowerCase()) {
                        case "SPIDER":
                        rewardMaterial = Material.STRING;
                        break;
                        case "SKELETON":
                            rewardMaterial = Material.BONE;
                            break;
                        case "ZOMBIE":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "BLAZE":
                            rewardMaterial = Material.BLAZE_ROD;
                            break;
                        case "CAVE_SPIDER":
                            rewardMaterial = Material.SPIDER_EYE;
                            break;
                        case "MAGMA_CUBE":
                            rewardMaterial = Material.MAGMA_CREAM;
                            break;
                        case "SHULKER":
                            rewardMaterial = Material.SHULKER_BOX;
                            break;
                        case "PHANTOM":
                            rewardMaterial = Material.PHANTOM_MEMBRANE;
                            break;
                        case "CREEPER":
                            rewardMaterial = Material.GUNPOWDER;
                            break;
                        case "ZOGLIN":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "GHAST":
                            rewardMaterial = Material.GHAST_TEAR;
                            break;
                        case "IRON_GOLEM":
                            rewardMaterial = Material.IRON_BARS;
                            break;
                        case "SQUID":
                            rewardMaterial = Material.INK_SAC;
                            break;
                        case "DROWNED":
                            rewardMaterial = Material.TRIDENT;
                            break;
                        case "HUSK":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "PILLAGER":
                            rewardMaterial = Material.CROSSBOW;
                            break;
                        case "ENDERMAN":
                            rewardMaterial = Material.ENDER_PEARL;
                            break;
                        case "GUARDIAN":
                            rewardMaterial = Material.PRISMARINE_SHARD;
                            break;
                        case "PIGLIN":
                            rewardMaterial = Material.GOLD_NUGGET;
                            break;
                        case "ZOMBIFIED_PIGLIN":
                            rewardMaterial = Material.GOLD_NUGGET;
                            break;
                    }
                }

                ItemStack item = createGuiItem(rewardMaterial, ChatColor.YELLOW + rewardName, lore, 0);
                gui.addItem(item);
            }
        }

        player.openInventory(gui);

        return true;
    }

    private void openMainGUI(Player player) {
        Inventory gui = Bukkit.createInventory(player, 27, ChatColor.GOLD + "Choose a Job");

        ItemStack farmerIcon = createGuiItem(Material.NETHERITE_HOE, ChatColor.YELLOW + "Fermeris", new ArrayList<>(), 0);
        ItemStack minerIcon = createGuiItem(Material.NETHERITE_PICKAXE, ChatColor.YELLOW + "Racejs", new ArrayList<>(), 0);
        ItemStack hunterIcon = createGuiItem(Material.NETHERITE_SWORD, ChatColor.YELLOW + "Mednieks", new ArrayList<>(), 0);
        ItemStack woodcutterIcon = createGuiItem(Material.NETHERITE_AXE, ChatColor.YELLOW + "Kokcirtejs", new ArrayList<>(), 0);




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

        Inventory gui = Bukkit.createInventory(player, 27, ChatColor.GOLD + jobName + " Rewards");

        Map<ActionType, List<JobInfo>> jobInfoList = job.getJobInfoList();

        for (Map.Entry<ActionType, List<JobInfo>> entry : jobInfoList.entrySet()) {
            List<JobInfo> jobInfos = entry.getValue();

            for (JobInfo jobInfo : jobInfos) {
                String rewardName = jobInfo.getName();
                double income = jobInfo.getBaseIncome();
                double experience = jobInfo.getBaseXp();
                Material rewardMaterial = Material.getMaterial(rewardName.toUpperCase());

                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GREEN + "Income: " + income);
                lore.add(ChatColor.BLUE + "Experience: " + experience);

                if (jobName.equalsIgnoreCase("Mednieks")) {
                    switch (rewardName.toUpperCase()) {
                        case "SPIDER":
                        rewardMaterial = Material.STRING;
                        break;
                        case "SKELETON":
                            rewardMaterial = Material.BONE;
                            break;
                        case "ZOMBIE":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "BLAZE":
                            rewardMaterial = Material.BLAZE_ROD;
                            break;
                        case "CAVE_SPIDER":
                            rewardMaterial = Material.SPIDER_EYE;
                            break;
                        case "MAGMA_CUBE":
                            rewardMaterial = Material.MAGMA_CREAM;
                            break;
                        case "SHULKER":
                            rewardMaterial = Material.SHULKER_BOX;
                            break;
                        case "PHANTOM":
                            rewardMaterial = Material.PHANTOM_MEMBRANE;
                            break;
                        case "CREEPER":
                            rewardMaterial = Material.GUNPOWDER;
                            break;
                        case "ZOGLIN":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "GHAST":
                            rewardMaterial = Material.GHAST_TEAR;
                            break;
                        case "IRON_GOLEM":
                            rewardMaterial = Material.IRON_BARS;
                            break;
                        case "SQUID":
                            rewardMaterial = Material.INK_SAC;
                            break;
                        case "DROWNED":
                            rewardMaterial = Material.TRIDENT;
                            break;
                        case "HUSK":
                            rewardMaterial = Material.ROTTEN_FLESH;
                            break;
                        case "PILLAGER":
                            rewardMaterial = Material.CROSSBOW;
                            break;
                        case "ENDERMAN":
                            rewardMaterial = Material.ENDER_PEARL;
                            break;
                        case "GUARDIAN":
                            rewardMaterial = Material.PRISMARINE_SHARD;
                            break;
                        case "PIGLIN":
                            rewardMaterial = Material.GOLD_NUGGET;
                            break;
                        case "ZOMBIFIED_PIGLIN":
                            rewardMaterial = Material.GOLD_NUGGET;
                            break;
                    }
                }

                ItemStack item = createGuiItem(rewardMaterial, ChatColor.YELLOW + rewardName, lore, 0);
                gui.addItem(item);
            }
        }

        player.openInventory(gui);
    }

    protected ItemStack createGuiItem(Material material, String name, List<String> lore, int i) {
        if (material == null) {
            return new ItemStack(Material.AIR);
        }

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);
        meta.setLore(lore);

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

        switch (itemName.toLowerCase()) {
            case "fermeris":
                openJobGui(player, "Fermeris");
                break;
            case "racejs":
                openJobGui(player, "Racejs");
                break;
            case "mednieks":
                openJobGui(player, "Mednieks");
                break;
            case "kokcirtejs":
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