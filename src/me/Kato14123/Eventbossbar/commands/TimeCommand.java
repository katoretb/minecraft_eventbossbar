package me.Kato14123.Eventbossbar.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import me.Kato14123.Eventbossbar.Main;

public class TimeCommand implements CommandExecutor{
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public TimeCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("time").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only player can use.");
			return true;
		}
		
		Player p = (Player) sender;
		if(p.hasPermission("time.use")) {
			p.sendMessage("Time:");
			LocalDateTime now = LocalDateTime.now();
			int hour = now.getHour() * 3600;
			int minute = now.getMinute() * 60;
			int second = now.getSecond();
			int timenowsec = hour + minute + second;
			int eventcount = 68400 - timenowsec;
			
			int lhours = eventcount / 3600;
			int remainder = eventcount - lhours * 3600;
			int lmins = remainder / 60;
		    remainder = remainder - lmins * 60;
		    int lsec = remainder;
			
			p.sendMessage(String.valueOf(lhours));
			p.sendMessage(String.valueOf(lmins));
			p.sendMessage(String.valueOf(lsec));
			p.sendMessage(String.valueOf(now.getDayOfWeek()));
			p.sendMessage(String.valueOf(eventcount));
		    //String pro = new DecimalFormat("#").format(percent);
			double percent1 = (eventcount * 1000) / 684000;
			p.sendMessage(String.valueOf(percent1));
		    
			p.sendMessage(String.valueOf(df2.format(percent1/100)));
			
			if(String.valueOf(now.getDayOfWeek()) == "SATURDAY") {
				p.sendMessage("Yep");
			}else {
				p.sendMessage("No");
			}
			return true;
		}else {
			p.sendMessage("You don't have permission to do this.");
		}
		return false;
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");

}