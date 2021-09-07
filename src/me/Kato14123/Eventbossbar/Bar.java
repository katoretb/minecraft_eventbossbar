package me.Kato14123.Eventbossbar;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class Bar {
	@SuppressWarnings("unused")
	private int taskID = -1;
	private final Main plugin;
	private BossBar bar;
	
	public Bar(Main plugin) {
		this.plugin = plugin;
	}
	
	public void addPlayer(Player player) {
		bar.addPlayer(player);
	}
	
	public BossBar getBar() {
		return bar;
	}
	
	public void createBar() {
		bar = Bukkit.createBossBar(format("&aEvent 19:00"), BarColor.YELLOW, BarStyle.SOLID);
		bar.setVisible(true);
		cast();
	}
	
	public void cast() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			double progress = 1.0;
			@Override
			public void run() {
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
			    if(eventcount >= 0) {
				    bar.setTitle(format("&eอีเวนต์เริ่มในอีก " + "&b" + String.valueOf(lhours) + " &e: " + "&b" + String.valueOf(lmins) + " &e: " + "&b" + String.valueOf(lsec)));
				    double percent1 = (eventcount * 1000) / 684000;
				    bar.setProgress(Double.parseDouble(df2.format(percent1/100)));
			    }else{
				    bar.setTitle(format("&aอีเวนต์ได้เริ่มขึ้นแล้ว"));
				    bar.setColor(BarColor.GREEN);
				    progress = 1.0;
				    bar.setProgress(progress);
			    }
			}
			
		}, 0, 0);
	}
	
	private String format(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
}
