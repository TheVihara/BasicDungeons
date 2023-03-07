package me.gorenjec.basicdungeons.commands;

import cloud.commandframework.Command;
import cloud.commandframework.paper.PaperCommandManager;
import me.gorenjec.basicdungeons.models.CloudCommand;
import me.gorenjec.basicdungeons.utils.HexUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BasicDungeonsCommand extends CloudCommand {
    @Override
    public Command<CommandSender> createCommand(PaperCommandManager<CommandSender> manager) {
        return manager.commandBuilder("basicdungeons", "bd", "basicd", "bdungeons")
                .permission("basicdungeons.use")
                .handler(commandContext -> {
                    Player player = (Player) commandContext.getSender();

                    player.sendMessage(HexUtils.colorify("&cUnknown subcommand, try /basicdungeons help for a list of commands!"));
                }).build();
    }
}
