import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/side-nav/src/vaadin-side-nav.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/app-layout/src/vaadin-app-layout.js';
import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/app-layout/src/vaadin-drawer-toggle.js';
import '@vaadin/virtual-list/src/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/icon/src/vaadin-icon.js';
import '@vaadin/side-nav/src/vaadin-side-nav-item.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/multi-select-combo-box/src/vaadin-multi-select-combo-box.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/button/src/vaadin-button.js';
import 'Frontend/generated/jar-resources/buttonFunctions.js';
import 'Frontend/generated/jar-resources/lit-renderer.ts';
import 'Frontend/js/custom.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '830afaa2837cfb2afb0563a286dde8aa3272f91bdf8bb876f622c439b47af290') {
    pending.push(import('./chunks/chunk-8825c01b2272d8f6a90e5d82cb332c97113af5ab1a0d7ef23645d43f3f758be7.js'));
  }
  if (key === '06bda167530166660205426e3e1dfb2c844956ecdaa783018966b40efe2e35c4') {
    pending.push(import('./chunks/chunk-c31517bc5d8b34b9cb41fd5f32803030a148eb2c04fcb0fd3fed6044c716b838.js'));
  }
  if (key === 'c6cbb74ba6b82c4ca32b3e9cf34e19a6b164e84a925e5d15161d0efe058531b3') {
    pending.push(import('./chunks/chunk-8825c01b2272d8f6a90e5d82cb332c97113af5ab1a0d7ef23645d43f3f758be7.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;
window.Vaadin.Flow.resetFocus = () => {
 let ae=document.activeElement;
 while(ae&&ae.shadowRoot) ae = ae.shadowRoot.activeElement;
 return !ae || ae.blur() || ae.focus() || true;
}