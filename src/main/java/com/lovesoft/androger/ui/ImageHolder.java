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




/**
 * Holds all images used in application
 * 
 * @author Patryk Kaluzny 2008.04.29
 * 
 */
public class ImageHolder {
	private static ImageHolder holder;
	private Map<String, ImageIcon> loadedImages;
//	private Map<Integer, List<ImageIcon>> loadedFrames;
//	private Map<String, Icon> loadedFileExtensionImages;
//	private Set<String> excludedFileExtensions;
	
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
//		loadedFrames = new TreeMap<Integer, List<ImageIcon>>();
//		loadedFileExtensionImages = new TreeMap<String, Icon>();
//		excludedFileExtensions = new TreeSet<String>();
//		excludedFileExtensions.add("exe");
//		excludedFileExtensions.add("ico");
//		excludedFileExtensions.add("com");
//		excludedFileExtensions.add("EXE");
//		excludedFileExtensions.add("ICO");
//		excludedFileExtensions.add("COM");
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

	
//	
//	public ImageIcon getImageIconSmall(int imageNumber) throws DetailedException {
//		return getImage(imageNumber, ImageSize.SMALL, null);
//	}
//	public ImageIcon getImageIconNormal(int imageNumber) throws DetailedException {
//		return getImage(imageNumber, ImageSize.NORMAL, null);
//	}

	private synchronized ImageIcon getImage(ImageEnum imageNumber, ImageSize size) {
		String key = getImageKey(imageNumber, size);
		ImageIcon image = loadedImages.get(key);
		if (image == null) {
			InputStream stream = getImageAsStream(imageNumber);
			LightStreamLoader loader = new LightStreamLoader(stream);
			byte[] buff;
			try {
				buff = loader.load();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			image = new ImageIcon(Toolkit.getDefaultToolkit().createImage(buff));
			if (size != null) {
				image = resize(size.getWidth(), size.getHeight(), image);
			}
			loadedImages.put(key, image);
		}
		return image;
	}
	
	@SuppressWarnings("resource")
	private InputStream getImageAsStream(ImageEnum imageEnum) {
		InputStream stream = null;
		try {
			stream = new FileInputStream(getPath(iconDir, imageEnum.getFileName()));
		} catch (FileNotFoundException e) {
			stream = ImageHolder.class.getResourceAsStream( getPathForJar(iconDir, imageEnum.getFileName()) );
			if(stream == null) {
				throw new RuntimeException("Can't open file " + imageEnum.getFileName(), e);
			}
		}
		return stream;
	}
	
	private static String getPath(String dirName, String fileName) {
		return Setup.getResourceFolder() + dirName + sep +  fileName;
	}
	
	private static String getPathForJar(String dirName, String fileName) {
		return '/' + dirName + '/' + fileName;
	}

	private String getImageKey(ImageEnum imageNumber, ImageSize size) {
		return (size == null ? "" : size.getPrefix()) + imageNumber.getFileName();
	}
	
//	private String getImageKey(String imageName, ImageSize size) {
//		return (size == null ? "" : size.getPrefix()) + imageName;
//	}

//
//	public List<ImageIcon> getFramesFromImage(int imageNumber) throws DetailedException {
//		List<ImageIcon> imageList = loadedFrames.get(imageNumber);
//		if (imageList == null) {
//			imageList = new ArrayList<ImageIcon>();
//			InputStream is = ApplicationConfiguration.getImageAsStream(getName(imageNumber));
//			GifDecoder gifDec = new GifDecoder();
//			int status = gifDec.read(is);
//			if (status != GifDecoder.STATUS_OK) {
//				if (status == 1) {
//					// Probably this is not GIF file, so just return it.
//					imageList.add(getImage(imageNumber));
//					return imageList;
//				}
//				Debug.error("Can't get frames. Error code " + status);
//				throw DetailedException.getCantGerFramesFromImage(getName(imageNumber));
//			}
//			for (int i = 0; i < gifDec.getFrameCount(); i++) {
//				imageList.add(new ImageIcon(gifDec.getFrame(i)));
//			}
//			loadedFrames.put(imageNumber, imageList);
//		}
//		return imageList;
//	}

	public static ImageHolder getInstance() {
		if (holder == null) {
			holder = new ImageHolder();
		}
		return holder;
	}

//
//	public Icon getFileIcon(FileInfo fileInfo) throws DetailedException {
//		// Directory
//		if (fileInfo.isDirectory()) {
//			return getImageSmall(FOLDER_ICON);
//		}
//		String ext = fileInfo.getExtension();
//		if (ext == null || ext.length() < 1) {
//			return getImageSmall(DEFAULT_FILE_ICON);
//		}
//		
//		// Check is file exist
//		if (!IOCenter.getInstance().isExisting(fileInfo)) {
//			// File not exist (probably remote dir), try to load image from cache
//			Icon icon = loadedFileExtensionImages.get(ext);
//			if(icon != null) {
//				return icon;
//			}
//			// No image for this extension in cache - load default icon
//			return getImageSmall(DEFAULT_FILE_ICON);
//		}
//		
//		// File is existing
//		FileSystemView view = FileSystemView.getFileSystemView();
//		if(excludedFileExtensions.contains(ext)) {
//			// For this kind of files, always load icon from system
//			return view.getSystemIcon(IOCenter.getInstance().getFile(fileInfo));
//		}
//		
//		// Try to load image from cache
//		Icon icon = loadedFileExtensionImages.get(ext);
//		if (icon != null) {
//			return icon;
//		}
//		
//		//Try load icon from system
//		icon = view.getSystemIcon(IOCenter.getInstance().getFile(fileInfo));
//		if (icon == null) {
//			icon = getImageSmall(DEFAULT_FILE_ICON);
//		} else {
//			loadedFileExtensionImages.put(ext, icon);
//		}
//		return icon;
//	}

	private ImageIcon resize(int width, int height, ImageIcon image) {
		ImageFilter replicate = new AreaAveragingScaleFilter(width, height);
		ImageProducer prod = new FilteredImageSource(image.getImage().getSource(), replicate);
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(prod));
	}
}
