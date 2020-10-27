package poo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import td3.Addition;
import td3.ConstanteEntiere;
import td3.ConstanteRationnelle;
import td3.ConstanteSymbolique;
import td3.Cosinus;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.Puissance;
import td3.RacineCarree;
import td3.Sinus;
import td3.Soustraction;
import td3.VariableSymbolique;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Question 1
	 * 
	 * Constantes entières et rationnelles
	 */
	@Test
	public void constants() {

		ExpressionArithmetique trois = new ConstanteEntiere(3);

		assertEquals(3, ((ConstanteEntiere) trois.simplifier()).getEntier());
		assertEquals(3, trois.calculer(), 0.0001);

		ExpressionArithmetique uncinquieme = new ConstanteRationnelle(1, 5);

		assertEquals(1, ((ConstanteRationnelle) uncinquieme.simplifier()).getNumerateur());
		assertEquals(5, ((ConstanteRationnelle) uncinquieme.simplifier()).getDenominateur());
		assertEquals(0.2, uncinquieme.calculer(), 0.0001);
	}

	/**
	 * Question 2
	 * 
	 * Variables symboliques
	 */
	@Test
	public void variables() {

		ExpressionArithmetique ce = new ConstanteEntiere(8);
		ExpressionArithmetique x = new VariableSymbolique(ce);

		assertEquals(8, ((ConstanteEntiere) x.simplifier()).getEntier());
		assertEquals(8, x.calculer(), 0.0001);

		ExpressionArithmetique cr = new ConstanteRationnelle(2, 4);
		ExpressionArithmetique y = new VariableSymbolique(cr);

		assertEquals(1, ((ConstanteRationnelle) y.simplifier()).getNumerateur());
		assertEquals(2, ((ConstanteRationnelle) y.simplifier()).getDenominateur());
		assertEquals(0.5, y.calculer(), 0.0001);
	}

	/**
	 * Question 3
	 * 
	 * Opérateurs binaires (+, -, *, /, ^)
	 */
	@Test
	public void binaryOp() {

		// addition

		ExpressionArithmetique neuf = new ConstanteEntiere(9);
		ExpressionArithmetique deux = new ConstanteEntiere(2);

		ExpressionArithmetique plus = new Addition(neuf, deux);

		// soustraction

		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique undixseptieme = new ConstanteRationnelle(1, 17);

		ExpressionArithmetique minus = new Soustraction(trois, undixseptieme);

		// multiplication

		ExpressionArithmetique times = new Multiplication(plus, minus);

		// division

		ExpressionArithmetique divided = new Division(times, neuf);

		assertEquals(550, ((ConstanteRationnelle) divided.simplifier()).getNumerateur());
		assertEquals(153, ((ConstanteRationnelle) divided.simplifier()).getDenominateur());
		assertEquals((11*(3-1.0/17))/9, divided.calculer(), 0.0001);

		// puissance

		ExpressionArithmetique pow = new Puissance(deux, trois);

		assertEquals(8, ((ConstanteEntiere) pow.simplifier()).getEntier());
		assertEquals(8, pow.calculer(), 0.0001);
	}

	/**
	 * Question 3 (suite)
	 * 
	 * Opérateurs unaires (racine carree, cos, sin, ln)
	 * 
	 * Question 9
	 * 
	 * Afficher une expression arithmetique (toString())
	 */
	@Test
	public void unaryOp() {

		// racine carrée

		ExpressionArithmetique ce = new ConstanteEntiere(75);
		ExpressionArithmetique racineCE = new RacineCarree(ce);

		assertEquals("(5 * sqrt(3))", racineCE.simplifier().toString());

		ExpressionArithmetique cr = new ConstanteRationnelle(9, 2);
		ExpressionArithmetique racineCR = new RacineCarree(cr);

		assertEquals("(3 / sqrt(2))", racineCR.simplifier().toString());

		// cosinus

		ExpressionArithmetique ce2 = new ConstanteEntiere(275);
		ExpressionArithmetique cosCE = new Cosinus(ce2);

		assertEquals("cos(275)", cosCE.simplifier().toString());

		ExpressionArithmetique cr2 = new ConstanteRationnelle(333, 18);
		ExpressionArithmetique cosCR = new Cosinus(cr2);

		assertEquals("cos((37/2))", cosCR.simplifier().toString());

		// sinus

		ExpressionArithmetique ce3 = new ConstanteEntiere(23);
		ExpressionArithmetique sinCE = new Sinus(ce3);

		assertEquals("sin(23)", sinCE.simplifier().toString());

		ExpressionArithmetique cr3 = new ConstanteRationnelle(1234, 444);
		ExpressionArithmetique sinCR = new Sinus(cr3);

		assertEquals("sin((617/222))", sinCR.simplifier().toString());

		// logarithme népérien

		ExpressionArithmetique ce4 = new ConstanteEntiere(8);
		ExpressionArithmetique lnCE = new LogarithmeNeperien(ce4);

		assertEquals("(3 * ln(2))", lnCE.simplifier().toString());

		ExpressionArithmetique cr4 = new ConstanteRationnelle(6, 7);
		ExpressionArithmetique lnCR = new LogarithmeNeperien(cr4);

		assertEquals("(ln(6) - ln(7))", lnCR.simplifier().toString());
	}

	/**
	 * Question 4
	 * 
	 * Constantes symboliques (π, e)
	 * 
	 * Question 6
	 * 
	 * Calculer une approximation à 10^-4 près
	 */
	@Test
	public void symbolicConstants() {

		ExpressionArithmetique ce = new ConstanteEntiere(1);

		// calcul avec constante rationnelle

		ExpressionArithmetique cr = new ConstanteRationnelle(3, 4);
		ExpressionArithmetique plus = new Addition(ce, cr);

		assertEquals(1.7500, plus.calculer(), 0.0001);

		// calcul avec π

		ExpressionArithmetique pluspi = new Addition(ce, ExpressionArithmetique.PI);

		assertEquals(4.1416, pluspi.calculer(), 0.0001);

		// calcul avec e

		ExpressionArithmetique plusexp = new Addition(ce, ExpressionArithmetique.E);

		assertEquals(3.7183, plusexp.calculer(), 0.0001);
	}

	/**
	 * Question 5
	 * 
	 * Simplifier une expression sous forme standard
	 */
	@Test
	public void simplifyStandardExpression() {

		ExpressionArithmetique unquart = new ConstanteRationnelle(1, 4);
		ExpressionArithmetique troisquart = new ConstanteRationnelle(3, 4);

		ExpressionArithmetique plus = new Addition(unquart, troisquart);

		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique x = new VariableSymbolique(un);

		ExpressionArithmetique plusX = new Addition(plus, x);

		assertEquals("(1 + x)", plusX.simplifier().toString());
	}
}