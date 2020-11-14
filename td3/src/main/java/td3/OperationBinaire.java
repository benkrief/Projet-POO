package td3;

import java.util.Collections;
import java.util.Map;

public abstract class OperationBinaire implements ExpressionArithmetique {
	protected ExpressionArithmetique left;
	protected ExpressionArithmetique right;

	public OperationBinaire(ExpressionArithmetique left, ExpressionArithmetique right) {
		this.left = left;
		this.right = right;
	}

	protected abstract ExpressionArithmetique getNeutralElement();

	protected abstract ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite);

	protected abstract ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite);

	protected abstract ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite);

	@Override
	public ExpressionArithmetique simplifier() {
		return simplifier(Collections.<ExpressionArithmetique, ExpressionArithmetique>emptyMap());
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		ExpressionArithmetique res;

		this.left = this.left.simplifier(affectations);
		this.right = this.right.simplifier(affectations);

		if (this.right.equals(this.getNeutralElement())) {
			return this.left;

		} else if (this instanceof Commutable && this.left.equals(this.getNeutralElement())) {
			return this.right;
		}

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

			res = simplifie(this.left, this.right);
		}

		return res;
	}
}