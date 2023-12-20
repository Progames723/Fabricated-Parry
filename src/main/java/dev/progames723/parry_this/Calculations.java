package dev.progames723.parry_this;

public class Calculations {
	public static double calculateArmorPenetration(double damage, double armorPenetration){
		return (100*damage-damage*armorPenetration)/100;
	}
	public static double calculateArmorPenetrationWithDmgReduction(double damage, double armorPenetration, double dmgReduction){
		double insaneNumber = armorPenetration-dmgReduction;
		double temp = (damage*insaneNumber)/100;
		double finalDamage;
		if (insaneNumber < 0){
			finalDamage = damage/100*(-100-insaneNumber);
		} else {
			finalDamage = (100*damage-damage*insaneNumber)/100;
		}
		return finalDamage;
	}
}
