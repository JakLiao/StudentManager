package internalFrame;

import internalFrame.courseGuanli.AddCourseJPanel;
import internalFrame.courseGuanli.ModifyCourseJPanel;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CourseGuanli extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public CourseGuanli() {
		setIconifiable(true);
		setClosable(true);
		setTitle("�γ���Ϣ����");
		setBounds(220, 120, 300, 250);
		JTabbedPane tabPane = new JTabbedPane();
		final AddCourseJPanel courseaddPanel = new AddCourseJPanel();
		final ModifyCourseJPanel coursemodPanel = new ModifyCourseJPanel();

		tabPane.addTab("�γ���Ϣ���", null, courseaddPanel, "�γ����");
		tabPane.addTab("�γ���Ϣ�޸�", null, coursemodPanel, "�γ��޸�");

		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// ת��ǩʱ����
			}
		});
		setVisible(true);
	}
}
