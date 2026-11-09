package com.parabank.uat.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

/**
 * "Accounts Overview" - the first page after login, plus the left-hand "Account Services" menu.
 */
public class AccountsOverviewPage {

    private final Page page;

    private final Locator welcomeText;
    private final Locator title;
    private final Locator accountLinks;

    public AccountsOverviewPage(Page page) {
        this.page = page;
        this.welcomeText = page.locator("#leftPanel .smallText");
        this.title = page.locator("#showOverview h1.title");
        // Only real account rows contain a link; the last "Total" row does not.
        this.accountLinks = page.locator("#accountTable tbody tr td a");
    }

    public Locator welcomeText() {
        return welcomeText;
    }

    public Locator title() {
        return title;
    }

    public Locator accountLinks() {
        return accountLinks;
    }

    // ---- left menu navigation ------------------------------------------------

    public OpenAccountPage goToOpenNewAccount() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Open New Account")).click();
        return new OpenAccountPage(page);
    }

    public TransferFundsPage goToTransferFunds() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Transfer Funds")).click();
        return new TransferFundsPage(page);
    }

    public LoginPage logOut() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log Out")).click();
        return new LoginPage(page);
    }
}
