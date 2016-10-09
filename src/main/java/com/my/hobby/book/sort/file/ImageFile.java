package com.my.hobby.book.sort.file;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import lombok.SneakyThrows;

public class ImageFile {
	
	private File file;
	
	public ImageFile(File file) {
		this.file = file;
	}
	
	public long getFileSize() {
		return file.length();
	}
	
	@SneakyThrows
	public int getImageHeight() {
		BufferedImage bi = ImageIO.read(file);
		int height = bi.getHeight();
		if (height == 0) {
			throw new RuntimeException("image size is 0. file is " + file.getAbsolutePath());
		}
		return height;
	}

}
