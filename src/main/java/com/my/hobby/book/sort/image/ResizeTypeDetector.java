package com.my.hobby.book.sort.image;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.my.hobby.book.sort.constant.ResizeType;
import com.my.hobby.book.sort.file.ImageFile;
import com.my.hobby.book.sort.file.ImageFileList;

public class ResizeTypeDetector {
	
	public static ResizeType detect(File imageDir) {
		
		List<File> imageFileList = getImageFiles(imageDir);
		
		if (imageFileList == null) {
			return ResizeType.FOLDER_EXISTS;
		} else if (imageFileList.size() == 0) {
			return ResizeType.NO_FILE;
		}
		
		int[] sampleIndexes = selectSampleIndexes(imageFileList.size());
		
		ImageFileList sampleImageFileList = selectSampleImageFile(imageFileList, sampleIndexes);
		
		long averageFileSize = sampleImageFileList.getAverageFileSize();
		long averageImageHeight = sampleImageFileList.getAverageImageHeight();
		
		System.out.println("AvgFileSize = " + (averageFileSize / 1024) + " KB, AvgImageHeight = " + averageImageHeight);
		
		if (averageFileSize < 600 * 1024) {
			return ResizeType.DONE;
		} else if (averageImageHeight <= 1920) {
			return ResizeType.RESIZE_NOT;
		} else {
			return ResizeType.RESIZE;
		}
	}
	
	/**
	 * 이미지파일을 선별한다.
	 */
	private static List<File> getImageFiles(File imageDir) {
		List<File> result = Lists.newArrayList();
		for (File file : imageDir.listFiles()) {
			if (file.isDirectory()) {
				return null; 
			}
			String fileName = file.getName();
			if (StringUtils.endsWithIgnoreCase(fileName, ".jpg")
					|| StringUtils.endsWithIgnoreCase(fileName, ".png")
					|| StringUtils.endsWithIgnoreCase(fileName, ".jpeg")
					|| StringUtils.endsWithIgnoreCase(fileName, ".gif")
					|| StringUtils.endsWithIgnoreCase(fileName, ".bmp")
					) {
				result.add(file);
			}
		}
		return result;
	}
	
	/**
	 * 샘플 번호를 정한다.
	 */
	private static int[] selectSampleIndexes(int imageFilesLength) {
		
		if (imageFilesLength > 15) {
			return new int[]{5,6,7,8,9};
		} else if (imageFilesLength > 10) {
			return new int[]{4,5,6,7,8};
		} else if (imageFilesLength > 5) {
			return new int[]{3,4};
		} else {
			return null;
		}
	}
	
	/**
	 * 샘플 이미지파일 목록을 만든다.
	 */
	private static ImageFileList selectSampleImageFile(List<File> imageFileList, int[] sampleIndexes) {
		ImageFileList result = new ImageFileList();
		
		if (sampleIndexes == null) {
			for (File file : imageFileList) {
				result.add(new ImageFile(file));
			}
		} else {
			for (int index : sampleIndexes) {
				result.add(new ImageFile(imageFileList.get(index)));
			}
		}
		
		return result;
	}

	

}
