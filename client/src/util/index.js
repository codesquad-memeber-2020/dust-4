const $SELETOR = target => document.querySelector(target);
const $SELETOR_ALL = target => document.querySelectorAll(target);

const $GET = async (url) => {
  const response = await fetch(url);
  const resPromise = await response.json();
  return resPromise;
}

export { $SELETOR, $SELETOR_ALL, $GET };
