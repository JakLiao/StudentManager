package internalFrame;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import internalFrame.stuGuanli.*;

public class StuGuanli extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public StuGuanli() {
		setIconifiable(true);
		setClosable(true);
		setTitle("学生信息管理");
		setBounds(100,100,300,200);
		JTabbedPane tabPane = new JTabbedPane();
		final AddStudentJPanel  stuaddPanel = new AddStudentJPanel();
		final ModifyStudentJPanel stumodPanel = new ModifyStudentJPanel();
		
		tabPane.addTab("学生信息添加", null, stuaddPanel, "学生添加");
		tabPane.addTab("学生信息修改", null, stumodPanel, "学生修改");
		
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//转标签时动作
			}
		});
		setVisible(true);
	}
}