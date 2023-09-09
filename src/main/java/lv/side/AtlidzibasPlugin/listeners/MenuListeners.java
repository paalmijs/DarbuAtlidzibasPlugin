package lv.side.AtlidzibasPlugin.listeners;

import lv.side.AtlidzibasPlugin.AtlidzibasPluginMain;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListeners {

    private final AtlidzibasPluginMain plugin;


    public MenuListeners(AtlidzibasPluginMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (!event.getView().getTitle().equals(ChatColor.GOLD + "Choose a Job")) {
                return;
            }

            event.setCancelled(true);

            ItemStack clickedItem = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();


            String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());

        switch (itemName.toLowerCase()) {
            case "fermeris":
                plugin.getMenuManager().openJobGUI(player, "Fermeris");
                break;
            case "racejs":
                plugin.getMenuManager().openJobGUI(player, "Racejs");
                break;
            case "mednieks":
                plugin.getMenuManager().openJobGUI(player, "Mednieks");
                break;
            case "kokcirtejs":
                plugin.getMenuManager().openJobGUI(player, "Kokcirtejs");
                break;
        }
    }
}