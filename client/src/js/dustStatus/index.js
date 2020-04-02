import { MOCK_DATA } from '../../data/mock.js';
import { DUST_GRADE } from '../../constant/constant.js';
import { $SELETOR, $SELETOR_ALL } from '../../util/index.js';

const INIT_NUMBER = 1;
const DUST_STATION = MOCK_DATA.DUST_STATION;
const DUST_TIMELINE = MOCK_DATA.DUST_TIMELINE;
const DUST_TIMELINE_LENGTH = DUST_TIMELINE.length - 1;

const DUST_ELEMENT = {
  wrap: $SELETOR('#dust'),
  emoji: $SELETOR('.status-img'),
  station: $SELETOR('.status-station strong'),
  statusTxt: $SELETOR('.status-txt'),
  value: $SELETOR('.display-value span'),
  time: $SELETOR('.display-time span'),
  timeline: $SELETOR('.dust-timeline')
};

const dustOption = {
  index: 0,
  scrollStartingPoint: 0,
  scrollEndPoint: 0,
  prevDirection: 0
};

const timelineList = DUST_TIMELINE.reduce((list, item) => {
  const dustGrade = item.pm10Grade;
  const dustValue = item.pm10Value;
  const dustProgressWidth = dustValue / 2;
  list += `<li><span class="dust-grade${dustGrade}" style="width:${dustProgressWidth}%">${dustValue}</span></li>`;
  return list;
}, '');

const saveStartingPoint = e => (dustOption.scrollStartingPoint = e.touches[0].clientY);

const calcIndexTouchMove = (e, dataLength) => {
  dustOption.scrollEndPoint = e.touches[0].clientY;
  const direction = parseInt((dustOption.scrollStartingPoint - dustOption.scrollEndPoint) / 10);
  if (direction > dustOption.prevDirection) {
    ++dustOption.index;
    dustOption.index >= dataLength ? (dustOption.index = dataLength) : dustOption.index;
  }
  if (direction < dustOption.prevDirection) {
    --dustOption.index;
    dustOption.index <= 0 ? (dustOption.index = 0) : dustOption.index;
  }
  return updateDustStatusView(dustOption.index);
};

const updateDustTimelineView = list => DUST_ELEMENT.timeline.insertAdjacentHTML('beforeend', list);

const updateDustStatusView = (curretDust = 0) => {
  const { dataTime, pm10Grade, pm10Value } = DUST_TIMELINE[curretDust];
  const gradeClassList = Object.values(DUST_GRADE.STATUS_CLASS);

  DUST_ELEMENT.station.innerHTML = DUST_STATION;
  DUST_ELEMENT.time.innerHTML = dataTime;
  DUST_ELEMENT.value.innerHTML = pm10Value;
  DUST_ELEMENT.wrap.classList.remove(...gradeClassList);
  DUST_ELEMENT.wrap.classList.add(DUST_GRADE.STATUS_CLASS[pm10Value]);
  DUST_ELEMENT.statusTxt.innerHTML = DUST_GRADE.STATUS_MESSAGE[pm10Value];
  DUST_ELEMENT.emoji.innerHTML = `<img src="../../assets/emoji/grade_${pm10Grade}.png" alt="${DUST_GRADE.STATUS_MESSAGE[pm10Value]}">`;
  return;
};

const dustStatusInit = () => {
  updateDustStatusView(dustOption.index);
  updateDustTimelineView(timelineList);
};

DUST_ELEMENT.timeline.addEventListener('touchstart', saveStartingPoint);
DUST_ELEMENT.timeline.addEventListener('touchmove', e => calcIndexTouchMove(e, DUST_TIMELINE_LENGTH));

export { dustStatusInit };
