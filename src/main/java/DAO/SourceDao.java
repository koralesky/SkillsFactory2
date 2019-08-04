package DAO;

import org.hibernate.SessionFactory;
import entities.*;

import java.util.List;

public class SourceDao extends BaseDao{


    protected SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Source get(Long id){
        return super.produceInTransaction(session -> session.get(Source.class, id));
    }

    public void save(Source source){
        super.executeInTransaction(session -> session.save(source));
    }

    public void delete(Source source){
        super.executeInTransaction(session -> session.delete(source));
    }

    public void update(Source source){
        super.executeInTransaction(session -> session.update(source));
    }

    public List<Source> getAll(){
        return super.produceInTransaction(session -> session.createQuery("SELECT s FROM Source s", Source.class)
        .getResultList());
    }

    public List<Source> getAllByName(String name){
        return super.produceInTransaction(session -> session.createQuery("SELECT s FROM Source s WHERE s.name = :name", Source.class)
                .setParameter("name", name)
                .getResultList());
    }
}
