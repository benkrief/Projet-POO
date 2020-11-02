package td3;

import java.util.Map;

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
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof ConstanteSymbolique && ((ConstanteSymbolique) ea).getSymbole() == this.symbole;
	}

	@Override
	public String toString() {
		return Character.toString(this.symbole);
	}
}