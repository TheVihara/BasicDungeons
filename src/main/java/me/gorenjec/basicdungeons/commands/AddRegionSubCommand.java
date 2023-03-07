package me.gorenjec.basicdungeons.commands;

import cloud.commandframework.Command;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.paper.PaperCommandManager;
import me.gorenjec.basicdungeons.models.CloudCommand;
import org.bukkit.command.CommandSender;

public class AddRegionSubCommand extends CloudCommand {
    @Override
    public Command<CommandSender> createCommand(PaperCommandManager<CommandSender> manager) {
        return manager.commandBuilder("basicdungeons", "bd", "basicd", "bdungeons")
                .literal("add-region")
                .argument(StringArgument.of("name"))
                .permission("basicdungeons.addregion")
                .handler(commandContext -> {
                    String name = commandContext.get("name");

                }).build();
    }
}
