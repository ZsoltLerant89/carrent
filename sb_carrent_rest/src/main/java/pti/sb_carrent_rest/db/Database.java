package pti.sb_carrent_rest.db;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_carrent_rest.model.Car;
import pti.sb_carrent_rest.model.Reservation;


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

	public List<Car> getActiveCars() {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Car> query = session.createSelectionQuery("SELECT c FROM Car c WHERE c.active = true", Car.class);
		
		List<Car> carList = query.getResultList();
		
		tx.commit();
		session.close();
		
		return carList;
	}
	
	public List<Reservation> getReservationByCarIdWithinPeriods(int carId,
																LocalDate beginOfReservation,
																LocalDate endOfReservation 
																) 
	{
	
		List<Reservation> reservationList = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Reservation> query = session.createSelectionQuery("SELECT r FROM Reservation r WHERE r.carId = ?1 AND"
						+ "((r.endOfReservation >= ?2 AND r.endOfReservation <= ?3 AND r.beginOfReservation <= ?2) OR "
						+ "(r.beginOfReservation >= ?2 AND r.beginOfReservation < ?3 AND r.endOfReservation > ?2 AND r.endOfReservation <= ?3) OR"
						+ "(r.beginOfReservation <= ?3 AND r.beginOfReservation >= ?2 AND r.endOfReservation > ?3))" , 
						Reservation.class);
		query.setParameter(1, carId);
		query.setParameter(2, beginOfReservation);
		query.setParameter(3, endOfReservation);
		
		reservationList = query.getResultList();
		
		tx.commit();
		session.close();

		return reservationList;
	}
	
	public void saveReservation(Reservation reservation) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(reservation);
		
		tx.commit();
		session.close();
		
	}
	
	public Car getCarById(int carId){
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Car car = session.get(Car.class, carId);
		
		tx.commit();
		session.close();
		
		return car;
	}
}
