package net.etna.myapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("password")
    @Expose
    private String password;

    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}
    public String  getName(){return name;}
    public void setName(String name){this.name = name;}
    public String  getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String  getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public Integer  getAge(){return age;}
    public void setAge(Integer age){this.age = age;}
    public String   getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public String getFirstname(){return firstname;}
    public void setFirstname(String firstname){this.firstname = firstname;}
}
