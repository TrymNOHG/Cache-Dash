describe('Registration Component', () => {
    beforeEach(() => {
        cy.visit('/register');
    });

    it('should allow a user to register successfully', () => {
        cy.get('#nameInput').type('name');
        cy.get('#usernameInput').type('username');
        cy.get('#passwordInput').type('password123');
        cy.get('#emailInput').type('test@example.com');
        cy.get('#dateInput').type('1990-01-01');
        cy.get('#phoneNumber').type('1234567890');

        cy.get('#submit_button').should('be.enabled');
    });

    it('should have the submit button disabled because missing input', () => {
        cy.get('#usernameInput').type('username');
        cy.get('#passwordInput').type('password123');
        cy.get('#emailInput').type('test@example.com');
        cy.get('#dateInput').type('1990-01-01');
        cy.get('#phoneNumber').type('1234567890');

        cy.get('#submit_button').should('be.disabled');

    });

    it('should have the submit button disabled because missing input', () => {
        cy.get('#nameInput').type('name');
        cy.get('#passwordInput').type('password123');
        cy.get('#emailInput').type('test@example.com');
        cy.get('#dateInput').type('1990-01-01');
        cy.get('#phoneNumber').type('1234567890');

        cy.get('#submit_button').should('be.disabled');

    });

    it('should have the submit button disabled because missing input', () => {
        // Leave out required fields
        cy.get('#nameInput').type('name');
        cy.get('#usernameInput').type('username');
        cy.get('#emailInput').type('test@example.com');
        cy.get('#dateInput').type('1990-01-01');
        cy.get('#phoneNumber').type('1234567890');

        cy.get('#submit_button').should('be.disabled');

    });

    it('should have the submit button disabled because missing input', () => {
        // Leave out required fields
        cy.get('#nameInput').type('name');
        cy.get('#usernameInput').type('username');
        cy.get('#passwordInput').type('password123');
        cy.get('#dateInput').type('1990-01-01');
        cy.get('#phoneNumber').type('1234567890');

        cy.get('#submit_button').should('be.disabled');
    });
});