package internalFrame.stuGuanli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Student;
import javax.swing.*;

import keyListener.InputKeyListener;
 
public class AddStudentJPanel extends JPanel {   
	private static final long serialVersionUID = 1L;
	private Box box = Box.createVerticalBox();
	private Box idBox = Box.createHorizontalBox();
	private Box nameBox = Box.createHorizontalBox();
	private Box buttonBox = Box.createHorizontalBox();
	
	private JTextField studentId = new JTextField(12);
	
	private JTextField studentName = new JTextField(12);
	private JButton confirm = new JButton("ȷ��");
	private JButton reset = new JButton("����");
	
	private ActionListener listener = new Listener();
	
    public  AddStudentJPanel() {
    	confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		studentId.addKeyListener(new InputKeyListener());
		
        idBox.add(new JLabel("ѧ�ţ�"), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(studentId);
		nameBox.add(new JLabel("������"), JLabel.CENTER);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(studentName);
		buttonBox.add(confirm);
		buttonBox.add(reset);
		
		box.add(Box.createVerticalStrut(10));
		box.add(idBox);
		box.add(Box.createVerticalStrut(10));
		box.add(nameBox);
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
			try{
	            long id=Long.parseLong(studentId.getText());
	            String name=studentName.getText();   
	            Student student=new Student(id,name);   
	            if(student.saveToFile()){
	                JOptionPane.showMessageDialog(null,"����ѧ����Ϣ�ɹ�","���",JOptionPane.WARNING_MESSAGE);   
	                reset();
	            }
	            else   
	                JOptionPane.showMessageDialog(null,"ѧ����Ϣ�Ѿ����ڻ�ѧ���ظ�","���",JOptionPane.ERROR_MESSAGE);       
	        }catch(Exception e){   
	            JOptionPane.showMessageDialog(null,"�������ݸ�ʽ����","����",JOptionPane.ERROR_MESSAGE);
	        }
		}
    }
    public void reset() {// ����
    	studentId.setText("");
		studentName.setText("");
	}
}   
