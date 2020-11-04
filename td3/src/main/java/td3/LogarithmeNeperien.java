package td3;

public class LogarithmeNeperien extends OperationUnaire {

	public LogarithmeNeperien(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		return Math.log(this.operande.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {

		if(op.equals(new ConstanteEntiere(0))) {
			throw new IllegalArgumentException("La valeur de la constante entière doit être strictement supérieure à 0 !");
		}

		if(op.equals(new ConstanteEntiere(1))) {
			return new ConstanteEntiere(0);
		}

		return simplifyLn(op.getEntier());
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {

		return new Soustraction(new LogarithmeNeperien(new ConstanteEntiere(op.getNumerateur())).simplifier(), 
				new LogarithmeNeperien(new ConstanteEntiere(op.getDenominateur())).simplifier()).simplifier();
	}

	/**
	 * Simplification du logarithme neperien (cas de la puissance).
	 * exemple : ln(8) = ln(2^3) = 3*ln(2)
	 * 
	 * @param n
	 * @return
	 */
	private ExpressionArithmetique simplifyLn(int n) {

		for(int i = 2; i <= n; i++) {
			int current = n;
			int puissance = 0;

			while(current % i == 0) {
				current /= i;
				puissance++;
			}

			if(current == 1 && puissance > 1) {
				return new Multiplication(new ConstanteEntiere(puissance), new LogarithmeNeperien(new ConstanteEntiere(i)));
			}
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof LogarithmeNeperien 
				&& ((LogarithmeNeperien) ea.simplifier()).operande.equals(((LogarithmeNeperien) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "ln(" + this.operande + ")";
	}
}