package glacier.craft.glaciercraft;

import com.google.common.base.Preconditions;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class wildCommand implements CommandExecutor {
    private GlacierCraft main;


    public wildCommand(GlacierCraft main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            int cooldownTime = 970; // Get number of seconds from wherever you want
            if(main.cooldowns.containsKey(sender.getName())) {
                long secondsLeft = ((main.cooldowns.get(sender.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                if (secondsLeft > 0) {
                    // Still cooling down
                    player.sendMessage(ChatColor.RED + "You can only use /wild one time!");
                    return true;
                }
            }
            // No cooldown found or cooldown has expired, save new cooldown
            if(player.getStatistic(Statistic.PLAY_ONE_MINUTE)/20/60 <= 15){
                player.sendMessage(ChatColor.GREEN + "Sending you to the wild!");
                sendToWild(player);
                main.cooldowns.put(sender.getName(), System.currentTimeMillis());
            } else {
                player.sendMessage(ChatColor.RED + "You have played for longer than 15 minutes!");
            }
        } else {
            main.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "You have to be a player to use this!");
        }
        return true;
    }
    public void sendToWild(Player player) {
        Location spawn = player.getWorld().getSpawnLocation();
        Location center = spawn;
        Random rand = new Random();
        double angle = rand.nextDouble()*360; //Generate a random angle
        double radius = 500;
        double x = center.getX() + (rand.nextDouble()*radius*Math.cos(Math.toRadians(angle))); // x
        double z = center.getZ() + (rand.nextDouble()*radius*Math.sin(Math.toRadians(angle))); // z
        Location newloc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt((int)x, (int)z)+2, z);
        player.teleport(newloc);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1, false, false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 1, false, false));
        player.sendTitle(ChatColor.AQUA + "Welcome to the Wild", ChatColor.WHITE + "Good Luck!", 10, 85, 10);
    }

}
