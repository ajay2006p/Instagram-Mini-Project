
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class Style {

    public void style(String word) {
        System.out.println("================ " + word + " ====================");
    }

    public void style2(String word) {
        System.out.println("-------------------- " + word + " --------------------------");
    }

    public void style3(String word) {
        System.out.println("______________________ " + word + " ____________________________");
    }

}

class Info extends Style {
    private String Name;
    private String pass;
    private String Uname;
    private String bio;
    private Set<String> Following = new HashSet<>();
    private Set<String> follwers = new HashSet<>();
    private List<String> post = new ArrayList<>();

    Info(String Name, String pass, String Uname) {

        this.Name = Name;
        this.pass = pass;
        this.Uname = Uname;

    }

    public void setFollowing(Set<String> following) {
        Following = following;
    }

    public void setFollwers(Set<String> follwers) {
        this.follwers = follwers;
    }

    public void setPost(List<String> post) {
        this.post = post;
    }

    public String getBio() {
        return bio;
    }

    public Set<String> getFollowing() {
        return Following;
    }

    public Set<String> getFollwers() {
        return follwers;
    }

    public List<String> getPost() {
        return post;
    }

    public String getName() {
        return Name;
    }

    public String getPass() {
        return pass;
    }

    public String getUname() {
        return Uname;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}

class Method  {
    public void Check_Profile(Scanner sc, String Target, HashMap<String, Info> db) {
        Style s = new Style();
        s.style("Profile");

        if (!db.containsKey(Target)) {
            System.out.println("Error : UserName IS Missing ");

        }
        s.style2("@" + Target);
        Info t = db.get(Target);
        System.out.println("Name: " + t.getName());

        System.out.println("Bio");
        System.out.println(t.getBio());
        s.style3("");
        System.out.println("");
        System.out.println(
                "Post | " + t.getPost() + " Following | " + t.getFollowing() + " Followers | " + t.getFollwers());
        s.style3("");

        System.out.println("1. Edit Bio\n2. Back...");
        int n = Integer.parseInt(check(sc, "Enter The Choose "));
        Info U = db.get(Target);

        switch (n) {
            case 1:
                System.out.println("Enter the Bio");
                String bio = sc.nextLine();
                U.setBio(bio);
                s.style2("Bio Update");
                Check_Profile(sc, Target, db);

                break;
            case 2:
                home_page(sc, db, Target);
                break;

            default:
                    Check_Profile(sc, Target, db);
                break;
        }

    }

    public void Other_Profile(Scanner sc, HashMap<String, Info> db) {
        Style s = new Style();

        s.style("Other_Profile");
        try {
            String Target = check(sc, "Enter The UserName ");
            if (!db.containsKey(Target)) {
                System.out.println("Error : UserName IS Missing ");

            }
            s.style2("@" + Target);
            Info t = db.get(Target);
            System.out.println("Name: " + t.getName());

            System.out.println("Bio");
            System.out.println(t.getBio());
            s.style3("");
            System.out.println("");
            System.out.println(
                    "Post | " + t.getPost() + " Following | " + t.getFollowing() + " Followers | " + t.getFollwers());
            s.style3("");
            System.out.println("1. Serach Other \n2. Back...");
            int n = Integer.parseInt(check(sc, "Enter The Choose "));
            Info U = db.get(Target);

            switch (n) {
                case 1:
                    Other_Profile(sc, db);
                    break;
                case 2:
                    home_page(sc, db, Target);
                    break;

            }

        } catch (Exception e) {
            System.out.println("Error  : " + e.getMessage());
            Other_Profile(sc, db);
        }

    }

    public void Add_Friend(Scanner sc, HashMap<String, Info> db, String Uname) {

        Style s = new Style();

        s.style2("Add Friend");
        try {
            String un = check(sc, "Enter the UserName");

            if (!db.containsKey(un) || Uname.equals(un)) {
                System.out.println("! User Not Found ;)  ");

            } else {

                Info t = db.get(Uname);
                t.getFollowing().add(un);
                Info f = db.get(un);

                f.getFollwers().add(Uname);
                s.style2(un + " Add As Friend ");
                System.out.println("1. Add Friend \n2. Back...");
                int n = Integer.parseInt(check(sc, "Enter The Choose "));

                switch (n) {
                    case 1:
                        Add_Friend(sc, db, Uname);

                        break;
                    case 2:
                        home_page(sc, db, Uname);
                        break;

                }
            }
        } catch (Exception e) {
            System.out.println(" Error :  " + e.getMessage());
            Add_Friend(sc, db, Uname);
        }

    }

