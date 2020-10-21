package td3;

public class Soustraction extends OperationBinaire {

	public Soustraction(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return this.left.calculer() - this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {
		return new ConstanteRationnelle(droite.getEntier() * gauche.getDenominateur() - gauche.getNumerateur(), 
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
		return new ConstanteRationnelle(gauche.getEntier() * droite.getDenominateur() - droite.getNumerateur(), 
				droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {
		return this;
	}

	@Override
	public String toString() {
		return "(" + this.left + " - " + this.right + ")";
	}
}