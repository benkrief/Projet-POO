package poo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import td3.Addition;
import td3.Association;
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
 * Unit tests for equation engine application
 * 
 * Important : la question 9 est testée au travers des tests
 * des autres questions
 */
public class AppTest {

	/**
	 * Question 1
	 * 
	 * Constantes entières et rationnelles
	 * Méthodes testées : calculer, simplifier et toString
	 */
	@Test
	public void constants() {

		ExpressionArithmetique trois = new ConstanteEntiere(3);

		assertEquals(3, trois.calculer(), 0.0001);
		assertEquals("3", trois.simplifier().toString());

		ExpressionArithmetique fraction = new ConstanteRationnelle(5, 25);

		assertEquals(0.2, fraction.calculer(), 0.0001);
		assertEquals("(1/5)", fraction.simplifier().toString());
	}

	/**
	 * Question 2
	 * 
	 * Variables symboliques
	 * Méthodes testées : calculer, simplifier et toString
	 */
	@Test
	public void variables() {

		ExpressionArithmetique x = new VariableSymbolique('x');

		try {
			x.calculer();
			fail("Cette méthode devrait lancer une RuntimeException !");
		} catch(Exception e) {
			System.out.println("exception attrapée : " + e);
		}

		assertEquals("x", x.simplifier().toString());
	}

	/**
	 * Question 3
	 * 
	 * Opérateurs binaires (+, -, *, /, ^)
	 * Opérateurs unaires (racine carrée, cos, sin, ln)
	 * Méthodes testées : calculer, simplifier et toString
	 * 
	 * exemple de calcul :
	 * (9 + 2) * (3 - 1/17) / 2^3 + sqrt(16) * ln(5) + cos(25) / sin(6 + 4/3)
	 */
	@Test
	public void binaryAndUnaryOp() {

		// première addition

		ExpressionArithmetique neuf = new ConstanteEntiere(9);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique plus = new Addition(neuf, deux);

		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique fraction = new ConstanteRationnelle(1, 17);
		ExpressionArithmetique minus = new Soustraction(trois, fraction);

		ExpressionArithmetique pow = new Puissance(deux, trois);

		ExpressionArithmetique times = new Multiplication(plus, minus);
		ExpressionArithmetique divided = new Division(times, pow);

		// deuxième addition

		ExpressionArithmetique seize = new ConstanteEntiere(16);
		ExpressionArithmetique sqrt = new RacineCarree(seize);

		ExpressionArithmetique cinq = new ConstanteEntiere(5);
		ExpressionArithmetique ln = new LogarithmeNeperien(cinq);

		ExpressionArithmetique timesu = new Multiplication(sqrt, ln);

		// troisième addition

		ExpressionArithmetique vingtcinq = new ConstanteEntiere(25);
		ExpressionArithmetique cos = new Cosinus(vingtcinq);

		ExpressionArithmetique six = new ConstanteEntiere(6);
		ExpressionArithmetique fractionu = new ConstanteRationnelle(4, 3);
		ExpressionArithmetique plusu = new Addition(six, fractionu);
		ExpressionArithmetique sin = new Sinus(plusu);

		ExpressionArithmetique dividedu = new Division(cos, sin);

		// total

		ExpressionArithmetique plusfsttotal = new Addition(divided, timesu);
		ExpressionArithmetique plussectotal = new Addition(plusfsttotal, dividedu);

		assertEquals(11.6245, plussectotal.calculer(), 0.0001);
		assertEquals("(((275/68) + (4 * ln(5))) + (cos(25) / sin((22/3))))", plussectotal.simplifier().toString());
	}

	/**
	 * Question 4
	 * 
	 * Constantes symboliques (π, e)
	 * Méthodes testées : calculer, simplifier et toString
	 */
	@Test
	public void symbolicConstants() {

		ExpressionArithmetique pi = new ConstanteSymbolique('π', Math.PI);

		assertEquals(3.1416, pi.calculer(), 0.0001);
		assertEquals("π", pi.simplifier().toString());

		ExpressionArithmetique e = new ConstanteSymbolique('e', Math.E);

		assertEquals(2.7183, e.calculer(), 0.0001);
		assertEquals("e", e.simplifier().toString());
	}

