import java.awt.BasicStroke;
import java.lang.Math;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

abstract class DrShape implements Serializable{
	int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	int brsType = 0;
	int brsWidth = 0;
	boolean isFilled = false;
	Color colorPre = null;
	Color colorFill = null;
	int pointNum = 0;
	int[][] pointSet = null;
	Font font = null;
	String text = null;
	
	abstract void draw(Graphics2D gra2d);
	abstract void addPoint(int a, int b);
}

//直线
class DrLine extends DrShape {
	public DrLine(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
		brsWidth = Window.brsWidth;
		colorPre = Window.colorPre;
	}
	@Override
	void draw(Graphics2D gra2d) {
		gra2d.setPaint(colorPre);
		gra2d.setStroke(new BasicStroke(brsWidth, 1, 2));
		gra2d.drawLine(x1, y1, x2, y2);
	}
	@Override
	void addPoint(int a, int b) {
		// TODO Auto-generated method stub
		
	}
	
}

//矩形
class DrRectangle extends DrShape {
	public DrRectangle(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
		brsWidth = Window.brsWidth;
		isFilled = Window.isFilled;
		colorPre = Window.colorPre;
		colorFill = Window.colorFill;
	}

	@Override
	void draw(Graphics2D gra2d) {
		gra2d.setPaint(colorPre);
		gra2d.setStroke(new BasicStroke(brsWidth, 1, 1));
		gra2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		if (isFilled) {
			gra2d.setPaint(colorFill);
			gra2d.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		}
	}

	@Override
	void addPoint(int a, int b) {
		// TODO Auto-generated method stub
		
	}
}

//椭圆
class DrOval extends DrShape {
	public DrOval(int a, int b, int c, int d) {
		x1 = a;
		y1 = b;
		x2 = c;
		y2 = d;
		brsWidth = Window.brsWidth;
		isFilled = Window.isFilled;
		colorPre = Window.colorPre;
		colorFill = Window.colorFill;
	}

	@Override
	void draw(Graphics2D gra2d) {
		gra2d.setPaint(colorPre);
		gra2d.setStroke(new BasicStroke(brsWidth, 1, 1));
		gra2d.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		if (isFilled) {
			gra2d.setPaint(colorFill);
			gra2d.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2-x1), Math.abs(y2-y1));
		}
	}

	@Override
	void addPoint(int a, int b) {
		// TODO Auto-generated method stub
		
	}
}

//画笔
class DrPen extends DrShape {
	public DrPen(int a, int b) {
		brsType = Window.brsType;
		brsWidth = Window.brsWidth;
		colorPre = Window.colorPre;
		pointNum = 0;
		pointSet = new int[2][10000];
		pointSet[0][pointNum] = a;
		pointSet[1][pointNum] = b;
		pointNum++;
	}
	
	@Override
	public void addPoint(int a, int b) {
		pointSet[0][pointNum] = a;
		pointSet[1][pointNum] = b;
		pointNum++;
	}

	@Override
	void draw(Graphics2D gra2d) {
		gra2d.setPaint(colorPre);
		gra2d.setStroke(new BasicStroke(brsWidth, 1, 1));
		for (int i = 0; i < pointNum-1; i++) {
			gra2d.drawLine(pointSet[0][i], pointSet[1][i], pointSet[0][i+1], pointSet[1][i+1]);
		}
	}
}

//文本
class DrText extends DrShape {
	public DrText(int a, int b, String t) {
		x1 = a;
		y1 = b;
		colorPre = Window.colorPre;
		font = Window.font;
		text = t;
	}

	@Override
	void draw(Graphics2D gra2d) {
		gra2d.setPaint(colorPre);
		gra2d.setStroke(new BasicStroke(brsWidth, 1, 1));
		gra2d.setFont(font);
		gra2d.drawString(text, x1, y1);
	}

	@Override
	void addPoint(int a, int b) {
		// TODO Auto-generated method stub
		
	}
}