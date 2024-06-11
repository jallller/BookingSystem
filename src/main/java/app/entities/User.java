package app.entities;




public class User {
    private int user_id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String roles;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public User() {
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, String password, String name, String phone, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int user_id, String email, String password, String name, String phone, String roles) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.roles = roles;
    }
}

