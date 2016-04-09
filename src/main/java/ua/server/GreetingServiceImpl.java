package ua.server;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import ua.client.GreetingService;
import ua.client.model.User;
import ua.client.ui.Greeting;
import ua.utils.HibernateInit;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Surger on 09.04.2016.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    HibernateInit hibernateInit = new HibernateInit();

    public static final Logger logger = Logger.getLogger(GreetingServiceImpl.class.getName());

    @Override
    public List<User> getAllUsers() {
        logger.info("Get all users from database");
        Session session = hibernateInit.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> result = criteria.list();
        session.close();
        return result;
    }

    @Override
    public void AuthorizedUser(String name, String password) {
        List<User> result = getAllUsers();
        for (User user: result) {
            logger.info("Comparing the login and password");
            if (name.equals(user.getLogin()) && hashPasswordAndSault(password).equals(user.getPassword())){
                logger.info("User find....");
                RootPanel.get("body").clear();
                RootPanel.get("body").add(new Greeting(user.getName()));
            }

    }
    }

    private   String hashPasswordAndSault(String name){
        name += "sault";
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(name.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}