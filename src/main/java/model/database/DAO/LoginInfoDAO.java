/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.database.DAO;

import java.util.List;
import model.APIs.SecurityAPIs.Encryption;
import model.database.POJO.Users;
import model.database.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public class LoginInfoDAO {

    /**
     * Verify login information of user
     *
     * @param login_name String
     * @param login_password String
     * @return true if the login is matched, false otherwise.
     */
    public static long check_login(String login_name, String login_password) {
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            byte[] encrypted_login_password = Encryption.encrypt_AES(login_password);
            String hql = "SELECT COUNT(*) FROM LoginInfo login_info WHERE login_info.loginName=:param_login_name AND login_info.loginPassword=:param_login_password";
            Query query = hibernate_session.createQuery(hql);
            query.setString("param_login_name", login_name);
            query.setBinary("param_login_password", encrypted_login_password);
            List<Long> result_list = query.list();
            long result = result_list.get(0);
            if (result > 0) {
                return result;
            } else {
                return 0;
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return 0;
    }

    /**
     *Get the user by user's login name and password
     * @param login_name
     * @param login_password
     * @return
     */
    public static Users get_user_by_login_name_and_password(String login_name, String login_password) {
        Users user = null;
        Session hibernate_session = HibernateUtil.getSessionFactory().openSession();
        hibernate_session.beginTransaction();
        try {
            byte[] encrypted_login_password = Encryption.encrypt_AES(login_password);
            String hql = "SELECT userId FROM LoginInfo login_info WHERE login_info.loginName=:param_login_name AND login_info.loginPassword=:param_login_password";
            Query query = hibernate_session.createQuery(hql);
            query.setString("param_login_name", login_name);
            query.setBinary("param_login_password", encrypted_login_password);
            List<Integer> result_list = query.list();
            int user_id = result_list.get(0);
            if (user_id > 0) {
                user = (Users) hibernate_session.get(Users.class, user_id);
            } else {
                return null;
            }
        } catch (Exception e) {
            hibernate_session.flush();
            hibernate_session.close();
        }
        hibernate_session.flush();
        hibernate_session.close();
        return user;
    }
}
