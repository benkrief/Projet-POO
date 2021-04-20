package td3;

import java.util.Collections;
import java.util.Map;

public class Polynome implements ExpressionArithmetique {
	private ExpressionArithmetique[] coefficients;
	private VariableSymbolique x;

	public Polynome(ExpressionArithmetique[] coeff, VariableSymbolique x) {
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
		throw new RuntimeException("Impossible de calculer un polynôme !");
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

		if (((Polynome) ea).getCoefficients().length == this.coefficients.length) {
			for (int i = 0; i < this.coefficients.length; i++) {
				if (((Polynome) ea).getCoefficients()[i] != this.coefficients[i]) {
					return false;
				}
			}

			return ((Polynome) ea).getX().equals(this.x);
		}

		return false;
	}

	@Override
	public String toString() {
		return evaluer(Collections.<ExpressionArithmetique, ExpressionArithmetique>emptyMap()).toString();
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {
		return (Polynome) super.clone();
	}

	public ExpressionArithmetique evaluer(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		ExpressionArithmetique res = new ConstanteEntiere(0);

		for (int i = 0; i < this.coefficients.length; i++) {
			res = new Addition(res, new Multiplication(
						this.coefficients[i], 
						new Puissance(this.x.simplifier(affectations), new ConstanteEntiere((this.coefficients.length-1) - i))));
		}

		return res.simplifier();
	}
}