package be.occam.zoncolan.heat.domain.people;

public abstract class HeatMan {
	
	public abstract boolean goTo( Float temperature );
	
	public abstract Float feel( Actor actor );
	
	public abstract Float readTarget( Actor actor );
	
	public abstract void turnOff( Actor actor );

}
