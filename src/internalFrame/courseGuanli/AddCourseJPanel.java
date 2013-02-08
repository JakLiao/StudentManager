package internalFrame.courseGuanli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Course;
import javax.swing.*;

import keyListener.InputKeyListener;

public class AddCourseJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Course course;
	private Box box = Box.createVerticalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box creditBox = Box.createHorizontalBox();
	private Box periodBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();

	private JTextField courseId = new JTextField(12);
	private JTextField courseName = new JTextField(12);
	private JTextField courseCredit = new JTextField(12);
	private JTextField coursePeriod = new JTextField(12);

	private JButton confirm = new JButton("确定");
	private JButton reset = new JButton("重置");

	private ActionListener listener = new Listener();

	public AddCourseJPanel() {
		confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		coursePeriod.addKeyListener(new InputKeyListener());
		
		courseId.setText("1");
		courseName.setText("JAVA语言程序设计");
		courseCredit.setText("2");
		coursePeriod.setText("40");
		
		idBox.add(new JLabel("课程编号"), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(courseId);
		nameBox.add(new JLabel("名称"), JLabel.CENTER);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(courseName);
		creditBox.add(new JLabel("学分"), JLabel.CENTER);
		creditBox.add(Box.createHorizontalStrut(20));
		creditBox.add(courseCredit);
		periodBox.add(new JLabel("学时"), JLabel.CENTER);
		periodBox.add(Box.createHorizontalStrut(20));
		periodBox.add(coursePeriod);
		buttonBox.add(confirm);
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(idBox);
		box.add(Box.createVerticalStrut(10));
		box.add(nameBox);
		box.add(Box.createVerticalStrut(10));
		box.add(creditBox);
		box.add(Box.createVerticalStrut(10));
		box.add(periodBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalGlue());
		add(box);
	}

	private class Listener implements ActionListener {// 监听器
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("确定")) {
				addRecord("确定");
			}
			if (e.getActionCommand().equals("重置")) {
				reset();
			}
		}

		private void addRecord(String command) {
			String id, name;
			float credit;
			int period;
			try {
				id = courseId.getText();
				name = courseName.getText();
				credit = Float.parseFloat(courseCredit.getText());
				period = Integer.parseInt(coursePeriod.getText());
				if ((credit <= 0) || (period <= 0))
					JOptionPane.showMessageDialog(null, "输入数据范围有误！", "警告",
							JOptionPane.ERROR_MESSAGE);
				else {
					course = new Course(id, name, credit, period);
					if (course.saveToFile())
						JOptionPane.showMessageDialog(null, "已经成功增加课程信息！",
								"增加成功", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"课程信息已经存在，或课程编号重复！", "增加失败",
								JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "输入数据格式有误！", "警告",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void reset() {// 重置
		courseId.setText("");
		courseName.setText("");
		courseCredit.setText("");
		coursePeriod.setText("");
	}
}
