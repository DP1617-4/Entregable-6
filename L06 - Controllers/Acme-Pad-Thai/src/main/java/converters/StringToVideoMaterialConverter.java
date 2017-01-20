package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.VideoMaterialRepository;
import domain.VideoMaterial;

@Component
@Transactional
public class StringToVideoMaterialConverter implements Converter<String, VideoMaterial> {

	@Autowired
	VideoMaterialRepository videoMaterialRepository;

	@Override
	public VideoMaterial convert(String text) {
		VideoMaterial result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = videoMaterialRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
