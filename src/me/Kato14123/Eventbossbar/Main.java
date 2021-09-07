package me.Kato14123.Eventbossbar;

import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.Kato14123.Eventbossbar.commands.TimeCommand;

public class Main extends JavaPlugin implements Listener{
	
	public Bar bar;
	
	@Override
	public void onEnable(){
		//new TimeCommand(this);
		LocalDateTime now = LocalDateTime.now();
		if(String.valueOf(now.getDayOfWeek()) == "SATURDAY") {
			System.out.println("Event is on today!");
			this.getServer().getPluginManager().registerEvents(this, this);
			bar = new Bar(this);
			bar.createBar();
				
			if (Bukkit.getOnlinePlayers().size() > 0)
				for (Player on : Bukkit.getOnlinePlayers())
					bar.addPlayer(on);
		}else {
			System.out.println("Event isn't on today");
		}
	}
	
	@Override
	public void onDisable(){
		
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!bar.getBar().getPlayers().contains(event.getPlayer()))
			bar.addPlayer(event.getPlayer());
	}
	
}