	/**
	 * Question 5
	 * 
	 * Simplifier une expression arithmétique lorsqu'elle est écrite
	 * sous forme standard
	 */
	@Test
	public void simplifyStandardExpression() {

		ExpressionArithmetique fraction1 = new ConstanteRationnelle(1, 4);
		ExpressionArithmetique fraction2 = new ConstanteRationnelle(3, 4);
		ExpressionArithmetique x = new VariableSymbolique('x');

		ExpressionArithmetique pluscr = new Addition(fraction1, fraction2);
		ExpressionArithmetique plusx = new Addition(pluscr, x);

		assertEquals("(1 + x)", plusx.simplifier().toString());
	}

	/**
	 * Question 6
	 * 
	 * Calculer une approximation à 10^-4 près de la valeur d'une
	 * expression arithmétique ne contenant que des constantes réelles
	 */
	@Test
	public void calculateApproximation() {

		ExpressionArithmetique un = new ConstanteEntiere(1);

		// calcul avec constante rationnelle

		ExpressionArithmetique fraction = new ConstanteRationnelle(3, 4);
		ExpressionArithmetique plus = new Addition(un, fraction);

		assertEquals(1.7500, plus.calculer(), 0.0001);

		// calcul avec π

		ExpressionArithmetique pi = new ConstanteSymbolique('π', Math.PI);
		ExpressionArithmetique pluspi = new Addition(un, pi);

		assertEquals(4.1416, pluspi.calculer(), 0.0001);

		// calcul avec e

		ExpressionArithmetique e = new ConstanteSymbolique('e', Math.E);
		ExpressionArithmetique pluse = new Addition(un, e);

		assertEquals(3.7183, pluse.calculer(), 0.0001);
	}

	/**
	 * Question 7
	 * 
	 * S'assurer de l'égalité entre deux expressions arithmétiques
	 * écrites sous forme standard
	 */
	@Test
	public void equals() {

		// première expression sous forme standard

		ExpressionArithmetique fraction1 = new ConstanteRationnelle(1, 4);
		ExpressionArithmetique fraction2 = new ConstanteRationnelle(3, 4);
		ExpressionArithmetique x = new VariableSymbolique('x');

		ExpressionArithmetique pluscr = new Addition(fraction1, fraction2);
		ExpressionArithmetique plusx = new Addition(pluscr, x);

		// deuxième expression sous forme standard

		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique plus = new Addition(un, x);

		assertEquals(true, plusx.equals(plus));
	}

	/**
	 * Question 8
	 * 
	 * Calculer la valeur d'une expression arithmétique contenant
	 * des variables symboliques lorsque la valeur des variables
	 * symboliques est connue
	 */
	@Test
	public void simplifyWithSymbolicVariables() {

		ExpressionArithmetique y = new VariableSymbolique('y');
		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique plus = new Addition(y, un);

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique divided = new Division(x, plus);

		ExpressionArithmetique plusun = new Addition(un, divided);

		Map<ExpressionArithmetique, ExpressionArithmetique> affectations = new HashMap<>();
		affectations.put(x, un);
		affectations.put(y, un);

		assertEquals("(3/2)", plusun.simplifier(affectations).toString());
	}

