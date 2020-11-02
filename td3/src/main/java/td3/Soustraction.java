package td3;

public class Soustraction extends OperationBinaire {
	private static final int ELEMENT_NEUTRE = 0;

	public Soustraction(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return this.left.calculer() - this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return new ConstanteRationnelle(
				droite.getEntier() * gauche.getDenominateur() - gauche.getNumerateur(), 
				gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {

		return new ConstanteRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() - droite.getNumerateur() * gauche.getDenominateur(), 
				gauche.getDenominateur() * droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return new ConstanteEntiere(gauche.getEntier() - droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {

		return new ConstanteRationnelle(
				gauche.getEntier() * droite.getDenominateur() - droite.getNumerateur(), 
				droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof Soustraction 
				&& ((Soustraction) ea.simplifier()).left.equals(((Soustraction) simplifier()).left) 
				&& ((Soustraction) ea.simplifier()).right.equals(((Soustraction) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " - " + this.right + ")";
	}

	@Override
	protected boolean estElementNeutre(ExpressionArithmetique ea) {
		return ea instanceof ConstanteEntiere && ((ConstanteEntiere) ea).getEntier() == Soustraction.ELEMENT_NEUTRE;
	}
}