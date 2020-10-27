package td3;

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

		ExpressionArithmetique res;

		this.operande = this.operande.simplifier();

		if(this.operande instanceof ConstanteEntiere) {
			res = simplifie((ConstanteEntiere) this.operande);

		} else if(this.operande instanceof ConstanteRationnelle) {
			res = simplifie((ConstanteRationnelle) this.operande);

		} else {
			res = this;
		}

		return res;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		this.operande = this.operande.simplifier();

		for(Map.Entry<ExpressionArithmetique, ExpressionArithmetique> e : affectations.entrySet()){

			if(this.operande.equals(e.getKey())) {
				this.operande = e.getValue();
			}
		}

		return this;
	}
}