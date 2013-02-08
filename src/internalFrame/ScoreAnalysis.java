package internalFrame;

import java.awt.GridBagLayout;

import javax.swing.JInternalFrame;

public class ScoreAnalysis extends JInternalFrame{
	private static final long serialVersionUID = 1L;
	public ScoreAnalysis(){
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("成绩文本分析");
		getContentPane().setLayout(new GridBagLayout());
		final ScoreAnalysisJPanel queryscoreMenuPane = new ScoreAnalysisJPanel();
		getContentPane().add(queryscoreMenuPane);
		pack();
		setVisible(true);
	}
}