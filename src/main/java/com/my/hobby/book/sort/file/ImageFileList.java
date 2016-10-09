package com.my.hobby.book.sort.file;

import java.util.ArrayList;

public class ImageFileList extends ArrayList<ImageFile> {

	private static final long serialVersionUID = 1L;

	public long getAverageFileSize() {
		long sum = 0;
		for (ImageFile imageFile : this) {
			sum += imageFile.getFileSize();
		}
		return sum / this.size();
	}

	public long getAverageImageHeight() {
		long sum = 0;
		for (ImageFile imageFile : this) {
			sum += imageFile.getImageHeight();
		}
		return sum / this.size();
	}


}
