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

import model.Course;

public class CourseChaxun extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	public CourseChaxun(){
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("�γ���Ϣ��ѯ");
		setLocation(200,200);
		getContentPane().setLayout(new GridBagLayout());
		final QueryCourseJPanel querycoursePane = new QueryCourseJPanel();
		getContentPane().add(querycoursePane);
		pack();
		setVisible(true);
	}
}
class QueryCourseJPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Box box = Box.createVerticalBox();
	private Box idinputBox = Box.createHorizontalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box creditBox = Box.createHorizontalBox();
	private Box periodBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();

	private JTextField inputcourseId = new JTextField(12);
	private JTextField courseId = new JTextField(12);
	private JTextField courseName = new JTextField(12);
	private JTextField courseCredit = new JTextField(12);
	private JTextField coursePeriod = new JTextField(12);

	private JButton confirm = new JButton("��ѯ");
	private JButton reset = new JButton("����");

	private ActionListener listener = new Listener();

	public QueryCourseJPanel() {
		confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		courseId.setEditable(false);  
		courseName.setEditable(false);  
		courseCredit.setEditable(false);  
		coursePeriod.setEditable(false);
		
		idinputBox.add(new JLabel("����γ̱�ţ�"), JLabel.CENTER);
		idinputBox.add(inputcourseId);
		idBox.add(new JLabel("�γ̱�ţ�"), JLabel.CENTER);
		idBox.add(courseId);
		nameBox.add(new JLabel("���ƣ�"), JLabel.CENTER);
		nameBox.add(courseName);
		creditBox.add(new JLabel("ѧ�֣�"), JLabel.CENTER);
		creditBox.add(courseCredit);
		periodBox.add(new JLabel("ѧʱ��"), JLabel.CENTER);
		periodBox.add(coursePeriod);
		buttonBox.add(confirm);
		buttonBox.add(reset);

		box.add(Box.createVerticalStrut(10));
		box.add(idinputBox);
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
		reset();
	}

	private class Listener implements ActionListener {// ������
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("��ѯ")) {
				queryRecord("��ѯ");
			}
			if (e.getActionCommand().equals("����")) {
				reset();
			}
		}

		private void queryRecord(String command) {
			Course course=null;   
	        String id=inputcourseId.getText();   
	        course=Course.queryFromFile(id);   
	        if(course!=null){   
	            courseId.setText(course.getId());   
	            courseName.setText(course.getName());   
	            courseCredit.setText(String.valueOf(course.getCredit()));   
	            coursePeriod.setText(String.valueOf(course.getPeriod()));    
	        }   
	        else{   
	            reset();  
	            JOptionPane.showMessageDialog(null,"��ѯ������Ӧ����Ϣ","��ѯ���",JOptionPane.WARNING_MESSAGE);               
	        }   
		}
	}

	public void reset() {// ����
		inputcourseId.setText("1");
		courseId.setText("");
		courseName.setText("");
		courseCredit.setText("");
		coursePeriod.setText("");

	}
}