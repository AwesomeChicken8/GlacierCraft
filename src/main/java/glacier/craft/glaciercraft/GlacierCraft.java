package glacier.craft.glaciercraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class GlacierCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("wild").setExecutor(new wildCommand(this));
        getCommand("admin").setExecutor(new adminCommand(this));
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Glacier " + ChatColor.WHITE + "Craft" + ChatColor.AQUA + " Plugin" + ChatColor.WHITE + " Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
