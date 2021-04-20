package td3;

public class DerivationPolynome extends OperationUnaire {
	private ConstanteEntiere ordre;

	public DerivationPolynome(ExpressionArithmetique op, ConstanteEntiere ordre) {
		super(op);
		this.ordre = ordre;
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une dérivation !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {

		if(op instanceof Polynome) {

			ExpressionArithmetique[] coeff = ((Polynome) op).getCoefficients();

			ExpressionArithmetique derivee = new ConstanteEntiere(0);

			int currCoeff;
			int degre;

			for (int i = 0; i < coeff.length; i++) {

				currCoeff = ((ConstanteEntiere) coeff[i]).getEntier();
				degre = coeff.length-1 - i;

				for (int j = 1; j <= this.ordre.getEntier(); j++) {
					currCoeff *= degre;
					degre--;
				}

				derivee = new Addition(derivee, new Multiplication(
						new ConstanteEntiere(currCoeff), 
						new Puissance(((Polynome) op).getX(), new ConstanteEntiere(degre))));
			}

			return derivee.simplifier();
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
		return ea instanceof DerivationPolynome 
				&& ((DerivationPolynome) ea.simplifier()).operande.equals(((DerivationPolynome) simplifier()).operande)
				&& ((DerivationPolynome) ea.simplifier()).ordre.equals(((DerivationPolynome) simplifier()).ordre);
	}

	@Override
	public String toString() {
		return "dériver" + this.ordre + "(" + this.operande + ")";
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (DerivationPolynome) super.clone();

		((DerivationPolynome) c).operande = operande.clone();
		((DerivationPolynome) c).ordre = (ConstanteEntiere) ordre.clone();

		return c;
	}
}