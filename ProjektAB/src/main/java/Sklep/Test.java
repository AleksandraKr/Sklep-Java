package Sklep;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        //StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        //Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
        //SessionFactory factory=meta.getSessionFactoryBuilder().build();
        //Session session=factory.openSession();

        //Transaction t=session.beginTransaction();

        TypedQuery query = session.createQuery("from TypUzytkownika");
        List<TypUzytkownika> list=query.getResultList();
        Adres a = new Adres();

        a.setIdAdres(22);
        a.setKodPocztowy(89100);
        a.setMiasto("Torun");
        a.setNrMieszkania(32);
        a.setUlica("Kasztanowa 31");



        Uzytkownik u = new Uzytkownik();
        u.setNazwaUzytkownika("Coooo45ooooo");
        u.setImie("Ola");
        u.setNazwisko("Kruza");
        u.setHaslo("jhsg");
        //u.setAdres(a);


        Set<Uzytkownik> uz = new HashSet<Uzytkownik>();
        uz.add(u);

        //a.setUzytkownicy(uz);
        Iterator<TypUzytkownika> itr=list.iterator();
        while(itr.hasNext()){
            TypUzytkownika tu = itr.next();
            tu.setUzytkownicy(uz);
            session.save(tu);
        }



        session.save(a);
        session.persist(u);
        //session.persist(u);

        //t.commit();
        //session.close();

        session.getTransaction().commit();

    }

}