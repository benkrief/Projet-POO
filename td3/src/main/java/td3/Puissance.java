package td3;

public class Puissance extends OperationBinaire {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(1);
	private static final ExpressionArithmetique VALEUR_REMARQUABLE = new ConstanteEntiere(0);

	public Puissance(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public ExpressionArithmetique getNeutralElement() {
		return Puissance.ELEMENT_NEUTRE;
	}

	@Override
	public double calculer() {
		return Math.pow(this.left.calculer(), this.right.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		if (droite.equals(Puissance.VALEUR_REMARQUABLE)) {
			return new ConstanteEntiere(1);
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

		if (gauche.equals(Puissance.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		if (gauche.equals(Puissance.ELEMENT_NEUTRE) || droite.equals(Puissance.VALEUR_REMARQUABLE)) {
			return new ConstanteEntiere(1);
		}

		return new ConstanteEntiere((int) Math.pow(gauche.getEntier(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {

		if (gauche.equals(Puissance.VALEUR_REMARQUABLE)) {
			return new ConstanteEntiere(0);
		}

		if (gauche.equals(Puissance.ELEMENT_NEUTRE)) {
			return new ConstanteEntiere(1);
		}

		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if (gauche.equals(Puissance.VALEUR_REMARQUABLE)) {
			return gauche;
		}

		if (gauche.equals(Puissance.ELEMENT_NEUTRE) || droite.equals(Puissance.VALEUR_REMARQUABLE)) {
			return new ConstanteEntiere(1);
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof Puissance 
				&& ((Puissance) ea.simplifier()).left.equals(((Puissance) simplifier()).left) 
				&& ((Puissance) ea.simplifier()).right.equals(((Puissance) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + "^" + this.right + ")";
	}
}