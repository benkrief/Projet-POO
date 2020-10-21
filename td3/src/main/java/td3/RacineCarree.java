package td3;

public class RacineCarree extends OperationUnaire {
	public RacineCarree(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		return Math.sqrt(this.operande.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {
		int n = op.getEntier();

		if(n == 0 || n == 1) {
			return new ConstanteEntiere(n);
		}

		int entierHorsRacine = 1;
		int entierSousRacine = 1;

		for(int i = 2; i <= n; i ++) {

			if(estPremier(i)) {

				int nbDivisions = 0;

				while(n % i == 0) {
					n /= i;
					nbDivisions++;
				}

				for(int j = 0; j < nbDivisions/2; j++) {
					entierHorsRacine *= i;
				}

				if(nbDivisions % 2 == 1) {
					entierSousRacine *= i;
				}
			}
		}

		return new Multiplication(new ConstanteEntiere(entierHorsRacine), 
				new RacineCarree(new ConstanteEntiere(entierSousRacine)));
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {

		return new Division(new RacineCarree(new ConstanteEntiere(op.getNumerateur())).simplifier(), 
				new RacineCarree(new ConstanteEntiere(op.getDenominateur())).simplifier());
	}

	/**
	 * Savoir si un entier est premier.
	 * 
	 * @param n
	 * @return boolean
	 */
	private boolean estPremier(int n) {
		for(int i = 2; i < n; i++) {
			if(n % i == 0) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return "sqrt(" + this.operande + ")";
	}
}