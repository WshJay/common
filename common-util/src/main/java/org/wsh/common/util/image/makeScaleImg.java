package org.wsh.common.util.image;

import java.io.*;
import java.util.*;

public class makeScaleImg {

	String path = "";

	public makeScaleImg() {}
	
	public makeScaleImg(String _path) {
	
		this.path = _path;
	}
	
	public void makeDirectoryOfImg(String _path1, String _path2) throws IOException {
	
		String _path           = _path1 + _path2;
		String _dPath          = _path + "small/";
		String _scaleType      = "width";
		String _sFile          = "";
		String _dFile          = "";
		int _scaleNum          = 530;
		boolean _originalShape = true;
		
		File   _smallPath = new File(_dPath);
		File   _smallFile;

		listDir dir = new listDir(_path);
		
		_smallPath.mkdir();
		
		for (int i = 0; i < dir.fileList.length; i++) { 
			
			File _file = dir.fileList[i];
			
			if (_file.isFile()) {
			
				_sFile = _path + _file.getName();
				_dFile = _dPath + _file.getName();
				
				String fileExtName = _dFile.substring(_dFile.length() - 4, _dFile.length());
				
				if (fileExtName.equals(".jpg")) {
				
					_smallFile = new File(_dFile);
					
					if (!_smallFile.isFile()) {
						System.out.println("sFile is: " + _sFile);
						System.out.println("dFile is: " + _dFile);
						
						jpgScaleZoom zoom = new jpgScaleZoom(_sFile, _dFile, _scaleType, _scaleNum);
						zoom.originalShape = _originalShape;
						zoom.makeImg();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		String _path = String.valueOf(args[0]);
		
		makeScaleImg img = new makeScaleImg(_path);
		listDir imgDir   = new listDir(_path);
		
		for (int i = 0; i < imgDir.fileList.length; i++) {
		
			if (imgDir.fileList[i].isDirectory()) {
			
				img = new makeScaleImg(_path);
				img.makeDirectoryOfImg(_path, imgDir.fileList[i].getName() + "/");
				System.out.println(_path);
				System.out.println(imgDir.fileList[i].getName() + "/");
			}
		}
	}
}
