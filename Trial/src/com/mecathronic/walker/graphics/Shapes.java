package com.mecathronic.walker.graphics;

public class Shapes {

	Screen screen;
	Vectors vStart;
	Vectors vStop;

	public Shapes(Screen screen) {
		this.screen = screen;
	}

	public void DrawCircle(int xCenter, int yCenter, int r) {
	}

	public void drawLine(int x1, int y1, int x2, int y2) {
		vStart = new Vectors(x1, y1);
		vStop = new Vectors(x2, y2);
		float delta;

		int rise = vStop.y - vStart.y;
		int run = vStop.x - vStart.x;
		float m = (float) rise / run;

		if (run == 0) {
			if (vStop.y < vStart.y) {
				swap(vStop, vStart, "y");
			}
			for (int y = vStart.y; y <= vStop.y; y++) {
				screen.setPixel(vStart.x, y);
			}
		} else {

			int adjust = 1;
			if (m >= 0) adjust = 1;
			else adjust = -1;
			int offset = 0;
			double threshold;
			int thresholdInc;
			if (m <= 1 && m >= -1) {

				delta = Math.abs(rise) * 2;
				threshold = Math.abs(run);
				thresholdInc = Math.abs(run) * 2;
				int y = vStart.y;

				if (vStop.x < vStart.x) {

					swap(vStart, vStop, "x");

					y = vStop.y;
				}
				for (int x = vStart.x; x <= vStop.x; x++) {
					screen.setPixel(x, y);

					offset += delta;
					if (offset >= threshold) {
						y += adjust;
						threshold += thresholdInc;
					}
				}
			} else {
				delta = Math.abs(run) * 2;
				threshold = Math.abs(rise);
				thresholdInc = Math.abs(rise) * 2;
				int x = vStart.x;
				if (vStop.y < vStart.y) {
					swap(vStop, vStart, "y");
					x = vStop.x;

				}

				for (int y = vStart.y; y <= vStop.y; y++) {

					screen.setPixel(x, y);
					offset += delta;
					if (offset >= threshold) {
						x += adjust;

						threshold += thresholdInc;
					}
				}
			}
		}

	}

	public void drawSquare(int x1, int y1, int side) {
		for(int i = 0; i <= side; i++){
			drawLine(x1, y1, x1 + i, y1 + side);
			drawLine(x1, y1, x1 + side, y1 + i);

		}
	}

	private void swap(Vectors vectorX, Vectors vectorY, String type) {
		if (type == "y") {
			int temp = vectorX.y;

			vectorX.y = vectorY.y;
			vectorY.y = temp;
		} else if (type == "x") {
			int temp = vectorX.x;

			vectorX.x = vectorY.x;
			vectorY.x = temp;
		}
	}
}
