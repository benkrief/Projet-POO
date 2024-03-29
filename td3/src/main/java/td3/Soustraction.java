package td3;

public class Soustraction extends OperationBinaire {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(0);

	public Soustraction(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public ExpressionArithmetique getNeutralElement() {
		return Soustraction.ELEMENT_NEUTRE;
	}

	@Override
	public double calculer() {
		return this.left.calculer() - this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

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
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (Soustraction) super.clone();

		((Soustraction) c).left = left.clone();
		((Soustraction) c).right = right.clone();

		return c;
	}
}