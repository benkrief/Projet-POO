package poo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import td3.Addition;
import td3.ConstanteEntiere;
import td3.ConstanteRationnelle;
import td3.Cosinus;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.LogarithmeNeperien;
import td3.Multiplication;
import td3.RacineCarree;
import td3.Sinus;
import td3.Soustraction;
import td3.VariableSymboliqueEntiere;
import td3.VariableSymboliqueRationnelle;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void simpleSum() {

		ExpressionArithmetique neuf = new ConstanteEntiere(9);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique racine = new Addition(neuf, deux);

		assertEquals(11, ((ConstanteEntiere) racine.simplifier()).getEntier());
	}
	
	@Test
	public void variablesSum() {

		ExpressionArithmetique ce = new ConstanteEntiere(3);
		ExpressionArithmetique cr = new ConstanteRationnelle(1, 5);

		ExpressionArithmetique x = new VariableSymboliqueEntiere('x');
		((VariableSymboliqueEntiere) x).setConstanteEntiere((ConstanteEntiere) ce);

		ExpressionArithmetique y = new VariableSymboliqueRationnelle('y');
		((VariableSymboliqueRationnelle) y).setConstanteRationnelle((ConstanteRationnelle) cr);

		ExpressionArithmetique plus = new Addition(x, y);

		assertEquals("(x + y)", plus.simplifier().toString());
		assertEquals(3+1.0/5, plus.calculer(), 0.0001);
	}
	
	@Test
	public void constantesSymboliquesSum() {

		ExpressionArithmetique ce = new ConstanteEntiere(1);
		ExpressionArithmetique cr = new ConstanteRationnelle(3, 4);

		ExpressionArithmetique plus = new Addition(ce, cr);
		assertEquals(1+3.0/4, plus.calculer(), 0.0001);

		ExpressionArithmetique pluspi = new Addition(ce, ExpressionArithmetique.PI);
		assertEquals(1+3.1415, pluspi.calculer(), 0.0001);
	}

	@Test
	public void simplifyBinaryOp() {

		ExpressionArithmetique neuf = new ConstanteEntiere(9);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique cr = new ConstanteRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);
		ExpressionArithmetique divided = new Division(times, neuf);

		assertEquals(550, ((ConstanteRationnelle) divided.simplifier()).getNumerateur());
		assertEquals(153, ((ConstanteRationnelle) divided.simplifier()).getDenominateur());
	}

	@Test
	public void calculateBinaryOp() {

		ExpressionArithmetique neuf = new ConstanteEntiere(9);
		ExpressionArithmetique deux = new ConstanteEntiere(2);
		ExpressionArithmetique trois = new ConstanteEntiere(3);
		ExpressionArithmetique cr = new ConstanteRationnelle(1, 17);

		ExpressionArithmetique plus = new Addition(neuf, deux);
		ExpressionArithmetique minus = new Soustraction(trois, cr);
		ExpressionArithmetique times = new Multiplication(plus, minus);
		ExpressionArithmetique divided = new Division(times, neuf);

		ExpressionArithmetique result = new ConstanteRationnelle(550, 153);

		assertEquals((11*(3-1.0/17))/9, divided.calculer(), 0.0001);
		assertEquals(550.0/153, result.calculer(), 0.0001);
	}
	
	@Test
	public void simplifyUnaryOp() {

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
}