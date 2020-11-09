package td3;

import java.util.Map;

public class VariableSymbolique implements ExpressionArithmetique {
	private final char symbole;

	public VariableSymbolique(char symbole) {
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
	}

	@Override
	public double calculer() {
		return 0.0;
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {
		return affectations.containsKey(this) ? affectations.get(this) : this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof VariableSymbolique && ((VariableSymbolique) ea).getSymbole() == this.symbole;
	}

	@Override
	public String toString() {
		return Character.toString(this.symbole);
	}
}