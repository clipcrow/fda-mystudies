// Replace the example domain name with your deployed address.
export const environment = {
  production: true,
  // remove http/https to appear relative. xsrf-token skips absolute paths.
  participantManagerDatastoreUrl:
    '//participants.kyoto-demo.clipcrow.com/participant-manager-datastore',
  baseHref: '/participant-manager/',
  hydraLoginUrl: 'https://participants.kyoto-demo.clipcrow.com/oauth2/auth',
  authServerUrl: 'https://participants.kyoto-demo.clipcrow.com/auth-server',
  authServerRedirectUrl:
    'https://participants.kyoto-demo.clipcrow.com/auth-server/callback',
  hydraClientId: 'QFMrv678quwk7Maf',
  appVersion: 'v0.1',
  termsPageTitle: 'terms title goes here',
  termsPageDescription: 'terms description goes here',
  aboutPageTitle: 'about page title goes here',
  aboutPageDescription: 'about page description goes here',
};
