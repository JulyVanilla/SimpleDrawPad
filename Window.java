import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream.PutField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

//界面
class Window extends JFrame {
	public static int brsType = 0;
	public static int brsWidth = 0;
	public static boolean isFilled = false;
	public static int brsShape = 0;
	public static Color colorPre = Color.black;
	public static Color colorFill = Color.white;
	public static Color colorBack = Color.white;
	public static int selColor = 0;
	public static Font font = new Font("仿宋", Font.BOLD, 20);
	
	public static DrawingBoard dBoard;
	public static DrShape[] shape = new DrShape[1000];
	public static int serial = 0;
	
	private JMenu[] menus = {
			new JMenu("File"),
			new JMenu("Help")
	};
	private JMenuItem[] item1 = {
			new JMenuItem("Open"),
			new JMenuItem("Save")
	};
	private JMenuItem[] item2 = {
			new JMenuItem("About")
	};
	
	public Window(String title) {
		super(title);
		setSize(900,700);
		setLayout(new BorderLayout());
		
		
		//菜单
		item1[0].addActionListener(new OpenFile(this));
		item1[1].addActionListener(new SaveFile(this));
		item2[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JLabel jLabel = new JLabel();
				jLabel.setText("<html><body>Version: 1.0.0<br>" + 
						"Time: 2018/06<br>" +
						"Made by July</body></html>");
				jLabel.setFont(font);
				JOptionPane.showMessageDialog(null,
						jLabel,
						"About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		for (JMenuItem mi : item1) {
			mi.setFont(font);
			menus[0].add(mi);
		}
		//menus[1].addSeparator();
		for (JMenuItem mi : item2) {
			mi.setFont(font);
			menus[1].add(mi);
		}
		JMenuBar menuBar = new JMenuBar();
		for (JMenu ms : menus) {
			Font font = new Font("宋体", Font.BOLD, 20);
			ms.setFont(font);
			menuBar.add(ms);
		}
		setJMenuBar(menuBar);
		
		//工具栏
		ToolList tList = new ToolList();
		add(BorderLayout.NORTH, tList);
		
		//绘图板
		dBoard = new DrawingBoard();
		dBoard.setBackground(colorBack);
		add(BorderLayout.CENTER, dBoard);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
