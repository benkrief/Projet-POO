package td3;

import java.util.Map;

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
		return this.entier;
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
		return ea instanceof ConstanteEntiere && ((ConstanteEntiere) ea).getEntier() == this.entier;
	}

	@Override
	public String toString() {
		return Integer.toString(this.entier);
	}
}