	/**
	 * Question 10
	 * 
	 * Simplifier les valeurs remarquables
	 */
	@Test
	public void simplifyWithRemarkableValues() {

		// racine carrée (carré parfait)

		ExpressionArithmetique quatre = new ConstanteEntiere(4);
		ExpressionArithmetique racine = new RacineCarree(quatre);

		assertEquals("2", racine.simplifier().toString());

		// cosinus (π/2)

		ExpressionArithmetique un = new ConstanteEntiere(1);

		ExpressionArithmetique pi = new ConstanteSymbolique('π', Math.PI);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique divided = new Division(pi, deux);
		ExpressionArithmetique cosinus = new Cosinus(divided);

		ExpressionArithmetique pluscos = new Addition(un, cosinus);

		assertEquals("1", pluscos.simplifier().toString());

		// sinus (π)

		ExpressionArithmetique sinus = new Sinus(pi);
		ExpressionArithmetique plussin = new Addition(un, sinus);

		assertEquals("1", plussin.simplifier().toString());

		// logarithme neperien (1)

		ExpressionArithmetique ln = new LogarithmeNeperien(un);
		ExpressionArithmetique plusln = new Addition(un, ln);

		assertEquals("1", plusln.simplifier().toString());

		// puissance (0)

		ExpressionArithmetique e = new ConstanteSymbolique('e', Math.E);
		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique pow = new Puissance(e, zero);

		ExpressionArithmetique pluspow = new Addition(un, pow);

		assertEquals("2", pluspow.simplifier().toString());
	}

	/**
	 * Question 11
	 * 
	 * Simplifier une BigSomme
	 */
	@Test
	public void simplifyBigSomme() {

		// bornes inférieures et supérieures

		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique quatre = new ConstanteEntiere(4);

		// expression arithmétique à additioner

		ExpressionArithmetique index = new VariableSymbolique('i');
		ExpressionArithmetique alphai = new VariableSymboliqueIndexee('α', index);

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique pow = new Puissance(x, index);

		ExpressionArithmetique times = new Multiplication(alphai, pow);

		// BigSomme

		ExpressionArithmetique bigSomme = new BigSomme(times, (VariableSymbolique) index, 
			(ConstanteEntiere) zero, (ConstanteEntiere) quatre);

		assertEquals("((((α0 + (α1 * x)) + (α2 * (x^2))) + (α3 * (x^3))) + (α4 * (x^4)))", 
			bigSomme.simplifier().toString());
	}

	/**
	 * Question 12
	 * 
	 * Simplifier un BigProduit
	 */
	@Test
	public void simplifyBigProduit() {

		// bornes inférieures et supérieures

		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique quatre = new ConstanteEntiere(4);

		// expression arithmétique à multiplier

		ExpressionArithmetique index = new VariableSymbolique('i');
		ExpressionArithmetique alphai = new VariableSymboliqueIndexee('α', index);

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique pow = new Puissance(x, index);

		ExpressionArithmetique times = new Multiplication(alphai, pow);

		// BigProduit

		ExpressionArithmetique bigProduit = new BigProduit(times, (VariableSymbolique) index, 
			(ConstanteEntiere) zero, (ConstanteEntiere) quatre);

		assertEquals("((((α0 * (α1 * x)) * (α2 * (x^2))) * (α3 * (x^3))) * (α4 * (x^4)))", 
			bigProduit.simplifier().toString());
	}

	/**
	 * Question 13 & 14
	 * 
	 * Dériver un polynôme à l'ordre n
	 */
	@Test
	public void derivatePolynomial() {

		// dérivation de polynômes (ordre = 1)

		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique cinq = new ConstanteEntiere(5);
		ExpressionArithmetique dix = new ConstanteEntiere(10);
		ExpressionArithmetique[] coefficients = { trois, cinq, dix };

		ExpressionArithmetique x = new VariableSymbolique('x');

		ExpressionArithmetique polynome = new Polynome(coefficients, (VariableSymbolique) x);

		ExpressionArithmetique ordre = new ConstanteEntiere(1);
		ExpressionArithmetique derivee = new DerivationPolynome(polynome, (ConstanteEntiere) ordre);

		assertEquals("((6 * x) + 5)", derivee.simplifier().toString());

		// dérivation de polynômes à l'ordre n

		ExpressionArithmetique quatre = new ConstanteEntiere(4);
		ExpressionArithmetique[] coefficientsbis = { cinq, quatre, trois, cinq, dix };

		ExpressionArithmetique polynomebis = new Polynome(coefficientsbis, (VariableSymbolique) x);

		ExpressionArithmetique ordrebis = new ConstanteEntiere(3);
		ExpressionArithmetique deriveebis = new DerivationPolynome(polynomebis, (ConstanteEntiere) ordrebis);

		assertEquals("((120 * x) + 24)", deriveebis.simplifier().toString());
	}

