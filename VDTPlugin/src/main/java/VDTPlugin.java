package tech.underside.mikelplayz.VDTPlugin;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class VDTPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("VDT Plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("VDT Plugin disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 2) {
            sender.sendMessage("§cUsage: /vdt <viewdistance|simulationdistance> <number>");
            return true;
        }

        String sub = args[0].toLowerCase();
        int value;

        try {
            value = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cPlease enter a valid number.");
            return true;
        }

        if (value < 2 || value > 32) {
            sender.sendMessage("§cValue must be between 2 and 32 chunks.");
            return true;
        }

        switch (sub) {
            case "viewdistance" -> {
                for (World world : Bukkit.getWorlds()) {
                    world.setViewDistance(value);
                }
                sender.sendMessage("§aSet view distance to §e" + value + "§a chunks for all worlds.");
            }
            case "simulationdistance" -> {
                for (World world : Bukkit.getWorlds()) {
                    world.setSimulationDistance(value);
                }
                sender.sendMessage("§aSet simulation distance to §e" + value + "§a chunks for all worlds.");
            }
            default -> sender.sendMessage("§cUnknown argument. Use 'viewdistance' or 'simulationdistance'.");
        }

        return true;
    }
}
