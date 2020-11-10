package td3;

public class Distribution extends OperationUnaire {

	public Distribution(ExpressionArithmetique multi) {
		super(multi);
	}

	@Override
	public double calculer() {
		return this.operande.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique multi) {

		if (multi instanceof Multiplication) {

			ExpressionArithmetique gauche = ((Multiplication) multi).left;
			ExpressionArithmetique droite = ((Multiplication) multi).right;

			if (gauche instanceof Addition && droite instanceof Addition) {

				return new Addition(
						new Addition(
							new Multiplication(((Addition) gauche).left, ((Addition) droite).left),
							new Multiplication(((Addition) gauche).left, ((Addition) droite).right)),
						new Addition(
							new Multiplication(((Addition) gauche).right, ((Addition) droite).left),
							new Multiplication(((Addition) gauche).right, ((Addition) droite).right))
						).simplifier();

			} else if (gauche instanceof Soustraction && droite instanceof Soustraction) {

				return new Soustraction(
						new Soustraction(
							new Multiplication(((Soustraction) gauche).left, ((Soustraction) droite).left),
							new Multiplication(((Soustraction) gauche).left, ((Soustraction) droite).right)),
						new Soustraction(
							new Multiplication(((Soustraction) gauche).right, ((Soustraction) droite).left),
							new Multiplication(((Soustraction) gauche).right, ((Soustraction) droite).right))
						).simplifier();

			} else if (gauche instanceof Addition && droite instanceof Soustraction) {

				return new Addition(
						new Soustraction(
							new Multiplication(((Addition) gauche).left, ((Soustraction) droite).left),
							new Multiplication(((Addition) gauche).left, ((Soustraction) droite).right)),
						new Soustraction(
							new Multiplication(((Addition) gauche).right, ((Soustraction) droite).left),
							new Multiplication(((Addition) gauche).right, ((Soustraction) droite).right))
						).simplifier();

			} else if (gauche instanceof Soustraction && droite instanceof Addition) {

				return new Soustraction(
						new Addition(
							new Multiplication(((Soustraction) gauche).left, ((Addition) droite).left),
							new Multiplication(((Soustraction) gauche).left, ((Addition) droite).right)),
						new Addition(
							new Multiplication(((Soustraction) gauche).right, ((Addition) droite).left),
							new Multiplication(((Soustraction) gauche).right, ((Addition) droite).right))
						).simplifier();

			} else if (gauche instanceof Addition) {

				return new Addition(
						new Multiplication(((Addition) gauche).left, droite),
						new Multiplication(((Addition) gauche).right, droite)
						).simplifier();

			} else if (gauche instanceof Soustraction) {

				return new Soustraction(
						new Multiplication(((Soustraction) gauche).left, droite),
						new Multiplication(((Soustraction) gauche).right, droite)
						).simplifier();

			} else if (droite instanceof Addition) {

				return new Addition(
						new Multiplication(gauche, ((Addition) droite).left),
						new Multiplication(gauche, ((Addition) droite).right)
						).simplifier();

			} else if (droite instanceof Soustraction) {

				return new Soustraction(
						new Multiplication(gauche, ((Soustraction) droite).left),
						new Multiplication(gauche, ((Soustraction) droite).right)
						).simplifier();
			}
		}

		throw new RuntimeException("Il est seulement possible de distribuer une multiplication !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere op) {
		throw new RuntimeException("Impossible de distribuer une constante entière !");
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle op) {
		throw new RuntimeException("Impossible de distribuer une constante rationnelle !");
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Distribution 
				&& ((Distribution) ea.simplifier()).operande.equals(((Distribution) simplifier()).operande);
	}
}