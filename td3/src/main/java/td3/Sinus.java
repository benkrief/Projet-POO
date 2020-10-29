package td3;

public class Sinus extends OperationUnaire {

	public Sinus(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		return Math.sin(this.operande.calculer());
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
		return new Sinus(op.simplifier());
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Sinus 
				&& ((Sinus) ea.simplifier()).operande.equals(((Sinus) this.simplifier()).operande);
	}

	@Override
	public String toString() {
		return "sin(" + this.operande + ")";
	}
}