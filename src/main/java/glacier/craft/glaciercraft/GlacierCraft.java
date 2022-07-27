package glacier.craft.glaciercraft;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.HashMap;

public final class GlacierCraft extends JavaPlugin {
    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();
    @Override
    public void onEnable() {
        getCommand("wild").setExecutor(new wildCommand(this));
        getCommand("admin").setExecutor(new adminCommand(this));
        getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "Glacier " + ChatColor.WHITE + "Craft" + ChatColor.AQUA + " Plugin" + ChatColor.WHITE + " Enabled");
        try {
            FileInputStream fis = new FileInputStream("cooldowns.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            cooldowns = (HashMap) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try{
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Saving HashMaps");
        FileOutputStream fos = new FileOutputStream("cooldowns.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cooldowns);
        oos.close();
        fos.close();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "HashMaps Saved!");
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
