package com.my.hobby.book.sort;

import com.my.hobby.book.sort.constant.Constants;
import com.my.hobby.book.sort.file.BookSorter;

public class SortByToBeResized {
	
	public static void main(String[] args) {
		BookSorter bookSorter = new BookSorter(Constants.SOURCE_DIR);
		bookSorter.sort();
	}
	

}
