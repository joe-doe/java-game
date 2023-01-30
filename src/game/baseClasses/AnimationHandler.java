package game.baseClasses;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class AnimationHandler {
	public List<Image> movingLeftImages = null;
	public List<Image> movingRightImages = null;
	public HashMap<Integer, Image> stillImages = null;

	public AnimationHandler() {
		movingLeftImages = new ArrayList<Image>();
		movingRightImages = new ArrayList<Image>();
		stillImages = new HashMap<Integer, Image>();
	}
}
