package asteroids.part3.programs.Statements;

import asteroids.part3.programs.SourceLocation;

public class breakStatement extends Statement {
	private SourceLocation sourceLocation;

	public breakStatement(SourceLocation sourceLocation){
		setSourceLocation(sourceLocation);
	}

	private void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation(){
		return this.sourceLocation;
	}
}
