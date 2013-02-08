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
		setTitle("�ɼ���Ϣ����");
		setBounds(0,250,750,250);
		JTabbedPane tabPane = new JTabbedPane();
		final AddScoreMenuJPanel  scoreaddPanel = new AddScoreMenuJPanel();
		final ModifyScoreMenuJPanel scoremodPanel = new ModifyScoreMenuJPanel();
		tabPane.addTab("�ɼ�����Ϣ���", null, scoreaddPanel, "�ɼ������");
		tabPane.addTab("�ɼ�����Ϣ�޸�", null, scoremodPanel, "�ɼ����޸�");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//ת����ʱ����
			}
		});
		setVisible(true);
	}
}
