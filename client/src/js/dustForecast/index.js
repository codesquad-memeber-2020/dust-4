const img = [
  'http://www.airkorea.or.kr/file/viewImage/?atch_id=138847',
  'http://www.airkorea.or.kr/file/viewImage/?atch_id=138845',
  'http://www.airkorea.or.kr/file/viewImage/?atch_id=138846'
];
const imgEl = document.querySelector('.info-display img');
const sliderEl = document.querySelector('.slider');
const init = () => {};

sliderEl.addEventListener('input', e => {
  const value = parseInt(e.target.value);
  const step = 100 / img.length;
  if (value === 100) return;
  return imgEl.src = img[parseInt(value / step)];
});

export { init };
