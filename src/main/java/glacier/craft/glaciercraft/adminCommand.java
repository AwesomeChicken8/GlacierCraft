package glacier.craft.glaciercraft;

import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class adminCommand implements CommandExecutor {
    private GlacierCraft main;

    public adminCommand(GlacierCraft main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.isOp()){
                player.setStatistic(Statistic.PLAY_ONE_MINUTE, 6000);
                player.sendMessage("Your play time is " + player.getStatistic(Statistic.PLAY_ONE_MINUTE) + " minute(s)");
            }
        }
        return false;
    }
}
