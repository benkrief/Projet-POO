package td3;

public class Association extends OperationUnaire {

	public Association(ExpressionArithmetique op) {
		super(op);
	}

	@Override
	public double calculer() {
		throw new RuntimeException("Impossible de calculer une association !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique op) {

		if (op instanceof Addition) {

			ExpressionArithmetique gauche = ((Addition) op).left;
			ExpressionArithmetique droite = ((Addition) op).right;

			if (gauche instanceof Addition) {

				return new Addition(((Addition) gauche).left, 
					new Addition(((Addition) gauche).right, droite)).simplifier();

			} else if (droite instanceof Addition) {

				return new Addition(new Addition(gauche, ((Addition) droite).left), 
					((Addition) droite).right).simplifier();
			}

		} else if (op instanceof Multiplication) {

			ExpressionArithmetique gauche = ((Multiplication) op).left;
			ExpressionArithmetique droite = ((Multiplication) op).right;

			if (gauche instanceof Multiplication) {

				return new Multiplication(((Multiplication) gauche).left, 
					new Multiplication(((Multiplication) gauche).right, droite)).simplifier();

			} else if (droite instanceof Multiplication) {

				return new Multiplication(new Multiplication(gauche, ((Multiplication) droite).left), 
					((Multiplication) droite).right).simplifier();
			}
		}

		throw new RuntimeException("Il est seulement possible d'associer une addition ou une multiplication !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {
		throw new RuntimeException("Impossible d'associer une constante entière !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		throw new RuntimeException("Impossible d'associer une constante rationnelle !");
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Association 
				&& ((Association) ea.simplifier()).operande.equals(((Association) simplifier()).operande);
	}

	@Override
	public String toString() {
		return "associer(" + this.operande + ")";
	}

	@Override
	public ExpressionArithmetique clone() throws CloneNotSupportedException {

		ExpressionArithmetique c = (Association) super.clone();

		((Association) c).operande = operande.clone();

		return c;
	}
}