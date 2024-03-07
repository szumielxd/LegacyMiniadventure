package me.szumielxd.legacyminiadventure;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

public class VersionableObject<T> {
	
	public static final int MAX_LEGACY_PROTOCOL = 734;
	
	private final @NotNull Map<@NotNull ChatVersion, @NotNull T> objects = new EnumMap<>(ChatVersion.class);
	
	private VersionableObject(@NotNull Map<@NotNull ChatVersion, T> objects) {
		for (var key : ChatVersion.values()) {
			var obj = objects.get(key);
			if (obj == null) {
				throw new IllegalArgumentException("map must contain values for all valid ChatVersions");
			}
			this.objects.put(key, obj);
		}
	}
	
	public <V> VersionableObject<V> map(@NotNull Function<@NotNull T, @NotNull V> mapping) {
		Map<ChatVersion, V> map = new EnumMap<>(ChatVersion.class);
		for (var entry : this.objects.entrySet()) {
			map.put(entry.getKey(), Objects.requireNonNull(mapping.apply(entry.getValue()),
					() -> "mapping returned null for `%s`".formatted(entry.getValue())));
		}
		return new VersionableObject<>(map);
	}
	
	public void forEach(BiConsumer<@NotNull ChatVersion, @NotNull T> action) {
		this.objects.forEach(action);
	}
	
	public static <T> @NotNull VersionableObject<T> of(@NotNull T normal, @NotNull T legacy) {
		return new VersionableObject<>(Map.of(
				ChatVersion.NORMAL, Objects.requireNonNull(normal, "normal"),
				ChatVersion.LEGACY, Objects.requireNonNull(legacy, "legacy")));
	}
	
	public @NotNull T get(@NotNull ChatVersion version) {
		return objects.get(version);
	}
	
	public enum ChatVersion {
		
		LEGACY,
		NORMAL;
		
		public static @NotNull ChatVersion getCorrect(int protocol) {
			return protocol > MAX_LEGACY_PROTOCOL ? NORMAL : LEGACY;
		}
		
		public static @NotNull ChatVersion getCorrect(@NotNull Optional<Integer> protocol) {
			return protocol.map(ChatVersion::getCorrect).orElse(NORMAL);
		}
		
	}

}
