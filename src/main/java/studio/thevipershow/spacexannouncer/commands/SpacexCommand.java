package studio.thevipershow.spacexannouncer.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import java.util.Date;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import studio.thevipershow.spacexannouncer.SpacexAnnouncer;
import studio.thevipershow.spacexannouncer.http.SpaceXHttp;
import studio.thevipershow.spacexannouncer.http.model.NextLaunchResponse;

@CommandAlias("spacex|space-x")
public final class SpacexCommand extends BaseCommand {

    private final SpacexAnnouncer spacexAnnouncer;
    private SpaceXHttp spaceXHttp = new SpaceXHttp();
    private final static String PREFIX = "&8[&6SpaceX&8]&7: ";

    private static String color(final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public SpacexCommand(SpacexAnnouncer spacexAnnouncer) {
        this.spacexAnnouncer = spacexAnnouncer;
    }

    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    public static void displayNextLaunch(CommandSender sender, NextLaunchResponse nextLaunch) {
        sender.sendMessage(color(PREFIX + " Next launch data"));
        sender.sendMessage(color("  &7Rocket UID &f-> &6" + nextLaunch.getRocketUID()));
        sender.sendMessage(color("  &7Launch Date &f-> &6" + new Date(nextLaunch.getFireDateUnixTime()).toString()));
        sender.sendMessage(color("  &7Details &f-> &6&o" + nextLaunch.getDetails()));
        sender.sendMessage(color("  &7Flight Number &f-> &6" + nextLaunch.getFlightNumber()));
        sender.sendMessage(color("  &7Mission Name &f-> &6" + nextLaunch.getMissionName()));
        sender.sendMessage(color("  &7Youtube Stream &f-> &c" + nextLaunch.getYoutubeWebcast()));
    }

    @Subcommand("next")
    @CommandPermission("spacex.next")
    @Description("Shows you the next programmed SpaceX launch.")
    public void nextLaunch(CommandSender sender) {
        spaceXHttp.getNextLaunch().thenAccept(nextLaunch -> displayNextLaunch(sender, nextLaunch));
    }
}
