package bvsm.panel;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.tools.Tool;

public class PerguntasPanel extends BasePanel{
	
	
	public PerguntasPanel(JFrame frame, String name, int x, int y, int width, int height, Color color) {
		super(frame, name, x, y, width, height, color);
	}

	protected void createComponents(){
		
		createLabel(700, 0, 300,300, images.getImage("Bombeiros"));
		
		String[] topics = {"Inc�ndio", "Sa�de", "Sorteado"};
		String[] incendio = {"Florestal", "Urbano"};
		
		createComboBox(topics, "topic", 100, 100, 150, 30, true);
		createComboBox(incendio, "subTopic", 300, 100, 150, 30, true);
		createButton("Iniciar teste", "iniciar", 100, 200);
		createQuestionArea();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String[] incendio = {"Florestal", "Urbano"};
		String[] saude = {"TS", "TAT", "TAS"};

        String topic = "";
        
        if(e.getActionCommand() == "comboBoxChanged"){
        	JComboBox<String> cb = (JComboBox<String>)e.getSource();
            topic = (String)cb.getSelectedItem();
        }
        
        if(topic == "Sa�de"){
        	updateCombo(saude, "subTopic");
        }
        
        if(topic == "Inc�ndio"){
        	updateCombo(incendio, "subTopic");
        }
        
        if(topic == "Sorteado"){
        }
        
		if(e.getActionCommand() == "Iniciar teste"){
			getButton("iniciar").setVisible(false);
			createQuestionArea();
			setRadioBoxVisible(true);
			setComboBoxEditable(false);
		}
	}

	private void createQuestionArea() {
		createButtonGroup();
		createRadioButton("Answer1", 100, 300, 400, 40, false).setSelected(true);
		createRadioButton("Answer2", 100, 350, 400, 40, false);
		createRadioButton("Answer3", 100, 400, 400, 40, false);
		
	}
}