    public void View_Post(Scanner sc, HashMap<String, Info> db) {

        Style s = new Style();

        try {
            s.style("View Post");
            String Uname = check(sc, "Enter the UserName ");

            Info t = db.get(Uname);

            System.out.println(t.getPost());
            int num = Integer.parseInt(check(sc, "Enter the Choose\n1. View Other Post\n2. Back... "));
            switch (num) {
                case 1:
                    View_Post(sc, db);
                    break;

                case 2:
                    home_page(sc, db, Uname);
                    break;

            }

        } catch (Exception e) {
            System.out.println("Erorr : " + e.getMessage());

        }

    }

    public void Add_post(Scanner sc, HashMap<String, Info> db, String Uname) {
        Style s = new Style();

        s.style("Add Post");
        String post = check(sc, "Enter The Post ( Only-Text )");

        Info U = db.get(Uname);

        U.getPost().add(post);

        System.out.println("1. Add Post \n2. Back...");
        int n = Integer.parseInt(check(sc, "Enter The Choose "));

        switch (n) {
            case 1:
                Add_post(sc, db, Uname);
                break;
            case 2:
                home_page(sc, db, Uname);
                break;

            default:
                break;
        }

    }

    public void home_page(Scanner sc, HashMap<String, Info> db, String Uname) {

        // String serch;
        // String view_post;

        // boolean Add_friend;
        // boolean log_out;
        // boolean exit;
        Style s = new Style();
        while (true) {
            try {
                s.style("Welcome to Home Page ");
                s.style3("Choose 1-4");
                System.out.println(
                        "1. Check Profile\n2. Add Friend\n3. View Post\n4. Other_Profile\n5. Add Post\n6. Log-Out");
                int Choise = Integer.parseInt(sc.nextLine());

                switch (Choise) {
                    case 1:
                        Check_Profile(sc, Uname, db);
                        break;
                    case 2:
                        Add_Friend(sc, db, Uname);
                        break;
                    case 3:
                        View_Post(sc, db);
                        break;

                    case 4:

                        Other_Profile(sc, db);
                        break;
                    case 5:
                        Add_post(sc, db, Uname);

                        break;
                    case 6:
                        s.style2("LOG-OUT");
                        break;

                    default:
                        break;
                }

                break;

            } catch (Exception e) {
                System.out.println(" Error : Someting is Wrong Try Again" + e.getMessage());
            }

        }

    }

    public  String check(Scanner sc, String promat) {

        while (true) {

            System.out.print(promat + ":");
            String line = sc.nextLine().trim();
            if (!line.isEmpty()) {
                return line;

            }
            System.out.println("====================== Errorr ===========================");

        }
    }

    
}

public class index {

    public static void main(String[] args) {

        HashMap<String, Info> DataBase = new HashMap<>();

        // key , obj
        Method M = new Method();
        Style s = new Style();
        Scanner sc = new Scanner(System.in);
        s.style("");
        System.out.println("WelCome To MINI INSTAGRAM - Console");
        s.style("");

        while (true) {
            try {

                System.out.println("1. Signup");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                int c = 0;
                try {

                    c = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    s.style("Invalid option! Please enter a number.");

                }

                switch (c) {
                    case 1:
                        s.style3("Signup");
                        try {
                            String name = M.check(sc, " Enter The Name ");
                            String pass = M.check(sc, " Enter The Pass ");
                            String Uname = M.check(sc, " Enter The Uname ");

                            if (DataBase.containsKey(Uname)) {
                                System.out.println(" Username already taken!");
                                break;
                            }

                            DataBase.put(Uname, new Info(name, pass, Uname));
                            // obj . as key uname , info obj ); new user

                            s.style("User created successfully!");

                        } catch (Exception e) {
                            System.out.println("Unexpected error during signup: " + e.getMessage());
                        }

                        break;

                    case 2:

                        s.style3("Login");
                        try {
                            String Una = M.check(sc, " Enter The Uname ");
                            String p = M.check(sc, " Enter The Pass ");
                            if (!DataBase.containsKey(Una)) {
                                System.out.println("Username Not Found");
                                break;
                            } else {
                                Info user = DataBase.get(Una);

                                if (user.getPass().equals(p)) {
                                    s.style2("Login Successful!");
                                    System.out.println("Welcome, " + user.getName() + "!");
                                    M.home_page(sc, DataBase, user.getUname());

                                } else {
                                    System.out.println(" Wrong Password!");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Unexpected error during LOGIN " + e.getMessage());
                        }

                        break;
                    case 3:
                        s.style3("EXit.............");
                        return;
                    default:
                        s.style3(" Choose 1-3 Only ");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong! Try again");
            }

        }

    }
}
