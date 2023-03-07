package me.gorenjec.basicdungeons.commands;

import cloud.commandframework.Command;
import cloud.commandframework.paper.PaperCommandManager;
import me.gorenjec.basicdungeons.models.CloudCommand;
import me.gorenjec.basicdungeons.utils.HexUtils;
import me.gorenjec.basicdungeons.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ToolSubCommand extends CloudCommand {
    @Override
    public Command<CommandSender> createCommand(PaperCommandManager<CommandSender> manager) {
        return manager.commandBuilder("basicdungeons", "bd", "basicd", "bdungeons")
                .literal("tool")
                .permission("basicdungeons.tool")
                .handler(commandContext -> {
                    Player player = (Player) commandContext.getSender();
                    ItemStack selectionTool = new ItemBuilder(Material.DIAMOND_HOE)
                            .displayName("&eSelection tool", true)
                            .lore(true, "", "&7L-Click to select first pos", "&7R-Click to select second pos")
                            .data("basicdungeons", "selection_tool")
                            .build();

                    player.getInventory().addItem(selectionTool);
                    player.sendMessage(HexUtils.colorify("&aGave " + player.getName() + " 1x SELECTION_TOOL"));
                }).build();
    }
}
