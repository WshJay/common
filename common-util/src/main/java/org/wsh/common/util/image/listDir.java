package org.wsh.common.util.image;

import java.io.*;

public class listDir {

	protected String path;
	protected String fileName;
	protected File[] fileList;
	protected File dir;
	protected boolean searchSubDir;
	
	public listDir() {
	
		this("./");
	}
	
	public listDir(String _path) {
		
		this.path = _path;
		this.setDir();
		this.setDirFileList();
	}
	
	protected void setDir() {
	
		this.dir = new File(this.path);
	}
	
	protected void setDirFileList() {
	
		this.fileList = this.dir.listFiles();
	}
	
	/*public static void main(String[]args) {
	
		listDir test = new listDir("f:/");
		
		for(int i=0;i<test.fileList.length;i++){
			
			if (!test.fileList[i].isHidden()) {
				if(test.fileList[i].isFile()){
				
					System.out.println("File is: " + test.fileList[i]);
				} else if (test.fileList[i].isDirectory()) {
				
					System.out.println("Directory is: " + test.fileList[i]);
				}
			}
		}
	}*/
}
