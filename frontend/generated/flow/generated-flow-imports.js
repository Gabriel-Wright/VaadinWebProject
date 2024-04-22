import 'Frontend/generated/jar-resources/flow-component-renderer.js';
import '@vaadin/side-nav/theme/lumo/vaadin-side-nav.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/vertical-layout/theme/lumo/vaadin-vertical-layout.js';
import '@vaadin/app-layout/theme/lumo/vaadin-app-layout.js';
import '@vaadin/tooltip/theme/lumo/vaadin-tooltip.js';
import '@vaadin/app-layout/theme/lumo/vaadin-drawer-toggle.js';
import '@vaadin/virtual-list/theme/lumo/vaadin-virtual-list.js';
import 'Frontend/generated/jar-resources/virtualListConnector.js';
import '@vaadin/icon/theme/lumo/vaadin-icon.js';
import '@vaadin/side-nav/theme/lumo/vaadin-side-nav-item.js';
import '@vaadin/horizontal-layout/theme/lumo/vaadin-horizontal-layout.js';
import '@vaadin/multi-select-combo-box/theme/lumo/vaadin-multi-select-combo-box.js';
import 'Frontend/generated/jar-resources/comboBoxConnector.js';
import '@vaadin/button/theme/lumo/vaadin-button.js';
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
  if (key === 'c6cbb74ba6b82c4ca32b3e9cf34e19a6b164e84a925e5d15161d0efe058531b3') {
    pending.push(import('./chunks/chunk-88510b02fbafdbe4d02c389141fb9ce4376ce3cec0a1f8ab34bca9882cb1be17.js'));
  }
  if (key === '06bda167530166660205426e3e1dfb2c844956ecdaa783018966b40efe2e35c4') {
    pending.push(import('./chunks/chunk-b9cee5494d79a60087bd672fe6d9a03cd08a15b103c71e0d25d21f035887a8d5.js'));
  }
  if (key === '830afaa2837cfb2afb0563a286dde8aa3272f91bdf8bb876f622c439b47af290') {
    pending.push(import('./chunks/chunk-88510b02fbafdbe4d02c389141fb9ce4376ce3cec0a1f8ab34bca9882cb1be17.js'));
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