package td3;

public class Puissance extends OperationBinaire {
	public Puissance(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return Math.pow(this.left.calculer(), this.right.calculer());
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {
		return new ConstanteRationnelle((int) Math.pow(gauche.getNumerateur(), droite.getEntier()), 
				(int) Math.pow(gauche.getDenominateur(), droite.getEntier())).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {
		return this;
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {
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
		if(ea instanceof Puissance) {
			if(((Puissance) ea).left.equals(this.left)
					&& ((Puissance) ea).right.equals(this.right)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "(" + this.left + "^" + this.right + ")";
	}
}