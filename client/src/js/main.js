import { $SELETOR, $SELETOR_ALL, $GET } from '../util/index.js';
import { TabNav } from './tabNav/tabNav.js';
import { dustStatusInit } from './dustStatus/index.js';
import { init } from './dustForecast/index.js';

const tabNav = new TabNav({
  tabNavItems: $SELETOR_ALL('.tab-nav li'),
  tabContents: $SELETOR_ALL('.tab-contents section')
});

const DUST_API_URL = {
  status: 'http://13.124.46.74:8080/dust-status',
  forecast: 'http://13.124.46.74:8080/forecast'
};

function getLocation() {
  if (navigator.geolocation) return navigator.geolocation.getCurrentPosition(showPosition);
}
const showPosition = position => {
  const lati = position.coords.latitude;
  const long = position.coords.longitude;
  $GET(`${DUST_API_URL.status}?latitude=${lati}&longitude=${long}`).then(data => {
    localStorage.setItem('DUST_STATUS', JSON.stringify(data));
  });
};

getLocation();
dustStatusInit();
init();