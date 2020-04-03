import { DUST_API_URL } from '../constant/constant.js';
import { $SELETOR, $SELETOR_ALL, $GET } from '../util/index.js';
import { TabNav } from './tabNav/tabNav.js';
import { initStatus } from './dustStatus/index.js';
import { initForecast } from './dustForecast/index.js';

const tabNav = new TabNav({
  tabNavItems: $SELETOR_ALL('.tab-nav li'),
  tabContents: $SELETOR_ALL('.tab-contents section')
});

// function getLocation() {
//   if (navigator.geolocation) return navigator.geolocation.getCurrentPosition(showPosition);
// }
// const showPosition = position => {
//   const lati = position.coords.latitude;
//   const long = position.coords.longitude;
//   $GET(`${DUST_API_URL.status}?latitude=${lati}&longitude=${long}`).then(data => {
//     localStorage.setItem('DUST_STATUS', JSON.stringify(data));
//   });
// };
// const defualtPosition = () => {
//   $GET(`${DUST_API_URL.status}?latitude=37.491076&longitude=127.033353`).then(data => {
//     localStorage.setItem('DUST_STATUS', JSON.stringify(data));
//   });
// }

// getLocation();
// dustStatusInit();
initStatus();
initForecast();