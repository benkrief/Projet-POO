package td3;

import java.util.Map;

public class Derivation implements ExpressionArithmetique {
	private ExpressionArithmetique[] coefficients;
	private VariableSymbolique x;

	public Derivation(ExpressionArithmetique[] coeff, VariableSymbolique x) {
		this.coefficients = coeff;
		this.x = x;
	}

	public ExpressionArithmetique[] getCoefficients() {
		return this.coefficients;
	}

	public ExpressionArithmetique getX() {
		return this.x;
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une dérivation !");
	}

	@Override
	public ExpressionArithmetique simplifier() {

		ExpressionArithmetique derive = new ConstanteEntiere(0);

		for (int i = this.coefficients.length-2; i >= 0; i--) {
			derive = new Addition(
					new Multiplication(
						new Multiplication(this.coefficients[i], new ConstanteEntiere(i)), 
						new Puissance(this.x, new ConstanteEntiere(i-1))), 
					derive);
		}

		return derive.simplifier();
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		if (((Derivation) ea).getCoefficients().length == this.coefficients.length) {
			for (int i = 0; i < this.coefficients.length; i++) {
				if (((Derivation) ea).getCoefficients()[i] != this.coefficients[i]) {
					return false;
				}
			}

			return ((Derivation) ea).getX().equals(this.x);
		}

		return false;
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {
		return (Derivation) super.clone();
	}
}