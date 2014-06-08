package com.mgdc.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ProgressBar extends TextButton {
	private Label bar;
	private float max;
	private float current;
	private float barWidth;

	public ProgressBar(float max, Skin skin) {
		super(String.format("%.0f / %.0f", max, max), skin, "bar");
		this.max = max;
		current = max;
		setDisabled(true);
		bar = new Label("", skin, "bar");
		addActorBefore(getLabel(), bar);
		bar.setPosition(2, 2);
	}
	
	@Override
	public void layout() {
		super.layout();
		barWidth = getWidth() - 4;
		bar.setSize(barWidth * getPercentage(), getHeight() - 4);
	}
	
	public void setProgress(float current) {
		this.current = current;
		setText(String.format("%.0f / %.0f", current, max));
		layout();
	}
	
	public float getProgress() {
		return current;
	}
	
	public float getPercentage() {
		return Math.max(0, current / max);
	}
	
}
