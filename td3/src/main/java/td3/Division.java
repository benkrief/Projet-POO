package td3;

public class Division extends OperationBinaire {
	private static final int elementNeutre = 1;

	public Division(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return this.left.calculer() / this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {
		if(droite.getEntier() == 0) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		if(droite.getEntier() == Division.elementNeutre) {
			return gauche;
		}

		return new ConstanteRationnelle(gauche.getNumerateur(), 
				gauche.getDenominateur() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		if(gauche.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(gauche.getEntier() == Division.elementNeutre) {
			return new ConstanteRationnelle(droite.getDenominateur(), droite.getNumerateur()).simplifier();
		}

		return new ConstanteRationnelle(gauche.getEntier() * droite.getDenominateur(), 
				droite.getNumerateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {
		return new ConstanteRationnelle(gauche.getNumerateur() * droite.getDenominateur(), 
				gauche.getDenominateur() * droite.getNumerateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {
		if(droite.getEntier() == 0) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		if(gauche.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(droite.getEntier() == Division.elementNeutre) {
			return gauche;
		}

		return new ConstanteRationnelle(gauche.getEntier(), droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Division 
				&& ((Division) ea.simplifier()).left.equals(((Division) this.simplifier()).left) 
				&& ((Division) ea.simplifier()).right.equals(((Division) this.simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " / " + this.right + ")";
	}
}