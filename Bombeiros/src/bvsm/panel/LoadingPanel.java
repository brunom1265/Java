package bvsm.panel;

import javax.swing.JFrame;

public class LoadingPanel extends BasePanel{

	public LoadingPanel(BasePanel previous, JFrame frame, String name, int x, int y, int width, int height) {
		super(previous, frame, name, x, y, width, height);
	}

	protected void createComponents(){
		createLabel("A carregar", "loadingLabel", 50, 50, 300, 35);
	}
	
	protected void tasks() {
		getLabel("loadingLabel").setText("A verificar atualizações");
		
	}
}
