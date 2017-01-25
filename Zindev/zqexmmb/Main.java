package me.Zindev.zqexmmb;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.Zindev.zqexmmb.actions.McAddExpAction;
import me.Zindev.zqexmmb.objectives.MMKillObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		ZQuestAPI.registerExtension(McAddExpAction.class, this);
		ZQuestAPI.registerExtension(MMKillObjective.class, this);
		Bukkit.getPluginManager().registerEvents(new MyListener(), this);
	}
	@Override
	public void onDisable() {
		if(Bukkit.getPluginManager().isPluginEnabled("ZQuest")){
			ZQuestAPI.unregisterAll(this);
		}
	}

}
