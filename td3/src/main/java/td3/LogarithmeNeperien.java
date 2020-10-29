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
		int n = op.getEntier();

		if(n == 0) {
			throw new IllegalArgumentException("La valeur de la constante entière doit être strictement supérieure à 0 !");
		}

		if(n == 1) {
			return new ConstanteEntiere(0);
		}

		for(int i = 2; i <= n; i++) {
			int tmp = n;
			int puissance = 0;

			while(tmp % i == 0) {
				tmp /= i;
				puissance++;
			}

			if(tmp == 1) {
				return new Multiplication(new ConstanteEntiere(puissance), new LogarithmeNeperien(new ConstanteEntiere(i)));
			}
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		int num = ((ConstanteRationnelle) op.simplifier()).getNumerateur();
		int denom = ((ConstanteRationnelle) op.simplifier()).getDenominateur();

		return new Soustraction(new LogarithmeNeperien(new ConstanteEntiere(num)), 
				new LogarithmeNeperien(new ConstanteEntiere(denom)));
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof LogarithmeNeperien 
				&& ((LogarithmeNeperien) ea.simplifier()).operande.equals(((LogarithmeNeperien) this.simplifier()).operande);
	}

	@Override
	public String toString() {
		return "ln(" + this.operande + ")";
	}
}