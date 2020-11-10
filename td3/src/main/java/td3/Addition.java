package td3;

public class Addition extends OperationBinaire implements Commutable {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(0);

	public Addition(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public ExpressionArithmetique getNeutralElement() {
		return Addition.ELEMENT_NEUTRE;
	}

	@Override
	public double calculer() {
		return this.left.calculer() + this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		return new ConstanteRationnelle(
				droite.getEntier() * gauche.getDenominateur() + gauche.getNumerateur(), 
				gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {

		return new ConstanteRationnelle(
				gauche.getNumerateur() * droite.getDenominateur() + droite.getNumerateur() * gauche.getDenominateur(), 
				gauche.getDenominateur() * droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {
		return new ConstanteEntiere(gauche.getEntier() + droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		return simplifie(droite, gauche).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof Addition 
				&& ((Addition) ea.simplifier()).left.equals(((Addition) simplifier()).left) 
				&& ((Addition) ea.simplifier()).right.equals(((Addition) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " + " + this.right + ")";
	}
}