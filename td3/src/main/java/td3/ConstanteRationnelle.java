package td3;

import java.util.Map;

public final class ConstanteRationnelle implements ExpressionArithmetique {
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
		int pgcd = gcd(this.numerateur, this.denominateur);
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
		if(ea instanceof ConstanteRationnelle) {
			if(((ConstanteRationnelle) ea).getNumerateur() == this.numerateur
					&& ((ConstanteRationnelle) ea).getDenominateur() == this.denominateur) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "(" + this.numerateur + "/" + this.denominateur + ")";
	}
}