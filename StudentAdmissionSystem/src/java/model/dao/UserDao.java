package model.dao;

import model.entity.Users;
import org.hibernate.*;


public class UserDao {
    public Users registerUser(Users user){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Transaction tr = ss.beginTransaction();
        ss.save(user);
        tr.commit();
        ss.close();
        return user;
    }
    
    public Users findUserByEmail(Users user){
        Session ss = HibernateUtil.getSessionFactory().openSession();
        Users theUser = (Users) ss.get(Users.class, user.getEmail());
        if(theUser != null){
            ss.close();
            return theUser;
        }
        ss.close();
        return null;
    }
    
    public Users login(Users user){
        Users theUser = findUserByEmail(user);
        if(theUser != null){
            if(theUser.getPassword().equals(user.getPassword())){
                return theUser;
            }
        }
        
        return null;
    }
    
}
