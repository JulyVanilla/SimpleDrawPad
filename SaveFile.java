import java.awt.MenuItem;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

class SaveFile implements ActionListener {
	Window win;
	
	public SaveFile(Window w) {
		win = w;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fChooser = new JFileChooser();
		fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if (fChooser.showSaveDialog(win) == JFileChooser.CANCEL_OPTION) {
			return;
		}
		
		File file = fChooser.getSelectedFile();
		file.canWrite();
		if (file != null && !file.getName().equals("")) {
			try {
				file.delete();
				FileOutputStream foutput = new FileOutputStream(file);
				ObjectOutputStream ooutput = new ObjectOutputStream(foutput);
				ooutput.writeInt(Window.serial);
				for (DrShape shape : Window.shape) {
					ooutput.writeObject(shape);
					ooutput.flush();
				}
				ooutput.close();
				foutput.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return;

	}

}
