package com.lovesoft.androger.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import com.lovesoft.androger.Setup;
import com.lovesoft.androger.tools.LightStreamLoader;
import com.lovesoft.androger.tools.ResourceLoader;




/**
 * Holds all images used in application
 * 
 * @author Patryk Kaluzny 2008.04.29
 * 
 */
public class ImageHolder {
	private static ImageHolder holder;
	private Map<String, ImageIcon> loadedImages;
	
	private static String sep = File.separator;
	private static String iconDir = Setup.getIconDirectory();
	
	public enum ImageEnum {
		ADD("add.png"), CLOSE("close.png"), GALAXY_PICTURE("West-HSTtop100median950.jpg"), TURN_ON("Circle_Green.png"), TURN_OFF("Circle_Red.png"), SPLASH_SCREEN("splashScreen.jpg");
		private String fileName;
		private ImageEnum(String fileName) {
			this.fileName = fileName;
		}
		public String getFileName() {
			return fileName;
		}
	}


	private enum ImageSize {

		SMALL("s", Setup.IMAGE_SIZE_SMALL), NORMAL("n", Setup.IMAGE_SIZE_NORMAL), LARGE("l", Setup.IMAGE_SIZE_LARGE);

		private final String prefix;
		private final int width;
		private final int height;

		private ImageSize(String prefix, Dimension d) {
			this.prefix = prefix;
			this.width = d.width;
			this.height = d.height;
		}

		public String getPrefix() {
			return prefix;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

	};

	private ImageHolder() {
		loadedImages = new TreeMap<String, ImageIcon>();
	}

	public Icon getImageSmall(ImageEnum imageEnum)  {
		return getImage(imageEnum, ImageSize.SMALL);
	}
	
	public Icon getImageMedium(ImageEnum imageEnum)  {
		return getImage(imageEnum, ImageSize.NORMAL);
	}
	
	public Icon getImageLarge(ImageEnum imageEnum)  {
		return getImage(imageEnum, ImageSize.LARGE);
	}
	
	public ImageIcon getImageOriginal(ImageEnum imageEnum)  {
		return getImage(imageEnum, null);
	}

	private synchronized ImageIcon getImage(ImageEnum imageNumber, ImageSize size) {
		String key = getImageKey(imageNumber, size);
		ImageIcon image = loadedImages.get(key);
		if (image == null) {
			byte[] buff = getImageAsStream(imageNumber);
			image = new ImageIcon(Toolkit.getDefaultToolkit().createImage(buff));
			if (size != null) {
				image = resize(size.getWidth(), size.getHeight(), image);
			}
			loadedImages.put(key, image);
		}
		return image;
	}
	
	private byte[] getImageAsStream(ImageEnum imageEnum) {
		return ResourceLoader.loadFileResource(iconDir + "/" + imageEnum.getFileName());
	}
	

	private String getImageKey(ImageEnum imageNumber, ImageSize size) {
		return (size == null ? "" : size.getPrefix()) + imageNumber.getFileName();
	}


	public static ImageHolder getInstance() {
		if (holder == null) {
			holder = new ImageHolder();
		}
		return holder;
	}

	private ImageIcon resize(int width, int height, ImageIcon image) {
		ImageFilter replicate = new AreaAveragingScaleFilter(width, height);
		ImageProducer prod = new FilteredImageSource(image.getImage().getSource(), replicate);
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(prod));
	}
}
