package shop.main;

import shop.data.Data;
import shop.ui.UI;
import shop.ui.UIFactory;

public class Main {
	private Main() {
	}

	public static void main(String[] args) {
		UIFactory uiFactory = new UIFactory();
		Control control;
		if (Math.random() <= 0.5) {
			control = new Control(Data.newInventory(), (UI) uiFactory.start("text", null, null));
		} else {
			control = new Control(Data.newInventory(), (UI) uiFactory.start("popup", null, null));
		}
		control.run();
	}
}


