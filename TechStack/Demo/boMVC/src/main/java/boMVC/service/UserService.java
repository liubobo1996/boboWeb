package boMVC.service;

import boMVC.Utility;
import boMVC.models.ModelFactory;
import boMVC.models.Todo;
import boMVC.models.User;
import boMVC.models.UserRole;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

class Digest {
    public static String hexFromBytes(byte[] array) {
        String hex = new BigInteger(1, array).toString(16);
        int zeroLength = array.length * 2 - hex.length();
        for (int i = 0; i < zeroLength; i++) {
            hex = "0" + hex;
        }
        return hex;
    }

    public static String md5(String origin) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        md.update(origin.getBytes(StandardCharsets.UTF_8));

        byte[] result = md.digest();
        String hex = hexFromBytes(result);
        return hex;
    }
}

public class UserService {
    public static User add(HashMap<String, String> form) {
        String username = form.get("username");
        String password = form.get("password");
        User m = new User();
        m.username = username;
        m.password = saltedPassword(password);
        m.role = UserRole.normal;

        ArrayList<User> all = load();
        if (all.size() == 0) {
            m.id = 1;
        } else {
            m.id = all.get(all.size() - 1).id + 1;
        }

        all.add(m);
        Utility.log("[User add][%s][%s][%s]", m.username, m.password, m.role);
        save(all);

        return m;
    }

    public static void save(ArrayList<User> list) {
        Utility.log("[User save]");

        String className = User.class.getSimpleName();
        ModelFactory.save(className, list, (model) -> {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(model.id.toString());
            lines.add(model.username);
            lines.add(model.password);
            lines.add(model.role.toString());

            return lines;
        });
    }

    public static ArrayList<User> load() {
        Utility.log("[User load]");

        String className = User.class.getSimpleName();
        ArrayList<User> all = ModelFactory.load(className, 4, (modelData) -> {
            Integer id = Integer.valueOf(modelData.get(0));
            String username = modelData.get(1);
            String password = modelData.get(2);
            UserRole role = UserRole.valueOf(modelData.get(3));

            User m = new User();
            m.id = id;
            m.username = username;
            m.password = password;
            m.role = role;

            return m;
        });

        return all;
    }

    // ????????????
    public static boolean validLogin(HashMap<String, String> form) {
        String username = form.get("username");
        String password = form.get("password");

        // ???????????? ????????????
        String salt = "salt";
        password = Digest.md5(password + salt);

        ArrayList<User> ms = load();
        for (User m : ms) {
            if (m.username.equals(username) && m.password.equals(password)) {
                Utility.log("[????????????]: ??????");
                return true;
            }
        }
        Utility.log("[????????????]: ??????");
        return false;
    }

    // ?????????????????????
    public static User findByUsername(String username) {
        Utility.log("[???????????????????????????]:%s", username);
        ArrayList<User> all = load();
//        return ModelFactory.findBy(all, (model) -> {
//            return model.username.equals(username);
//        });
        return ModelFactory.findBy(all, (model) -> model.username.equals(username));
    }

    // ????????????id??????
    public static User findById(Integer id) {
        Utility.log("[????????????????????????id]:%s", id);
        ArrayList<User> all = load();
        return ModelFactory.findBy(all, (model) -> model.id.equals(id));
    }

    // ??????
    public static User guest() {
        Utility.log("[????????????]:??????");

        User u = new User();
        u.id = -1;
        u.username = "??????";
        u.password = "";
        u.role = UserRole.guest;

        return u;
    }

    // ?????????????????????HTML
    public static String adminUsersHtml(){
        ArrayList<User> all = load();
        StringBuilder allHtml = new StringBuilder();

        for (User m:all) {
            String s = String.format(
                    " <h3>\n" +
                            "   %s:  %s " +
                            " </h3>",
                    m.username,
                    m.password
            );
            allHtml.append(s);
        }

        return allHtml.toString();
    }

    // ??????????????????
    public static void updatePassword(Integer id, String password) {
        ArrayList<User> ms = load();

        for (User e : ms) {
            if (e.id.equals(id)) {
                e.password = saltedPassword(password);
            }
        }

        save(ms);
    }

    // ????????????
    public static String saltedPassword(String password) {
//        String salt = UUID.randomUUID().toString();
        String salt = "salt";
        return Digest.md5(password + salt);
    }

}
