package acme.testing.anonymous.shout;

import java.time.LocalDate;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousShoutCreateTest extends AcmePlannerTest{

	
	/*
		En este test comprobamos que un anonimo no puede crear un shout.
		Para ello accedemos al formulario de create a través del menú y rellenamos los campos necesarios
		con valores no validos:
			- Author -> (NotBlank & Max 25 & No spam)
			- Text -> (NotBlank & Max 100 & No spam)
			- Info -> (URL)
		y pulsando el boton Shout!.
		A continuación se comprueba que el formulario devuelve errores.
	 */
//	@ParameterizedTest
//	@CsvFileSource(resources = "/anonymous/shout/createNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
//	@Order(10)
//	public void createNegative(final int recordIndex, final String author, final String text, final String info,
//		final String moment, final String money, final String b) {
//		
//		super.clickOnMenu("Anonymous", "Shout!");
//		
//		super.fillInputBoxIn("author", author);
//		super.fillInputBoxIn("text", text);
//		super.fillInputBoxIn("info", info);
//		super.fillInputBoxIn("domemi.deadline", moment);
//		super.fillInputBoxIn("domemi.budget", money);
//		super.fillInputBoxIn("domemi.important",b);
//		
//		super.clickOnSubmitButton("Shout!");
//		
//		super.checkErrorsExist();
//		if(recordIndex==1) {
//			super.checkErrorsExist("author");
//			super.checkErrorsExist("info");
//		}
//		
//	}
	/*
	En este test se comprueba que un usuario no autorizado no sea capaz de crear un shout
	Para ello accedemos a la url del formulario de create de shouts comprobando que nos devuelve un error de autorizacion
	 */
//	@ParameterizedTest
//	@CsvFileSource(resources="/anonymous/shout/users.csv", encoding="utf-8", numLinesToSkip=1)
//	@Order(20)
//	public void createNegative(final String username, final String password) {
//		if(username!=null) this.signIn(username, password);
//		super.driver.get("http://localhost:8080/June-Template/anonymous/shout/create");
//		super.checkPanicExists();
//		if(username!=null) super.signOut();
//	}
	
	/*
	En este test comprobamos que un anonimo puede crear un shout.
	Para ello accedemos al formulario de create a través del menú y rellenamos los campos necesarios
	con valores validos:
		- Author -> (NotBlank & Max 25 & No spam)
		- Text -> (NotBlank & Max 100 & No spam)
		- Info -> (URL)
	 y pulsando el boton Shout!.
	A continuación se comprueba si el shout se ha creado.
	 */
	@SuppressWarnings("deprecation")
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/shout/createPositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(30)
	public void createPositive(final int recordIndex, final String author, final String text, final String info,
		final String moment, final String money, final String b, final String sheet) {
		
		final String dia;
		super.clickOnMenu("Anonymous", "Shout!");
		
		final LocalDate fecha =LocalDate.now();
		if(String.valueOf(fecha.getDayOfMonth()).length()==1) {
			dia = "0" + fecha.getDayOfMonth();
		}else {
			dia = fecha.getDayOfMonth() + "";
		}
		final LocalDate hoy=LocalDate.now();
        final String  anyo= hoy.getYear() +"";
        final String yy=anyo.substring(2, 4);
        String mm = hoy.getMonthValue() + "";
        if(mm.length()==1) mm = "0"+mm;
        String dd = hoy.getDayOfMonth() + "";
        if(dd.length()==1) dd = "0"+dd;
        final Integer  i = 3;
        final String formatI = String.format("%%0%dd", 5);
        final String formattedI = String.format(formatI, i);
        
        
        
        final String pattern = yy + ":" + "DM:" + mm + ":"+ formattedI + ":" + dd;
        final String domemi = "Stamp:" + pattern + sheet;
        
		super.fillInputBoxIn("author", author);
		super.fillInputBoxIn("text", text);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("domemi.deadline", moment);
		super.fillInputBoxIn("domemi.budget", money);
		super.fillInputBoxIn("domemi.important",b);
		
		super.clickOnSubmitButton("Shout!");
	
		super.clickOnMenu("Anonymous", "List shouts");
	
		super.checkColumnHasValue(0, 1, author);
		super.checkColumnHasValue(0, 2, text);
		super.checkColumnHasValue(0, 3, info);
		super.checkColumnHasValue(0, 4, domemi);
		
	}
	
	
	
}
