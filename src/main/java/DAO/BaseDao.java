package DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDao {

    private static final Logger log = Logger.getLogger("BaseDao");

    private final SessionFactory sessionFactory;

    protected BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <R> R produceInTransaction(Function<Session, R> function) {
        Transaction transaction = null;
        R result = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = function.apply(session);
            transaction.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Błąd wykonania executeInTransaction", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    protected void executeInTransaction(Consumer<Session> consumer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Błąd wykonania executeInTransaction", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}