package internalFrame.stuGuanli;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ModifyStudentJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Box box = Box.createVerticalBox();
	private Box tabBox = Box.createHorizontalBox();
	public ModifyStudentJPanel(){
		tabBox.add(new JLabel("修改学生信息"), JLabel.CENTER);
		tabBox.add(Box.createHorizontalStrut(20));
		
		box.add(Box.createVerticalGlue());
		box.add(Box.createVerticalStrut(10));
		box.add(tabBox);
		box.add(Box.createVerticalStrut(10));
		box.add(Box.createVerticalGlue());
		
		add(box);
	}
}
