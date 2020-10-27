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
		if(ea instanceof Cosinus) {
			if(((Cosinus) ea).operande.equals(this.operande)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "cos(" + this.operande + ")";
	}
}