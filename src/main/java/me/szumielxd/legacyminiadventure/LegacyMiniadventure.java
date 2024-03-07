package me.szumielxd.legacyminiadventure;

import java.util.Set;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.Context;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.ArgumentQueue;

public class LegacyMiniadventure {
	
	
	private @NotNull MiniMessage NORMAL_PARSER = MiniMessage.builder()
			.editTags(b -> b.tag(Set.of("oldcolor", "legacycolor", "lc"), LegacyMiniadventure::legacyParser))
			.build();
	
	
	public VersionableObject<Component> parse(String text) {
		return null;
	}
	
	
	private static Tag legacyParser(ArgumentQueue args, Context context) {
		if (args.hasNext()) {
			var arg1 = args.pop();
			arg1.toString();
			
		}
		
	}
	

}
