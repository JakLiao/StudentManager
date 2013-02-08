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
		setTitle("�༶��Ϣ����");
		setBounds(0,0,750,220);
		JTabbedPane tabPane = new JTabbedPane();
		final AddClassJPanel  classaddPanel = new AddClassJPanel();
		final ModifyClassJPanel classmodPanel = new ModifyClassJPanel();
		tabPane.addTab("�༶��Ϣ���", null, classaddPanel, "�༶���");
		tabPane.addTab("�༶��Ϣ�޸�", null, classmodPanel, "�༶�޸�");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//ת����ʱ����
			}
		});
		setVisible(true);
	}
}
