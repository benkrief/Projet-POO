package poo;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import td3.Addition;
import td3.ConstanteEntiere;
import td3.ConstanteRationnelle;
import td3.Cosinus;
import td3.Division;
import td3.ExpressionArithmetique;
import td3.Multiplication;
import td3.RacineCarree;
import td3.Soustraction;

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

		assertEquals(33.0/9, divided.calculer(), 0.00001);
		assertEquals(550/153, result.calculer(), 0.00001);
	}
	
	@Test
	public void simplifyUnaryOp() {

		ExpressionArithmetique ce = new ConstanteEntiere(75);
		ExpressionArithmetique racineCE = new RacineCarree(ce);

		assertEquals("(5 * sqrt(3))", racineCE.simplifier().toString());

		ExpressionArithmetique cr = new ConstanteRationnelle(9, 2);
		ExpressionArithmetique racineCR = new RacineCarree(cr);

		assertEquals("((3 * sqrt(1)) / (1 * sqrt(2)))", racineCR.simplifier().toString());

		ExpressionArithmetique ce2 = new ConstanteEntiere(275);
		ExpressionArithmetique cosCE = new Cosinus(ce2);

		assertEquals("cos(4)", cosCE.simplifier().toString());

		ExpressionArithmetique cr2 = new ConstanteRationnelle(333, 18);
		ExpressionArithmetique cosCR = new Cosinus(cr2);

		assertEquals("cos((37/2))", cosCR.simplifier().toString());
	}
}