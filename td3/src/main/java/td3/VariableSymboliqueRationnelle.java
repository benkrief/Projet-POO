package td3;

public class VariableSymboliqueRationnelle implements ExpressionArithmetique {
	private final char symbole;
	private ConstanteRationnelle cr;

	public VariableSymboliqueRationnelle(char symbole) {
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
	}

	public ConstanteRationnelle getConstanteRationnelle() {
		return this.cr;
	}

	public void setConstanteRationnelle(ConstanteRationnelle cr) {
		this.cr = cr;
	}

	public double calculer() {
		return this.cr.calculer();
	}

	public ExpressionArithmetique simplifier() {
		this.cr = (ConstanteRationnelle) this.cr.simplifier();
		return this;
	}

	@Override
	public String toString() {
		return "" + this.symbole;
	}
}