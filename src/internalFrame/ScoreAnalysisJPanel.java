package internalFrame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Class;
import model.Course;
import model.ScoreMenu;

public class ScoreAnalysisJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Box box = Box.createVerticalBox();
	private Box gradeBox = Box.createHorizontalBox();
	private Box majorBox = Box.createHorizontalBox();
	private Box classBox = Box.createHorizontalBox();
	private Box courseIdBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	private Box titleBox = Box.createHorizontalBox();

	private Box textBox = Box.createHorizontalBox();

	private JPanel titlePanel = new JPanel();
	private JTextField grade = new JTextField(4);
	private JTextField major = new JTextField(12);
	private JTextField classNumber = new JTextField(3);
	private JTextField courseId = new JTextField(5);

	private JScrollPane jScrollPane1 = new JScrollPane();
	private JEditorPane text = new JEditorPane();

	private JButton confirm = new JButton("��ѯ");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public ScoreAnalysisJPanel() {
		confirm.addActionListener(listener);
		reset.addActionListener(listener);

		text.setEditable(false);
		text.setPreferredSize(new Dimension(100, 250));
		jScrollPane1.setViewportView(text);

		gradeBox.add(new JLabel("�꼶��"), JLabel.CENTER);
		gradeBox.add(grade);
		gradeBox.add(Box.createHorizontalStrut(20));
		majorBox.add(new JLabel("רҵ��"), JLabel.CENTER);
		majorBox.add(major);
		majorBox.add(Box.createHorizontalStrut(20));
		classBox.add(new JLabel("�ࣺ"), JLabel.CENTER);
		classBox.add(classNumber);
		classBox.add(Box.createHorizontalStrut(20));
		courseIdBox.add(new JLabel("�γ̱�ţ�"), JLabel.CENTER);
		courseIdBox.add(courseId);
		courseIdBox.add(Box.createHorizontalStrut(20));
		titlePanel.add(gradeBox);
		titlePanel.add(majorBox);
		titlePanel.add(classBox);
		titlePanel.add(courseIdBox);
		titlePanel.add(confirm);
		titleBox.add(titlePanel);

		textBox.add(jScrollPane1);

		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(confirm);
		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(titleBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalStrut(10));
		box.add(textBox);
		box.add(Box.createVerticalGlue());
		add(box);
		reset();
	}

	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("��ѯ")) {
				queryRecord("��ѯ");
			}
			if (e.getActionCommand().equals("����"))
				reset();
		}
	}

	private void queryRecord(String command) {
		int grade, classNumber;
		String major, courseId;
		ScoreMenu newscoreMenu;
		try {
			grade = Integer.parseInt(this.grade.getText());
			classNumber = Integer.parseInt(this.classNumber.getText());
			major = this.major.getText();
			courseId = this.courseId.getText();
			if ((newscoreMenu = (ScoreMenu.queryFromFile(new Class(grade,
					major, classNumber, 0, null), new Course(courseId, null, 0,
					0)))) == null) {
				JOptionPane.showMessageDialog(null, "���Ҳ�����Ӧ�ĳɼ�����Ϣ", "��ѯ���",
						JOptionPane.ERROR_MESSAGE);
				this.text.setText("���Ҳ�����Ӧ�ĳɼ�����Ϣ");
			} else {
				long[] id = newscoreMenu.getIdOfStudents();
				float[] scoreOfTest = newscoreMenu.getScoreOfTest();
				float max = newscoreMenu.getHighestScore();
				float min = newscoreMenu.getLowestScore();
				float average = newscoreMenu.getAverageScore();
				String scoreText = "\n��߷֣�" + max + "\n��ͷ֣�" + min + "\nƽ���֣�"
						+ average + "\nѧ���ɼ�������Ϣ���£�\nѧ��\t����\n";
				for (int i = 0; i < newscoreMenu.getNumberOfAttendTestStudent(); i++)
					scoreText += (id[i] + "\t" + scoreOfTest[i] + "\n");
				this.text.setText(scoreText);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݸ�ʽ����", "����",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	public void reset() {// ����
		major.setText("�������");
		grade.setText("2010");
		classNumber.setText("3");
		courseId.setText("1");
		text.setText("");
	}

}
