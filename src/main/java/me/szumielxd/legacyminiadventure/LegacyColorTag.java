package me.szumielxd.legacyminiadventure;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.Modifying;

public class LegacyColorTag implements Modifying {

	@Override
	public Component apply(@NotNull Component current, int depth) {
		return current;
	}
	
	

}
