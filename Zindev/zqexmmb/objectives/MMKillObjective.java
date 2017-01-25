package me.Zindev.zqexmmb.objectives;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Zindev.zquest.Gerekli;
import me.Zindev.zquest.chestlib.ChestField;
import me.Zindev.zquest.objects.QuestObjective;
import me.Zindev.zquest.objects.extension.QuestObjectiveMark;
import net.elseland.xikage.MythicMobs.MythicMobs;

@QuestObjectiveMark(objectiveID="MythicMobsKillObjective")
public class MMKillObjective extends QuestObjective{
	private static final long serialVersionUID = 1L;
	private Integer amt;
	private String mobName;
	
	
	
	public MMKillObjective(){
		
		setVariables(new String[2]);
		setVariable("<amount>", "remaining kill", 0);
		setVariable("<name>", "name of the mob", 1);
		setCompleteMessage("&aYou just completed a MMKill Objective !");
		setSuccessMessage("&aYou killed one <name> ! <amount> remaining...");
		setDisplayName("&dKill <amount> <name>s");
		this.amt = 4;
		mobName = "SkeletonKing";
	}
	@Override
	public void success() {
		check();
		
	}

	@Override
	public boolean check() {
		if(amt > 0){return false;}

		return true;
	}
	public boolean checkIn(String mtype,Integer cms,Player p){
		if(!mobName.equals(mtype))return false;
		if(!checkConditions(p))return false;
		amt = amt>cms?amt-cms:0;
		success();
		Gerekli.yollaMesaj(p, getSuccessMessage());
		return true;
	}

	@Override
	public String buildString() {
		return "(amount:"+amt+",mobName:"+mobName+")";
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(Integer amt) {
		this.amt = amt;
	}
	@Override
	public String getDisplayName() {
		return super.getDisplayName().replaceAll("<amount>", ""+amt).replaceAll("<name>", mobName);
	}
	@Override
	public String getSuccessMessage() {
		if(amt == 0){
			return super.getCompleteMessage();
		}
		return super.getSuccessMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", mobName);
	}
	@Override
	public String getCompleteMessage() {
		return super.getCompleteMessage().replaceAll("<amount>", ""+amt).replaceAll("<name>", mobName);
	}
	@Override
	public ArrayList<Object> getFields() {
		return getFields(new ArrayList<Object>(Arrays.asList(
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.DIAMOND), "&5&lAmount", 
								new ArrayList<String>(Arrays.asList(
										"&dHow many times player",
										"&dshould kill this mob ?",
										"&5&lCurrently:&d<value>"
										))
								, (short)0)
						
						, null, null, "amt", "&dRequired Kill", 0, 9999999),
				new ChestField( 
						Gerekli.yapEsya(new ItemStack(Material.GOLD_INGOT), "&4&lMob Name", 
								new ArrayList<String>(Arrays.asList(
										"&cWhich mob do you",
										"&cwant to spawn ?",
										"&4&lCurrently:&c<value>"
										))
								, (short)0)
						
						, null, null, "mobName", "&cMob Name", 0, 0,new ArrayList<String>(
								new ArrayList<String>(MythicMobs.plugin.mmList.keySet())))
						
					)));
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
	public ArrayList<String> getWikiDesc() {
		return new ArrayList<String>(Arrays.asList(
				"&cPlayer needs to kill",
				"&camonut of spesific mythic mob."
				));

	}
	public String getMobName() {
		return mobName;
	}
	public void setMobName(String mobName) {
		this.mobName = mobName;
	}
}
