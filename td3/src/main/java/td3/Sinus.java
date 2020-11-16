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

		if(op.equals(new Division(new ConstanteSymbolique('π', Math.PI), new ConstanteEntiere(2)))) {
			return new ConstanteEntiere(1);

		} else if(op.equals(new ConstanteSymbolique('π', Math.PI))) {
			return new ConstanteEntiere(0);
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {

		if(op.getEntier() == 0) {
			return op;
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		return new Sinus(op.simplifier());
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Sinus && ((Sinus) ea.simplifier()).operande.equals(((Sinus) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "sin(" + this.operande + ")";
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (Sinus) super.clone();

		((Sinus) c).operande = operande.clone();

		return c;
	}
}