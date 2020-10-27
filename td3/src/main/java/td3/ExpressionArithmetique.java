package td3;

import java.util.Map;

public interface ExpressionArithmetique {
	public static final ExpressionArithmetique PI = new ConstanteSymbolique('π', Math.PI);
	public static final ExpressionArithmetique E = new ConstanteSymbolique('e', Math.E);

	public abstract double calculer();
	public abstract ExpressionArithmetique simplifier();
	public abstract ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations);
	
	public abstract boolean equals(ExpressionArithmetique ea);
	public abstract String toString();
}