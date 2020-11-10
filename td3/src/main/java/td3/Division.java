package td3;

public class Division extends OperationBinaire {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(1);
	private static final ExpressionArithmetique VALEUR_REMARQUABLE = new ConstanteEntiere(0);

	public Division(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public ExpressionArithmetique getNeutralElement() {
		return Division.ELEMENT_NEUTRE;
	}

	@Override
	public double calculer() {
		return this.left.calculer() / this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		if (droite.equals(Division.VALEUR_REMARQUABLE)) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		return new ConstanteRationnelle(gauche.getNumerateur(), 
				gauche.getDenominateur() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {

		if (gauche.equals(Division.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		if (gauche.equals(Division.ELEMENT_NEUTRE)) {
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

		if (droite.equals(Division.VALEUR_REMARQUABLE)) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		if (gauche.equals(Division.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		return new ConstanteRationnelle(gauche.getEntier(), droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if (droite.equals(Division.VALEUR_REMARQUABLE)) {
			throw new IllegalArgumentException("La valeur du dénominateur doit être différente de 0 !");
		}

		if (gauche.equals(Division.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Division 
				&& ((Division) ea.simplifier()).left.equals(((Division) simplifier()).left) 
				&& ((Division) ea.simplifier()).right.equals(((Division) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " / " + this.right + ")";
	}
}