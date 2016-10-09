package com.my.hobby.book.sort.file;

import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import lombok.Getter;

@Getter
public class BookSorter {
	
	private File homeDir;
	private List<BookFile> bookFileList = Lists.newArrayList();
	
	public BookSorter(File homeDir) {
		this.homeDir = homeDir;
		init();
	}
	
	private void init() {
		for (File file : homeDir.listFiles()) {
			if (file.isDirectory()) continue;
			
			if (StringUtils.endsWithIgnoreCase(file.getName(), ".zip")) {
				bookFileList.add(new BookFile(file));
			}
		}
		
	}

	public void sort() {
		for (BookFile bookFile : bookFileList) {
			bookFile.sort();
		}
	}
	
	
	
	

}
