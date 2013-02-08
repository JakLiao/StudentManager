package internalFrame.classGuanli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import keyListener.InputKeyListener;

import model.Student;
import model.Class;

public class AddClassJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Class currentClass = null;
	private int number;
	private int currentNumber;

	private Box box = Box.createVerticalBox();
	private Box gradeBox = Box.createHorizontalBox();
	private Box majorBox = Box.createHorizontalBox();
	private Box classBox = Box.createHorizontalBox();
	private Box stuNumberBox = Box.createHorizontalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	private Box titleBox = Box.createHorizontalBox();
	private Box stuBox = Box.createHorizontalBox();
	private Box textBox = Box.createHorizontalBox();

	private JPanel titlePanel = new JPanel();
	private JTextField grade = new JTextField(5);
	private JTextField major = new JTextField(12);
	private JTextField classNumber = new JTextField(3);

	private JPanel stuPanel = new JPanel();
	private JTextField studentNumber = new JTextField(3);
	private JLabel text = new JLabel();
	private JTextField name = new JTextField(12);
	private JTextField id = new JTextField(12);
	private JButton confirm = new JButton("ȷ��");
	private JButton input = new JButton("���");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public AddClassJPanel() {
		confirm.addActionListener(listener);
		input.addActionListener(listener);
		reset.addActionListener(listener);
		
		grade.addKeyListener(new InputKeyListener());
		classNumber.addKeyListener(new InputKeyListener());
		id.addKeyListener(new InputKeyListener());
		
		id.setEditable(false);
		name.setEditable(false);
		input.setEnabled(false);
		
		gradeBox.add(new JLabel("�꼶��"), JLabel.CENTER);
		gradeBox.add(grade);
		gradeBox.add(Box.createHorizontalStrut(20));
		majorBox.add(new JLabel("רҵ��"), JLabel.CENTER);
		majorBox.add(major);
		majorBox.add(Box.createHorizontalStrut(20));
		classBox.add(new JLabel("�ࣺ"), JLabel.CENTER);
		classBox.add(classNumber);
		classBox.add(Box.createHorizontalStrut(20));
		stuNumberBox.add(new JLabel("�༶������"), JLabel.CENTER);
		stuNumberBox.add(studentNumber);
		stuNumberBox.add(Box.createHorizontalStrut(20));

		titlePanel.add(gradeBox);
		titlePanel.add(majorBox);
		titlePanel.add(classBox);
		titlePanel.add(stuNumberBox);
		titlePanel.add(confirm);
		titleBox.add(titlePanel);
		
		textBox.add(text);
		idBox.add(new JLabel("ѧ�ţ�"), JLabel.CENTER);
		idBox.add(id);
		idBox.add(Box.createHorizontalStrut(20));
		nameBox.add(new JLabel("������"), JLabel.CENTER);
		nameBox.add(name);
		nameBox.add(Box.createHorizontalStrut(20));
		stuPanel.add(textBox);
		stuPanel.add(idBox);
		stuPanel.add(nameBox);
		stuPanel.add(input);
		stuBox.add(stuPanel);

		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(titleBox);
		box.add(Box.createVerticalStrut(10));
		box.add(stuBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalGlue());
		add(box);
		reset();
		grade.setText("2010");
		major.setText("�������");
		classNumber.setText("1");
	}

	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ȷ��")) {
				addRecord("ȷ��");
			}
			if (e.getActionCommand().equals("���")) {
				inputRecord("���");
			}
			if (e.getActionCommand().equals("����")) {
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫȡ��������˰༶����Ϣ",
						"ȷ��", JOptionPane.OK_CANCEL_OPTION);
				if (i == 0)
					reset();
			}
		}
	}

	private void addRecord(String command) {
		int grade, classNumber;
		String major;
		try {
			grade = Integer.parseInt(this.grade.getText());
			classNumber = Integer.parseInt(this.classNumber.getText());
			number = Integer.parseInt(this.studentNumber.getText());
			major = this.major.getText();
			currentClass = new Class(grade, major, classNumber, 0, null);
			if (number <= 0) {
				currentClass = null;
				JOptionPane.showMessageDialog(null, "����༶�����Ƿ�", "����",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (Class.queryFromFile(grade, major, classNumber) != null) {
					currentClass = null;
					JOptionPane.showMessageDialog(null, "�༶��Ϣ�Ѿ�����", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				this.grade.setEditable(false);
				this.major.setEditable(false);
				this.classNumber.setEditable(false);
				this.studentNumber.setEditable(false);
				this.confirm.setEnabled(false);
				this.input.setEnabled(true);
				this.id.setEditable(true);
				this.name.setEditable(true);
				this.text.setText("�������1��ѧ����Ϣ");
				this.currentNumber = 1;
			}
		} catch (Exception e) {
			currentClass = null;
			JOptionPane.showMessageDialog(null, "����༶��Ϣ��ʽ����", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void inputRecord(String command) {
		Student student;
		try {
			if ((student = Student.queryFromFile(Long.parseLong(this.id
					.getText()))) == null) {
				JOptionPane.showMessageDialog(null, "ѧ���ļ���û�д�ѧ���ļ�¼\n", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!student.getName().equals(name.getText())) {
				JOptionPane.showMessageDialog(null, "ѧ��������ѧ�Ų�һ��\n", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!(currentClass.addStudent(student))) {
				JOptionPane.showMessageDialog(null, "�༶���Ѿ����ڴ�ѧ�ŵ�ѧ����¼\n", "����",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "�Ѿ��ɹ���¼��" + currentNumber
					+ "��ѧ����Ϣ", "�ɹ�", JOptionPane.WARNING_MESSAGE);
			currentNumber++;
			if (currentNumber > number) {
				currentClass.saveToFile();
				JOptionPane.showMessageDialog(null, "�Ѿ��ɹ����Ӱ༶��Ϣ\n", "���",
						JOptionPane.WARNING_MESSAGE);
				reset();
				return;
			}
			this.text.setText("�������" + currentNumber + "��ѧ����Ϣ");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�����ѧ����Ϣ��ʽ����\n", "����",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reset() {// ����
		grade.setEditable(true);
		major.setEditable(true);
		classNumber.setEditable(true);
		studentNumber.setEditable(true);
		confirm.setEnabled(true);
		input.setEnabled(false);
		grade.setText("");
		major.setText("");
		classNumber.setText("");
		studentNumber.setText("");
		text.setText("");
		id.setText("");
		name.setText("");
		id.setEditable(false);
		name.setEditable(false);
	}
}
