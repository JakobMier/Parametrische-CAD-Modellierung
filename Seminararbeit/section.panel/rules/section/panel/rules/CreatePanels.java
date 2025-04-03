package section.panel.rules;

import de.iils.dc43.core.JavaRule;
import section.*;
import section.panel.*;

@SuppressWarnings("all")
public class CreatePanels extends JavaRule {

	@Override
	public void execute() throws Exception {

		Section section = getGraph().firstInstance(Section.class);

		int n_PanelX = section.getNPanelX();
		int n_PanelZ = section.getNPanelZ();

		Panel[][] panels = new Panel[n_PanelZ][n_PanelX];

		for (int i = 0; i < n_PanelZ; i++) {
			for (int j = 0; j < n_PanelX; j++) {
				panels[i][j] = Panel.create();
				section.getPanel().add(panels[i][j]);
				panels[i][j].setXi(j);
				panels[i][j].setZi(i);
			}
		}
	}
}