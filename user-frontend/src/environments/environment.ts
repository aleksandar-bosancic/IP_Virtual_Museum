// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiURL: 'http://localhost:9000',
  bankURL: 'http://localhost:8000',
  forecastUrl: 'https://api.openweathermap.org/data/2.5/onecall?',
  weatherApiKey: '5c1644877b838835621078dbc3012ed8',
  forecastOptions: '&units=metric&exclude=minutely,hourly,alerts',
  rssToJsonUrl: 'https://api.rss2json.com/v1/api.json?rss_url=',
  newsFeedUrl: 'https://www.huffpost.com/section/arts/feed',
  infoCategory: 'INFO',
  errorCategory: 'ERROR'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
