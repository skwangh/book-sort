package com.my.hobby.book.sort.constant;

import java.io.File;

import lombok.Getter;

@Getter
public enum ResizeType {
	
	DONE("done")
	,RESIZE("resize")
	,RESIZE_NOT("resize_not")
	,FOLDER_EXISTS("folder_exists")
	,NO_FILE("no_file")
	;
	
	private File dir;
	
	private ResizeType(String dirName) {
		this.dir = new File(Constants.SOURCE_DIR_PATH + File.separator + dirName);
	}
	

}
