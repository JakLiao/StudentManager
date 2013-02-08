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
		setTitle("课程信息管理");
		setBounds(220, 120, 300, 250);
		JTabbedPane tabPane = new JTabbedPane();
		final AddCourseJPanel courseaddPanel = new AddCourseJPanel();
		final ModifyCourseJPanel coursemodPanel = new ModifyCourseJPanel();

		tabPane.addTab("课程信息添加", null, courseaddPanel, "课程添加");
		tabPane.addTab("课程信息修改", null, coursemodPanel, "课程修改");

		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// 转标签时动作
			}
		});
		setVisible(true);
	}
}
