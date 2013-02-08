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
		setTitle("ѧ����Ϣ����");
		setBounds(100,100,300,200);
		JTabbedPane tabPane = new JTabbedPane();
		final AddStudentJPanel  stuaddPanel = new AddStudentJPanel();
		final ModifyStudentJPanel stumodPanel = new ModifyStudentJPanel();
		
		tabPane.addTab("ѧ����Ϣ���", null, stuaddPanel, "ѧ�����");
		tabPane.addTab("ѧ����Ϣ�޸�", null, stumodPanel, "ѧ���޸�");
		
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//ת��ǩʱ����
			}
		});
		setVisible(true);
	}
}