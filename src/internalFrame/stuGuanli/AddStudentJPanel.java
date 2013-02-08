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
	private JButton confirm = new JButton("确定");
	private JButton reset = new JButton("重置");
	
	private ActionListener listener = new Listener();
	
    public  AddStudentJPanel() {
    	confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		studentId.addKeyListener(new InputKeyListener());
		
        idBox.add(new JLabel("学号："), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(studentId);
		nameBox.add(new JLabel("姓名："), JLabel.CENTER);
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
   
    private class Listener implements ActionListener {// 监听器
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("确定")) {
				addRecord("确定");
			}
			if (e.getActionCommand().equals("重置")) {
				reset();
			}
		}
		private void addRecord(String command) {
			try{
	            long id=Long.parseLong(studentId.getText());
	            String name=studentName.getText();   
	            Student student=new Student(id,name);   
	            if(student.saveToFile()){
	                JOptionPane.showMessageDialog(null,"增加学生信息成功","结果",JOptionPane.WARNING_MESSAGE);   
	                reset();
	            }
	            else   
	                JOptionPane.showMessageDialog(null,"学生信息已经存在或学号重复","结果",JOptionPane.ERROR_MESSAGE);       
	        }catch(Exception e){   
	            JOptionPane.showMessageDialog(null,"输入数据格式有误","出错",JOptionPane.ERROR_MESSAGE);
	        }
		}
    }
    public void reset() {// 重置
    	studentId.setText("");
		studentName.setText("");
	}
}   
