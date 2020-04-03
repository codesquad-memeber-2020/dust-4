import { $SELETOR, $SELETOR_ALL, $GET } from '../../util/index.js';

$GET(`http://13.124.46.74:8080/forecast`).then(data => {
  localStorage.setItem('DUST_FORECAST', JSON.stringify(data));
});

const dustData = localStorage.getItem('DUST_FORECAST');
const dustDataParse = JSON.parse(dustData);
const img = dustDataParse.content.images;

const FORECAST_ELEMENT = {
  display: $SELETOR('.info-display img'),
  slider: $SELETOR('.slider'),
  btn: $SELETOR('#btn')
};

const init = () => {};

FORECAST_ELEMENT.slider.addEventListener('input', e => {
  const value = parseInt(e.target.value);
  const step = 100 / img.length;
  if (value === 100) return;
  const printImg = (FORECAST_ELEMENT.display.src = img[parseInt(value / step)]);
  return printImg;
});

export { init };
