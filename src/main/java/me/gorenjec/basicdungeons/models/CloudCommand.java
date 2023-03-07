package me.gorenjec.basicdungeons.models;

import cloud.commandframework.Command;
import cloud.commandframework.paper.PaperCommandManager;
import org.bukkit.command.CommandSender;

public abstract class CloudCommand {
    public abstract Command<CommandSender> createCommand(PaperCommandManager<CommandSender> manager);
}
