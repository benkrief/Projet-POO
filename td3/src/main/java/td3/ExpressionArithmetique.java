package td3;

import java.util.Map;

public interface ExpressionArithmetique extends Cloneable {
	public abstract double calculer();
	public abstract ExpressionArithmetique simplifier();
	public abstract ExpressionArithmetique simplifier(Map<ExpressionArithmetique, ExpressionArithmetique> affectations);

	public abstract boolean equals(ExpressionArithmetique ea);
	public abstract String toString();

	public abstract ExpressionArithmetique clone() throws CloneNotSupportedException;
}