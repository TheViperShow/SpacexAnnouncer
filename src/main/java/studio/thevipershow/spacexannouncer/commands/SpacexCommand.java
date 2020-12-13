package studio.thevipershow.spacexannouncer.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import studio.thevipershow.spacexannouncer.SpacexAnnouncer;
import studio.thevipershow.spacexannouncer.http.SpaceXHttp;
import studio.thevipershow.spacexannouncer.http.model.RequestType;
import studio.thevipershow.spacexannouncer.http.model.data.NextLaunchResponse;
import studio.thevipershow.spacexannouncer.http.model.data.NextRocketResponse;

@CommandAlias("spacex|space-x")
public final class SpacexCommand extends BaseCommand {

    private final SpacexAnnouncer spacexAnnouncer;
    private final SpaceXHttp<RequestType> spacexHttp = new SpaceXHttp<>();
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

    public static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);

    public static void displayNextLaunch(CommandSender sender, NextLaunchResponse nextLaunch) {
        sender.sendMessage(color(PREFIX + " Next launch data"));
        sender.sendMessage(color("  &7Rocket UID &f-> &6" + nextLaunch.getRocketUID()));
        try {
            final var zonedDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(nextLaunch.getFireDateUnixTime()), ZoneId.systemDefault());
            sender.sendMessage(color("  &7Launch Date &f-> &6" + zonedDateTime.format(timeFormatter)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sender.sendMessage(color("  &7Details &f-> &6&o" + nextLaunch.getDetails()));
        sender.sendMessage(color("  &7Flight Number &f-> &6" + nextLaunch.getFlightNumber()));
        sender.sendMessage(color("  &7Mission Name &f-> &6" + nextLaunch.getMissionName()));
        sender.sendMessage(color("  &7Youtube Stream &f-> &c" + nextLaunch.getYoutubeWebcast()));
    }

    public static void displayNextRocket(CommandSender sender, NextRocketResponse nextRocket) {
        sender.sendMessage(color(PREFIX + " Next rocket data"));
        sender.sendMessage(color(String.format("  &7Rocket Height &f-> &6%.2f&7m", nextRocket.getRocketHeight())));
        sender.sendMessage(color(String.format("  &7Rocket Diameter &f-> &6%.2f&7m", nextRocket.getRocketDiameter())));
        sender.sendMessage(color(String.format("  &7Rocket Mass &f-> &6%d&7kg", nextRocket.getRocketMassKilograms())));
        sender.sendMessage(color("  &7Rocket Name &f-> &6" + nextRocket.getRocketName()));
        sender.sendMessage(color("  &7Rocket Type &f-> &6" + nextRocket.getRocketType()));
        sender.sendMessage(color("  &7Rocket Stages &f-> &6" + nextRocket.getStages()));
        sender.sendMessage(color("  &7Rocket Boosters &f-> &6" + nextRocket.getBoosters()));
        sender.sendMessage(color("  &7Rocket Launch Cost &f-> &6" + nextRocket.getLaunchCost() + "&7$"));
        sender.sendMessage(color("  &7Rocket Country &f-> &6" + nextRocket.getCountry()));
        sender.sendMessage(color("  &7Rocket Company &f-> &6" + nextRocket.getCompany()));
        sender.sendMessage(color("  &7Rocket Description &f-> &6" + nextRocket.getDescription()));
    }

    @Subcommand("next launch")
    @CommandPermission("spacex.next")
    @Description("Shows you the next programmed SpaceX launch.")
    public final void nextLaunch(CommandSender sender) {
        spacexHttp.<NextLaunchResponse>makeRequest(RequestType.LAUNCH).thenAccept(nextLaunch -> displayNextLaunch(sender, nextLaunch));
    }

    @Subcommand("next rocket")
    @CommandPermission("spacex.next")
    @Description("Shows you the next rocket that is going to be launched.")
    public final void nextRocket(CommandSender sender) {
        spacexHttp.<NextRocketResponse>makeRequest(RequestType.ROCKET).thenAccept(nextRocket -> displayNextRocket(sender, nextRocket));
    }
}
