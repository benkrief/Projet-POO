package td3;

public class Multiplication extends OperationBinaire {
	private static final int elementNeutre = 1;

	public Multiplication(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return this.left.calculer() * this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {
		if(droite.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(droite.getEntier() == Multiplication.elementNeutre) {
			return gauche;
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
		if(gauche.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(droite.getEntier() == 0) {
			return new ConstanteEntiere(0);
		}

		if(gauche.getEntier() == Multiplication.elementNeutre) {
			return droite;
		}

		if(droite.getEntier() == Multiplication.elementNeutre) {
			return gauche;
		}

		return new ConstanteEntiere(gauche.getEntier() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		return this.simplifie(droite, gauche).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Multiplication 
				&& ((Multiplication) ea.simplifier()).left.equals(((Multiplication) this.simplifier()).left) 
				&& ((Multiplication) ea.simplifier()).right.equals(((Multiplication) this.simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " * " + this.right + ")";
	}
}