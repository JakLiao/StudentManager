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

import model.Student;

public class StuChaxun extends JInternalFrame{
	private static final long serialVersionUID = 1L;

	public StuChaxun() {
		super();
		setIconifiable(true);
		setClosable(true);
		setLocation(100,100);
		setTitle("ѧ����Ϣ��ѯ");
		getContentPane().setLayout(new GridBagLayout());
		final QueryStudentJPanel querystuPane = new QueryStudentJPanel();
		getContentPane().add(querystuPane);
		pack();
		setVisible(true);
	}
}

class QueryStudentJPanel extends JPanel {   
	private static final long serialVersionUID = 1L;
	private Box box = Box.createVerticalBox();
	private Box idinputBox = Box.createHorizontalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	
	private JTextField inputstudentId = new JTextField(12);
	private JTextField studentId = new JTextField(12);
	private JTextField studentName = new JTextField(12);
	private JButton confirm = new JButton("��ѯ");
	private JButton reset = new JButton("����");
	
	private ActionListener listener = new Listener();
	
    public  QueryStudentJPanel() {
    	confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		studentName.setEditable(false);  
		studentId.setEditable(false);  
		
		idinputBox.add(new JLabel("����ѧ��"), JLabel.CENTER);
		idinputBox.add(Box.createHorizontalStrut(20));
		idinputBox.add(inputstudentId);
        idBox.add(new JLabel("ѧ��"), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(studentId);
		nameBox.add(new JLabel("����"), JLabel.CENTER);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(studentName);
		buttonBox.add(confirm);
		buttonBox.add(reset);
		
		box.add(Box.createVerticalStrut(10));
		box.add(idinputBox);
		box.add(Box.createVerticalStrut(10));
		box.add(idBox);
		box.add(Box.createVerticalStrut(10));
		box.add(nameBox);
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
			try{   
	            long id=Long.parseLong(inputstudentId.getText());   
	            Student student=Student.queryFromFile(id);   
	            if(student!=null){   
	                studentId.setText(String.valueOf(student.getId()));   
	                studentName.setText(student.getName());   
	                JOptionPane.showMessageDialog(null,"��ѯѧ����Ϣ�ɹ�","��ѯ���",JOptionPane.WARNING_MESSAGE);   
	            }   
	            else{   
	                studentId.setText("");   
	                studentName.setText("");
	                JOptionPane.showMessageDialog(null,"��ѯ������Ӧѧ����Ϣ","��ѯ���",JOptionPane.WARNING_MESSAGE);                   
	            }   
	        }catch(Exception e){   
	            JOptionPane.showMessageDialog(null,"������������","����",JOptionPane.ERROR_MESSAGE);   
	        }
		}
    }
    public void reset() {// ����
    	inputstudentId.setText("1");
    	studentId.setText("");
		studentName.setText("");
	}
}
