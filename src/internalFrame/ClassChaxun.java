package internalFrame;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import model.Class;
import model.Student;

public class ClassChaxun extends JInternalFrame {
	private static final long serialVersionUID = 1L;

	public ClassChaxun() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�༶��Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		final QueryClassJPanel queryclassPane = new QueryClassJPanel();
		getContentPane().add(queryclassPane);
		pack();
		setVisible(true);
	}
}

class QueryClassJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Box box = Box.createVerticalBox();
	private Box gradeBox = Box.createHorizontalBox();
	private Box majorBox = Box.createHorizontalBox();
	private Box classBox = Box.createHorizontalBox();

	private Box buttonBox = Box.createHorizontalBox();
	private Box titleBox = Box.createHorizontalBox();
	private Box textBox = Box.createHorizontalBox();

	private JPanel titlePanel = new JPanel();
	private JTextField grade = new JTextField(5);
	private JTextField major = new JTextField(12);
	private JTextField classNumber = new JTextField(3);

	private JScrollPane jScrollPane1 = new JScrollPane();
	private JEditorPane text = new JEditorPane();
	
	
	private JButton confirm = new JButton("��ѯ");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public QueryClassJPanel() {
		confirm.addActionListener(listener);
		reset.addActionListener(listener);

		text.setEditable(false);
		text.setPreferredSize(new Dimension(100,250));
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

		titlePanel.add(gradeBox);
		titlePanel.add(majorBox);
		titlePanel.add(classBox);
		titlePanel.add(confirm);
		titleBox.add(titlePanel);
		
		textBox.add(jScrollPane1);
		
		buttonBox.add(Box.createHorizontalStrut(40));
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(titleBox);
		box.add(Box.createVerticalStrut(10));
		box.add(textBox);
		box.add(Box.createVerticalStrut(10));
		box.add(buttonBox);
		box.add(Box.createVerticalGlue());
		add(box);
		reset();
	}

	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("��ѯ")) {
				addRecord("��ѯ");
			}
			if (e.getActionCommand().equals("����"))
				reset();
		}
	}

	private void addRecord(String command) {
		int grade, classNumber;
		String major;
		Class newClass;
		try {
			grade = Integer.parseInt(this.grade.getText());
			classNumber = Integer.parseInt(this.classNumber.getText());
			major = this.major.getText();
			if ((newClass = Class.queryFromFile(grade, major, classNumber)) != null) {
				String string = "�꼶��" + newClass.getGrade() + "\nרҵ��"
						+ newClass.getMajor() + "\n��ţ�"
						+ newClass.getClassNumber() + "\nѧ��������"
						+ newClass.getStudentNumber() + "\nѧ��������Ϣ���£�\nѧ��\t����\n";
				Student[] student = newClass.getStudents();
				for (int i = 0; i < newClass.getStudentNumber(); i++)
					string += (student[i].getId() + "\t" + student[i].getName() + "\n");
				this.text.setText(string);
			} else {
				JOptionPane.showMessageDialog(null, "ϵͳ��û�д˰༶�������Ϣ", "��ѯ���",
						JOptionPane.WARNING_MESSAGE);
				this.text.setText("");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "����İ༶��Ϣ��ʽ����", "����",
					JOptionPane.ERROR_MESSAGE);
			this.text.setText("");
		}
	}

	public void reset() {// ����
		grade.setText("2010");
		major.setText("�������");
		classNumber.setText("3");
		text.setText("��ѯ�༶������ѧ����Ϣ"); 
	}
}