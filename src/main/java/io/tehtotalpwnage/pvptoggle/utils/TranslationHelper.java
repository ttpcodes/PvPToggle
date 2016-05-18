package io.tehtotalpwnage.pvptoggle.utils;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

import javax.annotation.Nullable;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.translation.ResourceBundleTranslation;

public class TranslationHelper {
	private static final Function<Locale, ResourceBundle> LOOKUP_FUNC = new Function<Locale, ResourceBundle>() {
		@Nullable
		@Override
		public ResourceBundle apply(Locale input) {
			return ResourceBundle.getBundle("io.tehtotalpwnage.pvptoggle.lang.Locale", input);
		}
	};

	private TranslationHelper() {} // Prevent instance creation
	
	public static String s(String key, Locale locale, Object...args) {
		return new ResourceBundleTranslation(key, LOOKUP_FUNC).get(locale);
	}

	public static Text t(String key, Locale locale, Object... args) {
		return Text.of(new ResourceBundleTranslation(key, LOOKUP_FUNC).get(locale));
	}
	
	public static Text text(String key, Locale locale, Object... args) {
		ResourceBundle bundle =  ResourceBundle.getBundle("io.tehtotalpwnage.pvptoggle.lang.Messages", locale);
		return (Text) bundle.getObject(key);
	}

}