package io.tehtotalpwnage.pvptoggle.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.function.Function;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.translation.ResourceBundleTranslation;

import static com.google.common.base.Preconditions.checkNotNull;

public class ResourceBundleExtension extends ResourceBundleTranslation {

	private final String key;
	private final Function<Locale, ResourceBundle> bundleFunction;
	
	public ResourceBundleExtension(String key, Function<Locale, ResourceBundle> bundleFunction) {
		super(key, bundleFunction);
		this.key = key;
		this.bundleFunction = bundleFunction;
	}
	
	public Text getObject(Locale locale) {
		checkNotNull(locale, "locale");
		try {
			ResourceBundle bundle = this.bundleFunction.apply(locale);
			Sponge.getServer().getPlayer("TehTotalPwnage").get().sendMessage(Text.of(bundle.getObject(this.key)));
			return (Text) bundle.getObject(this.key);
		} catch (MissingResourceException ex) {
			return Text.of(this.key);
		}
	}
}
