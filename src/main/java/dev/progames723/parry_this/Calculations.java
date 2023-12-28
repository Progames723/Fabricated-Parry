package dev.progames723.parry_this;

public class Calculations {
	public static double calculateArmorPenetration(double damage, double armorPenetration){
		double finalDamage;
		if (armorPenetration > 100){
			finalDamage = ifArmorPenetrationIsHigherThan100Percent(damage,armorPenetration);
		} else {
			finalDamage = (100 * damage - damage * armorPenetration) / 100;
		}
		return finalDamage;
	}
	public static double calculateArmorPenetrationWithDmgReduction(double damage, double armorPenetration, double dmgReduction) {
		//fix 100% damage reduction (A SOLUTION: calculate insaneNumber before calling, if insane number is higher or equal to 100 then cancel damage)
		double insaneNumber = armorPenetration - dmgReduction;
		double finalDamage;
		if (insaneNumber < -100){
			insaneNumber = -100;
		} if (insaneNumber > 100){
			finalDamage = ifArmorPenetrationIsHigherThan100Percent(damage,insaneNumber);
		} else {
			if (insaneNumber < 0) {
				finalDamage = damage / 100 * (-100 - insaneNumber);
			} else {
				finalDamage = (100 * damage - damage * insaneNumber) / 100;
			}
		}
		return finalDamage;
	}
	public static double ifArmorPenetrationIsHigherThan100Percent(double damage, double armorPenetration){
		double finalDamage;
		finalDamage = Math.pow(((damage * armorPenetration)/100), 1.01); //(damage*armorPenetration)^1.01
		return finalDamage;
	}
}