	/**
	 * Question 15
	 * 
	 * Simplifier les quatres opérations avec l'élément neutre
	 */
	@Test
	public void simplifyWithNeutralElement() {

		ExpressionArithmetique x = new VariableSymbolique('x');

		// éléments neutres

		ExpressionArithmetique zero = new ConstanteEntiere(0);
		ExpressionArithmetique un = new ConstanteEntiere(1);

		// addition

		ExpressionArithmetique plus = new Addition(x, zero);
		assertEquals("x", plus.simplifier().toString());

		// soustraction

		ExpressionArithmetique minus = new Soustraction(x, zero);
		assertEquals("x", minus.simplifier().toString());

		// multiplication

		ExpressionArithmetique times = new Multiplication(x, un);
		assertEquals("x", times.simplifier().toString());

		// division

		ExpressionArithmetique divided = new Division(x, un);
		assertEquals("x", divided.simplifier().toString());

		// puissance

		ExpressionArithmetique pow = new Puissance(x, un);
		assertEquals("x", pow.simplifier().toString());
	}

	/**
	 * Question 16
	 * 
	 * Distribuer le produit sur l'addition et la soustraction
	 */
	@Test
	public void distribute() {

		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique fraction = new ConstanteRationnelle(1, 2);
		ExpressionArithmetique plus = new Addition(x, fraction);

		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique times = new Multiplication(deux, plus);

		ExpressionArithmetique distribute = new Distribution(times);

		assertEquals("((2 * x) + 1)", distribute.simplifier().toString());
	}

	/**
	 * Question 17
	 * 
	 * Associativité des sommes et des multiplications
	 */
	@Test
	public void associate() {

		// addition

		ExpressionArithmetique un = new ConstanteEntiere(1);
		ExpressionArithmetique x = new VariableSymbolique('x');
		ExpressionArithmetique plus = new Addition(un, x);

		ExpressionArithmetique plusun = new Addition(un, plus);

		ExpressionArithmetique associateplus = new Association(plusun);

		assertEquals("(2 + x)", associateplus.simplifier().toString());

		// multiplication

		ExpressionArithmetique fraction = new ConstanteRationnelle(1, 2);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique times = new Multiplication(fraction, x);

		ExpressionArithmetique timesdeux = new Multiplication(deux, times);

		ExpressionArithmetique associatetimes = new Association(timesdeux);

		assertEquals("x", associatetimes.simplifier().toString());
	}

	/**
	 * Question bonus 1
	 * 
	 * Simplifier une racine carrée lorsque son opérande est composé
	 * d'un carré parfait
	 * 
	 * exemple : sqrt(12) = 2 * sqrt(3)
	 */
	@Test
	public void simplifySqrt() {

		ExpressionArithmetique douze = new ConstanteEntiere(12);
		ExpressionArithmetique racine = new RacineCarree(douze);

		assertEquals("(2 * sqrt(3))", racine.simplifier().toString());
	}

	/**
	 * Question bonus 2
	 * 
	 * Simplifier un logarithme népérien lorsque son opérande est
	 * le résultat d'une puissance
	 * 
	 * exemple : ln(8) = 3 * ln(2)
	 */
	@Test
	public void simplifyLn() {

		ExpressionArithmetique huit = new ConstanteEntiere(8);
		ExpressionArithmetique ln = new LogarithmeNeperien(huit);

		assertEquals("(3 * ln(2))", ln.simplifier().toString());
	}
}