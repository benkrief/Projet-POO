package td3;

import java.util.Map;

public final class ConstanteRationnelle implements ExpressionArithmetique {
	private static final int elementNeutre = 1;

	private final int numerateur;
	private final int denominateur;

	public ConstanteRationnelle(int num, int denom) {
		this.numerateur = num;
		this.denominateur = denom;
	}

	public int getNumerateur() {
		return this.numerateur;
	}

	public int getDenominateur() {
		return this.denominateur;
	}

	@Override
	public double calculer() {
		return (double)this.numerateur / this.denominateur;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		if(this.denominateur == 0) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		if(this.numerateur == 0) {
			return new ConstanteEntiere(0);
		}

		if(this.numerateur == this.denominateur) {
			return new ConstanteEntiere(1);
		}

		int pgcd = gcd(this.numerateur, this.denominateur);

		if(this.denominateur / pgcd == ConstanteRationnelle.elementNeutre) {
			return new ConstanteEntiere(this.numerateur / pgcd);
		}

		return new ConstanteRationnelle(this.numerateur / pgcd, this.denominateur / pgcd);
	}

	/**
	 * Calucler le pgcd de deux entiers.
	 * 
	 * @param a
	 * @param b
	 * @return pgcd
	 */
	private static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof ConstanteRationnelle 
				&& ((ConstanteRationnelle) ea.simplifier()).getNumerateur() == ((ConstanteRationnelle) this.simplifier()).numerateur 
				&& ((ConstanteRationnelle) ea.simplifier()).getDenominateur() == ((ConstanteRationnelle) this.simplifier()).denominateur;
	}

	@Override
	public String toString() {
		return "(" + this.numerateur + "/" + this.denominateur + ")";
	}
}