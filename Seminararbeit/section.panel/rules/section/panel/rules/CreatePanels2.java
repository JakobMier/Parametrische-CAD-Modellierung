package section.panel.rules;

import de.iils.dc43.core.JavaRule;
import section.Panel;
import section.Section;

@SuppressWarnings("all")
public class CreatePanels extends JavaRule {

	@Override
	public void execute() throws Exception {
		Section section = getGraph().firstInstance(Section.class);

		int nPanelX = section.getNPanelX();
		int nPanelZ = section.getNPanelZ();

		Panel[][] panel = new Panel[nPanelZ][nPanelX];

		for (int i = 0; i < nPanelZ; i++) {
			for (int j = 0; j < nPanelX; j++) {
				panel[i][j] = Panel.create();
				section.getPanel().add(panel[i][j]);
				panel[i][j].setXi(j);
				panel[i][j].setZi(i);
			}
		}
	}

}