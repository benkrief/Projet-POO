package td3;

import java.util.Map;

public class VariableSymboliqueIndexee extends VariableSymbolique {
	private ExpressionArithmetique index;

	public VariableSymboliqueIndexee(char symbole, ExpressionArithmetique index) {
		super(symbole);
		this.index = index;
	}

	public ExpressionArithmetique getIndex() {
		return this.index;
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une variable symbolique indexée !");
	}

	@Override
	public ExpressionArithmetique simplifier() {
		return this;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		for(ExpressionArithmetique key : affectations.keySet()) {
			if(key.equals(this.index)) {
				return new VariableSymboliqueIndexee(this.symbole, affectations.get(this.index));
			}
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof VariableSymboliqueIndexee 
				&& ((VariableSymboliqueIndexee) ea).getSymbole() == this.symbole
				&& ((VariableSymboliqueIndexee) ea).getIndex().equals(this.index);
	}

	@Override
	public String toString() {
		return this.symbole + this.index.toString();
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {
		return (VariableSymboliqueIndexee) super.clone();
	}
}