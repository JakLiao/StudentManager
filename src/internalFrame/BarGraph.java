package internalFrame;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.ScoreMenu;
import model.Class;
import model.Course;

public class BarGraph extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public BarGraph() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("成绩柱状图分析");
		getContentPane().setLayout(new GridBagLayout());
		final BarGraphJPanel barGraphJPanel = new BarGraphJPanel();
		getContentPane().add(barGraphJPanel);
		pack();
		setVisible(true);
	}
}

class BarGraphJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	ScoreMenu scoreMenu;
	
	private Box box = Box.createVerticalBox();
	private Box gradeBox = Box.createHorizontalBox();
	private Box majorBox = Box.createHorizontalBox();
	private Box classBox = Box.createHorizontalBox();
	private Box courseIdBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	private Box titleBox = Box.createHorizontalBox();

	private Box bargraphBox = Box.createHorizontalBox();

	private JPanel titlePanel = new JPanel();
	private JTextField grade = new JTextField(4);
	private JTextField major = new JTextField(12);
	private JTextField classNumber = new JTextField(3);
	private JTextField courseId = new JTextField(5);

	private JButton confirm = new JButton("确定");
	private JButton reset = new JButton("重置");

	private ActionListener listener = new Listener();

	public BarGraphJPanel() {

		confirm.addActionListener(listener);
		reset.addActionListener(listener);

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


		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(confirm);
		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(titleBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalStrut(10));
		box.add(bargraphBox);
		box.add(Box.createVerticalGlue());
		add(box);
		reset();
	}
	public void reset() {// 重置
		major.setText("软件工程");
		grade.setText("2010");
		classNumber.setText("3");
		courseId.setText("1");
	}
	
	private class Listener implements ActionListener {// 监听器
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("确定")) {
				bargraphgenerate("确定");
			}
			if (e.getActionCommand().equals("重置"))
				reset();
		}
	}
	private void bargraphgenerate(String command) {
		int grade, classNumber;
		String major, courseId;
		ScoreMenu newscoreMenu;
		try {
			grade = Integer.parseInt(this.grade.getText());
			classNumber = Integer.parseInt(this.classNumber.getText());
			major = this.major.getText();
			courseId = this.courseId.getText();
			if ((newscoreMenu = (ScoreMenu.queryFromFile(new Class(grade,major, classNumber, 0, null), new Course(courseId, null, 0,0)))) == null) {
				JOptionPane.showMessageDialog(null, "查找不到相应的成绩单信息", "查询结果",
						JOptionPane.ERROR_MESSAGE);
			} else {
				BarGraphlAnasysJFrame scoreColumnAnasys=new BarGraphlAnasysJFrame(newscoreMenu);   
	            scoreColumnAnasys.setSize(520,400);   
	            scoreColumnAnasys.setLocationRelativeTo(null);
	            scoreColumnAnasys.setVisible(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "输入数据格式有误", "出错",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
}