package td3;

public class Multiplication extends OperationBinaire implements Commutable {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(1);
	private static final ExpressionArithmetique VALEUR_REMARQUABLE = new ConstanteEntiere(0);

	public Multiplication(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public ExpressionArithmetique getNeutralElement() {
		return Multiplication.ELEMENT_NEUTRE;
	}

	@Override
	public double calculer() {
		return this.left.calculer() * this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		if (droite.equals(Multiplication.VALEUR_REMARQUABLE)) {
			return droite;
		}

		return new ConstanteRationnelle(droite.getEntier() * gauche.getNumerateur(), 
				gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {

		return new ConstanteRationnelle(gauche.getNumerateur() * droite.getNumerateur(), 
				gauche.getDenominateur() * droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {

		if (gauche.equals(Multiplication.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		if (droite.equals(Multiplication.VALEUR_REMARQUABLE)) {
			return droite;
		}

		return new ConstanteEntiere(gauche.getEntier() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		return simplifie(droite, gauche).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if (gauche.equals(Multiplication.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		if (droite.equals(Multiplication.VALEUR_REMARQUABLE)) {
			return droite;
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof Multiplication 
				&& ((Multiplication) ea.simplifier()).left.equals(((Multiplication) simplifier()).left) 
				&& ((Multiplication) ea.simplifier()).right.equals(((Multiplication) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " * " + this.right + ")";
	}
}