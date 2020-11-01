package td3;

import java.util.Collections;
import java.util.Map;

public abstract class OperationUnaire implements ExpressionArithmetique {
	protected ExpressionArithmetique operande;

	public OperationUnaire(ExpressionArithmetique op) {
		this.operande = op;
	}

	protected abstract ExpressionArithmetique simplifie(ExpressionArithmetique op);

	protected abstract ExpressionArithmetique simplifie(ConstanteEntiere op);

	protected abstract ExpressionArithmetique simplifie(ConstanteRationnelle op);

	@Override
	public ExpressionArithmetique simplifier() {
		return this.simplifier(Collections.<ExpressionArithmetique, ExpressionArithmetique>emptyMap());
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		ExpressionArithmetique res;

		this.operande = this.operande.simplifier(affectations);

		if(this.operande instanceof ConstanteEntiere) {
			res = simplifie((ConstanteEntiere) this.operande);

		} else if(this.operande instanceof ConstanteRationnelle) {
			res = simplifie((ConstanteRationnelle) this.operande);

		} else {
			res = this;
		}

		return res;
	}
}