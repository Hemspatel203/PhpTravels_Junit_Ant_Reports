/**
 * 
 */
package testFile;

import static org.testng.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import junit.framework.Assert;
import mainPageFile.PhpTravels;

/**
 * @author Hems
 *
 */

public class PhpTravelsTestCase {
	static PhpTravels mPage = new PhpTravels();

	Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	public ErrorCollector errCollector = new ErrorCollector();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUpBeforeMethod() throws Exception {
		mPage.openBrowser();
		APPLICATION_LOGS.debug("Open Browser");
		mPage.openSite();
		APPLICATION_LOGS.debug("Open Site");
		// System.out.println("Site open");
	}

	@After
	public void setUpAfterMethod() throws Exception {
		mPage.closeBrowser();
		APPLICATION_LOGS.debug("Browser Closed");
	}

	@Test
	public void enterFlightDataTest() throws Exception {
		mPage.selectFlightButton();
		APPLICATION_LOGS.debug("In Enter flight Information Page");
		mPage.roundtrip();
		APPLICATION_LOGS.debug("Round trip check box clicked");
		mPage.cabinClassSelect();
		APPLICATION_LOGS.debug("business class selected");
		mPage.enterFrom();
		APPLICATION_LOGS.debug("Entered From");
		mPage.enterTo();
		APPLICATION_LOGS.debug("Entered To");
		mPage.enterDepartureDate();
		APPLICATION_LOGS.debug("Entered Departure Date");
		mPage.enterArrivalDate();
		APPLICATION_LOGS.debug("Entered Arrival Date");
		mPage.enterManualPassenger();
		APPLICATION_LOGS.debug("Entered Manual Passenger");
		mPage.clickSearchButton();
		APPLICATION_LOGS.debug("Clicked Search Button");

		try {
			assertEquals("Flights List", mPage.getTitle());
			APPLICATION_LOGS.debug("Test enterFlightData Passed");
		} catch (Throwable t) {
			// TODO: handle exception
			APPLICATION_LOGS.error(t.getMessage());
			errCollector.addError(t);
		}

		mPage.clickBookNowButton();
		APPLICATION_LOGS.debug("Book Now Button clicked");
		mPage.enterAsAGuest();
		APPLICATION_LOGS.debug("Enter Data in a guest Section");
		mPage.confirmBooking();
		APPLICATION_LOGS.debug("Confirm Booking Button Clicked");

		try {
			assertEquals("Invoice", mPage.getInvoicePageTitle());
			APPLICATION_LOGS.debug("Confirm Booking Successfully");
		} catch (Throwable t) {
			// TODO: handle exception
			APPLICATION_LOGS.error(t.getMessage());
			errCollector.addError(t);
		}

		mPage.downloadPDF();
		APPLICATION_LOGS.debug("PDF Downloaded");
		mPage.arrivalPayButtonClick();
		APPLICATION_LOGS.debug("Arrival pay button clicked");

		try {

			assertEquals("RESERVED", mPage.getReservedMsg());
			APPLICATION_LOGS.debug("Reserved test passed");
		} catch (Throwable t) {
			// TODO: handle exception
			APPLICATION_LOGS.error(t.getMessage());
			errCollector.addError(t);
		}

	}

}
