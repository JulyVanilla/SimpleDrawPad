import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Highlighter.Highlight;

class ToolList extends JPanel {
	private int high = 100;
	String[] item_wid = {
			"1", "10", "20", "30", "40"
	};
	String[] item_tra = {
			"�����", "���"
	};
	String[] item_shp = {
			"ֱ��", "����", "��Բ", "����", "�ı�"
	};
	String[] item_brs = {
			"ˢ��"
	};
	Color[] sample_color = {
			Color.black, Color.darkGray, Color.gray, Color.white,
			new Color(185, 122, 87), new Color(255, 174, 201),
			new Color(255, 201, 14), new Color(239, 228, 176),
			new Color(181, 230, 29), new Color(153, 217, 234),
			new Color(112, 146, 190), new Color(200, 191, 231)
	};
	Font font = Window.font;
	
	public ToolList() {
		setLayout(new FlowLayout());
		setSize(1000,high);
		setBorder(BorderFactory.createEtchedBorder());
		
		//��ˢ����
		JComboBox<String> brush = new JComboBox<String>();
		brush.setSize(2*high, high);
		for (String brs : item_brs) {
			brush.addItem(brs);
		}
		brush.setFont(font);
		brush.setToolTipText("��ˢ����");
		add(brush);
		
		brush.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Window.brsType = ((JComboBox<?>) e.getSource()).getSelectedIndex();
			}
		});
		
		//��ˢ��ϸ
		JComboBox<String> width = new JComboBox<String>();
		width.setSize(high, high);
		for (String str : item_wid) {
			width.addItem(str);
		}
		width.setFont(font);
		width.setToolTipText("��ˢ��ϸ");
		add(width);
		
		width.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Window.brsWidth = Integer.parseInt(e.getItem().toString());
				
			}
		});
		
		
		//��״
		JComboBox<String> shape = new JComboBox<String>();
		shape.setSize(high, high);
		for (String shp : item_shp) {
			shape.addItem(shp);
		}
		shape.setFont(font);
		shape.setToolTipText("��״");
		add(shape);
		
		shape.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				Window.brsShape = ((JComboBox<?>) e.getSource()).getSelectedIndex();
			}
		});
		
		//�Ƿ����
				JComboBox<String> filled = new JComboBox<String>();
				filled.setSize(high, high);
				for (String str : item_tra) {
					filled.addItem(str);
				}
				filled.setFont(font);
				filled.setToolTipText("���");
				add(filled);
				
				filled.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if (0 == ((JComboBox<?>) e.getSource()).getSelectedIndex()) {
							Window.isFilled = false;
						}
						if (1 == ((JComboBox<?>) e.getSource()).getSelectedIndex()) {
							Window.isFilled = true;
						}
					}
				});	
		
		//ǰ��ɫ
		JButton preColor = new JButton("ǰ��ɫ");
		preColor.setSize(high, high);
		preColor.setBackground(Color.black);
		preColor.setToolTipText("ǰ��ɫ");
		add(preColor);
		
		preColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.selColor = 0;
				
			}
		});
		
		//���ɫ
		JButton fillColor = new JButton("���ɫ");
		fillColor.setSize(high, high);
		fillColor.setBackground(Color.white);
		fillColor.setToolTipText("���ɫ");
		add(fillColor);
		
		fillColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.selColor = 1;
				
			}
		});
		
		//����ɫ
		JButton backColor = new JButton("����ɫ");
		backColor.setSize(high, high);
		backColor.setBackground(Color.white);
		backColor.setToolTipText("����ɫ");
		add(backColor);
		
		backColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Window.selColor = 2;
				
			}
		});
		
		//��ɫѡ���
		JPanel selectColor = new JPanel(new GridLayout(3, 4));
		selectColor.setSize(2*high, high);
		JButton[] buttons = new JButton[sample_color.length];
		for (int i = 0; i < sample_color.length; i++) {
			buttons[i] =new JButton();
			buttons[i].setBackground(sample_color[i]);
			selectColor.add(buttons[i]);
		}
		add(selectColor);
		
		for (JButton btn : buttons) {
			btn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (Window.selColor) {
					case 0: 
						Window.colorPre = ((JButton) e.getSource()).getBackground();
						preColor.setBackground(Window.colorPre);
						break;
					case 1:
						Window.colorFill = ((JButton) e.getSource()).getBackground();
						fillColor.setBackground(Window.colorFill);
						break;
					case 2:
						Window.colorBack = ((JButton) e.getSource()).getBackground();
						backColor.setBackground(Window.colorBack);
						Window.dBoard.setBackground(Window.colorBack);
						break;
					default:
						break;
					}
					
				}
			});
		}
		
		//������ɫ
		JButton elseColor = new JButton("������ɫ");
		selectColor.setSize(high, high);
		elseColor.setToolTipText("������ɫ");
		add(elseColor);
		
		elseColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = chooswColor();
				switch (Window.selColor) {
				case 0: 
					Window.colorPre = color;
					preColor.setBackground(Window.colorPre);
					break;
				case 1:
					Window.colorFill = color;
					fillColor.setBackground(Window.colorFill);
					break;
				case 2:
					Window.colorBack = color;
					backColor.setBackground(Window.colorBack);
					Window.dBoard.setBackground(Window.colorBack);
					break;
				default:
					break;
				}
			}
		});
	}

	private Color chooswColor() {
		Color defaultColor = new Color(0);
		switch (Window.selColor) {
		case 0: 
			defaultColor = Window.colorPre;
			break;
		case 1:
			defaultColor = Window.colorFill;
			break;
		case 2:
			defaultColor = Window.colorBack;
			break;
		default:
			break;
		}
		Color color = JColorChooser.showDialog(this, "��ѡ����ɫ", defaultColor);
		if (null == color) {
			return defaultColor;
		}
		return color;
	}
}
