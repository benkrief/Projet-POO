package td3;

public class ConstanteSymbolique implements ExpressionArithmetique {
	private final char symbole;
	private final double valeur;

	public ConstanteSymbolique(char symbole, double v) {
		this.symbole = symbole;
		this.valeur = v;
	}

	public char getSymbole() {
		return this.symbole;
	}

	public double getValeur() {
		return this.valeur;
	}

	@Override
	public double calculer() {
		return this.valeur;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public String toString() {
		return "" + this.symbole;
	}
}