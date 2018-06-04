import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

class OpenFile implements ActionListener {
	Window win;
	
	public OpenFile(Window w) {
		win = w;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fChooser = new JFileChooser();
		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if (fChooser.showOpenDialog(win) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		
		File file = fChooser.getSelectedFile();
		file.canRead();
		if (file != null && !file.getName().equals("")) {
			try {
				FileInputStream finput = new FileInputStream(file);
				ObjectInputStream oinput = new ObjectInputStream(finput);
				Window.shape = new DrShape[1000];
				Window.serial = oinput.readInt();
				for (int i = 0; i < Window.serial; i++) {
					Window.shape[i] = (DrShape)oinput.readObject();
				}
				Window.dBoard.repaint();
				oinput.close();
				finput.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;

	}

}
