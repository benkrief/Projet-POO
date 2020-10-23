package td3;

public interface ExpressionArithmetique {
	public static final ExpressionArithmetique PI = new ConstanteSymbolique('π', 3.1415);
	public static final ExpressionArithmetique E = new ConstanteSymbolique('e', 2.7182);

	public abstract ExpressionArithmetique simplifier();
	public abstract double calculer();
}