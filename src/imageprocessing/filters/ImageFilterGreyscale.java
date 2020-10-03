package imageprocessing.filters;

import imageprocessing.accessor.ImageAccessor;
import imageprocessing.accessor.structure.ImageColor;
import imageprocessing.filter.ImageFilterRaster;
import imageprocessing.filter.option.ImageFilterOption;
import imageprocessing.filter.option.ImageFilterSettings;
import imageprocessing.ui.options.ImageFilterOptionUIModes.ImageFilterOptionModes;
import lombok.Getter;
import imageprocessing.filters.ImageFilterGreyscale.ImageFilterGreyscaleSettings;


public class ImageFilterGreyscale extends ImageFilterRaster<ImageFilterGreyscaleSettings> {

	public ImageFilterGreyscale() {
		super(ImageFilterGreyscaleSettings.class, "Greyscale");
	}
	
	@Override
	public void filterImagePixel(ImageFilterGreyscaleSettings filterSettings, ImageAccessor filterImage, int imagePixelX, int imagePixelY, ImageColor imagePixelColor) {
		
		int red = imagePixelColor.getColorChannel(ImageColor.COLOR_CHANNEL_R);
		int green = imagePixelColor.getColorChannel(ImageColor.COLOR_CHANNEL_G);
		int blue = imagePixelColor.getColorChannel(ImageColor.COLOR_CHANNEL_B);

		int channelValue = 0;
		
		
		//Formula found on https://www.rapidtables.com/convert/image/rgb-to-grayscale.html
		switch(filterSettings.filterModes) {
			case 0:
				channelValue = (int) ( 0.2126 * red + 0.7152 * green + 0.0722 * blue);
				break;
			case 1:
				channelValue = (int) ( 0.2126 * red + 0.7152 * green + 0.0722 * blue);
				break;
			case 2:
				channelValue = (int) ( 0.2126 * red + 0.7152 * green + 0.0722 * blue);
				break;
		}
		
		//On greyscale each channel has the same value because R = G = B
		imagePixelColor.setColorChannel(ImageColor.COLOR_CHANNEL_R, channelValue);
		imagePixelColor.setColorChannel(ImageColor.COLOR_CHANNEL_G, channelValue);
		imagePixelColor.setColorChannel(ImageColor.COLOR_CHANNEL_B, channelValue);

		
	}

	public static class ImageFilterGreyscaleSettings extends ImageFilterSettings {
			@ImageFilterOption(
				optionTitle = "Greyscale Type",
				optionDescription = "Different methods has different weight for each RGB channel"
			)
			@ImageFilterOptionModes(
				optionModeDefault = 1,
				optionModesNames = { "(R+G+B) / 3", "0.2126R + 0.7152G + 0.0722B", " 0.299R + 0.587G + 0.114B" }
			)
			@Getter
			private int filterModes;

	}


}