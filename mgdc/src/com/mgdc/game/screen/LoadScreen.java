package com.mgdc.game.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mgdc.game.Global;

public class LoadScreen extends BaseScreen {

	public LoadScreen() {
		for (int i = 0; i < 3; i++) {
			table.add(new SaveBox()).expand().fill();
			table.row();
		}
	}

	private class SaveBox extends Table {
		private boolean wasDeleted = false;

		public SaveBox() {
			super();
			pad(5);
			final TextButton saveButton = new TextButton("ITS A SAVE", Global.skin);
			add(saveButton).expand().fill();
			row();
			Table managerTable = new Table();
			managerTable.right();
			TextButton deleteButton = new TextButton("Delete", Global.skin, "small");
			deleteButton.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					if(wasDeleted) {
						return;
					}
					Dialog confirmDiag = new Dialog("", Global.skin) {
						@Override
						protected void result(Object object) {
							if (object == Boolean.TRUE) {
								saveButton.setText("NEW SAVE");
								wasDeleted = true;
							} else {
								
							}
						}
					};
					confirmDiag.text("Are you sure you want to delete the save?");
					confirmDiag.button("OK", Boolean.TRUE);
					confirmDiag.button("Cancel", Boolean.FALSE);
					confirmDiag.show(stage);
				}
			});
			managerTable.add(deleteButton).right().pad(5);
			add(managerTable).fillX().expandX();
		}
	}

}
