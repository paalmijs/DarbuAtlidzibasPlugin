package lv.side.AtlidzibasPlugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class AtlidzibasPluginMain extends JavaPlugin {
    private List<String> darbuOptions;

    private AtlidzibasPluginaKommanda atlKommanda;

    @Override
    public void onEnable() {
        getLogger().info("Atlīdzibas plugins tika ieslēgts.");

        loadConfigValues();

        getCommand("darbuatlidziba").setExecutor(new AtlidzibasPluginaKommanda(this));
        getCommand("darbuatlidziba").setTabCompleter(new AtlidzibasPluginaKommanda(this));

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
}