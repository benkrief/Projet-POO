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
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		return new Cosinus(op.simplifier());
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Cosinus 
				&& ((Cosinus) ea.simplifier()).operande.equals(((Cosinus) this.simplifier()).operande);
	}

	@Override
	public String toString() {
		return "cos(" + this.operande + ")";
	}
}