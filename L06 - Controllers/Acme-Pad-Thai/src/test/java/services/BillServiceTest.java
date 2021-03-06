package services;

import java.util.Collection;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import utilities.AbstractTest;
import domain.Bill;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
						"classpath:spring/datasource.xml",
						"classpath:spring/config/packages.xml"})
@Transactional
public class BillServiceTest extends AbstractTest{

	//Service under test---------------
	@Autowired
	private BillService billService;
	@Autowired
	private SponsorService sponsorService;
	
	//Tests----------------------------
	@Test
	public void testCreate() {
		authenticate("sponsor1");
		sponsorService.findOne(20);
		billService.create();
		unauthenticate();
	}
	
	@Test
	public void testSave() {
		authenticate("sponsor1");
		sponsorService.findOne(20);
		Bill bill = billService.create();
		bill.setDescription("Esto es un bill");
		Bill saved = billService.save(bill);
		Collection<Bill> allBills = billService.findAll();
		Assert.isTrue(allBills.contains(saved));
		unauthenticate();
	}
	
	@Test
	public void testSaveNegative() {
		authenticate("sponsor1");
		sponsorService.findOne(20);
		Bill bill = billService.create();
		bill.setDescription(null);
		try {
			billService.save(bill);
		}
		catch(Exception e){
			Assert.isInstanceOf(IllegalArgumentException.class, e);
		}
	}
	
	@Test
	public void testDelete() {
		authenticate("sponsor1");
		sponsorService.findOne(20);
		Bill bill = billService.create();
		bill.setDescription("Esto es un bill");
		Bill saved = billService.save(bill);
		billService.delete(saved);
		Collection<Bill> allBills = billService.findAll();
		Assert.isTrue(!allBills.contains(saved));
		unauthenticate();
	}
	
	@Test
	public void testCompute() {
		authenticate("admin1");
		Collection<Bill> bills;
		
		bills = billService.computeProcedureMonthlyBills();
		for(Bill b: bills){
			System.out.println(b.getCost());
			System.out.println(b.getDescription());
		}
	}
	
}
