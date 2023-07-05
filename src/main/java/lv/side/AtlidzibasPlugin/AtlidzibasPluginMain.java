package lv.side.AtlidzibasPlugin;

import org.bukkit.plugin.java.JavaPlugin;


public class AtlidzibasPluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Atlīdzibas plugins tika ieslēgts.");

        this.getCommand("darbuatlidziba").setExecutor(new AtlidzibasPluginaKommanda());
    }

    @Override
    public void onDisable() {
        getLogger().info("Atlīdzibas plugins tika izslēgts.");
    }
}
