package td3;

public class Cosinus extends OperationUnaire {
	public Cosinus(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		return Math.cos(this.operande.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {
		int n = op.getEntier();
		int nbSoustractions = (int) (n / (2 * Math.PI));

		// on soustrait nbSoustractions fois 2π
		n -= nbSoustractions * 2 * Math.PI;

		return new Cosinus(new ConstanteEntiere(n));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		return new Cosinus(op.simplifier());
	}

	@Override
	public String toString() {
		return "cos(" + this.operande + ")";
	}
}