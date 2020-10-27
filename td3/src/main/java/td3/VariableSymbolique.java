package td3;

import java.util.Map;

public class VariableSymbolique implements ExpressionArithmetique {
	private ExpressionArithmetique ea;

	public VariableSymbolique(ExpressionArithmetique ea) {
		this.ea = ea;
	}

	public ExpressionArithmetique getConstante() {
		return this.ea;
	}

	@Override
	public double calculer() {
		return this.ea.calculer();
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this.ea.simplifier();
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		if(ea instanceof VariableSymbolique) {
			if(((VariableSymbolique) ea).getConstante() == this.ea) {
				return true;
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return this.ea.toString();
	}
}