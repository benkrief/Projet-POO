package td3;

import java.util.Map;

public abstract class OperationBinaire implements ExpressionArithmetique {
	protected ExpressionArithmetique left;
	protected ExpressionArithmetique right;

	public OperationBinaire(ExpressionArithmetique left, ExpressionArithmetique right) {
		this.left = left;
		this.right = right;
	}

	protected abstract ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite);

	@Override
	public ExpressionArithmetique simplifier() {

		ExpressionArithmetique res;

		this.left = this.left.simplifier();
		this.right = this.right.simplifier();

		if (this.left instanceof ConstanteEntiere && this.right instanceof ConstanteEntiere) {
			ConstanteEntiere gauche = (ConstanteEntiere) this.left;
			ConstanteEntiere droite = (ConstanteEntiere) this.right;

			res = simplifie(gauche, droite);

		} else if (this.left instanceof ConstanteRationnelle && this.right instanceof ConstanteRationnelle) {
			ConstanteRationnelle gauche = (ConstanteRationnelle) this.left;
			ConstanteRationnelle droite = (ConstanteRationnelle) this.right;

			res = simplifie(gauche, droite);

		} else if (this.left instanceof ConstanteRationnelle && this.right instanceof ConstanteEntiere) {
			ConstanteRationnelle gauche = (ConstanteRationnelle) this.left;
			ConstanteEntiere droite = (ConstanteEntiere) this.right;

			res = simplifie(gauche, droite);

		} else if (this.left instanceof ConstanteEntiere && this.right instanceof ConstanteRationnelle) {
			ConstanteEntiere gauche = (ConstanteEntiere) this.left;
			ConstanteRationnelle droite = (ConstanteRationnelle) this.right;

			res = simplifie(gauche, droite);

		} else {
			res = this;
		}

		return res;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		this.left = this.left.simplifier();
		this.right = this.right.simplifier();

		for(Map.Entry<ExpressionArithmetique, ExpressionArithmetique> e : affectations.entrySet()){

			if(this.left.equals(e.getKey())) {
				this.left = e.getValue();

			} else if(this.right.equals(e.getKey())) {
				this.right = e.getValue();
			}
		}

		return this;
	}
}