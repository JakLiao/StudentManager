package internalFrame;

import internalFrame.classGuanli.AddClassJPanel;
import internalFrame.classGuanli.ModifyClassJPanel;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ClassGuanli extends JInternalFrame{
	private static final long serialVersionUID = 1L;

	public ClassGuanli() {
		setIconifiable(true);
		setClosable(true);
		setTitle("班级信息管理");
		setBounds(0,0,750,220);
		JTabbedPane tabPane = new JTabbedPane();
		final AddClassJPanel  classaddPanel = new AddClassJPanel();
		final ModifyClassJPanel classmodPanel = new ModifyClassJPanel();
		tabPane.addTab("班级信息添加", null, classaddPanel, "班级添加");
		tabPane.addTab("班级信息修改", null, classmodPanel, "班级修改");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//转界面时动作
			}
		});
		setVisible(true);
	}
}
