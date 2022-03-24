import {DomSanitizer, SafeUrl} from "@angular/platform-browser";

export class Media {
  private _id: number;
  private _museumId: number;
  private _url: string;
  private _isVideo: boolean;


  constructor(id: number, museumId: number, url: string, isVideo: boolean, private sanitizer: DomSanitizer) {
    this._id = id;
    this._museumId = museumId;
    this._url = url;
    this._isVideo = isVideo;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get museumId(): number {
    return this._museumId;
  }

  set museumId(value: number) {
    this._museumId = value;
  }

  get url(): string {
    return <string>this.sanitizer.bypassSecurityTrustResourceUrl(this._url);
  }

  set url(value: string) {
    this._url = value;
  }

  get isVideo(): boolean {
    return this._isVideo;
  }

  set isVideo(value: boolean) {
    this._isVideo = value;
  }
}
