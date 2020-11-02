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

		if(op instanceof Division 
			&& ((Division) op).left instanceof ConstanteSymbolique 
				&& ((ConstanteSymbolique) ((Division) op).left).getSymbole() == 'π'
			&& ((Division) op).right instanceof ConstanteEntiere 
				&& ((ConstanteEntiere) ((Division) op).right).getEntier() == 2) {

			return new ConstanteEntiere(1);

		} else if(op instanceof ConstanteSymbolique && ((ConstanteSymbolique) op).getSymbole() == 'π') {
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
}