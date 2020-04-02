import { $SELETOR, $SELETOR_ALL } from '../util/index.js';
import { TabNav } from './tabNav/tabNav.js';
import { dustStatusInit } from './dustStatus/index.js'
import { init } from './dustForecast/index.js'

const tabNav = new TabNav({
  tabNavItems: $SELETOR_ALL('.tab-nav li'),
  tabContents: $SELETOR_ALL('.tab-contents section')
});

window.addEventListener('DOMContentLoaded', ()=>{
  dustStatusInit();
  init()
})