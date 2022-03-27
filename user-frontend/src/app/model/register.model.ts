export class Register {
  public username: string;
  public password: string;
  public name: string;
  public lastName: string;
  public email: string;

  constructor(username: string, password: string, name: string, lastName: string, eMail: string) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.lastName = lastName;
    this.email = eMail;
  }
}
