package cl.bebt.staffcore.commands.Time;

import cl.bebt.staffcore.main;
import cl.bebt.staffcore.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Weather implements TabExecutor {
    
    private final main plugin;
    
    public Weather( main plugin ){
        this.plugin = plugin;
        plugin.getCommand( "weather" ).setExecutor( this );
    }
    
    @Override
    public List < String > onTabComplete( CommandSender sender , Command command , String alias , String[] args ){
        if ( args.length == 1 ) {
            List < String > weather = new ArrayList <>( );
            weather.add( "rain" );
            weather.add( "clear" );
            weather.add( "storm" );
            return weather;
        }
        return null;
    }
    
    @Override
    public boolean onCommand( CommandSender sender , Command cmd , String label , String[] args ){
        if ( !(sender instanceof Player) ) {
            if ( args.length == 1 ) {
                if ( args[0].equalsIgnoreCase( "clear" ) ) {
                    for ( World world : Bukkit.getServer( ).getWorlds( ) ) {
                        world.setThundering( false );
                        world.setStorm( false );
                    }
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.clear" , "lg" , "sv" ) ) );
                }
                if ( args[0].equalsIgnoreCase( "rain" ) ) {
                    for ( World world : Bukkit.getServer( ).getWorlds( ) ) {
                        world.setStorm( true );
                        world.setThundering( false );
                    }
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.rain" , "lg" , "sv" ) ) );
                }
                if ( args[0].equalsIgnoreCase( "storm" ) ) {
                    for ( World world : Bukkit.getServer( ).getWorlds( ) ) {
                        world.setStorm( true );
                        world.setThundering( true );
                    }
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.storm" , "lg" , "sv" ) ) );
                }
            } else {
                utils.tell( sender , utils.getString( "wrong_usage" , "lg" , "staff" ).replace( "%command%" , "weather <rain/clear/storm>" ) );
            }
        }
        Player p = ( Player ) sender;
        if ( p.hasPermission( "staffcore.weather" ) ) {
            if ( args.length == 1 ) {
                if ( args[0].equalsIgnoreCase( "clear" ) ) {
                    p.getLocation( ).getWorld( ).setThundering( false );
                    p.getLocation( ).getWorld( ).setStorm( false );
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.clear" , "lg" , "sv" ) ) );
                }
                if ( args[0].equalsIgnoreCase( "rain" ) ) {
                    p.getLocation( ).getWorld( ).setStorm( true );
                    p.getLocation( ).getWorld( ).setThundering( false );
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.rain" , "lg" , "sv" ) ) );
                }
                if ( args[0].equalsIgnoreCase( "storm" ) ) {
                    p.getLocation( ).getWorld( ).setStorm( true );
                    p.getLocation( ).getWorld( ).setThundering( true );
                    Bukkit.broadcastMessage( utils.chat( utils.getString( "time.weather.storm" , "lg" , "sv" ) ) );
                }
            } else {
                utils.tell( sender , utils.getString( "wrong_usage" , "lg" , "staff" ).replace( "%command%" , "weather <rain/clear/storm>" ) );
            }
        } else {
            utils.tell( sender , utils.getString( "no_permission" , "lg" , "staff" ) );
        }
        
        return true;
    }
}