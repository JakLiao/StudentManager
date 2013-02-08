package internalFrame.scoreMenuGuanli;

import model.Course;
import model.ScoreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import keyListener.InputKeyListener;

import model.Class;

public class AddScoreMenuJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ScoreMenu scoreMenu;
	private Class tempClass;
	private Course tempCourse;
	private int attendTestNumber;

	private Box box = Box.createVerticalBox();
	private Box gradeBox = Box.createHorizontalBox();
	private Box majorBox = Box.createHorizontalBox();
	private Box classBox = Box.createHorizontalBox();
	private Box courseIdBox = Box.createHorizontalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	private Box titleBox = Box.createHorizontalBox();
	private Box stuBox = Box.createHorizontalBox();
	private Box textBox = Box.createHorizontalBox();
	private Box attendNumberBox = Box.createHorizontalBox();
	private Box scoredateBox = Box.createHorizontalBox();
	private Box yearBox = Box.createHorizontalBox();
	private Box monthBox = Box.createHorizontalBox();
	private Box dateBox = Box.createHorizontalBox();
	private Box hourBox = Box.createHorizontalBox();
	private Box minuteBox = Box.createHorizontalBox();

	private JPanel titlePanel = new JPanel();
	private JTextField grade = new JTextField(4);
	private JTextField major = new JTextField(12);
	private JTextField classNumber = new JTextField(3);
	private JTextField attendNumber = new JTextField(4);
	private JTextField courseId = new JTextField(3);
	private JTextField studentId = new JTextField(12);
	private JTextField score = new JTextField(6);

	private JPanel stuPanel = new JPanel();
	private JPanel datePanel = new JPanel();
	private JTextField year = new JTextField(4);
	private JTextField month = new JTextField(4);
	private JTextField date = new JTextField(4);
	private JTextField hour = new JTextField(4);
	private JTextField minute = new JTextField(4);

	private JLabel text = new JLabel();

	private JButton confirm = new JButton("确定");
	private JButton input = new JButton("添加");
	private JButton reset = new JButton("重置");

	private ActionListener listener = new Listener();

	public AddScoreMenuJPanel() {
		confirm.addActionListener(listener);
		input.addActionListener(listener);
		reset.addActionListener(listener);
		
		studentId.addKeyListener(new InputKeyListener());
		score.addKeyListener(new InputKeyListener());
		grade.addKeyListener(new InputKeyListener());
		classNumber.addKeyListener(new InputKeyListener());
		year.addKeyListener(new InputKeyListener());
		month.addKeyListener(new InputKeyListener());
		date.addKeyListener(new InputKeyListener());
		hour.addKeyListener(new InputKeyListener());
		minute.addKeyListener(new InputKeyListener());
		
		grade.setText("2010");
		major.setText("软件工程");
		classNumber.setText("3");
		attendNumber.setText("");
		courseId.setText("");
		year.setText("2012");
		month.setText("6");
		date.setText("1");
		hour.setText("15");
		minute.setText("00");

		gradeBox.add(new JLabel("年级："), JLabel.CENTER);
		gradeBox.add(grade);
		gradeBox.add(Box.createHorizontalStrut(20));
		majorBox.add(new JLabel("专业："), JLabel.CENTER);
		majorBox.add(major);
		majorBox.add(Box.createHorizontalStrut(20));
		classBox.add(new JLabel("班："), JLabel.CENTER);
		classBox.add(classNumber);
		classBox.add(Box.createHorizontalStrut(20));
		courseIdBox.add(new JLabel("课程编号："), JLabel.CENTER);
		courseIdBox.add(courseId);
		courseIdBox.add(Box.createHorizontalStrut(20));
		titlePanel.add(gradeBox);
		titlePanel.add(majorBox);
		titlePanel.add(classBox);
		titlePanel.add(courseIdBox);
		titlePanel.add(confirm);
		titleBox.add(titlePanel);

		attendNumberBox.add(new JLabel("参加考试人数："), JLabel.CENTER);
		attendNumberBox.add(attendNumber);
		attendNumberBox.add(Box.createHorizontalStrut(20));
		yearBox.add(year);
		yearBox.add(new JLabel("年"));
		monthBox.add(month);
		monthBox.add(new JLabel("月"));
		dateBox.add(date);
		dateBox.add(new JLabel("日"));
		hourBox.add(hour);
		hourBox.add(new JLabel(":"));
		minuteBox.add(minute);
		datePanel.add(attendNumberBox);
		datePanel.add(yearBox);
		datePanel.add(monthBox);
		datePanel.add(dateBox);
		datePanel.add(hourBox);
		datePanel.add(minuteBox);
		scoredateBox.add(datePanel);

		textBox.add(text);

		idBox.add(new JLabel("学号："), JLabel.CENTER);
		idBox.add(studentId);
		idBox.add(Box.createHorizontalStrut(20));
		nameBox.add(new JLabel("分数："), JLabel.CENTER);
		nameBox.add(score);
		nameBox.add(Box.createHorizontalStrut(20));
		stuPanel.add(textBox);
		stuPanel.add(idBox);
		stuPanel.add(nameBox);
		stuPanel.add(input);
		stuBox.add(stuPanel);

		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(scoredateBox);
		box.add(Box.createVerticalStrut(10));
		box.add(titleBox);
		box.add(Box.createVerticalStrut(10));
		box.add(stuBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalGlue());
		add(box);

		studentId.setEditable(false);
		score.setEditable(false);
		input.setEnabled(false);
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
				int select = JOptionPane.showConfirmDialog(null,
						"确认取消本次输入的成绩单", "确认", JOptionPane.OK_CANCEL_OPTION);
				if (select == 0)
					reset();
			}
		}
	}

	private void addRecord(String command) {
		String major, courseId;
		int grade, classNumber;
		int[] testTime = new int[5];
		try {
			grade = Integer.parseInt(this.grade.getText());
			major = this.major.getText();
			courseId = this.courseId.getText();
			classNumber = Integer.parseInt(this.classNumber.getText());
			attendTestNumber = Integer.parseInt(this.attendNumber.getText());
			testTime[0] = Integer.parseInt(this.year.getText());
			testTime[1] = Integer.parseInt(this.month.getText());
			testTime[2] = Integer.parseInt(this.date.getText());
			testTime[3] = Integer.parseInt(this.hour.getText());
			testTime[4] = Integer.parseInt(this.minute.getText());
			if ((testTime[1] > 12) || (testTime[1] <= 0) || (testTime[2] > 31)
					|| (testTime[2] <= 0) || (testTime[3] > 24)
					|| (testTime[3] < 0) || (testTime[4] > 60)
					|| (testTime[4] < 0)) {
				JOptionPane.showMessageDialog(null, "考试日期格式有误", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (attendTestNumber <= 0) {
				JOptionPane.showMessageDialog(null, "参加考试人数不能少于1", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ((tempCourse = Course.queryFromFile(courseId)) == null) {
				JOptionPane.showMessageDialog(null, "课程文件中没有此编号的课程信息记录", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ((tempClass = Class.queryFromFile(grade, major, classNumber)) == null) {
				JOptionPane.showMessageDialog(null, "班级文件中没有此班级信息的记录", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else if (tempClass.getStudentNumber() < this.attendTestNumber) {
				JOptionPane.showMessageDialog(null, "参加考试人数多于班级人数", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ((scoreMenu = ScoreMenu.queryFromFile(tempClass, tempCourse)) != null) {
				JOptionPane.showMessageDialog(null, "系统中已经存在该班级该门课程的成绩单", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			scoreMenu = new ScoreMenu(tempClass, tempCourse, testTime, 0, null,
					null);
			this.grade.setEditable(false);
			this.major.setEditable(false);
			this.classNumber.setEditable(false);
			this.courseId.setEditable(false);
			this.attendNumber.setEditable(false);
			this.confirm.setEnabled(false);
			this.text.setText("输入第1个学生信息：");
			this.studentId.setEditable(true);
			this.score.setEditable(true);
			this.input.setEnabled(true);
			this.year.setEditable(false);
			this.month.setEditable(false);
			this.date.setEditable(false);
			this.hour.setEditable(false);
			this.minute.setEditable(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入数据格式有误", "出错",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void inputRecord(String command) {
		long id;
		float score;
		try {
			id = Long.parseLong(this.studentId.getText());
			score = Float.parseFloat(this.score.getText());
			if ((score < 0) || (score > ScoreMenu.MAX_SCORE)) {
				JOptionPane.showMessageDialog(null, "分数超出范围", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			int num = scoreMenu.getNumberOfAttendTestStudent();
			if (!scoreMenu.setScoreOfTest(score, id)) {
				JOptionPane.showMessageDialog(null, "输入的学生不属于此班级", "出错",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (scoreMenu.getNumberOfAttendTestStudent() == num) {
				JOptionPane.showMessageDialog(null, "已经修改了学号为" + id + "的学生的成绩",
						"修改", JOptionPane.WARNING_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(null,
					"已经记录了第" + (num + 1) + "个学生的成绩", "登记",
					JOptionPane.WARNING_MESSAGE);
			if (attendTestNumber <= scoreMenu.getNumberOfAttendTestStudent()) {
				JOptionPane.showMessageDialog(null, "已经成功完成了此次成绩的登记工作",
						"登记成绩单成功", JOptionPane.WARNING_MESSAGE);
				reset();
				this.scoreMenu.saveToFile();
				return;
			}
			this.text.setText("输入第" + (num + 2) + "个学生的信息");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入的数据格式有误", "出错",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void reset() {// 重置
		this.grade.setEditable(true);
		this.major.setEditable(true);
		this.classNumber.setEditable(true);
		this.attendNumber.setEditable(true);
		this.courseId.setEditable(true);
		this.confirm.setEnabled(true);
		this.year.setEditable(true);
		this.month.setEditable(true);
		this.date.setEditable(true);
		this.hour.setEditable(true);
		this.minute.setEditable(true);
		this.text.setText("");
		this.studentId.setEditable(false);
		this.studentId.setText("");
		this.score.setEditable(false);
		this.score.setText("");
		this.input.setEnabled(false);
	}
}
