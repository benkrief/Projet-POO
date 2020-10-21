package td3;

public final class ConstanteEntiere implements ExpressionArithmetique {
	private final int entier;

	public ConstanteEntiere(int valeur) {
		this.entier = valeur;
	}

	public int getEntier() {
		return this.entier;
	}

	@Override
	public double calculer() {
		return this.getEntier();
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public String toString() {
		return Integer.toString(this.entier);
	}
}