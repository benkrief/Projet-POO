package td3;

public interface ExpressionArithmetique {
	public static final ExpressionArithmetique PI = new ConstanteSymbolique('π', Math.PI);
	public static final ExpressionArithmetique E = new ConstanteSymbolique('e', Math.E);

	public abstract ExpressionArithmetique simplifier();
	public abstract double calculer();
}