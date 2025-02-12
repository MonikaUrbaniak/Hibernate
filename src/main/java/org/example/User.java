package org.example;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    public User(){
    }
    public User(String name, LocalDate birthDate){
        this.name = name;
        this.birthDate = birthDate;
    }
    public int getId(){ return id; }
    public String getname(){ return name; }
    public LocalDate getBirthDate(){ return birthDate; }

    public void setId(int id){ this.id = id;}
    public void setName(String name){ this.name = name;}
    public void setBirthDate(LocalDate birthDate){ this.birthDate = birthDate;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
