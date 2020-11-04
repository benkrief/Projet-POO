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

		if(op.equals(new ConstanteEntiere(0)) || op.equals(new ConstanteEntiere(1))) {
			return op;
		}

		return simplifySqrt(op.getEntier());
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {

		return new Division(new RacineCarree(new ConstanteEntiere(op.getNumerateur())).simplifier(), 
				new RacineCarree(new ConstanteEntiere(op.getDenominateur())).simplifier()).simplifier();
	}

	/**
	 * Simplification de la racine carrée (cas du nombre qui se compose d'un carré parfait).
	 * exemple : sqrt(12) = sqrt(3*4) = 2*sqrt(3)
	 * 
	 * @param n
	 * @return
	 */
	private ExpressionArithmetique simplifySqrt(int n) {

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

		if(entierSousRacine == 1) {
			return new ConstanteEntiere(entierHorsRacine);
		}

		if(entierHorsRacine == 1) {
			return new RacineCarree(new ConstanteEntiere(entierSousRacine));
		}

		return new Multiplication(new ConstanteEntiere(entierHorsRacine), 
				new RacineCarree(new ConstanteEntiere(entierSousRacine))).simplifier();
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
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof RacineCarree 
				&& ((RacineCarree) ea.simplifier()).operande.equals(((RacineCarree) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "sqrt(" + this.operande + ")";
	}
}