package lv.side.AtlidzibasPlugin;

import lv.side.AtlidzibasPlugin.commands.AtlidzibasPluginaKommanda;
import lv.side.AtlidzibasPlugin.listeners.MenuListeners;
import lv.side.AtlidzibasPlugin.managers.MenuManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.List;


public class AtlidzibasPluginMain extends JavaPlugin {
    private List<String> darbuOptions;

    private AtlidzibasPluginaKommanda atlKommanda;

    private MenuListeners menuListeners;
    private MenuManager menuManager;

    @Override
    public void onEnable() {
        this.menuManager = new MenuManager(this);
        getLogger().info("Atlīdzibas plugins tika ieslēgts.");

        loadConfigValues();

        getCommand("darbuatlidziba").setExecutor(new AtlidzibasPluginaKommanda(this));
    }

    @Override
    public void onDisable() {
        getLogger().info("Atlīdzibas plugins tika izslēgts.");
    }

    private void loadConfigValues() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();

        darbuOptions = config.getStringList("darbu-options");
    }

    public List<String> getDarbuOptions() {
        return darbuOptions;
    }

    public AtlidzibasPluginaKommanda getAtlKommanda() {
        return atlKommanda;
    }

    public MenuListeners getMenuListeners(){
        return menuListeners;
    }

    public MenuManager getMenuManager(){
        return menuManager;
    }
}