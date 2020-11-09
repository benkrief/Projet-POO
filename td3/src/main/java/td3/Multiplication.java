package td3;

public class Multiplication extends OperationBinaire {
	private static final ExpressionArithmetique ELEMENT_NEUTRE = new ConstanteEntiere(1);
	private static final ExpressionArithmetique VALEUR_REMARQUABLE = new ConstanteEntiere(0);

	public Multiplication(ExpressionArithmetique left, ExpressionArithmetique right) {
		super(left, right);
	}

	@Override
	public double calculer() {
		return this.left.calculer() * this.right.calculer();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteEntiere droite) {

		if(estValeurRemarquable(droite)) {
			return new ConstanteEntiere(0);
		}

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return new ConstanteRationnelle(droite.getEntier() * gauche.getNumerateur(), 
				gauche.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteRationnelle gauche, ConstanteRationnelle droite) {

		return new ConstanteRationnelle(gauche.getNumerateur() * droite.getNumerateur(), 
				gauche.getDenominateur() * droite.getDenominateur()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteEntiere droite) {

		if(estValeurRemarquable(gauche) || estValeurRemarquable(droite)) {
			return new ConstanteEntiere(0);
		}

		if(estElementNeutre(gauche)) {
			return droite;
		}

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return new ConstanteEntiere(gauche.getEntier() * droite.getEntier()).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ConstanteEntiere gauche, ConstanteRationnelle droite) {
		return simplifie(droite, gauche).simplifier();
	}

	@Override
	protected ExpressionArithmetique simplifie(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if(estValeurRemarquable(gauche) || estValeurRemarquable(droite)) {
			return new ConstanteEntiere(0);
		}

		if(estElementNeutre(gauche)) {
			return droite;
		}

		if(estElementNeutre(droite)) {
			return gauche;
		}

		return distributivity(gauche, droite);
	}

	/**
	 * Distributivité sur l'addition et la soustraction.
	 * 
	 * @param gauche
	 * @param droite
	 * @return ExpressionArithmetique
	 */
	private ExpressionArithmetique distributivity(ExpressionArithmetique gauche, ExpressionArithmetique droite) {

		if(gauche instanceof Addition && droite instanceof Addition) {

			return new Addition(
					new Addition(
						new Multiplication(((Addition) gauche).left, ((Addition) droite).left), 
						new Multiplication(((Addition) gauche).left, ((Addition) droite).right)), 
					new Addition(
						new Multiplication(((Addition) gauche).right, ((Addition) droite).left), 
						new Multiplication(((Addition) gauche).right, ((Addition) droite).right))).simplifier();

		} else if(gauche instanceof Soustraction && droite instanceof Soustraction) {

			return new Soustraction(
					new Soustraction(
						new Multiplication(((Soustraction) gauche).left, ((Soustraction) droite).left), 
						new Multiplication(((Soustraction) gauche).left, ((Soustraction) droite).right)), 
					new Soustraction(
						new Multiplication(((Soustraction) gauche).right, ((Soustraction) droite).left), 
						new Multiplication(((Soustraction) gauche).right, ((Soustraction) droite).right))).simplifier();

		} else if(gauche instanceof Addition && droite instanceof Soustraction) {

			return new Addition(
					new Soustraction(
						new Multiplication(((Addition) gauche).left, ((Soustraction) droite).left), 
						new Multiplication(((Addition) gauche).left, ((Soustraction) droite).right)), 
					new Soustraction(
						new Multiplication(((Addition) gauche).right, ((Soustraction) droite).left), 
						new Multiplication(((Addition) gauche).right, ((Soustraction) droite).right))).simplifier();

		} else if(gauche instanceof Soustraction && droite instanceof Addition) {

			return new Soustraction(
					new Addition(
						new Multiplication(((Soustraction) gauche).left, ((Addition) droite).left), 
						new Multiplication(((Soustraction) gauche).left, ((Addition) droite).right)), 
					new Addition(
						new Multiplication(((Soustraction) gauche).right, ((Addition) droite).left), 
						new Multiplication(((Soustraction) gauche).right, ((Addition) droite).right))).simplifier();

		} else if(gauche instanceof Addition) {

			return new Addition(new Multiplication(((Addition) gauche).left, droite), 
					new Multiplication(((Addition) gauche).right, droite)).simplifier();

		} else if(gauche instanceof Soustraction) {

			return new Soustraction(new Multiplication(((Soustraction) gauche).left, droite), 
					new Multiplication(((Soustraction) gauche).right, droite)).simplifier();

		} else if(droite instanceof Addition) {

			return new Addition(new Multiplication(gauche, ((Addition) droite).left), 
					new Multiplication(gauche, ((Addition) droite).right)).simplifier();

		} else if(droite instanceof Soustraction) {

			return new Soustraction(new Multiplication(gauche, ((Soustraction) droite).left), 
					new Multiplication(gauche, ((Soustraction) droite).right)).simplifier();
		}

		return this;
	}

	@Override
	public boolean equals(ExpressionArithmetique ea) {
		return ea instanceof Multiplication 
				&& ((Multiplication) ea.simplifier()).left.equals(((Multiplication) simplifier()).left) 
				&& ((Multiplication) ea.simplifier()).right.equals(((Multiplication) simplifier()).right);
	}

	@Override
	public String toString() {
		return "(" + this.left + " * " + this.right + ")";
	}

	@Override
	protected boolean estElementNeutre(ExpressionArithmetique ea) {
		return ea.equals(Multiplication.ELEMENT_NEUTRE);
	}

	private boolean estValeurRemarquable(ExpressionArithmetique ea) {
		return ea.equals(Multiplication.VALEUR_REMARQUABLE);
	}
}