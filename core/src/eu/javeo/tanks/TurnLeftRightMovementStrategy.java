package eu.javeo.tanks;

public class TurnLeftRightMovementStrategy implements MovementStrategy {
	private boolean lastTurnRight = false;
	private final static Tank.Direction[] TURNS = new Tank.Direction[] {
			Tank.Direction.LEFT, Tank.Direction.UP, Tank.Direction.RIGHT, Tank.Direction.DOWN};
	private Tank.Direction lastDirection = TURNS[0];

	@Override
	public Tank.Direction getTankDirection() {
		// check for collision with the wall
		if (false) { // TODO collision with the wall detection HERE!
			// if colided with the wall choose left/right
			if (lastTurnRight) { // turn left
				lastTurnRight = false;
				lastDirection = turnLeft(lastDirection);
			} else { // turn right
				lastTurnRight = true;
				lastDirection = turnRight(lastDirection);
			}
		}
		return lastDirection;
	}

	protected Tank.Direction turnRight(Tank.Direction heading) {
		for (int i=0; i<4; i++) {
			if (TURNS[i].equals(heading)) return TURNS[(i+1)%4];
		}
		return null; // never happens
	}

	protected Tank.Direction turnLeft(Tank.Direction heading) {
		for (int i=0; i<4; i++) {
			if (TURNS[i].equals(heading)) return TURNS[(i-1+4)%4];
		}
		return null; // never happens
	}
}
