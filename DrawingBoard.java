import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;

class DrawingBoard extends JPanel {
	JTextField in = new JTextField();
	boolean isIn = false;
	
	public DrawingBoard() {
		setSize(1000, 1000);
		setBorder(BorderFactory.createEtchedBorder());
		in.setFont(Window.font);
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				switch (Window.brsShape) {
				case 0://÷±œﬂ
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;
				case 1://æÿ–Œ
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;
				case 2://Õ÷‘≤
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;
				case 3://Õ÷‘≤
					Window.shape[Window.serial-1].addPoint(arg0.getX(), arg0.getY());
					break;
				default:
					break;
				}
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				switch (Window.brsShape) {
				case 0://÷±œﬂ
					Window.shape[Window.serial] = 
						new DrLine(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY());
					Window.serial++;
					break;
				case 1://æÿ–Œ
					Window.shape[Window.serial] = 
						new DrRectangle(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY());
					Window.serial++;
					break;
				case 2://Õ÷‘≤
					Window.shape[Window.serial] = 
						new DrOval(arg0.getX(), arg0.getY(), arg0.getX(), arg0.getY());
					Window.serial++;
					break;
				case 3://ª≠± 
					Window.shape[Window.serial] = new DrPen(arg0.getX(), arg0.getY());
					Window.serial++;
					break;
				default:
					break;
				}
				
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch (Window.brsShape) {
				case 4:
					if (isIn) {
						Window.shape[Window.serial] = new DrText(in.getX(), in.getY(), in.getText());
						Window.serial++;
						remove(in);
						in.setText(null);
						in.setSize(0, 0);
						isIn = false;
					}
					else {
						in.setSize(1000, 30);
						add(in);
						isIn = true;
						in.setBounds(arg0.getX(), arg0.getY(), 1000, 30);
					}
					break;

				default:
					if (isIn) {
						Window.shape[Window.serial] = new DrText(in.getX(), in.getY(), in.getText());
						Window.serial++;
						remove(in);
						in.setText(null);
						in.setSize(0, 0);
						isIn = false;
					}
					break;
				}
				repaint();
			}
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				switch (Window.brsShape) {
				case 0://÷±œﬂ
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;
				case 1://æÿ–Œ
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;	
				case 2://Õ÷‘≤
					Window.shape[Window.serial-1].x2 = arg0.getX();
					Window.shape[Window.serial-1].y2 = arg0.getY();
					break;
				case 3://ª≠± 
					Window.shape[Window.serial-1].addPoint(arg0.getX(), arg0.getY());
					break;
				default:
					break;
				}
				
				repaint();
			}
		});
		
		in.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (KeyEvent.VK_ENTER == e.getKeyCode()) {
					Window.shape[Window.serial] = new DrText(in.getX(), in.getY(), in.getText());
					Window.serial++;
					remove(in);
					in.setText(null);
					in.setSize(0, 0);
					isIn = false;
					repaint();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		Graphics2D gra2d = (Graphics2D)arg0;
		for (int i = 0; i < Window.serial; i++) {
			Window.shape[i].draw(gra2d);
		}
	}

}
