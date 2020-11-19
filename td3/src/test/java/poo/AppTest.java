package poo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import td3.Addition;
import td3.BigProduit;
import td3.BigSomme;
import td3.ConstanteEntiere;
import td3.ConstanteRationnelle;
import td3.ConstanteSymbolique;
import td3.Cosinus;
import td3.DerivationPolynome;
import td3.Distribution;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.Polynome;
import td3.Puissance;
import td3.RacineCarree;
import td3.Sinus;
import td3.Soustraction;
import td3.VariableSymbolique;
import td3.VariableSymboliqueIndexee;

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

		ExpressionArithmetique x = new VariableSymbolique('x');

		assertEquals('x', ((VariableSymbolique) x.simplifier()).getSymbole());

		ExpressionArithmetique y = new VariableSymbolique('y');

		assertEquals('y', ((VariableSymbolique) y.simplifier()).getSymbole());
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

		ExpressionArithmetique cr4 = new ConstanteRationnelle(6, 1);
		ExpressionArithmetique lnCR = new LogarithmeNeperien(cr4);

		assertEquals("ln(6)", lnCR.simplifier().toString());
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

		ExpressionArithmetique pi = new ConstanteSymbolique('π', Math.PI);
		ExpressionArithmetique pluspi = new Addition(ce, pi);

		assertEquals(4.1416, pluspi.calculer(), 0.0001);

		// calcul avec e

		ExpressionArithmetique exp = new ConstanteSymbolique('e', Math.E);
		ExpressionArithmetique plusexp = new Addition(ce, exp);

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

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique plusX = new Addition(plus, x);

		assertEquals("(1 + x)", plusX.simplifier().toString());
	}

	/**
	 * Question 7
	 * 
	 * S'assurer de l'égalité entre deux expressions arithmetiques
	 */
	@Test
	public void areEquals() {

		// première expression sous forme standard

		ExpressionArithmetique unquart = new ConstanteRationnelle(1, 4);
		ExpressionArithmetique troisquart = new ConstanteRationnelle(3, 4);

		ExpressionArithmetique plusCR = new Addition(unquart, troisquart);

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique plusX = new Addition(plusCR, x);

		// deuxième expression sous forme standard

		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique plus = new Addition(un, x);

		assertEquals(true, plusX.equals(plus));
	}

	/**
	 * Question 8
	 * 
	 * Calculer la valeur d'une expression arithmetique contenant des variables symboliques
	 */
	@Test
	public void simplifyWithSymbolicVariables() {

		ExpressionArithmetique y = new VariableSymbolique('y');
		ExpressionArithmetique un = new ConstanteEntiere(1);

		ExpressionArithmetique plus = new Addition(y, un);

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique divided = new Division(x, plus);

		ExpressionArithmetique plusUn = new Addition(un, divided);

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();
		affectations.put(x, un);
		affectations.put(y, un);

		assertEquals("(3/2)", plusUn.simplifier(affectations).toString());
		assertEquals(1.5, plusUn.simplifier(affectations).calculer(), 0.0001);
	}

	/**
	 * Question 10
	 * 
	 * Simplifier les valeurs remarquables
	 */
	@Test
	public void simplifyWithRemarkableValues() {

		// racine carree

		ExpressionArithmetique quatre = new ConstanteEntiere(4);
		ExpressionArithmetique racine = new RacineCarree(quatre);

		assertEquals("2", racine.simplifier().toString());

		// cosinus

		ExpressionArithmetique pi = new ConstanteSymbolique('π', Math.PI);
		ExpressionArithmetique deux = new ConstanteEntiere(2);

		ExpressionArithmetique divided = new Division(pi, deux);
		ExpressionArithmetique cosinus = new Cosinus(divided);

		assertEquals("0", cosinus.simplifier().toString());

		// sinus

		ExpressionArithmetique sinus = new Sinus(pi);

		assertEquals("0", sinus.simplifier().toString());

		// logarithme neperien

		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique ln = new LogarithmeNeperien(un);

		assertEquals("0", ln.simplifier().toString());

		// puissance

		ExpressionArithmetique exp = new ConstanteSymbolique('e', Math.E);
		ExpressionArithmetique zero = new ConstanteEntiere(0);

		ExpressionArithmetique pow = new Puissance(exp, zero);

		assertEquals("1", pow.simplifier().toString());
	}

	/**
	 * Question 11
	 * 
	 * Simplifier une BigSomme
	 * 
	 * Question 12
	 * 
	 * Simplifier un BigProduit
	 */
	@Test
	public void simplifyBigSommeAndBigProduit() {

		// bornes inférieures et supérieures

		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique quatre = new ConstanteEntiere(4);

		// variable symbolique indexée

		ExpressionArithmetique index = new VariableSymbolique('i');
		ExpressionArithmetique alphai = new VariableSymboliqueIndexee('α', index);

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();
		affectations.put(index, quatre);

		assertEquals("α4", alphai.simplifier(affectations).toString());

		// puissance variable symbolique

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique pow = new Puissance(x, index);

		// multiplication

		ExpressionArithmetique times = new Multiplication(alphai, pow);

		// BigSomme

		ExpressionArithmetique bigSomme = new BigSomme(times, (VariableSymbolique) index, (ConstanteEntiere) zero, (ConstanteEntiere) quatre);

		assertEquals("((((α0 + (α1 * x)) + (α2 * (x^2))) + (α3 * (x^3))) + (α4 * (x^4)))", bigSomme.simplifier().toString());

		// BigProduit

		ExpressionArithmetique bigProduit = new BigProduit(times, (VariableSymbolique) index, (ConstanteEntiere) zero, (ConstanteEntiere) quatre);

		assertEquals("((((α0 * (α1 * x)) * (α2 * (x^2))) * (α3 * (x^3))) * (α4 * (x^4)))", bigProduit.simplifier().toString());
	}

	/**
	 * Question 13
	 * 
	 * Dériver un polynôme
	 */
	@Test
	public void derivatePolynomial() {

		// dérivation de polynômes

		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique cinq = new ConstanteEntiere(5);
		ExpressionArithmetique dix = new ConstanteEntiere(10);

		ExpressionArithmetique[] coefficients = { trois, cinq, dix };
		ExpressionArithmetique x = new VariableSymbolique('x');

		ExpressionArithmetique polynome = new Polynome(coefficients, (VariableSymbolique) x);

		ExpressionArithmetique ordre = new ConstanteEntiere(1);
		ExpressionArithmetique derive = new DerivationPolynome(polynome, (ConstanteEntiere) ordre);

		assertEquals("((6 * x) + 5)", derive.simplifier().toString());

		// évaluation de polynômes

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();
		affectations.put(x, trois);

		assertEquals("52", ((Polynome) polynome).evaluer(affectations).toString());

		// dérivation de polynômes à l'ordre n

		ExpressionArithmetique quatre = new ConstanteEntiere(4);

		ExpressionArithmetique[] coefficientsN = { cinq, quatre, trois, cinq, dix };

		ExpressionArithmetique polynomeN = new Polynome(coefficientsN, (VariableSymbolique) x);

		ExpressionArithmetique ordreN = new ConstanteEntiere(3);
		ExpressionArithmetique deriveN = new DerivationPolynome(polynomeN, (ConstanteEntiere) ordreN);

		assertEquals("((120 * x) + 24)", deriveN.simplifier().toString());
	}

	/**
	 * Question 15
	 * 
	 * Simplifier les quatres opérations avec l'élément neutre
	 */
	@Test
	public void simplifyWithNeutralElement() {

		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique deuxtiers = new ConstanteRationnelle(2, 3);

		// addition

		ExpressionArithmetique plus = new Addition(zero, deuxtiers);

		assertEquals("(2/3)", plus.simplifier().toString());

		// soustraction

		ExpressionArithmetique minus = new Soustraction(deuxtiers, zero);

		assertEquals("(2/3)", minus.simplifier().toString());

		// multiplication

		ExpressionArithmetique times = new Multiplication(deuxtiers, un);

		assertEquals("(2/3)", times.simplifier().toString());

		// division

		ExpressionArithmetique divided = new Division(deuxtiers, un);

		assertEquals("(2/3)", divided.simplifier().toString());

		// puissance

		ExpressionArithmetique pow = new Puissance(deuxtiers, un);

		assertEquals("(2/3)", pow.simplifier().toString());
	}

	/**
	 * Question 16
	 * 
	 * Supporter la distributivité du produit sur l'addition et la soustraction
	 */
	@Test
	public void simplifyWithDistributivity() {

		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique undemi = new ConstanteRationnelle(1, 2);
		ExpressionArithmetique x = new VariableSymbolique('x');

		ExpressionArithmetique plus = new Addition(x, undemi);
		ExpressionArithmetique times = new Multiplication(deux, plus);

		ExpressionArithmetique distrib = new Distribution(times);

		assertEquals("((2 * x) + 1)", distrib.simplifier().toString());
	}
}