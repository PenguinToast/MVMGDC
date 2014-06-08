package com.mgdc.game.objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.utils.Array;
import com.mgdc.game.objects.exploration.ExplorationObject;

public class ExplorationObjects extends MapObject {
	public Array<ExplorationObject> objects;

	public ExplorationObjects() {
		objects = new Array<ExplorationObject>();
	}
}
