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

		if(op instanceof Division 
			&& ((Division) op).left instanceof ConstanteSymbolique 
				&& ((ConstanteSymbolique) ((Division) op).left).getSymbole() == 'π'
			&& ((Division) op).right instanceof ConstanteEntiere 
				&& ((ConstanteEntiere) ((Division) op).right).getEntier() == 2) {

			return new ConstanteEntiere(0);

		} else if(op instanceof ConstanteSymbolique && ((ConstanteSymbolique) op).getSymbole() == 'π') {
			return new ConstanteEntiere(-1);
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {

		if(op.getEntier() == 0) {
			return new ConstanteEntiere(1);
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		return new Cosinus(op.simplifier());
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Cosinus && ((Cosinus) ea.simplifier()).operande.equals(((Cosinus) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "cos(" + this.operande + ")";
	}
}