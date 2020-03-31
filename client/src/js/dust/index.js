import { MOCK_DATA } from '../../data/mock.js';
import { DEFAULT_STATION, DUST_GARADE } from '../../constant/constant.js';
import { $SELETOR, $SELETOR_ALL } from '../../util/index.js';
import { TabNav } from '../../util/tabNav.js';

const DUST_STATION = MOCK_DATA.DUST_STATION;
const DUST_TIMELINE = MOCK_DATA.DUST_TIMELINE;

const DUST_ELEMENT = {
  wrap: $SELETOR('#dust'),
  emoji: $SELETOR('.status-img'),
  station: $SELETOR('.status-station strong'),
  statusTxt: $SELETOR('.status-txt'),
  value: $SELETOR('.display-value span'),
  time: $SELETOR('.display-time span'),
  timeline: $SELETOR('.dust-timeline')
};

const timelineList = DUST_TIMELINE.reduce((list, item) => {
  const dustGrade = item.pm10Grade;
  const dustValue = item.pm10Value;
  const dustProgressWidth = dustValue / 2;
  list += `<li><span class="dust-grade${dustGrade}" style="width:${dustProgressWidth}%">${dustValue}</span></li>`;
  return list;
}, '');

const tabNav = new TabNav({
  tabNavItems: $SELETOR_ALL('.tab-nav li'),
  tabContents: $SELETOR_ALL('.tab-contents section')
});

const getDustStatusMessage = grade => {
  switch (grade) {
    case '1':
      return DUST_GARADE.STATUS_MESSAGE.GOOD;
    case '2':
      return DUST_GARADE.STATUS_MESSAGE.NORMAL;
    case '3':
      return DUST_GARADE.STATUS_MESSAGE.BAD;
    case '4':
      return DUST_GARADE.STATUS_MESSAGE.VERY_BAD;
  }
};

const updateDustGradeView = ({ target, station, grade, statusMessage }) => {
  return (
    target.wrap.classList.add(`grade-${grade}`),
    (target.station.innerHTML = station || DEFAULT_STATION),
    (target.emoji.innerHTML = `<img src="../../assets/emoji/grade_${grade}.png" alt="${statusMessage}">`)
  );
};

const updateDustTimelineView = ({ target, list }) => {
  return target.timeline.insertAdjacentHTML('beforeend', list);
};

const dustViewInit = () => {
  updateDustGradeView({
    target: DUST_ELEMENT,
    station: DUST_STATION,
    grade: DUST_TIMELINE[0].pm10Grade,
    statusMessage: getDustStatusMessage(DUST_TIMELINE[0].pm10Grade)
  });
  updateDustTimelineView({
    target: DUST_ELEMENT,
    list: timelineList
  });
};

dustViewInit();