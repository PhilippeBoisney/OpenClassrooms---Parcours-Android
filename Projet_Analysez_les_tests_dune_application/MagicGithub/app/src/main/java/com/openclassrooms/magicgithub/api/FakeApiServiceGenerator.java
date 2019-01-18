package com.openclassrooms.magicgithub.api;

import com.openclassrooms.magicgithub.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class FakeApiServiceGenerator {


    static List<User> generateUsers() {
        return new ArrayList<>(FAKE_USERS);
    }

    public static List<User> FAKE_USERS = Arrays.asList(
            new User("001", "Jake", "https://api.adorable.io/AVATARS/512/1.png"),
            new User("002", "Paul", "https://api.adorable.io/AVATARS/512/2.png"),
            new User("003", "Phil", "https://api.adorable.io/AVATARS/512/3.png"),
            new User("004", "Guillaume", "https://api.adorable.io/AVATARS/512/4.png"),
            new User("005", "Francis", "https://api.adorable.io/AVATARS/512/5.png"),
            new User("006", "George", "https://api.adorable.io/AVATARS/512/6.png"),
            new User("007", "Louis", "https://api.adorable.io/AVATARS/512/7.png"),
            new User("008", "Mateo", "https://api.adorable.io/AVATARS/512/8.png"),
            new User("009", "April", "https://api.adorable.io/AVATARS/512/9.png"),
            new User("010", "Louise", "https://api.adorable.io/AVATARS/512/10.png"),
            new User("011", "Elodie", "https://api.adorable.io/AVATARS/512/11.png"),
            new User("012", "Helene", "https://api.adorable.io/AVATARS/512/12.png"),
            new User("013", "Fanny", "https://api.adorable.io/AVATARS/512/13.png"),
            new User("014", "Laura", "https://api.adorable.io/AVATARS/512/14.png"),
            new User("015", "Gertrude", "https://api.adorable.io/AVATARS/512/15.png"),
            new User("016", "Chloé", "https://api.adorable.io/AVATARS/512/16.png"),
            new User("017", "April", "https://api.adorable.io/AVATARS/512/17.png"),
            new User("018", "Marie", "https://api.adorable.io/AVATARS/512/18.png"),
            new User("019", "Henri", "https://api.adorable.io/AVATARS/512/19.png"),
            new User("020", "Rémi", "https://api.adorable.io/AVATARS/512/20.png")
    );

    public static List<User> FAKE_USERS_RANDOM = Arrays.asList(
            new User("021", "Lea", "https://api.adorable.io/AVATARS/512/21.png"),
            new User("022", "Geoffrey", "https://api.adorable.io/AVATARS/512/22.png"),
            new User("023", "Simon", "https://api.adorable.io/AVATARS/512/23.png"),
            new User("024", "André", "https://api.adorable.io/AVATARS/512/24.png"),
            new User("025", "Leopold", "https://api.adorable.io/AVATARS/512/25.png")
    );
}
