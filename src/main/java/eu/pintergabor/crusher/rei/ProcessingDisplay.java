package eu.pintergabor.crusher.rei;

import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;


public interface ProcessingDisplay extends SimpleGridMenuDisplay {
	@Override
	default int getWidth() {
		return 1;
	}

	@Override
	default int getHeight() {
		return 1;
	}

	default double getExperience() {
		return 0.1F;
	}

	default double getProcessingTime() {
		return 100;
	}
}
