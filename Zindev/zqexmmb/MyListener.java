package me.Zindev.zqexmmb;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.Zindev.zqexmmb.objectives.MMKillObjective;
import me.Zindev.zquest.objects.extension.ZQuestAPI;

public class MyListener implements Listener{
	@EventHandler
	public void killMM(MythicMobDeathEvent e){
		Player p = e.getKiller() instanceof Player?(Player)e.getKiller():null;
		if(p == null)return;
		if(!ZQuestAPI.playerIsMakingQuest(p.getUniqueId()))return;
		MMKillObjective ob = ZQuestAPI.playerHasObjective(p.getUniqueId(), MMKillObjective.class,
				true);
		String s = e.getMob().getType().getInternalName();
		if(ob != null)ob.checkIn(s==null?e.getMobType().getEntityType():s,1, p);
	}

}
