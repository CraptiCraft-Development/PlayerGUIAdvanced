package me.loving11ish.playerguiadvanced.commands.SubCommands;

import de.myzelyam.api.vanish.VanishAPI;
import me.loving11ish.playerguiadvanced.commands.SubCommand;
import me.loving11ish.playerguiadvanced.PlayerGUIAdvanced;
import me.loving11ish.playerguiadvanced.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class Show extends SubCommand {

    FileConfiguration messagesConfig = PlayerGUIAdvanced.getPlugin().messagesFileManager.getMessagesConfig();

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return ChatColor.AQUA + "If SuperVanish or PremiumVanish plugins are installed, this will set you out of vanish.";
    }

    @Override
    public String getSyntax() {
        return ChatColor.AQUA + "/pl show";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")){
            if (player.hasPermission("playergui.show")){
                if (VanishAPI.isInvisible(player)){
                    VanishAPI.showPlayer(player);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-command-player-visible")));
                }else {
                    player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-command-player-not-vanished")));
                }
            }else {
                player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-command-no-permission")));
            }
        }else {
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-1")));
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-2")));
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("Show-3")));
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}