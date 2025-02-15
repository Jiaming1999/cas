const puppeteer = require('puppeteer');
const cas = require('../../cas.js');

(async () => {
    const browser = await puppeteer.launch(cas.browserOptions());
    const page = await cas.newPage(browser);
    await page.goto("https://localhost:8443/cas/login?authn_method=mfa-duo");
    await cas.loginWith(page, "casuser", "Mellon");
    await cas.loginDuoSecurityBypassCode(page, 'universal-prompt');
    await page.waitForTimeout(3000)
    await cas.screenshot(page);
    await cas.assertInnerText(page, '#content div h2', "Log In Successful");
    await cas.assertTicketGrantingCookie(page);
    await browser.close();
})();
