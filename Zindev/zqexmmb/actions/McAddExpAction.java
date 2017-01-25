package me.Zindev.zqexmmb.actions;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestAction;
import me.Zindev.zquest.objects.SerLocation;
import me.Zindev.zquest.objects.extension.QuestActionMark;
import net.elseland.xikage.MythicMobs.MythicMobs;
import net.elseland.xikage.MythicMobs.API.MythicMobsAPI;
import net.elseland.xikage.MythicMobs.API.Exceptions.InvalidMobTypeException;
import net.elseland.xikage.MythicMobs.Mobs.MythicMob;

@QuestActionMark(actionID ="MythicMobsSpawn")
public class McAddExpAction extends QuestAction{
	private static final long serialVersionUID = 1L;
	
	
	private Integer amount;
	private String mobName;
	private SerLocation spawnLoc;
	public McAddExpAction() {
		mobName = "SkeletonKing";
		amount = 1;
		spawnLoc = new SerLocation(Bukkit.getWorlds().get(0).getSpawnLocation());
	}
	@Override
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&cSpawn amount of mythic",
				"&cmobs to given location."
				));
	}

	@Override
	public String getWikiName() {
		return "&4"+getID();
	}
	@Override
	public Material getWikiMaterial() {
		return Material.ANVIL;
	}
	@Override
	public String buildString() {
		return "(amount:"+amount+
				",mobName:"+mobName+
				",spawnLoc:"+spawnLoc+
				")";
	}

	@Override
	public void execute(Player p) {;
		Location a = spawnLoc.getLocation();
		if(MythicMobs.plugin.mmList.containsKey(mobName)){
			MythicMobsAPI api = MythicMobs.plugin.getAPI();
			try {
				MythicMob m = api.getMobAPI().getMythicMob(mobName);
				for(int i = 0;i<amount;i++)api.getMobAPI().spawnMythicMob(m, a);
			} catch (InvalidMobTypeException e) { }
			
		}
	}

	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow much do you",
										"&dwant to spawn ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amount", "&dAmount", 1, 9999999),
						new ChestField( 
								Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&4&lMob Name", 
										new ArrayList<String>(Arrays.asList(
												"&cWhich mob do you",
												"&cwant to spawn ?",
												"&4&lCurrently:&c<value>"
												))
										, (short)0)
								
								, null, null, "mobName", "&cMob Name", 0, 0,new ArrayList<String>(
										new ArrayList<String>(MythicMobs.plugin.mmList.keySet()))),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.IRON_INGOT), "&3&lLocation", 
								new ArrayList<String>(Arrays.asList(
										"&bWhere should I spawn ?",
										"&b<value>"
										))
								, (short)0)
						
						, null, null, "spawnLoc", "&dSpawn Location", 0, 0)
						
					)));
		
	}
	public Integer getAmount() {
		return amount;
	}
	public String getMobName() {
		return mobName;
	}
	public SerLocation getSpawnLoc() {
		return spawnLoc;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public void setMobName(String mobName) {
		this.mobName = mobName;
	}
	public void setSpawnLoc(SerLocation spawnLoc) {
		this.spawnLoc = spawnLoc;
	}


}
