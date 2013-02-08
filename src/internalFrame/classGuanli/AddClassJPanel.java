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
	private JButton confirm = new JButton("确定");
	private JButton input = new JButton("添加");
	private JButton reset = new JButton("重置");

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
		
		gradeBox.add(new JLabel("年级："), JLabel.CENTER);
		gradeBox.add(grade);
		gradeBox.add(Box.createHorizontalStrut(20));
		majorBox.add(new JLabel("专业："), JLabel.CENTER);
		majorBox.add(major);
		majorBox.add(Box.createHorizontalStrut(20));
		classBox.add(new JLabel("班："), JLabel.CENTER);
		classBox.add(classNumber);
		classBox.add(Box.createHorizontalStrut(20));
		stuNumberBox.add(new JLabel("班级人数："), JLabel.CENTER);
		stuNumberBox.add(studentNumber);
		stuNumberBox.add(Box.createHorizontalStrut(20));

		titlePanel.add(gradeBox);
		titlePanel.add(majorBox);
		titlePanel.add(classBox);
		titlePanel.add(stuNumberBox);
		titlePanel.add(confirm);
		titleBox.add(titlePanel);
		
		textBox.add(text);
		idBox.add(new JLabel("学号："), JLabel.CENTER);
		idBox.add(id);
		idBox.add(Box.createHorizontalStrut(20));
		nameBox.add(new JLabel("姓名："), JLabel.CENTER);
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
		major.setText("软件工程");
		classNumber.setText("1");
	}

	private class Listener implements ActionListener {// 监听器
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("确定")) {
				addRecord("确定");
			}
			if (e.getActionCommand().equals("添加")) {
				inputRecord("添加");
			}
			if (e.getActionCommand().equals("重置")) {
				int i = JOptionPane.showConfirmDialog(null, "确定要取消此输入此班级的信息",
						"确认", JOptionPane.OK_CANCEL_OPTION);
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
				JOptionPane.showMessageDialog(null, "输入班级人数非法", "出错",
						JOptionPane.ERROR_MESSAGE);
			} else {
				if (Class.queryFromFile(grade, major, classNumber) != null) {
					currentClass = null;
					JOptionPane.showMessageDialog(null, "班级信息已经存在", "出错",
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
				this.text.setText("请输入第1个学生信息");
				this.currentNumber = 1;
			}
		} catch (Exception e) {
			currentClass = null;
			JOptionPane.showMessageDialog(null, "输入班级信息格式有误", "出错",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void inputRecord(String command) {
		Student student;
		try {
			if ((student = Student.queryFromFile(Long.parseLong(this.id
					.getText()))) == null) {
				JOptionPane.showMessageDialog(null, "学生文件中没有此学生的记录\n", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (!student.getName().equals(name.getText())) {
				JOptionPane.showMessageDialog(null, "学生名称与学号不一致\n", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!(currentClass.addStudent(student))) {
				JOptionPane.showMessageDialog(null, "班级中已经存在此学号的学生记录\n", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null, "已经成功记录第" + currentNumber
					+ "个学生信息", "成功", JOptionPane.WARNING_MESSAGE);
			currentNumber++;
			if (currentNumber > number) {
				currentClass.saveToFile();
				JOptionPane.showMessageDialog(null, "已经成功增加班级信息\n", "结果",
						JOptionPane.WARNING_MESSAGE);
				reset();
				return;
			}
			this.text.setText("请输入第" + currentNumber + "个学生信息");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入的学生信息格式有误\n", "出错",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reset() {// 重置
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
