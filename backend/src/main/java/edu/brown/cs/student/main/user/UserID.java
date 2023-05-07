package edu.brown.cs.student.main.user;

public class UserID { // TODO: see if email needs to be private too for defensive?
  public String email;
  private String password;

  public UserID(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
