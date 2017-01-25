package me.Zindev.zqexmmb;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.Zindev.zqexmmb.objectives.MMKillObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;
import net.elseland.xikage.MythicMobs.API.Bukkit.Events.MythicMobDeathEvent;

public class MyListener implements Listener{
	@EventHandler
	public void killMM(MythicMobDeathEvent e){
		Player p = e.getKiller() instanceof Player?(Player)e.getKiller():null;
		if(p == null)return;
		if(!ZQuestAPI.playerIsMakingQuest(p.getUniqueId()))return;
		MMKillObjective ob = ZQuestAPI.playerHasObjective(p.getUniqueId(), MMKillObjective.class,
				true);
		if(ob != null)ob.checkIn(e.MobName,1, p);
	}

}
