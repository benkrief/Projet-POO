package td3;

import java.util.Map;

public class VariableSymbolique implements ExpressionArithmetique {
	protected final char symbole;

	public VariableSymbolique(char symbole) {
		this.symbole = symbole;
	}

	public char getSymbole() {
		return this.symbole;
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une variable symbolique !");
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		for(ExpressionArithmetique key : affectations.keySet()) {
			if(key.equals(this)) {
				return affectations.get(key);
			}
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof VariableSymbolique && ((VariableSymbolique) ea).getSymbole() == this.symbole;
	}

	@Override
	public String toString() {
		return Character.toString(this.symbole);
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {
		return (VariableSymbolique) super.clone();
	}
}