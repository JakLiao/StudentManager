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
		setTitle("学生信息查询");
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
	private JButton confirm = new JButton("查询");
	private JButton reset = new JButton("重置");
	
	private ActionListener listener = new Listener();
	
    public  QueryStudentJPanel() {
    	confirm.addActionListener(listener);
		reset.addActionListener(listener);
		
		studentName.setEditable(false);  
		studentId.setEditable(false);  
		
		idinputBox.add(new JLabel("输入学号"), JLabel.CENTER);
		idinputBox.add(Box.createHorizontalStrut(20));
		idinputBox.add(inputstudentId);
        idBox.add(new JLabel("学号"), JLabel.CENTER);
		idBox.add(Box.createHorizontalStrut(20));
		idBox.add(studentId);
		nameBox.add(new JLabel("姓名"), JLabel.CENTER);
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
   
    private class Listener implements ActionListener {// 监听器
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("查询")) {
				queryRecord("查询");
			}
			if (e.getActionCommand().equals("重置")) {
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
	                JOptionPane.showMessageDialog(null,"查询学生信息成功","查询结果",JOptionPane.WARNING_MESSAGE);   
	            }   
	            else{   
	                studentId.setText("");   
	                studentName.setText("");
	                JOptionPane.showMessageDialog(null,"查询不到相应学生信息","查询结果",JOptionPane.WARNING_MESSAGE);                   
	            }   
	        }catch(Exception e){   
	            JOptionPane.showMessageDialog(null,"输入数据有误","出错",JOptionPane.ERROR_MESSAGE);   
	        }
		}
    }
    public void reset() {// 重置
    	inputstudentId.setText("1");
    	studentId.setText("");
		studentName.setText("");
	}
}
