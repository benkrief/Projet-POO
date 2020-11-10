package td3;

import java.util.Map;

public class VariableSymboliqueIndexee extends VariableSymbolique {
	private ExpressionArithmetique index;

	public VariableSymboliqueIndexee(char symbole, ExpressionArithmetique index) {
		super(symbole);
		this.index = index;
	}

	@Override
	public ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations) {

		return affectations.containsKey(this.index) ? 
				new VariableSymboliqueIndexee(this.getSymbole(), affectations.get(this.index)) : this;
	}
}