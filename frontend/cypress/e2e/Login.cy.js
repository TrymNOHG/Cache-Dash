describe('Login Component', () => {
    beforeEach(() => {
        cy.visit('/login');
    });

    it('should allow a user to login successfully', () => {
        cy.get('#usernameInput').type('tomaber');
        cy.get('#passwordInput').type('123123123');

        cy.get('#submit_button').should('be.enabled');
    });

    it('should have the submit button disabled because missing input', () => {
        cy.get('#passwordInput').type('password123');

        cy.get('#submit_button').should('be.disabled');

    });

    it('should have the submit button disabled because missing input', () => {
        cy.get('#usernameInput').type('username');

        cy.get('#submit_button').should('be.disabled');
    });

    it('should have the submit button enabled, and logged in', () => {
        cy.get('#usernameInput').type('tomaber');
        cy.get('#passwordInput').type('123123123')

        cy.get('#submit_button').click();

        cy.url().should('equal', Cypress.config().baseUrl + '/')
    });
});