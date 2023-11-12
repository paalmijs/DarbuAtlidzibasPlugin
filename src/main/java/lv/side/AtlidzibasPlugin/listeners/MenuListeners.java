package lv.side.AtlidzibasPlugin.listeners;

import lv.side.AtlidzibasPlugin.AtlidzibasPluginMain;
import lv.side.AtlidzibasPlugin.managers.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListeners implements Listener {

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

        MenuManager menuManager = plugin.getMenuManager();

        switch (itemName.toLowerCase()) {
            case "fermeris":
                menuManager.openJobGUI(player, "Fermeris");
                break;
            case "racejs":
                menuManager.openJobGUI(player, "Racejs");
                break;
            case "mednieks":
                menuManager.openJobGUI(player, "Mednieks");
                break;
            case "kokcirtejs":
                menuManager.openJobGUI(player, "Kokcirtejs");
                break;
        }
    }
}