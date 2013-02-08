package internalFrame;

import internalFrame.scoreMenuGuanli.AddScoreMenuJPanel;
import internalFrame.scoreMenuGuanli.ModifyScoreMenuJPanel;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ScoreMenuGuanli extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	public ScoreMenuGuanli(){
		setIconifiable(true);
		setClosable(true);
		setTitle("成绩信息管理");
		setBounds(0,250,750,250);
		JTabbedPane tabPane = new JTabbedPane();
		final AddScoreMenuJPanel  scoreaddPanel = new AddScoreMenuJPanel();
		final ModifyScoreMenuJPanel scoremodPanel = new ModifyScoreMenuJPanel();
		tabPane.addTab("成绩单信息添加", null, scoreaddPanel, "成绩单添加");
		tabPane.addTab("成绩单信息修改", null, scoremodPanel, "成绩单修改");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//转界面时动作
			}
		});
		setVisible(true);
	}
}
