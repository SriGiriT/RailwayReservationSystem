public class User {
    int id;
    static int idGenerator = 0;
    String name;
    String password;
    String mailId;
    public User(String name, String mailId, String password) {
        this.id = ++idGenerator;
        this.name = name;
        this.password = password;
        this.mailId = mailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mailId='" + mailId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

}
