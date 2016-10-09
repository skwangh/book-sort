package com.my.hobby.book.sort.file;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.my.hobby.book.sort.constant.Constants;
import com.my.hobby.book.sort.constant.ResizeType;
import com.my.hobby.book.sort.image.ResizeTypeDetector;

import lombok.Data;
import lombok.SneakyThrows;
import net.lingala.zip4j.core.ZipFile;

@Data
public class BookFile {
	
	private File file;
	
	public BookFile(File file) {
		this.file = file;
	}
	
	/**
	 * 핵심 비즈니스
	 * 디버깅 및 모니터링은 여기서 처리
	 */
	public void sort() {
		System.out.println(file);
		ResizeType resizeType = detectResizeType();
		System.out.println(resizeType);
		move(resizeType);
	}
	
	private void move(ResizeType resizeType) {
		File targetFile = new File(resizeType.getDir() + File.separator + file.getName());
		targetFile.getParentFile().mkdirs();
		file.renameTo(targetFile);
	}

	@SneakyThrows
	private ResizeType detectResizeType() {
		//clean
		FileUtils.deleteDirectory(Constants.TEMP_DIR);
		
		//unzip
		ZipFile zipFile = new ZipFile(file);
		zipFile.extractAll(Constants.TEMP_DIR_PATH);
		
		//detect
		ResizeType result = ResizeTypeDetector.detect(Constants.TEMP_DIR);
		
		//clean
		FileUtils.deleteDirectory(Constants.TEMP_DIR);
		
		return result;
	}

}
