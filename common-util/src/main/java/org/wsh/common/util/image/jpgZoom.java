package org.wsh.common.util.image;



import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import javax.imageio.ImageIO;
import javax.imageio.IIOException;

public class jpgZoom {

	protected String sFile = "";	//大图文件
	protected String dFile = "";	//将要转换出的小图文件
	protected int nw = -1;			//缩小后的宽度
	protected int nh = -1;			//缩小后的高度
	protected int defaultNw = 80;	//默认转换图片的宽
	protected int defaultNh = 80;	//默认转换图片的高

	public jpgZoom() {};

	public jpgZoom(String _sFile, String _dFile) {

		this.sFile = _sFile;
		this.dFile = _dFile;
	}

	public jpgZoom(String _sFile, String _dFile, int _nw, int _nh) {

		this(_sFile, _dFile);
		this.setNw(_nw);
		this.setNh(_nh);
	}

	public jpgZoom(String _sFile, String _dFile, int _nw, int _nh, int _defaultNw, int _defaultNh) {

		this(_sFile, _dFile, _nw, _nh);
		this.setDefaultNw(_defaultNw);
		this.setDefaultNh(_defaultNh);
	}

	protected void setNw (int _nw) {

		if (_nw == -1) {

			this.nw = this.defaultNw;
		} else {

			this.nw = _nw;
		}
	}

	protected void setNh (int _nh) {

		if (_nh == -1) {

			this.nh = this.defaultNh;
		} else {

			this.nh = _nh;
		}
	}

	protected void setDefaultNw (int _nw) {

		this.defaultNw = _nw;
	}

	protected void setDefaultNh (int _nh) {

		this.defaultNh = _nh;
	}

	protected void makeImg() throws IOException {

		try {

			File fi = new File(this.sFile); //大图文件
			File fo = new File(this.dFile); //将要转换出的小图文件

			AffineTransform transform = new AffineTransform();
			BufferedImage bis         = ImageIO.read(fi);

			int w = bis.getWidth(); 		//原图片的宽
			int h = bis.getHeight(); 		//原图片的高
			double sx = (double)this.nw / w;
			double sy = (double)this.nh / h;
			transform.setToScale(sx, sy);

			System.out.println("Old Width and Height is: " + w + " " + h);
			System.out.println("New Width and Height is: " + this.nw + " " + this.nh);
			System.out.println("");

			AffineTransformOp ato = new AffineTransformOp(transform, null);
			BufferedImage bid     = new BufferedImage(this.nw, nh, BufferedImage.TYPE_3BYTE_BGR);
			ato.filter(bis, bid);
			ImageIO.write(bid, "jpg", fo);
		} catch(Exception e) {

			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) {

		String _sFile = String.valueOf(args[0]);
		String _dFile = String.valueOf(args[1]);
		int    _nw    = Integer.parseInt(args[2]);
		int    _nh    = Integer.parseInt(args[3]);

		jpgZoom test = new jpgZoom(_sFile, _dFile, _nw, _nh);
		test.makeImg();
	}*/
}
