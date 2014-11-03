package regalowl.hyperconomy.inventory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import regalowl.databukkit.CommonFunctions;

 

public class HFireworkMeta extends HItemMeta {
	
	private List<HFireworkEffect> effects = new ArrayList<HFireworkEffect>();
	private int power;

	
	public HFireworkMeta(String displayName, List<String> lore, List<HEnchantment> enchantments, List<HFireworkEffect> effects, int power) {
		super(displayName, lore, enchantments);
		this.effects = effects;
		this.power = power;
	}

	public HFireworkMeta(String serialized) {
		super(serialized);
		HashMap<String,String> data = CommonFunctions.explodeMap(serialized);
		ArrayList<String> stringEffects = CommonFunctions.explode(data.get("effects"));
		for (String ef:stringEffects) {
			effects.add(new HFireworkEffect(ef));
		}
		this.power = Integer.parseInt(data.get("power"));
    }

	@Override
	public String serialize() {
		HashMap<String,String> data = super.getMap();
		ArrayList<String> stringEffects = new ArrayList<String>();
		for (HFireworkEffect hfe:effects) {
			stringEffects.add(hfe.serialize());
		}
		data.put("effects", CommonFunctions.implode(stringEffects));
		data.put("power", power+"");
		return CommonFunctions.implodeMap(data);
	}
	

	public List<HFireworkEffect> getEffects() {
		return effects;
	}
	public int getPower() {
		return power;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((effects == null) ? 0 : effects.hashCode());
		result = prime * result + power;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HFireworkMeta other = (HFireworkMeta) obj;
		if (effects == null) {
			if (other.effects != null)
				return false;
		} else if (!effects.equals(other.effects))
			return false;
		if (power != other.power)
			return false;
		return true;
	}

}