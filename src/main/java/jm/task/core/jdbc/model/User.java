package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User (" + "name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ')';
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        User user = (User) object;
        return this.age == user.age
                && (this.name == user.name || (this.name != null && this.name.equals(user.getName())))
                && (this.lastName == user.lastName || (this.lastName != null && this.lastName.equals(user.getLastName())));
    }


    @Override
    public int hashCode() {
        return 31 + (age == null ? 0 : age.hashCode()) + (name == null ? 0 : name.hashCode()) + (lastName == null ? 0 : lastName.hashCode());
    }
}
