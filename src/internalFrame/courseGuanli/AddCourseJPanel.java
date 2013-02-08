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

	private JButton confirm = new JButton("ȷ��");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public AddCourseJPanel() {
		confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		coursePeriod.addKeyListener(new InputKeyListener());
		
		courseId.setText("1");
		courseName.setText("JAVA���Գ������");
		courseCredit.setText("2");
		coursePeriod.setText("40");
		
		idBox.add(new JLabel("�γ̱��"), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(courseId);
		nameBox.add(new JLabel("����"), JLabel.CENTER);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(courseName);
		creditBox.add(new JLabel("ѧ��"), JLabel.CENTER);
		creditBox.add(Box.createHorizontalStrut(20));
		creditBox.add(courseCredit);
		periodBox.add(new JLabel("ѧʱ"), JLabel.CENTER);
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

	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ȷ��")) {
				addRecord("ȷ��");
			}
			if (e.getActionCommand().equals("����")) {
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
					JOptionPane.showMessageDialog(null, "�������ݷ�Χ����", "����",
							JOptionPane.ERROR_MESSAGE);
				else {
					course = new Course(id, name, credit, period);
					if (course.saveToFile())
						JOptionPane.showMessageDialog(null, "�Ѿ��ɹ����ӿγ���Ϣ��",
								"���ӳɹ�", JOptionPane.WARNING_MESSAGE);
					else
						JOptionPane.showMessageDialog(null,
								"�γ���Ϣ�Ѿ����ڣ���γ̱���ظ���", "����ʧ��",
								JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "�������ݸ�ʽ����", "����",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void reset() {// ����
		courseId.setText("");
		courseName.setText("");
		courseCredit.setText("");
		coursePeriod.setText("");
	}
}
