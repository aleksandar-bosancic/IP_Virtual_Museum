export class LogEntry {
  public username: string;
  public timestamp: string;
  public category: string;
  public label: string;

  constructor(username: string, timestamp: string, category: string, label: string) {
    this.username = username;
    this.timestamp = timestamp;
    this.category = category;
    this.label = label;
  }
}
