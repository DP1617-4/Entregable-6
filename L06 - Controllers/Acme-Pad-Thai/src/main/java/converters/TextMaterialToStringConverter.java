package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TextMaterial;

@Component
@Transactional
public class TextMaterialToStringConverter implements Converter<TextMaterial, String> {

	@Override
	public String convert(TextMaterial textMaterial) {
		String result;

		if (textMaterial == null)
			result = null;
		else
			result = String.valueOf(textMaterial.getId());

		return result;
	}

}