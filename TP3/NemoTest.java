package nemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SumergibleTest {
	
	@Test public void test00CoordenadasIniciales() {
		assertEquals (0, new Sumergible().getCoordenadaX());
		assertEquals (0, new Sumergible().getCoordenadaY());
		assertEquals (0, new Sumergible().getProfundidad());
		assertEquals ("Norte", new Sumergible().getDireccion());
	}
	
	@Test public void test01() { //quedarse en el lugar
		Sumergible sumergible = new Sumergible().doThis("");
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals (0, sumergible.getProfundidad());
		assertEquals ("Norte", sumergible.getDireccion());
	}
	
	@Test public void test02() {
		Sumergible sumergible = new Sumergible().doThis("d"); //usar caracteres
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals ("Norte", sumergible.getDireccion());
	}
	
	//test 03 "u"
	@Test public void test03() {
		Sumergible sumergible = new Sumergible().doThis("d");
		assertEquals (-1, sumergible.getProfundidad());
		
		sumergible.doThis("u"); 
		assertEquals (0, sumergible.getProfundidad());
	}

	//test 04 "l" "r"
	@Test public void test04(){
		Sumergible sumergible = new Sumergible().doThis("l");
		assertEquals ("Oeste", sumergible.getDireccion());
		
		sumergible.doThis("r");
		assertEquals ("Norte", sumergible.getDireccion());
			
	}
    //caso de test con r
	@Test public void test05(){
		
		Sumergible sumergible = new Sumergible();
		assertEquals ("Norte", sumergible.getDireccion());
		
		sumergible.doThis("r");
		assertEquals ("Este", sumergible.getDireccion());
			
	}

	//caso de test con f 
	@Test public void test06(){ 
		Sumergible sumergible = new Sumergible().doThis("f");
		assertEquals (1, sumergible.getCoordenadaY());
		assertEquals ("Norte", sumergible.getDireccion());
	}
	
	//caso de test con m - no hay que hacerlo todavía pero hay que ir viendo y probando 
	@Test public void test07(){
		Sumergible sumergible = new Sumergible().doThis("m d");
		assertEquals (-1, sumergible.getProfundidad()); 
		assertTrue (sumergible.getEstadoCapsulaLiberada()); 

		sumergible.doThis("d");
		assertEquals (-2, sumergible.getProfundidad()); 
	}

	@Test public void test08(){
		Sumergible sumergible = new Sumergible().doThis("d d");
		assertThrowsLike (() -> sumergible.doThis("m"), Sumergible.errorMessage_Explota);
	}
	
	
	@Test public void test09(){
		Sumergible sumergible = new Sumergible().doThis("d d u f l f l f r f l l f f");
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals ("Este", sumergible.getDireccion());
		assertFalse (sumergible.getEstadoCapsulaLiberada());
		
	}
	
	@Test public void test10(){
		Sumergible sumergible = new Sumergible().doThis("d u f f l"); 
		assertEquals (0, sumergible.getProfundidad());
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (2, sumergible.getCoordenadaY());
		assertEquals ("Oeste", sumergible.getDireccion());
		assertFalse (sumergible.getEstadoCapsulaLiberada());
	}

	@Test public void test11(){ 
		Sumergible sumergible = new Sumergible().doThis("f f d l");
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (2, sumergible.getCoordenadaY());
		assertEquals ("Oeste", sumergible.getDireccion());
		assertFalse (sumergible.getEstadoCapsulaLiberada());
	}

	@Test public void test12(){
		Sumergible sumergible = new Sumergible().doThis("ffdl"); //pruebo con las letras sin espacios
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (2, sumergible.getCoordenadaY());
		assertEquals ("Oeste", sumergible.getDireccion());
		assertFalse (sumergible.getEstadoCapsulaLiberada());

	}
	
	
	
	private void assertThrowsLike( Executable e, String message ) {
		  assertEquals( message, assertThrows( Error.class, e ).getMessage() );
	  }

}	