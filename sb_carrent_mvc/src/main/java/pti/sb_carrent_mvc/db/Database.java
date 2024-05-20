package pti.sb_carrent_mvc.db;

import java.time.LocalDate;
import java.util.List;

import org.apache.catalina.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_carrent_mvc.model.Car;
import pti.sb_carrent_mvc.model.Reservation;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database()
	{
		Configuration config = new Configuration();
		config.configure();
		
		this.sessionFactory = config.buildSessionFactory();
	}
	
	public void closeDb()
	{
		this.sessionFactory.close();
	}

	public List<Car> getCars(LocalDate beginingOfReservation, LocalDate endOfReservation) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> query = session.createSelectionQuery("SELECT c FROM Car c", Car.class);
		
		List<Car> carList = query.getResultList();
		
		tx.commit();
		session.close();
		
		return carList;
	}

	public List<Reservation> getReservation(int carId) {
		List<Reservation> reservationList = null;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Reservation> query = session.createSelectionQuery("SELECT r FROM Reservation r WHERE r.carId = ?1", Reservation.class);
		query.setParameter(1, carId);
		
		reservationList = query.getResultList();
		
		tx.commit();
		session.close();

		return reservationList;
	}
	
	
}
