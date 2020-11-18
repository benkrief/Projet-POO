package td3;

public class Derivation extends OperationUnaire {

	public Derivation(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une dérivation !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {

		if(op instanceof Polynome) {

			ExpressionArithmetique[] coeff = ((Polynome) op).getCoefficients();

			ExpressionArithmetique derive = new ConstanteEntiere(0);

			for (int i = 0; i < coeff.length; i++) {
				derive = new Addition(derive, new Multiplication(
						new Multiplication(coeff[i], new ConstanteEntiere((coeff.length-1) - i)), 
						new Puissance(((Polynome) op).getX(), new ConstanteEntiere((coeff.length-2) - i))));
			}

			return derive.simplifier();
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {
		throw new RuntimeException("Impossible de dériver une constante entière !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		throw new RuntimeException("Impossible de dériver une constante rationnelle !");
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Derivation 
				&& ((Derivation) ea.simplifier()).operande.equals(((Derivation) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "dériver(" + this.operande + ")";
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (Derivation) super.clone();

		((Derivation) c).operande = operande.clone();

		return c;
	}
}