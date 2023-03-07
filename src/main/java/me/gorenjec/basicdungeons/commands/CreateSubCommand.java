package me.gorenjec.basicdungeons.commands;

import cloud.commandframework.Command;
import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.paper.PaperCommandManager;
import me.gorenjec.basicdungeons.models.CloudCommand;
import org.bukkit.command.CommandSender;

public class CreateSubCommand extends CloudCommand {
    @Override
    public Command<CommandSender> createCommand(PaperCommandManager<CommandSender> manager) {
        return manager.commandBuilder("basicdungeons", "bd", "basicd", "bdungeons")
                .literal("create")
                .argument(StringArgument.of("name"))
                .permission("basicdungeons.create")
                .handler(commandContext -> {
                    String name = commandContext.get("name");

                }).build();
    }
}
