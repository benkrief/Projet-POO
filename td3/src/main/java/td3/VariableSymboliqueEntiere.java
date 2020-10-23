package td3;

public class VariableSymboliqueEntiere implements ExpressionArithmetique {
	private final char symbole;
	private ConstanteEntiere ce;

	public VariableSymboliqueEntiere(char symbole) {
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
	}

	public ConstanteEntiere getConstanteEntiere() {
		return this.ce;
	}

	public void setConstanteEntiere(ConstanteEntiere ce) {
		this.ce = ce;
	}

	@Override
	public double calculer() {
		return this.ce.calculer();
	}

	@Override
	public ExpressionArithmetique simplifier() {
		this.ce = (ConstanteEntiere) this.ce.simplifier();
		return this;
	}

	@Override
	public String toString() {
		return "" + this.symbole;
	}
}