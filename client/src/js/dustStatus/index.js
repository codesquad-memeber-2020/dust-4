import { DUST_API_URL, DUST_GRADE } from '../../constant/constant.js';
import { $SELETOR, $GET } from '../../util/index.js';

const DUST_DATA = {
  station: null,
  timeline: null,
  timelineLength: null
}

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
  prevDirection: 0,
  scrollIndex: 0,
  maxScrollIndex: 10
};

const saveStartingPoint = e => (dustOption.scrollStartingPoint = e.touches[0].clientY);

const calcIndexTouchMove = (e, dataLength) => {
  dustOption.scrollEndPoint = e.touches[0].clientY;
  const direction = parseInt((dustOption.scrollStartingPoint - dustOption.scrollEndPoint) / 10);
  if (direction > dustOption.prevDirection) {
    ++dustOption.scrollIndex;
    if(dustOption.scrollIndex === dustOption.maxScrollIndex) {
      ++dustOption.index;
      dustOption.scrollIndex = 0;
    }
    dustOption.index >= dataLength ? (dustOption.index = dataLength) : dustOption.index;
  }
  if (direction < dustOption.prevDirection) {
    ++dustOption.scrollIndex;
    if(dustOption.scrollIndex === dustOption.maxScrollIndex) {
      --dustOption.index;
      dustOption.scrollIndex = 0;
    }
    dustOption.index <= 0 ? (dustOption.index = 0) : dustOption.index;
  }
  console.log(dustOption.index);
  return updateDustStatusView(dustOption.index);
};

const updateDustTimelineView = list => DUST_ELEMENT.timeline.insertAdjacentHTML('beforeend', list);

const updateDustStatusView = (curretDust = 0) => {
  const { dataTime, pm10Grade, pm10Value } = DUST_DATA.timeline[curretDust];
  const gradeClassList = Object.values(DUST_GRADE.STATUS_CLASS);

  DUST_ELEMENT.station.innerHTML = DUST_DATA.station;
  DUST_ELEMENT.time.innerHTML = dataTime;
  DUST_ELEMENT.value.innerHTML = pm10Value;
  DUST_ELEMENT.wrap.classList.remove(...gradeClassList);
  DUST_ELEMENT.wrap.classList.add(DUST_GRADE.STATUS_CLASS[pm10Grade]);
  DUST_ELEMENT.statusTxt.innerHTML = DUST_GRADE.STATUS_MESSAGE[pm10Grade];
  DUST_ELEMENT.emoji.innerHTML = DUST_GRADE.STATUS_EMOJI[pm10Grade];
  return;
};

const initRender = () => {
  const dustData = localStorage.getItem('DUST_STATUS');
  const dustDataParse = JSON.parse(dustData);

  DUST_DATA.station = dustDataParse.location.stationName;
  DUST_DATA.timeline = dustDataParse.content;
  DUST_DATA.timelineLength = DUST_DATA.timeline.length - 1;

  const timelineList = DUST_DATA.timeline.reduce((list, item) => {
    const dustGrade = item.pm10Grade;
    const dustValue = item.pm10Value;
    const dustProgressWidth = dustValue / 2;
    list += `<li><span class="dust-grade${dustGrade}" style="width:${dustProgressWidth}%">${dustValue}</span></li>`;
    return list;
  }, '');
  updateDustStatusView(dustOption.index);
  updateDustTimelineView(timelineList);
};

const initStatus = () => {
  if (navigator.geolocation) return navigator.geolocation.getCurrentPosition(showPosition);
  defualtPosition();
};
const showPosition = async position => {
  const lati = position.coords.latitude;
  const long = position.coords.longitude;
  await $GET(`${DUST_API_URL.status}?latitude=${lati}&longitude=${long}`).then(data => {
    localStorage.setItem('DUST_STATUS6', JSON.stringify(data));
  });
  initRender();
};
const defualtPosition = async () => {
  await $GET(`${DUST_API_URL.status}?latitude=37.491076&longitude=127.033353`).then(data => {
    localStorage.setItem('DUST_STATUS5', JSON.stringify(data));
  });
  initRender();
};


DUST_ELEMENT.timeline.addEventListener('touchstart', saveStartingPoint);
DUST_ELEMENT.timeline.addEventListener('touchmove', e => calcIndexTouchMove(e, DUST_DATA.timelineLength));

export { initStatus };
