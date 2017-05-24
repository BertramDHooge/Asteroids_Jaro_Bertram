package asteroids.part3.programs;
import java.util.List;
import asteroids.part3.programs.Statements.Statement;

public class Program {
	private List<Function> functions;
	private Statement main;

	
	public Program(List<Function> functions, Statement main) {
		setMain(main);
		setFunctions(functions);
		main.setProgram(this);
		for (Function function: functions) function.setProgram(this);
	}


	private void setFunctions(List<Function> functions) {
		this.functions = functions;
		
	}


	private void setMain(Statement main) {
		this.main = main;
		
	}
	
}