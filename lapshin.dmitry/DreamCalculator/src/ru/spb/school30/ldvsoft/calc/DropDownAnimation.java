package ru.spb.school30.ldvsoft.calc;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created with IntelliJ IDEA.
 * User: LDVSOFT
 * Date: 12.11.13
 * Time: 20:55
 * Animation of popping up / down
 */
public class DropDownAnimation extends Animation
{
	private final int targetHeight;
	private final View view;
	private final boolean down;

	public DropDownAnimation(View view, int targetHeight, boolean down) {
		this.view = view;
		this.targetHeight = targetHeight;
		this.down = down;
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		int newHeight;
		if (down) {
			newHeight = (int) (targetHeight * interpolatedTime);
		} else {
			newHeight = (int) (targetHeight * (1 - interpolatedTime));
		}
		view.getLayoutParams().height = newHeight;
		view.requestLayout();
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
						   int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
	}

	@Override
	public boolean willChangeBounds() {
		return true;
	}
}