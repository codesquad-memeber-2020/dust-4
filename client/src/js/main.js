
import { $SELETOR_ALL } from '../util/index.js';
import { TabNav } from './tabNav/tabNav.js';
import { initStatus } from './dustStatus/index.js';
import { initForecast } from './dustForecast/index.js';

const tabNav = new TabNav({
  tabNavItems: $SELETOR_ALL('.tab-nav li'),
  tabContents: $SELETOR_ALL('.tab-contents section')
});

initStatus();
initForecast();