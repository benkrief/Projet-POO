package td3;

import java.util.Map;

public class VariableSymbolique implements ExpressionArithmetique {
	private final char symbole;
	private ExpressionArithmetique ea;

	public VariableSymbolique(char symbole) {
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
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

		if(this.ea != null) {
			return this.ea.simplifier();
		}

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

		if(this.ea != null) {
			return this.ea.toString();
		}

		return Character.toString(this.symbole);
	}
}