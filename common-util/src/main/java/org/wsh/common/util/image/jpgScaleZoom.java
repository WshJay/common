package org.wsh.common.util.image;

import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;

public class jpgScaleZoom extends jpgZoom {

	private String sFile = "";			//大图文件
	private String dFile = "";			//将要转换出的小图文件
	private String scaleType = "width";	//根据宽或者根据高进行缩放 width or height or original shape
	private int    scaleNum  = 100;		//缩放的数值
	private int nw = -1;				//缩小后的宽度
	private int nh = -1;				//缩小后的高度
	protected boolean originalShape = false;	//如果目标图像大于源图像,是否还继续生成

	public jpgScaleZoom() {}

	public jpgScaleZoom(String _sFile, String _dFile) {

		this.sFile = _sFile;
		this.dFile = _dFile;
	}

	public jpgScaleZoom(String _sFile, String _dFile, String _scaleType) {

		this(_sFile, _dFile);

		if (_scaleType.equals("width") || _scaleType.equals("height")) {

			this.scaleType = _scaleType;
		}
	}

	public jpgScaleZoom(String _sFile, String _dFile, String _scaleType, int _scaleNum) {

		this(_sFile, _dFile, _scaleType);

		if (_scaleNum >= 0) {

			this.scaleNum = _scaleNum;
		}
	}

	public void makeImg() throws IOException {

			File fi = new File(this.sFile); //大图文件
			File fo = new File(this.dFile); //将要转换出的小图文件

			AffineTransform transform = new AffineTransform();
			BufferedImage bis         = ImageIO.read(fi);

			int w = bis.getWidth(); 		//原图片的宽
			int h = bis.getHeight(); 		//原图片的高
			double scale = (double)w / h; 	//计算出原图片的宽/高

			if (this.scaleType.equals("width")) {

				System.out.println("zoom by width:" + this.scaleNum);
				this.nw = this.scaleNum;
				this.nh = (this.nw * h) / w ;	//根据比例计算出缩小后高度
			} else if (this.scaleType.equals("height")) {

				System.out.println("zoom by height:" + this.scaleNum);
				this.nh = this.scaleNum;
				this.nw = (this.nh * w) / h ;	//根据比例计算出缩小后高度
			}

			if (this.originalShape && this.nw > w && this.nh > h) {

				System.out.println("zoom original shape");
				this.nw = w;
				this.nh = h;
			}
			//super.jpgZoom(this.sFile, this.dFile, this.nw, this.nh);
			super.sFile = this.sFile;
			super.dFile = this.dFile;
			super.nw    = this.nw;
			super.nh    = this.nh;
			super.makeImg();
	}
	
	public static void main(String[] args) {
		jpgScaleZoom test = new jpgScaleZoom("H:/小辉.jpg", "H:/小辉2.jpg", "width", 80);
		try {
			test.makeImg();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
