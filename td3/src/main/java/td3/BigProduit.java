package td3;

import java.util.HashMap;
import java.util.Map;

public class BigProduit implements ExpressionArithmetique {
	private ExpressionArithmetique ea;
	private VariableSymbolique index;
	private ConstanteEntiere inf;
	private ConstanteEntiere sup;

	public BigProduit(ExpressionArithmetique ea, VariableSymbolique index, ConstanteEntiere inf, ConstanteEntiere sup) {
		this.ea = ea;
		this.index = index;
		this.inf = inf;
		this.sup = sup;
	}

	public ExpressionArithmetique getExpression() {
		return this.ea;
	}

	public VariableSymbolique getIndex() {
		return this.index;
	}

	public ConstanteEntiere getInf() {
		return this.inf;
	}

	public ConstanteEntiere getSup() {
		return this.sup;
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer un BigProduit !");
	}

	@Override
	public ExpressionArithmetique simplifier() {

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();

		ExpressionArithmetique bigProduit = new ConstanteEntiere(1);

		try {
			for (int i = this.inf.getEntier(); i <= this.sup.getEntier(); i++) {
				affectations.put(this.index, new ConstanteEntiere(i));
				bigProduit = new Multiplication(bigProduit, this.ea.clone().simplifier(affectations));
			}
		} catch(CloneNotSupportedException e) {
			throw new RuntimeException("Echec du clonage : impossible de simplifier BigProduit !");
		}

		return bigProduit.simplifier();
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof BigProduit 
				&& ((BigProduit) ea).getExpression().equals(((BigProduit) simplifier()).ea)
				&& ((BigProduit) ea).getIndex().equals(((BigProduit) simplifier()).index)
				&& ((BigProduit) ea).getInf().equals(((BigProduit) simplifier()).inf)
				&& ((BigProduit) ea).getSup().equals(((BigProduit) simplifier()).sup);
	}

	@Override
	public String toString() {
		return this.sup + "Π" + this.index.toString() + "=" + this.inf + " " + this.ea.toString();
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (BigProduit) super.clone();

		((BigProduit) c).ea = ea.clone();
		((BigProduit) c).index = (VariableSymbolique) index.clone();
		((BigProduit) c).inf = (ConstanteEntiere) inf.clone();
		((BigProduit) c).sup = (ConstanteEntiere) sup.clone();

		return c;
	}
}