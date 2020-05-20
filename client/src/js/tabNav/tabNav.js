export class TabNav {
  constructor({ tabNavItems, tabContents }) {
    this.tabNavItems = tabNavItems;
    this.tabContents = tabContents;
    this.init();
  }
  tabContent(e) {
    let menuIndex = [...this.tabNavItems].indexOf(e.target);
    [...this.tabContents].map(cont => {
      [...this.tabContents].indexOf(cont) === menuIndex ? cont.classList.add('visible') : cont.classList.remove('visible');
    });
  }
  setSelectItem(e) {
    [...this.tabNavItems].forEach(item => (item.classList.value === 'select' ? item.classList.remove('select') : e.target.classList.add('select')));
    if (e.target.classList.value !== 'select') {
      e.target.classList.add('select');
    }
    this.tabContent(e);
  }

  onEvent() {
    [...this.tabNavItems].map(item => {
      item.addEventListener('click', e => {
        e.stopPropagation();
        this.setSelectItem(e);
      });
    });
  }

  init() {
    const firstIndex = 0;
    const menuList = [...this.tabNavItems];
    const contentsList = [...this.tabContents];

    menuList[firstIndex].classList.add('select');
    contentsList[firstIndex].classList.add('visible');
    this.onEvent();
  }
}
