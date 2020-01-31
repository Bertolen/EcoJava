
public class Herbe extends EtreBio {
	
	public Herbe() {
		super();
	}

	public Herbe(int _x, int _y) {
		super(_x,_y);
	}

	@Override
	protected void Tick() {
		super.Tick();
		
		gaugeRepro += 5.0f;
	}

}
