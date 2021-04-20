package td3;

import java.util.HashMap;
import java.util.Map;

public class BigSomme implements ExpressionArithmetique {
	private ExpressionArithmetique ea;
	private VariableSymbolique index;
	private ConstanteEntiere inf;
	private ConstanteEntiere sup;

	public BigSomme(ExpressionArithmetique ea, VariableSymbolique index, ConstanteEntiere inf, ConstanteEntiere sup) {
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
		throw new RuntimeException("Impossible de calculer une BigSomme !");
	}

	@Override
	public ExpressionArithmetique simplifier() {

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();

		ExpressionArithmetique bigSomme = new ConstanteEntiere(0);

		try {
			for (int i = this.inf.getEntier(); i <= this.sup.getEntier(); i++) {
				affectations.put(this.index, new ConstanteEntiere(i));
				bigSomme = new Addition(bigSomme, this.ea.clone().simplifier(affectations));
			}
		} catch(CloneNotSupportedException e) {
			throw new RuntimeException("Echec du clonage : impossible de simplifier BigSomme !");
		}

		return bigSomme.simplifier();
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {

		return ea instanceof BigSomme 
				&& ((BigSomme) ea).getExpression().equals(((BigSomme) simplifier()).ea)
				&& ((BigSomme) ea).getIndex().equals(((BigSomme) simplifier()).index)
				&& ((BigSomme) ea).getInf().equals(((BigSomme) simplifier()).inf)
				&& ((BigSomme) ea).getSup().equals(((BigSomme) simplifier()).sup);
	}

	@Override
	public String toString() {
		return this.sup + "Σ" + this.index.toString() + "=" + this.inf + " " + this.ea.toString();
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (BigSomme) super.clone();

		((BigSomme) c).ea = ea.clone();
		((BigSomme) c).index = (VariableSymbolique) index.clone();
		((BigSomme) c).inf = (ConstanteEntiere) inf.clone();
		((BigSomme) c).sup = (ConstanteEntiere) sup.clone();

		return c;
	}
}