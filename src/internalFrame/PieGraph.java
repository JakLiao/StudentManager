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

import model.Class;
import model.Course;
import model.ScoreMenu;

public class PieGraph extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	public PieGraph(){
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�ɼ���״ͼ����");
		setLocation(0,100);
		getContentPane().setLayout(new GridBagLayout());
		final PieGraphJPanel pieGraphJPanel = new PieGraphJPanel();
		getContentPane().add(pieGraphJPanel);
		pack();
		setVisible(true);
	}
}
class PieGraphJPanel extends JPanel {
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

	private JButton confirm = new JButton("ȷ��");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public PieGraphJPanel() {

		confirm.addActionListener(listener);
		reset.addActionListener(listener);

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
	public void reset() {// ����
		major.setText("�������");
		grade.setText("2010");
		classNumber.setText("3");
		courseId.setText("1");
	}
	
	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ȷ��")) {
				piegraphgenerate("ȷ��");
			}
			if (e.getActionCommand().equals("����"))
				reset();
		}
	}
	private void piegraphgenerate(String command) {
		int grade, classNumber;
		String major, courseId;
		ScoreMenu newscoreMenu;
		try {
			grade = Integer.parseInt(this.grade.getText());
			classNumber = Integer.parseInt(this.classNumber.getText());
			major = this.major.getText();
			courseId = this.courseId.getText();
			if ((newscoreMenu = (ScoreMenu.queryFromFile(new Class(grade,major, classNumber, 0, null), new Course(courseId, null, 0,0)))) == null) {
				JOptionPane.showMessageDialog(null, "���Ҳ�����Ӧ�ĳɼ�����Ϣ", "��ѯ���",
						JOptionPane.ERROR_MESSAGE);
			} else {
	            PieGraphAnasysJFrame scoreCircleAnasys=new PieGraphAnasysJFrame(newscoreMenu);   
	            scoreCircleAnasys.setSize(520,400);
	            scoreCircleAnasys.setLocationRelativeTo(null);
	            scoreCircleAnasys.setVisible(true); 
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "�������ݸ�ʽ����", "����",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
}