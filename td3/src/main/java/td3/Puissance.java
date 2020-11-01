package td3;

public class Puissance extends OperationBinaire {
	private static final int elementNeutre = 1;

	public Puissance(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return Math.pow(this.left.calculer(), this.right.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {
		if(droite.getEntier() == 0) {
			return new ConstanteEntiere(1);
		}

		if(droite.getEntier() == Puissance.elementNeutre) {
			return gauche;
		}

		return new ConstanteRationnelle((int) Math.pow(gauche.getNumerateur(), droite.getEntier()), 
				(int) Math.pow(gauche.getDenominateur(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {
		if(gauche.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(droite.getEntier() == 0) {
			return new ConstanteEntiere(1);
		}

		if(droite.getEntier() == Puissance.elementNeutre) {
			return gauche;
		}

		return new ConstanteEntiere((int) Math.pow(gauche.getEntier(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Puissance 
				&& ((Puissance) ea.simplifier()).left.equals(((Puissance) this.simplifier()).left) 
				&& ((Puissance) ea.simplifier()).right.equals(((Puissance) this.simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + "^" + this.right + ")";
	}
}