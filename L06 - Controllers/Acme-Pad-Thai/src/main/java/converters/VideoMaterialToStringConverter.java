package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.VideoMaterial;

@Component
@Transactional
public class VideoMaterialToStringConverter implements Converter<VideoMaterial, String> {

	@Override
	public String convert(VideoMaterial videoMaterial) {
		String result;

		if (videoMaterial == null)
			result = null;
		else
			result = String.valueOf(videoMaterial.getId());

		return result;
	}

}