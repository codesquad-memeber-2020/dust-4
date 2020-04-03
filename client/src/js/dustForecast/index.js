import { DUST_API_URL } from '../../constant/constant.js';
import { $SELETOR, $SELETOR_ALL, $GET } from '../../util/index.js';

const FORECAST_DATA = {
  grade: null,
  overall: null,
  img: null
};

const FORECAST_ELEMENT = {
  display: $SELETOR('.info-display img'),
  slider: $SELETOR('.slider'),
  btn: $SELETOR('#btn'),
  grade: $SELETOR('.info-grade'),
  overall: $SELETOR('.info-overall')
};

const initForecast = async () => {
  await $GET(DUST_API_URL.forecast)
    .then(data => {
    localStorage.setItem('DUST_FORECAST', JSON.stringify(data));
  });
  const dustData = localStorage.getItem('DUST_FORECAST');
  const dustDataParse = JSON.parse(dustData);
  FORECAST_DATA.grade = dustDataParse.content.informGrade;
  FORECAST_DATA.overall = dustDataParse.content.informOverall;
  FORECAST_DATA.img = dustDataParse.content.images;

  FORECAST_ELEMENT.grade.innerHTML = FORECAST_DATA.grade;
  FORECAST_ELEMENT.overall.innerHTML = FORECAST_DATA.overall;
};

FORECAST_ELEMENT.slider.addEventListener('input', e => {
  const value = parseInt(e.target.value);
  const step = 100 / FORECAST_DATA.img.length;
  if (value === 100) return;
  const printImg = (FORECAST_ELEMENT.display.src = FORECAST_DATA.img[parseInt(value / step)]);
  return printImg;
});

export { initForecast };
